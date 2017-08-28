/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.17-log : Database - humanresources
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`humanresources` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `humanresources`;

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `atdId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `atdCreateTime` datetime DEFAULT NULL COMMENT '时间',
  `atdFlag` int(11) DEFAULT NULL COMMENT '是否有效：1有效2无效',
  `userId` int(11) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`atdId`),
  KEY `userId` (`userId`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `attendance` */

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `deptId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `deptName` varchar(16) DEFAULT NULL COMMENT '名称',
  `deptRemark` varchar(16) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`deptId`,`deptName`,`deptRemark`) values (1,'技术部','主要负责本公司技术支持'),(2,'后勤部','负责本公司采购、维修、物资保障'),(3,'小卖部','公司里面卖东西的场所'),(4,'公关部','负责公司形象、外交等事务'),(5,'安保部','保障公司的治安，砸场子打架');

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `docId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `docTitle` varchar(50) DEFAULT NULL COMMENT '标题',
  `docFileName` varchar(100) DEFAULT NULL COMMENT '文件名称',
  `docRemark` text COMMENT '内容',
  `docCreateDate` datetime DEFAULT NULL COMMENT '时间',
  `userId` int(11) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`docId`),
  KEY `document_ibfk_1` (`userId`),
  CONSTRAINT `document_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `document` */

