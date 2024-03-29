
import React from 'react';
import cookie from 'react-cookies';
import { Icon, Modal, Avatar, Button, message, Input, Row, Col, List , Card, Divider,Comment,Form ,Empty, Collapse } from 'antd';
import {Pagination} from 'antd-mobile';
import E from 'wangeditor';
import {  } from 'antd-mobile';
import {NewProblem} from '../../config/router.js';
import {SelectReplyUrl} from '../../config/router.js';
import {AddComment} from '../../config/router.js';
import {ReplyProblemUrl} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
require('../../static/my/css/invitation.css');
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()
const { Meta } = Card;
const TextArea = Input.TextArea;
const Panel = Collapse.Panel;
class ProblemView extends React.Component{

	constructor(props){
		super(props);
		this.state = {
			lectureTitle:'',
			lectureBody:'',
			createUser:'',
			createDate:'',
			readNum:'',
			joinUserNum:0,
			myAns:'暂未发布题解',
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
		fetch(NewProblem,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	this.setState({problemTitle:data.resultBean.problemTitle});
                	this.setState({problemBody:data.resultBean.problemBody});
                	this.setState({createUser:data.resultBean.username});
                	this.setState({createDate:data.resultBean.createDate});
                	if (data.resultBean.myAns!='') {

                	this.setState({myAns:data.resultBean.myAns});
                	}
                	
                }
                else {
                	message.error(data.msg);
                }
            }
        )
	}
	render() {
		return(
			<div className="backF2">
				<div className="backCD">
					<div style={{padding:20}} >
						<center><span style={{fontSize:"25px"}}>{this.state.problemTitle}</span></center>
					</div>
					<div>
						<center>
						<Meta
			            title={this.state.invitationTitle}
			            description={ <div><span>{this.state.createUser}发表于{this.state.createDate}</span>
			        	</div>}/>
						</center>
			        </div>
			        <hr/>
			        <div>
			       		<div className="inBody" dangerouslySetInnerHTML={{__html: this.state.problemBody}} />
			        </div>
				</div>
				<div className="backCD" style={{marginTop:20, padding:0}}>
				<Collapse  >
				    <Panel header="作者题解" key="1">
				      <p dangerouslySetInnerHTML={{__html: this.state.myAns}} />
				    </Panel>
				  </Collapse>
				</div>
				<div>
				</div>
			</div>
		);
	}
}

class Kuang extends React.Component{
	render() {
		return(
		<div className="backF2">
			<div style={{ margin: '0 auto', padding: 10}}>
				<div >
	     	 		<ProblemView/>
	     	 	
	     		 </div>
	
    	   </div>
    	   </div>
		);
	}
}	
class NewsMobileInvitationDetail extends React.Component{
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
					<Kuang />
				</div>
			</div>
		);
	}
}


export default NewsMobileInvitationDetail;