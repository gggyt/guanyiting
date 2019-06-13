
import React from 'react';
import E from 'wangeditor'

import { Menu, Icon, Button, Input, Checkbox, Modal,Row, Col, Pagination,  Table, Divider, Tag,Alert ,Popconfirm, message } from 'antd';
import cookie from 'react-cookies';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import 'antd/lib/date-picker/style/css'; 
import 'antd/dist/antd.css';
import '../static/my/css/classfication.css';
import {SelectProblem} from '../../config/router.js';
import {DeleteProblemUrl} from '../../config/router.js';
import {DetailProblemUrl} from '../../config/router.js';
import {UploadImg} from '../../config/router.js';

import {UpdateAnnounceFirst} from '../../config/router.js';
import {AddAnsUrl} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2'
var emitter = new EventEmitter2()
var emitter2 = new EventEmitter2()

const id = -1;

class Ans extends React.Component {
  state = {visable: false};

  constructor(props) {
    super(props);
    this.state = {
      myAns:'',
      editorContent: '',
      editorContentText:'',
      editor:'',
    }
  }

  componentWillMount() {
    this.getData();
  }
  componentDidMount() {
    const elem = this.refs.editorElem
    const editor = new E(elem)
    this.setState({editor:editor})
    editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
    editor.customConfig.uploadFileName = 'myFileName';
    editor.customConfig.uploadImgServer = UploadImg;
    editor.customConfig.menus = [
    'head',  // 标题
    'bold',  // 粗体
    'fontSize',  // 字号
    'italic',  // 斜体
    'foreColor',  // 文字颜色
    'list',  // 列表
    'quote',  // 引用
    'image',  // 插入图片
    ];
    editor.customConfig.uploadImgHooks = { 
    customInsert: function (insertImg, result, editor) { 
      var url =result.data; insertImg(url); 
    } 
    };

    // 使用 onchange 函数监听内容的变化，并实时更新到 state 中
    editor.customConfig.onchange = html => {
      this.setState({
        editorContent: html
      })
      this.setState({editorContentText: editor.txt.text()})
      emitter.emit('changAns', html)
    }
    editor.create()
  }
  getData() {
    fetch(DetailProblemUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'problemId='+this.props.problemId
    }).then( res => res.json()).then(
      data => {
        if (data.code==0) {
          this.setState({myAns: data.resultBean.myAns});
          this.setState({editorContent: this.state.editor.txt.html(data.resultBean.myAns)});
        } else {
          message.error(data.msg)
        }
      }
  
    )
  }
 

 
  render() {
    return(
      <div ref="editorElem" className="toolbar">
        </div>  
    )
  }
}

class UpdateAns extends React.Component {
  state = {visable: false};

  constructor(props) {
    super(props);
    this.state = {
      myAns:'',
    }
    emitter.on('changAns', this.changeAns.bind(this))
  }

  changeAns(ans) {
    this.setState({myAns: ans});
  }

  componentWillMount() {
    //this.getData();
  }

  
  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleOk = e => {
    console.log(e);
    fetch(AddAnsUrl, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'problemId='+this.props.problemId+'&ans='+encodeURI(this.state.myAns)
    }).then( res => res.json()).then(
      data => {
        if (data.code==0) {
          message.success('修改成功');
        } else {
          message.error(data.msg)
        }
      }
  
    )
    this.setState({
      visible: false,
    });
  };

  handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

 
  render() {
    return(
      <span>
        <a href="javascript:;" onClick={this.showModal}><span>修改题解</span></a>
        
        <Modal
          title={this.props.problemTitle}
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <Ans problemId={this.props.problemId} />
        </Modal>
      </span>
    )
  }
}

