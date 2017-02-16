-- �������ݿ�
create database d_homeDecoration
on primary 
 (name='d_homeDecoration', filename='d:\HomeDecoration.mdf', size=6mb, maxsize=unlimited, filegrowth=2mb)
log on
 (name='d_homeDecoration_log', filename='d:\HomeDecoration_log.ldf', size=4mb, maxsize=2tb, filegrowth=10%);

-- ��λ��ָ�����ݿ�
use d_homeDecoration;

-- �½�װ�޹�˾��
create table t_company(
	id uniqueidentifier primary key default newid(), -- ���
	email varchar(100) not null, -- ����
	password varchar(200) not null, -- ����
	name varchar(100) not null, -- ��˾����
	principal varchar(10) not null, -- ������
	logo varchar(500), -- ��˾logoͼƬ��ַ
	address varchar(100) not null, -- ��ַ
	phone varchar(11) not null, -- �ֻ���
	tel varchar(11), -- �̶��绰
	open_date datetime not null, -- ��������
	longitude float, -- ���� 
	latitude float, -- γ��
	des varchar(500), -- ����
	created_time datetime not null, -- ����ʱ��
	checked char(1) not null, -- �Ƿ���ˣ�Y��ʾͨ����N��ʾΪ���
	checked_time datetime not null, -- ���ʱ��
	last_login_time datetime not null, -- ���һ�ε���ʱ��
	login_count int not null, -- ��¼����
	status char(1) not null -- �Ƿ����Y��ʾ���ã�N��ʾ������
);
-- ��װ�޹�˾�����ص�Լ��
alter table t_company add constraint UQ_t_company_email unique(email); -- ���������ΨһԼ��
alter table t_company add constraint DF_t_company_logo default 'images/logo.png' for logo; -- ��logo���Ĭ��Լ��
alter table t_company add constraint CK_t_company_checked check(checked = 'Y' or checked = 'N'); -- ��checked��Ӽ��Լ��
alter table t_company add constraint DF_t_company_checked default 'N' for checked; -- ��checked���Ĭ��Լ��
alter table t_company add constraint CK_t_company_status check(status = 'Y' or status = 'N'); -- ��status��Ӽ��Լ��
alter table t_company add constraint DF_t_company_status default 'N' for status; -- ��status���Ĭ��Լ��

