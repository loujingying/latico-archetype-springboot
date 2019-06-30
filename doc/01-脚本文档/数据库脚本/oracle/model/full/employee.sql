create table EMPLOYEE
(
  ID NUMBER not null
  primary key,
  NAME VARCHAR2(20),
  AGE NUMBER,
  ADDRESS VARCHAR2(100),
  INTRO CLOB,
  PICTURE BLOB
)
  /

comment on table EMPLOYEE is '员工样例表，测试用'
  /

comment on column EMPLOYEE.ID is 'id,主键'
  /

comment on column EMPLOYEE.NAME is '姓名'
  /

comment on column EMPLOYEE.AGE is '年龄'
  /

comment on column EMPLOYEE.ADDRESS is '地址'
  /

comment on column EMPLOYEE.INTRO is '简介'
  /

comment on column EMPLOYEE.PICTURE is '头像'
  /

