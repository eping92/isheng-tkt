-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(30) NOT NULL COMMENT 'id',
  `createuser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `loginName` varchar(30) NOT NULL COMMENT '登录名',
  `nick` varchar(30) DEFAULT NULL COMMENT '昵称',
  `realName` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `headPath` varchar(50) DEFAULT NULL COMMENT '图像地址',
  `pwd` varchar(32) NOT NULL COMMENT '登陆密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `gender` varchar(8) DEFAULT NULL COMMENT '性别枚举',
  `companyId` varchar(19) DEFAULT NULL COMMENT '公司id',
  `companyName` varchar(40) DEFAULT NULL COMMENT '公司名称',
  `deptId` varchar(19) DEFAULT NULL COMMENT '部门ID',
  `deptName` varchar(40) DEFAULT NULL COMMENT '部门名称',
  `userStatus` varchar(10) DEFAULT NULL COMMENT '状态枚举',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
INSERT INTO `user` VALUES ('1040786197856194560', '1040786197856194560', '2018-09-15 10:15:41', null, null, 'eping92', '深蓝', '何平波', null, 'e10adc3949ba59abbe56e057f20f883e', '18672222093', 'MALE', null, null, null, null, 'ENABLE');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(19) NOT NULL COMMENT '主键ID',
  `createUser` varchar(19) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateUser` varchar(19) DEFAULT NULL COMMENT '更新人',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `remark` varchar(50) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
INSERT INTO `role` VALUES ('1040799077196308480', '1040786197856194560', '2018-09-15 11:06:51', null, null, '超级管理员', '超级管理员');

<!-- 权限表 -->
DROP TABLE `menu`;
CREATE TABLE IF NOT EXISTS `role` (
	`id`  varchar(19) NOT NULL COMMENT '主键ID' ,
	`createUser`  varchar(19) NULL COMMENT '创建人' ,
	`createTime`  datetime NULL COMMENT '创建时间' ,
	`updateUser`  varchar(19) NULL COMMENT '更新人' ,
	`updateTime`  datetime NULL COMMENT '更新时间' ,
	`name`  varchar(50) NOT NULL COMMENT '权限名称' ,
	`code`  varchar(50) COMMENT '权限CODE' ,
	`url`  varchar(50)  COMMENT '权限路径',
	`menuType`  varchar(10)  COMMENT '权限类型',
	`parentId`  varchar(19)  COMMENT '父级权限ID',
	`sort`  int(10)  COMMENT '排序号',
	PRIMARY KEY (`id`)
)COMMENT='权限表';

<!-- 用户角色表 -->
DROP TABLE `userRole`;
CREATE TABLE IF NOT EXISTS `userRole` (
	`id`  varchar(19) NOT NULL COMMENT '主键ID' ,
	`createUser`  varchar(19) NULL COMMENT '创建人' ,
	`createTime`  datetime NULL COMMENT '创建时间' ,
	`updateUser`  varchar(19) NULL COMMENT '更新人' ,
	`updateTime`  datetime NULL COMMENT '更新时间' ,
	`userId`  varchar(19) NOT NULL COMMENT '用户ID' ,
	`roleId`  varchar(19) COMMENT '角色ID' ,
	PRIMARY KEY (`id`)
)COMMENT='用户角色表';

<!-- 角色权限表 -->
DROP TABLE `roleMenu`;
CREATE TABLE IF NOT EXISTS `roleMenu` (
	`id`  varchar(19) NOT NULL COMMENT '主键ID' ,
	`createUser`  varchar(19) NULL COMMENT '创建人' ,
	`createTime`  datetime NULL COMMENT '创建时间' ,
	`updateUser`  varchar(19) NULL COMMENT '更新人' ,
	`updateTime`  datetime NULL COMMENT '更新时间' ,
	`roleId`  varchar(19) COMMENT '角色ID' ,
	`menuId`  varchar(19) NOT NULL COMMENT '权限ID' ,
	PRIMARY KEY (`id`)
)COMMENT='角色权限表';

