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
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.io.File;
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
		
		collegeManager = new JMenu("ѧԺ����");
		menuBar.add(collegeManager);
		
		majorManager = new JMenu("רҵ����");
		menuBar.add(majorManager);
		
		teamManager = new JMenu("�༶����");
		menuBar.add(teamManager);
		
		teacherManager = new JMenu("��ʦ����");
		menuBar.add(teacherManager);
		
		studentManager = new JMenu("ѧ������");
		menuBar.add(studentManager);
		
		courseManager = new JMenu("�γ̹���");
		menuBar.add(courseManager);
		
		roleManager = new JMenu("��ɫ����");
		menuBar.add(roleManager);
		
		collegeAdd=new JMenuItem("ѧԺ���");
		collegeAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		collegeManager.add(collegeAdd);
		majorAdd=new JMenuItem("רҵ���");
		majorAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		majorManager.add(majorAdd);
		teamAdd=new JMenuItem("�༶���"); 
		teamAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		teamManager.add(teamAdd);
		teacherAdd=new JMenuItem("��ʦ���");
		teacherAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		excelInput=new JMenuItem();
		excelInput.setText("Excel���ݵ���");
		excelOutput=new JMenuItem();
		excelOutput.setText("����Excel�ļ�");
		teacherManager.add(teacherAdd);
		teacherManager.add(excelInput);
		teacherManager.add(excelOutput);
		studentAdd=new JMenuItem("ѧ�����");
		studentAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		courseAdd=new JMenuItem("�γ����");
		courseManager.add(courseAdd);
		courseAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		studentManager.add(studentAdd);
		roleAdd=new JMenuItem("��ɫ���");
		roleAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		roleManager.add(roleAdd);
		
		JLabel name = new JLabel("\u6559\u5E08\u540D\uFF1A");
		name.setFont(new Font("����", Font.PLAIN, 14));
		name.setBounds(10, 58, 56, 15);
		contentPane.add(name);
		
		teacher = new JTextField();
		teacher.setBounds(69, 55, 122, 21);
		contentPane.add(teacher);
		teacher.setColumns(10);
		
		JLabel college = new JLabel("\u5B66\u9662\uFF1A");
		college.setFont(new Font("����", Font.PLAIN, 14));
		college.setBounds(234, 61, 54, 15);
		contentPane.add(college);
		
		collegeName = new JComboBox();
		collegeName.setModel(new DefaultComboBoxModel(new String[] {"�Ź�ѧԺ","���ѧԺ","��ѧԺ"}));
		collegeName.setBounds(280, 55, 152, 21);
		contentPane.add(collegeName);
		
		JLabel sex1 = new JLabel("\u6027\u522B\uFF1A");
		sex1.setFont(new Font("����", Font.PLAIN, 14));
		sex1.setBounds(471, 61, 54, 15);
		contentPane.add(sex1);
		
		sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"��","Ů"}));
		sex.setBounds(516, 55, 64, 21);
		contentPane.add(sex);
		
		JLabel age1 = new JLabel("\u5E74\u9F84\uFF1A");
		age1.setFont(new Font("����", Font.PLAIN, 14));
		age1.setBounds(615, 58, 54, 15);
		contentPane.add(age1);
		
		age = new JTextField();
		age.setBounds(661, 55, 66, 21);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel telephone1 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		telephone1.setFont(new Font("����", Font.PLAIN, 14));
		telephone1.setBounds(12, 108, 70, 15);
		contentPane.add(telephone1);
		
		phone = new JTextField();
		phone.setBounds(80, 105, 131, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel position1 = new JLabel("\u804C\u4F4D\uFF1A");
		position1.setFont(new Font("����", Font.PLAIN, 14));
		position1.setBounds(234, 108, 54, 15);
		contentPane.add(position1);
		
		position = new JComboBox();
		position.setBounds(280, 105, 152, 21);
		position.addItem("������");
		position.addItem("��ͨ��ʦ");
		contentPane.add(position);
		
		JLabel role1 = new JLabel("\u89D2\u8272\uFF1A");
		role1.setFont(new Font("����", Font.PLAIN, 14));
		role1.setBounds(471, 108, 54, 15);
		contentPane.add(role1);
		
		role = new JComboBox();
		role.setBounds(516, 105, 116, 21);
		role.addItem("����");
		role.addItem("��ʦ");
		contentPane.add(role);
		
		submit = new JButton("�ύ");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		submit.setBounds(655, 104, 93, 23);
		contentPane.add(submit);
		
		JPanel mainData = new JPanel();
		mainData.setBounds(10, 188, 864, 317);
		contentPane.add(mainData);
		mainData.setLayout(new BorderLayout(0, 0));
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("���");
		columnNameV.add("���");
		columnNameV.add("��ʦ��");
		columnNameV.add("�Ա�");
		columnNameV.add("����");
		columnNameV.add("�ֻ���");
		columnNameV.add("ְλ");
		columnNameV.add("ѧԺ");
		columnNameV.add("��ɫ");
											
		tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int cov=0;cov<8;cov++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		aim=new MFixedColumnTable(columnNameV, tableValueV, 1);
		aim.setBorder(new EmptyBorder(50, 50, 10, 50));
		mainData.add(aim, BorderLayout.CENTER);
		JTable f=aim.getFixedColumnTable();
		TableColumnModel c=aim.getFloatingColumnTable().getColumnModel();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//���� end
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 536, 874, 26);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("��ӭ����");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(welcome);
		
		JLabel ctrl = new JLabel("ʹ���ߣ�"+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		panel_1.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("���ڣ�"+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("���ڵ�ʱ���ǣ�"+getTime.getTime());
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
		//�������ѧԺ�˵�ʱ
				collegeAdd.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						CollegeFrame college=new CollegeFrame(manager);
						System.out.println("�����ȤѧԺ����");
						setVisible(false);
						
					}
				});
				//���רҵ����ʱ
				majorAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Major majorFrame=new Major(manager);
						setVisible(false);
					}
				});
				//����༶����
				teamAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						TeamFrame teamFrame=new TeamFrame(manager);
						setVisible(false);
						
					}
				});
				//�����ʦ����
				teacherAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						TeacherFrame teacherFrame=new TeacherFrame(manager);
						setVisible(false);
						
					}
				});
				//���ѧ������
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
				//�����ɫ����
				roleAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						RoleFrame roleFrame=new RoleFrame(manager);
						setVisible(false);
					}
				});
				//����Excel����
				excelInput.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						open(e);
						File file=new File(path);
						com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
						String[] columnName={"��ʦ��","�Ա�","����","�ֻ���","ְλ","ѧԺ","��ɫ"};
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
							for(int i=0;i<9;i++){
								ll.add(ss.get(i));
								System.out.print(ss.get(i)+"\t");
							}
							System.out.println();
							list2.add(ll);
						}
						button(e);
						String[] columnName={"���","���","��ʦ��","�Ա�","����","�ֻ���","ְλ","ѧԺ","��ɫ"};
						com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
						excelOpt.writeExcelBo(path, columnName, list2);
						for(int i=0;i<list2.size();i++){
							//ѭ����ȡÿһ��Ԫ���ֵ
							for(int j=0;j<9;j++){
								//����д��Ԫ���ֵ
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
					String sql="insert into teacher(id,name,password,sex,age,position,collegeId,roleId)"
							+ "values('"+createId()+"','"+teacher.getText()+"','123456','"+sex.getSelectedItem()+"','"+age.getText()+"',"
									+ "'"+position.getItemAt(position.getSelectedIndex())+"','"+collegeId.get(collegeName.getSelectedIndex())+"','"+roleId.get(role.getSelectedIndex())+"')";
					if(collegeId.get(collegeName.getSelectedIndex())==null||collegeId.get(collegeName.getSelectedIndex()).equals("")){      
						Message message=new Message("ѧԺΪ�գ��������ӦѧԺ��");
						message.pack();
					}else{
						if(dao.add(sql)==1){
							Message message=new Message("��ӳɹ���");
							message.pack();
						}
					}
				update();
				}else{
					int[] x={1};
					for(int i=1;i<list2.size();i++){
						if(dao.query("select * from college where name='"+list2.get(i).get(5)+"'", x).size()==0){
							Message message=new Message("��û����Ϊ"+list2.get(i).get(5)+"��ѧԺ");
							message.pack();
						}else{
							if(dao.query("select * from role where name='"+list2.get(i).get(6)+"'", x).size()==0){
								Message message=new Message("��û����Ϊ"+list2.get(i).get(6)+"�Ľ�ɫ");
								message.pack();
							}else{
								String sql="insert into teacher(id,name,password,sex,age,position,collegeId,roleId)"
										+ "values('"+createId()+"','"+list2.get(i).get(0)+"','123456','"+list2.get(i).get(1)+"','"+list2.get(i).get(2)+"',"
												+ "'"+list2.get(i).get(4)+"','"+list2.get(i).get(5)+"','"+list2.get(i).get(6)+"')";
								dao.add(sql);
								
							}
						}
					}
					Message message=new Message("��ӳɹ���");
					message.pack();
					update();
				}
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update teacher set name='"+teacher.getText()+"',sex='"+sex.getItemAt(sex.getSelectedIndex())+"',"
						+ "age='"+age.getText()+"',telephone='"+phone.getText()+"',position='"+position.getItemAt(position.getSelectedIndex())+"',collegeId='"+collegeId.get(collegeName.getSelectedIndex())+"',roleId='"+roleId.get(role.getSelectedIndex())+"' where id='"+id+"'";
				if(dao.modify(sql)==1){
					Message message=new Message("�޸ĳɹ���");
					message.pack();
				}
				update();
			}
		});
	}
	
	//��ʼ������
		public void initData(){
			DAO dao=new DAO();
			String[] key={"���","��ʦ��","�Ա�","����","��ɫ","�ֻ���","ѧԺ","ְλ"};
			String[] values={"teacherId","teacherName","sex","age","roleName","telephone","collegeName","position"};
			list=dao.query("select t.id teacherId,t.name teacherName,sex,age,r.name roleName,c.name collegeName,telephone,position from teacher t,role r,college c where t.collegeId=c.id and r.id=t.roleId;", values, key);
			if(!list.isEmpty()){
				tableValueV.clear();
				int c=0;
				for(int row=0;row<list.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//����������
					rowV.add(row);
					for(Map<String,Object> m:list){
						rowV.add(m.get("���"+c));
						rowV.add(m.get("��ʦ��"+c));
						rowV.add(m.get("�Ա�"+c));
						rowV.add(m.get("����"+c));
						rowV.add(m.get("�ֻ���"+c));
						rowV.add(m.get("ְλ"+c));
						rowV.add(m.get("ѧԺ"+c));
						rowV.add(m.get("��ɫ"+c));
					}
					c++;
					tableValueV.add(rowV);									//����������ӵ���������
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
			//ѡ��ĳһ�б������ݣ���������ʾ����д��
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
		//����Timer 1000msʵ��һ�ζ��� ʵ����һ���߳�   
		 private void setTimer(JLabel time){   
		     final JLabel varTime = time;   
		     Timer timeAction = new Timer(1000, new ActionListener() {          
		  
		         public void actionPerformed(ActionEvent e) {       
		             GetTime getTime=new GetTime();  
		             varTime.setText("���ڵ�ʱ���ǣ�"+getTime.getTime());   
		         }      
		        });            
		        timeAction.start();        
		  } 
		    //����id
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
				String[] key={"���","��ʦ��","�Ա�","����","��ɫ","�ֻ���","ѧԺ","ְλ"};
				String[] values={"teacherId","teacherName","sex","age","roleName","telephone","collegeName","position"};
				list=dao.query("select t.id teacherId,t.name teacherName,sex,age,r.name roleName,c.name collegeName,telephone,position from teacher t,role r,college c where t.collegeId=c.id and r.id=t.roleId;;", values, key);
				if(!list.isEmpty()){
					tableValueV.clear();
					int c=0;
					for(int row=0;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//����������
						rowV.add(row);
						for(Map<String,Object> m:list){
							rowV.add(m.get("���"+c));
							rowV.add(m.get("��ʦ��"+c));
							rowV.add(m.get("�Ա�"+c));
							rowV.add(m.get("����"+c));
							rowV.add(m.get("�ֻ���"+c));
							rowV.add(m.get("ְλ"+c));
							rowV.add(m.get("ѧԺ"+c));
							rowV.add(m.get("��ɫ"+c));
						}
						c++;
						tableValueV.add(rowV);									//����������ӵ���������
					}
				}
		   }
		   protected void button(ActionEvent e){
				JFileChooser chooser=new JFileChooser();
				FileFilter filter=new FileNameExtensionFilter("�ı�����(.xls)","xls");
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
				FileFilter filter=new FileNameExtensionFilter("�ı�����(.xls)","xls");
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
