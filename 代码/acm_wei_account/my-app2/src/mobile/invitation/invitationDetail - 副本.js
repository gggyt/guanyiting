import React from 'react';
import cookie from 'react-cookies';
import {Pagination, Icon, Modal, Avatar, Button, message, Input, Row, Col, List , Card, Divider,Comment,Form ,Empty} from 'antd';
import E from 'wangeditor';
import {  } from 'antd-mobile';
import {AddInvitation} from '../../config/router.js';
import {SelectInvivation} from '../../config/router.js';
import {GetInvitationDetail} from '../../config/router.js';
import {SelectComment} from '../../config/router.js';
import {AddComment} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
require('../../static/my/css/invitation.css');
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()
var emitterComment = new EventEmitter2()
const { Meta } = Card;
const TextArea = Input.TextArea;

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
	AddCommentBtn = () => {
		console.log('--'+this.props.pCommentId)
		fetch(AddComment,{   //Fetch方法
	      method: 'POST',
	      headers: {
	        'Authorization': cookie.load('token'),
	        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
	      },
	      body: 'invitationId='+this.props.invitationId+'&commentBody='+this.state.commentBody+'&p_commentId='+this.props.pCommentId
	      }).then(res => res.json()).then(
	        data => {
	          if(data.code==0) {
	          	message.success('成功');
	            this.setState({commentBody: ''});
	            emitterComment.emit('addSuccess', 'success');
	            this.setState({visable:!this.state.visable});
	          } else {
	            message.error(data.msg);
	          }
	      }
	    )
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
			        添加
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
            	<a><span onClick={this.showReply} className="floatR">回复</span></a>
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
		emitterComment.on('addSuccess', this.addSuccess.bind(this));
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
		fetch(SelectComment,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'invitationId='+this.props.invitationId+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize+'&p_commentId='+this.props.commentId

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	this.setState({commentAll:data.resultBean.items});
		           this.setState({nowPage: data.resultBean.currentPage});
	         	  this.setState({totalPage: data.resultBean.totalPage});
                	//alert(data.resultBean.totalPage)
                	//alert(this.state.totalPage*this.state.pageSize)
                	console.log(this.state.totalPage*this.state.pageSize)
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
	}
	render(){
		let pageView;
		if (this.state.totalPage>1) {
			pageView = <div className="floatR" style={{ marginBottom:30, height:"30px!important"}}>
     	 			<Pagination size="small" total={this.state.totalPage*this.state.pageSize} current={this.state.nowPage} 
     	 			pageSize={this.state.pageSize} onChange={this.pageChange} />
     	 	</div>
		}
		return(
		<div style={{ marginTop:5}} >
			<div>
			<ExampleComment username={this.props.username} fcommentBody={this.props.commentBody} 
			image={this.props.image} pCommentId={this.props.commentId} invitationId={this.props.invitationId}/>
			<br/>
			<div style={{ marginLeft:20}}>
				{
					this.state.commentAll.map(item => {
						return(
						<div>
							<hr/>
							<ExampleComment username={item.username} fcommentBody={item.commentBody} image={item.image} 
							invitationId={this.props.invitationId} commentId={item.commentId}  pCommentId={this.props.commentId}/>
							
						</div>
						)
					})
				}
				{pageView}
			</div>
    		</div>
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
		emitterComment.on('list', this.list.bind(this));
	}
	list() {
		this.getComment();
		emitterComment.emit('addSuccess', 'success');
	}
	componentDidMount(){
		this.getComment();
	}
	getComment() {
		if(this.state.nowPage<1) {
			this.setState({nowPage:1})
		}
		fetch(SelectComment,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'invitationId='+this.props.invitationId+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize

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
			<div  style={{ marginTop:5}} className="backCD">
				{
					this.state.commentAll.length==0?<Empty description={"暂无数据"} />:this.state.commentAll.map(item => {
						return(
						<div>
							<CommentView username={item.username} commentBody={item.commentBody} image={item.image} 
							invitationId={this.props.invitationId} commentId={item.commentId}/>
							<hr/>
						</div>
						)
					})
				}
				<div className="floatR">
     	 			< Pagination size="small" onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize} 
     	 			pageSize={this.state.pageSize} defaultCurrent={this.state.nowPage} hideOnSinglePage={true} />
     	 		</div>
			</div>
		);
	}
}
class InvitationView extends React.Component{

