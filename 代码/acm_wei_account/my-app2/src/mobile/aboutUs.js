import React from 'react';
import cookie from 'react-cookies';
import {Pagination, Icon, Modal, Avatar, Button, message, Input, Row, Col, List , Card, Divider,Comment,Form ,Empty} from 'antd';
import E from 'wangeditor';
import { Carousel, WingBlank } from 'antd-mobile';
import {EventEmitter2} from 'eventemitter2'
require('../static/css/style.css');
require('../static/css/bootstrap.min.css');
require('../static/my/css/invitation.css');
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()
const { Meta } = Card;
const TextArea = Input.TextArea;

class LectureView extends React.Component{

	render() {
		return(
			<div>
				<div className="backCD">
					<div style={{padding:20}} >
						<center><span style={{fontSize:"25px"}}>CUIT-ACM校队</span></center>
					</div>
					<div>
						<center>
						<Meta
			            description={ <div><span>CUIT-ACM团队</span>
			        	</div>}/>
						</center>
			        </div>
			        <hr/>
			        <div>
			       		<div className="inBody" style={{height:600}}>
			       			<p>&nbsp;&nbsp;&nbsp;&nbsp;成都信息工程大学ACM成立于2007年，至今共有100余名队员，大部分队员都进入了BAT等大厂。
			       			</p>
			       			<div style={{ width:'100%', padding:'-10px', border:'1px'}}>
	              				<img
	                			src={require('../static/images/vidio.jpg')}
	                			alt=""
	                			className="imageM"
	                			style={{ verticalAlign: 'top', }}
	                			onLoad={() => {
	                  			window.dispatchEvent(new Event('resize'));
	                  			this.setState({ imgHeight: 'auto' });
	                			}}
	              				/>
	     					</div>
	     					<p>&nbsp;&nbsp;&nbsp;&nbsp;参加ACM组织的讲座，能提高同学的数据结构的能力，能更好、更快的实现编程。
	     					参加ACM比赛，能够赢得丰厚的奖品，还能加德育分智育分。
	     					加入ACM校队，能够获得来自学长学姐的bat大厂的内推。
	     					加入ACM校队能参加全国各地乃至全球的ACM-ICPC比赛，公费旅游。
			       			</p>
			       			<p style={{fontSize:20}}>&nbsp;&nbsp;&nbsp;&nbsp;还在等什么呢？加入我们吧！
			       			</p>
			       		</div>
			        </div>
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
class AboutUs extends React.Component{
   
	render() {
		return(
			<div className="mobileBodyMain">
				<div>
					<Kuang lectureId={this.props.match.params.id}/>
				</div>
			</div>
		);
	}
}

export default AboutUs;