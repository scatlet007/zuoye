package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.FlowLayout;

import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class TeamFrame extends JFrame {

	private JPanel contentPanel;
	private JTextField teamName;
	private JTextField name;
	private JTextField createTime;
	private JMenu collegeManager;
	private JMenu majorManager;
	private JMenu teamManager;
	private JMenu teacherManager;
	private JMenu studentManager;
	private JMenu courseManager;
	private JMenu roleManager;
	private JMenuItem collegeAdd;
	private JMenuItem majorAdd;
	private JMenuItem teamAdd;
	private JMenuItem teacherAdd;
	private JMenuItem studentAdd;
	private JMenuItem courseAdd;
	private JMenuItem roleAdd;
	private String manager;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String id;
	private JButton submit;
	private JButton modify;
	private JComboBox majorName;
	private JTextArea textArea;
	private String majorId;
	/**
	 * Create the frame.
	 */
	public TeamFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 630);
		this.setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel top = new JPanel();
		top.setBounds(5, 5, 874, 21);
		contentPanel.add(top);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0 , 0, this.getWidth(), 40);
		top.add(menuBar, BorderLayout.NORTH);
		
		collegeManager = new JMenu("学院管理");
		menuBar.add(collegeManager);
		
		majorManager = new JMenu("专业管理");
		menuBar.add(majorManager);
		
		teamManager = new JMenu("班级管理");
		menuBar.add(teamManager);
		
		teacherManager = new JMenu("教师管理");
		menuBar.add(teacherManager);
		
		studentManager = new JMenu("学生管理");
		menuBar.add(studentManager);
		
		courseManager = new JMenu("课程管理");
		menuBar.add(courseManager);
		
		roleManager = new JMenu("角色管理");
		menuBar.add(roleManager);
		collegeAdd=new JMenuItem("学院添加");
		collegeManager.add(collegeAdd);
		majorAdd=new JMenuItem("专业添加");
		majorManager.add(majorAdd);
		teamAdd=new JMenuItem("班级添加"); 
		teamManager.add(teamAdd);
		teacherAdd=new JMenuItem("教师添加");
		teacherManager.add(teacherAdd);
		studentAdd=new JMenuItem("学生添加");
		courseAdd=new JMenuItem("课程添加");
		courseManager.add(courseAdd);
		studentManager.add(studentAdd);
		roleAdd=new JMenuItem("角色添加");
		roleManager.add(roleAdd);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 859, 155);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel major = new JLabel("专业:");
		major.setFont(new Font("宋体", Font.PLAIN, 14));
		major.setBounds(53, 31, 54, 15);
		panel.add(major);
		
		majorName = new JComboBox();
		majorName.addItem("计算机");
		majorName.addItem("软件");
		majorName.setBounds(114, 28, 135, 21);
		panel.add(majorName);
		
		JLabel team = new JLabel("\u73ED\u7EA7\u540D\uFF1A");
		team.setBounds(316, 31, 54, 15);
		panel.add(team);
		
		name = new JTextField();
		name.setBounds(392, 28, 165, 21);
		panel.add(name);
		name.setColumns(10);
		
		JLabel create = new JLabel("\u521B\u5EFA\u65F6\u95F4");
		create.setBounds(53, 83, 54, 15);
		panel.add(create);
		
		createTime = new JTextField();
		createTime.setBounds(114, 80, 135, 21);
		panel.add(createTime);
		createTime.setColumns(10);
		
		JLabel status = new JLabel("\u63CF\u8FF0\uFF1A");
		status.setBounds(316, 86, 54, 15);
		panel.add(status);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 59, 270, 93);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setColumns(40);
		textArea.setRows(50);
		scrollPane.setViewportView(textArea);
		
		submit = new JButton("\u63D0\u4EA4");
		submit.setBounds(699, 79, 93, 23);
		panel.add(submit);
		
		modify = new JButton("修改");
		modify.setBounds(699, 112, 93, 23);
		panel.add(modify);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 201, 849, 299);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPanel.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("编号");
		columnNameV.add("班级名");
		columnNameV.add("学院");
		columnNameV.add("描述");
											
		tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int cov=0;cov<4;cov++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		//创建面板，在该面板中实现带行标题栏的表格
		aim=new MFixedColumnTable(columnNameV, tableValueV, 1);
		aim.setBorder(new EmptyBorder(50, 50, 10, 50));
		mainData.add(aim, BorderLayout.CENTER);
		JTable f=aim.getFixedColumnTable();
		TableColumnModel c=aim.getFloatingColumnTable().getColumnModel();
		c.getColumn(4).setPreferredWidth(400);
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//复制 end
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(5, 526, 874, 36);
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("欢迎光临");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(welcome);
		
		JLabel ctrl = new JLabel("使用者："+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		panel_2.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("日期："+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("现在的时间是："+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(time);
		this.setTimer(time);
		setVisible(true);
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
		DAO dao=new DAO();
		String[] str={"id"};
		List<List<Object>> tl=dao.query("select id from major where name='"+majorName.getSelectedItem()+"';", str);
		for(List<Object> l:tl){
			majorId=(String)l.get(0);
		}
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="insert into team(id,name,majorId,status)"
						+ "values('"+createId()+"','"+teamName.getText()+"','"+majorName.getSelectedItem()+"','"+textArea.getText()+"')";
				if(majorId.equals("")||majorId==null){
					Message message=new Message("专业为空，请添加相应专业！");
					message.pack();
				}else{
					if(dao.add(sql)==1){
						Message message=new Message("添加成功！");
						message.pack();
						update();
					}
				}
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update team set name='"+teamName.getText()+"',"
						+ "majorId='"+majorId+"',status='"+textArea.getText()+"' where id='"+id+"';";
				if(majorId.equals("")||majorId==null){
					Message message=new Message("专业为空，请添加相应专业！");
					message.pack();
				}else{
					if(dao.modify(sql)==1){
						Message message=new Message("修改成功！");
						message.pack();
						update();
					}
				}
			}
		});
	}
	//初始化数据
	public void initData(){
		DAO dao=new DAO();
		String[] key={"编号","班级","专业","描述"};
		String[] values={"id","team.name","major.name","team.status"};
		list=dao.query("select team.id,major.name,team.status from team"
				+ ",major where major.id in(select team.majorId from team);", values, key);
		if(!list.isEmpty()){
			int c=0;
			tableValueV.clear();
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("编号"+c));
					rowV.add(m.get("班级"+c));
					rowV.add(m.get("专业"+c));
					rowV.add(m.get("描述"+c));
				}
				c++;
				tableValueV.add(rowV);									//把行向量添加到数据向量
			}
		}
		int[] x={1};
		List<List<Object>> ls=dao.query("select name from college;", x);
		if(!ls.isEmpty()){
			for(List<Object> l:ls){
				majorName.addItem(l.get(0));
			}
		}
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
			id=list.get(0).toString();
			teamName.setText(list.get(1).toString());
			textArea.setText(list.get(3).toString());			
					
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
		   List<List<Object>> list=dao.query("select Max(id) as id from team;", x);
		   if(!list.isEmpty()){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(3);
			   return "team"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "team1001";
		   }
	   }
	   public void update(){
		   DAO dao=new DAO();
			String[] key={"编号","班级","专业","描述"};
			String[] values={"id","team.name","major.name","team.status"};
			list=dao.query("select team.id,major.name,team.status from team"
					+ ",major where major.id in(select team.majorId from team);", values, key);
			if(!list.isEmpty()){
				int c=0;
				tableValueV.clear();
				for(int row=1;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("编号"+c));
						rowV.add(m.get("班级"+c));
						rowV.add(m.get("专业"+c));
						rowV.add(m.get("描述"+c));
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
			}
	   }
	
}
