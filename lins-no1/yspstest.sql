/*
Navicat MySQL Data Transfer

Source Server         : RUOYI8
Source Server Version : 80100
Source Host           : localhost:3308
Source Database       : yspstest

Target Server Type    : MYSQL
Target Server Version : 80100
File Encoding         : 65001

Date: 2024-04-03 11:51:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ysps_classrooms
-- ----------------------------
DROP TABLE IF EXISTS `ysps_classrooms`;
CREATE TABLE `ysps_classrooms` (
  `ClassroomID` int NOT NULL AUTO_INCREMENT,
  `ClassName` varchar(255) NOT NULL,
  `Capacity` int NOT NULL,
  `Status` enum('空闲','占用') NOT NULL,
  PRIMARY KEY (`ClassroomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='记录教室的详细信息，包括名称、容量和当前状态';

-- ----------------------------
-- Records of ysps_classrooms
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_courses
-- ----------------------------
DROP TABLE IF EXISTS `ysps_courses`;
CREATE TABLE `ysps_courses` (
  `CourseID` int NOT NULL AUTO_INCREMENT,
  `CourseName` varchar(255) NOT NULL,
  `TeacherID` int DEFAULT NULL,
  `ClassTime` varchar(255) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `CourseType` enum('在线','面对面') NOT NULL,
  `Credits` int DEFAULT NULL,
  `Description` text,
  PRIMARY KEY (`CourseID`),
  KEY `TeacherID` (`TeacherID`),
  CONSTRAINT `ysps_courses_ibfk_1` FOREIGN KEY (`TeacherID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='包含课程信息，如课程名称、教师、时间和地点';

-- ----------------------------
-- Records of ysps_courses
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_events
-- ----------------------------
DROP TABLE IF EXISTS `ysps_events`;
CREATE TABLE `ysps_events` (
  `EventID` int NOT NULL AUTO_INCREMENT,
  `PublisherID` int DEFAULT NULL,
  `EventName` varchar(255) NOT NULL,
  `EventTime` datetime NOT NULL,
  `EventLocation` varchar(255) NOT NULL,
  `EventType` enum('讲座','社团活动') NOT NULL,
  `Description` text,
  PRIMARY KEY (`EventID`),
  KEY `PublisherID` (`PublisherID`),
  CONSTRAINT `ysps_events_ibfk_1` FOREIGN KEY (`PublisherID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='记录校园内的各种活动信息，如活动名称、时间、地点和描述';

-- ----------------------------
-- Records of ysps_events
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_exams
-- ----------------------------
DROP TABLE IF EXISTS `ysps_exams`;
CREATE TABLE `ysps_exams` (
  `ExamID` int NOT NULL AUTO_INCREMENT,
  `Subject` varchar(255) NOT NULL,
  `ExamTime` datetime NOT NULL,
  `ExamLocation` varchar(255) NOT NULL,
  `ExamType` enum('期中','期末') NOT NULL,
  `ExamFormat` enum('选择题','论文') NOT NULL,
  `Grade` decimal(5,2) DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  PRIMARY KEY (`ExamID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `ysps_exams_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='存储考试信息，包括科目、时间、地点、类型和成绩';

-- ----------------------------
-- Records of ysps_exams
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_participants
-- ----------------------------
DROP TABLE IF EXISTS `ysps_participants`;
CREATE TABLE `ysps_participants` (
  `ParticipantID` int NOT NULL AUTO_INCREMENT,
  `EventID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `SignUpTime` datetime NOT NULL,
  `CheckIn` tinyint(1) NOT NULL,
  PRIMARY KEY (`ParticipantID`),
  KEY `EventID` (`EventID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `ysps_participants_ibfk_1` FOREIGN KEY (`EventID`) REFERENCES `ysps_events` (`EventID`),
  CONSTRAINT `ysps_participants_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='记录活动参与者的信息，包括报名时间和签到状态';

-- ----------------------------
-- Records of ysps_participants
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_posts
-- ----------------------------
DROP TABLE IF EXISTS `ysps_posts`;
CREATE TABLE `ysps_posts` (
  `PostID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `Title` varchar(255) NOT NULL,
  `Content` text NOT NULL,
  `PostTime` datetime NOT NULL,
  `LastUpdateTime` datetime DEFAULT NULL,
  `ReplyCount` int DEFAULT '0',
  `MediaContent` text,
  PRIMARY KEY (`PostID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `ysps_posts_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='存储用户发布的帖子信息，包括标题、内容和发布时间';

-- ----------------------------
-- Records of ysps_posts
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_ratings
-- ----------------------------
DROP TABLE IF EXISTS `ysps_ratings`;
CREATE TABLE `ysps_ratings` (
  `RatingID` int NOT NULL AUTO_INCREMENT,
  `EventID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `Rating` decimal(3,2) NOT NULL,
  `Comment` text,
  `RatingTime` datetime NOT NULL,
  PRIMARY KEY (`RatingID`),
  KEY `EventID` (`EventID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `ysps_ratings_ibfk_1` FOREIGN KEY (`EventID`) REFERENCES `ysps_events` (`EventID`),
  CONSTRAINT `ysps_ratings_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='存储对活动的评价，包括评分和评论内容';

-- ----------------------------
-- Records of ysps_ratings
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_replies
-- ----------------------------
DROP TABLE IF EXISTS `ysps_replies`;
CREATE TABLE `ysps_replies` (
  `ReplyID` int NOT NULL AUTO_INCREMENT,
  `PostID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `Content` text NOT NULL,
  `ReplyTime` datetime NOT NULL,
  PRIMARY KEY (`ReplyID`),
  KEY `PostID` (`PostID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `ysps_replies_ibfk_1` FOREIGN KEY (`PostID`) REFERENCES `ysps_posts` (`PostID`),
  CONSTRAINT `ysps_replies_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `ysps_users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='存储用户对帖子的回复，包括回复内容和时间';

-- ----------------------------
-- Records of ysps_replies
-- ----------------------------

-- ----------------------------
-- Table structure for ysps_users
-- ----------------------------
DROP TABLE IF EXISTS `ysps_users`;
CREATE TABLE `ysps_users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `UserType` enum('学生','教师','管理员') NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='存储用户信息，包括学生、教师和管理员等用户类型';

-- ----------------------------
-- Records of ysps_users
-- ----------------------------
