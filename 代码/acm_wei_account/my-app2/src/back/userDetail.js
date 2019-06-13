import React from 'react';

import { Menu, Button, Input, Checkbox, Row, Col, Pagination, Table, Divider, Tag,Alert ,Popconfirm } from 'antd';
import { Upload, Icon, message, Select, Modal  } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import './static/my/css/classfication.css';
import {UploadImg1} from '../config/router.js';
import {UploadImg} from '../config/router.js';
import {UserInfo} from '../config/router.js';
import {DeleteAnnounce} from '../config/router.js';
import {EventEmitter2} from 'eventemitter2'
import {UploadUserImg} from '../config/router.js';
import {UpdateUserInfo} from '../config/router.js';
import {UpdateAnnounceFirst} from '../config/router.js';
import {PassUser} from '../config/router.js';
import {NotPassUser} from '../config/router.js';
import {BeAdmin} from '../config/router.js';
import {BePlayer} from '../config/router.js';
import AvatarEditor from 'react-avatar-editor';
import Slider from 'react-avatar-editor';

var emitter = new EventEmitter2()

function dataURLtoFile(dataurl, filename) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}
class ChangePic extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      src: "",
      imageUrl:'',
    }
    this.onChange = this.onChange.bind(this);
  }
  onChange(e) {
    e.preventDefault();
    let files;
    if (e.dataTransfer) {
      files = e.dataTransfer.files;
    } else if (e.target) {
      files = e.target.files;
    }
    const reader = new FileReader();
    reader.onload = () => {
      this.setState({ src: files[0] });
    };
    reader.readAsDataURL(files[0]);
  }
  onClickSave = () => {
    var tmp;
    if (this.editor) {
      const canvas = this.editor.getImage();
      console.log(canvas);
            const canvasScaled = this.editor.getImageScaledToCanvas();
            //alert(canvas);
             var image = new Image();  
    // canvas.toDataURL 返回的是一串Base64编码的URL
    // 指定格式 PNG  
            image.crossOrigin = "*";
      image =canvas.toDataURL("image/jpeg", 0.8);
     //alert(image);
      const formData = new FormData()
     formData.append('myFileName', dataURLtoFile(image, 'xx.jpg'));
      fetch(UploadImg1,{   //Fetch方法
      method: 'POST',
      headers: {
      },
      body: formData
      }).then(res => res.json()).then(
        data => {
          if (data.name!='') {
            //alert(data.name);
            this.setState({imageUrl: data.url}, () => emitter.emit('change', data.url));
            this.setState({loading: false});
            message.success('修改成功');
          }
                
      })
    }
    this.setState({
      visible: false,
    });
  }
  setEditorRef = (editor) => this.editor = editor
  onAvatarUpload=() => {
    const reader = new FileReader();
    reader.onload = (e) => {
      const imgFile = e.target.result;
      this.setState({
        originImg: imgFile,
      });
    };
    reader.readAsDataURL(this.file.input.files[0]);
  }
  showModal = () => {
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

  render() {
    console.log(this.props.imageUrl);
    return (
      <div>
        <a><img   onClick={this.showModal} className="btnpic" src={this.props.imageUrl} /></a>
        <Modal
          title="修改头像"
          visible={this.state.visible}
          onOk={this.onClickSave}
          onCancel={this.handleCancel}
          okText="确定"
          cancelText="取消"
        >
          <input type="file" className="default" onChange={this.onChange} />
         <AvatarEditor
        image={this.state.src}
        width={200}
        height={200}
        color={[248, 249, 250, 0.8]}
        scale={1.0}
        rotate={0}
        style={{  margin: '0 5px' }}
          ref={this.setEditorRef}
          url={UploadImg1}
          useCORS = {true}
        />
        </Modal>
      </div>
    );
  }
}
const Option = Select.Option;
function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}
function checkWH (file, width, height) {
 return new Promise(function (resolve, reject) {
   let filereader = new FileReader();
   filereader.onload = e => {
   let src = e.target.result;
   const image = new Image();
   image.onload = function () {
  //alert(this.width+' '+this.height);
   if (this.width != this.height) {
     Modal.error({ title: `请上传长宽相等的图片`});
     reject();
    }  else {
     resolve();
    }
  };
   image.onerror = reject;
   image.src = src;
  };
   filereader.readAsDataURL(file);
 });
}
function beforeUpload(file) {
  const isJPG = file.type === 'image/jpeg';
  if (!isJPG) {
    message.error('You can only upload JPG file!');
  }
  const isLt2M = file.size / 1024 / 1024 < 2;
  if (!isLt2M) {
    message.error('Image must smaller than 2MB!');
  }
  return isJPG && isLt2M;
}

var year = new Date().getFullYear();
var rows = [], i = 2000, len = 10;
while (++i <= year) rows.push(i);
var classes = [1,2,3,4,5,6,7,8];

