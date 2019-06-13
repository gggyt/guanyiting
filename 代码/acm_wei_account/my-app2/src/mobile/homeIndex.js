import React from 'react';
import { Grid } from 'antd-mobile';
import cookie from 'react-cookies';
import { Icon, message, } from 'antd';
import { Carousel, WingBlank } from 'antd-mobile';
import {WLoginUrl} from '../config/router.js';
import {SelectLectureUrl} from '../config/router.js';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Timeline } from 'antd';


require('./static/home.css');



const arr = [
	{
		icon: <Icon type="message" theme="twoTone" twoToneColor='#FFA54F' style={{ fontSize: '25px',color: 'black', }} />,
  	text: `论坛`,
  	hre:'/mobile/invitation'
	},{
    icon: <Icon type="picture" theme="twoTone" twoToneColor="#FFA500" style={{ fontSize: '25px',color: 'black' }} />,
    text: `相片`,
    hre:'/mobile/album'
  },{
    icon: <Icon type="notification" theme="twoTone" twoToneColor="#FFA07A" style={{ fontSize: '25px',color: 'black' }} />,
    text: `讲座`,
    hre:'/mobile/lecture'
  },{
    icon: <Icon type="pushpin" theme="twoTone" twoToneColor="#FF8C69" style={{ fontSize: '25px',color: 'black' }} />,
    text: `友链`,
    hre:'/mobile/friendurl'
  }
]
class GridExample extends React.Component {
 
  
	render() {
		return(
			<div>
			    <div className="sub-title">首页</div>
				    <Grid data={arr}
				      renderItem={dataItem => (
				      	<Link to = {dataItem.hre}>
				        <div style={{ padding: '5px' }}>
				         {dataItem.icon}
				          <div style={{ color: '#888', fontSize: '14px', marginTop: '12px' }}>
				           <span>{dataItem.text}</span>
				          </div>
				        </div>
				        </Link>
				      )}
				    />
			 </div>
		);
	}
}
   
class ViewIndex extends React.Component{

  constructor(props) {
    super(props);
    this.state = {
      all: [],
    }
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    //alert(this.state.lectureTitle);
    fetch(SelectLectureUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'pageSize=5'
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({all: data.resultBean.items});
        } else {
          
          message.error(data.msg);
        }
      }
    )
  }
  render() {
    return(
      <div>
        <div style={{ marginTop:'0px',  padding:'20px'}}>
         最近讲座
        </div>
         <div style={{ backgroundColor:'#ffffff', padding:'30px'}}>
          <Timeline>
            {
              this.state.all.map(item => {
                  return(
                    <div>
                    {item.isDone==1?<Timeline.Item color="green">
                     <Link to={'/mobile/lectureDetail/'+item.lectureId}>
                     <p style={{fontFamily:'Wawati SC', color:'#3B3B3B'}}>{item.lectureTitle}</p>
                     </Link>
                    </Timeline.Item>:
                    <Timeline.Item dot={<Icon type="clock-circle-o" style={{ fontSize: '16px' }} />} color="red">
                     <Link to={'/mobile/lectureDetail/'+item.lectureId}>
                     <p style={{fontFamily:'Wawati SC', color:'#8B8B83'}}><s>{item.lectureTitle}</s></p>
                     </Link>
                    </Timeline.Item>}
                    </div>
                  )
              })
            }
          </Timeline>
        </div>
      </div>
    );
  }
}

class MobileHome extends React.Component {
  state = {
    data: ['../static/images/vidio.jpg', '../static/images/vidio.jpg'],
    imgHeight: 176,
  }
  
  componentDidMount() {
    //alert(cookie.load('openId'))
    /*fetch(WLoginUrl,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
        body: 'openId='+cookie.load('openId')
    }).then(res => res.json()).then(
      data => {
          //alert(data.code);
          if(data.code==0) {
          cookie.save('token', data.resultBean.token);
                 // message.info('验证成功，欢迎登录');
                  console.log(cookie.load('token'));
          }
          else {
            //cookie.remove('token');
                  message.info( data.msg);
                  //this.props.history.push('mobilelogin');
            return false;
        }
      }
    )*/
    // simulate img loading
    setTimeout(() => {
      this.setState({
        data: ['url(../static/images/cuitacm.jpg)','../static/images/vidio.jpg', '../static/images/background.jpg' ],
      });
    }, 100);
  }
  render() {
    return (
    <div className="mobileBodyMain">
    <div style={{ width:'100%', padding:'-10px'}}>
      <WingBlank >
        <Carousel
          autoplay={false}
          infinite
          beforeChange={(from, to) => console.log(`slide from ${from} to ${to}`)}
          afterChange={index => console.log('slide to', index)}
        >

            <a
              key="1"
              style={{ display: 'inline-block', width: '100%', height: '150px' }}
            >
              <img
                src={require('../static/images/cuitacm.jpg')}
                alt=""
                className="imageM"
                style={{ verticalAlign: 'top', }}
                onLoad={() => {
                  // fire window resize event to change height
                  window.dispatchEvent(new Event('resize'));
                  this.setState({ imgHeight: 'auto' });
                }}
              />
            </a>
            <a
              key="2"
              style={{ display: 'inline-block', width: '100%', height: '150px' }}
            >
              <img
                src={require('../static/images/vidio.jpg')}
                alt=""
                className="imageM"
                style={{ verticalAlign: 'top', }}
                onLoad={() => {
                  // fire window resize event to change height
                  window.dispatchEvent(new Event('resize'));
                  this.setState({ imgHeight: 'auto' });
                }}
              />
            </a>
            <a
              key="3"
              style={{ display: 'inline-block', width: '100%', height: '150px' }}
            >
              <img
                src={require('../static/images/background.jpg')}
                alt=""
                className="imageM"
                style={{ verticalAlign: 'top', }}
                onLoad={() => {
                  // fire window resize event to change height
                  window.dispatchEvent(new Event('resize'));
                  this.setState({ imgHeight: 'auto' });
                }}
              />
            </a>
        </Carousel>
      </WingBlank>
      </div>
      <GridExample />
      <ViewIndex/>
    </div>
    );
  }
}
export default MobileHome;