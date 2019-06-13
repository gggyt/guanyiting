import React from 'react';
import { Grid } from 'antd-mobile';
import { Icon, Divider } from 'antd';
import { Carousel, WingBlank } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

require('./static/home.css');
class Head extends React.Component {
  

  render() {
    return (
    <div>
	    <div className="head">
	      <Link to="/mobilefirst"><Icon type="home" style={{ fontSize: '25px',color: 'black' }} /></Link>
	       <Divider type="vertical" />
	       <span className="headS">CUIT-ACM</span>
      	</div>
    </div>
    );
  }
}
export default Head;