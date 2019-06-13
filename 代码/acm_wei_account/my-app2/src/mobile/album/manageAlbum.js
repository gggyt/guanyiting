import React from 'react';
import { Card , Radio } from 'antd';
import { Tabs, Row, Col } from 'antd';
import { Upload, Icon, Modal, Input,  Dropdown, Menu } from 'antd';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Pagination, Button, Toast} from 'antd-mobile';
import '../../static/my/css/album.css';
import cookie from 'react-cookies';
import {AddAlbum} from '../../config/router.js';
import {SelectAlbum} from '../../config/router.js';
import Photo from './managePhoto';
import {EventEmitter2} from 'eventemitter2';
require('../../static/my/css/invitation.css');
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
      alert('相册名不为空');
      return;
    }
    if (this.state.albumName.length>20) {
      alert('相册名过长');
      return;
    }
    if (this.state.description.length>30) {
      alert('描述过长');
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
            Toast.info('操作成功', 1);
            this.setState({
              visible: false,
            });
            this.setState({albumName:''});
            this.setState({description:''});
            this.setState({isPublic:1});
            emitterAll.emit('add', 'add');
          }
          else {
             Toast.info(data.msg, 1);
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
              <TextArea value={this.state.description} rows={4} className="form-control" placeholder="描述" style={{width:300}} onChange={this.descriptionChange}/>
              </div> 
            </div>
           
          </div>
        </Modal>
      </div>
    );
  }
}
const menu = (
  <Menu >
    <Menu.Item key="1"><Icon type="user" />1st menu item</Menu.Item>
    <Menu.Item key="2"><Icon type="user" />2nd menu item</Menu.Item>
    <Menu.Item key="3"><Icon type="user" />3rd item</Menu.Item>
  </Menu>
);
class AlbumShow extends React.Component{
  constructor(){
    super();
    this.state={
      sty:0,
      width:document.body.clientWidth/2 ,
    }
    console.log(document.body.clientWidth);
  }
  onClick(e) {
    console.log('--');
   // emitter.emit('changeShow', this.props.albumId);
  }
  mouseNot() {
      this.setState({sty:0})
  }
  mouseOn() {
      this.setState({sty:1})
  }
  render(){
    return(
        <div>
          <Col span={12} style={{marginTop:"30px"}}>
              <Card
                className="mCard"
                hoverable
                
                cover={
                  <div>
                  <div className="MimageD" onMouseOver = {this.mouseOn.bind(this)}
                    onMouseOut = {this.mouseNot.bind(this)}>
                     <Link to={'/mobile/photo/'+this.props.albumId}><center><img 
                     className="Mimag" style={{height:this.state.width-40}} alt="example" src={this.props.coverPhotoId} 
                      onClick = {this.onClick.bind(this)} />
                      </center></Link>
                  </div>
                  </div>
                }
                
              >
                <Meta
                title={this.props.albumName} />
              </Card>
            </Col>
        </div>
    );
  }
}
class ShowAllAlbum extends React.Component{
  render(){
    return(
      <div style={{ margin: '0 auto', padding: 10, backgroundColor:'#ffffff'}}>
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
      pageSize: 10,
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
      body:'pageNum='+this.state.nowPage+'&isPublic=2'
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({nowPage: data.resultBean.currentPage});
          this.setState({totalPage: data.resultBean.totalPage});
          this.setState({albumAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({albumAll: ''});
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
          <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange} />
    
      </div>
    );
  }
}
class ManageAlbum extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      show:1,
      albumId: 0,
    };
    emitter.on('changeShow', this.toManagePhoto.bind(this));
  }
  toManagePhoto(key) {
      this.setState({show:2});
      this.setState({albumId:key});
      alert('---'+key);
  }
  toManagePhoto1(key) {
      this.setState({show:1});
      alert('---'+key);
  }
  render() {
    return(
      <div className="bodyMain">
        <div  style={{ margin: '0 auto', padding: 10, backgroundColor:'#ffffff'}}>
          <span>&nbsp;&nbsp;&nbsp;<Link to = "/mobilefirst">首页</Link>>照片</span><hr/>
          {/* <AddAlbumView />  */} 
          <AllAlbum />
        </div>
      </div>
    );
  }
}

export default ManageAlbum;