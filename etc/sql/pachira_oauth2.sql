/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : pachira_oauth2

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-05-24 07:31:36
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
