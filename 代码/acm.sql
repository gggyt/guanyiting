/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : acm

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-06-13 15:01:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `albumId` int(11) NOT NULL AUTO_INCREMENT,
  `albumName` varchar(255) DEFAULT NULL,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `coverPhotoId` bigint(11) DEFAULT '-1',
  `description` varchar(255) DEFAULT NULL,
  `isPublic` int(11) DEFAULT NULL,
  `isEffective` int(11) DEFAULT NULL,
  PRIMARY KEY (`albumId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('30', 'album1', '2', '2019-05-24 14:39:35', '1077454293576321', '', '1', '0');
INSERT INTO `album` VALUES ('31', 'album2', '2', '2019-05-24 14:40:13', '6996121240205470', '随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片', '1', '1');
INSERT INTO `album` VALUES ('32', '测试', '2', '2019-05-27 15:08:54', '4310326559678', '随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片随意上传图片', '1', '1');
INSERT INTO `album` VALUES ('33', '测试1', '2', '2019-05-28 13:14:37', '1683373878480', '123456789123456789123', '1', '1');
INSERT INTO `album` VALUES ('34', '测试2', '2', '2019-05-28 13:17:02', '6113867730378', '这只是测试相片', '0', '1');
INSERT INTO `album` VALUES ('35', '黄金客户', '2', '2019-05-28 13:20:27', '5844672922883', 'kllk', '0', '0');
INSERT INTO `album` VALUES ('36', '随手拍', '2', '2019-05-29 20:42:48', '6125884584560', '记录你们随手一拍的照片', '1', '1');
INSERT INTO `album` VALUES ('37', '奖状合集', '2', '2019-05-30 19:12:59', '527465766343', '请将各位的奖状照片上传至该相册', '1', '1');
INSERT INTO `album` VALUES ('38', '发送到', '2', '2019-05-30 19:14:15', '-1', '打算', '0', '0');
INSERT INTO `album` VALUES ('39', '测试惹我', '2', '2019-05-30 19:18:10', '-1', '测试第一张图片', '1', '0');
INSERT INTO `album` VALUES ('40', '胜多负少的', '2', '2019-05-30 19:18:42', '-1', '发送到', '1', '0');
INSERT INTO `album` VALUES ('41', '少奋斗烦得很', '2', '2019-05-30 19:20:49', '-1', '会更好', '1', '0');
INSERT INTO `album` VALUES ('42', '结核杆菌', '2', '2019-05-30 19:22:25', '-1', 'V型从', '1', '0');
INSERT INTO `album` VALUES ('43', '欢聚一堂', '2', '2019-05-30 19:23:54', '-1', '好贵他也', '1', '0');
INSERT INTO `album` VALUES ('44', '123', '2', '2019-05-30 19:26:26', '-1', '123', '1', '0');
INSERT INTO `album` VALUES ('45', '私人相册', '2', '2019-05-31 13:45:58', '-1', '管理员私人相册', '0', '1');
INSERT INTO `album` VALUES ('46', '私人相册1', '2', '2019-05-31 14:02:54', '-1', '大声道', '1', '1');
INSERT INTO `album` VALUES ('47', '私人的', '2', '2019-05-31 14:03:08', '-1', '防守打法', '0', '1');

-- ----------------------------
-- Table structure for `album_photo`
-- ----------------------------
DROP TABLE IF EXISTS `album_photo`;
CREATE TABLE `album_photo` (
  `apId` int(11) NOT NULL AUTO_INCREMENT,
  `albumId` int(11) DEFAULT NULL,
  `photoId` bigint(20) DEFAULT NULL,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isEffective` int(11) DEFAULT NULL,
  PRIMARY KEY (`apId`),
  KEY `albumId` (`albumId`),
  KEY `photoId` (`photoId`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album_photo
-- ----------------------------
INSERT INTO `album_photo` VALUES ('29', '30', '1077454293576321', '2', '2019-05-24 14:39:53', '1');
INSERT INTO `album_photo` VALUES ('30', '31', '6996121240205470', '2', '2019-05-24 14:40:19', '1');
INSERT INTO `album_photo` VALUES ('31', '31', '5281984972105396', '2', '2019-05-25 18:54:09', '1');
INSERT INTO `album_photo` VALUES ('32', '31', '5144879286788908', '2', '2019-05-27 14:50:44', '1');
INSERT INTO `album_photo` VALUES ('33', '31', '6682536410232941', '2', '2019-05-27 14:50:50', '1');
INSERT INTO `album_photo` VALUES ('34', '31', '5381433145448440', '2', '2019-05-27 14:50:55', '1');
INSERT INTO `album_photo` VALUES ('35', '32', '8405280586745577', '2', '2019-05-27 15:09:04', '1');
INSERT INTO `album_photo` VALUES ('36', '32', '1657735964929969', '2', '2019-05-27 15:09:08', '1');
INSERT INTO `album_photo` VALUES ('37', '32', '4310326559678', '2', '2019-05-27 15:28:43', '1');
INSERT INTO `album_photo` VALUES ('38', '33', '1683373878480', '2', '2019-05-28 13:24:49', '1');
INSERT INTO `album_photo` VALUES ('39', '33', '8992177120746', '2', '2019-05-28 13:25:23', '1');
INSERT INTO `album_photo` VALUES ('40', '35', '5832073138596', '2', '2019-05-28 13:36:35', '1');
INSERT INTO `album_photo` VALUES ('41', '35', '5844672922883', '2', '2019-05-28 13:38:37', '1');
INSERT INTO `album_photo` VALUES ('42', '34', '6113867730378', '2', '2019-05-28 13:41:08', '1');
INSERT INTO `album_photo` VALUES ('43', '33', '7821529605885', '2', '2019-05-28 13:55:31', '1');
INSERT INTO `album_photo` VALUES ('44', '33', '3670880179850', '6', '2019-05-28 21:11:19', '1');
INSERT INTO `album_photo` VALUES ('45', '36', '9899057005086', '3', '2019-05-29 20:43:03', '1');
INSERT INTO `album_photo` VALUES ('46', '36', '6013953879534', '3', '2019-05-29 20:44:55', '1');
INSERT INTO `album_photo` VALUES ('47', '35', '5639261536918', '2', '2019-05-30 16:26:58', '1');
INSERT INTO `album_photo` VALUES ('48', '36', '1106685899222', '2', '2019-05-30 16:40:35', '1');
INSERT INTO `album_photo` VALUES ('49', '37', '6282338346809', '4', '2019-05-30 19:13:18', '1');
INSERT INTO `album_photo` VALUES ('50', '37', '5462548293931', '4', '2019-05-30 19:13:26', '1');
INSERT INTO `album_photo` VALUES ('51', '38', '5487615861773', '2', '2019-05-30 19:14:23', '1');
INSERT INTO `album_photo` VALUES ('52', '39', '483542304900', '2', '2019-05-30 19:18:21', '1');
INSERT INTO `album_photo` VALUES ('53', '39', '4114989988965', '2', '2019-05-30 19:18:33', '1');
INSERT INTO `album_photo` VALUES ('54', '40', '3416422654370', '2', '2019-05-30 19:19:01', '1');
INSERT INTO `album_photo` VALUES ('55', '41', '1199349657191', '2', '2019-05-30 19:20:59', '1');
INSERT INTO `album_photo` VALUES ('56', '42', '7396917413828', '2', '2019-05-30 19:22:40', '1');
INSERT INTO `album_photo` VALUES ('57', '43', '7175051482058', '2', '2019-05-30 19:24:12', '1');
INSERT INTO `album_photo` VALUES ('58', '36', '6125884584560', '2', '2019-05-30 20:45:10', '1');
INSERT INTO `album_photo` VALUES ('59', '32', '8560377460994', '2', '2019-05-30 20:55:39', '1');
INSERT INTO `album_photo` VALUES ('60', '32', '1499797365498', '2', '2019-05-30 20:55:42', '1');
INSERT INTO `album_photo` VALUES ('61', '32', '2115611413028', '2', '2019-05-30 20:55:46', '1');
INSERT INTO `album_photo` VALUES ('62', '32', '1670782192802', '2', '2019-05-30 20:57:54', '1');
INSERT INTO `album_photo` VALUES ('63', '32', '2889813990830', '2', '2019-05-30 20:57:57', '1');
INSERT INTO `album_photo` VALUES ('64', '32', '3431225084314', '2', '2019-05-30 20:58:08', '1');
INSERT INTO `album_photo` VALUES ('65', '32', '6863295725236', '2', '2019-05-30 20:58:11', '1');
INSERT INTO `album_photo` VALUES ('66', '32', '6972938632727', '2', '2019-05-30 21:01:36', '1');
INSERT INTO `album_photo` VALUES ('67', '32', '6595340832828', '2', '2019-05-30 21:02:27', '1');
INSERT INTO `album_photo` VALUES ('68', '32', '9609881296735', '2', '2019-05-30 21:02:27', '1');
INSERT INTO `album_photo` VALUES ('69', '32', '8132066646405', '2', '2019-05-30 21:02:27', '1');
INSERT INTO `album_photo` VALUES ('70', '32', '1764879461640', '2', '2019-05-30 21:02:27', '1');
INSERT INTO `album_photo` VALUES ('71', '32', '692162586107', '2', '2019-05-30 21:02:27', '1');
INSERT INTO `album_photo` VALUES ('72', '32', '2819784293288', '2', '2019-05-30 21:02:28', '1');
INSERT INTO `album_photo` VALUES ('73', '32', '2800858062330', '2', '2019-05-30 21:02:28', '1');
INSERT INTO `album_photo` VALUES ('74', '32', '203158699594', '2', '2019-05-30 21:02:33', '1');
INSERT INTO `album_photo` VALUES ('75', '32', '9639052426447', '2', '2019-05-30 21:02:41', '1');
INSERT INTO `album_photo` VALUES ('76', '32', '9493493973112', '2', '2019-05-30 21:02:41', '1');
INSERT INTO `album_photo` VALUES ('77', '32', '6068859697741', '2', '2019-05-30 21:02:41', '1');
INSERT INTO `album_photo` VALUES ('78', '32', '5843801735487', '2', '2019-05-30 21:02:41', '1');
INSERT INTO `album_photo` VALUES ('79', '36', '7924144514340', '5', '2019-05-31 00:08:19', '1');
INSERT INTO `album_photo` VALUES ('80', '37', '5323706137795', '2', '2019-05-31 00:28:55', '1');
INSERT INTO `album_photo` VALUES ('81', '37', '503178109153', '2', '2019-05-31 00:29:22', '1');
INSERT INTO `album_photo` VALUES ('82', '37', '795178109809', '2', '2019-05-31 00:29:54', '1');
INSERT INTO `album_photo` VALUES ('83', '37', '8487252152527', '2', '2019-05-31 00:29:54', '1');
INSERT INTO `album_photo` VALUES ('84', '37', '1141006689360', '2', '2019-05-31 00:29:54', '1');
INSERT INTO `album_photo` VALUES ('85', '37', '6303796800188', '2', '2019-05-31 00:29:54', '1');
INSERT INTO `album_photo` VALUES ('86', '37', '9984177762028', '2', '2019-05-31 00:29:55', '1');
INSERT INTO `album_photo` VALUES ('87', '37', '2474393910840', '2', '2019-05-31 00:29:55', '1');
INSERT INTO `album_photo` VALUES ('88', '37', '7622172999696', '2', '2019-05-31 00:29:58', '1');
INSERT INTO `album_photo` VALUES ('89', '37', '1007291650333', '2', '2019-05-31 00:29:58', '1');
INSERT INTO `album_photo` VALUES ('90', '36', '4360449476855', '5', '2019-05-31 00:48:46', '1');
INSERT INTO `album_photo` VALUES ('91', '37', '7896654404694', '5', '2019-05-31 08:57:12', '1');
INSERT INTO `album_photo` VALUES ('92', '37', '527465766343', '5', '2019-05-31 09:03:30', '1');
INSERT INTO `album_photo` VALUES ('93', '37', '9999646525826', '5', '2019-05-31 14:02:02', '1');

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `announceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `announceTitle` varchar(50) DEFAULT NULL,
  `announceBody` text,
  `announceCreateUser` bigint(20) DEFAULT NULL,
  `announceCreateTime` datetime DEFAULT NULL,
  `announceUpdateUser` bigint(20) DEFAULT NULL,
  `announceUpdateTime` datetime DEFAULT NULL,
  `isEffective` int(11) DEFAULT NULL,
  `isFirst` int(11) DEFAULT NULL,
  `isPublic` int(11) DEFAULT NULL,
  PRIMARY KEY (`announceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------

-- ----------------------------
-- Table structure for `applylecture`
-- ----------------------------
DROP TABLE IF EXISTS `applylecture`;
CREATE TABLE `applylecture` (
  `applyLectureId` bigint(20) NOT NULL AUTO_INCREMENT,
  `LectureId` bigint(20) DEFAULT NULL,
  `joinUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isEffective` int(4) DEFAULT '1',
  PRIMARY KEY (`applyLectureId`),
  KEY `competitionId` (`LectureId`),
  CONSTRAINT `applylecture_ibfk_1` FOREIGN KEY (`LectureId`) REFERENCES `lecture` (`lectureId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applylecture
-- ----------------------------
INSERT INTO `applylecture` VALUES ('8', '2', '3', '2019-03-23 16:30:53', '1');
INSERT INTO `applylecture` VALUES ('9', '2', '2', '2019-03-23 16:31:31', '1');
INSERT INTO `applylecture` VALUES ('10', '3', '2', '2019-03-23 16:41:05', '0');
INSERT INTO `applylecture` VALUES ('11', '3', '2', '2019-03-23 16:43:08', '1');
INSERT INTO `applylecture` VALUES ('12', '2', '2', '2019-03-23 16:43:15', '1');
INSERT INTO `applylecture` VALUES ('13', '1', '2', '2019-03-23 16:43:19', '1');
INSERT INTO `applylecture` VALUES ('14', '9', '2', '2019-04-08 13:46:44', '1');
INSERT INTO `applylecture` VALUES ('15', '2720154942950151', '2', '2019-04-18 12:44:31', '1');
INSERT INTO `applylecture` VALUES ('18', '2720154942950151', '6', '2019-05-28 20:26:32', '1');
INSERT INTO `applylecture` VALUES ('19', '6332760766473', '4', '2019-05-30 09:06:06', '0');
INSERT INTO `applylecture` VALUES ('20', '2085708033698331', '4', '2019-05-30 19:00:41', '0');
INSERT INTO `applylecture` VALUES ('21', '2720154942950151', '4', '2019-05-30 19:02:22', '0');
INSERT INTO `applylecture` VALUES ('22', '6332760766473', '4', '2019-05-30 19:02:30', '0');
INSERT INTO `applylecture` VALUES ('23', '2085708033698331', '5', '2019-05-30 19:59:58', '0');
INSERT INTO `applylecture` VALUES ('24', '2085708033698331', '5', '2019-05-31 00:03:52', '1');
INSERT INTO `applylecture` VALUES ('25', '7469133945603', '7', '2019-05-31 08:10:47', '1');
INSERT INTO `applylecture` VALUES ('26', '7469133945603', '5', '2019-05-31 09:03:44', '0');
INSERT INTO `applylecture` VALUES ('27', '7469133945603', '5', '2019-05-31 09:09:15', '0');
INSERT INTO `applylecture` VALUES ('28', '7469133945603', '5', '2019-05-31 12:41:13', '0');
INSERT INTO `applylecture` VALUES ('29', '7469133945603', '5', '2019-05-31 13:31:16', '0');
INSERT INTO `applylecture` VALUES ('30', '7469133945603', '5', '2019-05-31 14:02:22', '0');
INSERT INTO `applylecture` VALUES ('31', '7469133945603', '5', '2019-05-31 14:02:26', '1');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `invitationId` bigint(20) DEFAULT NULL,
  `p_commentId` bigint(20) DEFAULT NULL,
  `commentBody` text,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isEffective` int(11) DEFAULT NULL,
  PRIMARY KEY (`commentId`),
  KEY `invitationId` (`invitationId`),
  CONSTRAINT `invitationId` FOREIGN KEY (`invitationId`) REFERENCES `invitation` (`invitationId`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('2', '230990923462386', '0', 'xxx111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111x', '2', '2019-02-09 16:31:05', '0');
INSERT INTO `comment` VALUES ('3', '230990923462386', '0', 'xxxxxxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111xxx11111111111111111111111111111111111111111111111111111111111', '3', '2019-02-09 16:37:55', '0');
INSERT INTO `comment` VALUES ('4', '8054001019179922', '0', 'ces测', '2', '2019-02-09 16:55:51', '1');
INSERT INTO `comment` VALUES ('5', '230990923462386', '3', 'xxxx', '2', '2019-02-09 16:56:32', '1');
INSERT INTO `comment` VALUES ('6', '230990923462386', '3', 'xxxx', '2', '2019-02-09 16:56:47', '1');
INSERT INTO `comment` VALUES ('7', '230990923462386', '3', '123', '2', '2019-02-09 21:37:10', '1');
INSERT INTO `comment` VALUES ('8', '230990923462386', '3', '123', '2', '2019-02-09 21:39:24', '1');
INSERT INTO `comment` VALUES ('9', '230990923462386', '3', '123', '2', '2019-02-09 21:39:57', '1');
INSERT INTO `comment` VALUES ('10', '230990923462386', '3', '124', '2', '2019-02-09 21:40:06', '1');
INSERT INTO `comment` VALUES ('11', '230990923462386', '10', '嘻嘻', '2', '2019-02-09 21:42:11', '1');
INSERT INTO `comment` VALUES ('12', '230990923462386', '9', '3', '2', '2019-02-09 21:43:02', '1');
INSERT INTO `comment` VALUES ('13', '230990923462386', '3', '123', '2', '2019-02-09 21:44:05', '1');
INSERT INTO `comment` VALUES ('14', '230990923462386', '3', '123', '2', '2019-02-09 21:44:37', '1');
INSERT INTO `comment` VALUES ('15', '230990923462386', '13', '123567', '2', '2019-02-09 21:44:44', '1');
INSERT INTO `comment` VALUES ('16', '230990923462386', '3', '12345', '2', '2019-02-09 21:45:48', '1');
INSERT INTO `comment` VALUES ('17', '230990923462386', '3', '测试', '2', '2019-02-09 21:46:10', '1');
INSERT INTO `comment` VALUES ('18', '230990923462386', '3', '测试', '2', '2019-02-09 21:46:33', '1');
INSERT INTO `comment` VALUES ('19', '230990923462386', '3', '123', '2', '2019-02-09 21:48:09', '1');
INSERT INTO `comment` VALUES ('20', '230990923462386', '3', '12', '2', '2019-02-09 21:48:44', '1');
INSERT INTO `comment` VALUES ('21', '230990923462386', '0', '123', '2', '2019-02-09 21:48:48', '0');
INSERT INTO `comment` VALUES ('22', '230990923462386', '21', '嘻嘻', '2', '2019-02-09 21:48:54', '1');
INSERT INTO `comment` VALUES ('23', '230990923462386', '21', '嘻嘻', '2', '2019-02-09 21:49:11', '1');
INSERT INTO `comment` VALUES ('24', '230990923462386', '21', '12cxc ', '2', '2019-02-09 21:52:19', '1');
INSERT INTO `comment` VALUES ('25', '230990923462386', '3', 'xzcf ', '2', '2019-02-09 21:53:19', '1');
INSERT INTO `comment` VALUES ('26', '230990923462386', '0', 'gg', '2', '2019-02-09 21:55:43', '1');
INSERT INTO `comment` VALUES ('27', '230990923462386', '26', '123', '2', '2019-02-09 21:55:58', '1');
INSERT INTO `comment` VALUES ('28', '230990923462386', '26', '123', '2', '2019-02-09 21:58:27', '1');
INSERT INTO `comment` VALUES ('29', '230990923462386', '26', '123', '2', '2019-02-09 21:58:38', '1');
INSERT INTO `comment` VALUES ('30', '230990923462386', '0', '1', '2', '2019-02-09 21:58:41', '1');
INSERT INTO `comment` VALUES ('31', '230990923462386', '30', '123', '2', '2019-02-09 21:58:46', '1');
INSERT INTO `comment` VALUES ('32', '230990923462386', '30', '123', '2', '2019-02-09 22:00:14', '1');
INSERT INTO `comment` VALUES ('33', '230990923462386', '30', '123', '2', '2019-02-09 22:00:18', '1');
INSERT INTO `comment` VALUES ('34', '230990923462386', '0', '1', '2', '2019-02-09 22:00:21', '0');
INSERT INTO `comment` VALUES ('35', '230990923462386', '30', '1', '2', '2019-02-09 22:01:14', '1');
INSERT INTO `comment` VALUES ('36', '230990923462386', '34', '1', '2', '2019-02-09 22:01:25', '1');
INSERT INTO `comment` VALUES ('37', '230990923462386', '34', '1', '2', '2019-02-09 22:01:34', '1');
INSERT INTO `comment` VALUES ('38', '230990923462386', '34', '1', '2', '2019-02-09 22:02:19', '1');
INSERT INTO `comment` VALUES ('39', '230990923462386', '34', '1', '2', '2019-02-09 22:03:51', '1');
INSERT INTO `comment` VALUES ('40', '230990923462386', '0', '1', '2', '2019-02-09 22:03:55', '0');
INSERT INTO `comment` VALUES ('41', '230990923462386', '40', '1', '2', '2019-02-09 22:04:03', '1');
INSERT INTO `comment` VALUES ('42', '230990923462386', '0', 'xxxxxxxxxxxxx', '2', '2019-02-09 22:05:40', '0');
INSERT INTO `comment` VALUES ('43', '230990923462386', '0', 'ceshi', '2', '2019-02-09 22:06:05', '1');
INSERT INTO `comment` VALUES ('44', '230990923462386', '43', '1', '2', '2019-02-09 22:06:09', '1');
INSERT INTO `comment` VALUES ('45', '230990923462386', '0', '123fd ', '2', '2019-02-09 22:06:52', '1');
INSERT INTO `comment` VALUES ('46', '230990923462386', '45', '123', '2', '2019-02-09 22:08:03', '1');
INSERT INTO `comment` VALUES ('47', '230990923462386', '0', 'xuxuha ', '2', '2019-02-09 22:08:49', '1');
INSERT INTO `comment` VALUES ('48', '230990923462386', '0', '12dsf gret ', '2', '2019-02-09 22:10:02', '1');
INSERT INTO `comment` VALUES ('49', '230990923462386', '0', 'grhgthytu', '2', '2019-02-09 22:10:11', '1');
INSERT INTO `comment` VALUES ('50', '230990923462386', '0', 'dffdg', '2', '2019-02-09 22:10:51', '1');
INSERT INTO `comment` VALUES ('51', '230990923462386', '0', 'dfg', '2', '2019-02-09 22:11:17', '1');
INSERT INTO `comment` VALUES ('52', '230990923462386', '51', '123', '2', '2019-02-09 22:11:37', '1');
INSERT INTO `comment` VALUES ('53', '230990923462386', '0', '1234', '2', '2019-02-09 22:11:42', '1');
INSERT INTO `comment` VALUES ('54', '230990923462386', '0', '士大夫', '2', '2019-02-09 22:13:08', '1');
INSERT INTO `comment` VALUES ('55', '230990923462386', '51', '·1323', '2', '2019-02-09 22:13:14', '1');
INSERT INTO `comment` VALUES ('56', '230990923462386', '0', '你好', '2', '2019-02-09 22:13:39', '1');
INSERT INTO `comment` VALUES ('57', '230990923462386', '56', '你好', '2', '2019-02-09 22:13:46', '1');
INSERT INTO `comment` VALUES ('58', '8054001019179922', '0', '你好啊', '2', '2019-02-09 22:14:16', '1');
INSERT INTO `comment` VALUES ('59', '230990923462386', '0', 'heihi', '3', '2019-02-09 22:16:28', '1');
INSERT INTO `comment` VALUES ('60', '230990923462386', '56', '回复@gyt: hehe', '3', '2019-02-09 22:16:35', '0');
INSERT INTO `comment` VALUES ('61', '230990923462386', '0', 'xgsb', '3', '2019-02-09 23:29:11', '0');
INSERT INTO `comment` VALUES ('62', '230990923462386', '61', '回复@xgg: qwerty', '3', '2019-02-09 23:29:23', '1');
INSERT INTO `comment` VALUES ('63', '3279702978103125', '0', '什么时候', '2', '2019-04-16 21:51:07', '1');
INSERT INTO `comment` VALUES ('64', '3279702978103125', '0', '你好', '2', '2019-04-16 21:51:36', '1');
INSERT INTO `comment` VALUES ('65', '230990923462386', '0', '你好呀', '2', '2019-04-17 09:17:01', '1');
INSERT INTO `comment` VALUES ('66', '230990923462386', '0', '爱的色放', '2', '2019-04-18 12:43:17', '1');
INSERT INTO `comment` VALUES ('67', '230990923462386', '0', '你好', '2', '2019-04-18 23:27:00', '0');
INSERT INTO `comment` VALUES ('68', '230990923462386', '0', '嘿嘿', '2', '2019-04-18 23:27:21', '1');
INSERT INTO `comment` VALUES ('69', '2364247792080840', '0', '你', '2', '2019-04-18 23:27:45', '1');
INSERT INTO `comment` VALUES ('70', '230990923462386', '0', '什么', '2', '2019-04-18 23:27:54', '1');
INSERT INTO `comment` VALUES ('71', '230990923462386', '0', '的功夫', '2', '2019-04-18 23:29:04', '1');
INSERT INTO `comment` VALUES ('72', '230990923462386', '0', '？？', '2', '2019-04-18 23:29:21', '1');
INSERT INTO `comment` VALUES ('73', '230990923462386', '0', '阿斯蒂芬', '2', '2019-04-18 23:30:01', '1');
INSERT INTO `comment` VALUES ('74', '230990923462386', '73', '回复@gyt: a案发当时', '2', '2019-04-18 23:30:06', '0');
INSERT INTO `comment` VALUES ('75', '230990923462386', '0', '夫是德国', '2', '2019-04-18 23:30:12', '1');
INSERT INTO `comment` VALUES ('76', '230990923462386', '0', '双方的', '2', '2019-05-22 14:39:30', '1');
INSERT INTO `comment` VALUES ('77', '954727870691122', '0', '我喜欢英短', '2', '2019-05-25 12:49:17', '1');
INSERT INTO `comment` VALUES ('78', '954727870691122', '0', '的方式', '2', '2019-05-25 12:49:32', '1');
INSERT INTO `comment` VALUES ('79', '954727870691122', '77', '回复@gyt: 我喜欢银渐层', '2', '2019-05-25 12:49:44', '1');
INSERT INTO `comment` VALUES ('80', '954727870691122', '77', '的撒', '2', '2019-05-25 12:49:51', '1');
INSERT INTO `comment` VALUES ('81', '954727870691122', '78', '回复@gyt: 什么，你在说什么，我听补丁呢？', '2', '2019-05-25 12:52:00', '1');
INSERT INTO `comment` VALUES ('82', '230990923462386', '67', '回复@gyt: 1', '2', '2019-05-25 12:59:00', '1');
INSERT INTO `comment` VALUES ('83', '230990923462386', '73', '回复@gyt: 1', '2', '2019-05-25 12:59:10', '0');
INSERT INTO `comment` VALUES ('84', '230990923462386', '73', '回复@gyt: 1', '2', '2019-05-25 12:59:13', '0');
INSERT INTO `comment` VALUES ('85', '230990923462386', '73', '1', '2', '2019-05-25 12:59:16', '1');
INSERT INTO `comment` VALUES ('86', '230990923462386', '73', '1', '2', '2019-05-25 12:59:19', '1');
INSERT INTO `comment` VALUES ('87', '230990923462386', '73', '1', '2', '2019-05-25 12:59:23', '1');
INSERT INTO `comment` VALUES ('88', '230990923462386', '73', '1', '2', '2019-05-25 12:59:30', '1');
INSERT INTO `comment` VALUES ('89', '230990923462386', '73', '2', '2', '2019-05-25 12:59:34', '1');
INSERT INTO `comment` VALUES ('90', '230990923462386', '73', '回复@gyt: 2', '2', '2019-05-25 13:01:26', '1');
INSERT INTO `comment` VALUES ('91', '230990923462386', '72', '回复@gyt: 发士大夫', '2', '2019-05-25 13:03:41', '0');
INSERT INTO `comment` VALUES ('92', '954727870691122', '0', '真爽', '2', '2019-05-25 13:11:23', '1');
INSERT INTO `comment` VALUES ('93', '954727870691122', '0', '的撒', '2', '2019-05-25 13:11:32', '1');
INSERT INTO `comment` VALUES ('94', '954727870691122', '0', '打算', '2', '2019-05-25 13:11:38', '1');
INSERT INTO `comment` VALUES ('95', '954727870691122', '0', '多少分', '2', '2019-05-25 13:11:43', '1');
INSERT INTO `comment` VALUES ('96', '954727870691122', '0', '十大', '2', '2019-05-25 13:11:55', '1');
INSERT INTO `comment` VALUES ('97', '954727870691122', '78', '回复@gyt: 打算', '2', '2019-05-25 13:12:13', '1');
INSERT INTO `comment` VALUES ('98', '954727870691122', '0', 'vxc', '2', '2019-05-25 18:45:54', '1');
INSERT INTO `comment` VALUES ('99', '230990923462386', '0', '是否', '2', '2019-05-25 18:47:08', '1');
INSERT INTO `comment` VALUES ('100', '230990923462386', '56', '回复@gyt: 11', '2', '2019-05-27 17:00:56', '1');
INSERT INTO `comment` VALUES ('101', '954727870691122', '98', '回复@gyt: 你在说什么', '2', '2019-05-28 12:56:31', '1');
INSERT INTO `comment` VALUES ('102', '954727870691122', '98', '回复@gyt: 111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '2', '2019-05-28 14:16:18', '1');
INSERT INTO `comment` VALUES ('103', '954727870691122', '0', '111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '2', '2019-05-28 14:18:57', '1');
INSERT INTO `comment` VALUES ('104', '536675195929624', '0', '呵呵', '3', '2019-05-28 14:34:23', '1');
INSERT INTO `comment` VALUES ('105', '2473559903859', '0', '你是什么呢', '6', '2019-05-28 20:30:02', '1');
INSERT INTO `comment` VALUES ('106', '1859766857732', '0', '好的，听管理员的话', '6', '2019-05-29 19:16:32', '1');
INSERT INTO `comment` VALUES ('107', '1859766857732', '0', '同意楼上', '6', '2019-05-29 19:16:38', '1');
INSERT INTO `comment` VALUES ('108', '1859766857732', '0', ' 1', '6', '2019-05-29 19:16:47', '1');
INSERT INTO `comment` VALUES ('109', '1859766857732', '108', '回复@gytd: 你是？', '6', '2019-05-29 19:16:55', '1');
INSERT INTO `comment` VALUES ('110', '1859766857732', '0', '好的，我会认真遵守ACM的纪律。', '3', '2019-05-29 20:26:52', '1');
INSERT INTO `comment` VALUES ('111', '5265346118918', '0', '我觉得应该加大对应的训练强度', '3', '2019-05-29 20:34:47', '1');
INSERT INTO `comment` VALUES ('112', '1859766857732', '0', '你好，我是第一次使用微信公众号，请各位多多指教。你好，我是第一次使用微信公众号，请各位多多指教。你好，我是第一次使用微信公众号，请各位多多指教。你好，我是第一次使用微信公众号，请各位多多指教。', '4', '2019-05-30 19:09:21', '1');
INSERT INTO `comment` VALUES ('113', '5628795717575', '0', '大家快来积极参与呀\n', '4', '2019-05-30 19:40:40', '1');
INSERT INTO `comment` VALUES ('114', '1859766857732', '108', '回复@gytd: 为什么不认真回复呢', '4', '2019-05-30 19:52:40', '1');
INSERT INTO `comment` VALUES ('115', '1859766857732', '108', '回复@gytd: 好好学习吧~', '4', '2019-05-30 19:52:48', '1');
INSERT INTO `comment` VALUES ('116', '1859766857732', '107', '回复@gytd: 我也同意', '4', '2019-05-30 19:53:07', '1');
INSERT INTO `comment` VALUES ('117', '1859766857732', '107', '佛挡杀佛', '4', '2019-05-30 19:53:14', '1');
INSERT INTO `comment` VALUES ('118', '1859766857732', '107', ' 分手的范德萨', '4', '2019-05-30 19:53:21', '1');
INSERT INTO `comment` VALUES ('119', '1859766857732', '108', '开开心心', '4', '2019-05-30 19:53:29', '1');
INSERT INTO `comment` VALUES ('120', '1859766857732', '108', '回复@gytd: 好的', '4', '2019-05-30 19:54:12', '1');
INSERT INTO `comment` VALUES ('121', '1859766857732', '108', '回复@gytd: 嘿嘿', '4', '2019-05-30 19:54:18', '1');
INSERT INTO `comment` VALUES ('122', '1859766857732', '0', '好的，我会遵守的', '5', '2019-05-30 19:59:27', '1');
INSERT INTO `comment` VALUES ('123', '1859766857732', '122', '回复@amy: 希望和大家和平相处', '5', '2019-05-30 19:59:43', '1');
INSERT INTO `comment` VALUES ('124', '1859766857732', '0', '士大夫', '5', '2019-05-30 19:59:50', '1');
INSERT INTO `comment` VALUES ('125', '1295658576479', '0', '发送到', '5', '2019-05-30 20:00:25', '1');
INSERT INTO `comment` VALUES ('126', '3868474992461', '0', '是的范德萨', '5', '2019-05-30 20:01:01', '1');
INSERT INTO `comment` VALUES ('127', '5628795717575', '113', '回复@www: 就很贵', '5', '2019-05-30 20:04:08', '1');
INSERT INTO `comment` VALUES ('128', '1859766857732', '0', '是的范德萨', '5', '2019-05-31 09:03:03', '1');
INSERT INTO `comment` VALUES ('129', '9511674303056', '0', '放到沙发上', '5', '2019-05-31 12:40:44', '1');
INSERT INTO `comment` VALUES ('130', '9511674303056', '129', '回复@amy: 是的法定', '5', '2019-05-31 12:40:49', '1');

-- ----------------------------
-- Table structure for `follow`
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `followId` bigint(20) NOT NULL AUTO_INCREMENT,
  `beUserId` int(11) DEFAULT NULL,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isEffective` int(11) DEFAULT '1',
  PRIMARY KEY (`followId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('1', '3', '2', '2019-05-26 14:12:25', '1');
INSERT INTO `follow` VALUES ('2', '4', '1', '2019-05-26 14:12:29', '0');
INSERT INTO `follow` VALUES ('3', '4', '1', '2019-05-26 14:12:49', '1');
INSERT INTO `follow` VALUES ('4', '4', '2', '2019-05-26 20:00:09', '0');
INSERT INTO `follow` VALUES ('5', '4', '2', '2019-05-26 20:07:36', '0');
INSERT INTO `follow` VALUES ('6', '4', '2', '2019-05-26 20:07:51', '0');
INSERT INTO `follow` VALUES ('7', '4', '2', '2019-05-26 20:08:25', '0');
INSERT INTO `follow` VALUES ('8', '4', '2', '2019-05-26 20:08:57', '0');
INSERT INTO `follow` VALUES ('9', '4', '2', '2019-05-26 20:11:18', '0');
INSERT INTO `follow` VALUES ('10', '4', '2', '2019-05-26 20:11:45', '0');
INSERT INTO `follow` VALUES ('11', '4', '2', '2019-05-26 20:12:44', '0');
INSERT INTO `follow` VALUES ('12', '4', '2', '2019-05-26 20:13:14', '0');
INSERT INTO `follow` VALUES ('13', '2', '6', '2019-05-28 21:10:47', '0');

-- ----------------------------
-- Table structure for `friendurl`
-- ----------------------------
DROP TABLE IF EXISTS `friendurl`;
CREATE TABLE `friendurl` (
  `friendurlId` bigint(20) NOT NULL AUTO_INCREMENT,
  `friendTitle` text,
  `friendBody` text,
  `createUser` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateUser` bigint(20) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `isEffective` int(11) DEFAULT '1',
  PRIMARY KEY (`friendurlId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendurl
-- ----------------------------
INSERT INTO `friendurl` VALUES ('1', '1', 'www.baidu.com', '2', '2019-03-24 17:15:28', '2', '2019-05-30 21:05:15', '0');
INSERT INTO `friendurl` VALUES ('2', '1111', '11', '2', '2019-03-24 17:16:07', '2', '2019-03-31 14:55:43', '0');
INSERT INTO `friendurl` VALUES ('3', 'sgf', 'dsg1', '2', '2019-03-31 14:51:31', '2', '2019-04-18 23:57:03', '0');
INSERT INTO `friendurl` VALUES ('4', '2', 'www.baidu.com', '2', '2019-04-18 23:57:13', '2', '2019-05-30 21:05:17', '0');
INSERT INTO `friendurl` VALUES ('5', '发送到', '士大夫', '2', '2019-05-28 13:58:09', '2', '2019-05-28 13:59:44', '0');
INSERT INTO `friendurl` VALUES ('6', '发送到', 'https://www.baidu.com', '2', '2019-05-28 13:59:58', '2', '2019-05-30 21:05:19', '0');
INSERT INTO `friendurl` VALUES ('7', '本地址', 'https://github.com/gggyt/acm_front/blob/master/acm_wei_account/my-app2/src/mobile/index.js', '2', '2019-05-28 14:04:53', '2', '2019-05-30 21:05:04', '0');
INSERT INTO `friendurl` VALUES ('8', '但是', 'http://www.baidu.com', '2', '2019-05-28 14:08:19', '2', '2019-05-30 21:05:01', '0');
INSERT INTO `friendurl` VALUES ('9', 'fsd ', 'http://www.baidu.com', '2', '2019-05-28 14:09:02', '2', '2019-05-30 21:04:58', '0');
INSERT INTO `friendurl` VALUES ('10', '测试', 'http://192.168.1.113:3000/Aside/manageFriendurl', '2', '2019-05-30 18:03:25', '2', '2019-05-30 21:04:14', '0');
INSERT INTO `friendurl` VALUES ('11', '牛客网-深圳校招', 'https://www.nowcoder.com/discuss/154351', '2', '2019-05-30 21:04:10', '2', '2019-05-30 21:04:10', '1');
INSERT INTO `friendurl` VALUES ('12', '百度', 'http://www.baidu.com', '2', '2019-05-30 21:05:44', '2', '2019-05-30 21:05:44', '1');
INSERT INTO `friendurl` VALUES ('13', '淘宝', 'https://uland.taobao.com/sem/tbsearch?refpid=mm_26632258_3504122_32538762', '2', '2019-05-30 21:06:33', '2', '2019-05-30 21:06:33', '1');
INSERT INTO `friendurl` VALUES ('14', '京东', 'https://www.jd.com/?cu=true', '2', '2019-05-30 21:07:09', '2', '2019-05-30 21:07:09', '1');
INSERT INTO `friendurl` VALUES ('15', '亚马逊', 'https://www.amazon.cn/', '2', '2019-05-30 21:11:38', '2', '2019-05-30 21:11:38', '1');
INSERT INTO `friendurl` VALUES ('16', '博客园', 'https://www.cnblogs.com/', '2', '2019-05-30 21:12:29', '2', '2019-05-30 21:12:29', '1');
INSERT INTO `friendurl` VALUES ('17', '牛客网', 'https://www.nowcoder.com/', '2', '2019-05-30 21:12:43', '2', '2019-05-30 21:12:43', '1');
INSERT INTO `friendurl` VALUES ('18', 'CSDN', 'https://www.csdn.net/', '2', '2019-05-30 21:12:59', '2', '2019-05-30 21:12:59', '1');
INSERT INTO `friendurl` VALUES ('19', '慕课网', 'https://class.imooc.com/?mc_marking=4e0b0537f151197140fed11920097988', '2', '2019-05-30 21:13:16', '2', '2019-05-30 21:13:16', '1');
INSERT INTO `friendurl` VALUES ('20', '教程网', 'http://www.jcwcn.com/', '2', '2019-05-30 21:14:33', '2', '2019-05-30 21:14:41', '0');
INSERT INTO `friendurl` VALUES ('21', '教程网', 'http://www.jcwcn.com/', '2', '2019-05-30 21:14:33', '2', '2019-05-30 21:14:43', '0');
INSERT INTO `friendurl` VALUES ('22', '教程网', 'http://www.jcwcn.com/', '2', '2019-05-30 21:14:33', '2', '2019-05-30 21:14:33', '1');
INSERT INTO `friendurl` VALUES ('23', '杭电oj', 'http://acm.hdu.edu.cn/', '2', '2019-05-30 21:15:21', '2', '2019-05-30 21:15:21', '1');

-- ----------------------------
-- Table structure for `invitation`
-- ----------------------------
DROP TABLE IF EXISTS `invitation`;
CREATE TABLE `invitation` (
  `invitationId` bigint(20) NOT NULL,
  `invitationTitle` varchar(100) DEFAULT NULL,
  `invitationBody` text,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateUser` int(11) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `readNum` int(11) DEFAULT NULL,
  `agreeNum` int(11) DEFAULT NULL,
  `isFirst` int(11) DEFAULT NULL,
  `isGreate` int(11) DEFAULT NULL COMMENT '是否加精',
  `isEffective` int(11) DEFAULT NULL,
  PRIMARY KEY (`invitationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of invitation
-- ----------------------------
INSERT INTO `invitation` VALUES ('1071434009936', '第一次来到ACM', '<p>&nbsp;&nbsp;&nbsp;&nbsp;这是我第一次参加学习集体活动，开始前内心还是不安，难耐，甚至激动的，参加之后才发现自己不虚此行。</p><p>&nbsp; &nbsp; ACM校队的同学都很友善，大家都乐于帮助他人，还有每周的讲座让我收获良多。<br></p><p>&nbsp; &nbsp; 非常感谢我校ACM校队给了我这么完美的体验。<br></p><p><img src=\"http://localhost:9999/image/c2813c9f-7911-44f7-b167-6f364fc569f2.png\" style=\"max-width:100%;\"><br></p>', '3', '2019-05-29 20:20:19', '3', '2019-05-29 20:20:19', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('1295658576479', '发送到', '<p>防守打法&nbsp;<img src=\"http://localhost:9999/image/1f1bdc67-ff0b-419e-b7e3-cdfad3f7bb1b.png\" style=\"max-width: 100%;\"></p>', '2', '2019-05-29 18:38:18', '2', '2019-05-29 18:38:18', '2', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('1859766857732', '关于CUIT-ACM的一些小规则', '<p>&nbsp; &nbsp; 非常感谢大家信任并使用ACM公众号，并在论坛区域永远发言，让我们感受到了我校对ACM感兴趣的学生的热血。</p><p>&nbsp; &nbsp; 但是没有规矩不成方圆，也请各位积极配合我们小队成员遵守以下规则：</p><ol><li>请文明用语</li><li>请勿发布违法犯忌的帖子</li><li>尊重他人劳动成果</li><li>切勿灌水</li><li>文明和谐友爱互助</li></ol>', '2', '2019-05-29 19:14:15', '2', '2019-05-30 16:23:52', '31', '0', '1', '1', '1');
INSERT INTO `invitation` VALUES ('2473559903859', '你是你是你是你是你是你是你是你是你是你是', 'nishishei 是否是<p>反倒是但是啊阿萨德</p>', '3', '2019-05-28 17:26:52', '3', '2019-05-28 18:56:07', '55', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('2879753588515', '测试添加帖子', '<p>发送到发送到<img src=\"http://localhost:9999/image/8a5c60b9-242e-49f8-8159-b6b535467918.png\" style=\"max-width: 100%;\"></p>', '5', '2019-05-31 13:49:51', '5', '2019-05-31 13:49:51', '2', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('3404944572162', '防守打法', '<p>反倒是</p>', '4', '2019-05-29 23:06:26', '4', '2019-05-29 23:06:26', '2', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('3868474992461', '测试帖子', '<p><img src=\"http://localhost:9999/image/ca54c0eb-8055-46da-b2ad-4088531a2ddd.png\" style=\"max-width:100%;\">你好，测试帖子<br></p>', '6', '2019-05-28 21:12:39', '6', '2019-05-28 21:12:39', '4', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('4730740970628', '防守打法  ', '<p>&nbsp;&nbsp;<img src=\"http://localhost:9999/image/e84472c1-98f8-4472-b420-cc516c3d768e.png\" style=\"max-width: 100%;\">&nbsp;防守打法</p>', '2', '2019-05-29 18:42:48', '2', '2019-05-29 18:42:48', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('5265346118918', '你们对我校ACM有什么建议呢？', '<p>rt，大家畅所欲言。</p>', '2', '2019-05-27 20:54:38', '2', '2019-05-27 20:54:38', '2', '0', '0', '1', '1');
INSERT INTO `invitation` VALUES ('5628795717575', '61快乐|寻找消失的童年', '<div>你的童年是什么样的？</div><div>是春天的风筝蚂蚱？还是夏天的冰棍西瓜？</div><div>是放学后为了五点半的动漫飞奔回家？</div><div>还是偷偷攒下的几毛钱买来辣条和头花？</div><div><br></div><div>你还记得，</div><div>你的童年是什么模样吗？</div><ul><li>参与下方有奖竞答（共12题），并将你的答案<strong>直接回复在本帖下</strong></li><li>每位牛友仅有<strong>1次</strong>参与机会，多次回复以<strong>最早发布的答案</strong>作为最终参与结果</li><li>6月1日公布中奖名单及正确答案~</li></ul><p>活动奖励：</p><ul><li>全部猜对的前5个牛友，送<strong>童年掌上游戏机（图左）</strong>一台！</li><li>每10个参与者（以id计算），抽1个牛友送<strong>牛可乐T恤（图右）</strong>一件！</li></ul><p><img src=\"http://localhost:9999/image/1b952547-07c5-4990-bed9-852ba81c11f6.png\" style=\"max-width:100%;\"><br></p>', '4', '2019-05-30 19:40:20', '4', '2019-05-30 19:40:20', '3', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('5976062703079', '测试', '<p>士大夫但是</p>', '3', '2019-05-28 14:37:12', '3', '2019-05-28 14:37:12', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('8123709015376', '测试发布帖子', '<p>分类考试的 &nbsp;&nbsp;<img src=\"http://localhost:9999/image/ced27c81-cf97-4c66-a945-829cd0df9f10.png\" style=\"max-width: 100%;\"></p><p>&nbsp; dskajd &nbsp;大 &nbsp;大&nbsp;</p><ol><li>&nbsp; 撒的的<br></li><li>大</li></ol>', '6', '2019-05-29 19:02:06', '6', '2019-05-29 19:02:49', '3', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('8403266149230', '大家好，测试添加帖子', '<p>', '2', '2019-05-29 16:20:30', '2', '2019-05-29 16:22:08', '4', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9511674303056', '发的所发生的', '<p>防守打法<img src=\"http://localhost:9999/image/ffb83cdf-03b3-4e6b-8538-b40035dc8ab4.png\" style=\"max-width: 100%;\"></p>', '5', '2019-05-31 12:40:37', '5', '2019-05-31 12:40:37', '2', '0', '1', '0', '1');
INSERT INTO `invitation` VALUES ('9605454425196', '测试添加帖子2', '<p>副书记&nbsp; 少奋斗</p><p>&nbsp;&nbsp;</p><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;撒</th><th>撒的&nbsp;</th></tr><tr><td>&nbsp;爱迪生</td><td>&nbsp;是大11111111111111<br>11111111111111111<br>11111111111111111111111111111111111<br>1111111111</td></tr></tbody></table><p><br></p><p>绕弯儿&nbsp; &nbsp;<span style=\"font-style: italic;\">而我&nbsp; &nbsp;</span></p><p><img src=\"http://localhost:9999/image/ebd6a2ea-2a10-4840-84e1-bd86ad018fba.png\" style=\"max-width:100%;\"><span style=\"font-style: italic;\"><br></span></p>', '2', '2019-05-29 16:24:54', '2', '2019-05-29 16:28:37', '3', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9816595231865', '大家好，我是Amy', '<p>很高兴认识大家 &nbsp; 我是Amy &nbsp;今年21岁</p><p>&nbsp; 下面是我的自拍照~</p><p><img src=\"http://localhost:9999/image/40ec0cb6-1dde-423a-9f41-da6e42bff51b.png\" style=\"max-width:100%;\"><br></p>', '5', '2019-05-31 00:07:51', '5', '2019-05-31 00:07:51', '3', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('230990923462386', '大家好', '<p>&nbsp; 大家好，我是对ACM感兴趣的王五，我们交个朋友吧~</p>', '2', '2019-02-05 17:20:42', '2', '2019-05-30 19:51:54', '4', '0', '0', '1', '1');
INSERT INTO `invitation` VALUES ('308897914006141', '嘻嘻', '<p>嘿嘿</p>', '2', '2019-02-05 17:04:21', '2', '2019-02-05 17:04:21', '0', '0', '0', '1', '0');
INSERT INTO `invitation` VALUES ('536675195929624', '快女老款车型', '<p>防守打法is的士大夫<img src=\"http://localhost:9999/image/17335ddf-8415-4da9-8261-b9a3d410d5d9.png\" style=\"max-width: 100%;\"></p>', '3', '2019-05-23 17:42:55', '3', '2019-05-23 17:43:30', '2', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('718016987019500', '法国韩国', '<p>的VS地方</p>', '2', '2019-04-18 12:42:56', '2', '2019-04-18 12:42:56', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('954727870691122', '大家喜欢猫吗？', '<p>今天看了只特别可爱的猫咪，大家喜欢猫吗？<img src=\"http://localhost:9999/image/00fd63cd-5cbe-4ecf-aa01-41a80de4aa2b.png\" style=\"max-width: 100%;\"></p>', '2', '2019-04-18 18:24:25', '2', '2019-04-18 18:24:25', '11', '0', '1', '1', '1');
INSERT INTO `invitation` VALUES ('1119390155742060', '恶趣味', '<p>发生的</p>', '2', '2019-02-05 17:10:26', '2', '2019-02-05 17:10:26', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('1435700423676110', 'vcxv 地方', '<p>生巅峰 是的</p>', '2', '2019-02-05 17:08:01', '2', '2019-02-05 17:08:01', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('1883505665513026', '今天天气真好', '<p>今天天气很好，心情也不错</p>', '2', '2019-04-17 09:11:11', '2', '2019-04-17 09:11:11', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('1892537549045909', '测试修改操作', '<p>你好啊是的范德萨</p>', '2', '2019-05-23 17:27:44', '2', '2019-05-23 17:27:47', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('2036456520214558', '士大夫', '<p>第三方防守打法</p>', '2', '2019-05-25 12:22:42', '2', '2019-05-27 20:55:59', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('2051039313976054', '梵蒂冈', '<p>电饭锅地方</p>', '2', '2019-05-25 12:22:26', '2', '2019-05-25 12:22:26', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('2364247792080840', '徐', '<p><img src=\"http://192.168.1.113:9999/image/3b0f2364-4e2d-4fcb-9e92-21a9cce028d7.jpg\" style=\"max-width:100%;\">执行程序士大夫<br></p>', '2', '2019-04-18 12:43:53', '2', '2019-05-23 17:35:26', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('2781522274799428', '124', '<p>地方</p>', '2', '2019-02-05 17:36:03', '2', '2019-02-05 17:36:03', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('3066211832274202', 'qq', 'qq', '2', '2019-02-05 16:41:30', '2', '2019-02-05 16:41:30', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('3219305957921195', '嘻嘻', '<p>会计法</p>', '2', '2019-02-05 17:22:38', '2', '2019-02-05 17:22:38', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('3279702978103125', '你好啊', '<p>罚款是假的回复</p>', '2', '2019-04-16 21:51:00', '2', '2019-04-16 21:51:00', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('3310312877769883', '呵呵', '<p><img src=\"http://localhost:9999/image/b0b9c996-6578-45ae-8200-dd57210b3b8c.png\" style=\"max-width:100%;\">嘻嘻<br></p>', '2', '2019-02-06 21:48:03', '2', '2019-02-06 21:48:03', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('3413284815091624', 'vcxv ', '<p>生巅峰', '2', '2019-02-05 17:07:42', '2', '2019-02-05 17:07:42', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('3703306343577686', '嘻嘻', '<p>你好</p>', '2', '2019-02-05 17:14:10', '2', '2019-02-05 17:14:10', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('4155492829009686', '124地方', '<p>苟富贵</p>', '2', '2019-02-05 17:29:35', '2', '2019-02-05 17:29:35', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('4845929065595230', 'vfg', '<p>苟富贵</p>', '2', '2019-02-05 17:24:26', '2', '2019-02-05 17:24:26', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('4855373045447920', '你好', '<p>分手的范德萨</p>', '3', '2019-05-23 17:40:35', '3', '2019-05-23 17:40:35', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('4915790288345535', '嘻嘻看', '<p>见覅</p>', '2', '2019-02-05 17:26:43', '2', '2019-02-05 17:26:43', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('5133061676258650', '234', '<p><img src=\"http://localhost:9999/image/6d963855-5967-4384-92ca-7a20c7dbee04.png\" style=\"max-width:100%;\">而对方<br></p>', '2', '2019-02-05 17:38:04', '2', '2019-02-05 17:38:04', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('5293074972392059', '你好，测试添加帖子功能', '<p><img src=\"http://localhost:9999/image/34e6ca56-5ba3-4133-bb1a-c45593642177.png\" style=\"max-width:100%;\"><span style=\"font-weight: bold;\">测试添加帖子成功</span><br></p>', '2', '2019-05-23 17:15:35', '2', '2019-05-23 17:15:35', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('5546779890534882', '嘻嘻', '<p>你好</p>', '2', '2019-02-05 17:38:20', '2', '2019-02-05 17:38:20', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('5703840197657974', '你好', '<p>你好</p>', '2', '2019-02-05 17:00:28', '2', '2019-02-05 17:00:28', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('5936212477099181', 'heheh ', '<p>dgf</p>', '3', '2019-02-06 21:52:54', '3', '2019-02-06 21:52:54', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('5937175664957778', '23', '<p>对方的</p>', '2', '2019-02-05 17:21:12', '2', '2019-02-05 17:21:12', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('6187656966395760', '测试', '<p>123456789的风格和健康</p>', '2', '2019-02-06 23:16:33', '2', '2019-02-06 23:16:33', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('6474734052243360', 'heihei', '<p><img src=\"http://localhost:9999/image/d59dd404-a192-4a2c-a5a9-440e0ad50a83.png\" style=\"max-width:100%;\">hehe<br></p>', '2', '2019-02-10 15:21:26', '2', '2019-02-10 15:21:26', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('6564625526063640', '12', '<p>32</p>', '2', '2019-02-05 17:18:08', '2', '2019-02-05 17:18:08', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('7564953237786583', '12', '<p>2564</p>', '2', '2019-02-05 17:33:20', '2', '2019-02-05 17:33:20', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('7809472500934933', '你好测试', '<h1>vkjdf;gl</h1><p>嘻嘻<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\" data-w-e=\"1\"></p><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>1', '2', '2019-02-08 20:14:59', '2', '2019-02-08 20:14:59', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('8045674857017881', '你好', '<p>你好啊</p>', '2', '2019-02-05 17:38:58', '2', '2019-02-05 17:38:58', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('8054001019179922', 'xgg', '<p>1234567890qwertyuiopsdfghjklzxcvbnm,wghjkl;dfghjklfghjkldfghjkl;zxcvbnm,rtk</p>', '3', '2019-02-06 21:51:27', '3', '2019-02-06 21:51:27', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('8210395643038462', '你你你你你你你你你你你你你你你你你你你你', '<p>你好啊</p>', '2', '2019-05-25 12:22:02', '2', '2019-05-25 12:22:13', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('8719956916988658', '户口挂靠', '<p>交互接口和</p>', '2', '2019-02-05 17:33:33', '2', '2019-02-05 17:33:33', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('9042283187470908', '表格', '<table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>', '2', '2019-02-08 20:15:53', '2', '2019-02-08 20:15:53', '0', '0', '0', '0', '0');
INSERT INTO `invitation` VALUES ('9184881617885980', '今天5.22', '<p>我在测试添加帖子功能<img src=\"http://localhost:9999/image/d7d8bdba-126f-4841-9613-f3d5dc642514.png\" style=\"max-width: 100%;\">地方少</p>', '2', '2019-05-22 13:14:12', '2', '2019-05-22 13:14:16', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9202820631981456', '231', '<p>反倒是</p>', '2', '2019-02-05 17:06:59', '2', '2019-02-05 17:06:59', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9315430920657146', '你好，这是测试', '<p>你好，这是测试添加帖子的帖子</p>', '2', '2019-05-03 13:54:56', '2', '2019-05-03 13:54:56', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9524921355119727', '13规范', '<p>地方法规</p>', '2', '2019-02-05 17:28:28', '2', '2019-02-05 17:28:28', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9807265304226575', '漆味', '<p>负担</p>', '2', '2019-02-05 17:02:57', '2', '2019-02-05 17:02:57', '0', '0', '0', '0', '1');
INSERT INTO `invitation` VALUES ('9924427597017446', 'qq', 'qq', '2', '2019-05-17 20:56:26', '2', '2019-05-17 20:56:26', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `lecture`
-- ----------------------------
DROP TABLE IF EXISTS `lecture`;
CREATE TABLE `lecture` (
  `lectureId` bigint(20) NOT NULL AUTO_INCREMENT,
  `lectureTitle` varchar(100) DEFAULT NULL,
  `lectureBody` text,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateUser` int(11) DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `isDone` int(11) DEFAULT '1' COMMENT '1 没结束 0结束',
  `isFirst` int(11) DEFAULT NULL,
  `isEffective` int(11) DEFAULT '1',
  PRIMARY KEY (`lectureId`)
) ENGINE=InnoDB AUTO_INCREMENT=4476177647457925 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lecture
-- ----------------------------
INSERT INTO `lecture` VALUES ('1', '1', '11', '2', '2019-03-11 22:41:14', '2', '2019-05-13 16:47:30', '1', '0', '0');
INSERT INTO `lecture` VALUES ('2', '1', '1', '2', '2019-03-22 21:39:08', '2', '2019-05-13 16:47:27', '1', '0', '0');
INSERT INTO `lecture` VALUES ('3', '3', '12', '2', '2019-03-22 21:39:12', '2', '2019-03-24 15:36:08', '0', '0', '0');
INSERT INTO `lecture` VALUES ('4', '11', '<p>23345</p>', '2', '2019-03-25 21:41:41', '2', '2019-04-18 23:56:46', '1', '0', '0');
INSERT INTO `lecture` VALUES ('5', '11', '<p>22</p>', '2', '2019-03-25 21:42:04', '2', '2019-04-18 23:56:44', '1', '0', '0');
INSERT INTO `lecture` VALUES ('6', '11', '<p>223</p>', '2', '2019-03-25 21:46:09', '2', '2019-05-13 16:47:26', '1', '0', '0');
INSERT INTO `lecture` VALUES ('7', 'test', '<p>kjfsdf</p>', '2', '2019-03-25 21:48:16', '2', '2019-04-18 23:56:40', '1', '0', '0');
INSERT INTO `lecture` VALUES ('8', '12', '<p>234</p>', '2', '2019-03-25 21:53:53', '2', '2019-04-18 23:56:42', '1', '0', '0');
INSERT INTO `lecture` VALUES ('9', '1232432', '<p>234543112</p>', '2', '2019-03-25 21:53:55', '2', '2019-04-18 23:56:38', '1', '0', '0');
INSERT INTO `lecture` VALUES ('10', '1234', '<p>dggd</p>', '2', '2019-03-25 21:54:46', '2', '2019-03-25 22:38:07', '1', '0', '0');
INSERT INTO `lecture` VALUES ('873381036022', '大', '<p>阿萨德 发送到烦反倒是&nbsp; &nbsp;<img src=\"http://localhost:9999/image/2eaca137-d02a-49ad-a53d-5ca18c1d29f7.png\" style=\"max-width: 100%;\">被第三方&nbsp; 反倒是</p>', '2', '2019-05-29 16:22:19', '2', '2019-05-29 19:10:33', '1', '0', '0');
INSERT INTO `lecture` VALUES ('2321546359670', '发送到', '<p>', '2', '2019-05-29 16:22:19', '2', '2019-05-29 19:10:30', '1', '0', '0');
INSERT INTO `lecture` VALUES ('2650477983086', ' 反倒是', '<p>地方少 地方少<img src=\"http://localhost:9999/image/9085be1b-e3eb-4028-8202-766ec31bb3f8.png\" style=\"max-width: 100%;\">&nbsp; &nbsp;&nbsp;<img src=\"http://localhost:9999/image/3f1ec30c-ae08-4e41-8c8c-d688c0b723bd.png\" style=\"max-width: 100%;\">&nbsp;分手</p><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;按时</th><th>是大&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;大</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><p><br></p><p><br></p>', '2', '2019-05-31 18:46:52', '2', '2019-05-29 19:09:16', '1', '0', '0');
INSERT INTO `lecture` VALUES ('3831833915138', '反倒是', '<p>', '2', '2019-05-29 16:22:19', '2', '2019-05-29 19:10:28', '1', '0', '0');
INSERT INTO `lecture` VALUES ('4838154584444', '反倒是 ', '<p>佛挡杀佛士大夫', '2', '2019-05-29 16:22:19', '2', '2019-05-29 19:10:32', '1', '0', '0');
INSERT INTO `lecture` VALUES ('6332760766473', '第一次ACM算法讲坛-DFS', '<p>&nbsp; &nbsp; 本次讲座是我校ACM校队第一次开展的讲座，主要内容如下：</p><p><br>1.什么是ACM<br><br>2.ACM入门<br><br>3.什么是DFS<br><br>4.对应任务布置<br><br><br>        时间：2019年5月1日<br><br>        地点：4012教室</p><p><br><br></p>', '2', '2019-05-01 19:20:52', '2', '2019-05-30 19:05:06', '0', '0', '1');
INSERT INTO `lecture` VALUES ('7469133945603', 'CUIT-ACM第二次讲座-BFS', '<p>&nbsp; &nbsp;大家好，ACM实验室又开课啦！</p><h1>&nbsp; 这次我们的主题是&nbsp; BFS</h1><p>&nbsp; &nbsp; 通过这次讲座，你会找知道如何走迷宫，怎么走路程最短。<br></p><p>&nbsp; &nbsp; 想玩迷宫游戏吗？<br></p><p>&nbsp; &nbsp; 快来参加我们的讲座吧！<br></p><p><br></p><p>&nbsp; &nbsp; &nbsp; &nbsp; 时间：2019年5月20日<br></p><p>&nbsp; &nbsp; &nbsp; &nbsp; 地点：6301实验室<br></p><p><br></p><h3>&nbsp; &nbsp; COME ON！</h3>', '2', '2019-05-20 19:00:57', '2', '2019-05-31 00:23:10', '1', '0', '1');
INSERT INTO `lecture` VALUES ('2085708033698331', 'CUIT-ACM第一次宣讲', '说明啊<p><br></p>', '2', '2019-04-24 15:10:39', '2', '2019-04-09 15:10:51', '1', '0', '1');
INSERT INTO `lecture` VALUES ('2720154942950151', 'BFS第一次讲座', '<p>的萨芬</p>', '2', '2019-04-11 15:09:38', '2', '2019-05-31 00:26:21', '0', '0', '1');
INSERT INTO `lecture` VALUES ('2887138914966933', '第一个讲座', '<p>欢迎参加成都信息工程大学ACM第一堂讲座</p>', '2', '2019-04-17 10:30:13', '2', '2019-05-13 16:47:33', '1', '0', '0');
INSERT INTO `lecture` VALUES ('4476177647457924', 'DFS第一次讲座', '<p>dgdfgdgfd</p>', '2', '2019-03-25 21:55:41', '2', '2019-03-25 22:33:15', '0', '0', '1');

-- ----------------------------
-- Table structure for `photo`
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `photoId` bigint(11) NOT NULL AUTO_INCREMENT,
  `photoName` varchar(50) DEFAULT NULL,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isPublic` int(11) DEFAULT NULL,
  `isEffective` int(11) DEFAULT NULL,
  `photoUrl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`photoId`)
) ENGINE=InnoDB AUTO_INCREMENT=8405280586745578 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('203158699594', '2019-05-30', '2', '2019-05-30 21:02:33', '1', '1', 'http://localhost:9999/image/photo/847c4368-fa41-46f2-9668-99e3ec1e90ec.png');
INSERT INTO `photo` VALUES ('483542304900', '2019-05-30', '2', '2019-05-30 19:18:21', '1', '0', 'http://localhost:9999/image/photo/2be8025d-671a-4d4c-983d-d3140b1036b9.png');
INSERT INTO `photo` VALUES ('503178109153', '2019-05-31', '2', '2019-05-31 00:29:22', '1', '1', 'http://localhost:9999/image/photo/47396130-0b5a-4184-8974-6e0dd5d1125f.jpg');
INSERT INTO `photo` VALUES ('527465766343', '2019-05-31', '5', '2019-05-31 09:03:30', '1', '1', 'http://localhost:9999/image/photo/15a69b19-6fab-48cd-ad26-e8df1b1cd5d0.jpg');
INSERT INTO `photo` VALUES ('692162586107', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '1', 'http://localhost:9999/image/photo/b842443b-3249-4d6a-bfda-b1f2fceb400c.png');
INSERT INTO `photo` VALUES ('795178109809', '2019-05-31', '2', '2019-05-31 00:29:53', '1', '1', 'http://localhost:9999/image/photo/d3d0bac7-96c0-4ca6-b837-3a50e41a5e5f.jpg');
INSERT INTO `photo` VALUES ('1007291650333', '2019-05-31', '2', '2019-05-31 00:29:58', '1', '1', 'http://localhost:9999/image/photo/b22f12e1-92c9-47b5-b0a4-191bfef6f9d6.jpg');
INSERT INTO `photo` VALUES ('1106685899222', '2019-05-30', '2', '2019-05-30 16:40:35', '1', '1', 'http://localhost:9999/image/photo/76353979-66b0-4acc-9781-891a5f34d532.png');
INSERT INTO `photo` VALUES ('1141006689360', '2019-05-31', '2', '2019-05-31 00:29:54', '1', '1', 'http://localhost:9999/image/photo/7dbdcbe8-699d-42d8-b351-9997a50353a9.jpg');
INSERT INTO `photo` VALUES ('1199349657191', '2019-05-30', '2', '2019-05-30 19:20:59', '1', '1', 'http://localhost:9999/image/photo/0a0aba78-a15b-48d9-a526-667b27b23e21.png');
INSERT INTO `photo` VALUES ('1499797365498', '2019-05-30', '2', '2019-05-30 20:55:42', '1', '0', 'http://localhost:9999/image/photo/aa114250-501c-484b-9efe-075b6157d185.png');
INSERT INTO `photo` VALUES ('1670782192802', '2019-05-30', '2', '2019-05-30 20:57:54', '1', '0', 'http://localhost:9999/image/photo/7081bd2c-7578-4a09-a218-60803edda1d8.png');
INSERT INTO `photo` VALUES ('1683373878480', '2019-05-28', '2', '2019-05-28 13:24:49', '1', '1', 'http://localhost:9999/image/photo/1b8975cb-1217-4fad-b28e-4022c3c24e03.png');
INSERT INTO `photo` VALUES ('1764879461640', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '1', 'http://localhost:9999/image/photo/83a9195f-eb92-4110-a44a-f0167ded780d.png');
INSERT INTO `photo` VALUES ('2115611413028', '2019-05-30', '2', '2019-05-30 20:55:46', '1', '0', 'http://localhost:9999/image/photo/05f0e3e4-85bd-44ee-a9db-0a7ccbd4fd51.png');
INSERT INTO `photo` VALUES ('2474393910840', '2019-05-31', '2', '2019-05-31 00:29:55', '1', '1', 'http://localhost:9999/image/photo/cc042fab-0513-4c7f-a816-430d24439097.jpg');
INSERT INTO `photo` VALUES ('2800858062330', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '1', 'http://localhost:9999/image/photo/34ee8308-5a3f-4733-91e2-e3047f61b00a.png');
INSERT INTO `photo` VALUES ('2819784293288', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '0', 'http://localhost:9999/image/photo/a77592c9-b715-4e06-bdbb-48299f7df817.png');
INSERT INTO `photo` VALUES ('2889813990830', '2019-05-30', '2', '2019-05-30 20:57:57', '1', '0', 'http://localhost:9999/image/photo/659f017c-665a-4925-8d0d-b82a6e983758.png');
INSERT INTO `photo` VALUES ('3416422654370', '2019-05-30', '2', '2019-05-30 19:19:01', '1', '1', 'http://localhost:9999/image/photo/23e77738-9aef-4cf6-ac95-4b637a64bbf0.png');
INSERT INTO `photo` VALUES ('3431225084314', '2019-05-30', '2', '2019-05-30 20:58:08', '1', '0', 'http://localhost:9999/image/photo/3197755e-8e09-46d7-b23d-586ee0ccb079.png');
INSERT INTO `photo` VALUES ('3670880179850', '2019-05-28', '6', '2019-05-28 21:11:19', '1', '1', 'http://localhost:9999/image/photo/601b0d1f-3ffa-42d9-b316-4354ecd8364f.png');
INSERT INTO `photo` VALUES ('4114989988965', '2019-05-30', '2', '2019-05-30 19:18:33', '1', '1', 'http://localhost:9999/image/photo/29616721-069d-43a3-9533-523ba840f7e4.png');
INSERT INTO `photo` VALUES ('4310326559678', '2019-05-27', '2', '2019-05-27 15:28:43', '1', '0', 'http://localhost:9999/image/photo/f873cb13-f962-4c9e-a4fd-642ab090044d.png');
INSERT INTO `photo` VALUES ('4360449476855', '2019-05-31', '5', '2019-05-31 00:48:46', '1', '1', 'http://localhost:9999/image/photo/d0b425b3-e3c6-47a5-a0c6-e07488432957.png');
INSERT INTO `photo` VALUES ('5323706137795', '2019-05-31', '2', '2019-05-31 00:28:55', '1', '1', 'http://localhost:9999/image/photo/e3c7719a-a847-48d9-aa0c-bc6e6e1a037e.jpg');
INSERT INTO `photo` VALUES ('5462548293931', '2019-05-30', '4', '2019-05-30 19:13:26', '1', '1', 'http://localhost:9999/image/photo/e794455c-bd7a-4330-b01a-51cf23a0bdfb.jpg');
INSERT INTO `photo` VALUES ('5487615861773', '2019-05-30', '2', '2019-05-30 19:14:23', '1', '1', 'http://localhost:9999/image/photo/59b460a8-52dc-4322-a8b9-208e578bd69f.png');
INSERT INTO `photo` VALUES ('5639261536918', '2019-05-30', '2', '2019-05-30 16:26:57', '1', '0', 'http://localhost:9999/image/photo/eb83c28c-b76a-4177-9fae-f0664249b3bc.docx');
INSERT INTO `photo` VALUES ('5832073138596', '2019-05-28', '2', '2019-05-28 13:36:35', '1', '1', 'http://localhost:9999/image/photo/1ddab12c-c645-4fbe-8640-a4f8edc61f73.png');
INSERT INTO `photo` VALUES ('5843801735487', '2019-05-30', '2', '2019-05-30 21:02:41', '1', '1', 'http://localhost:9999/image/photo/c2e7ea49-1a57-420c-960e-5217de0b636f.png');
INSERT INTO `photo` VALUES ('5844672922883', '2019-05-28', '2', '2019-05-28 13:38:37', '1', '1', 'http://localhost:9999/image/photo/9be5ad5c-f2bb-4ada-9d2d-402ca370dd45.png');
INSERT INTO `photo` VALUES ('6013953879534', '2019-05-29', '3', '2019-05-29 20:44:55', '1', '1', 'http://localhost:9999/image/photo/75085001-7028-426a-9dbf-4e25391fe413.png');
INSERT INTO `photo` VALUES ('6068859697741', '2019-05-30', '2', '2019-05-30 21:02:41', '1', '1', 'http://localhost:9999/image/photo/6e594251-7f4c-4efc-952a-e2403ce72cfc.png');
INSERT INTO `photo` VALUES ('6113867730378', '2019-05-28', '2', '2019-05-28 13:41:08', '1', '1', 'http://localhost:9999/image/photo/981d4cc6-7b18-4315-b08a-90dcbf4c3ad0.png');
INSERT INTO `photo` VALUES ('6125884584560', '2019-05-30', '2', '2019-05-30 20:45:10', '1', '1', 'http://localhost:9999/image/photo/0391df12-7265-49fe-ae5d-e3ed66fb5def.png');
INSERT INTO `photo` VALUES ('6282338346809', '2019-05-30', '4', '2019-05-30 19:13:18', '1', '1', 'http://localhost:9999/image/photo/9a203afc-b69f-4e25-b518-8b992349350f.jpg');
INSERT INTO `photo` VALUES ('6303796800188', '2019-05-31', '2', '2019-05-31 00:29:54', '1', '1', 'http://localhost:9999/image/photo/8148b09c-1568-4584-aae8-50520132902c.jpg');
INSERT INTO `photo` VALUES ('6595340832828', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '0', 'http://localhost:9999/image/photo/b42764fd-b62f-4aef-b423-8165c1346b6f.png');
INSERT INTO `photo` VALUES ('6863295725236', '2019-05-30', '2', '2019-05-30 20:58:11', '1', '0', 'http://localhost:9999/image/photo/dada684f-f007-4fd6-88ae-e609e2d2de30.png');
INSERT INTO `photo` VALUES ('6972938632727', '2019-05-30', '2', '2019-05-30 21:01:36', '1', '0', 'http://localhost:9999/image/photo/56036688-4c6f-4e43-82bb-194c232451ec.png');
INSERT INTO `photo` VALUES ('7175051482058', '2019-05-30', '2', '2019-05-30 19:24:12', '1', '1', 'http://localhost:9999/image/photo/fc44254c-cc20-4e61-9fc7-bbf3a2021727.png');
INSERT INTO `photo` VALUES ('7396917413828', '2019-05-30', '2', '2019-05-30 19:22:40', '1', '1', 'http://localhost:9999/image/photo/aa18f7b9-4057-4dbd-980d-d87fd41d7e89.png');
INSERT INTO `photo` VALUES ('7622172999696', '2019-05-31', '2', '2019-05-31 00:29:57', '1', '1', 'http://localhost:9999/image/photo/f67cd6f3-3348-4e7e-9c57-eba7a2657f1a.jpg');
INSERT INTO `photo` VALUES ('7821529605885', '2019-05-28', '2', '2019-05-28 13:55:31', '1', '1', 'http://localhost:9999/image/photo/2a0a177d-8390-473d-b2a6-327c81f672bf.png');
INSERT INTO `photo` VALUES ('7896654404694', '2019-05-31', '5', '2019-05-31 08:57:12', '1', '1', 'http://localhost:9999/image/photo/c2c37fc1-b246-4834-b336-3e7fbec4beb5.jpg');
INSERT INTO `photo` VALUES ('7924144514340', '2019-05-31', '5', '2019-05-31 00:08:19', '1', '1', 'http://localhost:9999/image/photo/6f99bbee-3367-49da-bfae-643a60b75ca3.png');
INSERT INTO `photo` VALUES ('8132066646405', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '0', 'http://localhost:9999/image/photo/31c473e2-3ebe-4d93-80ee-efd758579e00.png');
INSERT INTO `photo` VALUES ('8487252152527', '2019-05-31', '2', '2019-05-31 00:29:54', '1', '1', 'http://localhost:9999/image/photo/a4c5037c-2e30-4c9d-8f18-e1dc43c5e28d.jpg');
INSERT INTO `photo` VALUES ('8560377460994', '2019-05-30', '2', '2019-05-30 20:55:39', '1', '0', 'http://localhost:9999/image/photo/c65aa729-1b3e-4be9-a483-3814d2fa8b99.png');
INSERT INTO `photo` VALUES ('8992177120746', '2019-05-28', '2', '2019-05-28 13:25:23', '1', '1', 'http://localhost:9999/image/photo/e3f5f4d6-1176-4bcb-a687-a5b438cb1107.png');
INSERT INTO `photo` VALUES ('9493493973112', '2019-05-30', '2', '2019-05-30 21:02:41', '1', '1', 'http://localhost:9999/image/photo/e9b78cac-1bb0-4880-b9f8-4884fe809b35.png');
INSERT INTO `photo` VALUES ('9609881296735', '2019-05-30', '2', '2019-05-30 21:02:27', '1', '0', 'http://localhost:9999/image/photo/d352a725-b6e0-4466-b9fb-45c50837b025.png');
INSERT INTO `photo` VALUES ('9639052426447', '2019-05-30', '2', '2019-05-30 21:02:41', '1', '1', 'http://localhost:9999/image/photo/6b51a4a3-f3b5-4a07-bee1-266ac3901679.png');
INSERT INTO `photo` VALUES ('9899057005086', '2019-05-29', '3', '2019-05-29 20:43:03', '1', '0', 'http://localhost:9999/image/photo/8a6358ab-ea72-40c6-a2a7-6743d7044324.png');
INSERT INTO `photo` VALUES ('9984177762028', '2019-05-31', '2', '2019-05-31 00:29:55', '1', '1', 'http://localhost:9999/image/photo/08e07494-7b2c-4aa8-93e3-d5760e7255b8.jpg');
INSERT INTO `photo` VALUES ('9999646525826', '2019-05-31', '5', '2019-05-31 14:02:02', '1', '1', 'http://localhost:9999/image/photo/68ed3cbe-7c7b-4f0d-8f44-ef0475b43056.jpg');
INSERT INTO `photo` VALUES ('1077454293576321', '2019-05-24', '2', '2019-05-24 14:39:53', '1', '1', 'http://localhost:9999/image/photo/ec192344-70c0-4693-aff6-f430a213db7e.png');
INSERT INTO `photo` VALUES ('1657735964929969', '2019-05-27', '2', '2019-05-27 15:09:08', '1', '0', 'http://localhost:9999/image/photo/ad3b2f89-4026-4006-bd50-7b5bf96965cd.png');
INSERT INTO `photo` VALUES ('5144879286788908', '2019-05-27', '2', '2019-05-27 14:50:44', '1', '1', 'http://localhost:9999/image/photo/54015484-44bd-4844-a570-13f4cdd3da97.png');
INSERT INTO `photo` VALUES ('5281984972105396', '2019-05-25', '2', '2019-05-25 18:54:09', '1', '1', 'http://localhost:9999/image/photo/5051ed36-74bb-4f42-8e88-f5b6945ef0c7.jpg');
INSERT INTO `photo` VALUES ('5381433145448440', '2019-05-27', '2', '2019-05-27 14:50:55', '1', '1', 'http://localhost:9999/image/photo/9c87da5b-f9ff-4735-9885-50171723f5b1.png');
INSERT INTO `photo` VALUES ('6682536410232941', '2019-05-27', '2', '2019-05-27 14:50:50', '1', '1', 'http://localhost:9999/image/photo/dbe5196c-5f16-4136-b946-da5a334d18e8.png');
INSERT INTO `photo` VALUES ('6996121240205470', '2019-05-24', '2', '2019-05-24 14:40:19', '1', '1', 'http://localhost:9999/image/photo/01ab7424-2d2c-4797-9987-a02229cef894.png');
INSERT INTO `photo` VALUES ('8405280586745577', '2019-05-27', '2', '2019-05-27 15:09:04', '1', '0', 'http://localhost:9999/image/photo/f6f63929-24f3-4ac8-8f72-3c88e67c6c16.png');

-- ----------------------------
-- Table structure for `problem`
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `problemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `problemTitle` varchar(50) DEFAULT NULL,
  `problemBody` text,
  `myAns` text,
  `createUser` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `updateUser` int(11) DEFAULT NULL,
  `readNum` int(11) DEFAULT '0',
  `isEffective` int(11) DEFAULT '1',
  PRIMARY KEY (`problemId`)
) ENGINE=InnoDB AUTO_INCREMENT=9911228760115524 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES ('1', '1', '2', '<p>VS的发射点</p>', '2', '2019-04-15 22:29:41', '2019-04-15 22:29:41', '2', '123', '0');
INSERT INTO `problem` VALUES ('2', '11', '22', '', '2', '2019-04-15 23:11:18', '2019-04-15 23:11:18', '2', '3', '0');
INSERT INTO `problem` VALUES ('3', '2', '22', '', '2', '2019-04-15 23:11:33', '2019-04-15 23:11:33', '2', '3', '0');
INSERT INTO `problem` VALUES ('4', '3', '22', '', '2', '2019-04-15 23:11:36', '2019-04-15 23:11:36', '2', '0', '0');
INSERT INTO `problem` VALUES ('5', '官怡婷的今日问题', '<p>分类<img src=\"http://192.168.31.69:9999/image/a9d0571d-8dbb-4f63-a18f-f199c265bb2f.png\" style=\"max-width: 100%;\"><img src=\"http://192.168.1.113:9999/image/ee201d96-bee7-45b6-92a7-7e20241cc360.png\" style=\"max-width: 100%;\"></p>', '<p>阿凡达是的撒</p>', '2', '2019-04-16 09:31:57', '2019-04-16 09:31:57', '2', '16', '0');
INSERT INTO `problem` VALUES ('1268056490808', '复杂链表的复制', '<p>输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）&nbsp;&nbsp;<br></p>', '<p>具体分为三步： &nbsp;</p><p><img src=\"http://localhost:9999/image/70f23b0d-4d81-420e-8e63-56e0b02d5660.png\" style=\"max-width:100%;\"><br></p>', '4', '2019-05-30 19:45:48', '2019-05-30 19:45:48', '4', '2', '1');
INSERT INTO `problem` VALUES ('4362667099974', '测试问题', '<p>&nbsp; &nbsp; 防守打法</p><p>&nbsp; &nbsp;士大夫 大<span style=\"font-weight: bold;\">啥事大</span></p><p><span style=\"font-weight: bold;\">发送到</span></p>', '<p>&nbsp; &nbsp;防守打法 发送到 士大夫<img src=\"http://localhost:9999/image/dcdef2b9-f099-46de-a4a0-73febd8f6846.png\" style=\"max-width: 100%;\"></p>', '6', '2019-05-29 19:03:18', '2019-05-29 19:05:49', '6', '7', '0');
INSERT INTO `problem` VALUES ('7030257789187', '母牛的故事', '<div align=\"left\"><br>Problem Description</div><div>有一头母牛，它每年年初生一头小母牛。每头小母牛从第四个年头开始，每年年初也生一头小母牛。请编程实现在第n年的时候，共有多少头母牛？</div><div>&nbsp;</div><p><br></p><div align=\"left\">Input</div><div>输入数据由多个测试实例组成，每个测试实例占一行，包括一个整数n(0&lt;n&lt;55)，n的含义如题目中描述。<br>n=0表示输入数据的结束，不做处理。</div><div>&nbsp;</div><p><br></p><div align=\"left\">Output</div><div>对于每个测试实例，输出在第n年的时候母牛的数量。<br>每个输出占一行。</div><p><br></p><div align=\"left\">Sample Input</div><div><pre><div>2\n4\n5\n0</div></pre></div><p><br></p>', '<p>这题很简单，请大家踊跃发言</p>', '5', '2019-05-30 20:02:19', '2019-05-30 20:02:19', '5', '12', '1');
INSERT INTO `problem` VALUES ('2061451704539232', '测试跳转', '<p>房贷首付</p>', '<p>阿凡达是的撒</p>', '2', '2019-04-16 10:09:41', '2019-04-16 10:09:41', '2', '4', '0');
INSERT INTO `problem` VALUES ('3340176882427904', '题解大改', '<p>你跑了大打算</p>', '<p>阿凡达是的111撒深刻的风景佛挡杀佛感到反感</p>', '2', '2019-05-12 19:51:15', '2019-05-16 16:13:06', '2', '43', '1');
INSERT INTO `problem` VALUES ('4144114261413801', '怎么dfs', '<p>有这么一个问题balabala</p>', '<p>极大审批u', '2', '2019-04-18 12:45:09', '2019-04-18 12:45:09', '2', '21', '0');
INSERT INTO `problem` VALUES ('5038093498331926', '不开心', '<p>腰疼<img src=\"http://192.168.1.113:9999/image/fae52a65-11e2-4d9c-8e4f-78d235745434.png\" style=\"max-width: 100%;\">好无聊</p><p>心好累爱的方式感到反感</p>', '<p>如何介解决腰疼发士大夫给对方</p>', '2', '2019-04-16 21:46:48', '2019-04-16 21:49:03', '2', '15', '0');
INSERT INTO `problem` VALUES ('5500657982074351', 'f发射点犯得上', '<p>撒旦发射点</p>', '<p>范德萨范德萨</p>', '2', '2019-04-16 10:06:25', '2019-04-16 10:06:25', '2', '15', '0');
INSERT INTO `problem` VALUES ('5647853167281072', '今天5.25', '<p>今天的问题还好<img src=\"http://localhost:9999/image/24df8958-7158-4085-a6fc-2ee8b6ccf867.jpg\" style=\"line-height: 1.4285em; max-width: 100%;\"></p>', '<p>我就不告诉你们这个题怎么写</p>', '2', '2019-05-25 18:50:40', '2019-05-25 18:50:40', '2', '3', '0');
INSERT INTO `problem` VALUES ('7243000840389260', '是不是傻', '<p>发士大夫和规范化</p>', ' <p>要让他机构和</p>', '2', '2019-04-16 10:12:54', '2019-04-16 21:37:50', '2', '37', '0');
INSERT INTO `problem` VALUES ('8277263065194430', '风格和规范', '<p>和风格和规范</p>', '', '1', '2019-04-16 10:47:01', '2019-04-16 10:47:01', '2', '17', '1');
INSERT INTO `problem` VALUES ('9385048718728468', 'j;kljfa', '<p><img src=\"http://192.168.1.113:9999/image/e078aeeb-48b2-43d0-aef8-c3c5b1eb7ef3.png\" style=\"max-width:100%;\">nihao进口国', '<p>华国锋空间划分迪斯科浪费返还给辛苦了</p>', '2', '2019-04-16 21:44:18', '2019-04-16 21:50:29', '2', '15', '0');
INSERT INTO `problem` VALUES ('9911228760115523', '护理肌肤护理的数据库', '<p>奴隶的身份</p>', '', '2', '2019-04-16 10:05:51', '2019-04-16 10:05:51', '2', '0', '0');

-- ----------------------------
-- Table structure for `replyproblem`
-- ----------------------------
DROP TABLE IF EXISTS `replyproblem`;
CREATE TABLE `replyproblem` (
  `problemansId` bigint(20) NOT NULL AUTO_INCREMENT,
  `problemId` bigint(20) DEFAULT NULL,
  `p_problemans` bigint(20) DEFAULT NULL,
  `ansUser` int(11) DEFAULT NULL,
  `ansBody` text,
  `createDate` datetime DEFAULT NULL,
  `agreeNum` int(11) DEFAULT '0',
  `isEffective` int(11) DEFAULT '1',
  PRIMARY KEY (`problemansId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of replyproblem
-- ----------------------------
INSERT INTO `replyproblem` VALUES ('2', '1', '0', '2', '522', '2019-04-16 14:03:30', '0', '1');
INSERT INTO `replyproblem` VALUES ('3', '1', '0', '2', '522', '2019-04-16 14:03:36', '0', '1');
INSERT INTO `replyproblem` VALUES ('4', '1', '0', '2', '522', '2019-04-16 16:29:51', '0', '1');
INSERT INTO `replyproblem` VALUES ('5', '1', '0', '2', '522', '2019-04-16 16:29:52', '0', '1');
INSERT INTO `replyproblem` VALUES ('6', '1', '0', '2', '21323', '2019-04-16 17:07:23', '0', '1');
INSERT INTO `replyproblem` VALUES ('7', '1', '0', '2', '阿三打撒', '2019-04-16 17:08:11', '0', '1');
INSERT INTO `replyproblem` VALUES ('8', '5038093498331926', '0', '2', '你好啊', '2019-04-16 21:47:07', '0', '1');
INSERT INTO `replyproblem` VALUES ('9', '4144114261413801', '0', '2', '我这么看', '2019-04-18 12:45:34', '0', '0');
INSERT INTO `replyproblem` VALUES ('10', '4144114261413801', '0', '2', '题解测试', '2019-05-14 11:38:27', '0', '1');
INSERT INTO `replyproblem` VALUES ('11', '1268056490808', '0', '4', '你们怎么看呢', '2019-05-30 19:46:55', '0', '1');
INSERT INTO `replyproblem` VALUES ('12', '7030257789187', '0', '5', '这题主要思路如下：得仔细阅读题意', '2019-05-31 00:13:23', '0', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` bigint(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `classNum` int(11) DEFAULT '0',
  `grade` int(11) DEFAULT '0',
  `mobile` varchar(20) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `studentId` bigint(20) DEFAULT NULL,
  `createDay` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `auth` int(11) DEFAULT NULL,
  `isEffective` int(11) DEFAULT NULL,
  `unionid` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '', 'gyt', '123456', '4', '2015', '17700000000', '张三', '2015000000', '2019-01-13 15:31:06', 'http://thirdwx.qlogo.cn/mmopen/vi_32/TVAicR3KQSMfNBX0j4uJibQfZhribgx3sAn7sztUItwDFhmZMdz1NuBW73qR4bjAOxNntxZWQOKm6k2nxo6icHW27A/132', '5', '1', null);
INSERT INTO `user` VALUES ('3', '', 'watermelon', '1234561', '3', '2015', '17700000001', '袁曦焜', '2015000003', '2019-01-17 21:40:19', 'http://localhost:9999/image/cd8b89fc-b3b7-4c69-bd06-d7a5c8f28289.jpg', '4', '1', null);
INSERT INTO `user` VALUES ('4', '', 'www', '123456', '4', '2019', '17700000002', '官怡婷', '2015000002', '2019-01-23 22:55:53', 'http://localhost:9999/image/photo/981d4cc6-7b18-4315-b08a-90dcbf4c3ad0.png', '2', '1', null);
INSERT INTO `user` VALUES ('5', 'olD4e0x14bo2CRMHAJiNgweDKaG4', 'amy', '123456', '4', '2015', '17700000003', '王五', '2015000001', '2019-04-16 17:02:37', 'http://localhost:9999/image/photo/f873cb13-f962-4c9e-a4fd-642ab090044d.png', '2', '1', null);
INSERT INTO `user` VALUES ('6', '', 'gytd', '123456', '0', '2002', '17716164149', '王麻子', '2015081159', '2019-05-28 19:19:17', 'http://localhost:9999/image/a6fc0d36-bde3-4fc1-8a4b-3af832d7a900.jpg', '0', '1', null);
INSERT INTO `user` VALUES ('7', 'olD4e0_cnxwigLLZSJiK2r7scYdc', 'hhhhh_12', '123456', '0', '0', '18064955179', '马华', '2015082053', '2019-05-31 08:08:55', 'http://localhost:9999/image/4492e40e-18bb-431f-8cc8-afb35e05f010.jpg', '2', '1', null);
