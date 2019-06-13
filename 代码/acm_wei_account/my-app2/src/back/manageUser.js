import React from 'react';

import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Menu, Icon, Button, Input, Pagination,  Table, Divider, Tag,Alert ,Popconfirm, message } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import './static/my/css/classfication.css';
import {SelectUser} from '../config/router.js';
import {DeleteAnnounce} from '../config/router.js';
import {EventEmitter2} from 'eventemitter2'
import {SelectNewsMain} from '../config/router.js';
import {DeleteNewMain} from '../config/router.js';
import {UpdateAnnounceFirst} from '../config/router.js';
import {PassUser} from '../config/router.js';
import {NotPassUser} from '../config/router.js';
import {BeAdmin} from '../config/router.js';
import {BePlayer} from '../config/router.js';
import {DeleteUser} from '../config/router.js';
import UserDetail from './userDetail';
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()

const id = -1;

class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.columns = [{
      title: '用户名',
      dataIndex: 'username',
      key: 'username',
      render: (text, record) => (
        <span>
          <a href="javascript:;"><Link to={'/userDetail/'+record.userId}>{record.username}</Link></a>
        </span>
      )
    },{
      title: '手机号',
      dataIndex: 'mobile',
      key: 'mobile',
      
    },  {
      title: '学号',
      dataIndex: 'studentId',
      key: 'studentId',
    },  {
      title: '年级',
      dataIndex: 'grade',
      key: 'grade',
    }, {
      title: '操作',
      key: 'action',
      render: (text, record) => (
        <span>
        {
          record.auth==0?<Popconfirm title="确定审核通过?" okText="确认" cancelText="取消" onConfirm={() => this.passUser(record.userId)}>
            <a  href="javascript:;">审核通过</a>
          </Popconfirm>:<a  className="font-red" href="javascript:;" onClick={()=>{this.notPassUser(record.userId)}}>取消审核</a>
        }
        <Divider type="vertical" />
        <a href="javascript:;"><Link to={'/userDetail/'+record.userId}>管理用户</Link></a>
        <Divider type="vertical" />
        {
          record.auth!=4&&record.auth!=5&&record.userId!=2?<Popconfirm title="确定设为管理员?" okText="确认" cancelText="取消" onConfirm={() => this.beAdmin(record.userId)}>
          <a  href="javascript:;">设为管理员</a>
          </Popconfirm>:<Popconfirm title="确定取消管理员?" okText="确认" cancelText="取消" onConfirm={() => this.bePlayer(record.userId)}>
            <a className="font-red" href="javascript:;">取消管理员</a>
          </Popconfirm>
        }
        <Divider type="vertical" />
        <Popconfirm title="确定删除用户?" okText="确认" cancelText="取消" onConfirm={() => this.deleteUser(record.userId)}>
          <a className="font-red" href="javascript:;">删除</a>
          </Popconfirm>
        </span>
       ),
    }];
  }
  tmp = (key) => {
    console.log("------"+key);
    emitter2.emit('changeShow', key);

  }
  deleteUser = (key) => {
    fetch(DeleteUser,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+key
    }).then(res => res.json()).then(
      data => {
        if(data.code==0) {
          emitter.emit('changeFirstText', 'Second');
          message.success('操作成功');
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  passUser = (key) => {
    fetch(PassUser,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+key
    }).then(res => res.json()).then(
      data => {
        if(data.code==0) {
          emitter.emit('changeFirstText', 'Second');
          message.success('操作成功');
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  notPassUser = (key) => {
    fetch(NotPassUser,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+key
    }).then(res => res.json()).then(
      data => {
        if(data.code==0) {
          emitter.emit('changeFirstText', 'Second');
          message.success('操作成功');
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  beAdmin = (key) => {
    fetch(BeAdmin,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
           body: 'userId='+key

        }).then(res => res.json()).then(
            data => {
                if(data.code==0) {
                  emitter.emit('changeFirstText', 'Second');
                  message.success('操作成功');
                }
                else {
                  message.error(data.msg);
                }
            }
        )
  }
  bePlayer = (key) => {
    fetch(BePlayer,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'userId='+key
    }).then(res => res.json()).then(
      data => {
        if(data.code==0) {
          emitter.emit('changeFirstText', 'Second');
          message.error('操作成功');
        }
        else {
          message.error(data.msg);
        }
      }
    )
  }
  

  render() {
    return(
    <div>
      <Table columns={this.columns} dataSource={this.props.classAll} pagination={false} />
    </div>
    );
  }
}

class ManageUser extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      userAll: '',
      name: '',
    }
    this.announceTitleChange = this.announceTitleChange.bind(this);
    this.buttonClick = this.buttonClick.bind(this);
    emitter.on('changeFirstText', this.changeText.bind(this))
  }

  changeText( msg ){
    this.getClass();
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    fetch(SelectUser, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'name='+this.state.name+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({nowPage: data.resultBean.currentPage});
          this.setState({totalPage: data.resultBean.totalPage});
          this.setState({userAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({userAll: ''});
        }
      }
    )
  }
  announceTitleChange(e) {
    this.setState({name: e.target.value}, ()=>this.getClass());
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
      <div style={{ flex: 1, padding: "10px" }}>
        <div className="title">
          <h3>用户</h3>
        </div>
        <div className="searchF">
         <div className="example-input">
          <Input size="small" onChange={this.announceTitleChange} placeholder="用户名" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
          </div>
        </div>
        <div className="search"> 
         <ShowTable classAll={this.state.userAll}/>
        </div>
        <div className="searchPage">
        <Pagination size="small" simple onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize} 
        pageSize={this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
      </div>
      </div>
    );
  }
}
class ShowUser extends React.Component{
  constructor(props) {
    super(props);
  }
  render() {
    return(
      <div>
      <ManageUser />
      </div>
    );
  }
}
export default ShowUser;