import React from 'react';

import { Menu, Icon, Button, Input, Checkbox, Row, Col, Pagination,  Table, Divider, Tag,Alert ,Popconfirm, message } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/classfication.css';
import {SelectLectureUrl} from '../../config/router.js';
import {DeleteLectureUrl} from '../../config/router.js';
import {UpdateAnnounceFirst} from '../../config/router.js';
import {DoneLectureUrl} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()

const id = -1;


class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.tmp = this.tmp.bind(this);
    this.columns = [{
      title: '讲座名称',
      dataIndex: 'lectureTitle',
      key: 'lectureTitle',
      render: (text, record) => (
        <span>
        <a href="javascript:;"><Link to={'/updateLecture/'+record.lectureId}>{record.lectureTitle}</Link></a>
          
           </span>
       ),
    }, {
      title: '创建人',
      dataIndex: 'username',
      key: 'username',
    }, {
      title: '创建时间',
      dataIndex: 'createDate',
      key: 'createDate',
    },  {
      title: '操作',
      key: 'action',
      render: (text, record) => (
        <span>
        <a href="javascript:;"><Link to={'/personLecture/'+record.lectureId}>查看报名用户</Link></a>
          <Divider type="vertical" />
          <a href="javascript:;"><Link to={'/updateLecture/'+record.lectureId}>修改</Link></a>
          <Divider type="vertical" />
          <Popconfirm title="确定删除?" onConfirm={() => this.handleDelete(record.lectureId)} okText="确定" cancelText="取消">
            <a  href="javascript:;">删除</a>
          </Popconfirm>
           </span>
       ),
    },{
      title: '截至操作',
      key: 'stopaction',
      render: (text, record) => (
        <span>
          {
            record.isDone==1?<a href="javascript:;"><Tag color="#108ee9" onClick={()=>this.doneLecture(record.lectureId)}>截至报名</Tag></a>:
            <Tag color="#f50">已结束</Tag>
          }
          
          </span>
       ),
    }
    ];
  }
  doneLecture = (key) => {
    fetch(DoneLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
           body: 'lectureId='+key

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
  handleDelete = (lectureId) => {
    console.log("------");
    fetch(DeleteLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
           body: 'lectureId='+lectureId

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

class AllAnnounce extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      competitionAll: '',
      lectureTitle: '',
    }
    this.lectureTitleChange = this.lectureTitleChange.bind(this);
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
      <div style={{ flex: 1, padding: "10px" }}>
        <div className="title">
          <h3>讲座</h3>
        </div>
        <div className="searchF">
         <div className="example-input">
          <Input size="small" onChange={this.lectureTitleChange} placeholder="校赛名" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
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
class ShowLecture extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      show: 1,
      id: 1,
    }
    emitter2.on('changeShow', this.changeShow.bind(this))
  }
  changeShow(cid) {
    if (this.state.show==1) {
      this.setState({show:0});
    } else {
      this.setState({show:1});
    }
    this.setState({id: cid})
  }
  render() {
    return(
      <div>
      <AllAnnounce />
      </div>
    );
  }
}
export default ShowLecture;