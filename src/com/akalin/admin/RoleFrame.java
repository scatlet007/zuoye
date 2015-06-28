package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class RoleFrame extends JFrame {

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
	private JLabel roleName1;
	private JTextField roleName;
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
	/**
	 * Create the frame.
	 */
	public RoleFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
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
		
		roleName1 = new JLabel("\u89D2\u8272\u540D:");
		roleName1.setFont(new Font("黑体", Font.BOLD, 14));
		roleName1.setBounds(54, 65, 54, 15);
		contentPane.add(roleName1);
		
		roleName = new JTextField();
		roleName.setBounds(125, 62, 115, 21);
		contentPane.add(roleName);
		roleName.setColumns(10);
		
		JLabel status2 = new JLabel("\u63CF\u8FF0:");
		status2.setFont(new Font("黑体", Font.BOLD, 14));
		status2.setBounds(54, 126, 54, 15);
		contentPane.add(status2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 105, 270, 73);
		contentPane.add(scrollPane);
		
		status = new JTextArea();
		scrollPane.setViewportView(status);
		
		submit = new JButton("\u63D0\u4EA4");
		submit.setBounds(550, 155, 93, 23);
		contentPane.add(submit);
		
		modify = new JButton("\u4FEE\u6539");
		modify.setBounds(694, 155, 93, 23);
		contentPane.add(modify);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPane.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("编号");
		columnNameV.add("角色名");
		columnNameV.add("描述");
											
		tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int cov=0;cov<3;cov++){
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
		c.getColumn(2).setPreferredWidth(400);
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
				courseAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						CourseFrame courseFrame=new CourseFrame(manager);
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
				String sql="insert into role(id,name,status)"
						+ "values('"+createId()+"','"+roleName.getText()+"','"+status.getText()+"');";
				if(dao.add(sql)==1){
					Message message=new Message("添加成功！");
					message.pack();
					update();
				}
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update role set name='"+roleName.getText()+"',status='"+status.getText()+"' where id='"+id+"'";
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
		String[] key={"编号","角色名","描述"};
		String[] values={"id","name","status"};
		list=dao.query("select id,role.name,status from role", values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("编号"+c));
					rowV.add(m.get("角色名"+c));
					rowV.add(m.get("描述"+c));
				}
				c++;
				tableValueV.add(rowV);									//把行向量添加到数据向量
			}
		}
	}
		//选中某一行表格的数据，并将其显示在填写栏
	private class MyListener implements ListSelectionListener{
		int f=0;
		@Override
		public void valueChanged(ListSelectionEvent e) {
			/*if(flag!=aim.getSelectRow()){
				List<Vector<Object>> list=aim.getList();
				for(Vector<Object> vec:list){
					id=(String)vec.get(1);
					roleName.setText((String)vec.get(2));
					status.setText((String)vec.get(3));
			}
		}
			flag=aim.getSelectRow();*/
			f=aim.getFixedColumnTable().getSelectedRow();
			Vector<Object> list=new Vector<Object>();
			list.add(tableValueV.get(f).get(0));
			list.add(tableValueV.get(f).get(1));
			list.add(tableValueV.get(f).get(2));
			list.add(tableValueV.get(f).get(3));
			id=list.get(1).toString();
			roleName.setText(list.get(2).toString());
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
		   List<List<Object>> list=dao.query("select Max(id) as id from role", x);
		   if(!list.isEmpty()){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(4);
			   return "role"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "role1001";
		   }
	   }
	   public void update(){
		   DAO dao=new DAO();
			String[] key={"编号","角色名","描述"};
			String[] values={"id","name","status"};
			list=dao.query("select id,name,status from role", values, key);
			if(!list.isEmpty()){
				tableValueV.clear();
				int c=0;
				for(int row=1;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("编号"+c));
						rowV.add(m.get("角色名"+c));
						rowV.add(m.get("描述"+c));
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
			}
	   }
}
