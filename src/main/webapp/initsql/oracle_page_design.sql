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
  is 'ҳ����Ʊ�';
-- Add comments to the columns 
comment on column PAGE_DESIGN.id
  is '����';
comment on column PAGE_DESIGN.page_name
  is '����';
comment on column PAGE_DESIGN.page_describe
  is '����';
comment on column PAGE_DESIGN.serialized_data
  is '����';
comment on column PAGE_DESIGN.isvalid
  is '�Ƿ���Ч(1�� 0 ��)';
comment on column PAGE_DESIGN.designtime
  is '����ʱ��';
  
  

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
  is '�˻���Ϣ��';
comment on column S_USER.userid
  is '�û�����ˮ��';
comment on column S_USER.username
  is '�û���(�û�����)';
comment on column S_USER.loginname
  is '�û���¼��';
comment on column S_USER.idcard
  is '���֤��';
comment on column S_USER.password
  is '�û�����';
comment on column S_USER.realname
  is '��ʵ����';
comment on column S_USER.sex
  is '�Ա�(1�� 2Ů)';
comment on column S_USER.email
  is '�����ʼ�';
comment on column S_USER.mobilephone
  is '�ֻ���';
comment on column S_USER.phone
  is '�绰';
comment on column S_USER.type
  is '�û�����';
comment on column S_USER.logintimes
  is '��¼����';
comment on column S_USER.lastlogintime
  is '���һ�ε�¼ʱ��';
comment on column S_USER.lastloginip
  is '���һ�ε�¼ip';
comment on column S_USER.organname
  is '�û�������������';
comment on column S_USER.address
  is '��ϵ��ַ';
comment on column S_USER.post
  is '�ʱ�';
comment on column S_USER.salt
  is '��¼salt';
comment on column S_USER.enabled
  is '�Ƿ���Ч(1��Ч 0 ��Ч������)';
comment on column S_USER.createby
  is '������Ա';
comment on column S_USER.createtime
  is '����ʱ��';
comment on column S_USER.updateby
  is '������Ա';
comment on column S_USER.updatetime
  is '����ʱ��';
alter table S_USER
  add constraint PK_S_USER primary key (USERID);


insert into S_USER (userid, username, loginname, idcard, password, realname, sex, email, mobilephone, phone, type, logintimes, lastlogintime, lastloginip, organname, address, post, salt, enabled, createby, createtime, updateby, updatetime)
values ('1', 'admin', 'admin', null, '0192023a7bbd73250516f069df18b500', '��������Ա', null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null);
  
