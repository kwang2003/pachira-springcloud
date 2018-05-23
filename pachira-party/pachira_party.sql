/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : pachira_party

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-05-20 15:49:17
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
INSERT INTO `client` VALUES ('1', 'app1', 'https://mmbiz.qpic.cn/mmbiz_jpg/Pn4Sm0RsAuh3xpZAsbibM9XMnOm5icvzoakjgtCwibm2JUXMmeNtYmQ0pBXqLt4UW0LJ6wL1ic0zwfB8r4rmOIAJ4g/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1', null, 'aaaaaaaa', null, null, null, 'password,code', '1', null, '3000000', '333333333333', '2018-05-20 11:17:34', '2018-05-20 11:17:37', 'admin', 'admin');
