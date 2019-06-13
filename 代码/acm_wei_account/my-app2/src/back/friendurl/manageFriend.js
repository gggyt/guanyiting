import React from 'react';

import { Menu, Icon, Button, Input, Checkbox, Row, Col, Pagination,  Table, Divider, Tag,Alert ,Popconfirm, message, Modal } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/classfication.css';
import {SelectFriendUrl} from '../../config/router.js';
import {DeteleFriendUrl} from '../../config/router.js';
import {UpdateFriendUrl} from '../../config/router.js';
import {DetailFriendUrl} from '../../config/router.js';
import {AddFriendUrl} from '../../config/router.js';
import {DoneCompetitionUrl} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()

const id = -1;

class UpdateDayDuty extends React.Component {
  state = { visible: false }

  constructor(props) {
    super(props);
    this.state = {
      friendurlTitle:'',
      friendurlBody:'',
      friendurlId:''
    }
    this.getClass = this.getClass.bind(this);
    this.titleChange = this.titleChange.bind(this);
    this.urlbodyChange = this.urlbodyChange.bind(this);
    this.updateClass = this.updateClass.bind(this);
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

  componentWillMount(){
    this.getClass();
  }

  getClass() {
    fetch(DetailFriendUrl, {
      method: 'POST',
      headers:{
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body:'friendurlId='+this.props.friendurlId
    }).then(res => res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({friendurlTitle: data.resultBean.friendTitle})
          this.setState({friendurlBody: data.resultBean.friendBody})
          this.setState({friendurlId: data.resultBean.friendurlId})

        } else {
          message.error(data.msg);
        }
      }
    )
  }
  updateClass() {
    console.log(this.state.friendurlId)
    fetch(UpdateFriendUrl, {
      method: 'POST',
      headers : {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body:'friendurlId='+this.state.friendurlId+'&friendurlTitle='+this.state.friendurlTitle+'&friendurlBody='+this.state.friendurlBody
    }).then(res => res.json()).then(
      data => {
        if (data.code==0) {
          message.success('修改成功');
          emitter.emit('changeFirstText', '修改')
        } else {
          message.error(data.msg);
        }
      }
    )
  }

  urlbodyChange = (e) => {
    this.setState({friendurlBody: e.target.value});
  }
  titleChange = (e) => {
    console.log(e)
    this.setState({friendurlTitle: e.target.value});
  }
  render(){
    console.log(this.state.friendurlId)
    return (
      <span>
        <a href="javascript:;" onClick={this.showModal}>修改</a>
         <Modal
          title="修改"
          visible={this.state.visible}
          onOk={this.updateClass}
          onCancel={this.handleCancel}
          okText="确认修改"
          cancelText="取消"
        >
          <div>
            友链名称：<Input size="small" style={{height:30 , width:300}} 
            onChange={this.titleChange} value={this.state.friendurlTitle}/>
          </div>
          <div>
            友&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;链：
            <Input size="small"  style={{height:30 , width:300}} 
            onChange={this.urlbodyChange} value={this.state.friendurlBody}/>
          </div>
        </Modal>
      </span>
    )
  }
}
class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.tmp = this.tmp.bind(this);
    this.columns = [{
      title: '友链名称',
      dataIndex: 'friendTitle',
      key: 'friendTitle',
      render: (text, record) => (
        <span>
        <a href="javascript:;"><Link to={'/personCompetition/'+record.competitionId}>{record.friendTitle}</Link></a>
          
           </span>
       ),
    }, {
      title: '友链',
      dataIndex: 'friendBody',
      key: 'friendBody',
      width: 400
    }, {
      title: '创建时间',
      dataIndex: 'createDate',
      key: 'createDate',
    },  {
      title: '操作',
      key: 'action',
      render: (text, record) => (
        <span>
          <UpdateDayDuty friendurlId={record.friendurlId}/>
          <Divider type="vertical" />
          <Popconfirm title="确定删除?" onConfirm={() => this.handleDelete(record.friendurlId)} okText="确定" cancelText="取消">
            <a  href="javascript:;">删除</a>
          </Popconfirm>
           </span>
       ),
    }
    ];
  }
  doneCompetition = (key) => {
    fetch(DoneCompetitionUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
           body: 'competitionId='+key

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
  tmp = (key) => {
    console.log("------"+key);
    emitter2.emit('changeShow', key);

  }
  handleDelete = (friendurlId) => {
    console.log("------");
    fetch(DeteleFriendUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
           body: 'friendurlId='+friendurlId

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
  

  render() {
    return(
    <div>
      <Table columns={this.columns} dataSource={this.props.competitionAll} pagination={false} />
      
    </div>
    
    );
  }
}

class AllFriendUrl extends React.Component{
  state = { visible: false }

  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      competitionAll: '',
      competitionTitle: '',
      friendurlBody:'',
      friendurlTitle:''
    }
    this.competitionTitleChange = this.competitionTitleChange.bind(this);
    this.urlbodyChange = this.urlbodyChange.bind(this);
    this.titleChange = this.titleChange.bind(this);
    this.buttonClick = this.buttonClick.bind(this);
    this.addClass = this.addClass.bind(this);
    emitter.on('changeFirstText', this.changeText.bind(this))
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

  addClass() {
    if(this.state.friendurlTitle==0) {
      message.error('请输入标题');
      return;
    }
    if(this.state.friendurlTitle>20) {
      message.error('标题过长');
      return;
    }
    if(this.state.friendurlBody==0) {
      message.error('请输入网址');
      return;
    }

    fetch(AddFriendUrl, {
      method: 'POST',
      headers : {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body:'friendurlTitle='+this.state.friendurlTitle+'&friendurlBody='+this.state.friendurlBody
    }).then(res => res.json()).then(
      data => {
        if (data.code==0) {
          message.success('添加');
          this.setState({friendurlTitle: ''});
          this.setState({friendurlBody: ''});
          this.setState({visible: false});
          emitter.emit('changeFirstText', '添加')
        } else {
          message.error(data.msg);
        }
      }
    )
  }

  urlbodyChange = (e) => {
    console.log(this.state.friendurlBody)
    this.setState({friendurlBody: e.target.value});
  }
  titleChange = (e) => {
    console.log(this.state.friendurlTitle)
    this.setState({friendurlTitle: e.target.value});
  }

  changeText( msg ){
    this.getClass();
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    //alert(this.state.competitionTitle);
    fetch(SelectFriendUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'competitionTitle='+this.state.competitionTitle+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          if(data.resultBean.currentPage>0) {
            this.setState({nowPage: data.resultBean.currentPage});
          }else {
            this.setState({nowPage: 1});
          }
          this.setState({totalPage: data.resultBean.totalItems/data.resultBean.pageSize});
          this.setState({competitionAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({competitionAll: ''});
          message.error(data.msg);
        }
      }
    )
  }
  competitionTitleChange(e) {
    this.setState({competitionTitle: e.target.value}, ()=>this.getClass());
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
          <h3>友链</h3>
        </div>
        <div className="searchF">
         <div className="example-input">
          <Input size="small" onChange={this.competitionTitleChange} placeholder="友链名称" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
            <div className="searchPage">
              <Button type="primary" onClick={this.showModal}>+添加</Button>
              <Modal
              title="添加友链"
              visible={this.state.visible}
              onOk={this.addClass}
              onCancel={this.handleCancel}
              okText="确认添加"
              cancelText="取消"
            >
              <div>
                友链名称：<Input size="small" style={{height:30 , width:300}} 
                onChange={this.titleChange} value={this.state.friendurlTitle}/>
              </div>
              <div>
                友&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;链：
                <Input size="small"  style={{height:30 , width:300}} 
                onChange={this.urlbodyChange} value={this.state.friendurlBody}/>
              </div>
           </Modal>
           </div>
          </div>

        </div>
        <div className="search"> 
         <ShowTable competitionAll={this.state.competitionAll}/>
        </div>
        <div className="searchPage">
        <Pagination size="small" simple onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize}
        pageSize={this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
      </div>
      </div>
    );
  }
}
class ShowFriend extends React.Component{
  render() {
    return(
      <div>
       <AllFriendUrl />
      </div>
    );
  }
}
export default ShowFriend;