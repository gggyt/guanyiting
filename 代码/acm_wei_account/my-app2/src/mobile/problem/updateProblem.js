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

class UpdateProblem extends React.Component{
  
  render(){
    return(
      <div>
        
        <Link to={'/updateProblemDetail/'+this.props.match.params.id}><Button type="primary" style={{marginTop:40}} >修改题目</Button></Link>
        <Link to={'/mobile/updateAns/'+this.props.match.params.id}><Button type="primary" style={{marginTop:20}} >修改我的题解</Button></Link>
      </div>
    );
  }
  
}


export default UpdateProblem;