CREATE DATABASE zuoye;
USE zuoye;


CREATE TABLE college(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	createTime DATE NOT NULL ,
	status VARCHAR(200) NULL 
);

CREATE TABLE major(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(36) NOT NULL ,
	createTime DATE NOT NULL ,
	status VARCHAR(200) NULL ,
	collegeId VARCHAR(36) NOT NULL,
	CONSTRAINT major_collegeId FOREIGN KEY(collegeId) REFERENCES college(id) ON UPDATE CASCADE
);

CREATE TABLE team(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(30) NOT NULL ,
	status VARCHAR(200) NULL ,
	majorId VARCHAR(36) NOT NULL ,
	CONSTRAINT team_majorId FOREIGN KEY(majorId) REFERENCES major(id) ON UPDATE CASCADE
);

CREATE TABLE role(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(20) NOT NULL ,
	status VARCHAR(200) NOT NULL 
);

CREATE TABLE course(
	id VARCHAR(36) PRIMARY KEY,
	majorId VARCHAR(36) NOT NULL ,
	name VARCHAR(20) NOT NULL ,
	credit FLOAT NOT NULL ,
	ctype VARCHAR(20) NOT NULL ,
	ctype2 VARCHAR(20) NOT NULL ,
	CONSTRAINT course_majorId FOREIGN KEY(majorId) REFERENCES major(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE teacher(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(20) NOT NULL ,
	password VARCHAR(20) NOT NULL ,
	sex VARCHAR(4) NOT NULL	,
	age INT	NOT NULL ,
	telephone VARCHAR(11) NULL ,
	position VARCHAR(20) NULL ,
	collegeId VARCHAR(36) NOT NULL ,
	roleId VARCHAR(36) NULL ,
	CONSTRAINT teacher_collegeId FOREIGN KEY(collegeId) REFERENCES college(id) ON UPDATE CASCADE,
	CONSTRAINT teacher_roleId FOREIGN KEY(roleId) REFERENCES role(id) ON UPDATE CASCADE
);

CREATE TABLE student(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL ,
	sex VARCHAR(4) NOT NULL ,
	age INT NOT NULL ,
	telephone VARCHAR(11) NULL ,
	position VARCHAR(20) NULL ,
	teamId VARCHAR(36) NOT NULL ,
	roleId VARCHAR(36) NULL ,
	CONSTRAINT student_teamId FOREIGN KEY(teamId) REFERENCES team(id) ON UPDATE CASCADE,
	CONSTRAINT student_roleId FOREIGN KEY(roleId) REFERENCES role(id) ON UPDATE CASCADE
);

CREATE TABLE grade(
	studentId VARCHAR(36) NOT NULL,
	courseId VARCHAR(36) NOT NULL,
	score INT NOT NULL ,
	PRIMARY KEY(studentId,courseId),
	CONSTRAINT grade_studentId FOREIGN KEY(studentId) REFERENCES student(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT grade_courseId FOREIGN KEY(courseId) REFERENCES course(id) ON DELETE CASCADE
);

CREATE TABLE team_course(
	teamId VARCHAR(36) NOT NULL ,
	teacherId VARCHAR(36) NOT NULL,
	courseId VARCHAR(36) NOT NULL ,
	week VARCHAR(10) NOT NULL ,
	start INT NOT NULL ,
	lengths INT NOT NULL ,
	hour INT NOT NULL ,
	PRIMARY KEY(teamId,teacherId,courseId,week),
	CONSTRAINT tc_teamId FOREIGN KEY(teamId) REFERENCES team(id) ON DELETE CASCADE ,
	CONSTRAINT tc_teacherId FOREIGN KEY(teacherId) REFERENCES teacher(id) ON UPDATE CASCADE,
	CONSTRAINT tc_courseId FOREIGN KEY(courseId) REFERENCES course(id)  ON DELETE CASCADE
);

CREATE TABLE myfunction(
	id VARCHAR(36) PRIMARY KEY,
	teacherId VARCHAR(36) NOT NULL ,
	courseId VARCHAR(36) NOT NULL ,
	teamId VARCHAR(36) NOT NULL,
	task INT NOT NULL ,
	pacific INT NOT NULL ,
	mid INT NOT NULL ,
	final INT NOT NULL ,
	CONSTRAINT function_teacherId FOREIGN KEY(teacherId) REFERENCES teacher(id) ON DELETE CASCADE ,
	CONSTRAINT function_courseId FOREIGN KEY(courseId) REFERENCES course(id) ON DELETE CASCADE,
	CONSTRAINT function_teamId FOREIGN KEY(teamId) REFERENCES team(id) ON DELETE CASCADE
);

CREATE TABLE admin(
	id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(20) NOT NULL ,
	password VARCHAR(20) NOT NULL 
);
/*college*/
insert into college values('005','管理学院','2015-6-30',null);
insert into college values('006','艺术学院','2015-6-30',null);
insert into college values('007','化工学院','2015-6-30',null);
insert into college values('008','外国语学院','2015-6-30',null);

/*major*/
insert into major values('1020','工商管理','2015-6-30',null,'005');
insert into major values('1021','投资学','2015-6-30',null,'005');
insert into major values('1022','财政学','2015-6-30',null,'005');
insert into major values('1023','人力资源管理','2015-6-30',null,'005');


insert into major values('1024','艺术设计','2015-6-30',null,'006');
insert into major values('1025','音乐学','2015-6-30',null,'006');
insert into major values('1026','绘画','2015-6-30',null,'006');
insert into major values('1027','表演','2015-6-30',null,'006');
insert into major values('1028','舞蹈编导','2015-6-30',null,'006');

insert into major values('1029','化学','2015-6-30',null,'007');
insert into major values('1030','制药工程','2015-6-30',null,'007');
insert into major values('1031','药学','2015-6-30',null,'007');
insert into major values('1032','药物制剂','2015-6-30',null,'007');

insert into major values('1033','英语','2015-6-30',null,'008');
insert into major values('1034','法语','2015-6-30',null,'008');
insert into major values('1035','日语','2015-6-30',null,'008');
/*team*/
insert into team values('2020','工商管理01班','2015-6-30','1020');
insert into team values('2021','投资学01班','2015-6-30','1021');
insert into team values('2022','财政学01班','2015-6-30','1022');
insert into team values('2023','人力资源管理01班','2015-6-30','1023');

insert into team values('2024','艺术设计01班','2015-6-30','1024');
insert into team values('2025','音乐学01班','2015-6-30','1025');
insert into team values('2026','绘画01班','2015-6-30','1026');
insert into team values('2027','表演01班','2015-6-30','1027');
insert into team values('2028','舞蹈编导01班','2015-6-30','1028');

insert into team values('2029','化学01班','2015-6-30','1029');
insert into team values('2030','制药工程01班','2015-6-30','1030');
insert into team values('2031','药学01班','2015-6-30','1031');
insert into team values('2032','药物制剂01班','2015-6-30','1032');

insert into team values('2033','英语01班','2015-6-30','1033');
insert into team values('2034','法语01班','2015-6-30','1034');
insert into team values('2035','日语01班','2015-6-30','1035');
/*teacher*/
insert into teacher values('3020','赵少奇','13020','男','38','15977130200','工商管理辅导员','005',null);
insert into teacher values('3021','詹博文','13021','男','32','15977130201','投资学教师','005',null);
insert into teacher values('3022','周子涵','13022','女','28','15977130202','财政学主任','005',null);
insert into teacher values('3023','黄昱舟','13023','男','48','15977130203','人力资源管理教师','005',null);

insert into teacher values('3024','黄天磊','13024','男','36','15977130204','艺术设计辅导员','006',null);
insert into teacher values('3025','吴立春','13025','女','22','15977130205','音乐学教师','006',null);
insert into teacher values('3026','杨绍珍','13026','女','28','15977130206','绘画主任','006',null);
insert into teacher values('3027','黄华成','13027','男','27','15977130207','表演教师','006',null);
insert into teacher values('3029','李红霞','13029','男','38','15977130209','化学辅导员','007',null);
insert into teacher values('3030','黄浩天','13030','男','32','15977130210','制药工程教师','007',null);
insert into teacher values('3031','徐小琴','13031','女','28','15977130212','药学主任','007',null);
insert into teacher values('3032','李思成','13032','男','48','15977130213','药物制剂教师','007',null);

insert into teacher values('3033','赵展鹏','13033','男','36','15977130214','英语辅导员','008',null);
insert into teacher values('3034','郑佳希','13034','女','22','15977130215','法语教师','008',null);
insert into teacher values('3035','张子萱','13035','女','28','15977130216','日语主任','008',null);
/*student*/
insert into student values('s40201','周新平','140201','男','20','15977140200',null,'2020',null);
insert into student values('s40202','艾  琳','140202','女','20','15977141200',null,'2020',null);
insert into student values('s40203','李弘基','140203','男','20','15977142200',null,'2020',null);
insert into student values('s40204','莫妙航','140204','女','20','15977143200',null,'2020',null);
insert into student values('s40205','农技服','140205','男','20','15977144200',null,'2020',null);
insert into student values('s40206','周梦婷','140206','女','20','15977145200',null,'2020',null);
insert into student values('s40211','李国涛','140211','男','20','15977150200',null,'2021',null);
insert into student values('s40212','张琦松','140212','女','20','15977151200',null,'2021',null);
insert into student values('s40213','谢宏季','140213','男','20','15977152200',null,'2021',null);
insert into student values('s40214','雷美恒','140214','女','20','15977153200',null,'2021',null);
insert into student values('s40215','李思令','140215','男','20','15977154200',null,'2021',null);
insert into student values('s40216','何文静','140216','女','20','15977155200',null,'2021',null);
insert into student values('s40221','简高亮','140221','男','20','15977160200',null,'2022',null);
insert into student values('s40222','胡苏丽','140222','女','20','15977161200',null,'2022',null);
insert into student values('s40223','杨道建','140223','男','20','15977162200',null,'2022',null);
insert into student values('s40224','曾家露','140224','女','20','15977163200',null,'2022',null);
insert into student values('s40225','谢志恒','140225','男','20','15977164200',null,'2022',null);
insert into student values('s40226','张雨绮','140226','女','20','15977165200',null,'2022',null);
insert into student values('s40231','何盛东','140231','男','20','15977170200',null,'2023',null);
insert into student values('s40232','杜莹莹','140232','女','20','15977171200',null,'2023',null);
insert into student values('s40233','汪荆川','140233','男','20','15977172200',null,'2023',null);
insert into student values('s40234','何佳怡','140234','女','20','15977173200',null,'2023',null);
insert into student values('s40235','洪承骏','140235','男','20','15977174200',null,'2023',null);
insert into student values('s40236','刘佳雪','140236','女','20','15977175200',null,'2023',null);

insert into student values('s40241','谢余平','140241','男','20','15977180200',null,'2024',null);
insert into student values('s40242','王星琳','140242','女','20','15977181200',null,'2024',null);
insert into student values('s40243','黄昱弘','140243','男','20','15977182200',null,'2024',null);
insert into student values('s40244','农雨航','140244','女','20','15977183200',null,'2024',null);
insert into student values('s40245','农潇服','140245','男','20','15977184200',null,'2024',null);
insert into student values('s40246','张小婷','140246','女','20','15977185200',null,'2024',null);
insert into student values('s40251','黄新平','140251','男','20','15877140200',null,'2025',null);
insert into student values('s40252','艾恍琳','140252','女','20','15877141200',null,'2025',null);
insert into student values('s40253','李爱基','140253','男','20','15877142200',null,'2025',null);
insert into student values('s40254','莫  航','140254','女','20','15877143200',null,'2025',null);
insert into student values('s40255','张技服','140255','男','20','15877144200',null,'2025',null);
insert into student values('s40256','周梦婷','140256','女','20','15877145200',null,'2025',null);
insert into student values('s40261','吴国涛','140261','男','20','15877150200',null,'2026',null);
insert into student values('s40262','李琦松','140262','女','20','15877151200',null,'2026',null);
insert into student values('s40263','谢  季','140263','男','20','15877152200',null,'2026',null);
insert into student values('s40264','雷  美','140264','女','20','15877153200',null,'2026',null);
insert into student values('s40265','章思令','140265','男','20','15877154200',null,'2026',null);
insert into student values('s40266','李文静','140266','女','20','15877155200',null,'2026',null);
insert into student values('s40271','简  亮','140271','男','20','15877160200',null,'2027',null);
insert into student values('s40272','苏  丽','140272','女','20','15877161200',null,'2027',null);
insert into student values('s40273','杨华建','140273','男','20','15877162200',null,'2027',null);
insert into student values('s40274','曾思露','140274','女','20','15877163200',null,'2027',null);
insert into student values('s40275','谢后恒','140275','男','20','15877164200',null,'2027',null);
insert into student values('s40276','张新绮','140276','女','20','15877165200',null,'2027',null);
insert into student values('s40281','黄盛东','140281','男','20','15877170200',null,'2028',null);
insert into student values('s40282','张莹莹','140282','女','20','15877171200',null,'2028',null);
insert into student values('s40283','王荆川','140283','男','20','15877172200',null,'2028',null);
insert into student values('s40284','何心怡','140284','女','20','15877173200',null,'2028',null);
insert into student values('s40285','李承骏','140285','男','20','15877174200',null,'2028',null);
insert into student values('s40286','刘  雪','140286','女','20','15877175200',null,'2028',null);

insert into student values('s40291','农新平','140281','男','20','15877180200',null,'2029',null);
insert into student values('s40292','黄海琳','140282','女','20','15877181200',null,'2029',null);
insert into student values('s40293','何  弘','140283','男','20','15877182200',null,'2029',null);
insert into student values('s40294','周元航','140284','女','20','15877183200',null,'2029',null);
insert into student values('s40295','农海服','140285','男','20','15877184200',null,'2029',null);
insert into student values('s40296','张心婷','140286','女','20','15877185200',null,'2029',null);
insert into student values('s40301','周华平','140301','男','20','15677140200',null,'2030',null);
insert into student values('s40302','林  琳','140302','女','20','15677141200',null,'2030',null);
insert into student values('s40303','李  基','140303','男','20','15677142200',null,'2030',null);
insert into student values('s40304','王妙航','140304','女','20','15677143200',null,'2030',null);
insert into student values('s40305','李技服','140305','男','20','15677144200',null,'2030',null);
insert into student values('s40306','周潇婷','140306','女','20','15677145200',null,'2030',null);
insert into student values('s40311','李元涛','140311','男','20','15677150200',null,'2031',null);
insert into student values('s40312','张琦雨','140312','女','20','15677151200',null,'2031',null);
insert into student values('s40313','谢桦季','140313','男','20','15677152200',null,'2031',null);
insert into student values('s40314','雷玉恒','140314','女','20','15677153200',null,'2031',null);
insert into student values('s40315','黄思令','140315','男','20','15677154200',null,'2031',null);
insert into student values('s40316','文  静','140316','女','20','15677155200',null,'2031',null);
insert into student values('s40321','高  亮','140321','男','20','15677160200',null,'2032',null);
insert into student values('s40322','胡  丽','140322','女','20','15677161200',null,'2032',null);
insert into student values('s40323','李道建','140323','男','20','15677162200',null,'2032',null);
insert into student values('s40324','张家露','140324','女','20','15677163200',null,'2032',null);
insert into student values('s40325','谢家恒','140325','男','20','15677164200',null,'2032',null);
insert into student values('s40326','张桦绮','140326','女','20','15677165200',null,'2032',null);

insert into student values('s40331','何海东','140331','男','20','15677170200',null,'2033',null);
insert into student values('s40332','杜心莹','140332','女','20','15677171200',null,'2033',null);
insert into student values('s40333','汪  川','140333','男','20','15677172200',null,'2033',null);
insert into student values('s40334','何佳家','140334','女','20','15677173200',null,'2033',null);
insert into student values('s40335','曾承骏','140335','男','20','15677174200',null,'2033',null);
insert into student values('s40336','吴佳雪','140336','女','20','15677175200',null,'2033',null);
insert into student values('s40341','章  平','140341','男','20','15677180200',null,'2034',null);
insert into student values('s40342','黄星琳','140342','女','20','15677181200',null,'2034',null);
insert into student values('s40343','李兴弘','140343','男','20','15677182200',null,'2034',null);
insert into student values('s40344','元  航','140344','女','20','15677183200',null,'2034',null);
insert into student values('s40345','王  服','140345','男','20','15677184200',null,'2034',null);
insert into student values('s40346','张飞婷','140346','女','20','15677185200',null,'2034',null);
insert into student values('s40351','周喜平','140351','男','20','15967140200',null,'2035',null);
insert into student values('s40352','艾  草','140352','女','20','15967141200',null,'2035',null);
insert into student values('s40353','李弘挺','140353','男','20','15967142200',null,'2035',null);
insert into student values('s40354','莫妙玉','140354','女','20','15967143200',null,'2035',null);
insert into student values('s40355','农技艺','140355','男','20','15967144200',null,'2035',null);
insert into student values('s40356','张雪琪','140356','女','20','15967145200',null,'2035',null);
/*course*/
insert into course values('7020','1020','工商管理','5','必修课','考试')
insert into course values('7021','1021','投资学','4','必修课','考试')
insert into course values('7022','1022','财政学','4','必修课','考试')
insert into course values('7023','1023','人力资源管理','5','必修课','考试')

insert into course values('7024','1024','艺术设计','4','必修课','考试')
insert into course values('7025','1025','音乐学','4','必修课','考试')
insert into course values('7026','1026','绘画','3','必修课','考试')
insert into course values('7027','1027','表演','5','必修课','考试')
insert into course values('7028','1028','舞蹈编导','4','必修课','考试')

insert into course values('7029','1029','化学','5','必修课','考试')
insert into course values('7030','1030','制药工程','4','必修课','考试')
insert into course values('7031','1031','药学','4','必修课','考试')
insert into course values('7032','1032','制药制剂','3','必修课','考试')

insert into course values('7033','1033','英语','5','必修课','考试')
insert into course values('7034','1034','法语','4','必修课','考试')
insert into course values('7035','1035','日语','4','必修课','考试')
/*grade*/
select * from grade;
insert into grade values('s40201','7020','68');
insert into grade values('s40202','7020','76');
insert into grade values('s40203','7020','64');
insert into grade values('s40204','7020','88');
insert into grade values('s40205','7020','86');
insert into grade values('s40206','7020','67');
insert into grade values('s40211','7021','92');
insert into grade values('s40212','7021','66');
insert into grade values('s40213','7021','74');
insert into grade values('s40214','7021','86');
insert into grade values('s40215','7021','61');
insert into grade values('s40216','7021','67');
insert into grade values('s40221','7022','89');
insert into grade values('s40222','7022','63');
insert into grade values('s40223','7022','78');
insert into grade values('s40224','7022','91');
insert into grade values('s40225','7022','66');
insert into grade values('s40226','7022','86');
insert into grade values('s40231','7023','75');
insert into grade values('s40232','7023','75');
insert into grade values('s40233','7023','65');
insert into grade values('s40234','7023','82');
insert into grade values('s40235','7023','72');
insert into grade values('s40236','7023','64');

insert into grade values('s40241','7024','77');
insert into grade values('s40242','7024','74');
insert into grade values('s40243','7024','87');
insert into grade values('s40244','7024','67');
insert into grade values('s40245','7024','71');
insert into grade values('s40246','7024','81');
insert into grade values('s40251','7025','77');
insert into grade values('s40252','7025','61');
insert into grade values('s40253','7025','83');
insert into grade values('s40254','7025','79');
insert into grade values('s40255','7025','90');
insert into grade values('s40256','7025','66');
insert into grade values('s40261','7026','69');
insert into grade values('s40262','7026','88');
insert into grade values('s40263','7026','67');
insert into grade values('s40264','7026','79');
insert into grade values('s40265','7026','89');
insert into grade values('s40266','7026','69');
insert into grade values('s40271','7027','70');
insert into grade values('s40272','7027','60');
insert into grade values('s40273','7027','79');
insert into grade values('s40274','7027','90');
insert into grade values('s40275','7027','76');
insert into grade values('s40276','7027','87');
insert into grade values('s40281','7028','60');
insert into grade values('s40282','7028','70');
insert into grade values('s40283','7028','94');
insert into grade values('s40284','7028','87');
insert into grade values('s40285','7028','93');
insert into grade values('s40286','7028','70');

insert into grade values('s40291','7029','68');
insert into grade values('s40292','7029','76');
insert into grade values('s40293','7029','64');
insert into grade values('s40294','7029','88');
insert into grade values('s40295','7029','86');
insert into grade values('s40296','7029','67');
insert into grade values('s40301','7030','92');
insert into grade values('s40302','7030','66');
insert into grade values('s40303','7030','74');
insert into grade values('s40304','7030','86');
insert into grade values('s40305','7030','61');
insert into grade values('s40306','7030','67');
insert into grade values('s40311','7031','89');
insert into grade values('s40312','7031','63');
insert into grade values('s40313','7031','78');
insert into grade values('s40314','7031','91');
insert into grade values('s40315','7031','66');
insert into grade values('s40316','7031','86');
insert into grade values('s40321','7032','75');
insert into grade values('s40322','7032','75');
insert into grade values('s40323','7032','65');
insert into grade values('s40324','7032','82');
insert into grade values('s40325','7032','72');
insert into grade values('s40326','7032','64');

insert into grade values('s40331','7033','77');
insert into grade values('s40332','7033','74');
insert into grade values('s40333','7033','87');
insert into grade values('s40334','7033','67');
insert into grade values('s40335','7033','71');
insert into grade values('s40336','7033','81');
insert into grade values('s40341','7034','77');
insert into grade values('s40342','7034','61');
insert into grade values('s40343','7034','83');
insert into grade values('s40344','7034','79');
insert into grade values('s40345','7034','90');
insert into grade values('s40346','7034','66');
insert into grade values('s40351','7035','69');
insert into grade values('s40352','7035','88');
insert into grade values('s40353','7035','67');
insert into grade values('s40354','7035','79');
insert into grade values('s40355','7035','89');
insert into grade values('s40356','7035','69');

select * from team_course;
insert into team_course values('2020','3020','7020','星期一','1','3','60');
insert into team_course values('2021','3021','7021','星期二','4','2','60');
insert into team_course values('2022','3022','7022','星期三','6','3','60');
insert into team_course values('2023','3023','7023','星期四','4','3','60');
insert into team_course values('2024','3024','7024','星期五','1','3','60');
insert into team_course values('2025','3025','7025','星期一','10','3','60');
insert into team_course values('2026','3026','7026','星期二','1','3','60');
insert into team_course values('2027','3027','7027','星期三','4','2','60');
insert into team_course values('2028','3028','7028','星期四','1','3','60');
insert into team_course values('2029','3029','7029','星期五','6','3','60');
insert into team_course values('2030','3030','7030','星期一','4','2','60');
insert into team_course values('2031','3031','7031','星期二','10','3','60');
insert into team_course values('2032','3032','7032','星期三','1','3','60');
insert into team_course values('2033','3033','7033','星期四','3','3','60');
insert into team_course values('2034','3034','7034','星期五','1','3','60');
insert into team_course values('2035','3035','7035','星期一','6','3','60');

/*myfunction*/
insert into myfunction values('8020','3020','7020','2020','15','20','15','50');
insert into myfunction values('8021','3021','7021','2021','15','20','15','50');
insert into myfunction values('8022','3022','7022','2022','10','50','10','50');
insert into myfunction values('8023','3023','7023','2023','5','35','15','50');
insert into myfunction values('8024','3024','7024','2024','10','10','20','60');
insert into myfunction values('8025','3025','7025','2025','10','20','20','50');
insert into myfunction values('8026','3026','7026','2026','10','20','10','50');
insert into myfunction values('8027','3027','7027','2027','10','20','5','65');
insert into myfunction values('8028','3028','7028','2028','10','20','15','55');
insert into myfunction values('8029','3029','7029','2029','5','35','5','55');
insert into myfunction values('8030','3030','7030','2030','5','25','10','60');
insert into myfunction values('8031','3031','7031','2031','5','20','15','60');
insert into myfunction values('8032','3032','7032','2032','5','25','10','60');
insert into myfunction values('8033','3033','7033','2033','15','25','10','50');
insert into myfunction values('8034','3034','7034','2034','10','10','20','60');
insert into myfunction values('8035','3035','7035','2035','10','10','20','60');

create login  root  with password=‘123456’;
create user zuoye_root from login root;
sp_addrolemember 'db_owner','zuoye_root';