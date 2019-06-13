import React from 'react';
import { List } from 'antd-mobile';
import { Menu, Icon, Input, Checkbox, Row, Col, message, } from 'antd';
import {  Button,InputItem,Toast, ListView } from 'antd-mobile';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import cookie from 'react-cookies';
import E from 'wangeditor';
import {AddInvitation} from '../config/router.js';
import MobileHome from './homeIndex';
import UserInfo from './my/myIndex';
import AllProblem from './problem/showProblem';

import { TabBar } from 'antd-mobile';


class MobileFirst extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedTab: 'blueTab',
      hidden: false,
    };
  }

  renderContent(pageText) {
    return (
      <div style={{ backgroundColor: 'white', height: '100%', textAlign: 'center' }}>
        <div style={{ paddingTop: 60 }}>Clicked “{pageText}” tab， show “{pageText}” information</div>
        <a style={{ display: 'block', marginTop: 40, marginBottom: 20, color: '#108ee9' }}
          onClick={(e) => {
            e.preventDefault();
            this.setState({
              hidden: !this.state.hidden,
            });
          }}
        >
          Click to show/hide tab-bar
        </a>
      </div>
    );
  }

  render() {
    const path = this.props.location.pathname;
    return (
      <div style={{ position: 'fixed', height: '100%', width: '100%', top: 0 }}>
        <TabBar
          unselectedTintColor="#949494"
          tintColor="#F08080"
          barTintColor="white"
          tabBarPosition="bottom"
          hidden={this.state.hidden}
          prerenderingSiblingsNumber={0}
        >
          <TabBar.Item
            title="首页"
            key="Life"
            icon={<Icon type="home" style={{ fontSize: '23px' }}/>
            }
            selectedIcon={<Icon type="home" style={{ fontSize: '23px' }} theme="twoTone" twoToneColor="#FF8C69" />
            }
            selected={this.state.selectedTab === 'blueTab'}
            onPress={() => {
              this.setState({
                selectedTab: 'blueTab',
              });
            }}
            data-seed="logId"
          >
            <MobileHome />
          </TabBar.Item>
          
          <TabBar.Item
            icon={
              <Icon type="plus-circle" style={{ fontSize: '23px' }} />
            }
            selectedIcon={
              <Icon type="plus-circle" style={{ fontSize: '23px' }} theme="twoTone" twoToneColor="#FF8C69" />
            }
            title="今日一题"
            key="my"
            selected={this.state.selectedTab === 'yellowTab'}
            onPress={() => {
              this.setState({
                selectedTab: 'yellowTab',
              });
            }}
          >
            <AllProblem />
          </TabBar.Item>
          <TabBar.Item
            icon={
              <Icon type="setting" style={{ fontSize: '23px' }} />
            }
            selectedIcon={
              <Icon type="setting" style={{ fontSize: '23px' }} theme="twoTone" twoToneColor="#FF8C69" />
            }
            title="个人中心"
            key="Koubei"
            selected={this.state.selectedTab === 'redTab'}
            onPress={() => {
              this.setState({
                selectedTab: 'redTab',
              });
            }}
            data-seed="logId1"
          >
            <UserInfo />
          </TabBar.Item>
          
        </TabBar>
      </div>
    );
  }
}


export default MobileFirst;