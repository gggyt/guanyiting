
import React from 'react'

import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Row, Col, message, DatePicker } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/news.css';
import '../static/my/css/classfication.css';
import {AddLectureUrl} from '../../config/router.js';
import {UploadImg} from '../../config/router.js';
import {UpdateLectureUrl} from '../../config/router.js';
import {SelectClass} from '../../config/router.js';
require('../../static/css/style.css');
require('../../static/css/bootstrap.min.css');
require('../../static/my/css/login.css');

const { MonthPicker, RangePicker, WeekPicker } = DatePicker;

function getString(s) {
  s=s.replace(/\+/g, "%2B");
  s=s.replace(/&/g, "%26");

  return s;
}

class AddLecture extends React.Component {
  constructor(props, context) {
      super(props, context);
      this.state = {
        lectureId: -1,
        editorContent: '',
        title: '',
        editorContentText:'',
        data:'',
      }
      this.titleChange = this.titleChange.bind(this);
      this.publish = this.publish.bind(this);
      this.saveLecture = this.saveLecture.bind(this);
      this.updateLecture = this.updateLecture.bind(this);
      this.onChange = this.onChange.bind(this);
      this.onOk = this.onOk.bind(this);
  }
  titleChange(e) {
    console.log(e.target.value);
      this.setState({title: e.target.value});
      console.log(this.state.title);
  }
  publish() {
    console.log('----'+this.state.lectureId);
    console.log('----'+this.state.editorContent);
    if (this.state.lectureId==-1) {
     this.saveLecture()
    } else {
      this.updateLecture()
    //console.log(this.state.newsId);

    }
  }
  saveLecture() {
    if (this.state.title.length==0) {
      alert('讲座标题不为空');
      return;
    }
    if (this.state.editorContentText.length==0) {
      alert('讲座内容不为空');
      return;
    }
    if (this.state.data=='') {
      alert('请选择开始时间');
      return;
    }
    fetch(AddLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
            body: 'lectureTitle='+this.state.title+'&lectureBody='+encodeURI(getString(this.state.editorContent))+'&date='+this.state.data
        }).then(res => res.json()).then(
            data => {
                if(data.code==0) {
                  message.success('添加成功');
                  this.setState({lectureId: data.resultBean});
                   console.log(this.state.newsId);
                }
                else {
                  message.error(data.msg);
                  this.setState({newsId: -1});
                }
            }
        )
  }
  updateLecture() {
    if (this.state.title.length==0) {
      alert('新闻标题不为空');
      return;
    }
    if (this.state.editorContentText.length==0) {
      alert('新闻内容不为空');
      return;
    }
    fetch(UpdateLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
            body: 'lectureId='+this.state.lectureId+'&lectureTitle='+this.state.title+'&lectureBody='+encodeURI(getString(this.state.editorContent))+'&date='+this.state.data

        }).then(res => res.json()).then(
            data => {
                if(data.code==0) {
                  message.success('修改成功');
                }
                else {
                  message.error(data.msg);
                }
            }
        )
  }

  onChange(date, dateString) {
    console.log(date, dateString);
    console.log('--'+dateString);
    this.setState({data: dateString});
  }
  onOk(value) {
    console.log('onOk: ', value);
    console.log(value);
  }
  render() {
    return (
      <div>
      <div className="col-md-9">
        <div className="title">
          <h2>添加讲座</h2>
        </div>
        <div className="inputTitle">
          <Input size="small" placeholder="讲座名称" style={{height:30}} onChange={this.titleChange}/>
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
          <div>
            <DatePicker
              format="YYYY-MM-DD HH:mm:ss"
              showTime
              placeholder="选择时间"
              onChange={this.onChange}
              onOk={this.onOk}
            />
          </div>
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
        editorContent: html
      })
      this.setState({editorContentText: editor.txt.text()})
    }
    editor.create()
  }
}

export default AddLecture;