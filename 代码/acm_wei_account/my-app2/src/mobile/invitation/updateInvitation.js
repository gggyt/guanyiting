import React from 'react';
import { List } from 'antd-mobile';
import { Menu, Icon, Input, Checkbox, Row, Col, message } from 'antd';
import {  Button,InputItem,Toast } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import cookie from 'react-cookies';
import E from 'wangeditor';
import {UpdateInvitation} from '../../config/router.js';
import {GetInvitationDetail} from '../../config/router.js';

import {UploadImg} from '../../config/router.js';
require('../static/home.css');
const Item = List.Item;
const Brief = Item.Brief;

function getString(s) {
  s=s.replace(/\+/g, "%2B");
  s=s.replace(/&/g, "%26");

  return s;
}

class UpdateInvitationView extends React.Component{
  constructor(props){
    super(props);
    this.state={
      invitationTitle:'',
      invitationBody:'',
      invitationBodyText:'',
      editor:'',
    }
    this.invitationTitleChange = this.invitationTitleChange.bind(this);
    this.add = this.add.bind(this);
    this.getData = this.getData.bind(this);
  }
  componentWillMount() {
    this.getData();
  }
  getData() {
    fetch(GetInvitationDetail,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
    },
    body: 'invitationId='+this.props.match.params.id
    }).then(res => res.json()).then(
      data => {
        //window.alert('code'+data.code);
        if(data.code==0) {
          this.setState({invitationTitle:data.resultBean.invitationTitle});
          console.log(this.state.editor.txt.text());
          this.setState({invitationBodyText:this.state.editor.txt.text()});
          this.setState({invitationBody: this.state.editor.txt.html(data.resultBean.invitationBody)});
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  handleClick = () => {
    this.inputRef.focus();
  }
  add() {
    if (this.state.invitationTitle.length==0) {
      message.error('请输入标题',2);
      return;
    }
    if (this.state.invitationTitle.length>20) {
      message.error('标题长度过长', 2);
      return;
    }
    console.log(this.state.invitationBodyText)
    if (this.state.invitationBodyText.length==0) {
      message.error('请输入内容', 2);
      return;
    }
    let hide = Toast.loading('正在执行中...', 0);
    setTimeout(hide, 2000);
    fetch(UpdateInvitation,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationId='+this.props.match.params.id+'&invitationTitle='+this.state.invitationTitle+'&invitationBody='+encodeURI(getString(this.state.invitationBody))
      }).then(res => res.json()).then(
        data => {
          if(data.code==0) {
            setTimeout(hide)
            Toast.info('修改成功');
            this.props.history.push('/mobile/invitationDetatil/'+this.props.match.params.id)
          }
          else {
            setTimeout(hide)
            message.error(data.msg);
            Toast.fail(data.msg);
          }
        }
      )
    
  }
  componentDidMount() {
    const elem = this.refs.editorElem;
    const editor = new E(elem);
    this.setState({editor:editor});
    editor.customConfig.uploadImgShowBase64 = false   // 使用 base64 保存图片
    editor.customConfig.uploadFileName = 'myFileName';
    editor.customConfig.uploadImgServer = UploadImg;
    editor.customConfig.menus = [
      'head',  // 标题
      'bold',  // 粗体
      'fontSize',  // 字号
      'italic',  // 斜体
      'foreColor',  // 文字颜色
      'list',  // 列表
      'quote',  // 引用
      'image',  // 插入图片
    ];
    editor.customConfig.uploadImgHooks = { 
      customInsert: function (insertImg, result, editor) { 
      var url =result.data; insertImg(url); 
      } 
    };

      // 使用 onchange 函数监听内容的变化，并实时更新到 state 中
      editor.customConfig.onchange = html => {
        //window.alert(editor.txt.text());
        this.setState({
          editorContent: html
        })
        this.setState({invitationBodyText:editor.txt.text()});
        this.setState({invitationBody:html});
        console.log(this.state.editorContent);
        console.log(editor.txt.text());
      }
      editor.create()
    }
  blank(msg) {
    this.setState({invitationTitle: ''});
    this.setState({invitationBody: ''});
    this.setState({invitationBodyText: ''});
    this.state.editor.txt.html('');
  }
  invitationTitleChange(e) {
    console.log('e-'+e);
    this.setState({invitationTitle: e});
    console.log(this.state.invitationTitle);
  }
  render(){
    return(
      <div>
        <div className="form-inline form" role="form"> 
             
            <div>
            <List >
               <InputItem placeholder="一句话描述帖子" 
                onChange={this.invitationTitleChange} maxLength={20} value={this.state.invitationTitle}/>
              </List>
             </div>
          </div>
          <div style={{margin:"10px"}}>
          <div id="test" ref="editorElem" className="toolbar" >
          </div>  
          <br/>
          <Button type="primary" onClick={this.add}>提交</Button>
        </div>
      </div>
    );
  }
  
}


export default UpdateInvitationView;