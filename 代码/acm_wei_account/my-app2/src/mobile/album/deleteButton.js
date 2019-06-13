import React from 'react';
import { Card } from 'antd';
import { Tabs, Row, Col, Empty  } from 'antd';
import { Upload, Icon, Avatar,Button,  message } from 'antd';
import { Pagination, Modal, Toast, } from 'antd-mobile';
import ManageAlbum from './manageAlbum';
import '../../static/my/css/album.css';
import cookie from 'react-cookies';
import {SelectMyPhoto} from '../../config/router.js';
import {UploadImg1} from '../../config/router.js';
import {AddPhoto} from '../../config/router.js';
import {DeleteAlbum} from '../../config/router.js';
import {DeletePhoto} from '../../config/router.js';
import {BeCover} from '../../config/router.js';
import {AlbumDetail} from '../../config/router.js';
import {EventEmitter2} from 'eventemitter2';
const alert = Modal.alert;

class DeleteButon extends React.Component {

  render() {
    return(
       <Button
            type="danger"
            onClick={() =>
              alert('Delete', '确认删除???', [
                { text: 'Cancel', onPress: () => console.log('cancel') },
                { text: 'Ok', onPress: console.log('ok') },
              ])
            }
          >
          删除
      </Button>
    );
  }
}

export default DeleteButon;