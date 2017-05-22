/*==============================================================*/
/* DBMS name:      MySQL 4.0                                    */
/* Created on:     2009-1-22 14:00:27                           */
/*==============================================================*/


drop table if exists PRO_AUTHORITIES;

drop table if exists PRO_BANK_LOAN;

drop table if exists PRO_DEPARTMENT;

drop table if exists PRO_EMPLOYEE;

drop table if exists PRO_MENU;

drop table if exists PRO_RESOURCE;

drop table if exists PRO_RES_AUTH;

drop table if exists PRO_SYSTEM_LOG;

drop table if exists PRO_USER;

drop table if exists PRO_USER_AUTH;

drop table if exists PRO_USER_LOG;

/*==============================================================*/
/* Table: PRO_AUTHORITIES                                       */
/*==============================================================*/
create table PRO_AUTHORITIES
(
   AUTH_ID                        varchar(36)                    not null,
   AUTHORITY                      varchar(256)                   not null,
   DISPLAY                        varchar(256)                   not null,
   NOTE                           varchar(1024),
   primary key (AUTH_ID)
)
comment = "权限"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_BANK_LOAN                                         */
/*==============================================================*/
create table PRO_BANK_LOAN
(
   ORG                            varchar(256)                   not null,
   AREA                           varchar(256)                   not null,
   DEAD_LOANS                     float(8,2)                     not null,
   OVERDUE_LOANS                  float(8,2)                     not null,
   VIOLATION_LOANS                float(8,2)                     not null
)
comment = "银行贷款"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_DEPARTMENT                                        */
/*==============================================================*/
create table PRO_DEPARTMENT
(
   DEPT_ID                        varchar(36)                    not null,
   FULLNAME                       varchar(256)                   not null,
   SHORTNAME                      varchar(256)                   not null,
   REMARK                         varchar(1024),
   primary key (DEPT_ID)
)
comment = "部门"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_EMPLOYEE                                          */
/*==============================================================*/
create table PRO_EMPLOYEE
(
   EMPLOYEE_ID                    varchar(36)                    not null,
   DEPT_ID                        varchar(36)                    not null,
   EMPLOYEE_NAME                  varchar(256)                   not null,
   EMAIL                          varchar(256),
   MOBILE                         varchar(256),
   primary key (EMPLOYEE_ID)
)
comment = "员工"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_MENU                                              */
/*==============================================================*/
create table PRO_MENU
(
   MENU_ID                        varchar(36)                    not null,
   PARENT_ID                      varchar(36)                    not null,
   NAME                           varchar(256)                   not null,
   NOTE                           varchar(1024),
   HREF                           varchar(1024),
   SORT                           varchar(4)                     not null,
   LEAF                           int                            not null default 1,
   primary key (MENU_ID)
)
comment = "菜单"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_RESOURCE                                          */
/*==============================================================*/
create table PRO_RESOURCE
(
   RES_ID                         varchar(36)                    not null,
   RES_TYPE                       varchar(256)                   not null,
   RES_STRING                     varchar(1024)                  not null,
   DISPLAY                        varchar(256)                   not null,
   primary key (RES_ID)
)
comment = "资源"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_RES_AUTH                                          */
/*==============================================================*/
create table PRO_RES_AUTH
(
   RES_ID                         varchar(36)                    not null,
   AUTH_ID                        varchar(36)                    not null,
   primary key (RES_ID, AUTH_ID)
)
comment = "资源权限关系"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_SYSTEM_LOG                                        */
/*==============================================================*/
create table PRO_SYSTEM_LOG
(
   SYS_LOG_ID                     varchar(36)                    not null,
   SYS_LOG_TYPE                   varchar(4),
   CONTENT                        varchar(1024),
   SYS_LOG_RESULT                 varchar(4),
   CREATETIME                     varchar(32),
   primary key (SYS_LOG_ID)
)
type = InnoDB;

/*==============================================================*/
/* Table: PRO_USER                                              */
/*==============================================================*/
create table PRO_USER
(
   USER_ID                        varchar(36)                    not null,
   USERNAME                       varchar(256)                   not null,
   PASSWORD                       varchar(256)                   not null,
   ENABLED                        int                            not null,
   primary key (USER_ID)
)
comment = "用户"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_USER_AUTH                                         */
/*==============================================================*/
create table PRO_USER_AUTH
(
   USER_ID                        varchar(36)                    not null,
   AUTH_ID                        varchar(36)                    not null,
   primary key (USER_ID, AUTH_ID)
)
comment = "用户权限关系"
type = InnoDB;

/*==============================================================*/
/* Table: PRO_USER_LOG                                          */
/*==============================================================*/
create table PRO_USER_LOG
(
   USER_LOG_ID                    varchar(36)                    not null,
   USER_LOG_TYPE                  varchar(4),
   CONTENT                        varchar(1024),
   USERID                         varchar(36),
   USERNAME                       varchar(256),
   CREATETIME                     varchar(32),
   primary key (USER_LOG_ID)
)
type = InnoDB;

