
import React from 'react'

import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Row, Col, message } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/news.css';
import '../static/my/css/classfication.css';
import {AddInvitation} from '../../config/router.js';
import {UpdateInvitation} from '../../config/router.js';
import {UploadImg} from '../../config/router.js';
import {SelectClass} from '../../config/router.js';
require('../../static/css/style.css');
require('../../static/css/bootstrap.min.css');
require('../../static/my/css/login.css');

function getString(s) {
  s=s.replace(/\+/g, "%2B");
  s=s.replace(/&/g, "%26");

  return s;
}

class AddInvitationShow extends React.Component {
  constructor(props, context) {
      super(props, context);
      this.state = {
        id: -1,
        editorContent: '',
        title: '',
        editorContentText:'',
      }
      this.titleChange = this.titleChange.bind(this);
      this.publish = this.publish.bind(this);
      this.saveCompetition = this.saveCompetition.bind(this);
      this.updateCompetition = this.updateCompetition.bind(this);
  }
  titleChange(e) {
    this.setState({title: e.target.value});
  }
  publish() {
    console.log('----'+this.state.id);
    if (this.state.id==-1) {
     this.saveCompetition()
    } else {
      this.updateCompetition()
    }
  }
  saveCompetition() {
    if (this.state.title.length==0) {
      message.error('帖子标题不为空');
      return;
    }
    if (this.state.title.length>20) {
      message.error('帖子标题过长');
      return;
    }
    if (this.state.editorContentText.length==0) {
      message.error('帖子内容不为空');
      return;
    }
    fetch(AddInvitation,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationTitle='+this.state.title+'&invitationBody='+encodeURI(getString(this.state.editorContent))
    }).then(res => res.json()).then(
      data => {
        if(data.code==0) {
          message.success('添加成功');
          this.setState({id: data.resultBean});
        }
        else if (data.code==13) {
          message.error('用户登出');
          this.props.history.push('/login');
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  updateCompetition() {
    if (this.state.title.length==0) {
      message.error('帖子标题不为空');
      return;
    }
    if (this.state.editorContentText.length==0) {
      message.error('帖子内容不为空');
      return;
    }
    fetch(UpdateInvitation,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationId='+this.state.id+'&invitationTitle='+this.state.title+'&invitationBody='+encodeURI(getString(this.state.editorContent))
    }).then(res => res.json()).then(
      data => {
        if(data.code==0) {
          message.success('修改成功');
        }
        else if(data.code==13) {
          message.error(data.msg);
          this.props.history.push('/login');
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  render() {
    return (
      <div>
      <div className="col-md-9">
        <div className="title">
          <h2>添加帖子</h2>
        </div>
        <div className="inputTitle">
          <Input size="small" placeholder="帖子名称" style={{height:30}} onChange={this.titleChange}/>
        </div>
        <br/>
        <div className="inputTitle">
        <div ref="editorElem" className="toolbar">
        </div>  
        </div>

      </div>

      <div className="col-md-3">
      <br/><br/>
        <div className="push">
          <h3>发布</h3>
          <hr/>
          <Button type="primary" onClick={this.publish}>发布</Button>
        </div>
        
      </div>

      </div>
    );
  }
  componentDidMount() {
    const elem = this.refs.editorElem
    const editor = new E(elem)
	  editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
	  editor.customConfig.uploadFileName = 'myFileName';
	  editor.customConfig.uploadImgServer = UploadImg;
	  editor.customConfig.uploadImgHooks = { 
		  customInsert: function (insertImg, result, editor) { 
  		  var url =result.data; insertImg(url); 
  	  } 
	  };

    // 使用 onchange 函数监听内容的变化，并实时更新到 state 中
    editor.customConfig.onchange = html => {
      this.setState({
        editorContent: getString(html)
      })
      this.setState({editorContentText: editor.txt.text()})
    }
    editor.create()
  }
}

export default AddInvitationShow;