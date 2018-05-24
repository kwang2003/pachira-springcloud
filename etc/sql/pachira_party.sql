/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : pachira_party

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-05-24 07:31:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(30) NOT NULL COMMENT '客户端唯一编码',
  `image_url` varchar(255) NOT NULL COMMENT '应用图像url地址',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '可以访问的资源ID',
  `client_secret` varchar(100) DEFAULT NULL,
  `scope` varchar(60) DEFAULT NULL,
  `web_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(100) NOT NULL,
  `auto_approve` varchar(3) NOT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `access_token_validity` bigint(20) NOT NULL,
  `refresh_token_validity` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_by` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', 'app1', 'https://mmbiz.qpic.cn/mmbiz_jpg/Pn4Sm0RsAuh3xpZAsbibM9XMnOm5icvzoakjgtCwibm2JUXMmeNtYmQ0pBXqLt4UW0LJ6wL1ic0zwfB8r4rmOIAJ4g/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1', null, '$2a$10$6UnisNCdN.7/P4YNL1SZl.XCSBzgafxK0oBm/yWwc436m/EdlH00G', 'app', null, null, 'password,authorization_code', '1', null, '3000000', '333333333333', '2018-05-20 11:17:34', '2018-05-20 11:17:37', 'admin', 'admin');

-- ----------------------------
-- Table structure for party
-- ----------------------------
DROP TABLE IF EXISTS `party`;
CREATE TABLE `party` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(10) NOT NULL COMMENT '团体类型，1：人员，2：组织',
  `name` varchar(60) NOT NULL COMMENT '团体名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party
-- ----------------------------
INSERT INTO `party` VALUES ('1', '1', '一号首长');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL COMMENT 'party表主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sex` varchar(10) NOT NULL COMMENT '性别，MALE;男，FEMALE：女，UNKNOWN:未提供',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `crated_by` varchar(20) NOT NULL,
  `updated_by` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '一号首长', 'MALE', '2018-05-20 22:11:01', '2018-05-20 22:11:03', 'admin', 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `party_id` bigint(20) NOT NULL,
  `party_type` int(10) NOT NULL COMMENT '冗余party表的type字段',
  `party_name` varchar(20) NOT NULL,
  `login_id` varchar(60) NOT NULL COMMENT '登录帐号',
  `password` varchar(100) NOT NULL COMMENT '密码，使用BCrypt算法加密',
  `created_at` datetime NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', '一号首长', 'admin', '$2a$10$l8H3fWpJDRwsGy8f.HMvcOVXwxAjkz3GQi6pS0Cxzd03K38zMbIMq', '2018-05-20 22:12:15', 'admin', '2018-05-20 22:12:23', 'admin');
