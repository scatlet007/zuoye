package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.GridLayout;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherMain2 extends JFrame {

	private JPanel contentPane;
	private JTextField sno;
	private JTextField sname;
	private JTextField grade;
	private JTextField task;
	private JTextField mid;
	private JTextField pacific;
	private JTextField final1;
	private JTextField pass;
	private JTextField excellent;
	private JTable table;
	private Vector<Vector<Object>> tableValueV;
	private Vector<Vector<Object>> tableValueVs;
	private Vector<Vector<Object>> fValueV;
	private List<Map<String,Object>> list;
	private List<Map<String,Object>> lists;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModels;
	private DefaultTableModel ftableModel;
	private JTextField tasks;
	private JTextField mids;
	private JTextField pacifics;
	private JTextField finalss;
	private JTable functionTable;
	private ListSelectionModel sc;
	private ListSelectionModel ff;
	private JComboBox course;//sc
	private JComboBox team;//f
	private JComboBox courses;
	private JComboBox scno;
	private List<List<Object>> list2=new ArrayList<List<Object>>();
	private boolean flag=false;
	private JButton submit;
	private JButton submit2;
	private JButton modify;
	private JButton modify2;
	private JButton sure;
	private JButton scrn;
	private JLabel task1;
	private JLabel mid1;
	private JLabel pacific1;
	private JLabel finals;
	private JMenuItem input;
	private JMenuItem output;
	private JMenuItem logout;
	private String path;
	private int fk=0;
	private List<String> fid=new ArrayList<String>();
	private String theTeamId="";
	private String theTeacherId="";
	private List<Object> cid=new ArrayList<Object>();
	private int a1;
	private int a2;
	private int a3;
	private int a4;
	private int sub=0;
	private int ex=0;
	private String manager="";
	private JTextField condition;
	private JTable table_1;
	private List<String> courseId=new ArrayList<String>();
	private String teacherId="";
	private List<String> tId=new ArrayList<String>();
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMain2 frame = new TeacherMain2("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public TeacherMain2(String name) {
		setResizable(false);
		manager=name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 939, 537);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("成绩添加", null, panel, null);
		panel.setLayout(null);
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.setBounds(0, 424, 918, 25);
		panel.add(bottom);
		bottom.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel welcome = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		bottom.add(welcome);
		
		JLabel user = new JLabel("\u4F7F\u7528\u8005\uFF1A"+name);
		bottom.add(user);
		
		GetDate getdata2=new GetDate();
		JLabel date = new JLabel("\u65E5\u671F\uFF1A"+getdata2.getDateString());
		bottom.add(date);
		
		GetTime getTime=new GetTime();
		JLabel now = new JLabel("\u73B0\u5728\u65F6\u95F4\uFF1A"+getTime.getTime());
		bottom.add(now);
		this.setTimer(now);
		
		JLabel sno1 = new JLabel("\u5B66\u53F7\uFF1A");
		sno1.setBounds(23, 10, 41, 15);
		panel.add(sno1);
		
		sno = new JTextField();
		sno.setBounds(74, 7, 126, 21);
		panel.add(sno);
		sno.setColumns(10);
		
		JLabel sname1 = new JLabel("\u540D\u5B57\uFF1A");
		sname1.setBounds(236, 10, 54, 15);
		panel.add(sname1);
		
		sname = new JTextField();
		sname.setBounds(287, 7, 126, 21);
		panel.add(sname);
		sname.setColumns(10);
		
		JLabel course1 = new JLabel("\u8BFE\u7A0B\uFF1A");
		course1.setBounds(452, 10, 54, 15);
		panel.add(course1);
		
		course = new JComboBox();
		course.setBounds(506, 7, 96, 21);
		panel.add(course);
		
		JLabel grade1 = new JLabel("\u6210\u7EE9\uFF1A");
		grade1.setBounds(645, 10, 41, 15);
		panel.add(grade1);
		
		grade = new JTextField();
		grade.setEnabled(false);
		grade.setBounds(696, 7, 82, 21);
		panel.add(grade);
		grade.setColumns(10);
		
		JLabel functionLabel = new JLabel("\u51FD\u6570\uFF1A");
		functionLabel.setBounds(23, 48, 41, 15);
		panel.add(functionLabel);
		
		task = new JTextField();
		task.setBounds(74, 45, 60, 21);
		panel.add(task);
		task.setColumns(10);
		
		task1 = new JLabel("x15%\u4F5C\u4E1A+");
		task1.setBounds(144, 48, 65, 15);
		panel.add(task1);
		
		mid = new JTextField();
		mid.setBounds(219, 45, 80, 21);
		panel.add(mid);
		mid.setColumns(10);
		
		mid1 = new JLabel("x20%\u671F\u4E2D+");
		mid1.setBounds(309, 48, 65, 15);
		panel.add(mid1);
		
		pacific = new JTextField();
		pacific.setBounds(385, 45, 82, 21);
		panel.add(pacific);
		pacific.setColumns(10);
		
		pacific1 = new JLabel("x15%\u5E73\u65F6+");
		pacific1.setBounds(477, 48, 63, 15);
		panel.add(pacific1);
		
		final1 = new JTextField();
		final1.setBounds(550, 45, 82, 21);
		panel.add(final1);
		final1.setColumns(10);
		
		finals = new JLabel("x50%\u671F\u672B");
		finals.setBounds(645, 48, 65, 15);
		panel.add(finals);
		
		submit = new JButton("\u63D0\u4EA4");
		submit.setBounds(720, 44, 71, 23);
		panel.add(submit);
		
		modify = new JButton("\u4FEE\u6539");
		modify.setBounds(801, 44, 71, 23);
		panel.add(modify);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 73, 898, 298);
		panel.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		mainData.add(scrollPane);
		
		Vector<Object> columnNameV=new Vector<Object>();	//创建列名向量
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
				rowV.add("");
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		tableModel=new DefaultTableModel(tableValueV, columnNameV);
		table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JLabel give = new JLabel(">>>>>");
		give.setBounds(20, 384, 54, 15);
		panel.add(give);
		
		JLabel pass1 = new JLabel("\u53CA\u683C\u4EBA\u6570\uFF1A");
		pass1.setBounds(74, 384, 60, 15);
		panel.add(pass1);
		
		pass = new JTextField();
		pass.setEnabled(false);
		pass.setBounds(129, 381, 80, 21);
		panel.add(pass);
		pass.setColumns(10);
		
		JLabel excellent1 = new JLabel("80\u5206\u4EE5\u4E0A\uFF1A");
		excellent1.setBounds(236, 384, 71, 15);
		panel.add(excellent1);
		
		excellent = new JTextField();
		excellent.setEnabled(false);
		excellent.setBounds(302, 381, 87, 21);
		panel.add(excellent);
		excellent.setColumns(10);
		
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("自定义函数", null, panel1, null);
		panel1.setLayout(null);
		
		JLabel team1 = new JLabel("\u73ED\u7EA7:");
		team1.setBounds(24, 21, 54, 15);
		panel1.add(team1);
		
		team = new JComboBox();
		team.setBounds(76, 18, 97, 21);
		panel1.add(team);
		
		JLabel course2 = new JLabel("\u8BFE\u7A0B\uFF1A");
		course2.setBounds(197, 21, 54, 15);
		panel1.add(course2);
		
		courses = new JComboBox();
		courses.setBounds(250, 18, 97, 21);
		panel1.add(courses);
		
		JLabel task2 = new JLabel("\u4F5C\u4E1A");
		task2.setBounds(372, 21, 37, 15);
		panel1.add(task2);
		
		tasks = new JTextField();
		tasks.setBounds(417, 18, 139, 21);
		panel1.add(tasks);
		tasks.setColumns(10);
		
		JLabel mid2 = new JLabel("\u671F\u4E2D");
		mid2.setBounds(595, 21, 37, 15);
		panel1.add(mid2);
		
		mids = new JTextField();
		mids.setBounds(642, 18, 124, 21);
		panel1.add(mids);
		mids.setColumns(10);
		
		JLabel pacific2 = new JLabel("\u5E73\u65F6");
		pacific2.setBounds(24, 63, 48, 15);
		panel1.add(pacific2);
		
		pacifics = new JTextField();
		pacifics.setBounds(77, 60, 96, 21);
		panel1.add(pacifics);
		pacifics.setColumns(10);
		
		JLabel finals2 = new JLabel("\u671F\u672B");
		finals2.setBounds(197, 63, 37, 15);
		panel1.add(finals2);
		
		finalss = new JTextField();
		finalss.setBounds(250, 60, 97, 21);
		panel1.add(finalss);
		finalss.setColumns(10);
		
		submit2 = new JButton("\u63D0\u4EA4");
		submit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		submit2.setBounds(574, 59, 93, 23);
		panel1.add(submit2);
		
		modify2 = new JButton("\u4FEE\u6539");
		modify2.setBounds(688, 59, 93, 23);
		panel1.add(modify2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 103, 898, 299);
		panel1.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		Vector<Object> fcolumnNameV=new Vector<Object>();	//创建列名向量
		fcolumnNameV.add("编号");
		fcolumnNameV.add("班级");
		fcolumnNameV.add("课程");
		fcolumnNameV.add("作业");
		fcolumnNameV.add("期中");
		fcolumnNameV.add("平时");
		fcolumnNameV.add("期末");
		fValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=0;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int col=0;col<7;col++){
				rowV.add("");
			}
			fValueV.add(rowV);									//把行向量添加到数据向量
		}
		ftableModel=new DefaultTableModel(fValueV, fcolumnNameV);
		functionTable = new JTable(ftableModel);
		scrollPane_1.setViewportView(functionTable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(0, 428, 918, 21);
		panel1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel welcomes = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		panel_2.add(welcomes);
		
		JLabel users = new JLabel("\u4F7F\u7528\u8005\uFF1A"+name);
		panel_2.add(users);
		
		GetDate getdata=new GetDate();
		JLabel date2 = new JLabel("\u65E5\u671F\uFF1A"+getdata.getDateString());
		panel_2.add(date2);
		
		GetTime getTime2=new GetTime();
		JLabel now2 = new JLabel("\u73B0\u5728\u65F6\u95F4\uFF1A"+getTime2.getTime());
		panel_2.add(now2);
		this.setTimer(now2);
		
		JButton sure = new JButton("\u9009\u5B9A");
		sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Object> list=new Vector<Object>();
				list.add(fValueV.get(fk).get(0));
				list.add(fValueV.get(fk).get(1));
				list.add(fValueV.get(fk).get(2));
				list.add(fValueV.get(fk).get(3));
				list.add(fValueV.get(fk).get(4));
				list.add(fValueV.get(fk).get(5));
				list.add(fValueV.get(fk).get(6));
				tasks.setText(list.get(3).toString());
				mids.setText(list.get(4).toString());
				pacifics.setText(list.get(5).toString());
				finalss.setText(list.get(6).toString());
				a1=Integer.parseInt(tasks.getText());
				a2=Integer.parseInt(mids.getText());
				a3=Integer.parseInt(pacifics.getText());
				a4=Integer.parseInt(finalss.getText());
				task1.setText("作业"+a1+"%+");
				mid1.setText("期中"+a2+"%+");
				pacific1.setText("平时"+a3+"%+");
				finals.setText("期末"+a4+"%+");
			}
		});
		sure.setBounds(450, 59, 93, 23);
		panel1.add(sure);
		
		JPanel panel3 = new JPanel();
		tabbedPane.addTab("成绩管理", null, panel3, null);
		panel3.setLayout(null);
		
		JLabel tiaojian = new JLabel("\u68C0\u7D22\u6761\u4EF6");
		tiaojian.setBounds(175, 10, 54, 15);
		panel3.add(tiaojian);
		
		condition = new JTextField();
		condition.setBounds(352, 7, 207, 21);
		panel3.add(condition);
		condition.setColumns(10);
		
		scno = new JComboBox();
		scno.setBounds(239, 7, 86, 21);
		panel3.add(scno);
		scno.addItem("班级");
		scno.addItem("成绩");
		scno.addItem("学分");
		
		scrn = new JButton("\u68C0\u7D22");
		scrn.setBounds(581, 10, 93, 23);
		panel3.add(scrn);
		
		JPanel centers = new JPanel();
		centers.setBounds(10, 54, 898, 332);
		panel3.add(centers);
		centers.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		centers.add(scrollPane_2);
		
		Vector<Object> columnNameVs=new Vector<Object>();	//创建列名向量
		columnNameVs.add("学号");
		columnNameVs.add("名字");
		columnNameVs.add("课程");
		columnNameVs.add("学分");
		columnNameVs.add("课程分类");
		columnNameVs.add("考核方式");
		columnNameVs.add("成绩");
		columnNameVs.add("取得学分");
		columnNameVs.add("绩点");
		columnNameVs.add("取得绩点");
		tableValueVs=new Vector<Vector<Object>>();//创建数据向量
		for(int row=0;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int col=0;col<9;col++){
				rowV.add("");
			}
			tableValueVs.add(rowV);									//把行向量添加到数据向量
		}
		tableModels=new DefaultTableModel(tableValueVs, columnNameVs);
		table_1 = new JTable(tableModels);
		table_1.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(table_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 419, 918, 30);
		panel3.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel welcone2 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		panel_3.add(welcone2);
		
		JLabel user2 = new JLabel("\u4F7F\u7528\u8005\uFF1A"+name);
		panel_3.add(user2);
		
		GetDate g=new GetDate();
		JLabel date3 = new JLabel("\u65E5\u671F\uFF1A"+g.getDateString());
		panel_3.add(date3);
		
		GetTime t=new GetTime();
		JLabel now3 = new JLabel("\u73B0\u5728\u65F6\u95F4\uFF1A"+t.getTime());
		panel_3.add(now3);
		this.setTimer(now3);
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu file = new JMenu("\u6587\u4EF6");
		menuBar.add(file);
		
		input = new JMenuItem("Excel\u6570\u636E\u5BFC\u5165");
		file.add(input);
		
		output = new JMenuItem("Excel\u6570\u636E\u5BFC\u51FA");
		file.add(output);
		
		logout = new JMenuItem("\u9000\u51FA");
		file.add(logout);
		myEvent();
		myEventf();
		myEventscs();
		initf();
		initSc();
		initscs();
		setVisible(true);	
		}
	public void myEvent(){
		sc=table.getSelectionModel();
		sc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sc.addListSelectionListener(new scTableListener());
		
		ff=functionTable.getSelectionModel();
		ff.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ff.addListSelectionListener(new fTableListener());
		
		//导入Excel数据
		input.addActionListener(new ActionListener() {
					
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
		output.addActionListener(new ActionListener() {
					
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
								updatesc();
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
										String sql="insert into grade(studentId,courseId,score) values"
												+ "('"+list2.get(i).get(0)+"','"+ai+"','"+list2.get(i).get(6)+"')";
										dao.add(sql);
										
									}
								}
							}
							Message message=new Message("添加成功！");
							message.pack();
							updatesc();
							Function function=new Function(manager);
							function.setVisible(false);
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
							updatesc();
							Function function=new Function(manager);
							function.setVisible(false);
						}
					}
				});
	}
	public void myEventf(){
		submit2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DAO dao=new DAO();
				String sql="insert into myfunction(id,courseId,teamId,teacherId,task,mid,pacific,final) values"
						+ "('"+createIdf()+"'"
								+ ",'"+courseId.get(courses.getSelectedIndex())+"',"
										+ "'"+tId.get(team.getSelectedIndex())+"',"
												+ "'"+teacherId+"',"
								+ "'"+tasks.getText()+"','"+mids.getText()+"','"+pacifics.getText()+"','"+finalss.getText()+"');";
				if(dao.add(sql)==1){
					Message message=new Message("执行成功！");
					message.pack();
					updatef();
					Function function=new Function(manager);
					function.setVisible(false);
				}
			}
		});
		modify2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update myfunction set"
						+ "courseId='"+courseId.get(courses.getSelectedIndex())+"',"
								+ "teamId='"+tId.get(team.getSelectedIndex())+"',"
										+ "teacherId='"+teacherId+"',"
								+ "task='"+tasks.getText()+"',mid='"+mids.getText()+"',pacific='"+pacifics.getText()+"',final='"+finalss.getText()+"';";
				if(dao.modify(sql)==1){
					Message message=new Message("执行成功！");
					message.pack();
					updatef();
					Function function=new Function(manager);
					function.setVisible(false);
				}
				
			}
		});
	}
	public void myEventscs(){
		//条件检索
				scrn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] str={"id"};
						tableValueVs.clear();
						DAO dao=new DAO();
						String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
						String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
						List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
						String sql="";
						String item=(String)scno.getItemAt(scno.getSelectedIndex());
						if(item.equals("成绩")){
								sql="select student.id studentId,student.name studentName,course.name courseName,credit,ctype,ctype2,score,team.name teamName "
										+ "from student,course,team,grade,team_course "
										+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and team.id=team_course.teamId and team.id='"+theTeamId+"'"
												+ " and team_course.teacherId='"+theTeacherId+"' and score"+condition.getText()+"";
						}else if(item.equals("学分")){
								sql="select student.id studentId,student.name studentName,course.name courseName,credit,ctype,ctype2,score,team.name teamName "
									+ "from student,course,team,grade,team_course "
									+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and team.id=team_course.teamId and team.id='"+theTeamId+"'"
											+ " and team_course.teacherId='"+theTeacherId+"' and credit"+condition.getText()+"";
						}else if(item.equals("班级")){
							
							String[] k={"编号"};
							String[] v={"teamId"};
							List<Map<String,Object>> ts=dao.query("select id teamId from team where name='"+condition.getText()+"';", v, k);
							if(!ts.isEmpty()&&ts.size()>0){
								int c=0;
								for(Map<String,Object> m:ts){
									theTeamId=m.get("编号"+c).toString();
									c++;
								}
							}
							sql="select student.id studentId,student.name studentName,course.name courseName,credit,ctype,ctype2,score,team.name teamName "
									+ "from student,course,team t1,grade,team_course "
									+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and t1.id=team_course.teamId and"
									+ " and team_course.teacherId='"+theTeacherId+"' and t1.id='"+theTeamId+"')";
							String courseV[]={"courseId","courseName"};
							String coursekey[]={"编号","课程名"};
							List<Map<String,Object>> ls=dao.query("select distinct c.id courseId,c.name courseName "
									+ "from course c , team_course tc "
									+ "where c.id=tc.courseId and tc.teacherId='"+theTeacherId+"' and tc.teamId='"+theTeamId+"'",courseV,coursekey);
							if(!ls.isEmpty()&&ls.size()>0){
								int c=0;
								course.removeAllItems();
								for(Map<String,Object> obj:ls){
									cid.add(obj.get("编号"+c).toString());
									course.addItem(obj.get("课程名"+c).toString());
									System.out.println(obj.get("编号"+c).toString()+"\t"+obj.get("课程名"+c).toString());
									c++;
								}
							}
							//获取教师的自定义函数
							String key2[]={"编号","教师名","课程","作业","期中","平时","期末"};
							String values2[]={"functionId","teacherName","courseName","task","mid","pacific","final"};
							String sqls="select f.id functionId,t.name teacherName,c.name courseName,task,mid,pacific,final "
									+ "from myfunction f,teacher t,course c "
									+ "where f.teacherId=t.id and f.courseId=c.id and t.id='"+theTeacherId+"' and f.teamId='"+theTeamId+"'";
							list=dao.query(sqls, values2, key2);
							if(!list.isEmpty()&&list.get(0)!=null){
								int c=0;
								//theFunction.removeAllItems();
								for(Map<String,Object> map:list){
									String fvalue=""+map.get("课程"+c)+":作业"+map.get("作业"+c)+"%+期中"+map.get("期中"+c)+"%+平时"+map.get("平时"+c)+"%+期末"+map.get("期末"+c)+"";
									fid.add((String)map.get("编号"+c));
									//theFunction.addItem(fvalue);
									c++;
								}
							}
							
						}
						mlist=dao.query(sql, values, key);
						if(!mlist.isEmpty()&& mlist.get(0)!=null){
							int c=0;
							tableValueVs.clear();
							System.out.println("scrn");
							for(int row=0;row<mlist.size();row++){
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
									rowV.add(calCredit(Double.valueOf((mlist.get(0).get("成绩"+c).toString()))));
									rowV.add(calCredit(Integer.parseInt(mlist.get(0).get("成绩"+c).toString())+Double.valueOf((mlist.get(0).get("学分"+c).toString()))));
									rowV.add(mlist.get(0).get("班级"+c));
									if(60>=Integer.parseInt(mlist.get(0).get("成绩"+c).toString()))sub++;
									if(80<=Integer.parseInt(mlist.get(0).get("成绩"+c).toString()))ex++;
									//all=all+Integer.parseInt(mlist.get(0).get("成绩"+c).toString());
								}
								c++;
								tableValueVs.add(rowV);
							}//把行向量添加到数据向量
							//average=all/mlist.size();
							pass.setText(sub+"");
							excellent.setText(ex+"");
						}
						Function function=new Function(manager);
						function.setVisible(false);
					}
				});
	}
	public void updatesc(){
		tableValueV.clear();
		String[] str={"id"};
		DAO dao=new DAO();
		//取出该教师所教的班级的学生的成绩，默认显示第一个班级的学生
				String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
				String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
				List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
				//for(String ids:teamId){
					String sqls="select distinct s.id studentId,s.name studentName,c.name courseName,credit,ctype,ctype2,score,t.name teamName"
							+ " from student s,course c,team t,grade g,team_course tc "
							+ "where s.id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+theTeamId+"'"
									+ " and tc.teacherId='"+theTeacherId+"'";
					mlist=dao.query(sqls, values, key);
					if(!mlist.isEmpty()&& mlist.get(0)!=null){
						int c=0;
						//Vector<Object> rowV=new Vector<Object>();
						for(int row=0;row<mlist.size();row++){
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
								rowV.add(calCredit(Double.valueOf((m.get("成绩"+c).toString()))));
								rowV.add(calCredit(Integer.parseInt(m.get("成绩"+c).toString())+Double.valueOf((m.get("学分"+c).toString()))));
								rowV.add(m.get("班级"+c));
								if(60>=Integer.parseInt(m.get("成绩"+c).toString()))sub++;
								if(80<=Integer.parseInt(m.get("成绩"+c).toString()))sub++;
							}
							c++;
							tableValueV.add(rowV);
						}//把行向量添加到数据向量
						//tableValueV.add(rowV);
						pass.setText(sub+"");
						excellent.setText(ex+"");
					}
				//}
					String valuess[]={"courseId","courseName"};
					String keys[]={"编号","课程名"};
					List<Map<String,Object>> ls=dao.query("select distinct c.id courseId,c.name courseName "
							+ "from course c , team_course tc "
							+ "where c.id=tc.courseId and tc.teacherId='"+theTeacherId+"' and tc.teamId='"+theTeamId+"'",valuess,keys);
					if(!ls.isEmpty()&&ls.size()>0){
						int c=0;
						for(Map<String,Object> obj:ls){
							cid.add(obj.get("编号"+c).toString());
							course.addItem(obj.get("课程名"+c).toString());
							System.out.println(obj.get("编号"+c).toString()+"\t"+obj.get("课程名"+c).toString());
							c++;
						}
					}
					//获取教师的自定义函数
					String key2[]={"编号","教师名","课程","作业","期中","平时","期末"};
					String values2[]={"functionId","teacherName","courseName","task","mid","pacific","final"};
					String sql="select f.id functionId,t.name teacherName,c.name courseName,task,mid,pacific,final "
							+ "from myfunction f,teacher t,course c "
							+ "where f.teacherId=t.id and f.courseId=c.id and t.id='"+theTeacherId+"' and f.teamId='"+theTeamId+"'";
					list=dao.query(sql, values2, key2);
					if(!list.isEmpty()&&list.get(0)!=null){
						int c=0;
						for(Map<String,Object> map:list){
							String fvalue=""+map.get("课程"+c)+":作业"+map.get("作业"+c)+"%+期中"+map.get("期中"+c)+"%+平时"+map.get("平时"+c)+"%+期末"+map.get("期末"+c)+"";
							fid.add((String)map.get("编号"+c));
							//theFunction.addItem(fvalue);
							c++;
						}
					}
	}
	public void updatef(){
		String[] str={"id"};
		String teacherId="";
		//List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String key[]={"课程","作业","期中","平时","期末"};
		String values[]={"courseName","task","mid","pacific","final"};
		String sql="select c.name courseName,task,mid,pacific,final from course c,myfunction mf where c.id=mf.courseId and mf.teacherId='"+teacherId+"'";
		list=dao.query(sql, values, key);
		if(!list.isEmpty()&&list.size()>0){
			fValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("课程"+c));
					rowV.add(m.get("作业"+c));
					rowV.add(m.get("期中"+c));
					rowV.add(m.get("平时"+c));
					rowV.add(m.get("期末"+c));
				}
				c++;
				fValueV.add(rowV);									//把行向量添加到数据向量
			}
		}
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
	 private class scTableListener implements ListSelectionListener{
		 int f=0;
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			f=table.getSelectedRow();
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
	 private class fTableListener implements ListSelectionListener{
		 	int f=0;
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				fk=f=functionTable.getSelectedRow();
				Vector<Object> list=new Vector<Object>();
				list.add(fValueV.get(f).get(0));
				list.add(fValueV.get(f).get(1));
				list.add(fValueV.get(f).get(2));
				list.add(fValueV.get(f).get(3));
				list.add(fValueV.get(f).get(4));
				list.add(fValueV.get(f).get(5));
				list.add(fValueV.get(f).get(6));
				tasks.setText(list.get(3).toString());
				mids.setText(list.get(4).toString());
				pacifics.setText(list.get(5).toString());
				finalss.setText(list.get(6).toString());	
			}
			 
		 }
	 //制作id
	   private String createIdsc(){
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
	   private String createIdf(){
		   DAO dao=new DAO();
		   String[] x={"id"};
		   List<List<Object>> list=dao.query("select Max(id) as id from myfunction;", x);
		   if(!list.isEmpty()&&list.get(0).get(0)!=null){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(8);
			   return "function"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "function1001";
		   }
	   }
	public void initSc(){
		tableValueV.clear();
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
			theTeacherId=teacherId;
		}
		String[] s1={"teamId"};
		//获取教师所教的班级id
		List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+theTeacherId+"'", s1);
		if(!teams.isEmpty()&&teams.get(0)!=null){
			for(Object obj:teams.get(0)){
				teamId.add((String)obj);
			}
			theTeamId=teamId.get(0);
		}
		//取出该教师所教的班级的学生的成绩，默认显示第一个班级的学生
		String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
		String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
		List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
		//for(String ids:teamId){
			String sqls="select distinct s.id studentId,s.name studentName,c.name courseName,credit,ctype,ctype2,score,t.name teamName"
					+ " from student s,course c,team t,grade g,team_course tc "
					+ "where s.id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+theTeamId+"'"
							+ " and tc.teacherId='"+theTeacherId+"'";
			mlist=dao.query(sqls, values, key);
			if(!mlist.isEmpty()&& mlist.get(0)!=null){
				int c=0;
				//Vector<Object> rowV=new Vector<Object>();
				for(int row=0;row<mlist.size();row++){
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
						rowV.add(calCredit(Double.valueOf((m.get("成绩"+c).toString()))));
						rowV.add(calCredit(Integer.parseInt(m.get("成绩"+c).toString())+Double.valueOf((m.get("学分"+c).toString()))));
						rowV.add(m.get("班级"+c));
						if(60>=Integer.parseInt(m.get("成绩"+c).toString()))sub++;
						if(80<=Integer.parseInt(m.get("成绩"+c).toString()))ex++;
						//all=all+Integer.parseInt(m.get("成绩"+c).toString());
					}
					c++;
					tableValueV.add(rowV);
				}//把行向量添加到数据向量
				//tableValueV.add(rowV);
				//average=all/mlist.size();
				pass.setText(sub+"");
				excellent.setText(ex+"");
			}
		//}
		String valuess[]={"courseId","courseName"};
		String keys[]={"编号","课程名"};
		List<Map<String,Object>> ls=dao.query("select distinct c.id courseId,c.name courseName "
				+ "from course c , team_course tc "
				+ "where c.id=tc.courseId and tc.teacherId='"+teacherId+"' and tc.teamId='"+theTeamId+"'",valuess,keys);
		if(!ls.isEmpty()&&ls.size()>0){
			int c=0;
			for(Map<String,Object> obj:ls){
				cid.add(obj.get("编号"+c).toString());
				course.addItem(obj.get("课程名"+c).toString());
				System.out.println(obj.get("编号"+c).toString()+"\t"+obj.get("课程名"+c).toString());
				c++;
			}
		}
	}
	public void initf(){
		String[] str={"id"};
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String key[]={"班级","课程","作业","期中","平时","期末"};
		String values[]={"teamName","courseName","task","mid","pacific","final"};
		String sql="select t.name teamName,c.name courseName,task,mid,pacific,final "
				+ "from course c,myfunction mf,team t where"
				+ " c.id=mf.courseId and t.id=mf.teamId and mf.teacherId='"+teacherId+"'";
		lists=dao.query(sql, values, key);
		if(!lists.isEmpty()&&lists.size()>0){
			fValueV.clear();
			int c=0;
			for(int row=0;row<lists.size();row++){
				Vector<Object> rowV=new Vector<Object>();//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:lists){
					rowV.add(m.get("班级"+c));
					rowV.add(m.get("课程"+c));
					rowV.add(m.get("作业"+c));
					rowV.add(m.get("期中"+c));
					rowV.add(m.get("平时"+c));
					rowV.add(m.get("期末"+c));
				}
				c++;
				fValueV.add(rowV);		//把行向量添加到数据向量
			}
		}
		
		String value[]={"courseId","courseName"};
		String k[]={"编号","课程"};
		String sql2="select distinct c.id courseId,c.name courseName from course c,team_course tc where c.id=tc.courseId and tc.teacherId='"+teacherId+"'";
		List<Map<String,Object>> courseIds=dao.query(sql2, value, k);
		if(!courseIds.isEmpty()&&courseIds.size()>0){
			int c=0;
			for(Map<String,Object> obj:courseIds){
				courseId.add(obj.get("编号"+c).toString());
				courses.addItem(obj.get("课程"+c).toString());
				System.out.println(obj.get("编号"+c).toString()+"\t"+obj.get("课程"+c).toString());
				c++;
			}
		}
		String[] tid={"teamId","teamName"};
		String tk[]={"编号","班名"};
		String sql3="select distinct t.id teamId,t.name teamName from team t,team_course tc where t.id=tc.teamId and tc.teacherId='"+teacherId+"'";
		List<Map<String,Object>> teamIds=dao.query(sql3, tid, tk);
		if(!teamIds.isEmpty()&&teamIds.size()>0){
			int c=0;
			for(Map<String,Object> obj:teamIds){
				tId.add(obj.get("编号"+c).toString());
				team.addItem(obj.get("班名"+c).toString());
				System.out.println(obj.get("编号"+c).toString()+"\t"+obj.get("班名"+c).toString());
				c++;
			}
		}
	}
	public void initscs(){
		tableValueVs.clear();
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
			theTeacherId=teacherId;
		}
		String[] s1={"teamId"};
		//获取教师所教的班级id
		List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+theTeacherId+"'", s1);
		if(!teams.isEmpty()&&teams.get(0)!=null){
			for(Object obj:teams.get(0)){
				teamId.add((String)obj);
			}
			theTeamId=teamId.get(0);
		}
		//取出该教师所教的班级的学生的成绩，默认显示第一个班级的学生
		String key[]={"学号","名字","课程","学分","课程分类","考核方式","成绩","班级"};
		String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
		List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
		//for(String ids:teamId){
			String sqls="select distinct s.id studentId,s.name studentName,c.name courseName,credit,ctype,ctype2,score,t.name teamName"
					+ " from student s,course c,team t,grade g,team_course tc "
					+ "where s.id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+theTeamId+"'"
							+ " and tc.teacherId='"+theTeacherId+"'";
			mlist=dao.query(sqls, values, key);
			if(!mlist.isEmpty()&& mlist.get(0)!=null){
				int c=0;
				//Vector<Object> rowV=new Vector<Object>();
				for(int row=0;row<mlist.size();row++){
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
						rowV.add(calCredit(Double.valueOf((m.get("成绩"+c).toString()))));
						rowV.add(calCredit(Integer.parseInt(m.get("成绩"+c).toString())+Double.valueOf((m.get("学分"+c).toString()))));
						rowV.add(m.get("班级"+c));
						//if(60>=Integer.parseInt(m.get("成绩"+c).toString()))sub++;
						//if(80<=Integer.parseInt(m.get("成绩"+c).toString()))ex++;
						//all=all+Integer.parseInt(m.get("成绩"+c).toString());
					}
					c++;
					tableValueVs.add(rowV);
				}//把行向量添加到数据向量
				//tableValueV.add(rowV);
				//average=all/mlist.size();
				//pass.setText(sub+"");
				//excellent.setText(ex+"");
			}
	}
	private double calCredit(double d){
		return (d-60+10)/10;
	}
	//计算最终总分
	public int finalScore(){
		return (int)(Integer.parseInt(task.getText())*a1/100+Integer.parseInt(mids.getText())*a2/100+Integer.parseInt(pacifics.getText())*a3/100+Integer.parseInt(final1.getText())*a4/100);
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
