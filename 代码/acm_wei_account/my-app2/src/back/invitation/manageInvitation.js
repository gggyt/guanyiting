import React from 'react';

import { Menu, Icon, Button, Input, Checkbox, Row, Col, Pagination,  Table, Divider, Tag,Alert ,Popconfirm, message } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/classfication.css';
import {SelectInvivation} from '../../config/router.js';
import {DeleteInvitation} from '../../config/router.js';
import {UpdateAnnounceFirst} from '../../config/router.js';
import {DoneCompetitionUrl} from '../../config/router.js';
import {FirstInvitation} from '../../config/router.js';
import {GreateInvitation} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()

const id = -1;

class First extends React.Component{
  constructor(props) {
    super(props);
    this.first = this.first.bind(this);
  }
  render() {
    let ret;
    if (this.props.isFirst==1) {
      ret = <a  className="font-red">取消置顶</a>
    }
    else{
      ret = <a>置顶</a>
    }
    return(
      <span>{ret}</span>
    )
  }
}
class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.columns = [{
      title: '帖子标题',
      dataIndex: 'invitationTitle',
      key: 'invitationTitle',
      render: (text, record) => (
        <span>
          <a href="javascript:;"><Link to={'/updateInvitation/'+record.invitationId}>{record.invitationTitle}</Link></a> 
        </span>
       ),
    }, {
      title: '创建人',
      dataIndex: 'createUser',
      key: 'createUser',
    }, {
      title: '创建时间',
      dataIndex: 'createDate',
      key: 'createDate',
    },  {
      title: '操作',
      key: 'action',
      render: (text, record) => (
        <span>
          <a href="javascript:;"><Link to={'/manamgeComment/'+record.invitationId}>查看评论</Link></a>
          <Divider type="vertical" />
          <a href="javascript:;"><Link to={'/updateInvitation/'+record.invitationId}>修改</Link></a>
          <Divider type="vertical" />
          <Popconfirm title="确定删除?" okText="确定" cancelText="取消" onConfirm={() => this.handleDelete(record.invitationId)}>
          <a href="javascript:;">删除</a>
          </Popconfirm>
        </span>
       ),
    },{
      title: '截至操作',
      key: 'stopaction',
      render: (text, record) => (
        <span>
          {record.isFirst==1?<a href="javascript:;"><span className="plusFine" onClick={()=>this.first(record.invitationId, 0)}>取消置顶</span></a>:
          <a href="javascript:;"><span className="disTop" onClick={()=>this.first(record.invitationId, 1)}>置顶</span></a>}
          {record.isGreate==1?<a href="javascript:;"><span className="plusFine"  onClick={()=>this.greate(record.invitationId, 0)}>取消加精</span></a>:
          <a href="javascript:;"><span className="disTop" onClick={()=>this.greate(record.invitationId, 1)}>加精</span></a>}
        </span>
      ),
    }
    ];
  }
  first = (id, isFirst) => {
    fetch(FirstInvitation,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationId='+id+'&isFirst='+isFirst
    }).then(res => res.json()).then(
      data => {
        if (data.code==0) {
          emitter.emit('changeFirstText', 'Second');
          message.success('操作成功');
        } else {
          message.error(data.msg);
        }
      }
    )
  }
  greate = (id, isGreate) => {
    fetch(GreateInvitation,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationId='+id+'&isGreate='+isGreate
    }).then(res => res.json()).then(
      data => {
        if (data.code==0) {
          emitter.emit('changeFirstText', 'Second');
          message.success('操作成功');
        } else {
          message.error(data.msg);
        }
      }
    )
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
  handleDelete = (invitationId) => {
    console.log("------");
    fetch(DeleteInvitation,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationId='+invitationId
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
      <Table columns={this.columns} dataSource={this.props.all} pagination={false} />
    </div>
    );
  }
}

class AllAnnounce extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      all: '',
      title: '',
    }
    this.titleChange = this.titleChange.bind(this);
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
    //alert(this.state.competitionTitle);
    fetch(SelectInvivation, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'invitationTitle='+this.state.title+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          if(data.resultBean.currentPage>0) {
            this.setState({nowPage: data.resultBean.currentPage});
          }else {
            this.setState({nowPage: 1});
          }
          this.setState({totalPage: data.resultBean.totalItems/data.resultBean.pageSize});
          this.setState({all: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({all: ''});
          message.error(data.msg);
        }
      }
    )
  }
  titleChange(e) {
    this.setState({title: e.target.value}, ()=>this.getClass());
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
          <h3>帖子</h3>
        </div>
        <div className="searchF">
         <div className="example-input">
          <Input size="small" onChange={this.titleChange} placeholder="帖子名称" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
          </div>
        </div>
        <div className="search"> 
         <ShowTable all={this.state.all}/>
        </div>
        <div className="searchPage">
        <Pagination size="small" simple onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize}
        pageSize={this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
      </div>
      </div>
    );
  }
}
class ShowInvitation extends React.Component{
  constructor(props) {
    super(props);
  }
  render() {
    return(
      <div>
      <AllAnnounce />
      </div>
    );
  }
}
export default ShowInvitation;