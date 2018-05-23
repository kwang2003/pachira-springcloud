/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : scheduler

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-01-21 19:14:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `description` varchar(128) DEFAULT NULL,
  `cron` varchar(64) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT '任务类型，DBUUO,CONSUL,HTTP,EUREKA',
  `is_deleted` tinyint(1) NOT NULL COMMENT '是否删除，0：否，1：是',
  `registry` varchar(64) DEFAULT NULL COMMENT '注册中心地址',
  `interface_name` varchar(256) NOT NULL COMMENT '接口名称，http接口时为完整url',
  `method` varchar(100) NOT NULL COMMENT '要执行的方法名',
  `timeout` int(20) NOT NULL COMMENT '执行超时时间，单位毫秒',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', '测试任务', '测试一下', '0/1 * * * * ?', 'HTTP', '0', 'DUBBO_ZOOKEEPER_LOCALHOST', 'http://www.haier.com', 'GET', '5000', '2018-01-01 11:17:15', '2018-01-01 11:17:18');
INSERT INTO `job` VALUES ('2', '測試以下', '5秒钟执行一次', '0/5 * * * * ?', 'HTTP', '0', null, 'http://www.baidu.com', 'GET', '5000', '2018-01-13 09:33:48', '2018-01-13 09:33:48');
INSERT INTO `job` VALUES ('3', '測試以下', '5秒钟执行一次', '0/5 * * * * ?', 'HTTP', '1', null, 'http://www.baidu.com', 'GET', '5000', '2018-01-13 09:34:11', '2018-01-13 09:34:58');
INSERT INTO `job` VALUES ('4', '10秒钟执行一次', '123456', '0/10 * * * * ?', 'HTTP', '0', null, 'https://gitee.com/', 'GET', '5000', '2018-01-21 15:44:10', '2018-01-21 15:44:10');
INSERT INTO `job` VALUES ('5', '10秒钟执行一次', '4444', '0/10 * * * * ?', 'HTTP', '0', null, 'https://gitee.com/', 'GET', '5000', '2018-01-21 15:48:42', '2018-01-21 15:48:42');
INSERT INTO `job` VALUES ('6', '10秒钟执行一次', '123456789', '0/10 * * * * ?', 'HTTP', '0', 'DUBBO_ZOOKEEPER_LOCALHOST', 'https://gitee.com/', 'GET', '5000', '2018-01-21 15:50:01', '2018-01-21 15:50:01');
INSERT INTO `job` VALUES ('7', '10分钟执行一次发邮件通知', 'cbs使用', '* 0/10 * * * ?', 'HTTP', '0', null, '0/10', 'GET', '5000', '2018-01-21 15:53:59', '2018-01-21 15:53:59');
INSERT INTO `job` VALUES ('8', '10秒钟执行一次', '456456', '* 0/10 * * * ?', 'HTTP', '0', 'DUBBO_ZOOKEEPER_192.168.1.104', 'https://gitee.com/', 'GET', '5000', '2018-01-21 16:17:45', '2018-01-21 16:17:45');
INSERT INTO `job` VALUES ('9', '10秒钟执行一次', null, '* 0/10 * * * ?', 'HTTP', '0', 'DUBBO_ZOOKEEPER_192.168.1.104', 'https://gitee.com/', 'GET', '5000', '2018-01-21 16:25:35', '2018-01-21 16:25:35');
INSERT INTO `job` VALUES ('10', 'admin@q.com', null, '0/10 * * * * ?', 'HTTP', '0', null, 'https://gitee.com/', 'GET', '5000', '2018-01-21 16:25:50', '2018-01-21 16:25:50');
INSERT INTO `job` VALUES ('11', '5秒执行一次dubbo服务hello', '789', '0/10 * * * * ?', 'DUBBO', '0', 'DUBBO_ZOOKEEPER_LOCALHOST', 'com.pachiraframework.scheduler.service.DemoDubboService', 'doSomething', '50000', '2018-01-21 18:05:26', '2018-01-21 18:05:26');

-- ----------------------------
-- Table structure for job_history
-- ----------------------------
DROP TABLE IF EXISTS `job_history`;
CREATE TABLE `job_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) NOT NULL COMMENT '任务主键',
  `job_name` varchar(64) NOT NULL COMMENT '任务名称',
  `cron` varchar(30) NOT NULL COMMENT 'cron',
  `started_at` datetime NOT NULL COMMENT '开始时间',
  `ended_at` datetime DEFAULT NULL COMMENT '结束时间',
  `status` varchar(10) NOT NULL COMMENT '执行状态，SUCCESS：成功，FAIL：失败，ING：进行中',
  `instance` varchar(100) NOT NULL,
  `message` varchar(255) DEFAULT NULL COMMENT '消息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_history
-- ----------------------------

-- ----------------------------
-- Table structure for service_registry
-- ----------------------------
DROP TABLE IF EXISTS `service_registry`;
CREATE TABLE `service_registry` (
  `id` varchar(50) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '注册中心名称',
  `address` varchar(256) NOT NULL COMMENT '注册中心地址',
  `type` varchar(20) NOT NULL COMMENT '注册中心类型，DUBBO_ZOOKEEPER',
  `properties` varchar(255) DEFAULT '',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_registry
-- ----------------------------
INSERT INTO `service_registry` VALUES ('DUBBO_ZOOKEEPER_192.168.1.104', 'zookeeper注册中心192.168.1.104', 'zookeeper://192.168.1.104:2181', 'DUBBO_ZOOKEEPER', '', '2018-01-01 11:16:36', '2018-01-01 11:16:36');
INSERT INTO `service_registry` VALUES ('DUBBO_ZOOKEEPER_LOCALHOST', '本地环境zookeeper注册中心', 'zookeeper://localhost:2181', 'DUBBO_ZOOKEEPER', '', '2018-01-01 11:16:36', '2018-01-01 11:16:36');
