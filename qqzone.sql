
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_friend_basic_uid` (`uid`),
  KEY `FK_friend_basic_fid` (`fid`),
  CONSTRAINT `FK_friend_basic_fid` FOREIGN KEY (`fid`) REFERENCES `t_user_basic` (`id`),
  CONSTRAINT `FK_friend_basic_uid` FOREIGN KEY (`uid`) REFERENCES `t_user_basic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_host_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_host_reply`;
CREATE TABLE `t_host_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `hostReplyDate` datetime NOT NULL,
  `author` int(11) NOT NULL,
  `reply` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_host_basic` (`author`),
  KEY `FK_host_reply` (`reply`),
  CONSTRAINT `FK_host_basic` FOREIGN KEY (`author`) REFERENCES `t_user_basic` (`id`),
  CONSTRAINT `FK_host_reply` FOREIGN KEY (`reply`) REFERENCES `t_reply` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `replyDate` datetime NOT NULL,
  `author` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reply_basic` (`author`),
  KEY `FK_reply_topic` (`topic`),
  CONSTRAINT `FK_reply_basic` FOREIGN KEY (`author`) REFERENCES `t_user_basic` (`id`),
  CONSTRAINT `FK_reply_topic` FOREIGN KEY (`topic`) REFERENCES `t_topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `topicDate` datetime NOT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_topic_basic` (`author`),
  CONSTRAINT `FK_topic_basic` FOREIGN KEY (`author`) REFERENCES `t_user_basic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_basic
-- ----------------------------
DROP TABLE IF EXISTS `t_user_basic`;
CREATE TABLE `t_user_basic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(20) NOT NULL,
  `nickName` varchar(50) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `headImg` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_user_detail`;
CREATE TABLE `t_user_detail` (
  `id` int(11) NOT NULL,
  `realName` varchar(20) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `star` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_detail_basic` FOREIGN KEY (`id`) REFERENCES `t_user_basic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

