/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : pachira_party

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-06-03 14:09:54
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
  `id` bigint(20) NOT NULL,
  `party_type_id` varchar(30) NOT NULL COMMENT '团体类型，party_type.id',
  `external_id` varchar(60) DEFAULT NULL COMMENT '关联外部对象id',
  `status_id` varchar(20) NOT NULL COMMENT '团体名称',
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_by` varchar(30) NOT NULL,
  `updated_by` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party
-- ----------------------------
INSERT INTO `party` VALUES ('1', 'PERSON', '1234', '一号首长', 'abc', '2018-05-31 07:18:36', '2018-05-31 07:18:38', 'admin', 'admin');
INSERT INTO `party` VALUES ('452752572604747776', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 08:37:11', '2018-06-03 08:37:11', '大王', '大王');
INSERT INTO `party` VALUES ('452753002822897664', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 08:38:47', '2018-06-03 08:38:47', '大王', '大王');
INSERT INTO `party` VALUES ('452766806625488896', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 09:33:38', '2018-06-03 09:33:38', '大王', '大王');
INSERT INTO `party` VALUES ('452768801151258624', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 09:41:34', '2018-06-03 09:41:34', '大王', '大王');
INSERT INTO `party` VALUES ('452770043239862272', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 09:46:30', '2018-06-03 09:46:30', '大王', '大王');
INSERT INTO `party` VALUES ('452770707802165248', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 09:49:10', '2018-06-03 09:49:10', '大王', '大王');
INSERT INTO `party` VALUES ('452770956075601920', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 09:50:08', '2018-06-03 09:50:08', '大王', '大王');
INSERT INTO `party` VALUES ('452772340862816256', 'PERSON', null, 'PARTY_ENABLED', '大王102', '2018-06-03 09:55:38', '2018-06-03 09:55:38', '大王', '大王');
INSERT INTO `party` VALUES ('452831381324566528', 'PERSON', null, 'PARTY_ENABLED', '一號首長', '2018-06-03 13:50:19', '2018-06-03 13:50:19', '大王', '大王');
INSERT INTO `party` VALUES ('452834407363907584', 'PERSON', null, 'PARTY_ENABLED', '一號首長', '2018-06-03 14:02:19', '2018-06-03 14:02:19', '大王', '大王');
INSERT INTO `party` VALUES ('452835901148499968', 'PERSON', null, 'PARTY_ENABLED', '一號首長', '2018-06-03 14:08:15', '2018-06-03 14:08:15', '大王', '大王');

-- ----------------------------
-- Table structure for party_group
-- ----------------------------
DROP TABLE IF EXISTS `party_group`;
CREATE TABLE `party_group` (
  `id` bigint(20) NOT NULL COMMENT 'party.id',
  `group_name` varchar(100) NOT NULL COMMENT '组织名称',
  `office_site_name` varchar(100) DEFAULT NULL COMMENT '官网地址',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `logo_image_url` varchar(1000) DEFAULT NULL COMMENT 'logo图怕你地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party_group
-- ----------------------------

-- ----------------------------
-- Table structure for party_type
-- ----------------------------
DROP TABLE IF EXISTS `party_type`;
CREATE TABLE `party_type` (
  `id` varchar(30) NOT NULL COMMENT '团体类型',
  `parent_id` varchar(30) DEFAULT NULL COMMENT '上级类型ID',
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party_type
-- ----------------------------
INSERT INTO `party_type` VALUES ('AUTOMATED_AGENT', null, '自动化代理', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('CORPORATION', 'LEGAL_ORGANIZATION', '公司', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('FAMILY', 'INFORMAL_GROUP', '家庭', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('GOVERNMENT_AGENCY', 'LEGAL_ORGANIZATION', '政府机构', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('INFORMAL_GROUP', 'PARTY_GROUP', '非正式群体', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('LEGAL_ORGANIZATION', 'PARTY_GROUP', '法定组织', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('PARTY_GROUP', null, '组织', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('PERSON', null, '个人', '2018-05-31 07:14:32', '2018-05-31 07:14:32');
INSERT INTO `party_type` VALUES ('TEAM', 'INFORMAL_GROUP', '团队', '2018-05-31 07:14:32', '2018-05-31 07:14:32');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL COMMENT 'party表主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `gender` char(1) NOT NULL COMMENT '性别，M;男，F：女，N:未提供',
  `birth_date` datetime DEFAULT NULL COMMENT '生日',
  `height` double DEFAULT NULL COMMENT '身高',
  `weight` double DEFAULT NULL COMMENT '体重',
  `marital_status` char(1) NOT NULL COMMENT '婚姻状态,M已婚，U:未婚，N:未知',
  `card_id` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `passport_number` varchar(40) DEFAULT NULL COMMENT '护照号码',
  `passport_expire_date` datetime DEFAULT NULL COMMENT '护照过期时间',
  `social_security_number` varchar(20) DEFAULT NULL COMMENT '社保卡号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CARD_ID_IDX` (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '一号首长', 'M', '2018-05-29 09:49:55', null, null, 'N', '123456789', null, '2018-06-03 09:49:49', null);
