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
insert into college values('005','����ѧԺ','2015-6-30',null);
insert into college values('006','����ѧԺ','2015-6-30',null);
insert into college values('007','����ѧԺ','2015-6-30',null);
insert into college values('008','�����ѧԺ','2015-6-30',null);

/*major*/
insert into major values('1020','���̹���','2015-6-30',null,'005');
insert into major values('1021','Ͷ��ѧ','2015-6-30',null,'005');
insert into major values('1022','����ѧ','2015-6-30',null,'005');
insert into major values('1023','������Դ����','2015-6-30',null,'005');


insert into major values('1024','�������','2015-6-30',null,'006');
insert into major values('1025','����ѧ','2015-6-30',null,'006');
insert into major values('1026','�滭','2015-6-30',null,'006');
insert into major values('1027','����','2015-6-30',null,'006');
insert into major values('1028','�赸�ർ','2015-6-30',null,'006');

insert into major values('1029','��ѧ','2015-6-30',null,'007');
insert into major values('1030','��ҩ����','2015-6-30',null,'007');
insert into major values('1031','ҩѧ','2015-6-30',null,'007');
insert into major values('1032','ҩ���Ƽ�','2015-6-30',null,'007');

insert into major values('1033','Ӣ��','2015-6-30',null,'008');
insert into major values('1034','����','2015-6-30',null,'008');
insert into major values('1035','����','2015-6-30',null,'008');
/*team*/
insert into team values('2020','���̹���01��','2015-6-30','1020');
insert into team values('2021','Ͷ��ѧ01��','2015-6-30','1021');
insert into team values('2022','����ѧ01��','2015-6-30','1022');
insert into team values('2023','������Դ����01��','2015-6-30','1023');

insert into team values('2024','�������01��','2015-6-30','1024');
insert into team values('2025','����ѧ01��','2015-6-30','1025');
insert into team values('2026','�滭01��','2015-6-30','1026');
insert into team values('2027','����01��','2015-6-30','1027');
insert into team values('2028','�赸�ർ01��','2015-6-30','1028');

insert into team values('2029','��ѧ01��','2015-6-30','1029');
insert into team values('2030','��ҩ����01��','2015-6-30','1030');
insert into team values('2031','ҩѧ01��','2015-6-30','1031');
insert into team values('2032','ҩ���Ƽ�01��','2015-6-30','1032');

insert into team values('2033','Ӣ��01��','2015-6-30','1033');
insert into team values('2034','����01��','2015-6-30','1034');
insert into team values('2035','����01��','2015-6-30','1035');
/*teacher*/
insert into teacher values('3020','������','13020','��','38','15977130200','���̹�����Ա','005',null);
insert into teacher values('3021','ղ����','13021','��','32','15977130201','Ͷ��ѧ��ʦ','005',null);
insert into teacher values('3022','���Ӻ�','13022','Ů','28','15977130202','����ѧ����','005',null);
insert into teacher values('3023','������','13023','��','48','15977130203','������Դ�����ʦ','005',null);

insert into teacher values('3024','������','13024','��','36','15977130204','������Ƹ���Ա','006',null);
insert into teacher values('3025','������','13025','Ů','22','15977130205','����ѧ��ʦ','006',null);
insert into teacher values('3026','������','13026','Ů','28','15977130206','�滭����','006',null);
insert into teacher values('3027','�ƻ���','13027','��','27','15977130207','���ݽ�ʦ','006',null);
insert into teacher values('3029','���ϼ','13029','��','38','15977130209','��ѧ����Ա','007',null);
insert into teacher values('3030','�ƺ���','13030','��','32','15977130210','��ҩ���̽�ʦ','007',null);
insert into teacher values('3031','��С��','13031','Ů','28','15977130212','ҩѧ����','007',null);
insert into teacher values('3032','��˼��','13032','��','48','15977130213','ҩ���Ƽ���ʦ','007',null);

