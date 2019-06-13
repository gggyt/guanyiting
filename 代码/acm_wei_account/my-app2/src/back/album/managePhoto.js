import React from 'react';
import { Card } from 'antd';
import { Tabs, Row, Col, Empty  } from 'antd';
import { Upload, Icon, Modal, Avatar, Button, message,Pagination } from 'antd';
import ManageAlbum from './manageAlbum';
import '../static/my/css/album.css';
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
  name: 'file',
  multiple: true,
  action: '//jsonplaceholder.typicode.com/posts/',
  onChange(info) {
    const status = info.file.status;
    if (status !== 'uploading') {
      console.log(info.file, info.fileList);
    }
    if (status === 'done') {
      message.success(`${info.file.name} file uploaded successfully.`);
    } else if (status === 'error') {
      message.error(`${info.file.name} file upload failed.`);
    }
  },
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
          message.success(data.msg);
          this.setState({
            visible: false,
          });
          emitter.emit('selectPhoto', this.props.photoId);
        } 
        else {
          message.error(data.msg);
        }
      }
    )
  }
  render(){
    return(
      <span>
        <Button type="primary"
          onClick={this.showModal} >
          <Icon type="upload" /> 上传照片
        </Button>
        <Modal
          title="上传图片"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
          footer = {null}
        > 
          <Dragger 
          {...props}
          customRequest = {this.customRequest}
          showUploadList = {false}
          >
            <p className="ant-upload-drag-icon">
              <Icon type="inbox" />
            </p>
            <p className="ant-upload-text">Click or drag file to this area to upload</p>
            <p className="ant-upload-hint">Support for a single or bulk upload. Strictly prohibit from uploading company data or other band files</p>
          </Dragger>
        </Modal>
      </span>
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
    };
    this.showModal = this.showModal.bind(this);
    this.handleOk = this.handleOk.bind(this);
    this.handleCancel = this.handleCancel.bind(this);
    this.delete = this.delete.bind(this);
    this.beCover = this.beCover.bind(this);
          console.log(this.props.photoId);
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
  delete(){
   // alert(this.props.photoId);
    fetch(DeletePhoto,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'photoId='+this.props.photoId
    }).then(res => res.json()).then(
      data=>{
        if (data.code==0) {
          message.success(data.msg);
          emitter.emit('selectPhoto', this.props.photoId);
        } 
        else {
          message.error(data.msg);
        }
      }
    )
  }
  beCover() {
  //  alert(this.props.photoId);
    emitter2.emit('beCover', this.props.photoId);
  }
  render(){
    //alert('pId'+this.props.photoId);
    return(
      <div>
      <Col span={6}>
        <Card
          className="card"
          hoverable
          style={{ width: 150 }}
          cover={<img className="img" alt="example" src={this.props.photoUrl} onClick={this.showModal}/>}
           
          actions={[<Icon type="delete" onClick={this.delete}/>, <Icon type="book" onClick={this.beCover}/>]}>
        </Card>
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
const confirm = Modal.confirm;
class Photo extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      photoAll:[],
      nowPage: 1,
      totalPage: 1,
      pageSize: 8,
      albumName:'',
      coverPhotoId:'',
      description: '111',
    }
    emitter.on('selectPhoto', this.select.bind(this));
    emitter2.on('beCover', this.beCover.bind(this));
    emitter.on('delete', this.deleteAlbumU.bind(this));
    console.log(this.props);
    this.showDeleteConfirm = this.showDeleteConfirm.bind(this);
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
          this.setState({totalPage: data.resultBean.totalItems/data.resultBean.pageSize});
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
  deleteAlbumU (){
    fetch(DeleteAlbum, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'albumId='+this.props.match.params.id
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          message.success(data.msg);
          this.props.history.push('/album');
        } else {
          message.error(data.msg);
        }
      }
    )
  }
  beCover(key){
   // alert(this.props.photoId);
    //alert(this.props.match.params.id);
    fetch(BeCover,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'albumId='+this.props.match.params.id+'&photoId='+key
    }).then(res => res.json()).then(
      data=>{
        if (data.code==0) {
          message.success(data.msg);
          this.getAlbumInfo();
        } 
        else {
          message.error(data.msg);
        }
      }
    )
  }
  showDeleteConfirm() {
    confirm({
      title: '删除?',
      content: '确定删除？',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        console.log('OK');
        emitter.emit('delete', 'delete');
      },
      onCancel() {
        console.log('Cancel');
      },
    });
  }
  render(){
    return(
      <div className="main">
        <div className="albumTitle">
          <Meta
            avatar={<Avatar src={this.state.coverPhotoId} />}
            title={this.state.albumName}
            description={ 
              <div>
                <div>{this.state.description}</div>
                <div>
                <AddPhotoView albumId={this.props.match.params.id} /> &nbsp;&nbsp;
                <Button type="danger" onClick={this.showDeleteConfirm}>删除相册</Button>
                </div>
              </div>}
          />
        </div>
        <AllPhoto photoAll={this.state.photoAll} albumId={this.props.match.params.id} />
        <div className="searchPage">
        <Pagination size="small" simple onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
        </div>
      </div>
    );
  }
}
export default Photo;