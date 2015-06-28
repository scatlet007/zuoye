package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class TeacherFrame extends JFrame {

	private JPanel contentPane;
	private JTextField teacher;
	private JTextField age;
	private JTextField phone;
	private JTable table;
	private JButton submit;
	private JButton modify;
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
	private JComboBox collegeName;
	private JComboBox sex;
	private JComboBox position;
	private JComboBox role;
	private List<String> collegeId;
	private List<String> roleId;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherFrame frame = new TeacherFrame("");
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
	public TeacherFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 874, 33);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
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
		
		JLabel name = new JLabel("\u6559\u5E08\u540D\uFF1A");
		name.setFont(new Font("宋体", Font.PLAIN, 14));
		name.setBounds(10, 58, 56, 15);
		contentPane.add(name);
		
		teacher = new JTextField();
		teacher.setBounds(69, 55, 122, 21);
		contentPane.add(teacher);
		teacher.setColumns(10);
		
		JLabel college = new JLabel("\u5B66\u9662\uFF1A");
		college.setFont(new Font("宋体", Font.PLAIN, 14));
		college.setBounds(234, 61, 54, 15);
		contentPane.add(college);
		
		collegeName = new JComboBox();
		collegeName.setModel(new DefaultComboBoxModel(new String[] {"信工学院","软件学院","文学院"}));
		collegeName.setBounds(280, 55, 152, 21);
		contentPane.add(collegeName);
		
		JLabel sex1 = new JLabel("\u6027\u522B\uFF1A");
		sex1.setFont(new Font("宋体", Font.PLAIN, 14));
		sex1.setBounds(471, 61, 54, 15);
		contentPane.add(sex1);
		
		sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
		sex.setBounds(516, 55, 64, 21);
		contentPane.add(sex);
		
		JLabel age1 = new JLabel("\u5E74\u9F84\uFF1A");
		age1.setFont(new Font("宋体", Font.PLAIN, 14));
		age1.setBounds(615, 58, 54, 15);
		contentPane.add(age1);
		
		age = new JTextField();
		age.setBounds(661, 55, 66, 21);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel telephone1 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		telephone1.setFont(new Font("宋体", Font.PLAIN, 14));
		telephone1.setBounds(12, 108, 70, 15);
		contentPane.add(telephone1);
		
		phone = new JTextField();
		phone.setBounds(80, 105, 131, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel position1 = new JLabel("\u804C\u4F4D\uFF1A");
		position1.setFont(new Font("宋体", Font.PLAIN, 14));
		position1.setBounds(234, 108, 54, 15);
		contentPane.add(position1);
		
		position = new JComboBox();
		position.setBounds(280, 105, 152, 21);
		position.addItem("班主任");
		position.addItem("普通教师");
		contentPane.add(position);
		
		JLabel role1 = new JLabel("\u89D2\u8272\uFF1A");
		role1.setFont(new Font("宋体", Font.PLAIN, 14));
		role1.setBounds(471, 108, 54, 15);
		contentPane.add(role1);
		
		role = new JComboBox();
		role.setBounds(516, 105, 116, 21);
		role.addItem("教授");
		role.addItem("讲师");
		contentPane.add(role);
		
		submit = new JButton("提交");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		submit.setBounds(655, 104, 93, 23);
		contentPane.add(submit);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPane.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("编号");
		columnNameV.add("教师名");
		columnNameV.add("性别");
		columnNameV.add("年龄");
		columnNameV.add("手机号");
		columnNameV.add("职位");
		columnNameV.add("学院");
		columnNameV.add("角色");
											
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
		aim=new MFixedColumnTable(columnNameV, tableValueV, 1);
		aim.setBorder(new EmptyBorder(50, 50, 10, 50));
		mainData.add(aim, BorderLayout.CENTER);
		JTable f=aim.getFixedColumnTable();
		TableColumnModel c=aim.getFloatingColumnTable().getColumnModel();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//复制 end
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 536, 874, 26);
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
		
		modify = new JButton("\u4FEE\u6539");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		modify.setBounds(770, 108, 93, 23);
		contentPane.add(modify);
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
		DAO dao=new DAO();
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="insert into teacher(id,name,password,sex,age,position,collegeId,roleId)"
						+ "values('"+createId()+"','"+teacher.getText()+"','123456','"+sex.getSelectedItem()+"','"+age.getText()+"',"
								+ "'"+position.getItemAt(position.getSelectedIndex())+"','"+collegeId.get(collegeName.getSelectedIndex())+"','"+roleId.get(role.getSelectedIndex())+"')";
				if(collegeId.get(collegeName.getSelectedIndex())==null||collegeId.get(collegeName.getSelectedIndex()).equals("")){      
					Message message=new Message("学院为空，请添加相应学院！");
					message.pack();
				}else{
					if(dao.add(sql)==1){
						Message message=new Message("添加成功！");
						message.pack();
					}
				}
				update();
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update teacher set name='"+teacher.getText()+"',sex='"+sex.getItemAt(sex.getSelectedIndex())+"',"
						+ "age='"+age.getText()+"',telephone='"+phone.getText()+"',position='"+position.getItemAt(position.getSelectedIndex())+"',collegeId='"+collegeId.get(collegeName.getSelectedIndex())+"',roleId='"+roleId.get(role.getSelectedIndex())+"' where id='"+id+"'";
				if(dao.modify(sql)==1){
					Message message=new Message("修改成功！");
					message.pack();
				}
				update();
			}
		});
	}
	
	//初始化数据
		public void initData(){
			DAO dao=new DAO();
			String[] key={"编号","教师名","性别","年龄","角色","手机号","学院","职位"};
			String[] values={"teacherId","teacherName","sex","age","roleName","telephone","collegeName","position"};
			list=dao.query("select t.id teacherId,t.name teacherName,sex,age,r.name roleName,c.name collegeName,telephone,position from teacher t,role r,college c where t.collegeId=c.id and r.id=t.roleId;", values, key);
			if(!list.isEmpty()){
				tableValueV.clear();
				int c=0;
				for(int row=0;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("编号"+c));
						rowV.add(m.get("教师名"+c));
						rowV.add(m.get("性别"+c));
						rowV.add(m.get("年龄"+c));
						rowV.add(m.get("手机号"+c));
						rowV.add(m.get("职位"+c));
						rowV.add(m.get("学院"+c));
						rowV.add(m.get("角色"+c));
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
			}
			System.out.println("aaa");
			collegeId=new ArrayList<String>();
			String[] values1={"id","name"};
			String key1[]={"id","name"};
			List<Map<String,Object>> ls=dao.query("select id,name from college;", values1,key1);
			if(!ls.isEmpty()){
				int c=0;
				collegeName.removeAllItems();
				for(Map<String,Object> l:ls){
					collegeName.addItem(l.get("name"+c));
					collegeId.add((String)l.get("id"+c));
					c++;
				}
			}
			roleId=new ArrayList<String>();
			List<Map<String,Object>> roles=dao.query("select id,name from role;", values1,key1);
			if(!roles.isEmpty()){
				int c=0;
				role.removeAllItems();
				for(Map<String,Object> l:roles){
					role.addItem(l.get("name"+c));
					roleId.add((String)l.get("id"+c));
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
				list.add(tableValueV.get(f).get(5));
				list.add(tableValueV.get(f).get(6));
				list.add(tableValueV.get(f).get(7));
				list.add(tableValueV.get(f).get(8));
				id=list.get(1).toString();
				teacher.setText(list.get(2).toString());
				sex.setSelectedItem(list.get(3));		
				age.setText(list.get(4).toString());		
				phone.setText(list.get(5).toString());
				position.setSelectedItem(list.get(6));
				collegeName.setSelectedItem(list.get(7));
				role.setSelectedItem(list.get(8));
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
			   List<List<Object>> list=dao.query("select Max(id) as id from teacher;", x);
			   if(!list.isEmpty()&&list.get(0).get(0)!=null){
				   String id=list.get(0).get(0).toString();
				   String subId=id.substring(7);
				   return "teacher"+String.valueOf(Integer.parseInt(subId)+1);
			   }else{
				   return "teacher1001";
			   }
		   }
		   private void update(){
			   DAO dao=new DAO();
				String[] key={"编号","教师名","性别","年龄","角色","手机号","学院","职位"};
				String[] values={"teacherId","teacherName","sex","age","roleName","telephone","collegeName","position"};
				list=dao.query("select t.id teacherId,t.name teacherName,sex,age,r.name roleName,c.name collegeName,telephone,position from teacher t,role r,college c where t.collegeId=c.id and r.id=t.roleId;;", values, key);
				if(!list.isEmpty()){
					tableValueV.clear();
					int c=0;
					for(int row=0;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//创建行向量
						rowV.add(row);
						for(Map<String,Object> m:list){
							rowV.add(m.get("编号"+c));
							rowV.add(m.get("教师名"+c));
							rowV.add(m.get("性别"+c));
							rowV.add(m.get("年龄"+c));
							rowV.add(m.get("手机号"+c));
							rowV.add(m.get("职位"+c));
							rowV.add(m.get("学院"+c));
							rowV.add(m.get("角色"+c));
						}
						c++;
						tableValueV.add(rowV);									//把行向量添加到数据向量
					}
				}
		   }
}
