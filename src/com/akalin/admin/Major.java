package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import com.akalin.dao.DAO;
import com.akalin.tool.DateChooserJButton;
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
	private JMenuItem scAdd;
	private String manager;
	private JLabel majorName1;
	private JTextField majorName;
	private JButton submit;
	private JButton modify;
	private JTextArea status;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String id;
	private JPanel panel_1;
	private JLabel college2;
	private JComboBox college;
	private List<String> collegeId;
	private JTextField createTime;
	private DateChooserJButton dateChooserJButton;
	private List<List<Object>> list2=new ArrayList<List<Object>>();
	private boolean flag=false;
	private JMenuItem excelInput;
	private JMenuItem excelOutput;
	private String path;
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
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
		
		JMenu scManager=new JMenu("班级课表管理");
		menuBar.add(scManager);
		
		collegeAdd=new JMenuItem("学院添加");
		collegeAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		collegeManager.add(collegeAdd);
		majorAdd=new JMenuItem("专业添加");
		majorAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		excelInput=new JMenuItem();
		excelInput.setText("Excel数据导入");
		excelOutput=new JMenuItem();
		excelOutput.setText("导出Excel文件");
		majorManager.add(majorAdd);
		majorManager.add(excelInput);
		majorManager.add(excelOutput);
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
		
		majorName1 = new JLabel("\u4E13\u4E1A\u540D:");
		majorName1.setFont(new Font("黑体", Font.BOLD, 14));
		majorName1.setBounds(54, 65, 54, 15);
		contentPane.add(majorName1);
		
		majorName = new JTextField();
		majorName.setBounds(125, 62, 115, 21);
		contentPane.add(majorName);
		majorName.setColumns(10);
		
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
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		submit.setBounds(472, 155, 93, 23);
		contentPane.add(submit);
		
		modify = new JButton("\u4FEE\u6539");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
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
			for(int cov=0;cov<5;cov++){
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
		college2.setBounds(552, 65, 54, 15);
		contentPane.add(college2);
		
		college = new JComboBox();
		college.setBounds(636, 62, 134, 21);
		contentPane.add(college);
		
		JLabel lblNewLabel = new JLabel("\u521B\u5EFA\u65F6\u95F4\uFF1A");
		lblNewLabel.setBounds(276, 65, 78, 15);
		contentPane.add(lblNewLabel);
		
		dateChooserJButton=new DateChooserJButton();
		dateChooserJButton.setBounds(364, 62, 123, 21);
		contentPane.add(dateChooserJButton);
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
						String[] columnName={"专业名","创建时间","学院","描述"};
						list2=excelOpt.readExcel(file, columnName);
						flag=true;
						if(list2!=null){
							tableValueV.clear();
							for(int row=1;row<list.size();row++){
								Vector<Object> rowV=new Vector<Object>();
								for(List<Object>ls:list2){
									rowV.add("");
									rowV.add("");
									rowV.add(ls.get(0));
									rowV.add(ls.get(1));
									rowV.add(ls.get(2));
									rowV.add(ls.get(3));
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
							for(int i=0;i<7;i++){
								ll.add(ss.get(i));
								System.out.print(ss.get(i)+"\t");
							}
							System.out.println();
							list2.add(ll);
						}
						button(e);
						String[] columnName={"序号","编号","专业名","创建时间","学院","描述"};
						com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
						excelOpt.writeExcelBo(path, columnName, list2);
						for(int i=0;i<list2.size();i++){
							//循环读取每一单元格的值
							for(int j=0;j<7;j++){
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
					String sql="insert into major(id,name,createTime,collegeId,status)"
							+ "values('"+createId()+"','"+majorName.getText()+"','"+dateChooserJButton.getText()+"','"+collegeId.get(college.getSelectedIndex())+"','"+status.getText()+"')";
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
				}else{
					int[] x={1};
					for(int i=1;i<list2.size();i++){
						if(dao.query("select * from college where name='"+list2.get(i).get(2)+"'", x).size()==0){
							Message message=new Message("没有名为："+list2.get(i).get(2)+"的学院");
							message.pack();
						}else if(dao.query("select * from major where name='"+list2.get(i).get(0)+"'", x).size()>0){
							Message message=new Message("没有名为："+list2.get(i).get(0)+"的专业已存在");
							message.pack();
						}else{
							List<List<Object>> listt=dao.query("select * from college where name='"+list2.get(i).get(2)+"'", x);
							String ai="";
							if(listt.size()>0&&listt.get(0).get(0)!=null){
								ai=(String)listt.get(0).get(0);
							}
							String sql="insert into major(id,name,createTime,collegeId,status)"
									+ "values('"+createId()+"','"+list2.get(i).get(0)+"','"+list2.get(i).get(1)+"','"+ai+"','"+list2.get(i).get(3)+"')";
							dao.add(sql);
						}
						Message message=new Message("添加成功！");
						message.pack();
					}
				}
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update major set name='"+majorName.getText()+"',createTime='"+dateChooserJButton.getText()+"',collegeId='"+collegeId.get(college.getSelectedIndex())+"',status='"+status.getText()+"' where id='"+id+"'";
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
		String[] values={"majorId","majorName","majorCreateTime","collegeName","majorStatus"};
		list=dao.query("select m.id majorId,m.name majorName,m.createTime majorCreateTime,c.name collegeName,m.status majorStatus from college c,major m where m.collegeId=c.id", values, key);
		if(!list.isEmpty()){
			System.out.println("abc");
			tableValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				System.out.println("ab55");
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("编号"+c));
					rowV.add(m.get("专业名"+c));
					rowV.add(m.get("创建时间"+c));
					rowV.add(m.get("学院"+c));
					rowV.add(m.get("描述"+c));
					System.out.println("af->>>"+rowV.add(m.get("编号"+c)));
					System.out.println("abc99");
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
			for(Map<String,Object> l:ls){
				college.addItem(l.get("name"+c));
				collegeId.add((String)l.get("id"+c));
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
			id=list.get(1).toString();
			majorName.setText(list.get(2).toString());
			dateChooserJButton.setText(list.get(3).toString());
			status.setText(list.get(5).toString());			
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
		   if(!list.isEmpty()&&list.get(0).get(0)!=null){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(0);
			   return ""+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "1020";
		   }
	   }
	   public void update(){
		   DAO dao=new DAO();
			String[] key={"编号","专业名","创建时间","学院","描述"};
			String[] values={"majorId","majorName","majorCreateTime","collegeName","majorStatus"};
			list=dao.query("select m.id majorId,m.name majorName,m.createTime majorCreateTime,c.name collegeName,m.status majorStatus from college c,major m where m.collegeId=c.id", values, key);
			if(!list.isEmpty()){
				System.out.println("abc");
				tableValueV.clear();
				int c=0;
				for(int row=0;row<list.size();row++){
					System.out.println("ab55");
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("编号"+c));
						rowV.add(m.get("专业名"+c));
						rowV.add(m.get("创建时间"+c));
						rowV.add(m.get("学院"+c));
						rowV.add(m.get("描述"+c));
						System.out.println("af->>>"+rowV.add(m.get("编号"+c)));
						System.out.println("abc99");
					}
					c++;
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
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
