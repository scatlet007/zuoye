package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JButton submit;//
	private JButton modify;//
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String manager;
	private JComboBox course;
	/**
	 * Launch the application.
	 */
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
		start.add(logout);
		
		JMenu screen = new JMenu("筛选");
		menuBar.add(screen);
		
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
		grade1=new JLabel("成绩");
		this.grade=new JTextField(10);
		addTop.add(sno1);
		addTop.add(sno);
		addTop.add(sname1);
		addTop.add(sname);
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
		modify=new JButton("修改");
		addButton.add(submit);
		addButton.add(modify);
		//
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("及格人数：");
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("平均成绩：");
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
		//筛选方式为成绩
		btn_grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name "
						+ "from student,grade,team "
						+ "where team.id in(select student.teamId from student where student.id in(select team_course.sno from grade group by grade having score'"+condition.getText()+"'))"
								+ "and course.id=grade.id and student.id=grade.studentId";
				List<Map<String,Object>> list=dao.query(sql, values, key);
				if(!list.isEmpty()){
					tableValueV.clear();
					int c=0;
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//创建行向量
						for(Map<String,Object> m:list){
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
						}
						c++;
						tableValueV.add(rowV);									//把行向量添加到数据向量
					}
				}
			}
		});
		//筛选方式为学分
		credit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name "
						+ "from student,grade,team "
						+ "where team.id in(select student.teamId from student where student.id in(select grade.studentId from grade group by grade having score '"+(Integer.parseInt(condition.getText())*10+50)+"'))"
								+ "and course.id=grade.id and student.id=grade.studentId";
				List<Map<String,Object>> list=dao.query(sql, values, key);
				if(!list.isEmpty()){
					tableValueV.clear();
					int c=0;
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//创建行向量
						for(Map<String,Object> m:list){
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
						}
						c++;
						tableValueV.add(rowV);									//把行向量添加到数据向量
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
							for(int row=1;row<list.size();row++){
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
								}
								c++;
								tableValueV.add(rowV);									//把行向量添加到数据向量
							}
					}
				}
				}
			}
		});
		//点击了提交按钮
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//点击了修改按钮
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
		String[] str={"id"};
		DAO dao=new DAO();
		List<List<Object>> teacherId=dao.query("select id from teacher where name='"+manager+"'", str);
		String[] s1={"teamId","courseId"};
		List<List<Object>> ss=dao.query("select teamId,courseId fromteam_course where teacherId='"+teacherId.get(0).get(0)+"'", s1);
		for(List<Object> ls:ss){
			String[] sc={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
			String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score"
					+ "from student,course,grade,team"
					+ "where student.is=grade.studentId and course.id='"+ls.get(1)+"' and team.name='"+ls.get(0)+"' and grade.courseId=course.id and student.teamId=team.id";
			List<List<Object>> sc2=dao.query(sql, str);
		}
	}
	public void update(){
		DAO dao=new DAO();
		String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
		String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
		String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name "
				+ "from student,course,team,grade "
				+ "where team.id in(select student.teamId from student where student.id in(select grade.studentId from grade)) and student.id=grade.studentId and grade.courseId=course.id";
		List<Map<String,Object>> list=dao.query(sql, values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			int c=0;
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				for(Map<String,Object> m:list){
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
				}
				c++;
				tableValueV.add(rowV);									//把行向量添加到数据向量
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
}
