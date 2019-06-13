import React from 'react';
import { Grid } from 'antd-mobile';
import cookie from 'react-cookies';
import { Icon, Divider, message } from 'antd';
import { Carousel, WingBlank } from 'antd-mobile';
import {LoginUrl} from '../config/router.js';
import {WLogin} from '../config/router.js';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

require('./static/home.css');
class MobileLogin extends React.Component {
 
 constructor(props) {
    super(props);
    this.state={
      xx:'',
      title:'',
    }
  }
  componentWillMount() {
    console.log(this.props.match);
    console.log(this.props.location.search);
    console.log(this.props.location.query);
    const query = this.props.location.search 
    const arr = query.split('&') 
    const successCount = arr[0].substr(6);
    //alert(successCount);
    fetch(WLogin, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'code='+successCount
    }).then( res=> res.json()).then(
      data => {
        //alert(data.msg);
        if (data.code==0) {
          //alert(data.msg);
          cookie.save('openId', data.msg, 10000);
          cookie.save('token', data.resultBean.token, 1000);
          this.props.history.push('/mobilefirst');
        }  
        else if (data.code==2) {
          cookie.save('openId', data.resultBean.openId, 10000);
          cookie.save('image', data.resultBean.image, 10000);
          this.props.history.push('/register');
        } 
        else {
          this.props.history.push('/mobilelogin');
          this.setState({title:'未授权'});
        }
        
      }
    )
    
  }
  render() {
    console.log(this.props.match);
    console.log(this.props.location.search);
    console.log(this.props.location.query);
    const query = this.props.location.search 
    const arr = query.split('&') 
    const successCount = arr[0].substr(6)
    return (
    <div>
      {this.state.title}
    </div>
    );
  }
}
export default MobileLogin;

