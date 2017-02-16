-- 创建数据库
create database d_homeDecoration
on primary 
 (name='d_homeDecoration', filename='d:\HomeDecoration.mdf', size=6mb, maxsize=unlimited, filegrowth=2mb)
log on
 (name='d_homeDecoration_log', filename='d:\HomeDecoration_log.ldf', size=4mb, maxsize=2tb, filegrowth=10%);

-- 定位到指定数据库
use d_homeDecoration;

-- 新建装修公司表
create table t_company(
	id uniqueidentifier primary key default newid(), -- 编号
	email varchar(100) not null, -- 邮箱
	password varchar(200) not null, -- 密码
	name varchar(100) not null, -- 公司名称
	principal varchar(10) not null, -- 负责人
	logo varchar(500), -- 公司logo图片地址
	address varchar(100) not null, -- 地址
	phone varchar(11) not null, -- 手机号
	tel varchar(11), -- 固定电话
	open_date datetime not null, -- 成立日期
	longitude float, -- 经度 
	latitude float, -- 纬度
	des varchar(500), -- 描述
	created_time datetime not null, -- 创建时间
	checked char(1) not null, -- 是否审核，Y表示通过，N表示为审核
	checked_time datetime not null, -- 审核时间
	last_login_time datetime not null, -- 最近一次登入时间
	login_count int not null, -- 登录次数
	status char(1) not null -- 是否可以Y表示可用，N表示不可用
);
-- 给装修公司添加相关的约束
alter table t_company add constraint UQ_t_company_email unique(email); -- 给邮箱添加唯一约束
alter table t_company add constraint DF_t_company_logo default 'images/logo.png' for logo; -- 给logo添加默认约束
alter table t_company add constraint CK_t_company_checked check(checked = 'Y' or checked = 'N'); -- 给checked添加检查约束
alter table t_company add constraint DF_t_company_checked default 'N' for checked; -- 给checked添加默认约束
alter table t_company add constraint CK_t_company_status check(status = 'Y' or status = 'N'); -- 给status添加检查约束
alter table t_company add constraint DF_t_company_status default 'N' for status; -- 给status添加默认约束

