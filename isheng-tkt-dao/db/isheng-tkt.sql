DROP TABLE user;
CREATE TABLE IF NOT EXISTS user (
	id 			VARCHAR(30) 	NOT NULL PRIMARY KEY UNIQUE COMMENT 'id',
	createuser 	VARCHAR(30) 	COMMENT '创建人',
	createtime 	DATETIME 		COMMENT '创建时间',
	updateUser 	VARCHAR(30) 	COMMENT '更新人',
	updateTime 	DATETIME 		COMMENT '更新时间',
	loginName 	VARCHAR(30) 	NOT null comment '登录名',
	nick 		VARCHAR(30) 	COMMENT '昵称',
	realName 	VARCHAR(30) 	COMMENT '真实姓名',
	headPath 	VARCHAR(50) 	COMMENT '图像地址',
	pwd 		VARCHAR(32) 	NOT NULL COMMENT '登陆密码',
	mobile 		VARCHAR(11) 	COMMENT '手机号',
	gender 		VARCHAR(8) 		COMMENT '性别枚举',
	companyId 	VARCHAR(19) 	COMMENT '公司id',
	companyName VARCHAR(40) 	COMMENT '公司名称',
	deptId 		VARCHAR(19) 	COMMENT '部门ID',
	deptName 	VARCHAR(40) 	COMMENT '部门名称',
	userStatus 	VARCHAR(10) 	COMMENT '状态枚举'
)comment='用户表';

