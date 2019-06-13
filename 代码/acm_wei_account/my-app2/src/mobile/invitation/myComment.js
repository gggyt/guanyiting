import "babel-polyfill";
import React from 'react';
import { Grid } from 'antd-mobile';
import cookie from 'react-cookies';
import { Icon, Divider,Avatar, Affix, Button, message } from 'antd';
import { Carousel, WingBlank, Toast, Pagination,Card, WhiteSpace } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { ListView, List } from 'antd-mobile';
import {MyComment} from '../../config/router.js';
import {DeleteComment} from '../../config/router.js';
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
class AllMyComment extends React.Component {
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
     fetch(MyComment,{
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize+'&getMy=-1'
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
  deleteComment = (commentId) => {
    fetch(DeleteComment,{
        method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'commentId='+commentId
      }).then(res => res.json()).then(
        data=>{
          if (data.code==0) {
            //alert(data.msg);
             message.info(data.msg);
             this.getInvitation();
          } 
          else {
            message.error(data.msg);
          }

        }
      )
  }
  render() {
    return (
      <div className="bodyMain">
      <div>
        
      </div>
      <div>
      
      <div>
        <List renderHeader={() => '我发表的帖子'}>
        {
          this.state.invitationAll.map(item => {
              return(
                <Item multipleLine  extra={<a><p style={{fontSize: '20px', color: '#FF6E27'}} onClick={() => {this.deleteComment(item.commentId)}}>删除</p></a>}>
                  <Link to={'/mobile/invitationDetatil/'+item.invitationId}>{item.invitationTitle} </Link>
                  <Brief>{item.commentBody}</Brief>
                </Item>
              )
          })
        }
        
        {
          this.state.invitationAll.length==0?<p style={{height:50, backgroundColor:'#ffffff', padding:10}}>暂无数据</p>:<p></p>
        }
        </List>
      </div>
        
        <Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange} />
      
    </div>
    </div>);
  }
}
export default AllMyComment;