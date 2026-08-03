CREATE DATABASE IF NOT EXISTS workbench
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

USE workbench;

-- =============================
-- 用户表
-- =============================
DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user
(
    id              BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID 主键',

    user_name        VARCHAR(30) NOT NULL COMMENT '用户名  唯一索引',

    nick_name        VARCHAR(30) NOT NULL COMMENT '昵称',

    password        VARCHAR(255) NOT NULL COMMENT '密码',

    avatar          VARCHAR(255) DEFAULT NULL COMMENT '头像',

    email           VARCHAR(50) DEFAULT NULL COMMENT '邮箱 普通索引',

    phone           VARCHAR(20) DEFAULT NULL COMMENT '手机号 普通索引',

    gender          TINYINT DEFAULT 0 COMMENT '性别(0未知 1男 2女)',

    birthday        INT UNSIGNED DEFAULT NULL COMMENT '生日',

    signature       VARCHAR(255) DEFAULT NULL COMMENT '个性签名',

    theme           VARCHAR(20) DEFAULT 'light' COMMENT '主题 dark light',

    create_time     INT UNSIGNED DEFAULT 0 COMMENT '创建时间',

    update_time     INT UNSIGNED DEFAULT 0 COMMENT '更新时间',

    deleted         TINYINT(1) DEFAULT 0 COMMENT '逻辑删除 0 不删除  1 删除',

    PRIMARY KEY (id),

    UNIQUE KEY uk_user_name(user_name),

    KEY idx_email(email),

    KEY idx_phone(phone)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


-- =============================
-- 账户表
-- =============================
DROP TABLE IF EXISTS tb_bill_account;

CREATE TABLE tb_bill_account
(
    id              BIGINT NOT NULL AUTO_INCREMENT COMMENT '账户ID 主键索引',

    user_id         BIGINT NOT NULL COMMENT '所属用户 普通索引',

    name            VARCHAR(50) NOT NULL COMMENT '账户名称',

    balance         DECIMAL(10,2) DEFAULT 0.00 COMMENT '余额',

    icon            VARCHAR(255) DEFAULT NULL COMMENT '图标',

    create_time     INT UNSIGNED DEFAULT 0 COMMENT '创建时间',

    update_time     INT UNSIGNED DEFAULT 0 COMMENT '更新时间',

    deleted         TINYINT(1) DEFAULT 0 COMMENT '逻辑删除 0 不删除  1 删除',

    PRIMARY KEY(id),

    KEY idx_user_id(user_id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表';


-- =============================
-- 分类表
-- =============================
DROP TABLE IF EXISTS tb_bill_category;

CREATE TABLE tb_bill_category
(
    id              BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类ID 主键索引',

    user_id         BIGINT NOT NULL COMMENT '所属用户 普通索引',

    name            VARCHAR(50) NOT NULL COMMENT '分类名称',

    type            TINYINT NOT NULL COMMENT '1收入 2支出  普通索引',

    icon            VARCHAR(255) DEFAULT NULL COMMENT '图标',

    create_time     INT UNSIGNED DEFAULT 0 COMMENT '创建时间',

    update_time     INT UNSIGNED DEFAULT 0 COMMENT '更新时间',

    deleted         TINYINT(1) DEFAULT 0 COMMENT '逻辑删除 0 不删除  1 删除',

    PRIMARY KEY(id),

    KEY idx_user_id(user_id),

    KEY idx_type(type)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单分类';


-- =============================
-- 账单表
-- =============================
DROP TABLE IF EXISTS tb_bill;

CREATE TABLE tb_bill
(
    id              BIGINT NOT NULL AUTO_INCREMENT COMMENT '账单ID  主键索引',

    user_id         BIGINT NOT NULL COMMENT '所属用户  普通索引',

    category_id     BIGINT NOT NULL COMMENT '分类ID  普通索引 逻辑外键关联分类表id',

    account_id      BIGINT NOT NULL COMMENT '账户ID  普通索引 逻辑外键关联账户表id',

    type            TINYINT NOT NULL COMMENT '1收入 2支出  普通索引',

    amount          DECIMAL(10,2) NOT NULL COMMENT '金额',

    remark          VARCHAR(500) DEFAULT NULL COMMENT '备注',

    bill_date       INT UNSIGNED  COMMENT '账单时间  普通索引',

    create_time     INT UNSIGNED DEFAULT 0 COMMENT '创建时间',

    update_time     INT UNSIGNED DEFAULT 0 COMMENT '更新时间',

    deleted         TINYINT(1) DEFAULT 0 COMMENT '逻辑删除 0 不删除  1 删除',

    PRIMARY KEY(id),

    KEY idx_user_id(user_id),

    KEY idx_category_id(category_id),

    KEY idx_account_id(account_id),

    KEY idx_bill_date(bill_date),

    KEY idx_type(type)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账单表';


-- =============================
-- 纪念日表
-- =============================
DROP TABLE IF EXISTS tb_anniversary;

CREATE TABLE tb_anniversary
(
    id                  BIGINT NOT NULL AUTO_INCREMENT COMMENT '纪念日ID 主键',

    user_id             BIGINT NOT NULL COMMENT '所属用户  普通索引',

    title               VARCHAR(100) NOT NULL COMMENT '标题',

    anniversary_date    INT UNSIGNED NOT NULL COMMENT '纪念日期  普通索引',

    type                TINYINT DEFAULT 1 COMMENT '1纪念日 2生日 3节日',

    repeat_flag           TINYINT(1) DEFAULT 1 COMMENT '是否每年重复  1是 2否',

    icon                VARCHAR(255) DEFAULT NULL COMMENT '图标',

    color               VARCHAR(20) DEFAULT NULL COMMENT '颜色',

    remark              VARCHAR(500) DEFAULT NULL COMMENT '备注',

    create_time         INT UNSIGNED DEFAULT 0 COMMENT '创建时间',

    update_time         INT UNSIGNED DEFAULT 0 COMMENT '更新时间',

    deleted             TINYINT(1) DEFAULT 0 COMMENT '逻辑删除 0 不删除  1 删除',

    PRIMARY KEY(id),

    KEY idx_user_id(user_id),

    KEY idx_anniversary_date(anniversary_date),

    KEY idx_type(type)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='纪念日表';