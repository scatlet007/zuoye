package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.akalin.dao.DAO;
import com.akalin.tool.DateChooserJButton;
import com.akalin.tool.FileFilterFrame;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CollegeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3293667833496357103L;
	private JPanel contentPane;
	private JTextField collegeName;
	private JTextField creates;
	private JTextField details;
	private String thename;
	private String thecreate;
	private List<Map<String,Object>> list;
	private List<List<Object>> list2=new ArrayList<List<Object>>();
	private ListSelectionModel fixed;
	private boolean flag=false;
	private MFixedColumnTable mainData;
	private Vector<Vector<Object>> tableValueV;
	private JButton submit;
	private JButton modify;
	private JMenuItem collegeAdd;
	private JMenuItem excelInput;
	private JMenuItem excelOutput;
	private JMenuItem majorAdd;
	private JMenuItem teamAdd;
	private JMenuItem teacherAdd;
	private JMenuItem studentAdd;
	private JMenuItem courseAdd;
	private JMenuItem roleAdd;
	private JMenuItem scAdd;
	private String manager;
	private String id;
	private DateChooserJButton dateChooserJButton;
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
	public CollegeFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		this.setTitle("ѧԺ�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 550);
		this.setLocationRelativeTo(null);//��������Ļ�м���ʾ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setBorder(new EmptyBorder(0, 0, 30, 0));
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.CENTER);
		
		JMenu collegeManaer = new JMenu("ѧԺ����");
		menuBar.add(collegeManaer);
		
		JMenu majorManager = new JMenu("רҵ����");
		menuBar.add(majorManager);
		
		JMenu teamManager = new JMenu("�༶����");
		menuBar.add(teamManager);
		
		JMenu teacherManager = new JMenu("��ʦ����");
		menuBar.add(teacherManager);
		
		JMenu studentManager = new JMenu("ѧ������");
		menuBar.add(studentManager);
		
		JMenu courseManager = new JMenu("�γ̹���");
		menuBar.add(courseManager);
		
		JMenu roleManager = new JMenu("��ɫ����");
		menuBar.add(roleManager);
		
		JMenu scManager=new JMenu("�༶�α����");
		menuBar.add(scManager);
		
		collegeAdd=new JMenuItem("ѧԺ���");
		collegeAdd.setIcon(new ImageIcon("src/res/icon/add.png"));
		excelInput=new JMenuItem();
		excelInput.setText("Excel���ݵ���");
		excelOutput=new JMenuItem();
		excelOutput.setText("����Excel�ļ�");
		collegeManaer.add(collegeAdd);
		collegeManaer.add(excelInput);
		collegeManaer.add(excelOutput);
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
		
		JPanel panel_1 = new JPanel();
		top.add(panel_1, BorderLayout.SOUTH);
		panel_1.setBounds(0, 0, 675, 250);
		panel_1.setLayout(new BorderLayout());
		panel_1.setLayout(new BorderLayout());
		
		JPanel p1=new JPanel();
		panel_1.add(p1,BorderLayout.NORTH);
		JLabel name = new JLabel("ѧԺ��");
		p1.add(name);
		
		collegeName = new JTextField();
		p1.add(collegeName);
		collegeName.setColumns(10);
		
		JLabel createTime = new JLabel("����ʱ��");
		p1.add(createTime);
		
		dateChooserJButton=new DateChooserJButton();
		p1.add(dateChooserJButton);
		
		JPanel p2=new JPanel();
		panel_1.add(p2,BorderLayout.CENTER);
		p2.setBorder(new EmptyBorder(3, 3, 3, 3));
		JLabel status = new JLabel("������:");
		p2.add(status);
		
		details = new JTextField();
		details.setColumns(30);
		p2.add(details);	
		submit = new JButton("�ύ");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		p2.add(submit);
		modify = new JButton("�޸�");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		p2.add(modify);
		
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("���");
		columnNameV.add("ѧԺ");
		columnNameV.add("����ʱ��");
		columnNameV.add("������");
								
		tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int cov=0;cov<4;cov++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.add(mainData, BorderLayout.CENTER);		//�������ӵ���������
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, BorderLayout.SOUTH);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("��ӭʹ��");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("�����ߣ�"+manager);
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("���ڣ�"+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("���ڵ�ʱ���ǣ�"+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		this.setTimer(time);
		JTable f=mainData.getFixedColumnTable();
		TableColumnModel c=mainData.getFloatingColumnTable().getColumnModel();
		c.getColumn(2).setPreferredWidth(400);
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		setVisible(true);
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
				id=list.get(0).toString();
				collegeName.setText(list.get(1).toString());
				dateChooserJButton.setText(list.get(2).toString());
				details.setText(list.get(3).toString());
				thename=list.get(1).toString();
				thecreate=list.get(2).toString();
			}
			
			
		}
	//��ʼ������
	public void initData(){
		DAO dao=new DAO();
		String[] key={"���","ѧԺ","����ʱ��","������"};
		String[] values={"id","name","createTime","status"};
		list=dao.query("select id,name,createTime,status from college", values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//����������
				for(Map<String,Object> m:list){
					rowV.add(m.get("���"+c));
					rowV.add(m.get("ѧԺ"+c));
					rowV.add(m.get("����ʱ��"+c));
					rowV.add(m.get("������"+c));
				}
				c++;
				tableValueV.add(rowV);									//����������ӵ���������
			}
		}
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
				String[] columnName={"ѧԺ","����ʱ��","������"};
				list2=excelOpt.readExcel(file, columnName);
				flag=true;
				if(list2!=null){
					tableValueV.clear();
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();
						for(List<Object>ls:list2){
							rowV.add("");
							rowV.add(ls.get(0));
							rowV.add(ls.get(1));
							rowV.add(ls.get(2));
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
					for(int i=0;i<4;i++){
						ll.add(ss.get(i));
						System.out.print(ss.get(i)+"\t");
					}
					System.out.println();
					list2.add(ll);
				}
				button(e);
				String[] columnName={"���","ѧԺ","����ʱ��","������"};
				com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
				excelOpt.writeExcelBo(path, columnName, list2);
				for(int i=0;i<list2.size();i++){
					//ѭ����ȡÿһ��Ԫ���ֵ
					for(int j=0;j<4;j++){
						//����д��Ԫ���ֵ
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
					String sql="insert into college(id,name,createTime,status) values"
							+ "('"+createId()+"','"+collegeName.getText()+"','"+dateChooserJButton.getText()+"','"+details.getText()+"');";
					if(dao.add(sql)==1){
						Message message=new Message("ִ�гɹ���");
						message.pack();
						update();
					}
				}else{
					for(int i=1;i<list2.size();i++){
						int[] x={1};
						if(dao.query("select * from college where name='"+list2.get(i).get(0)+"'", x).size()>0){
							Message message=new Message("ѧԺ��Ϊ��"+list2.get(i).get(0)+"��ѧԺ�Ѵ��ڣ�");
							message.pack();
						}else{
							String sql="insert into college(id,name,createTime,status) values"
									+ "('"+createId()+"','"+list2.get(i).get(0)+"','"+list2.get(i).get(1)+"','"+list2.get(i).get(2)+"');";
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
				String sql="update college set name='"+collegeName.getText()+"',createTime='"+dateChooserJButton.getText()+"',status='"+details.getText()+"' where id='"+id+"'";
				if(dao.modify(sql)==1){
					Message message=new Message("ִ�гɹ���");
					message.pack();
					update();
				}
			}
		});
	}
	public void update(){
		DAO dao=new DAO();
		String[] key={"���","ѧԺ","����ʱ��","������"};
		String[] values={"id","name","createTime","status"};
		list=dao.query("select id,name,createTime,status from college where id is not null;", values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//����������
				for(Map<String,Object> m:list){
					rowV.add(m.get("���"+c));
					rowV.add(m.get("ѧԺ"+c));
					rowV.add(m.get("����ʱ��"+c));
					rowV.add(m.get("������"+c));
				}
				c++;
				tableValueV.add(rowV);									//����������ӵ���������
			}
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
	   List<List<Object>> list=dao.query("select Max(id) as id from college", x);
	   if(!list.isEmpty()&&list.get(0).get(0)!=null){
		   String id=list.get(0).get(0).toString();
		   String subId=id.substring(7);
		   return "college"+String.valueOf(Integer.parseInt(subId)+1);
	   }else{
		   return "college1001";
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
