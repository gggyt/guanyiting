
import React from 'react'

import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Row, Col, message } from 'antd';
import cookie from 'react-cookies';
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import './static/my/css/news.css';
import './static/my/css/classfication.css';
import {GetIndexInfo} from '../config/router.js';
import {GetLecComInfo} from '../config/router.js';
import {GetNewAnnInfo} from '../config/router.js';
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
           options : {
            chart: {
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false,
              type: 'pie'
            },
            title: {
              text: '讲座竞赛总数'
            },
            tooltip: {
              pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
              pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                  enabled: true,
                  format: '<b>{point.name}</b>: {point.y:.0f} 个',
                  style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                  }
                }
              }
            },
            series: [{
                name: 'Brands',
                colorByPoint: true,
                data: []
            }],
            
          },
          options1 : {
            chart: {
              plotBackgroundColor: null,
              plotBorderWidth: null,
              plotShadow: false,
              type: 'pie'
            },
            title: {
              text: '新闻公告总数'
            },
            tooltip: {
              pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
              pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                  enabled: true,
                  format: '<b>{point.name}</b>: {point.y:.0f} 个',
                  style: {
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                  }
                }
              }
            },
            series: [{
                name: 'Brands',
                colorByPoint: true,
                data: []
            }],
            
          }
        }
      
    }
    componentWillMount(){
      this.getData();
      this.getData2();
    }
    getData() {
      fetch(GetLecComInfo, {
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        }
      }).then( res => res.json()).then(
        data => {
          var tmp = this.state.options;
          //alert(data.code);
          if (data.code==0) {
            //this.setState({series:this.series[0].data.add(data.resultBean)});
           // this.setState({series[0].data: data.resultBean})
           console.log(data.resultBean);
           tmp.series[0].data = data.resultBean;
           this.setState({options: tmp}, ()=>{console.log(this.state.options)});
           //return data.resultBean;
          } else {
            message.error(data.msg);
          }
        }
      )
    }
    getData2() {
      fetch(GetNewAnnInfo, {
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        }
      }).then( res => res.json()).then(
        data => {
          var tmp = this.state.options1;
          //alert(data.code);
          if (data.code==0) {
            //this.setState({series:this.series[0].data.add(data.resultBean)});
           // this.setState({series[0].data: data.resultBean})
           console.log(data.resultBean);
           tmp.series[0].data = data.resultBean;
           this.setState({options1: tmp}, ()=>{console.log(this.state.options1)});
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
             <ReactHighcharts config={this.state.options} ref="chart" />
            </Col>
            <Col span={12} >
             <ReactHighcharts config={this.state.options1} ref="chart" />
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
    fetch(GetIndexInfo, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      }
    }).then( res => res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({allFriendNum: data.resultBean.allFriendNum});
          this.setState({nowDuty: data.resultBean.nowDuty});
          this.setState({allInvitationNum: data.resultBean.allInvitationNum});
          this.setState({allUserNum: data.resultBean.allUserNum});

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
              <p style={{fontSize:'25px'}}><strong>{this.state.allUserNum}</strong></p>
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
              <span><Icon type="contacts" style={{ fontSize: '20px' }}/>&nbsp;&nbsp;今日值日人</span>
              <p style={{fontSize:'25px'}}><strong>{this.state.nowDuty}</strong></p>
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