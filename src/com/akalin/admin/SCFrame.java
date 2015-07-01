package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.JMenuBar;

import com.akalin.dao.DAO;
import com.akalin.tool.DateChooserJButton;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

import javax.swing.JComboBox;

import java.awt.FlowLayout;

public class SCFrame extends JFrame {

	private JPanel contentPane;
	private JTextField creates;
	private String thename;
	private String thecreate;
	private List<Map<String,Object>> list;
	private List<List<Object>> list2=new ArrayList<List<Object>>();
	private ListSelectionModel fixed;
	private boolean flag=false;
	private MFixedColumnTable mainData;
	private Vector<Vector<Object>> tableValueV;
	private JComboBox teamName;
	private JComboBox teacher;
	private JComboBox course;
	private JComboBox week;
	private JComboBox start;
	private JComboBox length;
	private JButton submit;
	private JButton modify;
	private JMenuItem collegeAdd;
	private JMenuItem excelInput;
	private JMenuItem excelOutput;
	private JMenuItem majorAdd;
	private JMenuItem teamAdd;
	private JMenuItem teacherAdd;
	private JMenuItem studentAdd;
	private JMenuItem courseAdd;
	private JMenuItem roleAdd;
	private JMenuItem scAdd;
	private String manager;
	private List<String> teamId;
	private List<String> teacherId;
	private List<String> courseId;
	private String teamId2;
	private String teacherId2;
	private String courseId2;
	private boolean teamFlag=false;
	private boolean teacherFlag=false;
	private boolean courseFlag=false;
	private String path;
	private JTextField hour;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Create the frame.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SCFrame frame = new SCFrame("");
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
	public SCFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	
	public void init(){
		this.setTitle("学院管理界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 550);
		this.setLocationRelativeTo(null);//窗口在屏幕中间显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setBorder(new EmptyBorder(0, 0, 30, 0));
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.CENTER);
		
		JMenu collegeManaer = new JMenu("学院管理");
		menuBar.add(collegeManaer);
		
		JMenu majorManager = new JMenu("专业管理");
		menuBar.add(majorManager);
		
		JMenu teamManager = new JMenu("班级管理");
		menuBar.add(teamManager);
		
		JMenu teacherManager = new JMenu("教师管理");
		menuBar.add(teacherManager);
		
		JMenu studentManager = new JMenu("学生管理");
		menuBar.add(studentManager);
		
		JMenu courseManager = new JMenu("课程管理");
		menuBar.add(courseManager);
		
		JMenu roleManager = new JMenu("角色管理");
		menuBar.add(roleManager);
		
		JMenu scManager=new JMenu("班级课表管理");
		menuBar.add(scManager);
		
		collegeAdd=new JMenuItem("学院添加");
		collegeAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		excelInput=new JMenuItem();
		excelInput.setText("Excel数据导入");
		excelOutput=new JMenuItem();
		excelOutput.setText("导出Excel文件");
		collegeManaer.add(collegeAdd);
		majorAdd=new JMenuItem("专业添加");
		majorAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		majorManager.add(majorAdd);
		teamAdd=new JMenuItem("班级添加"); 
		teamAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		teamManager.add(teamAdd);
		teacherAdd=new JMenuItem("教师添加");
		teacherAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		teacherManager.add(teacherAdd);
		studentAdd=new JMenuItem("学生添加");
		studentAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		courseAdd=new JMenuItem("课程添加");
		courseManager.add(courseAdd);
		courseAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		studentManager.add(studentAdd);
		roleAdd=new JMenuItem("角色添加");
		roleAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		roleManager.add(roleAdd);
		scAdd=new JMenuItem("班级课表添加");
		scManager.add(scAdd);
		scManager.add(excelInput);
		scManager.add(excelOutput);
		
		JPanel panel_1 = new JPanel();
		top.add(panel_1, BorderLayout.SOUTH);
		panel_1.setBounds(0, 0, 675, 250);
		panel_1.setLayout(new BorderLayout());
		panel_1.setLayout(new BorderLayout());
		
		JPanel p1=new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) p1.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignOnBaseline(true);
		panel_1.add(p1,BorderLayout.NORTH);
		JLabel team1 = new JLabel("\u73ED\u7EA7");
		p1.add(team1);
		
		teamName = new JComboBox();
		teamName.addItem("13计科");
		p1.add(teamName);
		