class ShowTable extends React.Component{
  constructor(props) {
    super(props);
    this.tmp = this.tmp.bind(this);
    this.columns = [{
      title: '问题标题',
      dataIndex: 'problemTitle',
      key: 'problemTitle',
      render: (text, record) => (
        <span>
        <a href="javascript:;"><Link to={'/updateProblem/'+record.problemId}>{record.problemTitle}</Link></a>
          
           </span>
       ),
    }, {
      title: '创建人',
      dataIndex: 'username',
      key: 'username',
    }, {
      title: '创建时间',
      dataIndex: 'createDate',
      key: 'createDate',
    },  {
      title: '操作',
      key: 'action',
      render: (text, record) => (
        <span>
        <a href="javascript:;"><Link to={'/manageAns/'+record.problemId}>查看题解</Link></a>
        <Divider type="vertical" />
          <UpdateAns problemId={record.problemId} problemTitle={record.problemTitle}/>
          <Divider type="vertical" />
          <a href="javascript:;"><Link to={'/updateProblem/'+record.problemId}>修改</Link></a>
          <Divider type="vertical" />
          <Popconfirm title="确定删除?" onConfirm={() => this.handleDelete(record.problemId)}>
            <a  href="javascript:;">删除</a>
          </Popconfirm>
           </span>
       ),
    }
    ];
  }
  
  tmp = (key) => {
    console.log("------"+key);
    emitter2.emit('changeShow', key);

  }
  handleDelete = (problemId) => {
    console.log("------");
    fetch(DeleteProblemUrl,{   //Fetch方法
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'problemId='+problemId

      }).then(res => res.json()).then(
        data => {
          if(data.code==0) {
            emitter.emit('changeFirstText', 'Second');
            message.success('操作成功');
          }
          else {
            message.error(data.msg);
          }
        }
    )
  }
  

  render() {
    return(
    <div>
      <Table columns={this.columns} dataSource={this.props.all} pagination={false} />
      
    </div>
    
    );
  }
}

class AllProblem extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      nowPage: 1,
      totalPage: 1,
      pageSize: 10,
      all: '',
      title: '',
    }
    this.titleChange = this.titleChange.bind(this);
    this.buttonClick = this.buttonClick.bind(this);
    emitter.on('changeFirstText', this.changeText.bind(this))
  }

  changeText( msg ){
    this.getClass();
  }
  componentWillMount() {
    this.getClass();
  }
  getClass() {
    //alert(this.state.competitionTitle);
    fetch(SelectProblem, {
      method: 'POST',
      headers: {
        'Authorization': cookie.load('token'),
        'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
      },
      body: 'problemTitle='+this.state.title+'&pageNum='+this.state.nowPage+'&pageSize='+this.state.pageSize
    }).then( res=> res.json()).then(
      data => {
        if (data.code==0) {
          if(data.resultBean.currentPage>0) {
            this.setState({nowPage: data.resultBean.currentPage});
          }else {
            this.setState({nowPage: 1});
          }
          this.setState({totalPage: data.resultBean.totalItems/data.resultBean.pageSize});
          this.setState({all: data.resultBean.items});
        } else {
          this.setState({nowPage: 1});
          this.setState({totalPage: 1});
          this.setState({all: ''});
          message.error(data.msg);
        }
      }
    )
  }
  titleChange(e) {
    this.setState({title: e.target.value}, ()=>this.getClass());
  }
  pageChange = (page) => {
    console.log(page);
    this.setState({ nowPage: page }, () => this.getClass());
    
  }
  buttonClick() {
    this.getClass();
  }
  render() {
    return(
      <div style={{ flex: 1, padding: "10px" }}>
        <div className="title">
          <h3>每日问题</h3>
        </div>
        <div className="searchF">
         <div className="example-input">
          <Input size="small" onChange={this.titleChange} placeholder="问题名称" style={{height:30 , width:150}}/>
          &nbsp;&nbsp;<Button type="primary" shape="circle" icon="search" onClick={this.buttonClick}/>
          </div>
        </div>
        <div className="search"> 
         <ShowTable all={this.state.all}/>
        </div>
        <div className="searchPage">
        <Pagination size="small" simple onChange={this.pageChange} total={this.state.totalPage*this.state.pageSize}
        pageSize={this.state.pageSize} defaultCurrent={this.state.nowPage} showQuickJumper />
      </div>
      </div>
    );
  }
}
class ShowProblem extends React.Component{
  constructor(props) {
    super(props);
    this.state={
      show: 1,
      id: 1,
    }
    emitter2.on('changeShow', this.changeShow.bind(this))
  }
  changeShow(cid) {
    if (this.state.show==1) {
      this.setState({show:0});
    } else {
      this.setState({show:1});
    }
    this.setState({id: cid})
  }
  render() {
    
    return(
      <div>
      <AllProblem />
      </div>
    );
  }
}
export default ShowProblem;