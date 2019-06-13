import React from 'react';
import cookie from 'react-cookies';
import {Pagination, Icon, Modal, Avatar, Button, message, Input, Row, Col, List , Card, Divider,Comment,Form ,Empty} from 'antd';
import E from 'wangeditor';
import {  } from 'antd-mobile';
import {LectureDetailUrl} from '../../config/router.js';
import {ApplyLectureUrl} from '../../config/router.js';
import {DeleteApplyLectureUrl} from '../../config/router.js';
import {ApplyOrNot} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
require('../../static/css/style.css');
require('../../static/css/bootstrap.min.css');
require('../../static/my/css/invitation.css');
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()
const { Meta } = Card;
const TextArea = Input.TextArea;

class JoinLecture extends React.Component{
	constructor(props){
		super(props);
		this.state={
			joinUserNum:0,
			isDone:1,
			joinOrNot: 0,
		}
		this.join = this.join.bind(this);
		this.delete = this.delete.bind(this);
	}
	componentDidMount(){
		this.getInvitation();
	}
	getInvitation() {
		fetch(ApplyOrNot,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'lectureId='+this.props.lectureId

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	this.setState({joinOrNot:0});
                	
                }
                else {
                	//message.error(data.msg);
                	this.setState({joinOrNot:1});
                }
            }
        )
	}
	join (){
		fetch(ApplyLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'lectureId='+this.props.lectureId

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	message.success(data.msg);
                	emitter.emit('changNum', '1')
                	this.setState({joinOrNot:1});
                }
                else {
                	message.error(data.msg);
                }
            }
        )
	}
	delete (){
		fetch(DeleteApplyLectureUrl,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'lectureId='+this.props.lectureId

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	message.success(data.msg);
                	emitter.emit('changNum', '1')
                	this.setState({joinOrNot:0});
                }
                else {
                	message.error(data.msg);
                }
            }
        )
	}
	render(){
		let but;
		if (this.props.isDone==1) {
			but = <Button type="primary" block onClick={this.join}>点击报名</Button>
		} 
		if (this.state.joinOrNot==1) {
			but = <Button type="danger" block  onClick={this.delete}>取消报名</Button>
		}
		if (this.props.isDone==0) {
			but = <Button type="danger" block disabled >报名结束</Button>
		}
		return(
			<div className="backCBai">
				<div style={{marginTop:10}}>
					<center><p>共报名{this.props.joinUserNum}人</p></center>
				</div>
				<div style={{marginTop:20, marginBottom:20}}>
					{but}
				</div>
			</div>
		);
	}
}
class LectureView extends React.Component{

	constructor(props){
		super(props);
		this.state = {
			lectureTitle:'',
			lectureBody:'',
			createUser:'',
			image:'',
			createDate:'',
			agreeNum:'',
			readNum:'',
			joinUserNum:0,
			isDone:0,
		}
		emitter.on('changNum', this.change.bind(this));
	}
	change() {
		this.getInvitation();
	}
	componentDidMount(){
		this.getInvitation();
	}
	getInvitation() {
		fetch(LectureDetailUrl,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'lectureId='+this.props.lectureId

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	this.setState({lectureTitle:data.resultBean.lectureTitle});
                	this.setState({lectureBody:data.resultBean.lectureBody});
                	this.setState({createUser:data.resultBean.createUser});
                	this.setState({image:data.resultBean.imageUrl});
                	this.setState({createDate:data.resultBean.createDate});
                	this.setState({agreeNum:data.resultBean.agreeNum});
                	this.setState({readNum:data.resultBean.readNum});
                	this.setState({joinUserNum:data.resultBean.joinUserNum});
                	this.setState({isDone:data.resultBean.isDone});
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
				<div className="backCD">
					<div style={{padding:20}} >
						<center><span style={{fontSize:"25px"}}>{this.state.lectureTitle}</span></center>
					</div>
					<div>
						<center>
						<Meta
			            title={this.state.invitationTitle}
			            description={ <div><span>管理员发表于{this.state.createDate}</span>
			        	</div>}/>
						</center>
			        </div>
			        <hr/>
			        <div>
			       		<div className="inBody" dangerouslySetInnerHTML={{__html: this.state.lectureBody}} />
			        </div>
				</div>

				<div className="backCD" style={{marginTop:20}}>
					<JoinLecture joinUserNum={this.state.joinUserNum} 
					lectureId={this.props.lectureId} isDone={this.state.isDone}/>
				</div>
			</div>
		);
	}
}

class Kuang extends React.Component{
	render() {
		return(
			<div style={{ margin: '0 auto', padding: 10}}>
				<div >
	     	 		<LectureView lectureId={this.props.lectureId}/>
	     	 		
	     		 </div>
	
    	   </div>
		);
	}
}	
class MobileLectureDetail extends React.Component{
    state={
     id:1
	}
	componentwillreceiveprops(nextProps){
		console.log(nextProps);
	}
	render() {
		console.log(this.props.match);
		console.log(this.props.match.params.id);
		return(
			<div className="mobileBodyMain">
				<div>
					<Kuang lectureId={this.props.match.params.id}/>
				</div>
			</div>
		);
	}
}

export default MobileLectureDetail;