import React from 'react';
import { List } from 'antd-mobile';
import { Menu, Input, Checkbox, Row, Col, message } from 'antd';
import {  Button,InputItem,Toast, Result, Icon } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import cookie from 'react-cookies';
import E from 'wangeditor';
import {AddInvitation} from '../../config/router.js';
import {UploadImg} from '../../config/router.js';
require('../static/home.css');
const Item = List.Item;
const Brief = Item.Brief;

class ResultProblem extends React.Component{
  
  render(){
    return(
      <div>
        <Result
          img={<Icon type="check-circle" className="spe" style={{ fill: '#1F90E6' }} />}
          title="添加成功"
          message="您已成功发布今日问题"
        />
        <Link to={'/addAns/'+this.props.match.params.id}><Button type="primary" style={{marginTop:20}} >添加我的题解</Button></Link>
        <Link to='/mobilefirst'><Button type="primary" style={{marginTop:20}} >返回首页</Button></Link>
      </div>
    );
  }
  
}


export default ResultProblem;