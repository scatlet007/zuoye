package com.akalin.admin;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

public class CourseFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6179519234441454746L;
	private JPanel contentPanel;
	private JTextField course;
	private JTextField credit;
	private JTable table;
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
	private JButton modify;
	private JButton submit;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable mainData;
	private Vector<Vector<Object>> tableValueV;
	private String id;
	private JComboBox major;
	private JComboBox type;
	private JComboBox type2;
	private List<String> majorId;
	/**
	 * Create the frame.
	 */
	public CourseFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	
	public void init(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		this.setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 874, 33);
		contentPanel.add(panel);
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
		
		JLabel course1 = new JLabel("课程名：");
		course1.setFont(new Font("宋体", Font.PLAIN, 14));
		course1.setBounds(22, 55, 68, 15);
		contentPanel.add(course1);
		
		course = new JTextField();
		course.setBounds(85, 52, 131, 21);
		contentPanel.add(course);
		course.setColumns(20);
		
		JLabel credit1 = new JLabel("学分：");
		credit1.setBounds(265, 55, 54, 15);
		contentPanel.add(credit1);
		
		credit = new JTextField();
		credit.setBounds(309, 52, 77, 21);
		contentPanel.add(credit);
		credit.setColumns(10);
		
		JLabel type1 = new JLabel("课程分类：");
		type1.setFont(new Font("宋体", Font.PLAIN, 14));
		type1.setBounds(428, 55, 77, 15);
		contentPanel.add(type1);
		
		type = new JComboBox();
		type.setBounds(515, 52, 110, 21);
		type.addItem("专业选修课");
		type.addItem("专业必修课");
		type.addItem("通识必修课");
		contentPanel.add(type);
		
		JLabel type01 = new JLabel("考核方式：");
		type01.setFont(new Font("宋体", Font.PLAIN, 14));
		type01.setBounds(661, 58, 77, 15);
		contentPanel.add(type01);
		
		type2 = new JComboBox();
		type2.setBounds(736, 52, 99, 21);
		type2.addItem("考查");
		type2.addItem("考试");
		contentPanel.add(type2);
		
		JLabel major1 = new JLabel("专业：");
		major1.setFont(new Font("宋体", Font.PLAIN, 14));
		major1.setBounds(38, 104, 54, 15);
		contentPanel.add(major1);
		
		major = new JComboBox();
		major.setBounds(110, 101, 144, 21);
		contentPanel.add(major);
		
		submit = new JButton("提交");
		submit.setBounds(294, 100, 93, 23);
		contentPanel.add(submit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 138, 864, 379);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("编号");
		columnNameV.add("专业名");
		columnNameV.add("课程名");
		columnNameV.add("课程分类");
		columnNameV.add("考核方式");
		columnNameV.add("学分");
									
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
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(50, 50, 10, 50));
		panel_1.add(mainData, BorderLayout.CENTER);		//把面板添加到窗体中央
		JTable f=mainData.getFixedColumnTable();
		TableColumnModel c=mainData.getFloatingColumnTable().getColumnModel();
		c.getColumn(5).setPreferredWidth(300);
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//复制 end
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(0, 537, 884, 25);
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
		
		modify = new JButton("修改");
		modify.setBounds(557, 104, 93, 23);
		contentPanel.add(modify);
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
			public void actionPerformed(ActionEvent arg0) {
				DAO dao=new DAO();
				String[] x={"id"};
				String sql="insert into course(id,name,ctype,ctype2,credit,majorId) values"
						+ "('"+createId()+"','"+course.getText()+"','"+type.getItemAt(type.getSelectedIndex())+"','"+type2.getItemAt(type2.getSelectedIndex())+"','"+credit.getText()+"','"+majorId.get(major.getSelectedIndex())+"');";
				
				if(major.getSelectedItem()==null||major.getSelectedItem().equals("")){
					Message message=new Message("请添加相应的专业");
					message.pack();
				}else{
					if(dao.add(sql)==1){
						Message message=new Message("执行成功！");
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
				int[] x={1};
				String sql="update course set name='"+course.getText()+"',ctype='"+type.getItemAt(type.getSelectedIndex())+"',ctype2='"+type2.getItemAt(type2.getSelectedIndex())+"',majorId='"+majorId.get(major.getSelectedIndex())+"',credit='"+credit.getText()+"' where id='"+id+"';";
				if(dao.modify(sql)==1){
					Message message=new Message("执行成功！");
					message.pack();
					update();
				}
			}
		});
	}
	//初始化数据
		public void initData(){
			 DAO dao=new DAO();
			   String[] key={"编号","专业名","课程名","课程分类","考核方式","学分"};
			   String[] values={"course.id","major.name","course.name","ctype","ctype2","credit"};
				list=dao.query("select course.id,major.name,course.name,ctype,ctype2,credit from course,major where course.majorId=major.id;", values, key);
				if(!list.isEmpty()){
					int c=0;
					tableValueV.clear();
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//创建行向量
						rowV.add(row);
						for(Map<String,Object> m:list){
							rowV.add(m.get("编号"+c));
							rowV.add(m.get("专业名"+c));
							rowV.add(m.get("课程名"+c));
							rowV.add(m.get("课程分类"+c));
							rowV.add(m.get("考核方式"+c));
							rowV.add(m.get("学分"+c));
						}
						c++;
						tableValueV.add(rowV);									//把行向量添加到数据向量
					}
				}
				majorId=new ArrayList<String>();
				String[] values1={"id","name"};
				String key1[]={"id","name"};
				List<Map<String,Object>> ls=dao.query("select id,name from major;", values1,key1);
				if(!ls.isEmpty()){
					int c=0;
					major.removeAllItems();
					for(Map<String,Object> l:ls){
						major.addItem(l.get("name"+c));
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
					f=mainData.getFixedColumnTable().getSelectedRow();
					Vector<Object> list=new Vector<Object>();
					list.add(tableValueV.get(f).get(0));
					list.add(tableValueV.get(f).get(1));
					list.add(tableValueV.get(f).get(2));
					list.add(tableValueV.get(f).get(3));
					list.add(tableValueV.get(f).get(4));
					list.add(tableValueV.get(f).get(5));
					list.add(tableValueV.get(f).get(6));
					id=list.get(1).toString();
					major.setSelectedItem(list.get(2));
					course.setText(list.get(3).toString());
					type.setSelectedItem(list.get(4));
					type2.setSelectedItem(list.get(5));
					credit.setText(list.get(6).toString());
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
	   int[] x={1};
	   List<List<Object>> list=dao.query("select Max(id) as id from course;", x);
	   if(!list.isEmpty()&&list.get(0).get(0)!=null){
		   String id=list.get(0).get(0).toString();
		   String subId=id.substring(6);
		   return "course"+String.valueOf(Integer.parseInt(subId)+1);
	   }else{
		   return "course1001";
	   }
   }
   public void update(){
	   DAO dao=new DAO();
	   String[] key={"编号","专业名","课程名","课程分类","考核方式","学分"};
	   String[] values={"course.id","major.name","course.name","ctype","ctype2","credit"};
		list=dao.query("select course.id,major.name,course.name,ctype,ctype2,credit from course,major where course.majorId=major.id;", values, key);
		if(!list.isEmpty()){
			int c=0;
			tableValueV.clear();
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("编号"+c));
					rowV.add(m.get("专业名"+c));
					rowV.add(m.get("课程名"+c));
					rowV.add(m.get("课程分类"+c));
					rowV.add(m.get("考核方式"+c));
					rowV.add(m.get("学分"+c));
				}
				c++;
				tableValueV.add(rowV);									//把行向量添加到数据向量
			}
		}
   }
}
