import React from 'react';
import { Card } from 'antd';
import { Tabs, Row, Col, Empty  } from 'antd';
import { Upload, Icon, Modal, Avatar,Button,  message } from 'antd';
import { Pagination,  Toast, } from 'antd-mobile';
import ManageAlbum from './manageAlbum';
import DeleteButon from './deleteButton';
import '../../static/my/css/album.css';
import cookie from 'react-cookies';
import {SelectMyPhoto} from '../../config/router.js';
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
const confirm = Modal.confirm;
const props = {
  defaultFileList: [],
};
function deletePhoto(photoId){
   // alert(this.props.photoId);
    fetch(DeletePhoto,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'photoId='+photoId
    }).then(res => res.json()).then(
      data=>{
        if (data.code==0) {
          message.info(data.msg);
          this.setState({
            visible: false,
          });
          emitter.emit('selectPhoto', this.props.photoId);
        } 
        else {
          alert(data.msg);
        }
      }
    )
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
    this.delete = this.delete.bind(this);
    this.beCover = this.beCover.bind(this);
    this.showDeleteConfirm = this.showDeleteConfirm.bind(this);
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
          message.info(data.msg);
          this.setState({
            visible: false,
          });
          emitter.emit('selectPhoto', this.props.photoId);
        } 
        else {
          alert(data.msg);
        }
      }
    )
  }
  showDeleteConfirm() {
    confirm({
      title: '确认删除??',
      content: '删除之后无法恢复',
      okText: '确定',
      okType: 'danger',
      cancelText: '取消',
      onOk: ()=> {
        this.delete();
        console.log('yes');
      },
      onCancel() {
        console.log('no');
      },
    });
  }
  beCover() {
  //  alert(this.props.photoId);
    emitter2.emit('beCover', this.props.photoId);
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
          footer={
           <Button onClick={this.showDeleteConfirm} type="dashed">
            删除
          </Button>
          }
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
            <ShowPhoto photoUrl={item.photoUrl} photoId={item.photoId} al />
          )

        }):<Empty />
      }
      </Row>
        
      </div>
    );
  }
}
class MyMobilePhoto extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      photoAll:[],
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      albumName:'',
      coverPhotoId:'',
    }
    emitter.on('selectPhoto', this.select.bind(this));
  //  emitter2.on('beCover', this.beCover.bind(this));
  }
  select(key){
    this.getClass();
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    fetch(SelectMyPhoto, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
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
      <div style={{width:"100"}}>
      </div>
        <AllPhoto photoAll={this.state.photoAll} albumId={this.props.match.params.id} />
       <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange} />
    
      </div>
      </div>
    );
  }
}
export default MyMobilePhoto;