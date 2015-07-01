package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import com.akalin.dao.Conn;
import com.akalin.dao.DAO;
import com.akalin.main.Login;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class TeacherMain extends JFrame {

	private JPanel contentPane;
	private JMenuItem output;		//导出Excel
	private JMenuItem checkCourse;	//查看课表
	private JMenuItem logout;		//退出登录
	private JMenuItem btn_grade;	//成绩
	private JMenuItem credit;		//学分
	private JMenuItem team;			//班级
	private JMenuItem pie;			//饼形图
	private JMenuItem diagram;		//柱形图
	private JMenuItem setFuntion;	//自定义函数
	private JMenuItem selectFuntion;//选择函数
	private JMenuItem about;		//关于
	private JComboBox scrn;		//筛选方式
	private JTextField condition;	//筛选条件
	private JLabel sno1;			//学号 
	private JLabel sname1;			//名字
	private JLabel grade1;			//成绩
	private JTextField sno;
	private JTextField sname;
	private JTextField grade;
	private JLabel function0;//
	private JLabel function1;//
	private JLabel function2;//
	private JLabel function3;//
	private JLabel function4;//
	private JTextField te1;//作业
	private JTextField te2;//期中
	private JTextField te3;//平时
	private JTextField te4;//期末
	private int a1;
	private int a2;
	private int a3;
	private int a4;
	private JButton submit;//
	private JButton modify;//
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String manager;
	private JComboBox course;
	private List<Object> cid;
	private JComboBox theFunction;
	private List<String> fid;
	private JPanel check;
	private int average=0; //平均成绩
	private int sub=0; //及格人数
	private int all=0;//总成绩
	private List<List<Object>> list2=new ArrayList<List<Object>>();
	private boolean flag=false;
	private JMenuItem excelInput;
	private JMenuItem excelOutput;
	private String path;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
/*	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMain frame = new TeacherMain("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public TeacherMain(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setTitle("教师主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		JMenu start = new JMenu("开始");
		menuBar.add(start);
		
		logout = new JMenuItem("退出");
		excelInput=new JMenuItem();
		excelInput.setText("Excel数据导入");
		excelOutput=new JMenuItem();
		excelOutput.setText("导出Excel文件");
		start.add(excelInput);
		start.add(excelOutput);
		start.add(logout);
		
		
		JMenu screen = new JMenu("筛选");
		menuBar.add(screen);
		
		JMenu function=new JMenu("函数");
		menuBar.add(function);
		
		setFuntion=new JMenuItem("自定义函数设置");
		function.add(setFuntion);
		
		selectFuntion=new JMenuItem("选择函数");
		function.add(selectFuntion);
		
		btn_grade = new JMenuItem("成绩");
		screen.add(btn_grade);
		
		credit = new JMenuItem("学分");
		screen.add(credit);
		
		team = new JMenuItem("班级");
		screen.add(team);
		
		JPanel topCenter=new JPanel();//存放浮动式组件
		contentPane.add(topCenter, BorderLayout.CENTER);
		topCenter.setLayout(new BorderLayout(0, 0));
		JPanel jtop=new JPanel();
		topCenter.add(jtop, BorderLayout.NORTH);
		scrn=new JComboBox();
		scrn.addItem("成绩");
		scrn.addItem("学分");
		scrn.addItem("班级");
		jtop.add(scrn);
		condition=new JTextField(20);
		jtop.add(condition);
		check=new JPanel();
		theFunction=new JComboBox();
		topCenter.add(check,BorderLayout.SOUTH);
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("学号");
		columnNameV.add("名字");
		columnNameV.add("课程");
		columnNameV.add("学分");
		columnNameV.add("课程分类");
		columnNameV.add("考核方式");
		columnNameV.add("成绩");
		columnNameV.add("取得学分");
		columnNameV.add("绩点");
		columnNameV.add("取得绩点");
				
		tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=0;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int col=0;col<9;col++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		//创建面板，在该面板中实现带行标题栏的表格
		aim=new MFixedColumnTable(columnNameV, tableValueV, 1);
		aim.setBorder(new EmptyBorder(20, 20, 20, 20));
		topCenter.add(aim, BorderLayout.CENTER);		//把面板添加到窗体中央
		JTable f=aim.getFixedColumnTable();
		TableColumnModel c=aim.getFloatingColumnTable().getColumnModel();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//复制 end
		
		JPanel footPanel = new JPanel();
		contentPane.add(footPanel, BorderLayout.SOUTH);
		footPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel addData=new JPanel();
		addData.setBorder(new EmptyBorder(20, 20, 20, 20));
		addData.setLayout(new BorderLayout());
		footPanel.add(addData, BorderLayout.NORTH);
		JPanel addTop=new JPanel();
		addData.add(addTop, BorderLayout.NORTH);
		JPanel addBottom=new JPanel();
		addData.add(addBottom, BorderLayout.CENTER);
		JPanel addButton=new JPanel();
		addData.add(addButton, BorderLayout.SOUTH);
		//添加数据
		sno1=new JLabel("学号");
		sno=new JTextField(10);
		sname1=new JLabel("名字");
		sname=new JTextField(10);
		JLabel course1=new JLabel("课程");
		course=new JComboBox();
		grade1=new JLabel("成绩");
		this.grade=new JTextField(10);
		addTop.add(sno1);
		addTop.add(sno);
		addTop.add(sname1);
		addTop.add(sname);
		addTop.add(course1);
		addTop.add(course);
		addTop.add(grade1);
		addTop.add(this.grade);
		function0=new JLabel("函数：");
		function1=new JLabel("x12%(作业)+");
		function2=new JLabel("x12%(期中)+");
		function3=new JLabel("x12%(平时)+");
		function4=new JLabel("x12%(期末)");
		te1=new JTextField(4);
		te2=new JTextField(4);
		te3=new JTextField(4);
		te4=new JTextField(4);
		addBottom.add(function0);
		addBottom.add(te1);
		addBottom.add(function1);
		addBottom.add(te2);
		addBottom.add(function2);
		addBottom.add(te3);
		addBottom.add(function3);
		addBottom.add(te4);
		addBottom.add(function4);
		addBottom.add(function4);
		addBottom.add(function4);
		submit=new JButton("提交");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		modify=new JButton("修改");
		addButton.add(submit);
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		addButton.add(modify);
		//
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("及格人数："+sub);
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("平均成绩："+average);
		panel.add(averige);
		
		JPanel bottom = new JPanel();
		//bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		footPanel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("欢迎光临");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel ctrl = new JLabel("使用者："+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		bottom.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("日期："+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("现在的时间是："+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		this.setTimer(time);
		setVisible(true);
	}
	//设置Timer 1000ms实现一次动作 实际是一个线程   
		 private void setTimer(JLabel time){   
		     final JLabel varTime = time;   
		     Timer timeAction = new Timer(1000, new ActionListener() {          
		  
		         public void actionPerformed(ActionEvent e) {       
		             GetTime getTime=new GetTime();  
		             varTime.setText("现在的时间是："+getTime.getTime());   
		         }      
		        });            
		        timeAction.start();        
		  } 
	public void myEvent(){
		//退出登录
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login login=new Login();
				setVisible(false);
			}
		});
		setFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Function function=new Function(manager);
			}
		});
		selectFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] str={"id"};
				String teacherId="";
				List<String> teamId=new ArrayList<String>();
				DAO dao=new DAO();
				List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
				if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
					teacherId=(String)teacherIds.get(0).get(0);
				}
				//获取教师的自定义函数
				String key[]={"编号","教师名","课程","作业","期中","平时","期末"};
				String values[]={"function.id","teacher.name","task","mid","pacific","final"};
				String sql="select function.id,teacher.name,task,mid,pacific,final "
						+ "from function,teacher,course "
						+ "where function.teacherId=teacher.id and function.courseId=course.id and teacher.id='"+teacherId+"'";
				list=dao.query(sql, values, key);
				if(!list.isEmpty()&&list.get(0)!=null){
					int c=0;
					for(int i=0;i<list.size();i++){
						for(Map<String,Object> map:list){
							String fvalue=""+map.get("课程"+c)+":作业"+map.get("作业"+c)+"%+期中"+map.get("期中"+c)+"%+平时"+map.get("平时"+c)+"%+期末"+map.get("期末"+c)+"";
							fid.add((String)map.get("编号"+c));
							theFunction.addItem(fvalue);
						}
					c++;
					}
				}
				JLabel fff=new JLabel("请选择函数:");
				check.add(fff);
				check.add(theFunction);
			}
		});
		theFunction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DAO dao=new DAO();
				String key[]={"作业","期中","平时","期末"};
				String values[]={"task","mid","pacific","final"};
				String sql="select task,mid,pacific,final from myfunction where id='"+fid.get(theFunction.getSelectedIndex())+"'";
				list=dao.query(sql, values, key);
				if(!list.isEmpty()&&list.size()>0){
					int c=0;
					for(int i=0;i<list.size();i++){
						for(Map<String,Object> map:list){
							function1.setText(map.get("作业"+c)+"作业+");
							function2.setText(map.get("期中"+c)+"期中+");
							function3.setText(map.get("平时"+c)+"平时+");
							function4.setText(map.get("期末"+c)+"期末+");
							a1=(int)map.get("作业"+c);
							a2=(int)map.get("期中"+c);
							a3=(int)map.get("平时"+c);
							a4=(int)map.get("期末"+c);
						}
						c++;
					}
				}
			}
		});
		//筛选方式为成绩
		btn_grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableValueV.clear();
				String[] str={"id"};
				String teacherId="";
				List<String> teamId=new ArrayList<String>();
				DAO dao=new DAO();
				List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
				if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
					teacherId=(String)teacherIds.get(0).get(0);
				}
				String[] s1={"teamId"};
				//获取教师所教的班级id
				List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
				if(!teams.isEmpty()&&teams.get(0)!=null){
					for(Object obj:teams.get(0)){
						teamId.add((String)obj);
					}
				}
				String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
				for(String ids:teamId){
					String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name"
							+ "from student,course,team,grade,team_course"
							+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and team.id=team_course.teamId and team.id='"+ids+"'"
									+ "group by student.id having score "+condition.getText()+"";
					mlist=dao.query(sql, values, key);
					if(!mlist.isEmpty()&& mlist.get(0)!=null){
						int c=0;
						for(int row=1;row<mlist.size();row++){
							Vector<Object> rowV=new Vector<Object>();				//创建行向量
							for(Map<String,Object> m:mlist){
								rowV.add(m.get("学号"+c));
								rowV.add(m.get("名字"+c));
								rowV.add(m.get("课程"+c));
								rowV.add(m.get("学分"+c));
								rowV.add(m.get("课程分类"+c));
								rowV.add(m.get("考核方式"+c));
								rowV.add(m.get("成绩"+c));
								rowV.add(m.get("学分"+c));
								rowV.add(calCredit((int)m.get("成绩"+c)));
								rowV.add(calCredit((int)m.get("成绩"+c))+(int)m.get("学分"+c));
								rowV.add(m.get("班级"+c));
								if(60>=(int)m.get("成绩"+c))sub++;
								all=all+(int)m.get("成绩"+c);
							}
							c++;
							tableValueV.add(rowV);
						}//把行向量添加到数据向量
						average=all/mlist.size();
					}
				}
			}
		});
		//筛选方式为学分
		credit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableValueV.clear();
				String[] str={"id"};
				String teacherId="";
				List<String> teamId=new ArrayList<String>();
				DAO dao=new DAO();
				List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
				if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
					teacherId=(String)teacherIds.get(0).get(0);
				}
				String[] s1={"teamId"};
				//获取教师所教的班级id
				List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
				if(!teams.isEmpty()&&teams.get(0)!=null){
					for(Object obj:teams.get(0)){
						teamId.add((String)obj);
					}
				}
				String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
				for(String ids:teamId){
					String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name"
							+ "from student,course,team,grade,team_course"
							+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and team.id=team_course.teamId and team.id='"+ids+"'"
									+ "group by student.id having credit '"+condition.getText()+"'";
					mlist=dao.query(sql, values, key);
					if(!mlist.isEmpty()&& mlist.get(0)!=null){
						int c=0;
						for(int row=1;row<mlist.size();row++){
							Vector<Object> rowV=new Vector<Object>();				//创建行向量
							for(Map<String,Object> m:mlist){
								rowV.add(m.get("学号"+c));
								rowV.add(m.get("名字"+c));
								rowV.add(m.get("课程"+c));
								rowV.add(m.get("学分"+c));
								rowV.add(m.get("课程分类"+c));
								rowV.add(m.get("考核方式"+c));
								rowV.add(m.get("成绩"+c));
								rowV.add(m.get("学分"+c));
								rowV.add(calCredit((int)m.get("成绩"+c)));
								rowV.add(calCredit((int)m.get("成绩"+c))+(int)m.get("学分"+c));
								rowV.add(m.get("班级"+c));
								if(60>=(int)m.get("成绩"+c))sub++;
								all=all+(int)m.get("成绩"+c);
							}
							c++;
							tableValueV.add(rowV);
						}//把行向量添加到数据向量
						average=all/mlist.size();
					}
				}
			}
		});
		
		//筛选方式为班级
		team.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String[] str={"id,name"};
				String teamId="";
				String team="";
				List<List<Object>> teams=dao.query("select id,name from where name='"+condition.getText()+"'", str);
				if(!teams.isEmpty()&&teams.size()>0){
					teamId=(String)teams.get(0).get(0);
					team=(String)teams.get(0).get(1);
					List<List<Object>> students=dao.query("select id,name from student where teamId='"+teamId+"'", str);
					for(List<Object> ss:students){
						String key[]={"课程","学分","课程分类","考核方式","成绩"};
						String values[]={"name","credit","ctype","ctype2","score"};
						String sql="select name,credit,ctype,ctype2,score from course,grade where course.id=grade.courseId and grade.studentId='"+ss.get(0)+"'";
						List<Map<String,Object>> list=dao.query(sql, values, key);
						if(!list.isEmpty()){
							tableValueV.clear();
							int c=0;
							for(int row=0;row<list.size();row++){
								Vector<Object> rowV=new Vector<Object>();				//创建行向量
								for(Map<String,Object> m:list){
									rowV.add(ss.get(0));
									rowV.add(ss.get(1));
									rowV.add(m.get("课程"+c));
									rowV.add(m.get("学分"+c));
									rowV.add(m.get("课程分类"+c));
									rowV.add(m.get("考核方式"+c));
									rowV.add(m.get("成绩"+c));
									rowV.add(m.get("学分"+c));
									rowV.add(calCredit((int)m.get("成绩"+c)));
									rowV.add(calCredit((int)m.get("成绩"+c))+(int)m.get("学分"+c));
									rowV.add(team);
									if(60>=(int)m.get("成绩"+c))sub++;
									all=all+(int)m.get("成绩"+c);
								}
								c++;
								tableValueV.add(rowV);									//把行向量添加到数据向量
							}
							average=all/list.size();
					}
				}
				}
			}
		});
		//导入Excel数据
		excelInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open(e);
				File file=new File(path);
				com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
				String[] columnName={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				list2=excelOpt.readExcel(file, columnName);
				flag=true;
				if(list2!=null){
					tableValueV.clear();
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();
						for(List<Object>ls:list2){
							rowV.add(ls.get(0));
							rowV.add(ls.get(1));
							rowV.add(ls.get(2));
							rowV.add(ls.get(3));
							rowV.add(ls.get(4));
							rowV.add(ls.get(5));
							rowV.add(ls.get(6));
							rowV.add(ls.get(7));
						}
						tableValueV.add(rowV);
					}
				}
			}
		});
		excelOutput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				list2.clear();
				for(Vector<Object> ss:tableValueV){
					List<Object> ll=new ArrayList<Object>();
					for(int i=0;i<8;i++){
						ll.add(ss.get(i));
						System.out.print(ss.get(i)+"\t");
					}
					System.out.println();
					list2.add(ll);
				}
				button(e);
				String[] columnName={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
				excelOpt.writeExcelBo(path, columnName, list2);
				for(int i=0;i<list2.size();i++){
					//循环读取每一单元格的值
					for(int j=0;j<8;j++){
						//向外写单元格的值
						System.out.print((String)list2.get(i).get(j));
					}
					System.out.println();
				}
			}
		});
		//点击了提交按钮
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				if(!flag){
					String sql="insert into grade(studentId,courseId,score) values('"+sno.getText()+"','"+cid.get(course.getSelectedIndex())+"','"+finalScore()+"')";
					if(dao.add(sql)==1){
						Message message=new Message("添加成功！");
						message.pack();
						update();
					}
				}else{
					int[] x={0};
					for(int i=1;i<list2.size();i++){
						if(dao.query("select * from student where name='"+list2.get(i).get(1)+"'", x).size()==0){
							Message message=new Message("还没有名为"+list2.get(i).get(1)+"的学生");
							message.pack();
						}else{
							if(dao.query("select * from course where name='"+list2.get(i).get(2)+"'", x).size()==0){
								Message message=new Message("还没有名为"+list2.get(i).get(2)+"的课程");
								message.pack();
							}else if(dao.query("select * from team where name='"+list2.get(i).get(7)+"'", x).size()==0){
								Message message=new Message("还没有名为"+list2.get(i).get(7)+"的班级");
								message.pack();
							}else{
								List<List<Object>> listt=dao.query("select * from course where name='"+list2.get(i).get(2)+"'", x);
								String ai="";
								if(listt.size()>0&&listt.get(0).get(0)!=null){
									ai=(String)listt.get(0).get(0);
								}
								String sql="insert into grade(studentId,courseId,score) values('"+list2.get(i).get(0)+"','"+ai+"','"+list2.get(i).get(6)+"')";
								dao.add(sql);
								
							}
						}
					}
					Message message=new Message("添加成功！");
					message.pack();
					update();
				}
			}
		});
		//点击了修改按钮
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update grade set studentId='"+sno.getText()+"',courseId='"+cid.get(course.getSelectedIndex())+"',score='"+finalScore()+"')";
				if(dao.modify(sql)==1){
					Message message=new Message("修改成功！");
					message.pack();
					update();
				}
			}
		});
	}
	 //制作id
	   private String createId(){
		   DAO dao=new DAO();
		   String[] x={"id"};
		   List<List<Object>> list=dao.query("select Max(id) as id from team_course;", x);
		   if(!list.isEmpty()){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(2);
			   return "sc"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "sc1001";
		   }
	   }
	public void initData(){
		tableValueV.clear();
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String[] s1={"teamId"};
		//获取教师所教的班级id
		List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
		if(!teams.isEmpty()&&teams.get(0)!=null){
			for(Object obj:teams.get(0)){
				teamId.add((String)obj);
			}
		}
		String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
		String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
		List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
		for(String ids:teamId){
			String sql="select s.id studentId,s.name studentName,c.name coureName,credit,ctype,ctype2,score,t .name teamName"
					+ "from student s,course,team t,grade g,team_course tc"
					+ "where s .id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+ids+"'";
			mlist=dao.query(sql, values, key);
			if(!mlist.isEmpty()&& mlist.get(0)!=null){
				int c=0;
				for(int row=1;row<mlist.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					for(Map<String,Object> m:mlist){
						rowV.add(m.get("学号"+c));
						rowV.add(m.get("名字"+c));
						rowV.add(m.get("课程"+c));
						rowV.add(m.get("学分"+c));
						rowV.add(m.get("课程分类"+c));
						rowV.add(m.get("考核方式"+c));
						rowV.add(m.get("成绩"+c));
						rowV.add(m.get("学分"+c));
						rowV.add(calCredit((int)m.get("成绩"+c)));
						rowV.add(calCredit((int)m.get("成绩"+c))+(int)m.get("学分"+c));
						rowV.add(m.get("班级"+c));
						if(60>=(int)m.get("成绩"+c))sub++;
						all=all+(int)m.get("成绩"+c);
					}
					c++;
					tableValueV.add(rowV);
				}//把行向量添加到数据向量
				average=all/mlist.size();
			}
		}
		String s[]={"course.id","course.name"};
		List<List<Object>> ls=dao.query("select c.name from course c,team_course tc where c.id=tc.courseId and tc.teacherId='"+teacherId+"'", str);
		if(!ls.isEmpty()&&ls.size()>0){
			for(List<Object> ll:ls){
				for(Object obj:ll){
					cid.add(obj);
					course.addItem(obj);
				}
			}
		}
	}
	public void update(){
		tableValueV.clear();
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String[] s1={"teamId"};
		//获取教师所教的班级id
		List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
		if(!teams.isEmpty()&&teams.get(0)!=null){
			for(Object obj:teams.get(0)){
				teamId.add((String)obj);
			}
		}
		String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
		String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
		List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
		for(String ids:teamId){
			String sql="select s.id studentId,s.name studentName,c.name courseName,credit,ctype,ctype2,score,t.name teamName"
					+ "from student s,course c,team t,grade g,team_course tc"
					+ "where s.id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+ids+"'";
			mlist=dao.query(sql, values, key);
			if(!mlist.isEmpty()&& mlist.get(0)!=null){
				int c=0;
				for(int row=1;row<mlist.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					for(Map<String,Object> m:mlist){
						rowV.add(m.get("学号"+c));
						rowV.add(m.get("名字"+c));
						rowV.add(m.get("课程"+c));
						rowV.add(m.get("学分"+c));
						rowV.add(m.get("课程分类"+c));
						rowV.add(m.get("考核方式"+c));
						rowV.add(m.get("成绩"+c));
						rowV.add(m.get("学分"+c));
						rowV.add(calCredit((int)m.get("成绩"+c)));
						rowV.add(calCredit((int)m.get("成绩"+c))+(int)m.get("学分"+c));
						rowV.add(m.get("班级"+c));
						if(60>=(int)m.get("成绩"+c))sub++;
						all=all+(int)m.get("成绩"+c);
					}
					c++;
					tableValueV.add(rowV);
				}//把行向量添加到数据向量
				average=all/mlist.size();
			}
		}
	}
	//
	private double calCredit(int x){
		return (x-60+10)/10;
	}
	//选中某一行表格的数据，并将其显示在填写栏
		private class MyListener implements ListSelectionListener{
				int f=0;
				@Override
				public void valueChanged(ListSelectionEvent e) {
					f=aim.getFixedColumnTable().getSelectedRow();
					Vector<Object> list=new Vector<Object>();
					list.add(tableValueV.get(f).get(0));
					list.add(tableValueV.get(f).get(1));
					list.add(tableValueV.get(f).get(2));
					list.add(tableValueV.get(f).get(3));
					list.add(tableValueV.get(f).get(4));
					list.add(tableValueV.get(f).get(5));
					list.add(tableValueV.get(f).get(6));
					sno.setText(list.get(0).toString());
					sname.setText(list.get(1).toString());
					grade.setText(list.get(6).toString());		
				}	
		}
		//计算最终总分
	public double finalScore(){
		return Integer.parseInt(te1.getText())*a1+Integer.parseInt(te2.getText())*a2+Integer.parseInt(te3.getText())*a3+Integer.parseInt(te4.getText())*a4;
	}
	 protected void button(ActionEvent e){
			JFileChooser chooser=new JFileChooser();
			FileFilter filter=new FileNameExtensionFilter("文本类型(.xls)","xls");
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setMultiSelectionEnabled(false);
			int result=chooser.showSaveDialog(this);
			if(result==JFileChooser.APPROVE_OPTION){
				File file=chooser.getSelectedFile();
				this.path=file.getAbsolutePath();
				System.out.print(this.path);
			}
		}
	  protected void open(ActionEvent e){
			JFileChooser chooser=new JFileChooser();
			FileFilter filter=new FileNameExtensionFilter("文本类型(.xls)","xls");
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setMultiSelectionEnabled(false);
			int result=chooser.showOpenDialog(this);
			if(result==JFileChooser.APPROVE_OPTION){
				File file=chooser.getSelectedFile();
				this.path=file.getAbsolutePath();
			}
		}
}
