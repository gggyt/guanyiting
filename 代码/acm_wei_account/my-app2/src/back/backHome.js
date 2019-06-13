import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Menu, Icon, Button, Dropdown, message, Avatar } from 'antd';
import DatePicker from 'antd/lib/date-picker'; 
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import './static/my/css/home.css';
import {GetUserInfo} from '../config/router.js';
import routes from './config/backHomeConf';
import cookie from 'react-cookies';

const SubMenu = Menu.SubMenu;

const menu = (
  <Menu>
    <Menu.Item>
      <a rel="noopener noreferrer" href="http://localhost:3000/login">
        退出
      </a>
    </Menu.Item>
  </Menu>
);
class Aside extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username:'',
      image:'',
    }
    this.getData = this.getData.bind(this);
  }
  componentWillMount() {
    this.getData();
  }
  getData() {
    console.log(cookie.load('token'))
    fetch(GetUserInfo,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
    }).then(res => res.json()).then(
      data => {
        console.log(data);
        if(data.code==0) {
          this.setState({username: data.resultBean.username});
          this.setState({image: data.resultBean.image})
        }
        else {
          cookie.save('token', '');
          this.props.history.push('/login');
        }
      }
    )
  }

  render() {
    return (
    <Router basename="Aside">
    <div>
      <div className="head">
        <div className="fL">
          <h3>CUIT-ACM</h3>
        </div>
        <div className="fR">
          <Dropdown overlay={menu}>
            <a className="ant-dropdown-link" href="#">
              <Avatar src={this.state.image} />&nbsp;&nbsp;{this.state.username} <Icon type="menu" />
            </a>
          </Dropdown>
        </div>
      </div>
      <div  className="boxB">
      <div className="left" >
        <Menu
          defaultSelectedKeys={['2']}
          mode="inline"
          theme="light"
        >
          <Menu.Item key="2">
            <Link to="/"><Icon type="pie-chart" />
            <span>首页</span></Link>
          </Menu.Item>
          <Menu.Item key="1">
            <Link to="/manageUser"><Icon type="pie-chart" />
            <span>用户管理</span></Link>
          </Menu.Item>
           <SubMenu key="sub14" title={<span><Icon type="desktop" /><span>论坛</span></span>}>
            <Menu.Item key="20">
            <Link to="/addInvitation">添加帖子</Link>
            </Menu.Item>
            <Menu.Item key="21">
            <Link to = "/manageInvitation">管理帖子</Link>
            </Menu.Item>
          </SubMenu>
          <SubMenu key="sub3" title={<span><Icon type="appstore" /><span>相册</span></span>}>
            <Menu.Item key="11">
            <Link to="/album">管理相册</Link>
            </Menu.Item>
          </SubMenu>
          <SubMenu key="sub5" title={<span><Icon type="appstore" /><span>讲座</span></span>}>
            <Menu.Item key="15">
            <Link to="/addLecture">添加讲座</Link>
            </Menu.Item>
            <Menu.Item key="16">
            <Link to="/managelecture">管理讲座</Link>
            </Menu.Item>
          </SubMenu>
          <SubMenu key="sub6" title={<span><Icon type="appstore" /><span>友链</span></span>}>
            <Menu.Item key="17">
            <Link to="/manageFriendurl">管理友链</Link>
            </Menu.Item>
          </SubMenu>
          <SubMenu key="sub7" title={<span><Icon type="appstore" /><span>每日问题</span></span>}>
            <Menu.Item key="19">
            <Link to="/manageProblem">管理每日问题</Link>
            </Menu.Item>
          </SubMenu>
        </Menu>
        {routes.map((route, index) => (
            // You can render a <Route> in as many places
            // as you want in your app. It will render along
            // with any other <Route>s that also match the URL.
            // So, a sidebar or breadcrumbs or anything else
            // that requires you to render multiple things
            // in multiple places at the same URL is nothing
            // more than multiple <Route>s.
            <Route
              key={index}
              path={route.path}
              exact={route.exact}
              component={route.sidebar}
            />
          ))}
      </div>
      <div style={{ flex: 1, padding: "10px" }}>
          {routes.map((route, index) => (
            // Render more <Route>s with the same paths as
            // above, but different components this time.
            <Route
              key={index}
              path={route.path}
              exact={route.exact}
              component={route.main}
            />
          ))}
        </div>
      </div>
      </div>
      </Router>
    );
  }
}

export default Aside;