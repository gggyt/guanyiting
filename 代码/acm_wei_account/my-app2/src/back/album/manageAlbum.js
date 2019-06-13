import React from 'react';
import { Card , Radio } from 'antd';
import { Tabs, Button, Row, Col } from 'antd';
import { Upload, Icon, Modal, Input, Pagination, Dropdown, Menu, message } from 'antd';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import '../static/my/css/album.css';
import cookie from 'react-cookies';
import {AddAlbum} from '../../config/router.js';
import {SelectAlbum} from '../../config/router.js';
import Photo from './managePhoto';
import {EventEmitter2} from 'eventemitter2';
var emitter = new EventEmitter2()
var emitterAll = new EventEmitter2()

const { Meta } = Card;
const TabPane = Tabs.TabPane;
const { TextArea } = Input;
const RadioGroup = Radio.Group;

class AddAlbumView extends React.Component{
  constructor(props) {
    super(props);
    this.state = {
      albumName:'',
      description:'',
      isPublic:1,
    }
    this.albumNameChange = this.albumNameChange.bind(this);
    this.descriptionChange = this.descriptionChange.bind(this);
    this.isPublicChange = this.isPublicChange.bind(this);
  }
  state = { visible: false }

  showModal = () => {
    this.setState({
      visible: true,
    });
  }

  handleOk = (e) => {
    if (this.state.albumName=='') {
      message.error('相册名不为空');
      return;
    }
    if (this.state.albumName.length>20) {
      message.error('相册名过长');
      return;
    }
    if (this.state.description=='') {
      message.error('描述不为空');
      return;
    }
    if (this.state.description.length>30) {
      message.error('描述过长');
      return;
    }
    fetch(AddAlbum,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'albumName='+this.state.albumName+'&description='+this.state.description+'&isPublic='+this.state.isPublic

      }).then(res => res.json()).then(
        data => {
          if(data.code==0) {
            message.success('操作成功');
            this.setState({
              visible: false,
            });
            this.setState({albumName:''});
            this.setState({description:''});
            this.setState({isPublic:1});
            emitterAll.emit('add', 'add');
          }
          else {
             message.error(data.msg);
          }
        }
      )
  }

  handleCancel = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }
  albumNameChange(e) {
    console.log(e.target.value);
    this.setState({albumName:e.target.value});
  }
  descriptionChange(e) {
    this.setState({description:e.target.value});
  }
  isPublicChange = (e) => {
    this.setState({isPublic:e.target.value});
  }
  render() {
    return(
      <div >
        <Button type="primary" icon="plus" onClick={this.showModal}>新增相册</Button>
          <Modal
            title="创建相册"
            visible={this.state.visible}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            okText="确认"
            cancelText="取消"
          >
            <div className="form-inline form" role="form"> 
              <div className="form-group"> 
              <span className="form-label">相册名:&nbsp;&nbsp;</span> 
              <Input value={this.state.albumName} className="form-control" placeholder="相册名" onChange={this.albumNameChange} style={{height:30 , width:300}}/>
            </div> 
            <div className="form-inline form" role="form"> 
              <div className="form-group"> 
              <span className="form-label">描&nbsp;&nbsp;述:&nbsp;&nbsp;</span> 
              <TextArea value={this.state.description} rows={4} className="form-control" placeholder="一句话描述相册" style={{width:300}} onChange={this.descriptionChange}/>
              </div> 
            </div>
            <div className="form-inline form" role="form"> 
              <div className="form-group"> 
              <span className="form-label">权&nbsp;&nbsp;限:&nbsp;&nbsp;</span> 
              <RadioGroup onChange={this.isPublicChange} value={this.state.isPublic}>
                <Radio value={1}>公开</Radio>
                <Radio value={0}>私人</Radio>
              </RadioGroup>
              </div> 
            </div>
          </div>
        </Modal>
      </div>
    );
  }
}
class AlbumShow extends React.Component{
  constructor(){
    super();
  }
  
  render(){
    return(
        <div>
          <Col span={6}>
              <a href={'/Aside/managePhoto/'+this.props.albumId}>
              <Card
                className="card"
                hoverable
                style={{ width: 150 }}
                cover={
                  <div>
                  <div className="iamgeD" >
                    <img className="imag" alt="example" src={this.props.coverPhotoId}  />
                  </div>
                  </div>
                }
              >
                <Meta
                title={this.props.albumName} />
              </Card>
              </a>
            </Col>
        </div>
    );
  }
}
class ShowAllAlbum extends React.Component{
  render(){
    return(
      <div>
      <Row gutter={16}>
      {
        this.props.albumAll.map(function(item){
          return(
            <AlbumShow coverPhotoId={item.coverPhotoId} albumName={item.albumName} albumId={item.albumId}/>
          )

        })
      }
      </Row>
      </div>
    );
  }

}
class AllAlbum extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 8,
      albumAll:[],
    }
    emitterAll.on('add', this.select.bind(this));
  }
  select(key){

    this.getClass();
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    fetch(SelectAlbum, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body:'pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({nowPage: data.resultBean.currentPage});
          this.setState({totalPage: data.resultBean.totalItems/data.resultBean.pageSize});
          this.setState({albumAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({albumAll: ''});
          message.error(data.msg);
        }
      }
    )
  }
   pageChange = (page) => {
    console.log(page);
    this.setState({ nowPage: page }, () => this.getClass());
  }
  render() {
    return(
      <div>
        <ShowAllAlbum albumAll={this.state.albumAll}/>
        <div className="searchPage">
          <Pagination size="small" simple onChange={this.pageChange}
          pageSize={this.state.pageSize} total={this.state.totalPage*this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
        </div>
      </div>
    );
  }
}
class ManageAlbum extends React.Component{
  constructor(props) {
    super(props);
  }
  render() {
   
    return(
      <div >
        <AddAlbumView />
        <AllAlbum />
      </div>
    );
  }
}

export default ManageAlbum;