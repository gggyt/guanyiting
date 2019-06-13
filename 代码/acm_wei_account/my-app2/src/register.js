import React from 'react';
import cookie from 'react-cookies';
import {RegisterUrl} from './config/router.js';
require('./static/css/style.css');
require('./static/css/bootstrap.min.css');
require('./static/my/css/login.css');

class SendSuccess extends React.Component{
	render() {
		return (
			<div className="form-group">
				<div className="alert alert-success" role="alert" id="message">{this.props.reason}</div>
			</div>
		)
	}
}
class SendFail extends React.Component{
	render() {
		return (
			<div className="form-group">
				<div className="alert alert-danger" role="alert" id="message">{this.props.reason}</div>
			</div>
		)
	}
}
class Register extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			haveSend: 0, //0未发送，1成功，2失败
			failReason: '',
			mobile: '',
			username: '',
			number: '',
			password: '',
			repassword: ''
		}
		this.mobileChange = this.mobileChange.bind(this);
		this.usernameChange = this.usernameChange.bind(this);
		this.numberChange = this.numberChange.bind(this);
		this.passwordChange = this.passwordChange.bind(this);
		this.repasswordChange = this.repasswordChange.bind(this);
		this.sumbit = this.sumbit.bind(this);
	}
	mobileChange(e) {
		this.setState({mobile: e.target.value});
	}
	usernameChange(e) {
		this.setState({username: e.target.value});
	}
	numberChange(e) {
		this.setState({number: e.target.value});
	}
	passwordChange(e) {
		this.setState({password: e.target.value});
	}
	repasswordChange(e) {
		this.setState({repassword: e.target.value});
	}
	
	sumbit(e) {
		e.preventDefault();
		this.getData();
	}
	getData() {
		if (!(/^1[34578]\d{9}$/.test(this.state.mobile))) {
			this.setState({haveSend: 2});
			this.setState({failReason: '输入手机格式错误'});
			
			return;
		}
		console.log(this.state.username.length);
		if (this.state.username.length<3||this.state.username>20) {
			this.setState({haveSend: 2});
			this.setState({failReason: 'username请输入3-20字符'});
			return;
		}
		if (this.state.number.length<1||this.state.number.length>20) {
			this.setState({haveSend: 2});
			this.setState({failReason: '请输入正确格式的学号'});
			return;
		}
		if (this.state.password.length<6||this.state.password.length>20) {
			this.setState({haveSend: 2});
			this.setState({failReason: '密码应为6-20字符'});
			return;
		}
		if (this.state.repassword.length<6||this.state.repassword.length>20) {
			this.setState({haveSend: 2});
			this.setState({failReason: '密码应为6-20字符'});
			return;
		}
		if (this.state.repassword!=this.state.password) {
			this.setState({haveSend: 2});
			this.setState({failReason: '两次密码不一致'});
			return;
		}
		fetch(RegisterUrl, {
			method: 'POST',
			headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'mobile='+this.state.mobile+'&username='+this.state.username+'&number='+this.state.number+'&password='+this.state.password+'&openId='+cookie.load('openId')+'&image='+cookie.load('image')
		}).then( res => res.json()).then(
			data=>{
				//window.alert(data);
				if (data.code==0) {
					this.setState({haveSend: 1});
        			cookie.save('token', data.resultBean.token, 1000);
         			this.props.history.push('/mobilefirst');
				} else {
					this.setState({haveSend: 2});
					this.setState({failReason: data.msg});
				}
			}
		)
	}
	render() {
		let res;
		if (this.state.haveSend==1) {
			res = <SendSuccess reason="您提交的申请我们会尽快审核"/>
		}
		if (this.state.haveSend==2) {
			res = <SendFail reason={this.state.failReason} />
		}
		return (
			<div className="backg bai">
				<div className="container">
					<div className="row">
						<div className="col-md-4 col-md-push-8">
							<form action="/main" className="fh5co-form animate-box" data-animate-effect="fadeInRight">
								<h2>注 册</h2>
								{res}
								<div className="form-group">
									<label for="name" className="sr-only">mobile</label>
									<input type="text" className="form-control" id="mobile" placeholder="手机号" autocomplete="off" onChange={this.mobileChange} />
								</div>
								<div className="form-group">
									<label for="email" className="sr-only">Username</label>
									<input type="text" className="form-control" id="username" placeholder="username" autocomplete="off" onChange={this.usernameChange} />
								</div>
								<div className="form-group">
									<label for="email" className="sr-only">Number</label>
									<input type="text" className="form-control" id="number" placeholder="学号" autocomplete="off" onChange={this.numberChange} />
								</div>
								<div className="form-group">
									<label for="password" className="sr-only">Password</label>
									<input type="password" className="form-control" id="password" placeholder="密码" autocomplete="off" onChange={this.passwordChange}/>
								</div>
								<div className="form-group">
									<label for="re-password" className="sr-only">Re-type Password</label>
									<input type="password" className="form-control" id="repassword" placeholder="再次输入密码" autocomplete="off" onChange={this.repasswordChange}/>
								</div>
								
								<div className="form-group">
									<input type="submit" value="Sign Up" className="btn1 btn-primary1" id="sign" onClick={this.sumbit}/>
								</div>
							</form>

						</div>
					</div>
					<div className="row last" >
						<div className="col-md-12 text-center"><p><small className="font-size">&copy;CUIT-ACM.GYT</small></p></div>
					</div>
				</div>
			</div>
		);
	}
}

export default Register;