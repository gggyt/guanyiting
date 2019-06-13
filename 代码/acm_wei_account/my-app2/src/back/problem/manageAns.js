import React from 'react';

import { Menu, Icon, Button, Input, Checkbox, Row, Col, Pagination,  Table, Divider, Tag,Alert ,Popconfirm, message, Modal } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/classfication.css';
import {SelectReplyUrl} from '../../config/router.js';
import {DeleteAnsUrl} from '../../config/router.js';
import {DetailProblemUrl} from '../../config/router.js';
import {DoneCompetitionUrl} from '../../config/router.js';
import {FirstInvitation} from '../../config/router.js';
import {GreateInvitation} from '../../config/router.js';
import {AnsDetailUrl} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()

const id = -1;

class DetailAns extends React.Component{
  state = { visible: false }
  constructor(props) {
    super(props);
    this.state = {
      ans:'',
    }
  }
  componentWillMount() {
    this.getData();
  }
  getData() {
    fetch(AnsDetailUrl,{
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'ansId='+this.props.id
    }).then(res => res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({ans:data.resultBean.ansBody});
        } else {
          message.error(data.msg);
        }
      }
    )
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
  render() {
    return(
      <span>
        <a href="javascript:;" onClick={this.showModal}>查看详情</a>
         <Modal
          title="详情"
          visible={this.state.visible}
          onCancel={this.handleCancel}
          footer={null}
        >
          <div>
            {this.state.ans}
          </div>
        </Modal>
      </span>
    )
  }
}

class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.columns = [{
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
        <DetailAns id={record.problemansId} />
          <Divider type="vertical" />
          
          <Popconfirm title="确定删除?" onConfirm={() => this.handleDelete(record.problemansId)}>
            <a  href="javascript:;">删除</a>
          </Popconfirm>
           </span>
       ),
    }
    ];
  }
  
  handleDelete = (problemansId) => {
    console.log("------");
    fetch(DeleteAnsUrl,{   //Fetch方法
            method: 'POST',
            headers: {
              'Authorization': cookie.load('token'),
              'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
           body: 'ansId='+problemansId

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

class AllProblem extends React.Component{
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
    this.getData();
  }
  getClass() {
    //alert(this.state.competitionTitle);
    fetch(SelectReplyUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'problemId='+this.props.id+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
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
  getData() {
    fetch(DetailProblemUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'problemId='+this.props.id
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({title: data.resultBean.problemTitle})
        } else {

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
          <h3>{this.state.titile}</h3>
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
class ShowAns extends React.Component{
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
      <AllProblem id={this.props.match.params.id}/>
      </div>
    );
  }
}
export default ShowAns;