class UserDetail extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      userId: 4,
      username:'',
      realname:'',
      password:'',
      classNum:0,
      grade:0,
      mobile:'',
      studentId:'',
      auth:-1,
      loading: false,
      imageUrl:'',
    }
    this.gradeChange = this.gradeChange.bind(this);
    this.usernameChange = this.usernameChange.bind(this);
    this.passwordChange = this.passwordChange.bind(this);
    this.realnameChange = this.realnameChange.bind(this);
    this.classnumChange = this.classnumChange.bind(this);
    this.mobileChange = this.mobileChange.bind(this);
    this.studentIdChange = this.studentIdChange.bind(this);
    emitter.on('change', this.updateUserImage.bind(this));
  }
  componentWillMount(){
    this.getUserInfo();
  }
  customRequest = (option) => {
    const formData = new FormData()
    formData.append('myFileName', option.file)
    console.log('---'+option.file.name);
    fetch(UploadImg,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token')
      },
      body: formData
      }).then(res => res.json()).then(
        data => {
          this.setState({imageUrl: data.data});
          this.setState({loading: false});
          //window.alert(data.msg);
                
      })
  };
  updateUserImage(image) {
    //alert('image'+image);
    fetch(UploadUserImg,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+this.state.userId+'&image='+image
      }).then(res => res.json()).then(
        data => {
          //alert(data.code);
          if(data.code==0) {
            this.setState({imageUrl: image});
          } else {
            message.error(data.msg);
          }
      }
    )
  };
  handleChange = (info) => {
    if (info.file.status === 'uploading') {
      this.setState({ loading: true });
      return;
    }
    if (info.file.status === 'done') {
      // Get this url from response in real world.
      getBase64(info.file.originFileObj, imageUrl => this.setState({
        imageUrl,
        loading: false,
      }));
    }
  }
  getUserInfo() {
    fetch(UserInfo,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+this.props.match.params.id

      }).then(res => res.json()).then(
        data => {
          if (data.code==0) {
            this.setState({userId: data.resultBean.userId});
            this.setState({username: data.resultBean.username});
            this.setState({realname: data.resultBean.realname});
            this.setState({password: data.resultBean.password});
            this.setState({studentId: data.resultBean.studentId});
            this.setState({classNum: data.resultBean.classNum});
            this.setState({auth: data.resultBean.auth});
            this.setState({grade: data.resultBean.grade});
            this.setState({mobile: data.resultBean.mobile});
            this.setState({imageUrl: data.resultBean.image});
            this.setState({loading: false});
            //window.alert(data.msg); 
          } else{
            message.error(data.msg); 
          }       
        }
    )
  }
  gradeChange(value) {
    this.setState({grade: value});
  }
  usernameChange(e) {
    this.setState({username: e.target.value});
  }
  passwordChange(e) {
    this.setState({password: e.target.value});
  }
  realnameChange(e) {
    this.setState({realname: e.target.value});
  }
  mobileChange(e) {
    this.setState({mobile: e.target.value});
  }
  studentIdChange(e) {
    this.setState({studentId: e.target.value});
  }
  classnumChange(value) {
    this.setState({classNum: value});
  }
  updateUserInfo(){
    fetch(UpdateUserInfo,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+this.state.userId+'&username='+this.state.username+'&realname='+this.state.realname+'&password='+this.state.password+
            '&studentId='+this.state.studentId+'&classNum='+this.state.classNum+'&grade='+this.state.grade+'&mobile='+this.state.mobile
      }).then(res => res.json()).then(
        data => {
          if (data.code==0) {
            message.success('修改成功');
          } else {
            message.error(data.msg);
          }
                
      })
  }

  render() {
    const uploadButton = (
      <div>
        <Icon type={this.state.loading ? 'loading' : 'plus'} />
        <div className="ant-upload-text">Upload</div>
      </div>
    );
    const imageUrl = this.state.imageUrl;
    return(
    <div className="userMain">
      <div className="col-md-4 ">
        <div className="f-r">
          <ChangePic imageUrl={this.state.imageUrl}/>
        </div>
      </div>
      <div className="col-md-8 backgf">
        <div className="f-l">
          <div className="form-inline form" role="form"> 
            <div className="form-group"> 
            <label className="form-label">用户名：</label> 
            <Input value={this.state.username} className="form-control" placeholder="用户名" onChange={this.usernameChange} style={{height:30 , width:200}} disabled={true}/>
            </div> 
          </div>
          <div className="form-inline form" role="form"> 
            <div className="form-group"> 
            <label className="form-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label> 
            <Input value={this.state.realname} className="form-control" placeholder="姓名" onChange={this.realnameChange} style={{height:30 , width:200}}/>
            </div> 
          </div>
          <div className="form-inline form"  role="form"> 
            <div className="form-group"> 
            <label className="form-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label> 
            <Input type="password" value={this.state.password} className="form-control" placeholder="密码" onChange={this.passwordChange} style={{height:30 , width:200}}/>
            </div> 
          </div>
          <div className="form-inline form" role="form"> 
            <div className="form-group"> 
            <label className="form-label">手机号：</label> 
            <Input value={this.state.mobile} className="form-control" placeholder="手机号" onChange={this.mobileChange} style={{height:30 , width:200}}/>
            </div> 
          </div>
          <div className="form-inline form" role="form"> 
            <div className="form-group"> 
            <label className="form-label">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</label> 
            <Input value={this.state.studentId} className="form-control" placeholder="学号" onChange={this.studentIdChange} style={{height:30 , width:200}}/>
            </div> 
          </div>
          <div className="form-inline form" role="form"> 
            <div className="form-group"> 
            <label className="form-label">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</label> 
            <Select value={this.state.grade} style={{ width: 120 }} onChange={this.gradeChange}>
            {
              rows.map(function(y, i){
                return(
                 <Option value={y} key={i}>{y}</Option>
                )
              })
            }
           </Select>
           <Select value={this.state.classNum} style={{ width: 80 }} onChange={this.classnumChange}>
            {
              classes.map(function(y, i){
                return(
                 <Option value={y} key={i}>{y}</Option>
                )
              })
            }
           </Select>
          </div> 
          <br/><br/><br/>
          <div> 
            <Button type="primary" onClick={this.updateUserInfo.bind(this)}>修改</Button>
          </div>
        </div>
        </div>
      </div>
    </div>
    );
  }
}
export default UserDetail;