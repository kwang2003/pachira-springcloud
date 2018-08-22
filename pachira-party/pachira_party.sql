/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : pachira_party

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-08-23 07:05:10
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
INSERT INTO `client` VALUES ('1', 'app1', 'https://mmbiz.qpic.cn/mmbiz_jpg/Pn4Sm0RsAuh3xpZAsbibM9XMnOm5icvzoakjgtCwibm2JUXMmeNtYmQ0pBXqLt4UW0LJ6wL1ic0zwfB8r4rmOIAJ4g/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1', null, '$2a$10$j97n.xa6nrWof3i3SEG58Oc97jo9bXXmGENfwFD8PFZ2DKtdvlli6', 'app', null, null, 'password,authorization_code', '1', null, '3000000', '333333333333', '2018-05-20 11:17:34', '2018-05-20 11:17:37', 'admin', 'admin');

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
  PRIMARY KEY (`id`),
  KEY `fk_party_party_type` (`party_type_id`),
  CONSTRAINT `fk_party_party_type` FOREIGN KEY (`party_type_id`) REFERENCES `party_type` (`id`)
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
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_party_id` FOREIGN KEY (`id`) REFERENCES `party` (`id`)
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
  PRIMARY KEY (`id`),
  KEY `fk_parent_id` (`parent_id`),
  CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `party_type` (`id`)
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
  UNIQUE KEY `CARD_ID_IDX` (`card_id`),
  CONSTRAINT `fk_id` FOREIGN KEY (`id`) REFERENCES `party` (`id`)
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
-- Table structure for security_group
-- ----------------------------
DROP TABLE IF EXISTS `security_group`;
CREATE TABLE `security_group` (
  `id` varchar(60) COLLATE latin1_general_cs NOT NULL,
  `description` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

-- ----------------------------
-- Records of security_group
-- ----------------------------
INSERT INTO `security_group` VALUES ('ACCTG_FUNCTNL_ADMIN', 'Accounting permission for all accounting functions.');
INSERT INTO `security_group` VALUES ('ASSETMAINTADMIN', 'Asset Maintenance Admin group, has all maintenance permissions.');
INSERT INTO `security_group` VALUES ('ASSETMAINTTECH', 'Asset Maintenance Technician group, has view and update.');
INSERT INTO `security_group` VALUES ('BIZADMIN', 'Full Business Applications permission group, has all business app admin permissions, not technical permissions.');
INSERT INTO `security_group` VALUES ('CATALOGADMIN', 'Catalog Admin group, has all catalog permissions.');
INSERT INTO `security_group` VALUES ('CATALOGADMIN_LTD', 'Limited Catalog Admin group, has limited catalog permissions.');
INSERT INTO `security_group` VALUES ('COMPDOCADMIN', 'CompDoc admin group, all permissions.');
INSERT INTO `security_group` VALUES ('CONTENT_USER', 'Content user group; all limited content permissions.');
INSERT INTO `security_group` VALUES ('ECOMMERCE_CUSTOMER', 'Customer user of ECOMMERCE Limited access to own account');
INSERT INTO `security_group` VALUES ('FLEXADMIN', 'Flexible Admin group, has all granular permissions.');
INSERT INTO `security_group` VALUES ('FULLADMIN', 'Full Admin group, has all general permissions.');
INSERT INTO `security_group` VALUES ('HUMANRES_ADMIN', 'This security group is for those who have full access to the human resource component.');
INSERT INTO `security_group` VALUES ('HUMANRES_APPROVER', 'This security group is for those who will approve the internal job posting application.');
INSERT INTO `security_group` VALUES ('HUMANRES_EMPLOYEE', 'This security group is for all the employees who want to apply for internal Job Posting.');
INSERT INTO `security_group` VALUES ('IMAGEADMIN', 'Image Management Admin.');
INSERT INTO `security_group` VALUES ('IMAGEAPPROVE', 'Image Management Approve');
INSERT INTO `security_group` VALUES ('IMAGEUPLOAD', 'Image Management Upload.');
INSERT INTO `security_group` VALUES ('MYPORTAL_CUSTOMER', 'Customer user of MYPORTAL, Limited access to own custRequests, and project');
INSERT INTO `security_group` VALUES ('MYPORTAL_EMPL-NOEML', 'Employee user of MYPORTAL, Limited access to the projectmgr and comms, custReq, no email');
INSERT INTO `security_group` VALUES ('MYPORTAL_EMPLOYEE', 'Employee user of myportal, Limited access to the projectmgr, comms,custRequests');
INSERT INTO `security_group` VALUES ('ORDERADMIN', 'Order Admin group, has all order permissions.');
INSERT INTO `security_group` VALUES ('ORDERADMIN_LTD', 'Limited Order Admin group, has all limited order permissions.');
INSERT INTO `security_group` VALUES ('ORDERENTRY', 'Order Entry Admin group; permissions for creating orders.');
INSERT INTO `security_group` VALUES ('ORDERENTRY_ALL', 'Order entry permission for all stores.  No special role is needed.');
INSERT INTO `security_group` VALUES ('ORDERPROC', 'Admin group for restricted order processing.');
INSERT INTO `security_group` VALUES ('ORDERPURCH', 'Order entry with purchasing permissions');
INSERT INTO `security_group` VALUES ('ORDERSUPPLIER_LTD', 'Limited Order Admin group for Supplier Agents.');
INSERT INTO `security_group` VALUES ('PARTYADMIN', 'Party Admin group, has all party permissions.');
INSERT INTO `security_group` VALUES ('POSCLERK', 'POS Clerk');
INSERT INTO `security_group` VALUES ('PROJECTADMIN', 'Project Admin group, has update access to own projects.');
INSERT INTO `security_group` VALUES ('PROJECTUSER', 'Project User group, has read, task create/assign and timesheet create/update access to own projects.');
INSERT INTO `security_group` VALUES ('SCRUM_ADMIN', 'Scrum Administrator group, has all scrum permissions.');
INSERT INTO `security_group` VALUES ('SCRUM_BILLING', 'Scrumbilling');
INSERT INTO `security_group` VALUES ('SCRUM_MASTER', 'Scrum Master');
INSERT INTO `security_group` VALUES ('SCRUM_PRODUCT_OWNER', 'Product Owner');
INSERT INTO `security_group` VALUES ('SCRUM_STAKEHOLDER', 'Stakeholder');
INSERT INTO `security_group` VALUES ('SCRUM_TEAM', 'Scrum Team');
INSERT INTO `security_group` VALUES ('SCRUM_TEST_ADMIN', 'Scrum Test Administrator');
INSERT INTO `security_group` VALUES ('SECURITYADMIN', 'Security Admin group, has all permissions to modify security settings in party manager.');
INSERT INTO `security_group` VALUES ('SUPER', 'Super admin group, has all *_ADMIN permission loaded as seed data');
INSERT INTO `security_group` VALUES ('VIEWADMIN', 'Demo Admin group, has all view permissions.');
INSERT INTO `security_group` VALUES ('WORKEFFORTADMIN', 'WorkEffort Admin group, has all workeffort permissions.');
INSERT INTO `security_group` VALUES ('WORKEFFORT_USER', 'WorkEffort user group; all limited workeffort permissions.');

-- ----------------------------
-- Table structure for security_permission
-- ----------------------------
DROP TABLE IF EXISTS `security_permission`;
CREATE TABLE `security_permission` (
  `id` varchar(60) COLLATE latin1_general_cs NOT NULL,
  `description` varchar(255) COLLATE latin1_general_cs DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

-- ----------------------------
-- Records of security_permission
-- ----------------------------
INSERT INTO `security_permission` VALUES ('ACCOUNTING_ADMIN', 'ALL operations in the Accounting Manager.');
INSERT INTO `security_permission` VALUES ('ACCOUNTING_COMM_VIEW', 'View commission rates');
INSERT INTO `security_permission` VALUES ('ACCOUNTING_CREATE', 'Create operations in the Accounting Manager.');
INSERT INTO `security_permission` VALUES ('ACCOUNTING_DELETE', 'Delete operations in the Accounting Manager.');
INSERT INTO `security_permission` VALUES ('ACCOUNTING_PRINT_CHECKS', 'Print checks.');
INSERT INTO `security_permission` VALUES ('ACCOUNTING_UPDATE', 'Update operations in the Accounting Manager.');
INSERT INTO `security_permission` VALUES ('ACCOUNTING_VIEW', 'View operations in the Accounting Manager.');
INSERT INTO `security_permission` VALUES ('ACCTG_ATX_ADMIN', 'ALL operations involving general ledger accounting transactions and entries.');
INSERT INTO `security_permission` VALUES ('ACCTG_ATX_CREATE', 'Create general ledger accounting transaction and entries.');
INSERT INTO `security_permission` VALUES ('ACCTG_ATX_DELETE', 'Delete general ledger accounting transaction and entries.');
INSERT INTO `security_permission` VALUES ('ACCTG_ATX_UPDATE', 'Update general ledger accounting transaction and entries.');
INSERT INTO `security_permission` VALUES ('ACCTG_ATX_VIEW', 'View general ledger accounting transaction and entries.');
INSERT INTO `security_permission` VALUES ('ACCTG_FX_UPDATE', 'Set foreign exchange rates');
INSERT INTO `security_permission` VALUES ('ACCTG_PREF_ADMIN', 'ALL organization accounting preferences operations');
INSERT INTO `security_permission` VALUES ('ACCTG_PREF_CREATE', 'Set organization accounting preferences');
INSERT INTO `security_permission` VALUES ('ACCTG_PREF_DELETE', 'Delete organization accounting preferences');
INSERT INTO `security_permission` VALUES ('ACCTG_PREF_UPDATE', 'Update organization accounting preferences');
INSERT INTO `security_permission` VALUES ('ACCTG_PREF_VIEW', 'View organization accounting preferences');
INSERT INTO `security_permission` VALUES ('ARTIFACT_INFO_VIEW', 'View the Artifact Info pages.');
INSERT INTO `security_permission` VALUES ('ASSETMAINT_ADMIN', 'ALL Asset Maintenance operations.');
INSERT INTO `security_permission` VALUES ('ASSETMAINT_CREATE', 'Asset Maintenance Create operations.');
INSERT INTO `security_permission` VALUES ('ASSETMAINT_DELETE', 'Asset Maintenance Delete operations.');
INSERT INTO `security_permission` VALUES ('ASSETMAINT_UPDATE', 'Asset Maintenance Update operations.');
INSERT INTO `security_permission` VALUES ('ASSETMAINT_VIEW', 'Asset Maintenance View permission.');
INSERT INTO `security_permission` VALUES ('access', 'Base ACCESS permission');
INSERT INTO `security_permission` VALUES ('BI_ADMIN', 'ALL Business Intelligence operations.');
INSERT INTO `security_permission` VALUES ('BI_VIEW', 'Business Intelligence View permission.');
INSERT INTO `security_permission` VALUES ('CATALOG_ADMIN', 'ALL operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_CREATE', 'Create operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_DELETE', 'Delete operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_PRICE_MAINT', 'To be able to maintain product prices, promotions, and price rules.');
INSERT INTO `security_permission` VALUES ('CATALOG_PURCHASE_ALLOW', 'Allow create/update of \'Purchase Allow\' in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_ROLE_CREATE', 'Limited Create operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_ROLE_DELETE', 'Limited Delete operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_ROLE_UPDATE', 'Limited Update operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_UPDATE', 'Update operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_VIEW', 'View operations in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('CATALOG_VIEW_ALLOW', 'Allow create/update of \'View Allow\' in the Catalog Manager.');
INSERT INTO `security_permission` VALUES ('COMMONEXT_PUBLMSG', 'Create public system information messages.');
INSERT INTO `security_permission` VALUES ('COMMON_ADMIN', 'Admin operations in the Common Component.');
INSERT INTO `security_permission` VALUES ('COMMON_CREATE', 'Create operations in the Common Component.');
INSERT INTO `security_permission` VALUES ('COMMON_DELETE', 'Delete operations in the Common Component.');
INSERT INTO `security_permission` VALUES ('COMMON_UPDATE', 'Update operations in the Common Component.');
INSERT INTO `security_permission` VALUES ('COMMON_VIEW', 'View operations in the Common Component.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_ADMIN', 'ALL operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_CREATE', 'Create operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_DELETE', 'Delete operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_ROLE_CREATE', 'Limited Create operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_ROLE_DELETE', 'Limited Delete operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_ROLE_UPDATE', 'Limited Update operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_ROLE_VIEW', 'Limited View operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_UPDATE', 'Update operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('CONTENTMGR_VIEW', 'View operations in the Content Manager.');
INSERT INTO `security_permission` VALUES ('create', 'Base CREATE permission');
INSERT INTO `security_permission` VALUES ('DATAFILE_MAINT', 'Use the Data File Maintenance pages.');
INSERT INTO `security_permission` VALUES ('delete', 'Base DELETE permission');
INSERT INTO `security_permission` VALUES ('EBAY_ADMIN', 'All operations in the eBay application.');
INSERT INTO `security_permission` VALUES ('EBAY_VIEW', 'View operations in the eBay application.');
INSERT INTO `security_permission` VALUES ('EMPLOYEE_VIEW', 'Employee can check the status of his requests and trainings assigned to him with this permission.');
INSERT INTO `security_permission` VALUES ('ENTITY_DATA_ADMIN', 'ALL with the Entity Data Maintenance pages.');
INSERT INTO `security_permission` VALUES ('ENTITY_DATA_CREATE', 'Create with the Entity Data Maintenance pages.');
INSERT INTO `security_permission` VALUES ('ENTITY_DATA_DELETE', 'Delete with the Entity Data Maintenance pages.');
INSERT INTO `security_permission` VALUES ('ENTITY_DATA_UPDATE', 'Update with the Entity Data Maintenance pages.');
INSERT INTO `security_permission` VALUES ('ENTITY_DATA_VIEW', 'View with the Entity Data Maintenance pages.');
INSERT INTO `security_permission` VALUES ('ENTITY_MAINT', 'Use the Entity Maintenance pages.');
INSERT INTO `security_permission` VALUES ('ENTITY_SYNC_ADMIN', 'Use the Entity Sync Admin pages.');
INSERT INTO `security_permission` VALUES ('ENUM_STATUS_MAINT', 'Use the Enum and Status Maintenance pages.');
INSERT INTO `security_permission` VALUES ('EXAMPLE_ADMIN', 'ALL operations in the Example Management Screens.');
INSERT INTO `security_permission` VALUES ('EXAMPLE_CREATE', 'Create operations in the Example Management Screens.');
INSERT INTO `security_permission` VALUES ('EXAMPLE_DELETE', 'Delete operations in the Example Management Screens.');
INSERT INTO `security_permission` VALUES ('EXAMPLE_UPDATE', 'Update operations in the Example Management Screens.');
INSERT INTO `security_permission` VALUES ('EXAMPLE_VIEW', 'View operations in the Example Management Screens.');
INSERT INTO `security_permission` VALUES ('FACILITY_ADMIN', 'ALL operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('FACILITY_CREATE', 'Create operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('FACILITY_DELETE', 'Delete operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('FACILITY_ROLE_UPDATE', 'Limited update operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('FACILITY_ROLE_VIEW', 'Limited view operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('FACILITY_UPDATE', 'Update operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('FACILITY_VIEW', 'View operations in the Facility Manager.');
INSERT INTO `security_permission` VALUES ('HHFACILITY_ADMIN', 'ALL hh facility operations.');
INSERT INTO `security_permission` VALUES ('HHFACILITY_VIEW', 'hh facility View permission.');
INSERT INTO `security_permission` VALUES ('HUMANRES_ADMIN', 'All operations in the HumanRes Manager.');
INSERT INTO `security_permission` VALUES ('HUMANRES_APPROVE', 'Approver can update the IJP approval status with this permission.');
INSERT INTO `security_permission` VALUES ('HUMANRES_CREATE', 'Create operations in the HumanRes Manager.');
INSERT INTO `security_permission` VALUES ('HUMANRES_DELETE', 'Delete operations in the HumanRes Manager.');
INSERT INTO `security_permission` VALUES ('HUMANRES_UPDATE', 'Update operations in the HumanRes Manager.');
INSERT INTO `security_permission` VALUES ('HUMANRES_VIEW', 'View operations in the HumanRes Manager.');
INSERT INTO `security_permission` VALUES ('IMAGE_MANAGEMENT_ADMIN', 'All operations in the Image Management.');
INSERT INTO `security_permission` VALUES ('IMAGE_MANAGEMENT_APPROVE', 'Approve photos in the Image Management.');
INSERT INTO `security_permission` VALUES ('IMAGE_MANAGEMENT_UPLOAD', 'Upload photos in the Image Management.');
INSERT INTO `security_permission` VALUES ('LABEL_MANAGER_VIEW', 'View the Labels Info pages.');
INSERT INTO `security_permission` VALUES ('MANUAL_PAYMENT', 'Manual Payment Transaction.');
INSERT INTO `security_permission` VALUES ('MANUFACTURING_ADMIN', 'ALL operations in the Manufacturing Manager.');
INSERT INTO `security_permission` VALUES ('MANUFACTURING_CREATE', 'Create operations in the Manufacturing Manager.');
INSERT INTO `security_permission` VALUES ('MANUFACTURING_DELETE', 'Delete operations in the Manufacturing Manager.');
INSERT INTO `security_permission` VALUES ('MANUFACTURING_UPDATE', 'Update operations in the Manufacturing Manager.');
INSERT INTO `security_permission` VALUES ('MANUFACTURING_VIEW', 'View operations in the Manufacturing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_ADMIN', 'ALL operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_CREATE', 'Create operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_DELETE', 'Delete operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_ROLE_UPDATE', 'Limited update operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_ROLE_VIEW', 'Limited view operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_UPDATE', 'Update operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MARKETING_VIEW', 'View operations in the Marketing Manager.');
INSERT INTO `security_permission` VALUES ('MYPORTALBASE_ADMIN', 'ALL MyPortal base operations');
INSERT INTO `security_permission` VALUES ('MYPORTALBASE_VIEW', 'MyPortal Base View permission.');
INSERT INTO `security_permission` VALUES ('MYPORTAL_CUSTOMER', 'Use the customer screens in MYPORTAL.');
INSERT INTO `security_permission` VALUES ('MYPORTAL_EMPL-NOEML', 'Usage of the employee screens in MYPORTAL: projectmanager and internal notes.');
INSERT INTO `security_permission` VALUES ('MYPORTAL_EMPLOYEE', 'Use the employee screens in myportal.');
INSERT INTO `security_permission` VALUES ('OFBTOOLS_VIEW', 'Permission to access the Stock OFBiz Manager Applications.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_4C_CREATE', 'Create sales forecasts in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_4C_UPDATE', 'Update sales forecasts in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_ADMIN', 'ALL operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_CREATE', 'Create operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_CRQ_CREATE', 'Create Customer Requests in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_DELETE', 'Delete operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_NOTE', 'Create notes in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_PURCHASE_CREATE', 'Create purchase orders in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_PURCHASE_ENTRY', 'Purchase Order Entry in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_PURCHASE_VIEW', 'View purchase orders in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_QUOTE_PRICE', 'Quote price manager in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_RETURN', 'Process returns in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_ROLE_CREATE', 'Limited Create operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_ROLE_DELETE', 'Limited Delete operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_ROLE_UPDATE', 'Limited Update operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_ROLE_VIEW', 'Limited view operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_SALES_CREATE', 'Create sales orders for all stores in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_SALES_ENTRY', 'Sales Order Entry in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_SALES_PRICEMOD', 'Price change permission for sales orders.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_SALES_UPDATE', 'Update sales orders for all stores in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_SEND_CONFIRMATION', 'Send order confirmation notification.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_UPDATE', 'Update operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('ORDERMGR_VIEW', 'View operations in the Order Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_ADMIN', 'ALL operations in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME-EMAIL_CREATE', 'Can create Email communication events for logged-in user.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME-EMAIL_DELETE', 'Can delete Email communication events for logged-in user.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME-EMAIL_UPDATE', 'Can update Email communication events for logged-in user.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME-NOTE_CREATE', 'Can create Internal note communication event for logged-in user.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME_CREATE', 'Create communication event, any from/to party.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME_DELETE', 'Delete communication event, any from/to party.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CME_UPDATE', 'Update communication event, any from/to party.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_CREATE', 'Create operations in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_DELETE', 'Delete operations in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_GRP_UPDATE', 'Update PartyGroup or Person detail information.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_NOTE', 'Create notes in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_PCM_CREATE', 'Create party contact mechs in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_PCM_DELETE', 'Delete party contact mechs in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_PCM_UPDATE', 'Update party contact mechs in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_QAL_CREATE', 'Create party quals in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_QAL_DELETE', 'Delete party quals in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_QAL_UPDATE', 'Update party quals in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_REL_CREATE', 'Create party relationships in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_REL_DELETE', 'Delete party relationships in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_REL_UPDATE', 'Update party relationships in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_ROLE_CREATE', 'Create party roles in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_ROLE_DELETE', 'Delete party roles in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_SRC_CREATE', 'Create party to data source relations.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_STS_UPDATE', 'Update party status in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_UPDATE', 'Update operations in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PARTYMGR_VIEW', 'View operations in the Party Manager.');
INSERT INTO `security_permission` VALUES ('PAYPROC_ADMIN', 'ALL operations in the Payment Processors Setup.');
INSERT INTO `security_permission` VALUES ('PAYPROC_CREATE', 'Create operations in the Payment Processors Setup.');
INSERT INTO `security_permission` VALUES ('PAYPROC_DELETE', 'Delete operations in the Payment Processors Setup.');
INSERT INTO `security_permission` VALUES ('PAYPROC_VIEW', 'View operations in the Payment Processors Setup.');
INSERT INTO `security_permission` VALUES ('PAY_INFO_ADMIN', 'ALL Payment Information Operations.');
INSERT INTO `security_permission` VALUES ('PAY_INFO_CREATE', 'Create Payment Information.');
INSERT INTO `security_permission` VALUES ('PAY_INFO_DELETE', 'Delete Payment Information.');
INSERT INTO `security_permission` VALUES ('PAY_INFO_UPDATE', 'Update Payment Information.');
INSERT INTO `security_permission` VALUES ('PAY_INFO_VIEW', 'View Payment Information.');
INSERT INTO `security_permission` VALUES ('PERIOD_MAINT', 'Use the Period Maintenance pages.');
INSERT INTO `security_permission` VALUES ('PORTALPAGE_ADMIN', 'Admin operations on Portal Pages.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ADMIN', 'ALL operations in the Project Manager.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ROLE_ADMIN', 'All admin operations in the Project Manager for a project/phase/task the user is member of.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ROLE_TASK_CREATE', 'Be able to create a task (should be member of project)');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ROLE_TIMESHEET_CREATE', 'Be able to create a weekly timesheet for the loginid.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ROLE_TIMESHEET_UPDATE', 'Be able to update(report) on an existing own timesheet');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ROLE_UPDATE', 'Update operations in the Project Manager for a project/phase/task the user is member of.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_ROLE_VIEW', 'All view operations in the Project Manager for a project/phase/task the user is member of.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_TIMESHEET_CREATE', 'Be able to create any weekly timesheet.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_TIMESHEET_UPDATE', 'Be able to update any weekly timesheet.');
INSERT INTO `security_permission` VALUES ('PROJECTMGR_VIEW', 'ALL View operations in the Project Manager(but can be limited by ROLE_VIEW)');
INSERT INTO `security_permission` VALUES ('read', 'Base READ permission');
INSERT INTO `security_permission` VALUES ('SCRUM_ADMIN', 'ALL operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SCRUM_BILLING_ADMIN', 'Admin operations for Billing');
INSERT INTO `security_permission` VALUES ('SCRUM_BILLING_CREATE', 'Create operations for Billing');
INSERT INTO `security_permission` VALUES ('SCRUM_BILLING_DELETE', 'Delete operations for Billing');
INSERT INTO `security_permission` VALUES ('SCRUM_BILLING_UPDATE', 'Update operations for Billing');
INSERT INTO `security_permission` VALUES ('SCRUM_BILLING_VIEW', 'View operations for Billing');
INSERT INTO `security_permission` VALUES ('SCRUM_CREATE', 'Create operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SCRUM_DAILY_CREATE', 'Create Daily Scrum Meeting Minute');
INSERT INTO `security_permission` VALUES ('SCRUM_DAILY_DELETE', 'Delete Daily Scrum Meeting Minute');
INSERT INTO `security_permission` VALUES ('SCRUM_DAILY_UPDATE', 'Update Daily Scrum Meeting Minute');
INSERT INTO `security_permission` VALUES ('SCRUM_DAILY_VIEW', 'View Daily Scrum Meeting Minute');
INSERT INTO `security_permission` VALUES ('SCRUM_DELETE', 'Delete operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SCRUM_MEMBER_CREATE', 'Create member operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_MEMBER_DELETE', 'Delete member operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_MEMBER_ROLE_UPDATE', 'Update member operations for Scrum the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_MEMBER_UPDATE', 'Update member operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_MEMBER_VIEW', 'View member operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_ADMIN', 'Admin operations for Product');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_BACKLOG_ADMIN', 'Admin operations for Product Backlog');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_BACKLOG_CREATE', 'Create operations for Product Backlog');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_BACKLOG_DELETE', 'Delete operations for Product Backlog');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_BACKLOG_UPDATE', 'Update operations for Product Backlog');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_BACKLOG_VIEW', 'View operations for Product Backlog');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_CREATE', 'Create operations for Product');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_DELETE', 'Delete operations for Product');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_ROLE_UPDATE', 'Limited update operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_ROLE_VIEW', 'Limited view operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_UPDATE', 'Update operations for Product');
INSERT INTO `security_permission` VALUES ('SCRUM_PRODUCT_VIEW', 'View operations for Product');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_CREATE', 'Create operations for Project');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_DELETE', 'Delete operations for Project');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_ROLE_ADMIN', 'All admin operations in the Scrum component for a project the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_ROLE_UPDATE', 'Update operations in the Scrum component for a project the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_ROLE_VIEW', 'All view operations in the Scrum component for a project the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_UPDATE', 'Update operations for Project');
INSERT INTO `security_permission` VALUES ('SCRUM_PROJECT_VIEW', 'View operations for Project');
INSERT INTO `security_permission` VALUES ('SCRUM_ROLE_ADMIN', 'All admin operations in the Scrum component for a project/sprint/task the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_ROLE_TIMESHEET_CREATE', 'Be able to create a weekly timesheet for the loginid.');
INSERT INTO `security_permission` VALUES ('SCRUM_ROLE_TIMESHEET_UPDATE', 'Be able to update(report) on an existing own timesheet');
INSERT INTO `security_permission` VALUES ('SCRUM_ROLE_UPDATE', 'Update operations in the Scrum component for a project/sprint/task the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_ROLE_VIEW', 'All view operations in the Scrum component for a project/sprint/task the user is member of.');
INSERT INTO `security_permission` VALUES ('SCRUM_SPRINT_CREATE', 'Create operations for Sprint');
INSERT INTO `security_permission` VALUES ('SCRUM_SPRINT_DELETE', 'Delete operations for Sprint');
INSERT INTO `security_permission` VALUES ('SCRUM_SPRINT_UPDATE', 'Update operations for Sprint');
INSERT INTO `security_permission` VALUES ('SCRUM_SPRINT_VIEW', 'View operations for Sprint');
INSERT INTO `security_permission` VALUES ('SCRUM_TASK_CREATE', 'Create operations for Task');
INSERT INTO `security_permission` VALUES ('SCRUM_TASK_DELETE', 'Delete operations for Task');
INSERT INTO `security_permission` VALUES ('SCRUM_TASK_UPDATE', 'Update operations for Task');
INSERT INTO `security_permission` VALUES ('SCRUM_TASK_VIEW', 'View operations for Task');
INSERT INTO `security_permission` VALUES ('SCRUM_TEST_ADMIN', 'All Admin Test operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_TEST_CREATE', 'Create Test operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_TEST_DELETE', 'Delete Test operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_TEST_UPDATE', 'Update Test operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_TEST_VIEW', 'View Test operations for Scrum');
INSERT INTO `security_permission` VALUES ('SCRUM_TIMESHEET_CREATE', 'Be able to create any weekly timesheet.');
INSERT INTO `security_permission` VALUES ('SCRUM_TIMESHEET_UPDATE', 'Be able to update any weekly timesheet.');
INSERT INTO `security_permission` VALUES ('SCRUM_UPDATE', 'Update operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SCRUM_VIEW', 'View operations in the SCRUM Component.');
INSERT INTO `security_permission` VALUES ('SECURITY_ADMIN', 'ALL operations in the Security Management Screens.');
INSERT INTO `security_permission` VALUES ('SECURITY_CREATE', 'Create operations in the Security Management Screens.');
INSERT INTO `security_permission` VALUES ('SECURITY_DELETE', 'Delete operations in the Security Management Screens.');
INSERT INTO `security_permission` VALUES ('SECURITY_UPDATE', 'Update operations in the Security Management Screens.');
INSERT INTO `security_permission` VALUES ('SECURITY_VIEW', 'View operations in the Security Management Screens.');
INSERT INTO `security_permission` VALUES ('SEND_CONTROL_APPLET', 'Send to the Control Applet.');
INSERT INTO `security_permission` VALUES ('SERVER_STATS_VIEW', 'View the Server Statistics pages.');
INSERT INTO `security_permission` VALUES ('SERVICE_INVOKE_ANY', 'Permission to invoke any service remotely.');
INSERT INTO `security_permission` VALUES ('SERVICE_JM_LOCK', 'Edit the job manager lock on the Service Maintenance pages.');
INSERT INTO `security_permission` VALUES ('SERVICE_MAINT', 'Use the Service Maintenance pages.');
INSERT INTO `security_permission` VALUES ('SETUP_ADMIN', 'ALL OFBiz Setup operations.');
INSERT INTO `security_permission` VALUES ('SETUP_VIEW', 'OFBiz Setup View permission.');
INSERT INTO `security_permission` VALUES ('SFA_ADMIN', 'ALL operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('SFA_CREATE', 'Create operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('SFA_DELETE', 'Delete operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('SFA_ROLE_UPDATE', 'Limited update operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('SFA_ROLE_VIEW', 'Limited view operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('SFA_UPDATE', 'Update operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('SFA_VIEW', 'View operations in the SFA Manager.');
INSERT INTO `security_permission` VALUES ('TEMPEXPR_ADMIN', 'Temporal expression admin');
INSERT INTO `security_permission` VALUES ('USERPREF_ADMIN', 'User preferences admin');
INSERT INTO `security_permission` VALUES ('UTIL_CACHE_EDIT', 'Edit a UtilCache instance.');
INSERT INTO `security_permission` VALUES ('UTIL_CACHE_VIEW', 'View a UtilCache instance.');
INSERT INTO `security_permission` VALUES ('UTIL_DEBUG_EDIT', 'Edit a UtilDebug instance.');
INSERT INTO `security_permission` VALUES ('UTIL_DEBUG_VIEW', 'View a UtilDebug instance.');
INSERT INTO `security_permission` VALUES ('update', 'Base UPDATE permission');
INSERT INTO `security_permission` VALUES ('VISUALTHEME_ADMIN', 'ALL operations on Visual Themes and Visual Theme Resources.');
INSERT INTO `security_permission` VALUES ('VISUALTHEME_CREATE', 'Create Visual Themes and Visual Theme Resources.');
INSERT INTO `security_permission` VALUES ('VISUALTHEME_DELETE', 'Delete Visual Themes and Visual Theme Resources.');
INSERT INTO `security_permission` VALUES ('VISUALTHEME_UPDATE', 'Update Visual Themes and Visual Theme Resources.');
INSERT INTO `security_permission` VALUES ('WEBPOS_ADMIN', 'ALL Web POS operations.');
INSERT INTO `security_permission` VALUES ('WEBPOS_VIEW', 'Web POS View permission.');
INSERT INTO `security_permission` VALUES ('WEBTOOLS_VIEW', 'Permission to access the WebTools Menu.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_ADMIN', 'ALL operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_CREATE', 'Create operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_DELETE', 'Delete operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_ROLE_CREATE', 'Limited Create operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_ROLE_DELETE', 'Limited Delete operations the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_ROLE_UPDATE', 'Limited Update operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_ROLE_VIEW', 'Limited View operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_UPDATE', 'Update operations in the Work Effort Manager.');
INSERT INTO `security_permission` VALUES ('WORKEFFORTMGR_VIEW', 'View operations in the Work Effort Manager.');

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
  UNIQUE KEY `login_id` (`login_id`),
  KEY `fK_party_group_party` (`party_id`),
  CONSTRAINT `fK_party_group_party` FOREIGN KEY (`party_id`) REFERENCES `party` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login
-- ----------------------------
INSERT INTO `user_login` VALUES ('1', '1', '一号首长', 'admin', '$2a$10$l8H3fWpJDRwsGy8f.HMvcOVXwxAjkz3GQi6pS0Cxzd03K38zMbIMq', 'Y', null, null, null, null, null, null, null, '2018-05-20 22:12:15', 'admin', '2018-05-20 22:12:23', 'admin');
INSERT INTO `user_login` VALUES ('452835981230346240', '452835901148499968', '一號首長', 'abc', '$2a$10$Smt5Y/OtJ2C/Dk0tKEegyOBor2DQkD4ch9SUUY/yBb3.Efgokb.nC', 'Y', null, null, null, null, null, null, null, '2018-06-03 14:10:53', '大王', '2018-06-03 14:10:53', '大王');

-- ----------------------------
-- Table structure for user_login_history
-- ----------------------------
DROP TABLE IF EXISTS `user_login_history`;
CREATE TABLE `user_login_history` (
  `id` bigint(20) NOT NULL,
  `login_id` varchar(30) NOT NULL,
  `login_ip` varchar(20) DEFAULT NULL COMMENT '登录ip',
  `login_date` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login_history
-- ----------------------------