insert into teacher values('3033','��չ��','13033','��','36','15977130214','Ӣ�︨��Ա','008',null);
insert into teacher values('3034','֣��ϣ','13034','Ů','22','15977130215','�����ʦ','008',null);
insert into teacher values('3035','������','13035','Ů','28','15977130216','��������','008',null);
/*student*/
insert into student values('s40201','����ƽ','140201','��','20','15977140200',null,'2020',null);
insert into student values('s40202','��  ��','140202','Ů','20','15977141200',null,'2020',null);
insert into student values('s40203','����','140203','��','20','15977142200',null,'2020',null);
insert into student values('s40204','Ī�','140204','Ů','20','15977143200',null,'2020',null);
insert into student values('s40205','ũ����','140205','��','20','15977144200',null,'2020',null);
insert into student values('s40206','������','140206','Ů','20','15977145200',null,'2020',null);
insert into student values('s40211','�����','140211','��','20','15977150200',null,'2021',null);
insert into student values('s40212','������','140212','Ů','20','15977151200',null,'2021',null);
insert into student values('s40213','л�꼾','140213','��','20','15977152200',null,'2021',null);
insert into student values('s40214','������','140214','Ů','20','15977153200',null,'2021',null);
insert into student values('s40215','��˼��','140215','��','20','15977154200',null,'2021',null);
insert into student values('s40216','���ľ�','140216','Ů','20','15977155200',null,'2021',null);
insert into student values('s40221','�����','140221','��','20','15977160200',null,'2022',null);
insert into student values('s40222','������','140222','Ů','20','15977161200',null,'2022',null);
insert into student values('s40223','�����','140223','��','20','15977162200',null,'2022',null);
insert into student values('s40224','����¶','140224','Ů','20','15977163200',null,'2022',null);
insert into student values('s40225','л־��','140225','��','20','15977164200',null,'2022',null);
insert into student values('s40226','�����','140226','Ů','20','15977165200',null,'2022',null);
insert into student values('s40231','��ʢ��','140231','��','20','15977170200',null,'2023',null);
insert into student values('s40232','��ӨӨ','140232','Ů','20','15977171200',null,'2023',null);
insert into student values('s40233','������','140233','��','20','15977172200',null,'2023',null);
insert into student values('s40234','�μ���','140234','Ů','20','15977173200',null,'2023',null);
insert into student values('s40235','��п�','140235','��','20','15977174200',null,'2023',null);
insert into student values('s40236','����ѩ','140236','Ů','20','15977175200',null,'2023',null);

