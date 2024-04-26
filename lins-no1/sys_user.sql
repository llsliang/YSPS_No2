/*
Navicat MySQL Data Transfer

Source Server         : RUOYI8
Source Server Version : 80100
Source Host           : localhost:3308
Source Database       : ysps_no1

Target Server Type    : MYSQL
Target Server Version : 80100
File Encoding         : 65001

Date: 2024-04-06 21:54:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb3 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '103', 'linsen', 'lins', '00', '2972114139@qq.com', '16888888888', '0', '/profile/avatar/2024/04/03/吃瓜_20240403110016A001.jpg', '$2a$10$y29xB2dwaEzIawh7K8gHjuRkVuc14tlZmOzc7dQS95Kl367dKoMCC', '0', '0', '127.0.0.1', '2024-04-06 16:25:45', 'admin', '2024-04-02 17:33:40', '', '2024-04-06 16:25:44', '管理员');
INSERT INTO `sys_user` VALUES ('104', null, 'zhouzhou', 'zhouzhou', '00', '', '', '0', '', '$2a$10$oZ9dRszClpeHlFO4.HSi2e5zT7r3JkLUpyFcJUk3uG6vxrkeFlX5S', '0', '0', '127.0.0.1', '2024-04-06 15:38:18', '', '2024-04-06 14:16:50', '', '2024-04-06 15:38:17', null);
INSERT INTO `sys_user` VALUES ('105', null, 'admin', 'admin', '00', '', '', '0', '/profile/avatar/2024/04/06/F8E0298AA3236EAB8EDC856E8C62CA5B_20240406145450A001.png', '$2a$10$oZ62Ea9qw3zTj1FinK1LXO6QIpmyme7JmDYzu1G9JKnnugUjquvfa', '0', '0', '127.0.0.1', '2024-04-06 16:24:08', '', '2024-04-06 14:18:28', 'linsen', '2024-04-06 16:24:07', null);
