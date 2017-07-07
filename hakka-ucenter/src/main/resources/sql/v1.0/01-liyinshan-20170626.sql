use hakka;
#用户表
create table if not exists tu_e_user(
    id bigint(20) unsigned not null
        comment 'ID',
    user_accout varchar(50) not null
        comment '用户账户',
    user_password varchar(64) not null
        comment '用户密码',
    user_name varchar(80) not null
        comment '用户名',
    mobile_no varchar(30) not null
        comment '手机号码',
    email varchar(30) not null
        comment '邮箱地址',
    birthday timestamp not null
        comment '出生年月',
    gender tinyint unsigned not null
        comment '性别:1-男;2-女',
    dept_id bigint(20) unsigned not null
        comment '所属部门ID',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_user_accout(user_accout)
        comment '索引-用户账户',
    index idx_mobile_no(mobile_no)
        comment '索引-手机号码',
    index idx_email(email)
        comment '索引-email',
    index idx_dept_id(dept_id)
        comment '索引-部门编码'
)
    engine innoDB,
    default charset utf8mb4,
    comment '用户表';


#部门表
create table if not exists tu_e_dept(
    id bigint(20) unsigned not null auto_increment
        comment 'id',
    dept_code varchar(20) not null
        comment '部门编码:全大写,英文数字加-组成',
    dept_name varchar(200) not null
        comment '部门名称',
    dept_describe varchar(500) not null
        comment '部门描述',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_dept_code(dept_code)
        comment '索引-部门编码'
)
    engine innoDB
    default charset utf8mb4
    comment '部门表';

#角色类型表
create table if not exists tu_e_role_type(
    id bigint(20) unsigned not null auto_increment
        comment 'id',
    role_type_code varchar(20)
        comment '角色类型编码:全大写,英文数字加-组成',
    role_type_desc varchar(200)
        comment '角色类型描述',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_role_type_code(role_type_code)
        comment '索引-角色类型编码'
)
    engine innoDB
    default charset utf8mb4
    comment '角色类型表';


#角色表
create table if not exists tu_e_role(
    id bigint(20) unsigned not null
        comment 'id',
    role_code varchar(20) not null
        comment '角色编码',
    role_name varchar(200) not null
        comment '角色名称',
    role_desc varchar(400) not null
        comment '角色描述',
    role_type_id varchar(20) not null
        comment '角色类型编码',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_role_code(role_code)
        comment '索引-角色编码',
    index idx_role_type_id(role_type_id)
        comment '索引-角色类型ID'
)
    engine innoDB
    default charset utf8mb4
    comment '角色表';

#用户与角色关系表
create table if not exists tu_r_user_role(
    id bigint(20) unsigned not null
        comment 'id',
    user_id bigint(20) unsigned not null
        comment '用户ID',
    role_id bigint(20) unsigned not null
        comment '角色ID',
    start_time timestamp not null
        comment '开始时间',
    end_time timestamp not null,
        comment '结束时间',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_user_id(user_id)
        comment '索引-用户ID',
    index idx_role_id(role_id)
        comment '索引-角色ID'
)
    engine innoDB
    default charset utf8mb4
    comment '用户与角色关系表';


#部门与角色关系表
create table if not exists tu_r_dept_role(
    id bigint(20) unsigned not null
        comment 'id',
    dept_id bigint(20) unsigned not null
        comment '用户ID',
    role_id bigint(20) unsigned not null
        comment '角色ID',
    start_time timestamp not null
        comment '开始时间',
    end_time timestamp not null
        comment '结束时间',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_dept_id(dept_id)
        comment '索引-部门ID',
    index idx_role_id(role_id)
        comment '索引-角色ID'
)
    engine innoDB
    default charset utf8mb4
    comment '部门与角色关系表';


#应用程序
create table if not exists tu_e_app(
    id bigint(20) unsigned not null
        comment 'id',
    app_code varchar(20) not null
        comment '应用程序编码',
    app_name varchar(200) not null
        comment '应用程序名称',
    app_img varchar(200) not null
        comment '应用程序图标路径',
    app_desc varchar(400) not null
        comment '应用程序描述',
    app_url varchar(200) not null
        comment '应用的URL',
    access_key varchar(64) not null
        comment '访问的KEY',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_app_code(app_code)
        comment '索引-应用程序编码'
)
    engine innoDB
    default charset utf8mb4
    comment '应用程序';


#权限
create table if not exists tu_e_perm(
    id bigint(20) unsigned not null
        comment 'id',
    perm_code varchar(20) not null
        comment '权限编码',
    perm_desc varchar(200) not null
        comment '权限描述',
    app_id bigint(20) not null
        comment '权限对应的应用程序',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_perm_code(perm_code)
        comment '索引-权限编码'
)
    engine innoDB
    default charset utf8mb4
    comment '权限表';


#部门与权限关系表
create table if not exists tu_r_dept_perm(
    id bigint(20) unsigned not null
        comment 'id',
    dept_id bigint(20) unsigned not null
        comment '部门ID',
    perm_id bigint(20) unsigned not null
        comment '权限ID',
    rel_type tinyint unsigned not null
        comment '关系类型:0-没有权限;1-有权限',
    start_time timestamp not null
        comment '开始时间',
    end_time timestamp not null
        comment '结束时间',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_dept_id(dept_id)
        comment '索引-部门id'
)
    engine innoDB
    default charset utf8mb4
    comment '部门与权限关系表';

#角色与权限关系表
create table if not exists tu_r_role_perm(
    id bigint(20) unsigned not null
        comment 'id',
    role_id bigint(20) unsigned not null
        comment '角色ID',
    perm_id bigint(20) unsigned not null
        comment '权限ID',
    rel_type tinyint unsigned not null
        comment '关系类型:0-没有权限;1-有权限',
    start_time timestamp not null
        comment '开始时间',
    end_time timestamp not null
        comment '结束时间',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_role_id(role_id)
        comment '索引-角色id'
)
    engine innoDB
    default charset utf8mb4
    comment '角色与权限关系表';


#用户与权限关系表
create table if not exists tu_r_user_perm(
    id bigint(20) unsigned not null
        comment 'id',
    user_id bigint(20) unsigned not null
        comment '用户ID',
    perm_id bigint(20) unsigned not null
        comment '权限ID',
    rel_type tinyint unsigned not null
        comment '关系类型:0-没有权限;1-有权限',
    start_time timestamp not null
        comment '开始时间',
    end_time timestamp not null
        comment '结束时间',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_user_id(user_id)
        comment '索引-用户id'
)
    engine innoDB
    default charset utf8mb4
    comment '用户与权限关系表';


#操作日志
create table if not exists tu_l_optrlog(
    id bigint(20) unsigned not null
        comment 'id',
    user_id bigint(20) unsigned not null
        comment '用户ID',
    dept_id bigint(20) unsigned not null
        comment '部门ID',
    app_id bigint(20) unsigned not null
        comment '应用ID',
    main_model varchar(200) not null
        comment '应用主模块',
    sub_model varchar(200)
        comment '应用子模块',
    optr_target varchar(200) not null
        comment '操作对象',
    optr_desc varchar(400) not null
        comment '操作描述',
    optr_time timestamp not null
        comment '操作时间',
    is_delete tinyint unsigned not null
        comment '是否删除:0-否,1-是',
    gmt_create timestamp not null default current_timestamp
        comment '创建时间',
    gmt_modified timestamp not null default current_timestamp on update current_timestamp
        comment '更新时间',
    primary key (id)
        comment '主键',
    index idx_user_id(user_id)
        comment '索引-用户ID'
)
    engine innoDB
    default charset utf8mb4
    comment '操作日志';



