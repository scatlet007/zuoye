package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
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
import javax.swing.table.TableColumnModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class TeamFrame extends JFrame {

	private JPanel contentPanel;
	private JTextField teamName;
	private JTextField name;
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
	private List<String> majorId;
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
		collegeAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		collegeManager.add(collegeAdd);
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 859, 155);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel major = new JLabel("专业:");
		major.setFont(new Font("宋体", Font.PLAIN, 14));
		major.setBounds(53, 31, 54, 15);
		panel.add(major);
		
		majorName = new JComboBox();
		majorName.setBounds(114, 28, 135, 21);
		panel.add(majorName);
		
		JLabel team = new JLabel("\u73ED\u7EA7\u540D\uFF1A");
		team.setBounds(316, 31, 54, 15);
		panel.add(team);
		
		name = new JTextField();
		name.setBounds(392, 28, 165, 21);
		panel.add(name);
		name.setColumns(10);
		
		JLabel status = new JLabel("\u63CF\u8FF0\uFF1A");
		status.setBounds(53, 87, 54, 15);
		panel.add(status);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 56, 270, 93);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setColumns(40);
		textArea.setRows(50);
		
		submit = new JButton("\u63D0\u4EA4");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		submit.setBounds(699, 79, 93, 23);
		panel.add(submit);
		
		modify = new JButton("修改");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		modify.setBounds(699, 112, 93, 23);
		panel.add(modify);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPanel.add(mainData);
		mainData.setLayout(new BorderLayout());
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("编号");
		columnNameV.add("班级名");
		columnNameV.add("专业");
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
		aim.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainData.add(aim, BorderLayout.CENTER);
		JTable f=aim.getFixedColumnTable();
		TableColumnModel c=aim.getFloatingColumnTable().getColumnModel();
		c.getColumn(3).setPreferredWidth(400);
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
				courseAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CourseFrame courseFrame=new CourseFrame(manager);
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
				String sql="insert into team(id,name,majorId,status)"
						+ "values('"+createId()+"'"
								+ ",'"+name.getText()+"',"
										+ "'"+majorId.get(majorName.getSelectedIndex())+"',"
												+ "'"+textArea.getText()+"')";
				if(majorId.get(majorName.getSelectedIndex()).equals("")||majorId.get(majorName.getSelectedIndex())==null){
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
				String sql="update team set name='"+name.getText()+"',"
						+ "majorId='"+majorId.get(majorName.getSelectedIndex())+"',status='"+textArea.getText()+"' where id='"+id+"';";
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
		String[] values={"teamId","teamName","majorName","teamStatus"};
		list=dao.query("select t.id teamId,t.name teamName,m.name majorName,t.status teamStatus from team t,major m where t.majorId=m.id", values, key);
		if(!list.isEmpty()&&list.size()>0){
			int c=0;
			System.out.println("a101");
			tableValueV.clear();
			for(int row=0;row<list.size();row++){
				System.out.println("a102");
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
		System.out.println("a103");
		majorId=new ArrayList<String>();
		String[] values1={"id","name"};
		String key1[]={"id","name"};
		List<Map<String,Object>> ls=dao.query("select id,name from major;", values1,key1);
		if(!ls.isEmpty()){
			int c=0;
			for(Map<String,Object> l:ls){
				majorName.addItem(l.get("name"+c));
				majorId.add((String)l.get("id"+c));
				System.out.println(l.get("name"+c)+"--name");
				c++;
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
			list.add(tableValueV.get(f).get(4));
			id=list.get(1).toString();
			name.setText((String)list.get(2));
			textArea.setText((String)list.get(4));			
					
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
		   if(!list.isEmpty()&&list.get(0).get(0)!=null){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(4);
			   return "team"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "team1001";
		   }
	   }
	   public void update(){
		   DAO dao=new DAO();
			String[] key={"编号","班级","专业","描述"};
			String[] values={"teamId","teamName","majorName","teamStatus"};
			list=dao.query("select t.id teamId,t.name teamName,m.name majorName,t.status teamStatus from team t,major m where t.majorId=m.id", values, key);
			if(!list.isEmpty()&&list.size()>0){
				int c=0;
				System.out.println("a101");
				tableValueV.clear();
				for(int row=0;row<list.size();row++){
					System.out.println("a102");
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
