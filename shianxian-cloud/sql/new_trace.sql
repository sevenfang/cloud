/*
Navicat MySQL Data Transfer

Source Server         : 测试-all
Source Server Version : 50721
Source Host           : 47.100.183.36:3306
Source Database       : new_trace

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-10-29 17:20:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dual
-- ----------------------------
DROP TABLE IF EXISTS `dual`;
CREATE TABLE `dual` (
  `test` char(1) DEFAULT '1' COMMENT '测试表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_mobile_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_mobile_log`;
CREATE TABLE `sys_mobile_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `log_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '项目名称',
  `log_detail` varchar(11840) COLLATE utf8_bin NOT NULL COMMENT '日志明细',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='移动端日志表';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `permission_code` varchar(100) CHARACTER SET utf32 DEFAULT NULL COMMENT '权限代码',
  `parent_id` int(255) DEFAULT NULL COMMENT '父id',
  `type` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '类型',
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'url',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(10) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(10) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限中间表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户登录名称',
  `password` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户登录密码（md5加密）',
  `parent_id` int(10) DEFAULT NULL COMMENT '父id',
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `company_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT '0' COMMENT '是否为管理员  1：有效  0：无效  2：超级管理员（此账号为数据库手动所建）',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否有效  1：有效  0：无效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色中间表';

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `category_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类别名称',
  `category_type` int(10) DEFAULT NULL COMMENT '物料类别分类',
  `description` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '类别描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='类别表';

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '公司ID',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '企业名称',
  `short_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '企业简称',
  `company_code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '企业节点码',
  `trace_code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '追溯节点码',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '经营地址',
  `code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '统一社会代码/证件号码',
  `corporate` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '法人代表',
  `contacts` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `tel` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `company_type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '企业类型',
  `territory` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '经营领域',
  `business` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '主营业务',
  `scope` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '经营范围',
  `province` char(6) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `city` char(6) COLLATE utf8_bin DEFAULT NULL COMMENT '市',
  `area` char(6) COLLATE utf8_bin DEFAULT NULL COMMENT '区',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否有效  1：有效  0：无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=244 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='企业表';

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `customer_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '客户名称',
  `contacts` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `tel` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '经营地址',
  `customer_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '追溯节点码',
  `business_license` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '营业执照/证件号',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否有效  1：有效  0：无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='客户表';

-- ----------------------------
-- Table structure for t_goods_in_order
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_in_order`;
CREATE TABLE `t_goods_in_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `goods_in_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '商品入库单号',
  `goods_in_order_time` datetime DEFAULT NULL COMMENT '入库日期',
  `process_goods_num` int(10) DEFAULT NULL COMMENT '加工数量，加工后的商品',
  `process_material_id` int(10) DEFAULT NULL COMMENT '物料加工id',
  `process_material_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '物料加工单号',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id，加工后的商品',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `status` tinyint(1) DEFAULT '3' COMMENT '审核状态。1：待审核，2：审核不过，3：审核通过。默认是审核通过',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品入库';

-- ----------------------------
-- Table structure for t_goods_out_order
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_out_order`;
CREATE TABLE `t_goods_out_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `goods_out_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '商品出库单号',
  `goods_out_order_time` datetime DEFAULT NULL COMMENT '出库日期',
  `sale_order_id` int(10) DEFAULT NULL COMMENT '销售单id',
  `sale_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '销售单号',
  `operator_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '操作员',
  `consigner_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '操作员',
  `status` tinyint(1) DEFAULT NULL COMMENT '订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品出库单';

-- ----------------------------
-- Table structure for t_goods_out_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_out_order_detail`;
CREATE TABLE `t_goods_out_order_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_out_order_id` int(10) DEFAULT NULL COMMENT '商品出库单id',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `goods_num` int(10) DEFAULT NULL COMMENT '商品出库数量',
  `goods_trace_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '商品追溯码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品出库单详情';

-- ----------------------------
-- Table structure for t_material
-- ----------------------------
DROP TABLE IF EXISTS `t_material`;
CREATE TABLE `t_material` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `material_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '物料名称',
  `material_type` int(1) DEFAULT NULL COMMENT '物料类型。1：原料。2：商品',
  `nation_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '国家标准编码',
  `nation_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '国家标准名称',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `is_product` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '是否半成品，1：是  0：不是',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `quarantine_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '检疫证号',
  `standard` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '产品规格',
  `images` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '产品图片',
  `num` int(10) DEFAULT NULL COMMENT '物料库存',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物料表';

-- ----------------------------
-- Table structure for t_process_material
-- ----------------------------
DROP TABLE IF EXISTS `t_process_material`;
CREATE TABLE `t_process_material` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `process_material_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '物料加工单号',
  `process_material_num` int(10) DEFAULT NULL COMMENT '物料加工数量，0：加工库存里所有的这个原料',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id，加工的原料',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物料加工主表';

-- ----------------------------
-- Table structure for t_process_material_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_process_material_detail`;
CREATE TABLE `t_process_material_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `process_material_id` int(10) DEFAULT NULL COMMENT '物料加工主表id',
  `process_goods_num` int(10) DEFAULT NULL COMMENT '加工数量，加工后的商品',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id，加工后的商品',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物料加工详情';

-- ----------------------------
-- Table structure for t_process_template
-- ----------------------------
DROP TABLE IF EXISTS `t_process_template`;
CREATE TABLE `t_process_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id，此物料为原料',
  `process_template_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '加工模板名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='加工模板表';

-- ----------------------------
-- Table structure for t_process_template_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_process_template_detail`;
CREATE TABLE `t_process_template_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `process_template_id` int(10) DEFAULT NULL COMMENT '加工模板id',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id，此物料为加工过后的商品',
  `process_template_num` int(10) DEFAULT NULL COMMENT '加工数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='加工模板明细表';

-- ----------------------------
-- Table structure for t_purchase_in_order
-- ----------------------------
DROP TABLE IF EXISTS `t_purchase_in_order`;
CREATE TABLE `t_purchase_in_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `purchase_in_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '采购入库单号',
  `material_in_time` datetime DEFAULT NULL COMMENT '入库日期',
  `status` tinyint(1) DEFAULT NULL COMMENT '审核状态。1：待审核，2：审核中，3：审核通过',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='采购入库单表';

-- ----------------------------
-- Table structure for t_purchase_in_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_purchase_in_order_detail`;
CREATE TABLE `t_purchase_in_order_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `purchase_in_order_id` int(10) DEFAULT NULL COMMENT '采购入库单id',
  `supplier_in_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '供应商采购入库单号，为供应商自己的进货单号',
  `purchase_in_order_num` int(10) DEFAULT NULL COMMENT '进货物料数量',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `supplier_id` int(10) DEFAULT NULL COMMENT '供应商id',
  `quarantine_no` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '检疫证号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='采购入库详情表';

-- ----------------------------
-- Table structure for t_raw_out_order
-- ----------------------------
DROP TABLE IF EXISTS `t_raw_out_order`;
CREATE TABLE `t_raw_out_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `raw_out_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '原料出库单号',
  `raw_out_order_time` datetime DEFAULT NULL COMMENT '出库日期',
  `process_material_id` int(10) DEFAULT NULL COMMENT '原料加工id',
  `process_material_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '原料加工单号',
  `process_material_num` int(10) DEFAULT NULL COMMENT '原料加工数量，0：加工库存里所有的这个原料',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id，加工的原料',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `status` tinyint(1) DEFAULT '3' COMMENT '审核状态。1：待审核，2：审核不过，3：审核通过。默认是审核通过',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='原料出库';

-- ----------------------------
-- Table structure for t_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_order`;
CREATE TABLE `t_sale_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `sale_order_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '销售单号',
  `sale_order_time` datetime DEFAULT NULL COMMENT '销售日期',
  `status` tinyint(1) DEFAULT NULL COMMENT '订单状态。1：待审核，2：已审核，3：未发货，4：已发货，5：已收货',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='销售单';

-- ----------------------------
-- Table structure for t_sale_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_order_detail`;
CREATE TABLE `t_sale_order_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sale_order_id` int(10) DEFAULT NULL COMMENT '销售单id',
  `material_id` int(10) DEFAULT NULL COMMENT '物料id',
  `category_id` int(10) DEFAULT NULL COMMENT '物料类别id',
  `unit_id` int(10) DEFAULT NULL COMMENT '计量单位id',
  `sale_num` int(10) DEFAULT NULL COMMENT '销售物料数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='销售单详情';

-- ----------------------------
-- Table structure for t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `supplier_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '供应商名称',
  `contacts` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `tel` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '联系电话',
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '经营地址',
  `business_license` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '营业执照/证件号',
  `intro` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否有效  1：有效  0：无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='供应商表';

-- ----------------------------
-- Table structure for t_unit
-- ----------------------------
DROP TABLE IF EXISTS `t_unit`;
CREATE TABLE `t_unit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `company_id` int(10) DEFAULT NULL COMMENT '企业id',
  `unit_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '计量单位名称',
  `description` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '计量单位描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='计量单位表';
