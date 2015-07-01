package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import com.akalin.dao.Conn;
import com.akalin.dao.DAO;
import com.akalin.main.Login;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class TeacherMain extends JFrame {

	private JPanel contentPane;
	private JMenuItem output;		//����Excel
	private JMenuItem checkCourse;	//�鿴�α�
	private JMenuItem logout;		//�˳���¼
	private JMenuItem btn_grade;	//�ɼ�
	private JMenuItem credit;		//ѧ��
	private JMenuItem team;			//�༶
	private JMenuItem pie;			//����ͼ
	private JMenuItem diagram;		//����ͼ
	private JMenuItem setFuntion;	//�Զ��庯��
	private JMenuItem selectFuntion;//ѡ����
	private JMenuItem about;		//����
	private JComboBox scrn;		//ɸѡ��ʽ
	private JTextField condition;	//ɸѡ����
	private JLabel sno1;			//ѧ�� 
	private JLabel sname1;			//����
	private JLabel grade1;			//�ɼ�
	private JTextField sno;
	private JTextField sname;
	private JTextField grade;
	private JLabel function0;//
	private JLabel function1;//
	private JLabel function2;//
	private JLabel function3;//
	private JLabel function4;//
	private JTextField te1;//��ҵ
	private JTextField te2;//����
	private JTextField te3;//ƽʱ
	private JTextField te4;//��ĩ
	private int a1;
	private int a2;
	private int a3;
	private int a4;
	private JButton submit;//
	private JButton modify;//
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String manager;
	private JComboBox course;
	private List<Object> cid;
	private JComboBox theFunction;
	private List<String> fid;
	private JPanel check;
	private int average=0; //ƽ���ɼ�
	private int sub=0; //��������
	private int all=0;//�ܳɼ�
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
/*	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMain frame = new TeacherMain("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public TeacherMain(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		setTitle("��ʦ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		JMenu start = new JMenu("��ʼ");
		menuBar.add(start);
		
		logout = new JMenuItem("�˳�");
		excelInput=new JMenuItem();
		excelInput.setText("Excel���ݵ���");
		excelOutput=new JMenuItem();
		excelOutput.setText("����Excel�ļ�");
		start.add(excelInput);
		start.add(excelOutput);
		start.add(logout);
		
		
		JMenu screen = new JMenu("ɸѡ");
		menuBar.add(screen);
		
		JMenu function=new JMenu("����");
		menuBar.add(function);
		
		setFuntion=new JMenuItem("�Զ��庯������");
		function.add(setFuntion);
		
		selectFuntion=new JMenuItem("ѡ����");
		function.add(selectFuntion);
		
		btn_grade = new JMenuItem("�ɼ�");
		screen.add(btn_grade);
		
		credit = new JMenuItem("ѧ��");
		screen.add(credit);
		
		team = new JMenuItem("�༶");
		screen.add(team);
		
		JPanel topCenter=new JPanel();//��Ÿ���ʽ���
		contentPane.add(topCenter, BorderLayout.CENTER);
		topCenter.setLayout(new BorderLayout(0, 0));
		JPanel jtop=new JPanel();
		topCenter.add(jtop, BorderLayout.NORTH);
		scrn=new JComboBox();
		scrn.addItem("�ɼ�");
		scrn.addItem("ѧ��");
		scrn.addItem("�༶");
		jtop.add(scrn);
		condition=new JTextField(20);
		jtop.add(condition);
		check=new JPanel();
		theFunction=new JComboBox();
		topCenter.add(check,BorderLayout.SOUTH);
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("ѧ��");
		columnNameV.add("����");
		columnNameV.add("�γ�");
		columnNameV.add("ѧ��");
		columnNameV.add("�γ̷���");
		columnNameV.add("���˷�ʽ");
		columnNameV.add("�ɼ�");
		columnNameV.add("ȡ��ѧ��");
		columnNameV.add("����");
		columnNameV.add("ȡ�ü���");
				
		tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=0;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int col=0;col<9;col++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		aim=new MFixedColumnTable(columnNameV, tableValueV, 1);
		aim.setBorder(new EmptyBorder(20, 20, 20, 20));
		topCenter.add(aim, BorderLayout.CENTER);		//�������ӵ���������
		JTable f=aim.getFixedColumnTable();
		TableColumnModel c=aim.getFloatingColumnTable().getColumnModel();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//���� end
		
		JPanel footPanel = new JPanel();
		contentPane.add(footPanel, BorderLayout.SOUTH);
		footPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel addData=new JPanel();
		addData.setBorder(new EmptyBorder(20, 20, 20, 20));
		addData.setLayout(new BorderLayout());
		footPanel.add(addData, BorderLayout.NORTH);
		JPanel addTop=new JPanel();
		addData.add(addTop, BorderLayout.NORTH);
		JPanel addBottom=new JPanel();
		addData.add(addBottom, BorderLayout.CENTER);
		JPanel addButton=new JPanel();
		addData.add(addButton, BorderLayout.SOUTH);
		//�������
		sno1=new JLabel("ѧ��");
		sno=new JTextField(10);
		sname1=new JLabel("����");
		sname=new JTextField(10);
		JLabel course1=new JLabel("�γ�");
		course=new JComboBox();
		grade1=new JLabel("�ɼ�");
		this.grade=new JTextField(10);
		addTop.add(sno1);
		addTop.add(sno);
		addTop.add(sname1);
		addTop.add(sname);
		addTop.add(course1);
		addTop.add(course);
		addTop.add(grade1);
		addTop.add(this.grade);
		function0=new JLabel("������");
		function1=new JLabel("x12%(��ҵ)+");
		function2=new JLabel("x12%(����)+");
		function3=new JLabel("x12%(ƽʱ)+");
		function4=new JLabel("x12%(��ĩ)");
		te1=new JTextField(4);
		te2=new JTextField(4);
		te3=new JTextField(4);
		te4=new JTextField(4);
		addBottom.add(function0);
		addBottom.add(te1);
		addBottom.add(function1);
		addBottom.add(te2);
		addBottom.add(function2);
		addBottom.add(te3);
		addBottom.add(function3);
		addBottom.add(te4);
		addBottom.add(function4);
		addBottom.add(function4);
		addBottom.add(function4);
		submit=new JButton("�ύ");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		modify=new JButton("�޸�");
		addButton.add(submit);
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		addButton.add(modify);
		//
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("����������"+sub);
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("ƽ���ɼ���"+average);
		panel.add(averige);
		
		JPanel bottom = new JPanel();
		//bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		footPanel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("��ӭ����");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel ctrl = new JLabel("ʹ���ߣ�"+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		bottom.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("���ڣ�"+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("���ڵ�ʱ���ǣ�"+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		this.setTimer(time);
		setVisible(true);
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
	public void myEvent(){
		//�˳���¼
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login login=new Login();
				setVisible(false);
			}
		});
		setFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Function function=new Function(manager);
			}
		});
		selectFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] str={"id"};
				String teacherId="";
				List<String> teamId=new ArrayList<String>();
				DAO dao=new DAO();
				List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
				if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
					teacherId=(String)teacherIds.get(0).get(0);
				}
				//��ȡ��ʦ���Զ��庯��
				String key[]={"���","��ʦ��","�γ�","��ҵ","����","ƽʱ","��ĩ"};
				String values[]={"function.id","teacher.name","task","mid","pacific","final"};
				String sql="select function.id,teacher.name,task,mid,pacific,final "
						+ "from function,teacher,course "
						+ "where function.teacherId=teacher.id and function.courseId=course.id and teacher.id='"+teacherId+"'";
				list=dao.query(sql, values, key);
				if(!list.isEmpty()&&list.get(0)!=null){
					int c=0;
					for(int i=0;i<list.size();i++){
						for(Map<String,Object> map:list){
							String fvalue=""+map.get("�γ�"+c)+":��ҵ"+map.get("��ҵ"+c)+"%+����"+map.get("����"+c)+"%+ƽʱ"+map.get("ƽʱ"+c)+"%+��ĩ"+map.get("��ĩ"+c)+"";
							fid.add((String)map.get("���"+c));
							theFunction.addItem(fvalue);
						}
					c++;
					}
				}
				JLabel fff=new JLabel("��ѡ����:");
				check.add(fff);
				check.add(theFunction);
			}
		});
		theFunction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DAO dao=new DAO();
				String key[]={"��ҵ","����","ƽʱ","��ĩ"};
				String values[]={"task","mid","pacific","final"};
				String sql="select task,mid,pacific,final from myfunction where id='"+fid.get(theFunction.getSelectedIndex())+"'";
				list=dao.query(sql, values, key);
				if(!list.isEmpty()&&list.size()>0){
					int c=0;
					for(int i=0;i<list.size();i++){
						for(Map<String,Object> map:list){
							function1.setText(map.get("��ҵ"+c)+"��ҵ+");
							function2.setText(map.get("����"+c)+"����+");
							function3.setText(map.get("ƽʱ"+c)+"ƽʱ+");
							function4.setText(map.get("��ĩ"+c)+"��ĩ+");
							a1=(int)map.get("��ҵ"+c);
							a2=(int)map.get("����"+c);
							a3=(int)map.get("ƽʱ"+c);
							a4=(int)map.get("��ĩ"+c);
						}
						c++;
					}
				}
			}
		});
		//ɸѡ��ʽΪ�ɼ�
		btn_grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableValueV.clear();
				String[] str={"id"};
				String teacherId="";
				List<String> teamId=new ArrayList<String>();
				DAO dao=new DAO();
				List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
				if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
					teacherId=(String)teacherIds.get(0).get(0);
				}
				String[] s1={"teamId"};
				//��ȡ��ʦ���̵İ༶id
				List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
				if(!teams.isEmpty()&&teams.get(0)!=null){
					for(Object obj:teams.get(0)){
						teamId.add((String)obj);
					}
				}
				String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
				for(String ids:teamId){
					String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name"
							+ "from student,course,team,grade,team_course"
							+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and team.id=team_course.teamId and team.id='"+ids+"'"
									+ "group by student.id having score "+condition.getText()+"";
					mlist=dao.query(sql, values, key);
					if(!mlist.isEmpty()&& mlist.get(0)!=null){
						int c=0;
						for(int row=1;row<mlist.size();row++){
							Vector<Object> rowV=new Vector<Object>();				//����������
							for(Map<String,Object> m:mlist){
								rowV.add(m.get("ѧ��"+c));
								rowV.add(m.get("����"+c));
								rowV.add(m.get("�γ�"+c));
								rowV.add(m.get("ѧ��"+c));
								rowV.add(m.get("�γ̷���"+c));
								rowV.add(m.get("���˷�ʽ"+c));
								rowV.add(m.get("�ɼ�"+c));
								rowV.add(m.get("ѧ��"+c));
								rowV.add(calCredit((int)m.get("�ɼ�"+c)));
								rowV.add(calCredit((int)m.get("�ɼ�"+c))+(int)m.get("ѧ��"+c));
								rowV.add(m.get("�༶"+c));
								if(60>=(int)m.get("�ɼ�"+c))sub++;
								all=all+(int)m.get("�ɼ�"+c);
							}
							c++;
							tableValueV.add(rowV);
						}//����������ӵ���������
						average=all/mlist.size();
					}
				}
			}
		});
		//ɸѡ��ʽΪѧ��
		credit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableValueV.clear();
				String[] str={"id"};
				String teacherId="";
				List<String> teamId=new ArrayList<String>();
				DAO dao=new DAO();
				List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
				if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
					teacherId=(String)teacherIds.get(0).get(0);
				}
				String[] s1={"teamId"};
				//��ȡ��ʦ���̵İ༶id
				List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
				if(!teams.isEmpty()&&teams.get(0)!=null){
					for(Object obj:teams.get(0)){
						teamId.add((String)obj);
					}
				}
				String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
				for(String ids:teamId){
					String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name"
							+ "from student,course,team,grade,team_course"
							+ "where student.id=grade.studentId and grade.courseId=course.id and course.id=team_course.courseId and team.id=team_course.teamId and team.id='"+ids+"'"
									+ "group by student.id having credit '"+condition.getText()+"'";
					mlist=dao.query(sql, values, key);
					if(!mlist.isEmpty()&& mlist.get(0)!=null){
						int c=0;
						for(int row=1;row<mlist.size();row++){
							Vector<Object> rowV=new Vector<Object>();				//����������
							for(Map<String,Object> m:mlist){
								rowV.add(m.get("ѧ��"+c));
								rowV.add(m.get("����"+c));
								rowV.add(m.get("�γ�"+c));
								rowV.add(m.get("ѧ��"+c));
								rowV.add(m.get("�γ̷���"+c));
								rowV.add(m.get("���˷�ʽ"+c));
								rowV.add(m.get("�ɼ�"+c));
								rowV.add(m.get("ѧ��"+c));
								rowV.add(calCredit((int)m.get("�ɼ�"+c)));
								rowV.add(calCredit((int)m.get("�ɼ�"+c))+(int)m.get("ѧ��"+c));
								rowV.add(m.get("�༶"+c));
								if(60>=(int)m.get("�ɼ�"+c))sub++;
								all=all+(int)m.get("�ɼ�"+c);
							}
							c++;
							tableValueV.add(rowV);
						}//����������ӵ���������
						average=all/mlist.size();
					}
				}
			}
		});
		
		//ɸѡ��ʽΪ�༶
		team.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String[] str={"id,name"};
				String teamId="";
				String team="";
				List<List<Object>> teams=dao.query("select id,name from where name='"+condition.getText()+"'", str);
				if(!teams.isEmpty()&&teams.size()>0){
					teamId=(String)teams.get(0).get(0);
					team=(String)teams.get(0).get(1);
					List<List<Object>> students=dao.query("select id,name from student where teamId='"+teamId+"'", str);
					for(List<Object> ss:students){
						String key[]={"�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�"};
						String values[]={"name","credit","ctype","ctype2","score"};
						String sql="select name,credit,ctype,ctype2,score from course,grade where course.id=grade.courseId and grade.studentId='"+ss.get(0)+"'";
						List<Map<String,Object>> list=dao.query(sql, values, key);
						if(!list.isEmpty()){
							tableValueV.clear();
							int c=0;
							for(int row=0;row<list.size();row++){
								Vector<Object> rowV=new Vector<Object>();				//����������
								for(Map<String,Object> m:list){
									rowV.add(ss.get(0));
									rowV.add(ss.get(1));
									rowV.add(m.get("�γ�"+c));
									rowV.add(m.get("ѧ��"+c));
									rowV.add(m.get("�γ̷���"+c));
									rowV.add(m.get("���˷�ʽ"+c));
									rowV.add(m.get("�ɼ�"+c));
									rowV.add(m.get("ѧ��"+c));
									rowV.add(calCredit((int)m.get("�ɼ�"+c)));
									rowV.add(calCredit((int)m.get("�ɼ�"+c))+(int)m.get("ѧ��"+c));
									rowV.add(team);
									if(60>=(int)m.get("�ɼ�"+c))sub++;
									all=all+(int)m.get("�ɼ�"+c);
								}
								c++;
								tableValueV.add(rowV);									//����������ӵ���������
							}
							average=all/list.size();
					}
				}
				}
			}
		});
		//����Excel����
		excelInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open(e);
				File file=new File(path);
				com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
				String[] columnName={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
				list2=excelOpt.readExcel(file, columnName);
				flag=true;
				if(list2!=null){
					tableValueV.clear();
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();
						for(List<Object>ls:list2){
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
					for(int i=0;i<8;i++){
						ll.add(ss.get(i));
						System.out.print(ss.get(i)+"\t");
					}
					System.out.println();
					list2.add(ll);
				}
				button(e);
				String[] columnName={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
				com.akalin.tool.ExcelOpt excelOpt=new com.akalin.tool.ExcelOpt();
				excelOpt.writeExcelBo(path, columnName, list2);
				for(int i=0;i<list2.size();i++){
					//ѭ����ȡÿһ��Ԫ���ֵ
					for(int j=0;j<8;j++){
						//����д��Ԫ���ֵ
						System.out.print((String)list2.get(i).get(j));
					}
					System.out.println();
				}
			}
		});
		//������ύ��ť
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				if(!flag){
					String sql="insert into grade(studentId,courseId,score) values('"+sno.getText()+"','"+cid.get(course.getSelectedIndex())+"','"+finalScore()+"')";
					if(dao.add(sql)==1){
						Message message=new Message("��ӳɹ���");
						message.pack();
						update();
					}
				}else{
					int[] x={0};
					for(int i=1;i<list2.size();i++){
						if(dao.query("select * from student where name='"+list2.get(i).get(1)+"'", x).size()==0){
							Message message=new Message("��û����Ϊ"+list2.get(i).get(1)+"��ѧ��");
							message.pack();
						}else{
							if(dao.query("select * from course where name='"+list2.get(i).get(2)+"'", x).size()==0){
								Message message=new Message("��û����Ϊ"+list2.get(i).get(2)+"�Ŀγ�");
								message.pack();
							}else if(dao.query("select * from team where name='"+list2.get(i).get(7)+"'", x).size()==0){
								Message message=new Message("��û����Ϊ"+list2.get(i).get(7)+"�İ༶");
								message.pack();
							}else{
								List<List<Object>> listt=dao.query("select * from course where name='"+list2.get(i).get(2)+"'", x);
								String ai="";
								if(listt.size()>0&&listt.get(0).get(0)!=null){
									ai=(String)listt.get(0).get(0);
								}
								String sql="insert into grade(studentId,courseId,score) values('"+list2.get(i).get(0)+"','"+ai+"','"+list2.get(i).get(6)+"')";
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
		//������޸İ�ť
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="update grade set studentId='"+sno.getText()+"',courseId='"+cid.get(course.getSelectedIndex())+"',score='"+finalScore()+"')";
				if(dao.modify(sql)==1){
					Message message=new Message("�޸ĳɹ���");
					message.pack();
					update();
				}
			}
		});
	}
	 //����id
	   private String createId(){
		   DAO dao=new DAO();
		   String[] x={"id"};
		   List<List<Object>> list=dao.query("select Max(id) as id from team_course;", x);
		   if(!list.isEmpty()){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(2);
			   return "sc"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "sc1001";
		   }
	   }
	public void initData(){
		tableValueV.clear();
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String[] s1={"teamId"};
		//��ȡ��ʦ���̵İ༶id
		List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
		if(!teams.isEmpty()&&teams.get(0)!=null){
			for(Object obj:teams.get(0)){
				teamId.add((String)obj);
			}
		}
		String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
		String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
		List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
		for(String ids:teamId){
			String sql="select s.id studentId,s.name studentName,c.name coureName,credit,ctype,ctype2,score,t .name teamName"
					+ "from student s,course,team t,grade g,team_course tc"
					+ "where s .id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+ids+"'";
			mlist=dao.query(sql, values, key);
			if(!mlist.isEmpty()&& mlist.get(0)!=null){
				int c=0;
				for(int row=1;row<mlist.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//����������
					for(Map<String,Object> m:mlist){
						rowV.add(m.get("ѧ��"+c));
						rowV.add(m.get("����"+c));
						rowV.add(m.get("�γ�"+c));
						rowV.add(m.get("ѧ��"+c));
						rowV.add(m.get("�γ̷���"+c));
						rowV.add(m.get("���˷�ʽ"+c));
						rowV.add(m.get("�ɼ�"+c));
						rowV.add(m.get("ѧ��"+c));
						rowV.add(calCredit((int)m.get("�ɼ�"+c)));
						rowV.add(calCredit((int)m.get("�ɼ�"+c))+(int)m.get("ѧ��"+c));
						rowV.add(m.get("�༶"+c));
						if(60>=(int)m.get("�ɼ�"+c))sub++;
						all=all+(int)m.get("�ɼ�"+c);
					}
					c++;
					tableValueV.add(rowV);
				}//����������ӵ���������
				average=all/mlist.size();
			}
		}
		String s[]={"course.id","course.name"};
		List<List<Object>> ls=dao.query("select c.name from course c,team_course tc where c.id=tc.courseId and tc.teacherId='"+teacherId+"'", str);
		if(!ls.isEmpty()&&ls.size()>0){
			for(List<Object> ll:ls){
				for(Object obj:ll){
					cid.add(obj);
					course.addItem(obj);
				}
			}
		}
	}
	public void update(){
		tableValueV.clear();
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String[] s1={"teamId"};
		//��ȡ��ʦ���̵İ༶id
		List<List<Object>> teams=dao.query("select teamId from team_course where teacherId='"+teacherId+"'", s1);
		if(!teams.isEmpty()&&teams.get(0)!=null){
			for(Object obj:teams.get(0)){
				teamId.add((String)obj);
			}
		}
		String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
		String values[]={"studentId","studentName","courseName","credit","ctype","ctype2","score","teamName"};
		List<Map<String,Object>> mlist=new ArrayList<Map<String,Object>>();
		for(String ids:teamId){
			String sql="select s.id studentId,s.name studentName,c.name courseName,credit,ctype,ctype2,score,t.name teamName"
					+ "from student s,course c,team t,grade g,team_course tc"
					+ "where s.id=g.studentId and g.courseId=c.id and c.id=tc.courseId and t.id=tc.teamId and t.id='"+ids+"'";
			mlist=dao.query(sql, values, key);
			if(!mlist.isEmpty()&& mlist.get(0)!=null){
				int c=0;
				for(int row=1;row<mlist.size();row++){
					Vector<Object> rowV=new Vector<Object>();				//����������
					for(Map<String,Object> m:mlist){
						rowV.add(m.get("ѧ��"+c));
						rowV.add(m.get("����"+c));
						rowV.add(m.get("�γ�"+c));
						rowV.add(m.get("ѧ��"+c));
						rowV.add(m.get("�γ̷���"+c));
						rowV.add(m.get("���˷�ʽ"+c));
						rowV.add(m.get("�ɼ�"+c));
						rowV.add(m.get("ѧ��"+c));
						rowV.add(calCredit((int)m.get("�ɼ�"+c)));
						rowV.add(calCredit((int)m.get("�ɼ�"+c))+(int)m.get("ѧ��"+c));
						rowV.add(m.get("�༶"+c));
						if(60>=(int)m.get("�ɼ�"+c))sub++;
						all=all+(int)m.get("�ɼ�"+c);
					}
					c++;
					tableValueV.add(rowV);
				}//����������ӵ���������
				average=all/mlist.size();
			}
		}
	}
	//
	private double calCredit(int x){
		return (x-60+10)/10;
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
					sno.setText(list.get(0).toString());
					sname.setText(list.get(1).toString());
					grade.setText(list.get(6).toString());		
				}	
		}
		//���������ܷ�
	public double finalScore(){
		return Integer.parseInt(te1.getText())*a1+Integer.parseInt(te2.getText())*a2+Integer.parseInt(te3.getText())*a3+Integer.parseInt(te4.getText())*a4;
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
