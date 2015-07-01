package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;

import com.akalin.dao.Conn;
import com.akalin.dao.DAO;
import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;
import com.akalin.tool.Message;

import javax.swing.JLabel;

public class Function extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=77,277
	 */
	private JLabel label1;//�γ�
	private JLabel label2;//��ҵ
	private JLabel label3;//����
	private JLabel label4;//ƽʱ
	private JLabel label5;//��ĩ
	private JComboBox course;//�γ�
	private JComboBox team;
	private JTextField homework;
	private JTextField middy;
	private JTextField normal;
	private JTextField end;
	private JPanel foot;
	private int h;
	private int m;
	private int n;
	private int t;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable mainData;
	private Vector<Vector<Object>> tableValueV;
	private String manager;
	private JButton submit;
	private JButton modify;
	private List<String> courseId;
	private String teacherId="";
	private List<String> teamId;
	
	public Function(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void myEvent(){
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DAO dao=new DAO();
				String sql="insert into myfunction(id,courseId,teamId,teacherId,task,mid,pacific,final) values"
						+ "('"+createId()+"''"+courseId.get(course.getSelectedIndex())+"','"+teamId.get(team.getSelectedIndex())+"','"+teacherId+"',"
								+ "'"+homework.getText()+"','"+middy.getText()+"','"+normal.getText()+"','"+end.getText()+"');";
				if(dao.add(sql)==1){
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
				String sql="update myfunction set"
						+ "courseId='"+courseId.get(course.getSelectedIndex())+"',teamId='"+teamId.get(team.getSelectedIndex())+"',teacherId='"+teacherId+"',"
								+ "task='"+homework.getText()+"',mid='"+middy.getText()+"',pacific='"+normal.getText()+"',final='"+end.getText()+"';";
				if(dao.modify(sql)==1){
					Message message=new Message("ִ�гɹ���");
					message.pack();
					update();
				}
				
			}
		});
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
			course.setSelectedItem(list.get(1).toString());
			homework.setText(list.get(2).toString());
			middy.setText(list.get(3).toString());	
			normal.setText(list.get(4).toString());	
			end.setText(list.get(5).toString());	
		}
		
	}
	public void init(){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		contentPane.add(center, BorderLayout.CENTER);
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("���");
		columnNameV.add("�γ�");
		columnNameV.add("��ҵ");
		columnNameV.add("����");
		columnNameV.add("ƽʱ");
		columnNameV.add("��ĩ");
						
		tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<10;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int i=0;i<5;i++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(50, 50, 10, 50));
		center.add(mainData, BorderLayout.CENTER);		//�������ӵ���������
		JTable f=mainData.getFixedColumnTable();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//���� end
		JPanel bottom=new JPanel();
		bottom.setBounds(20, 20, center.getWidth(), 100);
		center.add(bottom, BorderLayout.SOUTH);
		label1=new JLabel("�γ�");
		label2=new JLabel("��ҵ");
		label3=new JLabel("����");
		label4=new JLabel("ƽʱ");
		label5=new JLabel("��ĩ");
		course=new JComboBox();
		homework=new JTextField(5);
		middy=new JTextField(5);
		normal=new JTextField(5);
		end=new JTextField(5);
		JLabel te=new JLabel("�༶");
		team=new JComboBox();
		bottom.add(te);
		bottom.add(team);
		bottom.add(label1);
		bottom.add(course);
		bottom.add(label2);
		bottom.add(homework);
		bottom.add(label3);
		bottom.add(middy);
		bottom.add(label4);
		bottom.add(normal);
		bottom.add(label5);
		bottom.add(end);
		submit=new JButton("�ύ");
		submit.setIcon(new ImageIcon("src/res/icon/add.png"));
		modify=new JButton("�޸�");
		modify.setIcon(new ImageIcon("src/res/icon/modify16.png"));
		bottom.add(submit);
		bottom.add(modify);
		
		foot = new JPanel();
		contentPane.add(foot, BorderLayout.SOUTH);
		foot.setLayout(new GridLayout(1,6));
		JLabel welcome = new JLabel("��ӭ����");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(welcome);
		
		JLabel ctrl = new JLabel("ʹ���ߣ�"+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));   
		foot.add(ctrl);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("���ڣ�"+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("���ڵ�ʱ���ǣ�"+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(time);
		this.setTimer(time);
		setVisible(true);
	}

	public void initData(){
		String[] str={"id"};
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String key[]={"�γ�","��ҵ","����","ƽʱ","��ĩ"};
		String values[]={"courseName","task","mid","pacific","final"};
		String sql="select c.name courseName,task,mid,pacific,final from course c,myfunction mf where c.id=mf.courseId and mf.teacherId='"+teacherId+"'";
		list=dao.query(sql, values, key);
		if(!list.isEmpty()&&list.size()>0){
			tableValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();//����������
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("�γ�"+c));
					rowV.add(m.get("��ҵ"+c));
					rowV.add(m.get("����"+c));
					rowV.add(m.get("ƽʱ"+c));
					rowV.add(m.get("��ĩ"+c));
				}
				c++;
				tableValueV.add(rowV);									//����������ӵ���������
			}
		}
		String key2[]={"���","�γ�"};
		String values2[]={"courseId","courseName"};
		sql="select c.id courseId,c.name courseName from course c,team_course tc where c.id=tc.courseId and tc.teacherId='"+teacherId+"'";
		list=dao.query(sql, values2, key2);
		if(!list.isEmpty()&&list.size()>0){
			int c=0;
			for(int row=0;row<list.size();row++){
				for(Map<String,Object> m:list){
					course.addItem(m.get("�γ�"+c).toString());
					courseId.add((String)m.get("���"+c));
				}
				c++;
			}
		}
		String key3[]={"���","�༶"};
		String values3[]={"teamId","teamName"};
		sql="select t.id teamId,t.name teamName from team t,team_course tc where t.id=tc.teamId and tc.teacherId='"+teacherId+"'";
		list=dao.query(sql, values2, key2);
		if(!list.isEmpty()&&list.size()>0){
			int c=0;
			for(int row=0;row<list.size();row++){
				for(Map<String,Object> m:list){
					team.addItem(m.get("�༶"+c).toString());
					teamId.add((String)m.get("���"+c));
				}
				c++;
			}
		}
	}
	
	public void update(){
		String[] str={"id"};
		String teacherId="";
		List<String> teamId=new ArrayList<String>();
		DAO dao=new DAO();
		List<List<Object>> teacherIds=dao.query("select id from teacher where name='"+manager+"'", str);
		if(!teacherIds.isEmpty()&&teacherIds.get(0).get(0)!=null){
			teacherId=(String)teacherIds.get(0).get(0);
		}
		String key[]={"�γ�","��ҵ","����","ƽʱ","��ĩ"};
		String values[]={"courseName","task","mid","pacific","final"};
		String sql="select c.name courseName,task,mid,pacific,final from course c,myfunction mf where c.id=mf.courseId and mf.teacherId='"+teacherId+"'";
		list=dao.query(sql, values, key);
		if(!list.isEmpty()&&list.size()>0){
			tableValueV.clear();
			int c=0;
			for(int row=0;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();//����������
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("�γ�"+c));
					rowV.add(m.get("��ҵ"+c));
					rowV.add(m.get("����"+c));
					rowV.add(m.get("ƽʱ"+c));
					rowV.add(m.get("��ĩ"+c));
				}
				c++;
				tableValueV.add(rowV);									//����������ӵ���������
			}
		}
	}
	private String createId(){
		   DAO dao=new DAO();
		   String[] x={"id"};
		   List<List<Object>> list=dao.query("select Max(id) as id from myfunction;", x);
		   if(!list.isEmpty()&&list.get(0).get(0)!=null){
			   String id=list.get(0).get(0).toString();
			   String subId=id.substring(8);
			   return "function"+String.valueOf(Integer.parseInt(subId)+1);
		   }else{
			   return "function1001";
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
}