insert into student values('s40241','л��ƽ','140241','��','20','15977180200',null,'2024',null);
insert into student values('s40242','������','140242','Ů','20','15977181200',null,'2024',null);
insert into student values('s40243','���ź�','140243','��','20','15977182200',null,'2024',null);
insert into student values('s40244','ũ�꺽','140244','Ů','20','15977183200',null,'2024',null);
insert into student values('s40245','ũ���','140245','��','20','15977184200',null,'2024',null);
insert into student values('s40246','��С��','140246','Ů','20','15977185200',null,'2024',null);
insert into student values('s40251','����ƽ','140251','��','20','15877140200',null,'2025',null);
insert into student values('s40252','������','140252','Ů','20','15877141200',null,'2025',null);
insert into student values('s40253','���','140253','��','20','15877142200',null,'2025',null);
insert into student values('s40254','Ī  ��','140254','Ů','20','15877143200',null,'2025',null);
insert into student values('s40255','�ż���','140255','��','20','15877144200',null,'2025',null);
insert into student values('s40256','������','140256','Ů','20','15877145200',null,'2025',null);
insert into student values('s40261','�����','140261','��','20','15877150200',null,'2026',null);
insert into student values('s40262','������','140262','Ů','20','15877151200',null,'2026',null);
insert into student values('s40263','л  ��','140263','��','20','15877152200',null,'2026',null);
insert into student values('s40264','��  ��','140264','Ů','20','15877153200',null,'2026',null);
insert into student values('s40265','��˼��','140265','��','20','15877154200',null,'2026',null);
insert into student values('s40266','���ľ�','140266','Ů','20','15877155200',null,'2026',null);
insert into student values('s40271','��  ��','140271','��','20','15877160200',null,'2027',null);
insert into student values('s40272','��  ��','140272','Ů','20','15877161200',null,'2027',null);
insert into student values('s40273','���','140273','��','20','15877162200',null,'2027',null);
insert into student values('s40274','��˼¶','140274','Ů','20','15877163200',null,'2027',null);
insert into student values('s40275','л���','140275','��','20','15877164200',null,'2027',null);
insert into student values('s40276','�����','140276','Ů','20','15877165200',null,'2027',null);
insert into student values('s40281','��ʢ��','140281','��','20','15877170200',null,'2028',null);
insert into student values('s40282','��ӨӨ','140282','Ů','20','15877171200',null,'2028',null);
insert into student values('s40283','������','140283','��','20','15877172200',null,'2028',null);
insert into student values('s40284','������','140284','Ů','20','15877173200',null,'2028',null);
insert into student values('s40285','��п�','140285','��','20','15877174200',null,'2028',null);
insert into student values('s40286','��  ѩ','140286','Ů','20','15877175200',null,'2028',null);

insert into student values('s40291','ũ��ƽ','140281','��','20','15877180200',null,'2029',null);
insert into student values('s40292','�ƺ���','140282','Ů','20','15877181200',null,'2029',null);
insert into student values('s40293','��  ��','140283','��','20','15877182200',null,'2029',null);
insert into student values('s40294','��Ԫ��','140284','Ů','20','15877183200',null,'2029',null);
insert into student values('s40295','ũ����','140285','��','20','15877184200',null,'2029',null);
insert into student values('s40296','������','140286','Ů','20','15877185200',null,'2029',null);
insert into student values('s40301','�ܻ�ƽ','140301','��','20','15677140200',null,'2030',null);
insert into student values('s40302','��  ��','140302','Ů','20','15677141200',null,'2030',null);
insert into student values('s40303','��  ��','140303','��','20','15677142200',null,'2030',null);
insert into student values('s40304','���','140304','Ů','20','15677143200',null,'2030',null);
insert into student values('s40305','���','140305','��','20','15677144200',null,'2030',null);
insert into student values('s40306','������','140306','Ů','20','15677145200',null,'2030',null);
insert into student values('s40311','��Ԫ��','140311','��','20','15677150200',null,'2031',null);
insert into student values('s40312','������','140312','Ů','20','15677151200',null,'2031',null);
insert into student values('s40313','л�뼾','140313','��','20','15677152200',null,'2031',null);
insert into student values('s40314','�����','140314','Ů','20','15677153200',null,'2031',null);
insert into student values('s40315','��˼��','140315','��','20','15677154200',null,'2031',null);
insert into student values('s40316','��  ��','140316','Ů','20','15677155200',null,'2031',null);
insert into student values('s40321','��  ��','140321','��','20','15677160200',null,'2032',null);
insert into student values('s40322','��  ��','140322','Ů','20','15677161200',null,'2032',null);
insert into student values('s40323','�����','140323','��','20','15677162200',null,'2032',null);
insert into student values('s40324','�ż�¶','140324','Ů','20','15677163200',null,'2032',null);
insert into student values('s40325','л�Һ�','140325','��','20','15677164200',null,'2032',null);
insert into student values('s40326','�����','140326','Ů','20','15677165200',null,'2032',null);

