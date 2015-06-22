package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMain frame = new TeacherMain();
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
	public TeacherMain() {
		setTitle("教师主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 540);
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
		
		output = new JMenuItem("导出Excel");
		start.add(output);
		
		checkCourse = new JMenuItem("查看课表");
		start.add(checkCourse);
		
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
		
		JMenu view = new JMenu("视图");
		menuBar.add(view);
		
		pie = new JMenuItem("饼形图");
		view.add(pie);
		
		diagram = new JMenuItem("柱形图");
		view.add(diagram);
		
		JMenu function = new JMenu("函数");
		menuBar.add(function);
		
		setFuntion = new JMenuItem("自定义函数");
		function.add(setFuntion);
		
		selectFuntion = new JMenuItem("选择函数");
		function.add(selectFuntion);
		
		JMenu help = new JMenu("帮助");
		menuBar.add(help);
		
		about = new JMenuItem("关于");
		help.add(about);
		
		JPanel topCenter=new JPanel();//存放浮动式组件
		contentPane.add(topCenter, BorderLayout.CENTER);
		topCenter.setLayout(new BorderLayout(0, 0));
		
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
				
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=1;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int col=0;col<9;col++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		//创建面板，在该面板中实现带行标题栏的表格
		final MFixedColumnTable mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(mainData, BorderLayout.CENTER);		//把面板添加到窗体中央
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
		
		JLabel welcome = new JLabel("欢迎使用");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("操作者：");
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		
		JLabel data = new JLabel("日期");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		JLabel time = new JLabel("现在的时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
	}
	
	public void myEvent(){
		//导出excel
		output.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//查看课表
		checkCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//退出登录
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//筛选方式为成绩
		btn_grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//筛选方式为学分
		credit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//筛选方式为班级
		team.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//饼形图
		pie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//柱形图
		diagram.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//自定义函数设置
		setFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//选择函数
		selectFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//点击了关于按钮
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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

}