-- 给装修公司插入调试数据
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('121@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '危总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('122@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '王总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('124@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '陈总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'Y', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('125@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '邱总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('129@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '沙总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'Y');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('128@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '李总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('129@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '李总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('138@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '公司名称', '李总', 'images/logo.png', '赣州', '18279700225', '18279700002', getdate(), 12.3, 12.4, '赣州最大的公司', getdate(), 'N', getdate(), getdate(), 0, 'N');

-- 装修公司表java中sql的测试
select top 5 * from t_company where id not in(select top 0 id from t_company) and checked = 'N'; -- 查询前5条记录，并且是未审核公司的记录
update t_company set checked = 'Y' where email = '121@qq.com';
select * from t_company;

-- 新建建材商表
create table t_supply(
	id uniqueidentifier primary key default newid(), -- 编号
	email varchar(100) not null, -- 邮箱
	password varchar(200) not null, -- 密码
	name varchar(100) not null, -- 名称
	principal varchar(10) not null, -- 负责人
	logo varchar(500), -- logo图片地址
	address varchar(100) not null, -- 地址
	phone varchar(11) not null, -- 手机号
	tel varchar(11), -- 固定电话
	open_date datetime not null, -- 成立日期
	longitude float, -- 经度 
	latitude float, -- 纬度
	des varchar(500), -- 描述
	created_time datetime not null, -- 创建时间
	checked char(1) not null, -- 是否审核，Y表示通过，N表示为审核
	checked_time datetime not null, -- 审核时间
	last_login_time datetime not null, -- 最近一次登入时间
	login_count int not null, -- 登录次数
	status char(1) not null -- 是否可以Y表示可用，N表示不可用
);
-- 给建材商表添加相关约束
alter table t_supply add constraint UQ_t_supply_email unique(email); -- 给邮箱添加唯一约束
alter table t_supply add constraint DF_t_supply_logo default 'images/logo.png' for logo; -- 给logo添加默认约束
alter table t_supply add constraint CK_t_supply_checked check(checked = 'Y' or checked = 'N'); -- 给checked添加检查约束
alter table t_supply add constraint DF_t_supply_checked default 'N' for checked; -- 给checked添加默认约束
alter table t_supply add constraint CK_t_supply_status check(status = 'Y' or status = 'N'); -- 给status添加检查约束
alter table t_supply add constraint DF_t_supply_status default 'Y' for status; -- 给status添加默认约束

-- 新建设计师表
create table t_designer(
	id uniqueidentifier primary key default newid(), -- 编号
	email varchar(100) not null, -- 邮箱
	password varchar(200) not null, -- 密码
	name varchar(100) not null, -- 设计师名称
	headicon varchar(500), -- 设计师头像
	phone varchar(11) not null, -- 手机号
	address varchar(100) not null, -- 地址
	experience varchar(50), -- 工作经验
	style varchar(100), -- 擅长风格
	des varchar(500), -- 描述
	create_time datetime not null, -- 创建时间
	checked char(1) not null, -- 是否审核，Y表示通过，N表示为审核
	checked_time datetime not null, -- 审核时间
	last_login_time datetime not null, -- 最近一次登入时间
	login_count int not null, -- 登录次数
	status char(1) not null -- 是否可以Y表示可用，N表示不可用
);

-- 给设计师表添加相关约束
alter table t_designer add constraint UQ_t_designer_email unique(email); -- 给邮箱添加唯一约束
alter table t_designer add constraint CK_t_designer_checked check(checked = 'Y' or checked = 'N'); -- 给checked添加检查约束
alter table t_designer add constraint DF_t_designer_checked default 'N' for checked; -- 给checked添加默认约束
alter table t_designer add constraint CK_t_designer_status check(status = 'Y' or status = 'N'); -- 给status添加检查约束
alter table t_designer add constraint DF_t_designer_status default 'Y' for status; -- 给status添加默认约束

-- 给设计师插入调试数据
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('123@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师1', 'images/15.jpg', '18279800225', '赣州', '1-3年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('124@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师2', 'images/15.jpg', '18279800225', '南昌', '1-3年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('125@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师3', 'images/15.jpg', '18279800225', '石城', '2-3年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('126@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师4', 'images/15.jpg', '18279800225', '于都', '2-3年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('127@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师5', 'images/15.jpg', '18279800225', '赣州', '1-5年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('128@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师6', 'images/15.jpg', '18279800225', '赣州', '1-3年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('129@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '设计师7', 'images/15.jpg', '18279800225', '赣州', '1-3年', '地中海风格', '设计师介绍', getdate(), 'N', getdate(), getdate(), 0, 'N');

-- java端代码测试
select * from t_designer where id = '99de7be2-90e8-4123-abf6-1e7ec578545c';

-- 新建装修公司案例表
create table t_company_case(
	id uniqueidentifier primary key default newid(), -- 编号
	company_id uniqueidentifier not null, -- 装修公司编号
	name varchar(100) not null, -- 案例名称
	plot_name varchar(100) not null, -- 小区名称
	style varchar(20) not null, -- 装修风格
	image_1 varchar(500), -- 图片1路径
	image_2 varchar(500), -- 图片2路径
	image_3 varchar(500), -- 图片3路径
	image_4 varchar(500), -- 图片4路径
	image_5 varchar(500), -- 图片5路径
	des varchar(500), -- 描述
	created_time datetime not null -- 创建时间
);
-- 给装修公司案例表添加相关约束
alter table t_company_case add constraint FK_t_company_case_t_company_id foreign key(company_id) references t_company(id); -- 添加外键关联

-- 给装修公司案例表插入调试数据
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0a0cef8a-6885-4057-b46b-254a541504f4', '案例名称', '平安小区', '地中海风格', 'images/w-2.jpg', 'images/w-2.jpg', 'images/w-2.jpg', 'images/w-2.jpg', 'images/w-2.jpg', '案例介绍', getdate());

-- 下面是装修公司案例表添加调试数据
select * from t_company; -- 先把装修公司查出来
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '平安小区案例', '平安小区', '地中海风格', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '案例介绍', getdate());

-- 下面是装修公司案例java端sql语句测试
select top 4 * from t_company_case where id not in(select top 0 id from t_company_case); -- 做分页查询
select * from t_supply;

-- 新建设计师案例表
create table t_designer_case(
	id uniqueidentifier primary key default newid(), -- 编号
	designer_id uniqueidentifier not null,
	name varchar(100) not null,
	plot_name varchar(100) not null, -- 小区名称
	style varchar(20) not null, -- 装修风格
	image_1 varchar(500), -- 图片1路径
	image_2 varchar(500), -- 图片2路径
	image_3 varchar(500), -- 图片3路径
	image_4 varchar(500), -- 图片4路径
	image_5 varchar(500), -- 图片5路径
	des varchar(500), -- 描述
	created_time datetime not null -- 创建时间
);
-- 给设计师表添加相关约束
alter table t_designer_case add constraint FK_t_designer_case_t_designer_id foreign key(designer_id) references t_designer(id); -- 添加外键关联

-- 给设计师案例表添加调试数据
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('3e5d7177-667a-4dfd-b51f-1c40b52ae42f', '案例名称', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('565e1f41-1f0c-4c96-bc05-59c53754975d', '案例名称1', '阳光小区1', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('565e1f41-1f0c-4c96-bc05-59c53754975d', '案例名称2', '阳光小区2', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('e35023d7-0c0d-4578-8c2c-5887d281be1d', '案例名称3', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('3e5d7177-667a-4dfd-b51f-1c40b52ae42f', '案例名称4', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '案例名称5', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '案例名称6', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '案例名称7', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '案例名称8', '阳光小区', '意大利风格', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '案例介绍', getdate());

-- 装修公司活动表
create table t_company_activity(
	id uniqueidentifier primary key default newid(), -- 编号
	company_id uniqueidentifier not null, -- 装修公司编号
	name varchar(100) not null, -- 活动名称
	image varchar(500), -- 活动图片路径
	des varchar(500), -- 描述
	created_time datetime not null -- 创建时间
);
-- 给装修公司活动表添加相关约束
alter table t_company_activity add constraint FK_t_company_activity_t_company_id foreign key(company_id) references t_company(id); -- 添加外键关联

-- 给装修公司活动表插入调试数据
insert into t_company_activity(company_id, name, image, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '活动1', '<%=path %>/images/15.jpg', '活动描述1', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '活动2', '<%=path %>/images/15.jpg', '活动描述2', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '活动3', '<%=path %>/images/15.jpg', '活动描述3', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '活动4', '<%=path %>/images/15.jpg', '活动描述4', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '活动5', '<%=path %>/images/15.jpg', '活动描述5', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '活动6', '<%=path %>/images/15.jpg', '活动描述6', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '活动7', '<%=path %>/images/15.jpg', '活动描述7', getdate());

-- 新建建材商活动表
create table t_supply_activity(
	id uniqueidentifier primary key default newid(), -- 编号
	supply_id uniqueidentifier not null, -- 建材商编号
	name varchar(100) not null, -- 案例名称
	image varchar(500), -- 活动图片路径
	des varchar(500), -- 描述
	created_time datetime not null -- 创建时间
);
-- 给建材商活动表添加相关约束
alter table t_supply_activity add constraint FK_t_supply_activity_t_supply_id foreign key(supply_id) references t_supply(id); -- 添加外键关联

-- 给建材商活动表插入调试数据
insert into t_supply_activity(supply_id, name, image, des, created_time) values('27baafdb-2a74-4891-b614-bad450aec6a4', '活动1', 'images/1.jpg', '介绍', getdate());
insert into t_supply_activity(supply_id, name, image, des, created_time) values('27baafdb-2a74-4891-b614-bad450aec6a4', '活动2', 'images/1.jpg', '介绍', getdate());

-- 新建建材商品表
create table t_product(
	id uniqueidentifier primary key default newid(), -- 编号
	supply_id uniqueidentifier not null, -- 建材商编号
	name varchar(100) not null, -- 商品名称
	price float not null, -- 商品价格
	sale_price float not null, -- 折后价格
	image varchar(500), -- 商品图片地址
	des varchar(500), -- 描述
	created_time datetime not null, -- 创建时间
	status char(1) not null -- 是否可用，Y表示上架，N表示下架
);
-- 给建材表添加相关约束
alter table t_product add constraint FK_t_product_t_supply_id foreign key(supply_id) references t_supply(id); -- 添加外键约束
alter table t_product add constraint CK_t_product_status check(status = 'Y' or status = 'N'); -- 给status添加检查约束
alter table t_product add constraint DF_t_product_status default 'Y' for status; -- 给status添加默认约束

-- 给建材表java端代码调试
select top 4 * from t_product;

-- 给建材表插入调试数据
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('c27b0ba9-b6c4-49a8-9251-39a152dd9f03', '商品名称', '10.4', '9.0', 'images/15.jpg', '产品介绍', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('c27b0ba9-b6c4-49a8-9251-39a152dd9f03', '商品名称2', '10.4', '9.0', 'images/15.jpg', '产品介绍', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('c27b0ba9-b6c4-49a8-9251-39a152dd9f03', '商品名称3', '10.4', '9.0', 'images/15.jpg', '产品介绍', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('4f236ad9-85c4-47f9-9936-754b40b25375', '商品名称4', '10.4', '9.0', 'images/15.jpg', '产品介绍', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('4f236ad9-85c4-47f9-9936-754b40b25375', '商品名称5', '10.4', '9.0', 'images/15.jpg', '产品介绍', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('4f236ad9-85c4-47f9-9936-754b40b25375', '商品名称6', '10.4', '9.0', 'images/15.jpg', '产品介绍', getdate(), 'Y');

-- 新建用户表
create table t_customer(
	id uniqueidentifier primary key default newid(), -- 编号
	email varchar(100) not null, -- 邮箱
	password varchar(200) not null, -- 密码
	name varchar(100) not null, -- 名称
	phone varchar(11) not null, -- 手机号
	plot_name varchar(100), -- 小区名称
	address varchar(100), -- 地址
	created_time datetime not null, -- 创建时间
	last_login_time datetime not null, -- 最近一次登入时间
	login_count int not null, -- 登录次数
	status char(1) not null -- 是否可以Y表示可用，N表示不可用
);
-- 给用户表添加相关约束
alter table t_customer add constraint UQ_t_customer_email unique(email); -- 给邮箱添加唯一约束
alter table t_customer add constraint CK_t_customer_status check(status = 'Y' or status = 'N'); -- 给status添加检查约束
alter table t_customer add constraint DF_t_customer_status default 'Y' for status; -- 给status添加默认约束

-- 给用户表插入调试数据
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('123@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '阳光小区', '赣州', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('456@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '阳光小区', '赣州', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('789@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '阳光小区', '赣州', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('124@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '阳光小区', '赣州', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('125@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '阳光小区', '赣州', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('126@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '阳光小区', '赣州', getdate(), getdate(), 1, 'Y');

-- java端代码验证
select * from t_customer;

-- 新建装修公司收藏表
create table t_company_col(
	id uniqueidentifier primary key default newid(), -- 编号
	customer_id uniqueidentifier not null, -- 用户编号
	company_id uniqueidentifier not null, -- 装修公司编号
	created_time datetime not null, -- 创建时间
);
-- 给装修公司收藏表添加相关约束
alter table t_company_col add constraint FK_t_company_col_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键约束
alter table t_company_col add constraint FK_t_company_col_t_company_id foreign key(company_id) references t_company(id); -- 添加外键约束

-- 给公司收藏表插入调试数据
insert into t_company_col(customer_id, company_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c80aff7f-b5ae-4faf-b9fa-051652fd50b5', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c80aff7f-b5ae-4faf-b9fa-051652fd50b5', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c80aff7f-b5ae-4faf-b9fa-051652fd50b5', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c80aff7f-b5ae-4faf-b9fa-051652fd50b5', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c80aff7f-b5ae-4faf-b9fa-051652fd50b5', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c80aff7f-b5ae-4faf-b9fa-051652fd50b5', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('8f2d9ae4-07ee-44a9-ac9d-652a0e937c6f', '0ee1c8cb-20c4-4198-ba49-06e1c4c61a7f', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('8f2d9ae4-07ee-44a9-ac9d-652a0e937c6f', '0ee1c8cb-20c4-4198-ba49-06e1c4c61a7f', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('9fe6c883-b3de-4acd-a932-66d33b8381b0', '251ff08f-43bf-49d0-b4a3-c90cfdf9b20b', getdate());
insert into t_company_col(customer_id, company_id, created_time) values('9fe6c883-b3de-4acd-a932-66d33b8381b0', '251ff08f-43bf-49d0-b4a3-c90cfdf9b20b', getdate());

-- java端的代码测试
select top 5 * from t_company_col where id not in(select top 0 id from t_company_col) and customer_id = '1f05ee0a-73f4-4305-a4bd-c4518d726ddc';


-- 新建建材商收藏表
create table t_supply_col(
	id uniqueidentifier primary key default newid(), -- 编号
	customer_id uniqueidentifier not null, -- 用户编号
	supply_id uniqueidentifier not null, -- 建材商编号
	created_time datetime not null, -- 创建时间
);
-- 给建材商收藏表添加相关约束
alter table t_supply_col add constraint FK_t_supply_col_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键约束
alter table t_supply_col add constraint FK_t_supply_col_t_supply_id foreign key(supply_id) references t_supply(id); -- 添加外键约束

-- 给建材商收藏表添加调试数据
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c27b0ba9-b6c4-49a8-9251-39a152dd9f03', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c27b0ba9-b6c4-49a8-9251-39a152dd9f03', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c27b0ba9-b6c4-49a8-9251-39a152dd9f03', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());

-- 新建设计师收藏表
create table t_designer_col(
	id uniqueidentifier primary key default newid(), -- 编号
	customer_id uniqueidentifier not null, -- 用户编号
	designer_id uniqueidentifier not null, -- 设计师编号
	created_time datetime not null, -- 创建时间
);
-- 给设计师收藏表添加相关约束
alter table t_designer_col add constraint FK_t_designer_col_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键约束
alter table t_designer_col add constraint FK_t_designer_col_t_designer_id foreign key(designer_id) references t_designer(id); -- 添加外键约束

-- 给设计师收场表添加调试数据
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '3e5d7177-667a-4dfd-b51f-1c40b52ae42f', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '3e5d7177-667a-4dfd-b51f-1c40b52ae42f', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '152f4543-42de-49e6-bfeb-49536dec9bc7', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '152f4543-42de-49e6-bfeb-49536dec9bc7', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e35023d7-0c0d-4578-8c2c-5887d281be1d', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e35023d7-0c0d-4578-8c2c-5887d281be1d', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '565e1f41-1f0c-4c96-bc05-59c53754975d', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '565e1f41-1f0c-4c96-bc05-59c53754975d', getdate());

-- 新建建材收藏表
create table t_product_col(
	id uniqueidentifier primary key default newid(), -- 编号
	customer_id uniqueidentifier not null, -- 用户编号
	product_id uniqueidentifier not null, -- 建材编号
	created_time datetime not null, -- 创建时间
);
-- 给建材收藏表添加相关约束
alter table t_product_col add constraint FK_t_product_col_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键约束
alter table t_product_col add constraint FK_t_product_col_t_product_id foreign key(product_id) references t_product(id); -- 添加外键约束

select * from t_designer_col;
-- 给建材商品插入调试数据
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '01196a0a-41f5-4a91-9b2c-606e47ff11cc', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '01196a0a-41f5-4a91-9b2c-606e47ff11cc', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '90e79e94-3ee8-4644-8c1f-aedaf6d627b5', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '90e79e94-3ee8-4644-8c1f-aedaf6d627b5', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '90e79e94-3ee8-4644-8c1f-aedaf6d627b5', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '150311f0-5d4d-4a2a-84d1-e5faf182b619', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '150311f0-5d4d-4a2a-84d1-e5faf182b619', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '150311f0-5d4d-4a2a-84d1-e5faf182b619', getdate());

-- 新建装修公司案例收藏表
create table t_company_case_col(
	id uniqueidentifier primary key default newid(), -- 编号
	customer_id uniqueidentifier not null, -- 用户编号
	case_id uniqueidentifier not null, -- 装修公司案例编号
	created_time datetime not null, -- 创建时间
);
-- 给装修公司案例表添加相关约束
alter table t_company_case_col add constraint FK_t_company_case_col_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键约束
alter table t_company_case_col add constraint FK_t_company_case_col_t_company_case_id foreign key(case_id) references t_company_case(id); -- 添加外键约束

-- 给装修公司案例收藏表添加调试数据
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'debd5a7e-6ccf-424f-9b63-2d3ecf232fc5', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'debd5a7e-6ccf-424f-9b63-2d3ecf232fc5', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4d1eb254-758b-4325-b999-3277d37ec530', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4d1eb254-758b-4325-b999-3277d37ec530', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4d1eb254-758b-4325-b999-3277d37ec530', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e3d6b0c5-705b-4d90-bfa5-33255b409c20', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e3d6b0c5-705b-4d90-bfa5-33255b409c20', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '9844438b-6a0f-4c0f-842f-78ad8f7b65ea', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '9844438b-6a0f-4c0f-842f-78ad8f7b65ea', getdate());

-- 新建设计师案例收藏表
create table t_designer_case_col(
	id uniqueidentifier primary key default newid(), -- 编号
	customer_id uniqueidentifier not null, -- 用户编号
	case_id uniqueidentifier not null, -- 设计师案例编号
	created_time datetime not null, -- 创建时间
);
-- 给设计师案例表添加相关约束
alter table t_designer_case_col add constraint FK_t_designer_case_col_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键约束
alter table t_designer_case_col add constraint FK_t_designer_case_col_t_designer_case_id foreign key(case_id) references t_designer_case(id); -- 添加外键约束

-- 给设计师案例收藏表添加调试数据
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '527dd62f-850d-405b-a44b-2d092586ef5b', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '527dd62f-850d-405b-a44b-2d092586ef5b', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '6945e907-790a-4c0a-b8ce-5b16a404ff70', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '6945e907-790a-4c0a-b8ce-5b16a404ff70', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '29a794e8-40e2-43a2-acf3-9c6309d2ec75', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '29a794e8-40e2-43a2-acf3-9c6309d2ec75', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'a1a16bd8-b2c5-463c-97e9-d6544ef7af8b', getdate());

-- 用户预约表
create table t_appointment(
	id uniqueidentifier primary key default newid(), -- 编号
	company_id uniqueidentifier not null, -- 预约的装修公司编号
	name varchar(50) not null, -- 称呼
	phone varchar(11) not null, -- 手机号
	plot_name varchar(100) not null, -- 小区名称
	area float not null, -- 建筑面积
	way varchar(10) not null, -- 装修方式
	budget varchar(20) not null, -- 装修预算
	created_time datetime not null -- 创建时间
);
-- 给用户表添加相关约束
alter table t_appointment add constraint CK_t_appointment_way check(way = 'whole' or way = 'half'); -- 给way添加检查约束
alter table t_appointment add constraint FK_t_company_t_appointment_company_id foreign key(company_id) references t_company(id);

-- 预约查看记录表
create table t_appointment_view(
	id uniqueidentifier primary key default newid(), -- 编号
	app_id uniqueidentifier not null, -- 预约编号
	company_id uniqueidentifier not null, -- 装修公司编号
	created_time datetime not null -- 创建时间
);
-- 给预约表添加相关约束
alter table t_appointment_view add constraint FK_t_appointment_t_appointment_id foreign key(app_id) references t_appointment(id); -- 添加外键约束
alter table t_appointment_view add constraint FK_t_appointment_t_company_id foreign key(company_id) references t_company(id); -- 添加外键约束

-- 给预约查看表插入调试数据
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '73fca101-a21d-4fd3-8024-1e277eaa1e09', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '0a0cef8a-6885-4057-b46b-254a541504f4', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '2ded2438-4f6c-4778-b448-52806416bd80', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', 'f3565c3b-70e6-4101-aa55-59d6e523f3a6', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '83f37fa1-c412-47a1-85a0-64f0b6add183', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', 'a1ce80e9-a421-4baf-ad69-76ab362c4ac6', getdate());


delete t_appointment_view where id = 'A5F63F62-7F7C-4430-ADDF-2B55DFA43812';
-- java短的代码测试
select * from t_appointment_view;
select * from t_appointment_view where app_id = 'B7A0ECCD-A253-4ACB-AA28-326A8C082C6A' and company_id = '73FCA101-A21D-4FD3-8024-1E277EAA1E09';
select count(id) from t_appointment_view where app_id = 'B7A0ECCD-A253-4ACB-AA28-326A8C082C6A';
select count(id) from t_appointment_view where company_id = '0a0cef8a-6885-4057-b46b-254a541504f4';

-- 添加用户评论表
create table t_comment (
	id uniqueidentifier primary key default newid(),--编号
	customer_id uniqueidentifier not null, --外键用户编号
	content varchar(500),--用户评论信息
	created_time datetime not null --创建时间
)

-- 给用户评论表添加相关的约束
alter table t_comment add constraint FK_t_comment_t_customer_id foreign key(customer_id) references t_customer(id); -- 添加外键关联

-- 给用户评论表插入调试数据
select top 3 * from t_comment order by created_time desc;
insert into t_comment(customer_id, content, created_time) values('3BD59CF3-339E-4A39-9A59-213617ED2E33', '非常好的一个装修公司，非常尽责', getdate());
insert into t_comment(customer_id, content, created_time) values('4D378C74-19A3-492D-9BBF-2BB464DBCE86', '公司服务态度好，非常满意', getdate());
insert into t_comment(customer_id, content, created_time) values('14110A8C-CFCD-42DA-A87D-70C7778AF860', '质量不行，差评', getdate());

-- 新建管理员表
create table t_admin(
	id uniqueidentifier primary key default newid(), -- 编号
	email varchar(100) not null, -- 邮箱
	password varchar(200) not null, -- 密码
	name varchar(100) not null, -- 名称
	phone varchar(11) not null, -- 手机号
	role varchar(10) not null, -- 角色
	created_time datetime not null, -- 创建时间
	last_login_time datetime not null, -- 最近一次登入时间
	login_count int not null, -- 登录次数
	status char(1) not null -- 是否可以Y表示可用，N表示不可用
);
-- 给管理员表添加相关约束
alter table t_admin add constraint UQ_t_admin_email unique(email); -- 给邮箱添加唯一约束
alter table t_admin add constraint CK_t_admmin_role check(role = 'super' or role = 'normal'); -- 给role添加检查约束
alter table t_admin add constraint CK_t_admin_status check(status = 'Y' or status = 'N'); -- 给status添加检查约束
alter table t_admin add constraint DF_t_admin_status default 'Y' for status; -- 给status添加默认约束
insert into t_admin(email, password, name, phone, role, created_time, last_login_time, login_count, status) values('Admin@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', 'Admin', '18279700225', 'super', getdate(), getdate(), 1, 'Y');

-- 下面是java程序sql代码检查：
select * from t_admin; -- 查询所有管理员
select * from t_admin where name = 'Admin' and password = 'ICy5YqxZB1uWSwcVLSNLcA=='; -- 查询指定管理员的信息
update t_admin set password = '4QrcOUm6Wau+VuBX8g+IPg==' where name = 'Admin'; -- 修改指定名称的管理员信息
select count(id) from t_admin; -- 查询管理员表有多少条记录
select top 1 * from t_admin where id not in(select top 0 id from t_admin); -- 查询第一行的记录
select * from t_admin where email = 'Wjhsmart@qq.com'; -- 根据邮箱查询数据
delete from t_admin where email = 'admin@qq.com'; -- 根据邮箱删除数据


select * from t_company
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '6DECE320-77D7-4667-961D-24BF6DC70D1E', getdate())
select top 6 * from t_appointment where id 
not in(select top 0 id from t_appointment where id not in(select app_id from t_appointment_view))
and id not in(select app_id from t_appointment_view)