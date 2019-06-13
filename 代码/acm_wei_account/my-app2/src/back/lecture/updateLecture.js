
import React from 'react'

import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Row, Col, message , DatePicker} from 'antd';
import moment from 'moment';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/news.css';
import '../static/my/css/classfication.css';
import {LectureDetailUrl} from '../../config/router.js';
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

class UpdateLecture extends React.Component {
  constructor(props, context) {
      super(props, context);
      this.state = {
        lectureId: -1,
        editorContent: '',
        title: '',
        editorContentText:'  ',
        editor:'',
        createDate: '2000-10-10 00:00',
      }
      this.titleChange = this.titleChange.bind(this);
      this.publish = this.publish.bind(this);
      this.updateLecture = this.updateLecture.bind(this);
      this.onChange = this.onChange.bind(this);
      this.onOk = this.onOk.bind(this);
  }
  componentWillMount(){
    this.getData();
  }
  getData() {
    fetch(LectureDetailUrl, {
      method: 'POST',
      headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'lectureId='+this.props.match.params.id
    }).then( res => res.json()).then(
      data => {
        //console.log('token'+cookie.load('token'));
       // window.alert(data);
       // window.alert(data.code);
        if (data.code==0) {
          this.setState({title: data.resultBean.lectureTitle});
          this.setState({editorContent: this.state.editor.txt.html(data.resultBean.lectureBody)});
          this.setState({createDate: data.resultBean.createDate});
        } else {
          message.error(data.msg)
        }
      }
  
    )
  }
  titleChange(e) {
    console.log(e.target.value);
      this.setState({title: e.target.value});
      console.log(this.state.title);
  }
  publish() {
      this.updateLecture()
  }
  updateLecture() {
    if (this.state.title.length==0) {
      message.error('讲座标题不为空');
      return;
    }
    if (this.state.title.length>20) {
      message.error('讲座标题过长');
      return;
    }
    if (this.state.editorContentText.length==0) {
      message.error('讲座内容不为空');
      return;
    }
    fetch(UpdateLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
            body: 'lectureId='+this.props.match.params.id+'&lectureTitle='+this.state.title+'&lectureBody='+encodeURI(getString(this.state.editorContent))+'&date='+this.state.createDate

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
    this.setState({createDate: dateString});
  }
  onOk(value) {
    console.log('onOk: ', value);
    console.log(value);
  }
  render() {
    let sh;
    if (this.state.createDate!="2000-10-10 00:00") {
      sh = <div>
        <DatePicker
          defaultValue={moment(this.state.createDate, 'YYYY-MM-DD HH:mm:ss')}
          format="YYYY-MM-DD HH:mm:ss"
          showTime
          placeholder="选择时间"
          onChange={this.onChange}
          onOk={this.onOk}
        />
          </div>
    }
    return (
      <div>
      <div className="col-md-9">
        <div className="title">
          <h2>修改讲座</h2>
        </div>
        <div className="inputTitle">
          <Input size="small" placeholder="讲座名称" style={{height:30}} onChange={this.titleChange}
          value={this.state.title}/>
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
          {sh}
          <Button type="primary" onClick={this.publish}>发布</Button>
        </div>
        
      </div>

      </div>
    );
  }
  componentDidMount() {
    const elem = this.refs.editorElem
    const editor = new E(elem)
    this.setState({editor:editor})
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
      }, ()=>console.log(this.state.editorContent.replace(/nbsp/, "&nbsp")))
      this.setState({editorContentText: editor.txt.text()})
    }
    editor.create()
  }
}

export default UpdateLecture;