create table reader
(id int primary key auto_increment,
cardNo varchar(20) unique comment '借阅证卡号',
name varchar(10) not null comment '姓名',
gender varchar(2) comment '性别',
identityNo varchar(18) comment '身份证号',
address varchar(60) comment '住址（或工作单位）',
phone varchar(16) comment '联系电话',
email varchar(30) comment '电子邮件',
validThru datetime comment '有效期至',
category tinyint comment '读者类型（1A类原价，2B类半价，3C类免费）',
managerId int comment '经办人',
remark varchar(60) comment '备注',
createdAt datetime comment '创建时间',
modifiedAt datetime comment '修改时间'
)comment='校外读者信息';

create table manager
(id int primary key auto_increment,
loginName char(16) unique not null comment '登录名',
password char(16) not null comment '密码',
name varchar(10) comment '姓名',
superAdmin tinyint default 0 comment '是否超级管理员（0不是，1是）'
)comment='管理员信息';

create table transaction
(id int primary key auto_increment,
readerId int not null comment '缴费人',
date datetime not null comment '缴费日期',
amount decimal(10,2) not null comment '金额',
type tinyint comment '类型（1阅览服务费，2借阅服务费，3借阅押金，4退押金）',
validThru datetime comment '有效期至',
managerId int comment '经办人',
createdAt datetime comment '创建时间'
)comment='校外读者缴（退）费记录';