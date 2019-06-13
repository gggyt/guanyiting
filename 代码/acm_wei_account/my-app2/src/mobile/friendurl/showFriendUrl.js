import React from 'react';

import { Menu, Icon, Button, Input, Checkbox, Row, Col, Table, Divider, Tag,Alert ,Popconfirm, message } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import {  Pagination } from 'antd-mobile';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../../back/static/my/css/classfication.css';
import {SelectFriendUrl} from '../../config/router.js';
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
      title: '名称',
      dataIndex: 'friendTitle',
      key: 'friendTitle',
      render: (text, record) => (
        <span>
            {record.friendTitle}
       </span>
      ),
    },  {
      title: '链接',
      dataIndex: 'friendBody',
      key: 'friendBody',
      render: (text, record) => (
        <span style={{ wordWrap: 'break-word', wordBreak: 'break-all' }}>
            <a href={record.friendBody} target="_blank">{record.friendBody}</a>
       </span>
      )}
    ];
  }
  tmp = (key) => {
    console.log("------"+key);

  }
  

  render() {
    return(
    <div>
      <Table columns={this.columns} dataSource={this.props.friendAll} pagination={false} bordered />
      
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
      friendAll: '',
      friendTitle: '',
    }
    this.friendTitleChange = this.friendTitleChange.bind(this);
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
    fetch(SelectFriendUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'friendurlTitle='+this.state.friendTitle+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          if(data.resultBean.currentPage>0) {
            this.setState({nowPage: data.resultBean.currentPage});
          }else {
            this.setState({nowPage: 1});
          }
          this.setState({totalPage: data.resultBean.totalPage});
          this.setState({friendAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({friendAll: ''});
          message.error(data.msg);
        }
      }
    )
  }
  friendTitleChange(e) {
    this.setState({friendTitle: e.target.value}, ()=>this.getClass());
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
          <Input size="small" onChange={this.friendTitleChange} placeholder="校赛名" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
          </div>
        </div>
        <div className="search"> 
         <ShowTable friendAll={this.state.friendAll}/>
        </div>
        <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange}
        pageSize={this.state.pageSize} />
      
      </div>
      </div>
    );
  }
}
class ShowFriend extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      show: 1,
      id: 1,
    }
  }
  render() {
    return(

      <div className="mobileBodyMain">
        <div>
        <AllLecture />
        </div>
      </div>
    );
  }
}
export default ShowFriend;