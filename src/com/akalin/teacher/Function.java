package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;

import com.akalin.dao.Conn;
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
	private JTextField course;//�γ�
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
	private final MFixedColumnTable mainData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Function frame = new Function();
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
	public Function() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
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
						
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<10;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(Map<String,Object> m:list){
				rowV.add(m.get("�γ�"));
				rowV.add(m.get("��ҵ"));
				rowV.add(m.get("����"));
				rowV.add(m.get("ƽʱ"));
				rowV.add(m.get("��ĩ"));
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
		course=new JTextField(10);
		homework=new JTextField(5);
		middy=new JTextField(5);
		normal=new JTextField(5);
		end=new JTextField(5);
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
		
		foot = new JPanel();
		contentPane.add(foot, BorderLayout.SOUTH);
		foot.setLayout(new GridLayout(1,6));
		JLabel welcome = new JLabel("��ӭʹ��");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(welcome);
		
		JLabel user = new JLabel("�����ߣ�");
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(user);
		
		JLabel data = new JLabel("����");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(data);
		
		JLabel time = new JLabel("���ڵ�ʱ�䣺");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(time);
	}
	public void myEvent(){
		//����ĩ�������ӻس��¼�
		end.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					h=Integer.parseInt(homework.getText());
					m=Integer.parseInt(middy.getText());
					n=Integer.parseInt(normal.getText());
					t=Integer.parseInt(end.getText());
				}
			}
		});
	}
	
	//��������
	public void save(int arg0,int arg1,int arg2,int arg3) throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";//����д�����sql���
			conn.getStatement().executeQuery(sql);
			conn.close();
		}else{
			Message message=new Message("��������޷����ӵ����ݿ�");
			message.pack();
			conn.close();
		}
	}
	//���±���е�����
	public List<Map<String,Object>> update() throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";//����д��ѯ��sql���
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				Map<String,Object> m=new HashMap<String, Object>();
				m.put("�γ�", resultSet.getString("course"));
				m.put("��ҵ", resultSet.getString("task"));
				m.put("����", resultSet.getString("mid"));
				m.put("ƽʱ", resultSet.getString("pacific"));
				m.put("��ĩ", resultSet.getString("final"));
				list.add(m);
			}
			conn.close();
		}else{
			Message message=new Message("��������޷����ӵ����ݿ�");
			message.pack();
			conn.close();
		}
		return list;
	}
	//�޸�һ������
	public void modify(int arg0) throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";//����д�޸ĵ�sql���
			conn.getStatement().executeUpdate(sql);
			conn.close();
		}else{
			Message message=new Message("��������޷����ӵ����ݿ�");
			message.pack();
			conn.close();
		}
	}
	//ѡ��ĳһ�б������ݣ���������ʾ����д��
	private class MyListener implements ListSelectionListener{
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(flag!=mainData.getSelectRow()){
				List<Vector<Object>> list=mainData.getList();
				for(Vector<Object> vec:list){
					course.setText((String)vec.get(1));
					homework.setText((String)vec.get(2));
					middy.setText((String)vec.get(3));
					normal.setText((String)vec.get(4));
					end.setText((String)vec.get(5));
				}
			}
		}
		
	}
}