-- ��װ�޹�˾�����������
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('121@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', 'Σ��', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('122@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', '����', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('124@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', '����', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'Y', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('125@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', '����', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('129@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', 'ɳ��', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'Y');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('128@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', '����', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('129@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', '����', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values('138@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '��˾����', '����', 'images/logo.png', '����', '18279700225', '18279700002', getdate(), 12.3, 12.4, '�������Ĺ�˾', getdate(), 'N', getdate(), getdate(), 0, 'N');

-- װ�޹�˾��java��sql�Ĳ���
select top 5 * from t_company where id not in(select top 0 id from t_company) and checked = 'N'; -- ��ѯǰ5����¼��������δ��˹�˾�ļ�¼
update t_company set checked = 'Y' where email = '121@qq.com';
select * from t_company;

-- �½������̱�
create table t_supply(
	id uniqueidentifier primary key default newid(), -- ���
	email varchar(100) not null, -- ����
	password varchar(200) not null, -- ����
	name varchar(100) not null, -- ����
	principal varchar(10) not null, -- ������
	logo varchar(500), -- logoͼƬ��ַ
	address varchar(100) not null, -- ��ַ
	phone varchar(11) not null, -- �ֻ���
	tel varchar(11), -- �̶��绰
	open_date datetime not null, -- ��������
	longitude float, -- ���� 
	latitude float, -- γ��
	des varchar(500), -- ����
	created_time datetime not null, -- ����ʱ��
	checked char(1) not null, -- �Ƿ���ˣ�Y��ʾͨ����N��ʾΪ���
	checked_time datetime not null, -- ���ʱ��
	last_login_time datetime not null, -- ���һ�ε���ʱ��
	login_count int not null, -- ��¼����
	status char(1) not null -- �Ƿ����Y��ʾ���ã�N��ʾ������
);
-- �������̱�������Լ��
alter table t_supply add constraint UQ_t_supply_email unique(email); -- ���������ΨһԼ��
alter table t_supply add constraint DF_t_supply_logo default 'images/logo.png' for logo; -- ��logo���Ĭ��Լ��
alter table t_supply add constraint CK_t_supply_checked check(checked = 'Y' or checked = 'N'); -- ��checked��Ӽ��Լ��
alter table t_supply add constraint DF_t_supply_checked default 'N' for checked; -- ��checked���Ĭ��Լ��
alter table t_supply add constraint CK_t_supply_status check(status = 'Y' or status = 'N'); -- ��status��Ӽ��Լ��
alter table t_supply add constraint DF_t_supply_status default 'Y' for status; -- ��status���Ĭ��Լ��

-- �½����ʦ��
create table t_designer(
	id uniqueidentifier primary key default newid(), -- ���
	email varchar(100) not null, -- ����
	password varchar(200) not null, -- ����
	name varchar(100) not null, -- ���ʦ����
	headicon varchar(500), -- ���ʦͷ��
	phone varchar(11) not null, -- �ֻ���
	address varchar(100) not null, -- ��ַ
	experience varchar(50), -- ��������
	style varchar(100), -- �ó����
	des varchar(500), -- ����
	create_time datetime not null, -- ����ʱ��
	checked char(1) not null, -- �Ƿ���ˣ�Y��ʾͨ����N��ʾΪ���
	checked_time datetime not null, -- ���ʱ��
	last_login_time datetime not null, -- ���һ�ε���ʱ��
	login_count int not null, -- ��¼����
	status char(1) not null -- �Ƿ����Y��ʾ���ã�N��ʾ������
);

-- �����ʦ��������Լ��
alter table t_designer add constraint UQ_t_designer_email unique(email); -- ���������ΨһԼ��
alter table t_designer add constraint CK_t_designer_checked check(checked = 'Y' or checked = 'N'); -- ��checked��Ӽ��Լ��
alter table t_designer add constraint DF_t_designer_checked default 'N' for checked; -- ��checked���Ĭ��Լ��
alter table t_designer add constraint CK_t_designer_status check(status = 'Y' or status = 'N'); -- ��status��Ӽ��Լ��
alter table t_designer add constraint DF_t_designer_status default 'Y' for status; -- ��status���Ĭ��Լ��

-- �����ʦ�����������
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('123@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ1', 'images/15.jpg', '18279800225', '����', '1-3��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('124@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ2', 'images/15.jpg', '18279800225', '�ϲ�', '1-3��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('125@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ3', 'images/15.jpg', '18279800225', 'ʯ��', '2-3��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('126@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ4', 'images/15.jpg', '18279800225', '�ڶ�', '2-3��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('127@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ5', 'images/15.jpg', '18279800225', '����', '1-5��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('128@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ6', 'images/15.jpg', '18279800225', '����', '1-3��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');
insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status)
	values('129@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '���ʦ7', 'images/15.jpg', '18279800225', '����', '1-3��', '���к����', '���ʦ����', getdate(), 'N', getdate(), getdate(), 0, 'N');

-- java�˴������
select * from t_designer where id = '99de7be2-90e8-4123-abf6-1e7ec578545c';

-- �½�װ�޹�˾������
create table t_company_case(
	id uniqueidentifier primary key default newid(), -- ���
	company_id uniqueidentifier not null, -- װ�޹�˾���
	name varchar(100) not null, -- ��������
	plot_name varchar(100) not null, -- С������
	style varchar(20) not null, -- װ�޷��
	image_1 varchar(500), -- ͼƬ1·��
	image_2 varchar(500), -- ͼƬ2·��
	image_3 varchar(500), -- ͼƬ3·��
	image_4 varchar(500), -- ͼƬ4·��
	image_5 varchar(500), -- ͼƬ5·��
	des varchar(500), -- ����
	created_time datetime not null -- ����ʱ��
);
-- ��װ�޹�˾������������Լ��
alter table t_company_case add constraint FK_t_company_case_t_company_id foreign key(company_id) references t_company(id); -- ����������

-- ��װ�޹�˾����������������
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0a0cef8a-6885-4057-b46b-254a541504f4', '��������', 'ƽ��С��', '���к����', 'images/w-2.jpg', 'images/w-2.jpg', 'images/w-2.jpg', 'images/w-2.jpg', 'images/w-2.jpg', '��������', getdate());

-- ������װ�޹�˾��������ӵ�������
select * from t_company; -- �Ȱ�װ�޹�˾�����
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());
insert into t_company_case(company_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', 'ƽ��С������', 'ƽ��С��', '���к����', 'images/1.png', 'images/2.png', 'images/3.png', 'images/4.png', 'images/5.png', '��������', getdate());

-- ������װ�޹�˾����java��sql������
select top 4 * from t_company_case where id not in(select top 0 id from t_company_case); -- ����ҳ��ѯ
select * from t_supply;

-- �½����ʦ������
create table t_designer_case(
	id uniqueidentifier primary key default newid(), -- ���
	designer_id uniqueidentifier not null,
	name varchar(100) not null,
	plot_name varchar(100) not null, -- С������
	style varchar(20) not null, -- װ�޷��
	image_1 varchar(500), -- ͼƬ1·��
	image_2 varchar(500), -- ͼƬ2·��
	image_3 varchar(500), -- ͼƬ3·��
	image_4 varchar(500), -- ͼƬ4·��
	image_5 varchar(500), -- ͼƬ5·��
	des varchar(500), -- ����
	created_time datetime not null -- ����ʱ��
);
-- �����ʦ��������Լ��
alter table t_designer_case add constraint FK_t_designer_case_t_designer_id foreign key(designer_id) references t_designer(id); -- ����������

-- �����ʦ��������ӵ�������
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('3e5d7177-667a-4dfd-b51f-1c40b52ae42f', '��������', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('565e1f41-1f0c-4c96-bc05-59c53754975d', '��������1', '����С��1', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('565e1f41-1f0c-4c96-bc05-59c53754975d', '��������2', '����С��2', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('e35023d7-0c0d-4578-8c2c-5887d281be1d', '��������3', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('3e5d7177-667a-4dfd-b51f-1c40b52ae42f', '��������4', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '��������5', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '��������6', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '��������7', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());
insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values('99de7be2-90e8-4123-abf6-1e7ec578545c', '��������8', '����С��', '��������', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', 'images/15.jpg', '��������', getdate());

-- װ�޹�˾���
create table t_company_activity(
	id uniqueidentifier primary key default newid(), -- ���
	company_id uniqueidentifier not null, -- װ�޹�˾���
	name varchar(100) not null, -- �����
	image varchar(500), -- �ͼƬ·��
	des varchar(500), -- ����
	created_time datetime not null -- ����ʱ��
);
-- ��װ�޹�˾���������Լ��
alter table t_company_activity add constraint FK_t_company_activity_t_company_id foreign key(company_id) references t_company(id); -- ����������

-- ��װ�޹�˾�������������
insert into t_company_activity(company_id, name, image, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '�1', '<%=path %>/images/15.jpg', '�����1', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '�2', '<%=path %>/images/15.jpg', '�����2', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '�3', '<%=path %>/images/15.jpg', '�����3', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('C80AFF7F-B5AE-4FAF-B9FA-051652FD50B5', '�4', '<%=path %>/images/15.jpg', '�����4', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '�5', '<%=path %>/images/15.jpg', '�����5', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('0EE1C8CB-20C4-4198-BA49-06E1C4C61A7F', '�6', '<%=path %>/images/15.jpg', '�����6', getdate());
insert into t_company_activity(company_id, name, image, des, created_time) values('251FF08F-43BF-49D0-B4A3-C90CFDF9B20B', '�7', '<%=path %>/images/15.jpg', '�����7', getdate());

-- �½������̻��
create table t_supply_activity(
	id uniqueidentifier primary key default newid(), -- ���
	supply_id uniqueidentifier not null, -- �����̱��
	name varchar(100) not null, -- ��������
	image varchar(500), -- �ͼƬ·��
	des varchar(500), -- ����
	created_time datetime not null -- ����ʱ��
);
-- �������̻��������Լ��
alter table t_supply_activity add constraint FK_t_supply_activity_t_supply_id foreign key(supply_id) references t_supply(id); -- ����������

-- �������̻������������
insert into t_supply_activity(supply_id, name, image, des, created_time) values('27baafdb-2a74-4891-b614-bad450aec6a4', '�1', 'images/1.jpg', '����', getdate());
insert into t_supply_activity(supply_id, name, image, des, created_time) values('27baafdb-2a74-4891-b614-bad450aec6a4', '�2', 'images/1.jpg', '����', getdate());

-- �½�������Ʒ��
create table t_product(
	id uniqueidentifier primary key default newid(), -- ���
	supply_id uniqueidentifier not null, -- �����̱��
	name varchar(100) not null, -- ��Ʒ����
	price float not null, -- ��Ʒ�۸�
	sale_price float not null, -- �ۺ�۸�
	image varchar(500), -- ��ƷͼƬ��ַ
	des varchar(500), -- ����
	created_time datetime not null, -- ����ʱ��
	status char(1) not null -- �Ƿ���ã�Y��ʾ�ϼܣ�N��ʾ�¼�
);
-- �����ı�������Լ��
alter table t_product add constraint FK_t_product_t_supply_id foreign key(supply_id) references t_supply(id); -- ������Լ��
alter table t_product add constraint CK_t_product_status check(status = 'Y' or status = 'N'); -- ��status��Ӽ��Լ��
alter table t_product add constraint DF_t_product_status default 'Y' for status; -- ��status���Ĭ��Լ��

-- �����ı�java�˴������
select top 4 * from t_product;

-- �����ı�����������
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('c27b0ba9-b6c4-49a8-9251-39a152dd9f03', '��Ʒ����', '10.4', '9.0', 'images/15.jpg', '��Ʒ����', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('c27b0ba9-b6c4-49a8-9251-39a152dd9f03', '��Ʒ����2', '10.4', '9.0', 'images/15.jpg', '��Ʒ����', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('c27b0ba9-b6c4-49a8-9251-39a152dd9f03', '��Ʒ����3', '10.4', '9.0', 'images/15.jpg', '��Ʒ����', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('4f236ad9-85c4-47f9-9936-754b40b25375', '��Ʒ����4', '10.4', '9.0', 'images/15.jpg', '��Ʒ����', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('4f236ad9-85c4-47f9-9936-754b40b25375', '��Ʒ����5', '10.4', '9.0', 'images/15.jpg', '��Ʒ����', getdate(), 'N');
insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values('4f236ad9-85c4-47f9-9936-754b40b25375', '��Ʒ����6', '10.4', '9.0', 'images/15.jpg', '��Ʒ����', getdate(), 'Y');

-- �½��û���
create table t_customer(
	id uniqueidentifier primary key default newid(), -- ���
	email varchar(100) not null, -- ����
	password varchar(200) not null, -- ����
	name varchar(100) not null, -- ����
	phone varchar(11) not null, -- �ֻ���
	plot_name varchar(100), -- С������
	address varchar(100), -- ��ַ
	created_time datetime not null, -- ����ʱ��
	last_login_time datetime not null, -- ���һ�ε���ʱ��
	login_count int not null, -- ��¼����
	status char(1) not null -- �Ƿ����Y��ʾ���ã�N��ʾ������
);
-- ���û���������Լ��
alter table t_customer add constraint UQ_t_customer_email unique(email); -- ���������ΨһԼ��
alter table t_customer add constraint CK_t_customer_status check(status = 'Y' or status = 'N'); -- ��status��Ӽ��Լ��
alter table t_customer add constraint DF_t_customer_status default 'Y' for status; -- ��status���Ĭ��Լ��

-- ���û�������������
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('123@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '����С��', '����', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('456@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '����С��', '����', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('789@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '����С��', '����', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('124@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '����С��', '����', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('125@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '����С��', '����', getdate(), getdate(), 1, 'Y');
insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values('126@qq.com', 'ICy5YqxZB1uWSwcVLSNLcA==', '123', '12323212321', '����С��', '����', getdate(), getdate(), 1, 'Y');

-- java�˴�����֤
select * from t_customer;

-- �½�װ�޹�˾�ղر�
create table t_company_col(
	id uniqueidentifier primary key default newid(), -- ���
	customer_id uniqueidentifier not null, -- �û����
	company_id uniqueidentifier not null, -- װ�޹�˾���
	created_time datetime not null, -- ����ʱ��
);
-- ��װ�޹�˾�ղر�������Լ��
alter table t_company_col add constraint FK_t_company_col_t_customer_id foreign key(customer_id) references t_customer(id); -- ������Լ��
alter table t_company_col add constraint FK_t_company_col_t_company_id foreign key(company_id) references t_company(id); -- ������Լ��

-- ����˾�ղر�����������
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

-- java�˵Ĵ������
select top 5 * from t_company_col where id not in(select top 0 id from t_company_col) and customer_id = '1f05ee0a-73f4-4305-a4bd-c4518d726ddc';


-- �½��������ղر�
create table t_supply_col(
	id uniqueidentifier primary key default newid(), -- ���
	customer_id uniqueidentifier not null, -- �û����
	supply_id uniqueidentifier not null, -- �����̱��
	created_time datetime not null, -- ����ʱ��
);
-- ���������ղر�������Լ��
alter table t_supply_col add constraint FK_t_supply_col_t_customer_id foreign key(customer_id) references t_customer(id); -- ������Լ��
alter table t_supply_col add constraint FK_t_supply_col_t_supply_id foreign key(supply_id) references t_supply(id); -- ������Լ��

-- ���������ղر���ӵ�������
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c27b0ba9-b6c4-49a8-9251-39a152dd9f03', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c27b0ba9-b6c4-49a8-9251-39a152dd9f03', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'c27b0ba9-b6c4-49a8-9251-39a152dd9f03', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());
insert into t_supply_col(customer_id, supply_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4f236ad9-85c4-47f9-9936-754b40b25375', getdate());

-- �½����ʦ�ղر�
create table t_designer_col(
	id uniqueidentifier primary key default newid(), -- ���
	customer_id uniqueidentifier not null, -- �û����
	designer_id uniqueidentifier not null, -- ���ʦ���
	created_time datetime not null, -- ����ʱ��
);
-- �����ʦ�ղر�������Լ��
alter table t_designer_col add constraint FK_t_designer_col_t_customer_id foreign key(customer_id) references t_customer(id); -- ������Լ��
alter table t_designer_col add constraint FK_t_designer_col_t_designer_id foreign key(designer_id) references t_designer(id); -- ������Լ��

-- �����ʦ�ճ�����ӵ�������
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '3e5d7177-667a-4dfd-b51f-1c40b52ae42f', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '3e5d7177-667a-4dfd-b51f-1c40b52ae42f', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '152f4543-42de-49e6-bfeb-49536dec9bc7', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '152f4543-42de-49e6-bfeb-49536dec9bc7', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e35023d7-0c0d-4578-8c2c-5887d281be1d', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e35023d7-0c0d-4578-8c2c-5887d281be1d', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '565e1f41-1f0c-4c96-bc05-59c53754975d', getdate());
insert into t_designer_col(customer_id, designer_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '565e1f41-1f0c-4c96-bc05-59c53754975d', getdate());

-- �½������ղر�
create table t_product_col(
	id uniqueidentifier primary key default newid(), -- ���
	customer_id uniqueidentifier not null, -- �û����
	product_id uniqueidentifier not null, -- ���ı��
	created_time datetime not null, -- ����ʱ��
);
-- �������ղر�������Լ��
alter table t_product_col add constraint FK_t_product_col_t_customer_id foreign key(customer_id) references t_customer(id); -- ������Լ��
alter table t_product_col add constraint FK_t_product_col_t_product_id foreign key(product_id) references t_product(id); -- ������Լ��

select * from t_designer_col;
-- ��������Ʒ�����������
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '01196a0a-41f5-4a91-9b2c-606e47ff11cc', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '01196a0a-41f5-4a91-9b2c-606e47ff11cc', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '90e79e94-3ee8-4644-8c1f-aedaf6d627b5', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '90e79e94-3ee8-4644-8c1f-aedaf6d627b5', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '90e79e94-3ee8-4644-8c1f-aedaf6d627b5', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '150311f0-5d4d-4a2a-84d1-e5faf182b619', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '150311f0-5d4d-4a2a-84d1-e5faf182b619', getdate());
insert into t_product_col(customer_id, product_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '150311f0-5d4d-4a2a-84d1-e5faf182b619', getdate());

-- �½�װ�޹�˾�����ղر�
create table t_company_case_col(
	id uniqueidentifier primary key default newid(), -- ���
	customer_id uniqueidentifier not null, -- �û����
	case_id uniqueidentifier not null, -- װ�޹�˾�������
	created_time datetime not null, -- ����ʱ��
);
-- ��װ�޹�˾������������Լ��
alter table t_company_case_col add constraint FK_t_company_case_col_t_customer_id foreign key(customer_id) references t_customer(id); -- ������Լ��
alter table t_company_case_col add constraint FK_t_company_case_col_t_company_case_id foreign key(case_id) references t_company_case(id); -- ������Լ��

-- ��װ�޹�˾�����ղر���ӵ�������
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'debd5a7e-6ccf-424f-9b63-2d3ecf232fc5', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'debd5a7e-6ccf-424f-9b63-2d3ecf232fc5', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4d1eb254-758b-4325-b999-3277d37ec530', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4d1eb254-758b-4325-b999-3277d37ec530', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '4d1eb254-758b-4325-b999-3277d37ec530', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e3d6b0c5-705b-4d90-bfa5-33255b409c20', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'e3d6b0c5-705b-4d90-bfa5-33255b409c20', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '9844438b-6a0f-4c0f-842f-78ad8f7b65ea', getdate());
insert into t_company_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '9844438b-6a0f-4c0f-842f-78ad8f7b65ea', getdate());

-- �½����ʦ�����ղر�
create table t_designer_case_col(
	id uniqueidentifier primary key default newid(), -- ���
	customer_id uniqueidentifier not null, -- �û����
	case_id uniqueidentifier not null, -- ���ʦ�������
	created_time datetime not null, -- ����ʱ��
);
-- �����ʦ������������Լ��
alter table t_designer_case_col add constraint FK_t_designer_case_col_t_customer_id foreign key(customer_id) references t_customer(id); -- ������Լ��
alter table t_designer_case_col add constraint FK_t_designer_case_col_t_designer_case_id foreign key(case_id) references t_designer_case(id); -- ������Լ��

-- �����ʦ�����ղر���ӵ�������
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '527dd62f-850d-405b-a44b-2d092586ef5b', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '527dd62f-850d-405b-a44b-2d092586ef5b', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '6945e907-790a-4c0a-b8ce-5b16a404ff70', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '6945e907-790a-4c0a-b8ce-5b16a404ff70', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '29a794e8-40e2-43a2-acf3-9c6309d2ec75', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', '29a794e8-40e2-43a2-acf3-9c6309d2ec75', getdate());
insert into t_designer_case_col(customer_id, case_id, created_time) values('1f05ee0a-73f4-4305-a4bd-c4518d726ddc', 'a1a16bd8-b2c5-463c-97e9-d6544ef7af8b', getdate());

-- �û�ԤԼ��
create table t_appointment(
	id uniqueidentifier primary key default newid(), -- ���
	company_id uniqueidentifier not null, -- ԤԼ��װ�޹�˾���
	name varchar(50) not null, -- �ƺ�
	phone varchar(11) not null, -- �ֻ���
	plot_name varchar(100) not null, -- С������
	area float not null, -- �������
	way varchar(10) not null, -- װ�޷�ʽ
	budget varchar(20) not null, -- װ��Ԥ��
	created_time datetime not null -- ����ʱ��
);
-- ���û���������Լ��
alter table t_appointment add constraint CK_t_appointment_way check(way = 'whole' or way = 'half'); -- ��way��Ӽ��Լ��
alter table t_appointment add constraint FK_t_company_t_appointment_company_id foreign key(company_id) references t_company(id);

-- ԤԼ�鿴��¼��
create table t_appointment_view(
	id uniqueidentifier primary key default newid(), -- ���
	app_id uniqueidentifier not null, -- ԤԼ���
	company_id uniqueidentifier not null, -- װ�޹�˾���
	created_time datetime not null -- ����ʱ��
);
-- ��ԤԼ��������Լ��
alter table t_appointment_view add constraint FK_t_appointment_t_appointment_id foreign key(app_id) references t_appointment(id); -- ������Լ��
alter table t_appointment_view add constraint FK_t_appointment_t_company_id foreign key(company_id) references t_company(id); -- ������Լ��

-- ��ԤԼ�鿴������������
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '73fca101-a21d-4fd3-8024-1e277eaa1e09', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '0a0cef8a-6885-4057-b46b-254a541504f4', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '2ded2438-4f6c-4778-b448-52806416bd80', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', 'f3565c3b-70e6-4101-aa55-59d6e523f3a6', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '83f37fa1-c412-47a1-85a0-64f0b6add183', getdate());
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', 'a1ce80e9-a421-4baf-ad69-76ab362c4ac6', getdate());


delete t_appointment_view where id = 'A5F63F62-7F7C-4430-ADDF-2B55DFA43812';
-- java�̵Ĵ������
select * from t_appointment_view;
select * from t_appointment_view where app_id = 'B7A0ECCD-A253-4ACB-AA28-326A8C082C6A' and company_id = '73FCA101-A21D-4FD3-8024-1E277EAA1E09';
select count(id) from t_appointment_view where app_id = 'B7A0ECCD-A253-4ACB-AA28-326A8C082C6A';
select count(id) from t_appointment_view where company_id = '0a0cef8a-6885-4057-b46b-254a541504f4';

-- ����û����۱�
create table t_comment (
	id uniqueidentifier primary key default newid(),--���
	customer_id uniqueidentifier not null, --����û����
	content varchar(500),--�û�������Ϣ
	created_time datetime not null --����ʱ��
)

-- ���û����۱������ص�Լ��
alter table t_comment add constraint FK_t_comment_t_customer_id foreign key(customer_id) references t_customer(id); -- ����������

-- ���û����۱�����������
select top 3 * from t_comment order by created_time desc;
insert into t_comment(customer_id, content, created_time) values('3BD59CF3-339E-4A39-9A59-213617ED2E33', '�ǳ��õ�һ��װ�޹�˾���ǳ�����', getdate());
insert into t_comment(customer_id, content, created_time) values('4D378C74-19A3-492D-9BBF-2BB464DBCE86', '��˾����̬�Ⱥã��ǳ�����', getdate());
insert into t_comment(customer_id, content, created_time) values('14110A8C-CFCD-42DA-A87D-70C7778AF860', '�������У�����', getdate());

-- �½�����Ա��
create table t_admin(
	id uniqueidentifier primary key default newid(), -- ���
	email varchar(100) not null, -- ����
	password varchar(200) not null, -- ����
	name varchar(100) not null, -- ����
	phone varchar(11) not null, -- �ֻ���
	role varchar(10) not null, -- ��ɫ
	created_time datetime not null, -- ����ʱ��
	last_login_time datetime not null, -- ���һ�ε���ʱ��
	login_count int not null, -- ��¼����
	status char(1) not null -- �Ƿ����Y��ʾ���ã�N��ʾ������
);
-- ������Ա��������Լ��
alter table t_admin add constraint UQ_t_admin_email unique(email); -- ���������ΨһԼ��
alter table t_admin add constraint CK_t_admmin_role check(role = 'super' or role = 'normal'); -- ��role��Ӽ��Լ��
alter table t_admin add constraint CK_t_admin_status check(status = 'Y' or status = 'N'); -- ��status��Ӽ��Լ��
alter table t_admin add constraint DF_t_admin_status default 'Y' for status; -- ��status���Ĭ��Լ��
insert into t_admin(email, password, name, phone, role, created_time, last_login_time, login_count, status) values('Admin@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', 'Admin', '18279700225', 'super', getdate(), getdate(), 1, 'Y');

-- ������java����sql�����飺
select * from t_admin; -- ��ѯ���й���Ա
select * from t_admin where name = 'Admin' and password = 'ICy5YqxZB1uWSwcVLSNLcA=='; -- ��ѯָ������Ա����Ϣ
update t_admin set password = '4QrcOUm6Wau+VuBX8g+IPg==' where name = 'Admin'; -- �޸�ָ�����ƵĹ���Ա��Ϣ
select count(id) from t_admin; -- ��ѯ����Ա���ж�������¼
select top 1 * from t_admin where id not in(select top 0 id from t_admin); -- ��ѯ��һ�еļ�¼
select * from t_admin where email = 'Wjhsmart@qq.com'; -- ���������ѯ����
delete from t_admin where email = 'admin@qq.com'; -- ��������ɾ������


select * from t_company
insert into t_appointment_view(app_id, company_id, created_time) values('B7A0ECCD-A253-4ACB-AA28-326A8C082C6A', '6DECE320-77D7-4667-961D-24BF6DC70D1E', getdate())
select top 6 * from t_appointment where id 
not in(select top 0 id from t_appointment where id not in(select app_id from t_appointment_view))
and id not in(select app_id from t_appointment_view)