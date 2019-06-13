import React from 'react';
import { List } from 'antd-mobile';
import { Menu, Icon, Input, Checkbox, Row, Col, message,Divider,Tag } from 'antd';
import {  Button,InputItem,Toast } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import cookie from 'react-cookies';
import E from 'wangeditor';
import {AllUserInfo} from '../../config/router.js';
import {IsFollow} from '../../config/router.js';
import {AddFollow} from '../../config/router.js';
import {DeleteFollow} from '../../config/router.js';
import MobileHome from '../homeIndex';


const Item = List.Item;
class ListMenu extends React.Component{

	render() {
		return(
			<div>
				<List renderHeader={() => 'Ta的'}>
			        <Item
			          arrow="horizontal"
			          onClick={() => {window.location.href='/mobile/userInvitation/'+this.props.id}}
			        >Ta的帖子</Item>
			        <Item
			          onClick={() => {window.location.href="/mobile/userComment/"+this.props.id}}
			          arrow="horizontal"
			        >
			          Ta的回复
			        </Item>
			     </List>
			</div>
		);
	}
}

class AuthTag extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			follow: 0
		}
		this.addFollow = this.addFollow.bind(this);
		this.deleteFollow = this.deleteFollow.bind(this);
		this.getClass = this.getClass.bind(this);
	}

	componentWillMount() {
		this.getClass();
	}

	getClass() {
		fetch(IsFollow, {
  		method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'beUserId='+this.props.id
  		}).then(res=>res.json()).then(
  			data => {
  				if (data.code==0) {
  					this.setState({follow: 0});
  				} 
  				else {
  					this.setState({follow: 1});
  				}
  			}
  		)
	}
	addFollow() {
		fetch(AddFollow, {
  		method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'beUserId='+this.props.id
  		}).then(res=>res.json()).then(
  			data => {
  				if (data.code==0) {
  					message.success('关注成功');
  					this.getClass();
  				}
  				else {
  					message.error(data.msg);
  				}
  			}
  		)
	}
	deleteFollow() {
		fetch(DeleteFollow, {
  		method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'beUserId='+this.props.id
  		}).then(res=>res.json()).then(
  			data => {
  				if (data.code==0) {
  					message.success('取消关注成功');
  					this.getClass();
  				}
  				else {
  					message.error(data.msg);
  				}
  			}
  		)
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
		let btn;
		if (this.state.follow==0) {
			btn = <Tag color="#87d068" onClick={this.addFollow}>关注</Tag>
		}
		else {
			btn = <Tag color="#FF0000" onClick={this.deleteFollow}>取消关注</Tag>
		}
		return(
		<div style={{marginTop: '10px'}}>
			{sh}{btn}
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
  	fetch(AllUserInfo, {
  		method: 'POST',
        headers: {
          'Authorization': cookie.load('token'),
          'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        },
        body: 'userId='+this.props.id
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
	           			<AuthTag auth={this.state.auth} id={this.props.id}/>
	           		</div>
	            </div>
         	</div>
          </div>
    	</div>
    );
  }
}

class OtherUserInfo extends React.Component{

	render() {
		return(
		<div className="mobileBodyMain">
			<UserAllInfo id={this.props.match.params.id}/>
			<ListMenu id={this.props.match.params.id}/>
		</div>
		);
	}
}
export default OtherUserInfo;