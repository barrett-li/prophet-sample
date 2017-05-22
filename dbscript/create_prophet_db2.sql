--==============================================================
-- DBMS name:      IBM DB2 UDB 8.x Common Server
-- Created on:     2009-1-22 14:00:56
--==============================================================


drop table PRO_AUTHORITIES;

drop table PRO_BANK_LOAN;

drop table PRO_DEPARTMENT;

drop table PRO_EMPLOYEE;

drop table PRO_MENU;

drop table PRO_RESOURCE;

drop table PRO_RES_AUTH;

drop table PRO_SYSTEM_LOG;

drop table PRO_USER;

drop table PRO_USER_AUTH;

drop table PRO_USER_LOG;

--==============================================================
-- Table: PRO_AUTHORITIES
--==============================================================
create table PRO_AUTHORITIES
(
   AUTH_ID              VARCHAR(36)            not null,
   AUTHORITY            VARCHAR(256)           not null,
   DISPLAY              VARCHAR(256)           not null,
   NOTE                 VARCHAR(1024),
   constraint "P_Identifier_AUTHO" primary key (AUTH_ID)
);

comment on table PRO_AUTHORITIES is
'权限';

--==============================================================
-- Table: PRO_BANK_LOAN
--==============================================================
create table PRO_BANK_LOAN
(
   ORG                  VARCHAR(256)           not null,
   AREA                 VARCHAR(256)           not null,
   DEAD_LOANS           NUMERIC(8,2)           not null,
   OVERDUE_LOANS        NUMERIC(8,2)           not null,
   VIOLATION_LOANS      NUMERIC(8,2)           not null
);

comment on table PRO_BANK_LOAN is
'银行贷款';

--==============================================================
-- Table: PRO_DEPARTMENT
--==============================================================
create table PRO_DEPARTMENT
(
   DEPT_ID              VARCHAR(36)            not null,
   FULLNAME             VARCHAR(256)           not null,
   SHORTNAME            VARCHAR(256)           not null,
   REMARK               VARCHAR(1024),
   constraint "P_Identifier_DEPAR" primary key (DEPT_ID)
);

comment on table PRO_DEPARTMENT is
'部门';

--==============================================================
-- Table: PRO_EMPLOYEE
--==============================================================
create table PRO_EMPLOYEE
(
   EMPLOYEE_ID          VARCHAR(36)            not null,
   DEPT_ID              VARCHAR(36)            not null,
   EMPLOYEE_NAME        VARCHAR(256)           not null,
   EMAIL                VARCHAR(256),
   MOBILE               VARCHAR(256),
   constraint "P_Identifier_EMPLO" primary key (EMPLOYEE_ID)
);

comment on table PRO_EMPLOYEE is
'员工';

--==============================================================
-- Table: PRO_MENU
--==============================================================
create table PRO_MENU
(
   MENU_ID              VARCHAR(36)            not null,
   PARENT_ID            VARCHAR(36)            not null,
   "NAME"               VARCHAR(256)           not null,
   NOTE                 VARCHAR(1024),
   HREF                 VARCHAR(1024),
   SORT                 VARCHAR(4)             not null,
   LEAF                 INTEGER                not null default 1,
   constraint "P_Identifier_MENU" primary key (MENU_ID)
);

comment on table PRO_MENU is
'菜单';

comment on column PRO_MENU.LEAF is
'0=否;1=是;';

--==============================================================
-- Table: PRO_RESOURCE
--==============================================================
create table PRO_RESOURCE
(
   RES_ID               VARCHAR(36)            not null,
   RES_TYPE             VARCHAR(256)           not null,
   RES_STRING           VARCHAR(1024)          not null,
   DISPLAY              VARCHAR(256)           not null,
   constraint "P_Identifier_PRO_R" primary key (RES_ID)
);

comment on table PRO_RESOURCE is
'资源';

--==============================================================
-- Table: PRO_RES_AUTH
--==============================================================
create table PRO_RES_AUTH
(
   RES_ID               VARCHAR(36)            not null,
   AUTH_ID              VARCHAR(36)            not null,
   constraint "P_Identifier_PRO_R" primary key (RES_ID, AUTH_ID)
);

comment on table PRO_RES_AUTH is
'资源权限关系';

--==============================================================
-- Table: PRO_SYSTEM_LOG
--==============================================================
create table PRO_SYSTEM_LOG
(
   SYS_LOG_ID           VARCHAR(36)            not null,
   SYS_LOG_TYPE         VARCHAR(4),
   CONTENT              VARCHAR(1024),
   SYS_LOG_RESULT       VARCHAR(4),
   CREATETIME           VARCHAR(32),
   constraint "P_Identifier_PRO_S" primary key (SYS_LOG_ID)
);

--==============================================================
-- Table: PRO_USER
--==============================================================
create table PRO_USER
(
   USER_ID              VARCHAR(36)            not null,
   USERNAME             VARCHAR(256)           not null,
   PASSWORD             VARCHAR(256)           not null,
   ENABLED              INTEGER                not null,
   constraint "P_Identifier_USER" primary key (USER_ID)
);

comment on table PRO_USER is
'用户';

--==============================================================
-- Table: PRO_USER_AUTH
--==============================================================
create table PRO_USER_AUTH
(
   USER_ID              VARCHAR(36)            not null,
   AUTH_ID              VARCHAR(36)            not null,
   constraint "P_Identifier_USER_" primary key (USER_ID, AUTH_ID)
);

comment on table PRO_USER_AUTH is
'用户权限关系';

--==============================================================
-- Table: PRO_USER_LOG
--==============================================================
create table PRO_USER_LOG
(
   USER_LOG_ID          VARCHAR(36)            not null,
   USER_LOG_TYPE        VARCHAR(4),
   CONTENT              VARCHAR(1024),
   USERID               VARCHAR(36),
   USERNAME             VARCHAR(256),
   CREATETIME           VARCHAR(32),
   constraint "P_Identifier_PRO_U" primary key (USER_LOG_ID)
);