	constructor(props){
		super(props);
		this.state = {
			invitationTitle:'',
			invitationBody:'',
			createUser:'',
			image:'',
			createDate:'',
			agreeNum:'',
			readNum:'',
		}
	}

	componentDidMount(){
		this.getInvitation();
	}
	getInvitation() {
		fetch(GetInvitationDetail,{   //Fetch方法
            method: 'POST',
            headers: {
            	'Authorization': cookie.load('token'),
            	'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
            },
			body: 'invitationId='+this.props.invitationId

        }).then(res => res.json()).then(
            data => {
				//window.alert('code'+data.code);
                if(data.code==0) {
                	this.setState({invitationTitle:data.resultBean.invitationTitle});
                	this.setState({invitationBody:data.resultBean.invitationBody});
                	this.setState({createUser:data.resultBean.createUser});
                	this.setState({image:data.resultBean.imageUrl});
                	this.setState({createDate:data.resultBean.createDate});
                	this.setState({agreeNum:data.resultBean.agreeNum});
                	this.setState({readNum:data.resultBean.readNum});
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
					<Icon type="message" theme="twoTone" />&nbsp;<span style={{fontSize:"25px"}}>{this.state.invitationTitle}</span>
				</div>
				<div>
					<Meta
		            avatar={ 
			            this.state.image!=''?<a><Avatar size={50} src={this.state.image} /></a>:
			            <a><Avatar size={50} style={{ backgroundColor: '#f56a00', verticalAlign: 'middle' }} >
             			{this.state.createUser}</Avatar></a>
			        }
		            title={this.state.invitationTitle}
		            description={ <div><span>{this.state.createUser}发表于{this.state.createDate}</span>
		            <span className="floatR">回复{this.state.agreeNum}<Divider type="vertical" />阅读{this.state.readNum}</span> </div>}/>
		        </div>
		        <hr/>
		        <div>
		       		<div className="inBody" dangerouslySetInnerHTML={{__html: this.state.invitationBody}} />
		        </div>
			</div>
			</div>
		);
	}
}
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
		fetch(AddComment,{   //Fetch方法
	      method: 'POST',
	      headers: {
	        'Authorization': cookie.load('token'),
	        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
	      },
	      body: 'invitationId='+this.props.invitationId+'&commentBody='+this.state.commentBody
	      }).then(res => res.json()).then(
	        data => {
	          if(data.code==0) {
	          	message.success('成功');
	            this.setState({commentBody: ''});
	            emitterComment.emit('list', 'list');
	            window.location.reload(true); 
	          } else {
	            message.error(data.msg);
	          }
	      }
	    )
	}
	render(){
		return(
			<div style={{ marginTop:9}} className="backCD">
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
class Tmp extends React.Component{
	render(){
		return(
		<div>
			<div>
				<InvitationView invitationId={this.props.invitationId}/>
     	 		<CommentList invitationId={this.props.invitationId} />
     	 		
			</div>
			<div>
				<AddCommentView invitationId={this.props.invitationId}/>
			</div>
		</div>
		);
	}
}
class Xx extends React.Component{
	render(){
		return(
			<div className="backCD">
				111
			</div>
		);
	}
}
class Kuang extends React.Component{
	render() {
		return(
			<div style={{ margin: '0 auto', padding: 10}}>
				<div >
	     	 			<Tmp invitationId={this.props.invitationId}/>
	     	 		
	     		 </div>
	
    	   </div>
		);
	}
}	
class MobileInvitationDetail extends React.Component{
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
					<Kuang invitationId={this.props.match.params.id}/>
				</div>
			</div>
		);
	}
}

export default MobileInvitationDetail;