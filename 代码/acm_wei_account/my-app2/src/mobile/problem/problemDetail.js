import React from 'react';
import cookie from 'react-cookies';
import { Icon, Modal, Avatar, Button, message, Input, Row, Col, List , Card, Divider,Comment,Form ,Empty, Collapse } from 'antd';
import {Pagination} from 'antd-mobile';
import E from 'wangeditor';
import {  } from 'antd-mobile';
import {DetailProblemUrl} from '../../config/router.js';
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

class AddCommentView extends React.Component{
	constructor(props){
		super(props);
		this.state={
			commentBody:''
		}
	}
	CommentBodyChange = (e) => {
		this.setState({commentBody:e.target.value});
	}
	AddCommentBtn = () => {
		fetch(ReplyProblemUrl,{   //Fetch方法
	      method: 'POST',
	      headers: {
	        'Authorization': cookie.load('token'),
	        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
	      },
	      body: 'problemId='+this.props.problemId+'&ansBody='+this.state.commentBody
	      }).then(res => res.json()).then(
	        data => {
	          if(data.code==0) {
	          	message.success('成功');
	            this.setState({commentBody: ''});
	            //emitterComment.emit('list', 'list');
	            window.location.reload(true); 
	          } else {
	            message.error(data.msg);
	          }
	      }
	    )
	}
	render(){
		return(
			<div style={{ marginTop:16}} className="backCD">
				<div>
				    <Form.Item>
				      <TextArea rows={3} onChange={this.CommentBodyChange} value={this.state.commentBody}/>
				    </Form.Item>
				    <Form.Item>
				      <Button
				        htmlType="submit"
				        type="primary"
				        className="floatR"
				        onClick={this.AddCommentBtn}
				      >
				        回帖
				      </Button>
				    </Form.Item>
				</div>
			</div>
		);
	}
}
class ExampleComment extends React.Component {
	constructor(props){
		super(props);
		this.state={
			visable:false,
			commentBody:'回复@'+this.props.username+': ',
		}
		console.log(this.state.pCommentId);
	}
	CommentBodyChange = (e) => {
		this.setState({commentBody:e.target.value});
	}
	showReply = () =>{
		this.setState({visable:!this.state.visable});
	}
	render(){
		let reply;
		if (this.state.visable==true) {
			reply = <div style={{ marginTop:5}}>
			<Row gutter={24}>
				<Col span={18}>
					<Form.Item>
				      <TextArea rows={1} onChange={this.CommentBodyChange} value={this.state.commentBody} />
				    </Form.Item>
				</Col>
				<Col span={2}>
					<Button
					style={{ marginTop:5}}
			        htmlType="submit"
			        onClick={this.AddCommentBtn}
			        type="primary"
			      >
			        Add
			      </Button>
				</Col>
				
			</Row>
			</div>
		}
		return(
			<div>
			<Meta
            avatar={<Avatar size={40} src={this.props.image}  />}
            title={this.props.username}
            description={
            	<div style={{wordWrap:"break-word"}}>
            	<span>{this.props.fcommentBody}</span>
            	</div>}
          	>
          	</Meta>
		  {reply}
		  </div>
		);

	}
  
}
class CommentView extends React.Component{
	constructor(props){
		super(props);
		this.state = {
		  nowPage: 1,
	      totalPage: 1,
	      pageSize: 5,
	      commentAll:[],
		}
		console.log('--'+this.props.invitationId)
		console.log(this.props.commentId+'-'+this.state.nowPage);
		//emitterComment.on('addSuccess', this.addSuccess.bind(this));
	}
	addSuccess(key) {
		this.getComment();
	}
	componentDidMount(){
		this.getComment();
	}
	getComment() {
		if(this.state.nowPage<1) {
			this.setState({nowPage:1})
		}
		//alert(this.props.commentId+'-'+this.state.nowPage);
		console.log('pageNum:'+this.state.nowPage)
		
	}
	pageChange = (page) => {
	    console.log(page);
	    this.setState({ nowPage: page }, () => this.getComment());
	}
	render(){
		let pageView;
		if (this.state.totalPage>0) {
			pageView = <div className="floatR" style={{ marginBottom:30, height:"30px!important"}}>
     	 			<Pagination size="small" total={this.state.totalPage*this.state.pageSize} current={this.state.nowPage} onChange={this.pageChange} />
     	 	</div>
		}
		return(
		<div style={{ marginTop:5}} >
			<ExampleComment username={this.props.username} fcommentBody={this.props.commentBody} 
			image={this.props.image} pCommentId={this.props.commentId} invitationId={this.props.invitationId}/>
			<br/>
    			
    	</div>
		);
	}
}
class CommentList extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			nowPage: 1,
	      totalPage: 1,
	      pageSize: 5,
	      commentAll:[],
		}
		console.log('--'+this.props.invitationId)
		//emitterComment.on('list', this.list.bind(this));
	}
	list() {
		this.getComment();
		//emitterComment.emit('addSuccess', 'success');
	}
	componentDidMount(){
		this.getComment();
	}
	getComment() {
		if(this.state.nowPage<1) {
			this.setState({nowPage:1})
		}
		fetch(SelectReplyUrl,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'problemId='+this.props.problemId+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	this.setState({commentAll:data.resultBean.items});
		           this.setState({nowPage: data.resultBean.currentPage});
	         	  this.setState({totalPage: data.resultBean.totalPage});
                	
                }
                else {
                	message.error(data.msg);
                }
            }
        )
	}
	pageChange = (page) => {
	    console.log(page);
	    this.setState({ nowPage: page }, () => this.getComment());
	     document.documentElement.scrollTop =0;
	}
	render(){
		return(
			<div  style={{ marginTop:20}} className="backCD">
				{
					this.state.commentAll.length==0?<Empty description={"暂无数据"} />:this.state.commentAll.map(item => {
						return(
						<div>
							<CommentView username={item.username} commentBody={item.ansBody} image={item.image} 
							invitationId={this.props.invitationId} commentId={item.commentId}/>
							<hr/>
						</div>
						)
					})
				}
				<div >
     	 			<Pagination total={this.state.totalPage} current={this.state.nowPage} onChange={this.pageChange}
        			pageSize={this.state.pageSize} />
     	 		</div>
			</div>
		);
	}
}

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
		fetch(DetailProblemUrl,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'problemId='+this.props.problemId

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
	     	 		<ProblemView problemId={this.props.problemId}/>
	     	 		<CommentList problemId={this.props.problemId} />
	     		 </div>
	
    	   </div>
    	   <div>
	     	 		<AddCommentView problemId={this.props.problemId} />
    	   </div>
    	   </div>
		);
	}
}	
class MobileProblemDetail extends React.Component{
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
					<Kuang problemId={this.props.match.params.id}/>
				</div>
			</div>
		);
	}
}

export default MobileProblemDetail;