
import React from 'react'

import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Row, Col, message, Table, Pagination } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/news.css';
import '../static/my/css/classfication.css';
import {PersonLectureUrl} from '../../config/router.js';
import {SelectClass} from '../../config/router.js';
import {LectureDetailUrl} from '../../config/router.js';
import {ExportLecturePerson} from '../../config/router.js';
require('../../static/css/style.css');
require('../../static/css/bootstrap.min.css');
require('../../static/my/css/login.css');


class PersonLecture extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      personAll: '',
      lectureTitle: '',
    }
    this.tmp = this.tmp.bind(this);
    this.columns = [ {
      title: '参加人',
      dataIndex: 'realname',
      key: 'realname',
    }, {
      title: '学号',
      dataIndex: 'studentId',
      key: 'studentId',
    },
       {
        title: '创建时间',
        dataIndex: 'createDate',
        key: 'createDate',
        
      }
    ];
  }
  tmp = (key) => {
    console.log("------"+key);

  }
  componentWillMount() {
    this.getClass();
    this.getData();
  }
  getClass() {
    fetch(PersonLectureUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'lectureId='+this.props.match.params.id+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
            this.setState({nowPage: data.resultBean.currentPage});
         
          this.setState({totalPage: data.resultBean.totalItems/data.resultBean.pageSize});
          this.setState({personAll: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({personAll: ''});
          message.error(data.msg);
        }
      }
    )
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
          this.setState({lectureTitle: data.resultBean.lectureTitle});
        } else {
          message.error(data.msg)
        }
      }
  
    )
  }
  pageChange = (page) => {
    console.log(page);
    this.setState({ nowPage: page }, () => this.getClass());
    
  }

  render() {
    return(
    <div style={{ flex: 1, padding: "10px",backgroundColor:"#ffffff", width:1000, margin:'0 auto', marginTop:50 }}>
      <div>
        <span style={{fontSize:20, padding:10}}>{this.state.lectureTitle}</span>
        {
          this.state.personAll.length>0?<div className="searchPage">
          <a href={'http://localhost:9999/lecture/export?lectureId='+this.props.match.params.id}><Button>导出数据</Button></a>
        </div>:<div></div>
        }
        
      </div>
      <Table columns={this.columns} dataSource={this.state.personAll} pagination={false} bordered />
      <div className="searchPage">
        <Pagination size="small" simple onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize}
        pageSize={this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
      </div>
    </div>
    
    );
  }
}

export default PersonLecture;