		JLabel teacher1 = new JLabel("\u6559\u5E08");
		p1.add(teacher1);
		
		teacher = new JComboBox();
		p1.add(teacher);
		
		JLabel course1 = new JLabel("\u79D1\u76EE\uFF1A");
		p1.add(course1);
		
		course = new JComboBox();
		p1.add(course);
		
		JLabel week1 = new JLabel("\u65E5\u671F:");
		p1.add(week1);
		
		week = new JComboBox();
		p1.add(week);
		week.addItem("星期日");
		week.addItem("星期一");
		week.addItem("星期二");
		week.addItem("星期三");
		week.addItem("星期四");
		week.addItem("星期五");
		week.addItem("星期六");
		
		JPanel p2=new JPanel();
		FlowLayout flowLayout = (FlowLayout) p2.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignOnBaseline(true);
		panel_1.add(p2,BorderLayout.CENTER);
		p2.setBorder(new EmptyBorder(3, 3, 3, 3));
		
		JLabel start1 = new JLabel("\u5F00\u59CB\u65F6\u95F4\uFF1A");
		p2.add(start1);
		
		start = new JComboBox();
		p2.add(start);
		start.addItem("1");
		start.addItem("2");
		start.addItem("3");
		start.addItem("4");
		start.addItem("5");
		start.addItem("6");
		start.addItem("7");
		start.addItem("8");
		start.addItem("9");
		start.addItem("10");
		start.addItem("11");
		start.addItem("12");
		
		JLabel length1 = new JLabel("\u4E0A\u51E0\u8282\u8BFE\uFF1A");
		p2.add(length1);
		
		length = new JComboBox();
		p2.add(length);
		length.addItem("1");
		length.addItem("2");
		length.addItem("3");
		
		JLabel hour1 = new JLabel("\u8BFE\u65F6\uFF1A");
		p2.add(hour1);
		
		hour = new JTextField();
		p2.add(hour);
		hour.setColumns(10);
		submit = new JButton("提交");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		p2.add(submit);
		modify = new JButton("修改");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		p2.add(modify);
		
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("编号");
		columnNameV.add("班级");
		columnNameV.add("教师");
		columnNameV.add("科目");
		columnNameV.add("日期");
		columnNameV.add("第几节课开始");
		columnNameV.add("长度");
		columnNameV.add("课时");
								
		tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int cov=0;cov<8;cov++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		//创建面板，在该面板中实现带行标题栏的表格
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(mainData, BorderLayout.CENTER);		//把面板添加到窗体中央
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, BorderLayout.SOUTH);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("欢迎使用");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("操作者："+manager);
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("日期："+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("现在的时间是："+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		this.setTimer(time);
		JTable f=mainData.getFixedColumnTable();
		TableColumnModel c=mainData.getFloatingColumnTable().getColumnModel();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		setVisible(true);
	}
	
	//选中某一行表格的数据，并将其显示在填写栏
			private class MyListener implements ListSelectionListener{
				int f=0;
				@Override
				public void valueChanged(ListSelectionEvent e) {
					f=mainData.getFixedColumnTable().getSelectedRow();
					Vector<Object> list=new Vector<Object>();
					list.add(tableValueV.get(f).get(0));
					list.add(tableValueV.get(f).get(1));
					list.add(tableValueV.get(f).get(2));
					list.add(tableValueV.get(f).get(3));
					list.add(tableValueV.get(f).get(4));
					list.add(tableValueV.get(f).get(5));
					list.add(tableValueV.get(f).get(6));
					list.add(tableValueV.get(f).get(7));
					teamName.setSelectedItem(list.get(1).toString());
					teacher.setSelectedItem(list.get(2).toString());
					course.setSelectedItem(list.get(3).toString());
					week.setSelectedItem(list.get(4).toString());
					start.setSelectedItem(list.get(5).toString());
					length.setSelectedItem(list.get(6).toString());
					hour.setText(list.get(7).toString());
					teamId2=teamId.get(teamName.getSelectedIndex());
					courseId2=courseId.get(course.getSelectedIndex());
					teacherId2=teacherId.get(teacher.getSelectedIndex());
				}
				
				
			}
		//初始化数据
		public void initData(){
			DAO dao=new DAO();
			String[] key={"班级","教师","科目","日期","开始时间","上课长度","课时"};
			String[] values={"teamName","teacherName","courseName","week","start","l","hour"};
			list=dao.query("select t1.name teamName,t2.name teacherName,c.name courseName,week,start,lengths l,hour from "
					+ "team t1,teacher t2,course c,team_course tc where tc.teacherId=t2.id and tc.courseId=c.id and t1.id=tc.teamId", values, key);
			if(!list.isEmpty()){
				tableValueV.clear();
				int c=0;
				for(int row=0;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					for(Map<String,Object> m:list){
						rowV.add(row);
						rowV.add(m.get("班级"+c));
						rowV.add(m.get("教师"+c));
						rowV.add(m.get("科目"+c));
						rowV.add(m.get("日期"+c));
						rowV.add(m.get("开始时间"+c));
						rowV.add(m.get("上课长度"+c));
						rowV.add(m.get("课时"+c));
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
			}
			teamId=new ArrayList<String>();
			String[] values1={"id","name"};
			String key1[]={"id","name"};
			List<Map<String,Object>> ls=dao.query("select id,name from team;", values1,key1);
			if(!ls.isEmpty()){
				int c=0;
				for(Map<String,Object> l:ls){
					teamName.addItem(l.get("name"+c));
					teamId.add((String)l.get("id"+c));
					c++;
				}
			}
			teacherId=new ArrayList<String>();
			List<Map<String,Object>> teachers=dao.query("select id,name from teacher;", values1,key1);
			if(!teachers.isEmpty()){
				int c=0;
				for(Map<String,Object> l:teachers){
					teacher.addItem(l.get("name"+c));
					teacherId.add((String)l.get("id"+c));
					c++;
				}
			}
			courseId=new ArrayList<String>();
			List<Map<String,Object>> courses=dao.query("select id,name from course;", values1,key1);
			if(!ls.isEmpty()){
				int c=0;
				for(Map<String,Object> l:ls){
					course.addItem(l.get("name"+c));
					courseId.add((String)l.get("id"+c));
					c++;
				}
			}
		}
		
		public void myEvent(){
			//点击管理学院菜单时
					collegeAdd.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							CollegeFrame college=new CollegeFrame(manager);
							System.out.println("点击乐趣学院管理");
							setVisible(false);
							
						}
					});
					//点击专业管理时
					majorAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Major majorFrame=new Major(manager);
							setVisible(false);
						}
					});
					//点击班级管理
					teamAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							TeamFrame teamFrame=new TeamFrame(manager);
							setVisible(false);
							
						}
					});
					//点击教师管理
					teacherAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							TeacherFrame teacherFrame=new TeacherFrame(manager);
							setVisible(false);
							
						}
					});
					//点击学生管理
					studentAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							StudentFrame studentFrame=new StudentFrame(manager);
							setVisible(false);
							
						}
					});
					//点击角色管理
					roleAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							RoleFrame roleFrame=new RoleFrame(manager);
							setVisible(false);
						}
					});
					scAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							SCFrame scFrame=new SCFrame(manager);
							setVisible(false);
							
						}
					});
			//导入Excel数据
			excelInput.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					open(e);
					File file=new File(path);
					com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
					String[] columnName={"班级","教师","科目","日期","开始时间","上课长度","课时"};
					list2=excelOpt.readExcel(file, columnName);
					flag=true;
					if(list2!=null){
						tableValueV.clear();
						for(int row=1;row<list.size();row++){
							Vector<Object> rowV=new Vector<Object>();
							for(List<Object>ls:list2){
								rowV.add("");
								rowV.add(ls.get(0));
								rowV.add(ls.get(1));
								rowV.add(ls.get(2));
								rowV.add(ls.get(3));
								rowV.add(ls.get(4));
								rowV.add(ls.get(5));
								rowV.add(ls.get(6));
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
					String[] columnName={"编号","班级","教师","科目","日期","开始时间","上课长度","课时"};
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
			submit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DAO dao=new DAO();
					if(!flag){
						String sql="insert into team_course(teacherId,courseId,teamId,week,start,lengths,hour) values"
								+ "('"+teacherId.get(teacher.getSelectedIndex())+"','"+courseId.get(course.getSelectedIndex())+"',"
										+ "'"+teamId.get(teamName.getSelectedIndex())+"','"+week.getSelectedItem()+"','"+start.getSelectedItem()+"','"+length.getSelectedItem()+"','"+hour.getText()+"');";
						if(dao.add(sql)==1){
							Message message=new Message("执行成功！");
							message.pack();
							update();
						}
						
					}else{
						for(int i=1;i<list2.size();i++){
							int[] x={1};
							int p=0;
							for(p=0;p<teamName.getItemCount();p++){
								if(list2.get(i).get(0).equals(teamName.getItemAt(p))){
									teamId2=teamId.get(p);
									teamFlag=true;
									break;
								}
							}
							if(p==teamName.getItemCount())teamFlag=false;
							for(p=0;p<course.getItemCount();p++){
								if(list2.get(i).get(2).equals(teamName.getItemAt(p))){
									courseId2=courseId.get(p);
									courseFlag=true;
									break;
								}
							}
							if(p==course.getItemCount()) courseFlag=false;
							for(p=0;p<teacher.getItemCount();p++){
								if(list2.get(i).get(1).equals(teacher.getItemAt(p))){
									teacherId2=teacherId.get(p);
									teacherFlag=true;
									break;
								}
							}
							if(p==teacher.getItemCount()) teacherFlag=false;
							if(dao.query("select * from team_course where teamId='"+teacherId2+"' and teacherId='"+teacherId2+"' and courseId='"+teacherId2+"'", x).size()>0){
								Message message=new Message("该课程已存在！");
								message.pack();
							}else if(teacherFlag==false){
								Message message=new Message("不存在名为"+list2.get(i).get(1)+"的老师");
								message.pack();
							}else if(teamFlag==false){
								Message message=new Message("不存在名为"+list2.get(i).get(0)+"的班级");
								message.pack();
							}else if(courseFlag==false){
								Message message=new Message("不存在名为"+list2.get(i).get(2)+"的课程");
								message.pack();
							}else{
								String sql="insert into team_course(teacherId,courseId,teamId,week,start,length,hour) values"
										+ "('"+teacherId2+"','"+courseId2+"',"
												+ "'"+teamId2+"','"+list2.get(i).get(3)+"','"+list2.get(i).get(4)+"','"+list2.get(i).get(5)+"','"+list2.get(i).get(6)+"');";
								dao.add(sql);
							}
						}
						flag=false;
						Message message=new Message("执行成功！");
						message.pack();
						update();
					}
				}
			});
			
			modify.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					DAO dao=new DAO();
					String sql="update team_course set teamId='"+teamId.get(teamName.getSelectedIndex())+"',teacherId='"+teacherId.get(teacher.getSelectedIndex())+"',"
							+ "courseId='"+courseId.get(course.getSelectedIndex())+"', week='"+week.getToolTipText()+"',start='"+start.getToolTipText()+"'"
									+ ", length='"+length.getToolTipText()+"',hour='"+hour.getText()+"' where"
											+ " teamId='"+teamId2+"' and courseId='"+courseId2+"' and teacherId='"+teacherId2+"'" ;
					if(dao.modify(sql)==1){
						Message message=new Message("执行成功！");
						message.pack();
						update();
					}
				}
			});
		}
		public void update(){
			DAO dao=new DAO();
			String[] key={"班级","教师","科目","日期","开始时间","上课长度","课时"};
			String[] values={"teamName","teacherName","courseName","week","start","length","hour"};
			list=dao.query("select t1.name teamName,t2.name teacherName,c.name courseName,week,start,length,hour from "
					+ "team t1,teacher t2,course c,team_course tc where tc.teacherId=t2.id and tc.courseId=c.id and t1.id=tc.teamId", values, key);
			if(!list.isEmpty()){
				tableValueV.clear();
				int c=0;
				for(int row=0;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					for(Map<String,Object> m:list){
						rowV.add(row);
						rowV.add(m.get("班级"+c));
						rowV.add(m.get("教师"+c));
						rowV.add(m.get("科目"+c));
						rowV.add(m.get("日期"+c));
						rowV.add(m.get("开始时间"+c));
						rowV.add(m.get("上课长度"+c));
						rowV.add(m.get("课时"+c));
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
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
	    //制作id
	   private String createId(){
		   DAO dao=new DAO();
		   String[] x={"id"};
		   List<List<Object>> list=dao.query("select Max(id) as id from team_course", x);
		   if(!list.isEmpty()&&list.get(0).get(0)!=null){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(2);
			   return "tc"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "tc1001";
		   }
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
