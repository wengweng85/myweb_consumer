-- Create table
create table PAGE_DESIGN
(
  id              VARCHAR2(32) not null,
  page_name       VARCHAR2(200),
  page_describe   VARCHAR2(500),
  serialized_data CLOB,
  isvalid         VARCHAR2(3),
  designtime      DATE
);
-- Add comments to the table 
comment on table PAGE_DESIGN
  is '页面设计表';
-- Add comments to the columns 
comment on column PAGE_DESIGN.id
  is '主键';
comment on column PAGE_DESIGN.page_name
  is '名称';
comment on column PAGE_DESIGN.page_describe
  is '描述';
comment on column PAGE_DESIGN.serialized_data
  is '数据';
comment on column PAGE_DESIGN.isvalid
  is '是否有效(1是 0 否)';
comment on column PAGE_DESIGN.designtime
  is '保存时间';
  
  

create table S_USER
(
  userid        VARCHAR2(32) not null,
  username      VARCHAR2(200),
  loginname     VARCHAR2(200),
  idcard        VARCHAR2(20),
  password      VARCHAR2(200),
  realname      VARCHAR2(200),
  sex           VARCHAR2(32),
  email         VARCHAR2(200),
  mobilephone   VARCHAR2(32),
  phone         VARCHAR2(32),
  type          VARCHAR2(6),
  logintimes    VARCHAR2(32),
  lastlogintime DATE,
  lastloginip   VARCHAR2(200),
  organname     VARCHAR2(100),
  address       VARCHAR2(1000),
  post          VARCHAR2(100),
  salt          VARCHAR2(200),
  enabled       VARCHAR2(3),
  createby      VARCHAR2(100),
  createtime    DATE,
  updateby      VARCHAR2(100),
  updatetime    DATE
)
;
comment on table S_USER
  is '账户信息表';
comment on column S_USER.userid
  is '用户表流水号';
comment on column S_USER.username
  is '用户名(用户内码)';
comment on column S_USER.loginname
  is '用户登录名';
comment on column S_USER.idcard
  is '身份证号';
comment on column S_USER.password
  is '用户密码';
comment on column S_USER.realname
  is '真实姓名';
comment on column S_USER.sex
  is '性别(1男 2女)';
comment on column S_USER.email
  is '电子邮件';
comment on column S_USER.mobilephone
  is '手机号';
comment on column S_USER.phone
  is '电话';
comment on column S_USER.type
  is '用户类型';
comment on column S_USER.logintimes
  is '登录次数';
comment on column S_USER.lastlogintime
  is '最后一次登录时间';
comment on column S_USER.lastloginip
  is '最后一次登录ip';
comment on column S_USER.organname
  is '用户所属机构名称';
comment on column S_USER.address
  is '联系地址';
comment on column S_USER.post
  is '邮编';
comment on column S_USER.salt
  is '登录salt';
comment on column S_USER.enabled
  is '是否有效(1有效 0 无效黑名单)';
comment on column S_USER.createby
  is '创建人员';
comment on column S_USER.createtime
  is '创建时间';
comment on column S_USER.updateby
  is '更新人员';
comment on column S_USER.updatetime
  is '更新时间';
alter table S_USER
  add constraint PK_S_USER primary key (USERID);


insert into S_USER (userid, username, loginname, idcard, password, realname, sex, email, mobilephone, phone, type, logintimes, lastlogintime, lastloginip, organname, address, post, salt, enabled, createby, createtime, updateby, updatetime)
values ('1', 'admin', 'admin', null, '0192023a7bbd73250516f069df18b500', '超级管理员', null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null);
  
