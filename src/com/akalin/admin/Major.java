package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class Major extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1593960146462638258L;
	private JPanel contentPane;
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
	private JLabel majorName1;
	private JTextField majorName;
	private JTextField createTime;
	private JButton submit;
	private JButton modify;
	private JTextArea status;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String id;
	private JPanel panel_1;
	private JLabel college2;
	private JComboBox college;
	/**
	 * Create the frame.
	 */
	public Major(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 874, 33);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar, BorderLayout.CENTER);
		
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
		
		majorName1 = new JLabel("\u4E13\u4E1A\u540D:");
		majorName1.setFont(new Font("黑体", Font.BOLD, 14));
		majorName1.setBounds(54, 65, 54, 15);
		contentPane.add(majorName1);
		
		majorName = new JTextField();
		majorName.setBounds(125, 62, 115, 21);
		contentPane.add(majorName);
		majorName.setColumns(10);
		
		JLabel createTime2 = new JLabel("\u521B\u5EFA\u65F6\u95F4:");
		createTime2.setFont(new Font("黑体", Font.BOLD, 14));
		createTime2.setBounds(304, 68, 75, 15);
		contentPane.add(createTime2);
		
		createTime = new JTextField();
		createTime.setFont(new Font("黑体", Font.BOLD, 14));
		createTime.setBounds(389, 62, 134, 21);
		contentPane.add(createTime);
		createTime.setColumns(10);
		
		JLabel status2 = new JLabel("\u63CF\u8FF0:");
		status2.setFont(new Font("黑体", Font.BOLD, 14));
		status2.setBounds(54, 121, 54, 15);
		contentPane.add(status2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 105, 270, 73);
		contentPane.add(scrollPane);
		
		status = new JTextArea();
		status.setBounds(125, 105, 270, 73);
		scrollPane.setViewportView(status);
		
		submit = new JButton("\u63D0\u4EA4");
		submit.setBounds(472, 155, 93, 23);
		contentPane.add(submit);
		
		modify = new JButton("\u4FEE\u6539");
		modify.setBounds(605, 155, 93, 23);
		contentPane.add(modify);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPane.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("编号");
		columnNameV.add("专业名");
		columnNameV.add("创建时间");
		columnNameV.add("学院");
		columnNameV.add("描述");
											
		tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int cov=0;cov<6;cov++){
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
		panel_1 = new JPanel();
		panel_1.setBounds(10, 515, 864, 37);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,4));
		JLabel welcome = new JLabel("欢迎光临");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(welcome);
		
		JLabel ctrl = new JLabel("使用者："+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		panel_1.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("日期："+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("现在的时间是："+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(time);
		this.setTimer(time);
		
		college2 = new JLabel("\u5B66\u9662\u540D:");
		college2.setFont(new Font("黑体", Font.BOLD, 14));
		college2.setBounds(550, 68, 54, 15);
		contentPane.add(college2);
		
		college = new JComboBox();
		college.setBounds(627, 62, 134, 21);
		contentPane.add(college);
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
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="insert into major(id,name,createTime,collegeId,status)"
						+ "values('"+createId()+"','"+majorName.getText()+"','"+createTime.getText()+"','"+college.getSelectedItem()+"','"+status.getText()+"')";
				if(college.getSelectedItem().equals("")||college.getSelectedItem()==null){
					Message message=new Message("学院为空，请添加相应的学院");
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
				String sql="update major set name='"+majorName.getText()+"',createTime='"+createTime.getText()+"',"
						+ "collegeId='"+college.getSelectedItem()+"',status='"+status.getText()+"' where id='"+id+"'";
				if(dao.modify(sql)==1){
					Message message=new Message("修改成功！");
					message.pack();
					update();
				}
			}
		});
	}
	//初始化数据
	public void initData(){
		DAO dao=new DAO();
		String[] key={"编号","专业名","创建时间","学院","描述"};
		String[] values={"id","major.name","major.create","college.name","major.status"};
		list=dao.query("select major.id,major.name,major.createTime,college.name,major.status from course"
				+ ",major where major.collegeId=college.id;", values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			int c=0;
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("编号"+c));
					rowV.add(m.get("专业名"+c));
					rowV.add(m.get("创建时间"+c));
					rowV.add(m.get("学院"+c));
					rowV.add(m.get("描述"+c));
				}
				c++;
				tableValueV.add(rowV);									//把行向量添加到数据向量
			}
		}
		String[] x={"id"};
		List<List<Object>> ls=dao.query("select name from college;", x);
		if(!ls.isEmpty()){
			for(List<Object> l:ls){
				college.addItem(l.get(0));
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
			majorName.setText(list.get(1).toString());
			createTime.setText(list.get(2).toString());
			status.setText(list.get(3).toString());			
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
		   List<List<Object>> list=dao.query("select Max(id) as id from major;", x);
		   if(!list.isEmpty()){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(5);
			   return "major"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "major1001";
		   }
	   }
	   public void update(){
		   DAO dao=new DAO();
			String[] key={"编号","专业名","创建时间","学院","描述"};
			String[] values={"id","major.name","major.create","college.name","major.status"};
			list=dao.query("select major.id,major.name,major.createTime,college.name,major.status from course"
					+ ",major where major.collegeId=college.id;", values, key);
			if(!list.isEmpty()){
				int c=0;
				tableValueV.clear();
				for(int row=1;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("编号"+c));
						rowV.add(m.get("专业名"+c));
						rowV.add(m.get("创建时间"+c));
						rowV.add(m.get("学院"+c));
						rowV.add(m.get("描述"+c));
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
			}
	   }
}
