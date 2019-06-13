import React from 'react';
import { Card } from 'antd';
import { Tabs, Row, Col, Empty  } from 'antd';
import { Upload, Icon, Modal, Avatar,Button,  message } from 'antd';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Pagination,  Toast} from 'antd-mobile';
import ManageAlbum from './manageAlbum';
import '../../static/my/css/album.css';
import cookie from 'react-cookies';
import {SelectPhoto} from '../../config/router.js';
import {UploadImg1} from '../../config/router.js';
import {AddPhoto} from '../../config/router.js';
import {DeleteAlbum} from '../../config/router.js';
import {DeletePhoto} from '../../config/router.js';
import {BeCover} from '../../config/router.js';
import {AlbumDetail} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2';
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()
const { Meta } = Card;
const id = -1;
const Dragger = Upload.Dragger;


const props = {
  defaultFileList: [],
};

class AddPhotoView extends React.Component{
  state = { visible: false }

  showModal = () => {
    console.log('--');
    this.setState({
      visible: true,
    });
  }
  handleOk = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }

  handleCancel = (e) => {
    console.log(e);
    this.setState({
      visible: false,
    });
  }
  customRequest = (option) => {
    //alert(option.file)
    const formData = new FormData()
    formData.append('myFileName', option.file);
    formData.append('albumId', this.props.albumId);
    fetch(AddPhoto,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
      },
      body: formData
    }).then(res => res.json()).then(
      data=>{
        if (data.code==0) {
          Toast.info(data.msg, 1);
          this.setState({
            visible: false,
          });
          emitter.emit('selectPhoto', this.props.photoId);
        } 
        else {
          Toast.info(data.msg, 1);
        }
      }
    )
  }
  render(){
    return(
      <div style={{ width:"100%"}}>
      <Upload fileList={[]} customRequest = {this.customRequest}>
        <Button type="primary" block>
          <Icon type="upload" /> 上传照片
        </Button>
      </Upload>
      </div>
    );
  }
}
class ShowPhoto extends React.Component{
  constructor(props) {
    super(props);
    this.state = { 
      visible: false ,
      photoAll:[],
      imgUrl:'',
      width:document.body.clientWidth/3 ,
    };console.log(document.body.clientWidth);
    this.showModal = this.showModal.bind(this);
    this.handleOk = this.handleOk.bind(this);
    this.handleCancel = this.handleCancel.bind(this);
  }
  showModal() {
    this.setState({
      visible: true,
    });
  }

  handleOk(e)  {
    console.log(e);
    this.setState({
      visible: false,
    });
  }

  handleCancel(e)  {
    console.log(e);
    this.setState({
      visible: false,
    });
  }
  render(){
    //alert('pId'+this.props.photoId);
    return(
      <div>
      <div>
      <Col span={8}>
        <div
          className="mCard"
          style={{ width: "100%"}}>
          <img className="Mimag"
          style={{ border:"1px solid #a5b6c8", height:this.state.width-25}}
           alt="example" src={this.props.photoUrl} onClick={this.showModal}/>
           
        </div>
      </Col>
      <Modal
          footer={null}
          style={{width: 800}}
          visible={this.state.visible}
          width={700}  
          onOk={this.handleOk}
          onCancel={this.handleCancel}
            >
          <img alt="example" style={{ width: '100%' }} src={this.props.photoUrl}/>
        </Modal>
        </div>
        <div>
        </div>
        </div>
    );
  }
}
class AllPhoto extends React.Component{
  constructor(props) {
    super(props);
  }
  
  render(){
    return(
      <div>
      <Row gutter={16}>
      {
        this.props.photoAll.length>0?
        this.props.photoAll.map(function(item){
          return(
            <ShowPhoto photoUrl={item.photoUrl} photoId={item.photoId} />
          )

        }):<Empty />
      }
      </Row>
        
      </div>
    );
  }
}
class MobilePhoto extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      photoAll:[],
      nowPage: 1,
      totalPage: 1,
      pageSize: 9,
      albumName:'',
      coverPhotoId:'',
      description:'',
    }
    emitter.on('selectPhoto', this.select.bind(this));
  //  emitter2.on('beCover', this.beCover.bind(this));
  }
  select(key){
    this.getClass();
  }
  componentWillMount() {
    this.getClass();
    this.getAlbumInfo();
  }
  getAlbumInfo() {
    fetch(AlbumDetail, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body:'albumId='+this.props.match.params.id
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({albumName: data.resultBean.albumName});
          this.setState({coverPhotoId: data.resultBean.coverPhotoId});
          this.setState({description: data.resultBean.description});
        } else {
          //alert(data.msg);
          message.error(data.msg);
        }
      }
    )
  }
  getClass() {
    fetch(SelectPhoto, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'albumId='+this.props.match.params.id+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({nowPage: data.resultBean.currentPage});
          this.setState({totalPage: data.resultBean.totalPage});
          this.setState({photoAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({photoAll: ''});
        }
      }
    )
  }
  pageChange = (page) => {
    console.log(page);
    this.setState({ nowPage: page }, () => this.getClass());
  }
  render(){
    return(
      <div className="bodyMain">
      <div  style={{margin: '0 auto', padding: 10, backgroundColor:'#ffffff'}}>
          <span>&nbsp;&nbsp;&nbsp;<Link to = "/mobilefirst">首页</Link>>照片</span><hr/>
      <div>
        <div className="albumTitle">
          <Meta
            avatar={<Avatar src={this.state.coverPhotoId} />}
            title={this.state.albumName}
            description={ this.state.description }
          />
        </div>
      </div>
      <div style={{width:"100"}}>
        <AddPhotoView albumId={this.props.match.params.id}/>
      </div>
        <AllPhoto photoAll={this.state.photoAll} albumId={this.props.match.params.id} />
       <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange} />
    
      </div>
      </div>
    );
  }
}
export default MobilePhoto;