insert into student values('s40331','�κ���','140331','��','20','15677170200',null,'2033',null);
insert into student values('s40332','����Ө','140332','Ů','20','15677171200',null,'2033',null);
insert into student values('s40333','��  ��','140333','��','20','15677172200',null,'2033',null);
insert into student values('s40334','�μѼ�','140334','Ů','20','15677173200',null,'2033',null);
insert into student values('s40335','���п�','140335','��','20','15677174200',null,'2033',null);
insert into student values('s40336','���ѩ','140336','Ů','20','15677175200',null,'2033',null);
insert into student values('s40341','��  ƽ','140341','��','20','15677180200',null,'2034',null);
insert into student values('s40342','������','140342','Ů','20','15677181200',null,'2034',null);
insert into student values('s40343','���˺�','140343','��','20','15677182200',null,'2034',null);
insert into student values('s40344','Ԫ  ��','140344','Ů','20','15677183200',null,'2034',null);
insert into student values('s40345','��  ��','140345','��','20','15677184200',null,'2034',null);
insert into student values('s40346','�ŷ���','140346','Ů','20','15677185200',null,'2034',null);
insert into student values('s40351','��ϲƽ','140351','��','20','15967140200',null,'2035',null);
insert into student values('s40352','��  ��','140352','Ů','20','15967141200',null,'2035',null);
insert into student values('s40353','���ͦ','140353','��','20','15967142200',null,'2035',null);
insert into student values('s40354','Ī����','140354','Ů','20','15967143200',null,'2035',null);
insert into student values('s40355','ũ����','140355','��','20','15967144200',null,'2035',null);
insert into student values('s40356','��ѩ��','140356','Ů','20','15967145200',null,'2035',null);
/*course*/
insert into course values('7020','1020','���̹���','5','���޿�','����')
insert into course values('7021','1021','Ͷ��ѧ','4','���޿�','����')
insert into course values('7022','1022','����ѧ','4','���޿�','����')
insert into course values('7023','1023','������Դ����','5','���޿�','����')

insert into course values('7024','1024','�������','4','���޿�','����')
insert into course values('7025','1025','����ѧ','4','���޿�','����')
insert into course values('7026','1026','�滭','3','���޿�','����')
insert into course values('7027','1027','����','5','���޿�','����')
insert into course values('7028','1028','�赸�ർ','4','���޿�','����')

insert into course values('7029','1029','��ѧ','5','���޿�','����')
insert into course values('7030','1030','��ҩ����','4','���޿�','����')
insert into course values('7031','1031','ҩѧ','4','���޿�','����')
insert into course values('7032','1032','��ҩ�Ƽ�','3','���޿�','����')

insert into course values('7033','1033','Ӣ��','5','���޿�','����')
insert into course values('7034','1034','����','4','���޿�','����')
insert into course values('7035','1035','����','4','���޿�','����')
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
insert into team_course values('2020','3020','7020','����һ','1','3','60');
insert into team_course values('2021','3021','7021','���ڶ�','4','2','60');
insert into team_course values('2022','3022','7022','������','6','3','60');
insert into team_course values('2023','3023','7023','������','4','3','60');
insert into team_course values('2024','3024','7024','������','1','3','60');
insert into team_course values('2025','3025','7025','����һ','10','3','60');
insert into team_course values('2026','3026','7026','���ڶ�','1','3','60');
insert into team_course values('2027','3027','7027','������','4','2','60');
insert into team_course values('2028','3028','7028','������','1','3','60');
insert into team_course values('2029','3029','7029','������','6','3','60');
insert into team_course values('2030','3030','7030','����һ','4','2','60');
insert into team_course values('2031','3031','7031','���ڶ�','10','3','60');
insert into team_course values('2032','3032','7032','������','1','3','60');
insert into team_course values('2033','3033','7033','������','3','3','60');
insert into team_course values('2034','3034','7034','������','1','3','60');
insert into team_course values('2035','3035','7035','����һ','6','3','60');

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

create login  root  with password=��123456��;
create user zuoye_root from login root;
sp_addrolemember 'db_owner','zuoye_root';