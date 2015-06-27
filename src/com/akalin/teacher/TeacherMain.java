package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JButton submit;//
	private JButton modify;//
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable aim;
	private Vector<Vector<Object>> tableValueV;
	private String manager;
	private JComboBox course;
	/**
	 * Launch the application.
	 */
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
		start.add(logout);
		
		JMenu screen = new JMenu("ɸѡ");
		menuBar.add(screen);
		
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
		grade1=new JLabel("�ɼ�");
		this.grade=new JTextField(10);
		addTop.add(sno1);
		addTop.add(sno);
		addTop.add(sname1);
		addTop.add(sname);
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
		modify=new JButton("�޸�");
		addButton.add(submit);
		addButton.add(modify);
		//
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("����������");
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("ƽ���ɼ���");
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
		//ɸѡ��ʽΪ�ɼ�
		btn_grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name "
						+ "from student,grade,team "
						+ "where team.id in(select student.teamId from student where student.id in(select team_course.sno from grade group by grade having score'"+condition.getText()+"'))"
								+ "and course.id=grade.id and student.id=grade.studentId";
				List<Map<String,Object>> list=dao.query(sql, values, key);
				if(!list.isEmpty()){
					tableValueV.clear();
					int c=0;
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//����������
						for(Map<String,Object> m:list){
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
						}
						c++;
						tableValueV.add(rowV);									//����������ӵ���������
					}
				}
			}
		});
		//ɸѡ��ʽΪѧ��
		credit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
				String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
				String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name "
						+ "from student,grade,team "
						+ "where team.id in(select student.teamId from student where student.id in(select grade.studentId from grade group by grade having score '"+(Integer.parseInt(condition.getText())*10+50)+"'))"
								+ "and course.id=grade.id and student.id=grade.studentId";
				List<Map<String,Object>> list=dao.query(sql, values, key);
				if(!list.isEmpty()){
					tableValueV.clear();
					int c=0;
					for(int row=1;row<list.size();row++){
						Vector<Object> rowV=new Vector<Object>();				//����������
						for(Map<String,Object> m:list){
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
						}
						c++;
						tableValueV.add(rowV);									//����������ӵ���������
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
							for(int row=1;row<list.size();row++){
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
								}
								c++;
								tableValueV.add(rowV);									//����������ӵ���������
							}
					}
				}
				}
			}
		});
		//������ύ��ť
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//������޸İ�ť
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
		String[] str={"id"};
		DAO dao=new DAO();
		List<List<Object>> teacherId=dao.query("select id from teacher where name='"+manager+"'", str);
		String[] s1={"teamId","courseId"};
		List<List<Object>> ss=dao.query("select teamId,courseId fromteam_course where teacherId='"+teacherId.get(0).get(0)+"'", s1);
		for(List<Object> ls:ss){
			String[] sc={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
			String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score"
					+ "from student,course,grade,team"
					+ "where student.is=grade.studentId and course.id='"+ls.get(1)+"' and team.name='"+ls.get(0)+"' and grade.courseId=course.id and student.teamId=team.id";
			List<List<Object>> sc2=dao.query(sql, str);
		}
	}
	public void update(){
		DAO dao=new DAO();
		String key[]={"ѧ��","����","�γ�","ѧ��","�γ̷���","���˷�ʽ","�ɼ�","�༶"};
		String values[]={"student.id","student.name","course.name","credit","ctype","ctype2","score","team.name"};
		String sql="select student.id,student.name,course.name,credit,ctype,ctype2,score,team.name "
				+ "from student,course,team,grade "
				+ "where team.id in(select student.teamId from student where student.id in(select grade.studentId from grade)) and student.id=grade.studentId and grade.courseId=course.id";
		List<Map<String,Object>> list=dao.query(sql, values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			int c=0;
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//����������
				for(Map<String,Object> m:list){
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
				}
				c++;
				tableValueV.add(rowV);									//����������ӵ���������
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
}