INSERT INTO `person` VALUES ('452752572604747776', '大王102', 'M', '2018-06-03 08:37:03', '180', '89', 'M', '37018219630605326454', '12345566', '2018-06-03 08:37:03', '3702');
INSERT INTO `person` VALUES ('452753002822897664', '大王102', 'M', '2018-06-03 08:38:48', '180', '89', 'M', '37018219630605326424', '12345566', '2018-06-03 08:38:48', '3702');
INSERT INTO `person` VALUES ('452766806625488896', '大王102', 'M', '2018-06-03 09:33:39', '180', '89', 'M', 'cad9ade5-38a0-4c56', '12345566', '2018-06-03 09:33:39', '3702');
INSERT INTO `person` VALUES ('452768801151258624', '大王102', 'M', '2018-06-03 09:41:34', '180', '89', 'M', '6f883b35-65c3-4b03', '12345566', '2018-06-03 09:41:34', '3702');
INSERT INTO `person` VALUES ('452770043239862272', '大王102', 'M', '2018-06-03 09:46:30', '180', '89', 'M', '208af941-307f-4c60', '12345566', '2018-06-03 09:46:30', '3702');
INSERT INTO `person` VALUES ('452770707802165248', '大王102', 'M', '2018-06-03 09:49:06', '180', '89', 'M', 'a17351bf-94e7-431a', '12345566', '2018-06-03 09:49:06', '3702');
INSERT INTO `person` VALUES ('452770956075601920', '大王102', 'M', '2018-06-03 09:50:08', '180', '89', 'M', '68624400-3920-4a86', '12345566', '2018-06-03 09:50:08', '3702');
INSERT INTO `person` VALUES ('452772340862816256', '大王102', 'M', '2018-06-03 09:55:38', '180', '89', 'M', '3bf7cc3f-f3f7-483a', '12345566', '2018-06-03 09:55:38', '3702');
INSERT INTO `person` VALUES ('452831381324566528', '一號首長', 'N', null, null, null, 'N', null, null, null, null);
INSERT INTO `person` VALUES ('452834407363907584', '一號首長', 'N', null, null, null, 'N', null, null, null, null);
INSERT INTO `person` VALUES ('452835901148499968', '一號首長', 'N', null, null, null, 'N', null, null, null, null);

-- ----------------------------
-- Table structure for user_login
-- ----------------------------
DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `id` bigint(20) NOT NULL,
  `party_id` bigint(20) NOT NULL,
  `party_name` varchar(20) NOT NULL,
  `login_id` varchar(60) NOT NULL COMMENT '登录帐号',
  `password` varchar(100) NOT NULL COMMENT '密码，使用BCrypt算法加密',
  `enabled` char(1) NOT NULL COMMENT '启用Y，禁用N',
  `last_local` varchar(20) DEFAULT NULL COMMENT '最近使用的语言',
  `last_time_zone` varchar(20) DEFAULT NULL COMMENT '最近使用的时区',
  `disabled_date` datetime DEFAULT NULL COMMENT '帐号禁用时间',
  `disabled_by` varchar(20) DEFAULT NULL COMMENT '禁用人',
  `user_ldap_dn` varchar(100) DEFAULT NULL COMMENT 'ldap名称',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最近登录IP',
  `last_login_date` datetime DEFAULT NULL COMMENT '最近登录时间',
  `created_at` datetime NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', '1', '一号首长', 'admin', '$2a$10$l8H3fWpJDRwsGy8f.HMvcOVXwxAjkz3GQi6pS0Cxzd03K38zMbIMq', 'Y', null, null, null, null, null, null, null, '2018-05-20 22:12:15', 'admin', '2018-05-20 22:12:23', 'admin');
