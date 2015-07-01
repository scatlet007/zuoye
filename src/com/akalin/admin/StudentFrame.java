package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class StudentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField student;
	private JTextField age;
	private JTextField phone;
	private JButton submit;
	private JButton modify;
	private JTable table;
	private JTextField sno;
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
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String id;
	private JPanel panel_1;
	private JComboBox sex;
	private JComboBox role;
	private JComboBox team;
	private List<String> teamId;
	private List<String> roleId;
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
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame frame = new StudentFrame();
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
	public StudentFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,900, 600);
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
		
		JMenu scManager=new JMenu("班级课表管理");
		menuBar.add(scManager);
		
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
		excelInput=new JMenuItem();
		excelInput.setText("Excel数据导入");
		excelOutput=new JMenuItem();
		excelOutput.setText("导出Excel文件");
		studentManager.add(studentAdd);
		studentManager.add(excelInput);
		studentManager.add(excelOutput);
		roleAdd=new JMenuItem("角色添加");
		roleAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		roleManager.add(roleAdd);
		scAdd=new JMenuItem("班级课表添加");
		scManager.add(scAdd);
		JLabel name = new JLabel("学生名：");
		name.setFont(new Font("宋体", Font.PLAIN, 14));
		name.setBounds(175, 58, 56, 15);
		contentPane.add(name);
		
		student = new JTextField();
		student.setBounds(241, 55, 83, 21);
		contentPane.add(student);
		student.setColumns(10);
		
		JLabel college = new JLabel("班级名：");
		college.setFont(new Font("宋体", Font.PLAIN, 14));
		college.setBounds(334, 58, 69, 15);
		contentPane.add(college);
		
		team = new JComboBox();
		team.setModel(new DefaultComboBoxModel(new String[] {"信工学院","软件学院","文学院"}));
		team.setBounds(413, 55, 152, 21);
		contentPane.add(team);
		
		JLabel sex1 = new JLabel("性别");
		sex1.setFont(new Font("宋体", Font.PLAIN, 14));
		sex1.setBounds(586, 58, 54, 15);
		contentPane.add(sex1);
		
		sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
		sex.setBounds(650, 55, 64, 21);
		contentPane.add(sex);
		
		JLabel age1 = new JLabel("年龄：");
		age1.setFont(new Font("宋体", Font.PLAIN, 14));
		age1.setBounds(724, 58, 54, 15);
		contentPane.add(age1);
		
		age = new JTextField();
		age.setBounds(788, 55, 66, 21);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel role1 = new JLabel("角色名：");
		role1.setFont(new Font("宋体", Font.PLAIN, 14));
		role1.setBounds(12, 108, 66, 15);
		contentPane.add(role1);
		
		role = new JComboBox();
		role.setBounds(88, 105, 116, 21);
		role.addItem("aa");
		role.addItem("bb");
		contentPane.add(role);
		
		submit = new JButton("提交");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		submit.setBounds(280, 104, 93, 23);
		contentPane.add(submit);
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPane.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("学号");
		columnNameV.add("姓名");
		columnNameV.add("性别");
		columnNameV.add("年龄");
		columnNameV.add("手机号");
		columnNameV.add("职位");
		columnNameV.add("班级");
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
		
		modify = new JButton("修改");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		modify.setBounds(471, 104, 93, 23);
		contentPane.add(modify);
		
		JLabel sno1 = new JLabel("\u5B66\u53F7:");
		sno1.setBounds(10, 58, 46, 15);
		contentPane.add(sno1);
		
		sno = new JTextField();
		sno.setBounds(45, 55, 106, 21);
		contentPane.add(sno);
		sno.setColumns(10);
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
						String[] columnName={"学号","姓名","性别","年龄","手机号","职位","班级","角色"};
						list2=excelOpt.readExcel(file, columnName);
						flag=true;
						if(list2!=null){
							tableValueV.clear();
							for(int row=1;row<list.size();row++){
								Vector<Object> rowV=new Vector<Object>();
								for(List<Object>ls:list2){
									rowV.add(row);
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
				excelOutput.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						list2.clear();
						for(Vector<Object> ss:tableValueV){
							List<Object> ll=new ArrayList<Object>();
							for(int i=0;i<9;i++){
								ll.add(ss.get(i));
								System.out.print(ss.get(i)+"\t");
							}
							System.out.println();
							list2.add(ll);
						}
						button(e);
						String[] columnName={"序号","学号","姓名","性别","年龄","手机号","职位","班级","角色"};
						com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
						excelOpt.writeExcelBo(path, columnName, list2);
						for(int i=0;i<list2.size();i++){
							//循环读取每一单元格的值
							for(int j=0;j<9;j++){
								//向外写单元格的值
								System.out.print((String)list2.get(i).get(j));
							}
							System.out.println();
						}
					}
				});
		DAO dao=new DAO();
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				if(!flag){
					String sql="insert into student(id,name,password,sex,age,teamId,roleId)"
							+ "values('"+sno.getText()+"','"+student.getText()+"','123456','"+sex.getSelectedItem()+"','"+age.getText()+"',"
									+ "'"+teamId.get(team.getSelectedIndex())+"','"+roleId.get(role.getSelectedIndex())+"')";
					if(teamId==null||teamId.equals("")){
						Message message=new Message("班级为空，请添加相应班级！");
						message.pack();
					}else{
						int[] x={1};
						if(dao.query("select * from student where id='"+sno.getText()+"'", x).size()>0){
							Message message=new Message("添加失败！学号"+sno.getText()+"已存在");
							message.pack();
						}else if(dao.add(sql)==1){
							Message message=new Message("添加成功！");
							message.pack();
							update();
						}
					}
				}else{
					int[] x={1};
					for(int i=1;i<list2.size();i++){
						if(dao.query("select * from team where name='"+list2.get(i).get(6)+"'", x).size()==0){
							Message message=new Message("还没有名为"+list2.get(i).get(6)+"的班级");
							message.pack();
						}else{
							if(dao.query("select * from role where name='"+list2.get(i).get(7)+"'", x).size()==0){
								Message message=new Message("还没有名为"+list2.get(i).get(7)+"的角色");
								message.pack();
							}else if(dao.query("select * from student where id='"+list2.get(i).get(0)+"'", x).size()>0){
								Message message=new Message("已有学号为"+list2.get(i).get(0)+"的学生");
								message.pack();
							}else{
								List<List<Object>> listt=dao.query("select * from team where name='"+list2.get(i).get(6)+"'", x);
								List<List<Object>> listt2=dao.query("select * from role where name='"+list2.get(i).get(7)+"'", x);
								String ai="";
								String am="";
								if(listt.size()>0&&listt.get(0).get(0)!=null){
									ai=(String)listt.get(0).get(0);
								}
								if(listt.size()>0&&listt2.get(0).get(0)!=null){
									am=(String)listt2.get(0).get(0);
								}
								String sql="insert into student(id,name,password,sex,age,teamId,roleId)"
										+ "values('"+list2.get(i).get(0)+"','"+list2.get(i).get(1)+"','123456','"+list2.get(i).get(2)+"','"+list2.get(i).get(3)+"',"
											+ "'"+ai+"','"+am+"')";
								dao.add(sql);
								
							}
						}
					}
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
				String sql="update student set name='"+student.getText()+"',sex='"+sex.getItemAt(sex.getSelectedIndex())+"',"
						+ "age='"+age.getText()+"',teamId='"+teamId.get(team.getSelectedIndex())+"',roleId='"+roleId.get(role.getSelectedIndex())+"' where id='"+id+"'";
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
		String[] key={"学号","学生名","性别","年龄","角色","班级","职位"};
		String[] values={"studentId","studentName","sex","age","rolename","teamName","position"};
		list=dao.query("select s.id studentId,s.name studentName,sex,age,r.name roleName,t.name teamName,position from student s"
				+ ",team t,role r where r.id=s.roleId and s.teamId=t.id;", values, key);
		if(!list.isEmpty()){
			int c=0;
			tableValueV.clear();
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//创建行向量
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("学号"+c));
					rowV.add(m.get("学生名"+c));
					rowV.add(m.get("性别"+c));
					rowV.add(m.get("年龄"+c));
					rowV.add("");
					rowV.add(m.get("职位"+c));
					rowV.add(m.get("班级"+c));
					rowV.add(m.get("角色"+c));
				}
				c++;
				tableValueV.add(rowV);									//把行向量添加到数据向量
			}
		}
		roleId=new ArrayList<String>();
		String[] values1={"id","name"};
		String key1[]={"id","name"};
		List<Map<String,Object>> ls=dao.query("select id,name from role;", values1,key1);
		if(!ls.isEmpty()){
			int c=0;
			role.removeAllItems();
			for(Map<String,Object> l:ls){
				role.addItem(l.get("name"+c));
				roleId.add((String)l.get("id"+c));
				System.out.println(l.get("name"+c)+"--name");
				c++;
			}
		}
		teamId=new ArrayList<String>();
		List<Map<String,Object>> teams=dao.query("select id,name from team;", values1,key1);
		if(!teams.isEmpty()){
			int c=0;
			team.removeAllItems();
			for(Map<String,Object> l:teams){
				team.addItem(l.get("name"+c));
				teamId.add((String)l.get("id"+c));
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
			list.add(tableValueV.get(f).get(5));
			list.add(tableValueV.get(f).get(6));
			list.add(tableValueV.get(f).get(7));
			list.add(tableValueV.get(f).get(8));
			id=list.get(1).toString();
			student.setText(list.get(2).toString());
			sex.setSelectedItem(list.get(3).toString());
			age.setText(list.get(4).toString());
			team.setSelectedItem(list.get(7));
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
		   List<List<Object>> list=dao.query("select Max(id) as id from student;", x);
		   if(!list.isEmpty()&&list.get(0).get(0)!=null){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(7);
			   return "student"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "student1001";
		   }
	   }
	   public void update(){
		   DAO dao=new DAO();
			String[] key={"编号","学生名","性别","年龄","角色","班级","职位"};
			String[] values={"studentId","studentName","sex","age","rolename","teamName","position"};
			list=dao.query("select s.id studentId,s.name studentName,sex,age,r.name roleName,t.name teamName,position from student s"
					+ ",team t,role r where r.id=s.roleId and s.teamId=t.id;", values, key);
			if(!list.isEmpty()){
				int c=0;
				tableValueV.clear();
				for(int row=0;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("编号"+c));
						rowV.add(m.get("学生名"+c));
						rowV.add(m.get("性别"+c));
						rowV.add(m.get("年龄"+c));
						rowV.add("");
						rowV.add(m.get("职位"+c));
						rowV.add(m.get("班级"+c));
						rowV.add(m.get("角色"+c));
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
