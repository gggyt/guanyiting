
import React from 'react'

import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Row, Col, message } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import './static/my/css/news.css';
import './static/my/css/classfication.css';
import {GetIndexInfoUrl} from '../config/router.js';
import {GetLecComInfo} from '../config/router.js';
import {GetNewAnnInfo} from '../config/router.js';
import {InvitationSumUrl} from '../config/router.js';
import {ProblemSumUrl} from '../config/router.js';
import ReactHighcharts from 'react-highcharts';
import Highcharts from 'highcharts';
require('../static/css/style.css');
require('../static/css/bootstrap.min.css');
require('../static/my/css/login.css');
require('./static/my/css/home.css');


class DoughnutChart extends React.Component{

    constructor(props) {
      super(props);
        this.state = {
          config : {
            title: {
            text: '帖子总量'
          },

          yAxis: {
            title: {
              text: '总数'
            }
          },
          credits:{
            enabled:false
          },
          xAxis:{
            categories:[]
          },
          series: [{
            name: '发帖总量',
            data: [0, 0]
          }],
          },
          config1 : {
            title: {
            text: '每日问题总量'
            },

            yAxis: {
              title: {
                text: '总数'
              }
            },
            credits:{
              enabled:false
            },
            xAxis:{
              categories:['2010','2011','2012','2013','2014','2015','2016','2017']
            },
            series: [{
              name: '问题总量',
              data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
            }],
          }
        }
      
    }
    componentWillMount(){
      this.getData();
      this.getData2();
    }
    getData() {
      fetch(InvitationSumUrl, {
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        }
      }).then( res => res.json()).then(
        data => {
          var tmp = this.state.config;
          //alert(data.code);
          if (data.code==0) {
            //this.setState({series:this.series[0].data.add(data.resultBean)});
           // this.setState({series[0].data: data.resultBean})
           console.log(data.resultBean);
           tmp.xAxis.categories = data.resultBean.data;
           tmp.series[0].data = data.resultBean.sum;
           this.setState({config: tmp}, ()=>{console.log(this.state.config)});
           //return data.resultBean;
          } else {
            message.error(data.msg);
          }
        }
      )
    }
    getData2() {
      fetch(ProblemSumUrl, {
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        }
      }).then( res => res.json()).then(
        data => {
          var tmp = this.state.config1;
          //alert(data.code);
          if (data.code==0) {
            //this.setState({series:this.series[0].data.add(data.resultBean)});
           // this.setState({series[0].data: data.resultBean})
           console.log(data.resultBean);
           tmp.xAxis.categories = data.resultBean.data;
           tmp.series[0].data = data.resultBean.sum;
           this.setState({config1: tmp}, ()=>{console.log(this.state.config1)});
           //return data.resultBean;
          } else {
            message.error(data.msg);
          }
        }
      )
    }
    render() {
      

    return (
      <div>
        <div style={{margin:'30px'}}>
          <Row gutter={16} >
            <Col span={12} >
             <ReactHighcharts config={this.state.config} />
            </Col>
            <Col span={12} >
             <ReactHighcharts config={this.state.config1} />
            </Col>
          </Row>

        </div>
      </div>
    );
  }
}
class AllNum extends React.Component{

  constructor(props) {
    super(props);
    this.state = {
      allFriendNum: '',
      nowDuty: '',
      allInvitationNum: '',
      allUserNum: '',
    }

  }
  componentWillMount(){
    this.getData();
  }
  getData() {
    fetch(GetIndexInfoUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      }
    }).then( res => res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({allFriendNum: data.resultBean.allFriendNum});
          this.setState({nowDuty: data.resultBean.allUserNum});
          this.setState({allInvitationNum: data.resultBean.allInvitationNum});
          this.setState({allUserNum: data.resultBean.allPhotoNum});

        } else {
          message.error(data.msg);
        }
      }
  
    )
  }
  render() {
    return(
      <div style={{margin:'20px', padding:'20px'}}>
        <Row gutter={16}>
          <Col span={5} className="statis">
            <div style={{margin:'30px', backgroundColor: '#ffffff'}}>
              <span><Icon type="user" style={{ fontSize: '20px' }}/>&nbsp;&nbsp;用户人数</span>
              <p style={{fontSize:'25px'}}><strong>{this.state.nowDuty}</strong></p>
            </div>
          </Col>
          <Col span={5} className="statis">
            <div style={{margin:'30px', backgroundColor: '#ffffff'}}>
              <span><Icon type="desktop" style={{ fontSize: '20px' }}/>&nbsp;&nbsp;帖子个数</span>
              <p style={{fontSize:'25px'}}><strong>{this.state.allInvitationNum}</strong></p>
            </div>
            
          </Col>
          <Col span={5} className="statis">
            <div style={{margin:'30px', backgroundColor: '#ffffff'}}>
              <span><Icon type="team" style={{ fontSize: '20px' }}/>&nbsp;&nbsp;友链总数</span>
              <p style={{fontSize:'25px'}}><strong>{this.state.allFriendNum}</strong></p>
            </div>
          </Col>
          <Col span={5} className="statis">
            <div style={{margin:'30px', backgroundColor: '#ffffff'}}>
              <span><Icon type="camera" style={{ fontSize: '20px' }}/>&nbsp;&nbsp;相片总数</span>
              <p style={{fontSize:'25px'}}><strong>{this.state.allUserNum}</strong></p>
            </div>
            
          </Col>
        </Row>
      </div>
    );
  }
}

class BackHome extends React.Component{

  render() {
    return(
      <div>
        <AllNum />
        <DoughnutChart/>
      </div>
    );
  }

}

export default BackHome;