import React from 'react';
import { BrowserRouter as Router,Route} from 'react-router-dom';
import { Switch} from 'react-router-dom';
import Login from './Login';
import Register from './register';

import Aside from './back/backHome';

import Head from './mobile/home';
import AllInvitationView from './mobile/invitation/showInvitation';
import AddInvitationView from './mobile/invitation/addInvitation';
import UpdateInvitationView from './mobile/invitation/updateInvitation';
import AllMyInvitationView from './mobile/invitation/myInvitation';
import AllUserInvitationView from './mobile/invitation/userInvitation';
import ManageMyInvitation from './mobile/invitation/manageMyInvitation';
import AllMyComment from './mobile/invitation/myComment';
import AllUserComment from './mobile/invitation/userComment';
import MobileInvitationDetail from './mobile/invitation/invitationDetail';
import NewsMobileInvitationDetail from './mobile/invitation/weiInvitationDetail';
import ManageAlbum from './mobile/album/manageAlbum';
import MobilePhoto from './mobile/album/managePhoto';
import MyMobilePhoto from './mobile/album/myPhoto';
import MobileLogin from './mobile/mobilelogin';
import MobileFirst from './mobile/index';
import ShowLecture from './mobile/lecture/showLecture';
import MobileLectureDetail from './mobile/lecture/lectureDetail';
import AllMyLecture from './mobile/lecture/myLecture';
import NewLectureDetail from './mobile/lecture/newLectureDetail';
import ShowFriend from './mobile/friendurl/showFriendUrl';
import UserInfo from './mobile/my/myIndex';
import MyUserDetail from './mobile/my/userDetail';
import OtherUserInfo from './mobile/my/userIndex';
import AddProblem from './mobile/problem/addProblem';
import ResultProblem from './mobile/problem/resultProblem';
import AddAns from './mobile/problem/addAns';
import MobileProblemDetail from './mobile/problem/problemDetail';
import ManageMyProblem from './mobile/problem/manageMyProblem';
import UpdateProblem from './mobile/problem/updateProblem';
import UpdateProblemDetail from './mobile/problem/updateProblemDetail';
import UpdateAns from './mobile/problem/updateAns';

import MobileHome from './mobile/homeIndex';
import AllProblem from './mobile/problem/showProblem';

import AboutUs from './mobile/aboutUs';

import 'antd-mobile/dist/antd-mobile.css';
class App extends React.Component {

	render() {
		return (
			<Router >
				<div>
                    <Route path="/login" component={Login} />
                    <Route path="/register" component={Register} />
                    
                    <Route path="/Aside" component={Aside} />
                    

                    <Route path="/mobileIndex" component={MobileHome} />
                    <Route path="/mobile" component={Head}  />
                    <Route path="/mobile/invitation" component={AllInvitationView} />
                    <Route path="/mobile/updateInvitation/:id" component={UpdateInvitationView} />
                    <Route path="/mobile/myInvitation" component={AllMyInvitationView} />
                    <Route path="/mobile/userInvitation/:id" component={AllUserInvitationView} />
                    <Route path="/mobile/manageMyInvitation" component={ManageMyInvitation} />
                    <Route path="/mobile/myComment" component={AllMyComment} />
                    <Route path="/mobile/userComment/:id" component={AllUserComment} />
                    <Route path="/mobile/addInvitation" component={AddInvitationView} />
                    <Route path="/mobile/invitationDetatil/:id" component={MobileInvitationDetail} />

                    <Route path="/newInvitation" component={NewsMobileInvitationDetail} />

                    <Route path="/mobile/album" component={ManageAlbum} />
                    <Route path="/mobile/photo/:id" component={MobilePhoto} />
                    <Route path="/mobile/myPhoto" component={MyMobilePhoto} />
                    <Route path="/mobile/lecture" component={ShowLecture} />
                    <Route path="/mobile/myLecture" component={AllMyLecture} />
                    <Route path="/mobile/updateMyInfo" component={MyUserDetail} />

                    <Route path="/newLecture" component={NewLectureDetail} />

                    <Route path="/mobile/lectureDetail/:id" component={MobileLectureDetail} />
                    <Route path="/mobile/friendurl" component={ShowFriend} />
                    <Route path="/mobile/myIndex" component={UserInfo} />



                    <Route path="/addProblem" component={AddProblem} />
                    <Route path="/addProblemSuccess/:id" component={ResultProblem} />
                    <Route path="/addAns/:id" component={AddAns} />
                    <Route path="/mobile/problemDetail/:id" component={MobileProblemDetail} />
                    <Route path="/updateProblem/:id" component={UpdateProblem} />
                    <Route path="/updateProblemDetail/:id" component={UpdateProblemDetail} />
                    <Route path="/mobile/updateAns/:id" component={UpdateAns} />


                    <Route path="/mobilefirst" component={MobileFirst} />
                    <Route path="/mobilelogin" component={MobileLogin} />

                    <Route path="/mobilefirst/mobileHome" component={MobileHome} />
                    <Route path="/mobilefirst/mobileUserInfo" component={UserInfo} />
                    <Route path="/mobileAllPro" component={AllProblem} />

                    <Route path="/mobile/myProblem" component={ManageMyProblem} />

                    <Route path="/mobile/userIndex/:id" component={OtherUserInfo}/>
					
                    <Route path="/aboutUs" component={AboutUs} />
                    
			</div>
            </Router>
		)
	}
}

export default App;