insert  into `document`(`docId`,`docTitle`,`docFileName`,`docRemark`,`docCreateDate`,`userId`) values (1,'测试文档.txt','C:\\Users\\Administrator\\Desktop\\aaa.txt','我是个文档而已哇\r\n','2017-07-14 11:58:04',1),(2,'再测一个','C:\\Users\\Administrator\\Desktop\\','这是一个测试文件，等下记得删掉就好\r\n','2017-07-14 12:26:25',80);

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `empId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `empName` varchar(50) DEFAULT NULL COMMENT '姓名',
  `empCardId` varchar(100) DEFAULT NULL COMMENT '身份证',
  `empAddress` varchar(100) DEFAULT NULL COMMENT '住址',
  `empPostCode` varchar(10) DEFAULT NULL COMMENT '邮编',
  `empTel` varchar(11) DEFAULT NULL COMMENT '手机号',
  `empPhone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `empQQNum` varchar(12) DEFAULT NULL COMMENT 'QQ号',
  `empEmail` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `empSex` int(11) DEFAULT NULL COMMENT '性别',
  `empParty` varchar(10) DEFAULT NULL COMMENT '政治面貌',
  `empBirthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `empRace` varchar(20) DEFAULT NULL COMMENT '民族',
  `empEducation` varchar(10) DEFAULT NULL COMMENT '学历',
  `empSpeciality` varchar(20) DEFAULT NULL COMMENT '专业',
  `empHobby` varchar(50) DEFAULT NULL COMMENT '爱好',
  `empRemark` varchar(100) DEFAULT NULL COMMENT '备注',
  `empCreateDate` varchar(40) DEFAULT NULL COMMENT '创建时间',
  `userId` int(11) DEFAULT NULL COMMENT '用户表主键',
  `deptId` int(11) DEFAULT NULL COMMENT '部门表主键',
  `posId` int(11) DEFAULT NULL COMMENT '职位表主键',
  PRIMARY KEY (`empId`),
  KEY `userId` (`userId`),
  KEY `deptId` (`deptId`),
  KEY `posId` (`posId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`deptId`) REFERENCES `dept` (`deptId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`posId`) REFERENCES `position` (`posId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`empId`,`empName`,`empCardId`,`empAddress`,`empPostCode`,`empTel`,`empPhone`,`empQQNum`,`empEmail`,`empSex`,`empParty`,`empBirthday`,`empRace`,`empEducation`,`empSpeciality`,`empHobby`,`empRemark`,`empCreateDate`,`userId`,`deptId`,`posId`) values (1,'赵凯琦VIP','230231199307187827','力宝大厦18楼','100000','18381687289','0665-1234567','1447354054','1447354054@qq.com',1,'党员','1993-07-18 15:25:20','汉族','海归博士','机械设计制造及其自动化','敲代码','帅、聪明、博学','2017-07-12 00:00:00',1,4,1),(2,'赵丽颖','234367876894565456','天府三街','122111','18381687289','0665-6757546','121212121','123123123@qq.com',2,'群众','2017-07-19','汉','大学本科','戏剧','吃','楚乔传','2017-07-12 00:00:00',82,4,1),(3,'张学友','230238899307187827','春熙路','212333','13245646578','0665-7654321','12345678','zhangxueyou@qq.com',1,'群众','2017-07-17','满','小学没毕业','歌唱','唱歌','遥远的她','2017-07-13 12:17:38',93,3,4),(5,'陈奕迅','234312345694565456','五里沟','122133','13281687289','0665-1239876','1234567894','chenyixun@qq.com',1,'党员','2017-06-25','汉','硕士','表演','唱歌','富士山下','2017-07-13 12:58:23',97,4,1),(6,'黄晓明','213938447309487526','哈萨克斯坦','233221','15281685468','0665-1234560','222223333','huangxiaoming@qq.com',1,'群众','2017-07-18','汉','小学','演戏','吃饭','琅琊榜','2017-07-13 13:28:57',99,3,2),(9,'张飞','313938447309487526','虎牢关','999999','13299999999','0665-9999999','999999999','zhangfei@qq.com',1,'五虎上将','2017-07-04','汉','幼儿园','杀猪','杀猪','三国蜀国五虎上将之一','2017-07-13 23:57:37',102,5,4),(10,'貂蝉','440231199307187827','建邺','212098','18381667289','0665-9874567','1234333394','diaochan@qq.com',2,'党员','2017-07-10','汉','技校','舞蹈','时装','这个，挺好的','2017-07-14 00:01:12',103,2,3);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `msgId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `msgTitle` varchar(50) DEFAULT NULL COMMENT '标题',
  `msgContent` text COMMENT '内容',
  `msgCreateDate` datetime DEFAULT NULL COMMENT '时间',
  `userId` int(11) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`msgId`),
  KEY `userId` (`userId`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`msgId`,`msgTitle`,`msgContent`,`msgCreateDate`,`userId`) values (1,'第一章-测试篇','通告表的第一条测试文件','2017-07-25 01:22:03',1),(2,'第二章','通告表的第二条测试文件\r\n','2017-07-14 01:22:44',80),(3,'第三章','通告表的第三篇测试文件阿','2017-07-26 01:23:13',82),(4,'添加一篇公告作为测试','首先我们应该了解这两个类型的字段到底有什么不同:\r\n虽然看起这他们是叫TEXT,但他不是用来保存文本的,实际上是用来保存文件,和IMAGE类型一样的,一般用来保存MIME类型的数据.\r\n而我把它用来保存新闻的主体内容了,这样能存的更多.老是读取不出来','2017-07-14 10:23:14',64),(7,'再添加一篇测试路径','尊敬的用户，您已经欠费，请及时续费','2017-07-14 10:36:29',104),(8,'备份前进行的测试','看看行不行','2017-07-14 11:59:50',1),(9,'第三条','试试就试试','2017-07-14 12:47:39',101);

/*Table structure for table `position` */

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `posId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `posName` varchar(16) DEFAULT NULL COMMENT '名称',
  `posRemark` varchar(16) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`posId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `position` */

insert  into `position`(`posId`,`posName`,`posRemark`) values (1,'项目经理','带领团队'),(2,'小卖部长','管理小卖部'),(3,'后勤部长','保障后勤工作正常运作'),(4,'保安部长','保障治安，保护财产安全'),(8,'A1级员工','A类1级普通员工'),(9,'A2级员工','A类2级普通员工'),(10,'B1级员工','B类1级普通员工'),(11,'B2级员工','B类2级普通员工');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `loginName` varchar(16) DEFAULT NULL COMMENT '登录名',
  `userPassWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `status` int(11) DEFAULT NULL COMMENT '状态值',
  `createDate` date DEFAULT NULL COMMENT '建档日期',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`userName`,`loginName`,`userPassWord`,`status`,`createDate`) values (1,'赵凯琦VIP','912167269','123456',1,'2017-07-11'),(63,'李连杰','1447354054','1234567',1,'2017-07-11'),(64,'甄子丹','1234567','1234567',1,'2017-07-11'),(68,'刘德华','123456','1234567',1,'2017-07-11'),(80,'狗剩子','22445566','33445566',1,'2017-07-11'),(82,'赵丽颖','12121212','12121212',2,'2017-07-11'),(85,'赵丽颖','44423231','12455532',2,'2017-07-11'),(88,'赵丽颖','12344444','1243414114',1,'2017-07-11'),(92,'二狗子','12443533333','75435235',1,'2017-07-12'),(93,'zhangxueyou@qq.com','13245646578','13245646578',0,'2017-07-13'),(94,'dongfangbubai@qq.com','1324564623','13245646578',0,'2017-07-13'),(95,'66666666@qq.com','18381681234','18381681234',0,'2017-07-13'),(96,'huangfeihong@qq.com','18381681111','18381681111',0,'2017-07-13'),(97,'chenyixun@qq.com','13281687289','13281687289',0,'2017-07-13'),(98,'laoganma@qq.com','15281687289','15281687289',0,'2017-07-13'),(99,'huangxiaoming@qq.com','15281685468','15281685468',0,'2017-07-13'),(100,'zhengchenggong@qq.com','15833352018','15833352018',0,'2017-07-13'),(101,'huitailang@qq.com','15981687289','15981687289',0,'2017-07-13'),(102,'zhangfei@qq.com','13299999999','13299999999',0,'2017-07-13'),(103,'diaochan@qq.com','18381667289','18381667289',0,'2017-07-14'),(104,'貂蝉','diaochan','1111111',1,'2017-07-14'),(105,'郭靖','guojing','12121212',1,'2017-07-14');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
