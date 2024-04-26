/*
Navicat MySQL Data Transfer

Source Server         : RUOYI8
Source Server Version : 80100
Source Host           : localhost:3308
Source Database       : top_news

Target Server Type    : MYSQL
Target Server Version : 80100
File Encoding         : 65001

Date: 2024-04-11 22:43:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for news_type
-- ----------------------------
DROP TABLE IF EXISTS `news_type`;
CREATE TABLE `news_type` (
  `tid` int NOT NULL AUTO_INCREMENT COMMENT '新闻类型id',
  `tname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '新闻类型描述',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of news_type
-- ----------------------------
INSERT INTO `news_type` VALUES ('1', '新闻');
INSERT INTO `news_type` VALUES ('2', '体育');
INSERT INTO `news_type` VALUES ('3', '娱乐');
INSERT INTO `news_type` VALUES ('4', '科技');
INSERT INTO `news_type` VALUES ('5', '其他');
