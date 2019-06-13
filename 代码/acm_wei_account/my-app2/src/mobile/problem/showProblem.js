import "babel-polyfill";
import React from 'react';
import { Grid } from 'antd-mobile';
import cookie from 'react-cookies';
import { Icon, Divider,Avatar, Affix } from 'antd';
import { Carousel, WingBlank, Toast, Pagination,Card, WhiteSpace, Button } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { ListView, List } from 'antd-mobile';
import {SelectProblem} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
import axios from 'axios';

var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()
const Item = List.Item;
const Brief = Item.Brief;

function getString(htmls) {
  let div = document.createElement("div");
  div.innerHTML = htmls;
  const text = div.textContent || div.innerText || "";
  return text.substring(0, 20);
}

class AllProblem extends React.Component {
  state = {
    disabled: false,
  }
  constructor(props) {
      super(props);
      this.state={
        invitationAll:[],
        invitationFirst:[],
        nowPage: 1,
        totalPage: 1,
        pageSize: 10,
        totleSize: 0,
        invitaionTitle:'',
        height:document.body.clientHeight ,
        xx:'now',
      }
     // emitter2.on('getInvitation', this.getI.bind(this));
  }
  componentDidMount() {
      this.getInvitation();

  }
  getInvitation() {
     fetch(SelectProblem,{
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'problemTitle='+this.state.invitaionTitle+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
      }).then(res => res.json()).then(
        data=>{
          if (data.code==0) {
            //alert(data.msg);
             this.setState({nowPage: data.resultBean.currentPage});
            this.setState({totalPage: data.resultBean.totalPage});
            this.setState({totleSize: data.resultBean.totalItems});
            this.setState({invitationAll:data.resultBean.items})
          } 
          else {
            alert(data.msg);
          }
        }
      )
  }
  pageChange = (page) => {
      console.log(page);
      this.setState({ nowPage: page }, () => this.getInvitation());
       document.documentElement.scrollTop =0;
  }
  render() {
    return (
      <div>
      

        <Link to="/addProblem"><Button type="primary" style={{marginTop:20}} >添加问题</Button></Link>
      
      <div>
        {
          this.state.invitationAll.map(item => {
              return(
                <div>
                  <WingBlank size="lg">
                    <WhiteSpace size="lg" />
                    <Card>
                      <Card.Header
                        title={<div><Link to={'/mobile/problemDetail/'+item.problemId}>{item.problemTitle}</Link>
                         </div>}
                       
                        thumbStyle={{width:30, height:30}}
                      />
                      <Card.Body>
                        <div>{getString(item.problemBody)}</div>
                      </Card.Body>
                      <Card.Footer content={item.createDate} />
                    </Card>
                    <WhiteSpace size="lg" />
                  </WingBlank>
                </div>
              )
          })
        }
        </div>
        
        <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange} />
      
    </div>
    );
  }
}
export default AllProblem;