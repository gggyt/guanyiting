import React from 'react';

import { Menu, Icon, Button, Input, Checkbox, Row, Col, Table, Divider, Tag,Alert ,Popconfirm, message } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import {  Pagination } from 'antd-mobile';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../../back/static/my/css/classfication.css';
import {SelectLectureUrl} from '../../config/router.js';
require('../../static/my/css/invitation.css');

const id = -1;

class TagTitle extends React.Component{
  render(){
    let re;
    if(this.props.isDone==0) {
      re = <Tag color="#f50">已结束</Tag>
    } else {
      re = <Tag color="#108ee9">正在报名</Tag>
    }
    return(
      <a>
      {re}
      </a>
    )
  }
    
}
class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.tmp = this.tmp.bind(this);
    this.columns = [{
      title: '讲座名',
      dataIndex: 'lectureTitle',
      key: 'lectureTitle',
      render: (text, record) => (
        <span>
            <Link to={'/mobile/lectureDetail/'+record.lectureId}>{record.lectureTitle}</Link>
       </span>
      ),
    },  {
      title: '开始时间',
      dataIndex: 'createDate',
      key: 'createDate',
      render: (text, record) => (
        <span>
            {record.createDate}
       </span>
      )},
       {
        title: '状态',
        dataIndex: 'status',
        key: 'status',
        render: (text, record) => (
          <span>
              <TagTitle isDone={record.isDone}/>
         </span>
        ),
      }
    ];
  }
  tmp = (key) => {
    console.log("------"+key);

  }
  

  render() {
    return(
    <div>
      <Table columns={this.columns} dataSource={this.props.lectureAll} pagination={false} bordered />
      
    </div>
    
    );
  }
}

class AllLecture extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      lectureAll: '',
      lectureTitle: '',
    }
    this.lectureTitleChange = this.lectureTitleChange.bind(this);
    this.buttonClick = this.buttonClick.bind(this);
  }

  changeText( msg ){
    this.getClass();
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    //alert(this.state.lectureTitle);
    fetch(SelectLectureUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'lectureTitle='+this.state.lectureTitle+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          if(data.resultBean.currentPage>0) {
            this.setState({nowPage: data.resultBean.currentPage});
          }else {
            this.setState({nowPage: 1});
          }
          this.setState({totalPage: data.resultBean.totalPage});
          this.setState({lectureAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({lectureAll: ''});
          message.error(data.msg);
        }
      }
    )
  }
  lectureTitleChange(e) {
    this.setState({lectureTitle: e.target.value}, ()=>this.getClass());
  }
  pageChange = (page) => {
    console.log(page);
    this.setState({ nowPage: page }, () => this.getClass());
    
  }
  buttonClick() {
    this.getClass();
  }
  render() {
    return(
      <div style={{ margin: '0 auto', padding: 10, minHeight:400}} >
      <div className="backCD">
        <div className="searchF">
         <div className="example-input">
          <Input size="small" onChange={this.lectureTitleChange} placeholder="校赛名" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
          </div>
        </div>
        <div className="search"> 
         <ShowTable lectureAll={this.state.lectureAll}/>
        </div>
        <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange}
        pageSize={this.state.pageSize} />
      
      </div>
      </div>
    );
  }
}
class ShowLecture extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      show: 1,
      id: 1,
    }
  }
  render() {
    return(

      <div className="bodyMain">
        <div>
        <AllLecture />
        </div>
      </div>
    );
  }
}
export default ShowLecture;