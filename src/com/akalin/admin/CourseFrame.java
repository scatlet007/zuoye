package com.akalin.admin;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private JMenuItem scAdd;
	private String manager;
	private JButton modify;
	private JButton submit;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private MFixedColumnTable mainData;
	private Vector<Vector<Object>> tableValueV;
	private String id;
	private JComboBox major;
	private JComboBox type;
	private JComboBox type2;
	private List<String> majorId;
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
		excelInput=new JMenuItem();
		excelInput.setText("Excel���ݵ���");
		excelOutput=new JMenuItem();
		excelOutput.setText("����Excel�ļ�");
		courseManager.add(excelInput);
		courseManager.add(excelOutput);
		
		roleManager = new JMenu("��ɫ����");
		menuBar.add(roleManager);
		
		JMenu scManager=new JMenu("�༶�α����");
		menuBar.add(scManager);
		
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
		teacherManager.add(teacherAdd);
		studentAdd=new JMenuItem("ѧ�����");
		studentAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		courseAdd=new JMenuItem("�γ����");
		courseManager.add(courseAdd);
		courseAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		studentManager.add(studentAdd);
		roleAdd=new JMenuItem("��ɫ���");
		roleAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		roleManager.add(roleAdd);
		scAdd=new JMenuItem("�༶�α����");
		scManager.add(scAdd);
		
		JLabel course1 = new JLabel("�γ�����");
		course1.setFont(new Font("����", Font.PLAIN, 14));
		course1.setBounds(22, 55, 68, 15);
		contentPanel.add(course1);
		
		course = new JTextField();
		course.setBounds(85, 52, 131, 21);
		contentPanel.add(course);
		course.setColumns(20);
		
		JLabel credit1 = new JLabel("ѧ�֣�");
		credit1.setBounds(265, 55, 54, 15);
		contentPanel.add(credit1);
		
		credit = new JTextField();
		credit.setBounds(309, 52, 77, 21);
		contentPanel.add(credit);
		credit.setColumns(10);
		
		JLabel type1 = new JLabel("�γ̷��ࣺ");
		type1.setFont(new Font("����", Font.PLAIN, 14));
		type1.setBounds(428, 55, 77, 15);
		contentPanel.add(type1);
		
		type = new JComboBox();
		type.setBounds(515, 52, 110, 21);
		type.addItem("רҵѡ�޿�");
		type.addItem("רҵ���޿�");
		type.addItem("ͨʶ���޿�");
		contentPanel.add(type);
		
		JLabel type01 = new JLabel("���˷�ʽ��");
		type01.setFont(new Font("����", Font.PLAIN, 14));
		type01.setBounds(661, 58, 77, 15);
		contentPanel.add(type01);
		
		type2 = new JComboBox();
		type2.setBounds(736, 52, 99, 21);
		type2.addItem("����");
		type2.addItem("����");
		contentPanel.add(type2);
		
		JLabel major1 = new JLabel("רҵ��");
		major1.setFont(new Font("����", Font.PLAIN, 14));
		major1.setBounds(38, 104, 54, 15);
		contentPanel.add(major1);
		
		major = new JComboBox();
		major.setBounds(110, 101, 144, 21);
		contentPanel.add(major);
		
		submit = new JButton("�ύ");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		submit.setBounds(294, 100, 93, 23);
		contentPanel.add(submit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 138, 864, 379);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("���");
		columnNameV.add("���");
		columnNameV.add("רҵ��");
		columnNameV.add("�γ���");
		columnNameV.add("�γ̷���");
		columnNameV.add("���˷�ʽ");
		columnNameV.add("ѧ��");
									
		tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int cov=0;cov<6;cov++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(50, 50, 10, 50));
		panel_1.add(mainData, BorderLayout.CENTER);		//�������ӵ���������
		JTable f=mainData.getFixedColumnTable();
		TableColumnModel c=mainData.getFloatingColumnTable().getColumnModel();
		c.getColumn(5).setPreferredWidth(300);
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//���� end
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(0, 537, 884, 25);
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("��ӭ����");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(welcome);
		
		JLabel ctrl = new JLabel("ʹ���ߣ�"+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		panel_2.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("���ڣ�"+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("���ڵ�ʱ���ǣ�"+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(time);
		this.setTimer(time);
		
		modify = new JButton("�޸�");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		modify.setBounds(557, 104, 93, 23);
		contentPanel.add(modify);
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
				scAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						SCFrame scFrame=new SCFrame(manager);
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
						String[] columnName={"רҵ��","�γ���","�γ̷���","���˷�ʽ","ѧ��"};
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
						String[] columnName={"���","���","רҵ��","�γ���","�γ̷���","���˷�ʽ","ѧ��"};
						com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
						excelOpt.writeExcelBo(path, columnName, list2);
						for(int i=0;i<list2.size();i++){
							//ѭ����ȡÿһ��Ԫ���ֵ
							for(int j=0;j<7;j++){
								//����д��Ԫ���ֵ
								System.out.print((String)list2.get(i).get(j));
							}
							System.out.println();
						}
					}
				});
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				DAO dao=new DAO();
				if(!flag){
					String[] x={"id"};
					String sql="insert into course(id,name,ctype,ctype2,credit,majorId) values"
							+ "('"+createId()+"','"+course.getText()+"','"+type.getItemAt(type.getSelectedIndex())+"','"+type2.getItemAt(type2.getSelectedIndex())+"','"+credit.getText()+"','"+majorId.get(major.getSelectedIndex())+"');";
				
					if(major.getSelectedItem()==null||major.getSelectedItem().equals("")){
						Message message=new Message("�������Ӧ��רҵ");
						message.pack();
					}else{
						if(dao.add(sql)==1){
							Message message=new Message("ִ�гɹ���");
							message.pack();
							update();
						}
					}
				}else{
					for(int i=1;i<list2.size();i++){
						int[] x={1};
						if(dao.query("select * from course where name='"+list2.get(i).get(1)+"'", x).size()>0){
							Message message=new Message("�γ���Ϊ��"+list2.get(i).get(1)+"�Ѵ��ڣ�");
							message.pack();
						}else{
							List<List<Object>> listt=dao.query("select * from major where name='"+list2.get(i).get(0)+"'", x);
							String ai="";
							if(listt.size()>0&&listt.get(0).get(0)!=null){
								ai=(String)listt.get(0).get(0);
							}
							String sql="insert into course(id,name,ctype,ctype2,credit,majorId) values"
									+ "('"+createId()+"','"+list2.get(i).get(1)+"','"+list2.get(i).get(2)+"','"+list2.get(i).get(3)+"','"+list2.get(i).get(4)+"','"+ai+"');";
							dao.add(sql);
						}
					}
					Message message=new Message("ִ�гɹ���");
					message.pack();
					update();
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
					Message message=new Message("ִ�гɹ���");
					message.pack();
					update();
				}
			}
		});
	}
	//��ʼ������
		public void initData(){
			 DAO dao=new DAO();
			   String[] key={"���","רҵ��","�γ���","�γ̷���","���˷�ʽ","ѧ��"};
			   String[] values={"courseId","majorName","courseName","ctype","ctype2","credit"};
				list=dao.query("select c.id courseId,m.name majorName,c.name courseName,ctype,ctype2,credit from course c,major m where c.majorId=m.id;", values, key);
				if(!list.isEmpty()){
					int c=0;
					tableValueV.clear();
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//����������
						rowV.add(row);
						for(Map<String,Object> m:list){
							rowV.add(m.get("���"+c));
							rowV.add(m.get("רҵ��"+c));
							rowV.add(m.get("�γ���"+c));
							rowV.add(m.get("�γ̷���"+c));
							rowV.add(m.get("���˷�ʽ"+c));
							rowV.add(m.get("ѧ��"+c));
						}
						c++;
						tableValueV.add(rowV);									//����������ӵ���������
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
	//ѡ��ĳһ�б������ݣ���������ʾ����д��
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
	   String[] key={"���","רҵ��","�γ���","�γ̷���","���˷�ʽ","ѧ��"};
	   String[] values={"courseId","majorName","courseName","ctype","ctype2","credit"};
		list=dao.query("select c.id courseId,m.name majorName,c.name courseName,ctype,ctype2,credit from course c,major m where c.majorId=m.id;", values, key);
		if(!list.isEmpty()){
			int c=0;
			tableValueV.clear();
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//����������
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("���"+c));
					rowV.add(m.get("רҵ��"+c));
					rowV.add(m.get("�γ���"+c));
					rowV.add(m.get("�γ̷���"+c));
					rowV.add(m.get("���˷�ʽ"+c));
					rowV.add(m.get("ѧ��"+c));
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
