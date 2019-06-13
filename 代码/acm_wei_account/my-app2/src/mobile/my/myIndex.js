import React from 'react';
import { List } from 'antd-mobile';
import { Menu, Icon, Input, Checkbox, Row, Col, message,Divider,Tag } from 'antd';
import {  Button,InputItem,Toast } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import cookie from 'react-cookies';
import E from 'wangeditor';
import {GetUserInfo} from '../../config/router.js';
import MobileHome from '../homeIndex';


const Item = List.Item;
class ListMenu extends React.Component{

	render() {
		return(
			<div>
				<List renderHeader={() => '我的'}>
			        <Item
			          arrow="horizontal"
			          onClick={() => {window.location.href="/mobile/myInvitation"}}
			        >我的帖子</Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/manageMyInvitation"}}
			          arrow="horizontal"
			        >
			          管理我的帖子
			        </Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/myComment"}}
			          arrow="horizontal"
			        >
			          我的回复
			        </Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/myPhoto"}}
			          arrow="horizontal"
			        >
			          我的相片
			        </Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/myLecture"}}
			          arrow="horizontal"
			        >
			          我参加的讲座
			        </Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/myProblem"}}
			          arrow="horizontal"
			        >
			          我的今日一题
			        </Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/updateMyInfo"}}
			          arrow="horizontal"
			        >
			          修改个人信息
			        </Item>
			     </List>
			</div>
		);
	}
}

class AuthTag extends React.Component{
	constructor(props) {
		super(props);
	}

	render() {
		let sh;
		if (this.props.auth==0) {
			sh = <Tag color="#f50">未通过审核</Tag>
		}
		else if (this.props.auth==2) {
			sh = <Tag color="#2db7f5">普通用户</Tag>
		}
		else {
			sh = <Tag color="#87d068">管理员</Tag>
		}


		return(
		<div style={{marginTop: '10px'}}>
			{sh}
		</div>
		);
	}
}

class UserAllInfo extends React.Component {
  
  constructor(props) {
  	super(props);
  	this.state = {
  		username: '',
  		image: '',
  		auth: '',
  	}

  }

  componentWillMount() {
    this.getClass();
  }

  getClass = () => {
  	fetch(GetUserInfo, {
  		method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        }
  	}).then(res=>res.json()).then(
  		data => {
  			if (data.code==0) {
  				this.setState({username: data.resultBean.username});
  				this.setState({image: data.resultBean.image});
  				this.setState({auth: data.resultBean.auth});
  			} else {
  				message.error(data.msg);
  			}
  		}
  	)
  }

  render() {
    return (
    	<div>
    		<div style={{margin: '20px'}}>
    			<h3>用户信息</h3>
    		</div>
    		<div style={{backgroundColor:'#ffffff'}}>
    		<div style={{ display: '-webkit-box', display: 'flex', padding: '15px 0' }} >
    			<div style={{padding:'5px'}}>
	            	<img style={{ height: '80px', marginRight: '5px', marginLeft:'10px' }} src={this.state.image} alt="" />
	            </div>
	            <Divider type="vertical" style={{height:'90px'}} />
	            <div style={{ lineHeight: 1 }} >
	            	<div style={{ marginBottom: '8px', fontWeight: 'bold' }}></div>
	            	<div>用户名：<span style={{ fontSize: '20px', color: '#FF6E27' }}>{this.state.username}</span></div>
	           		<div>
	           			<AuthTag auth={this.state.auth} />
	           		</div>
	            </div>
         	</div>
          </div>
    	</div>
    );
  }
}

class UserInfo extends React.Component{

	render() {
		return(
		<div className="mobileBodyMain">
			<UserAllInfo />
			<ListMenu />
		</div>
		);
	}
}
export default UserInfo;