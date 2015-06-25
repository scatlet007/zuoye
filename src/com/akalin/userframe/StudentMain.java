package com.akalin.userframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.akalin.dao.Conn;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class StudentMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4461864905127875923L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JMenuItem output;//������Excl
	private JMenuItem lookCourse;//�鿴�α�
	private JMenuItem logout;//�˳���¼
	private JMenuItem grammer;//����ͼ
	private JMenuItem pie;//����ͼ
	private JMenuItem about;//����
	private JComboBox term;//ѧ��
	private JComboBox termData;//ѧ������
	private List<Map<String, Object>> list;//���ڱ���ɼ����ݵļ���
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain frame = new StudentMain("");
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
	public StudentMain(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,675, 450);
		this.setLocationRelativeTo(null);//��������Ļ�м���ʾ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		JMenu file = new JMenu("�ļ�");
		menuBar.add(file);
		
		/*output = new JMenuItem("����Excel");
		file.add(output);*/
		
		lookCourse=new JMenuItem("�鿴�α�");
		file.add(lookCourse);
		
		logout=new JMenuItem("�˳�");
		file.add(logout);
		
		/*JMenu view = new JMenu("��ͼ");
		menuBar.add(view);
		
		grammer=new JMenuItem("����ͼ");
		view.add(grammer);
		
		pie=new JMenuItem("����ͼ");
		view.add(pie);*/
		
		JMenu help = new JMenu("����");
		menuBar.add(help);
		
		about=new JMenuItem("����");
		help.add(about);
		
		JPanel centerPanel = new JPanel();
		top.add(centerPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		term = new JComboBox();
		term.setMaximumRowCount(10);
		term.addItem("ѧ��");
		term.addItem("ѧ��");
		centerPanel.add(term);
		
		termData = new JComboBox();
		termData.setMaximumRowCount(10);
		termData.addItem("1");
		termData.addItem("2");
		termData.addItem("3");
		termData.addItem("4");
		termData.addItem("5");
		termData.addItem("6");
		centerPanel.add(termData);
		
		JButton search = new JButton("��ѯ");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		centerPanel.add(search);
		
		/*JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] columnValues={"���","�γ�","ѧ��","�γ̷���","���˷�ʽ","�γ�����","�ɼ�","ȡ��ѧ��","����","ѧ�ּ���"};
		String[][] tableValues=new String[20][10];
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++){
				tableValues[i][j]="+";
			}
		}
		tableModel=new DefaultTableModel(tableValues, columnValues);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);*/
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("���");
		columnNameV.add("�γ�");
		columnNameV.add("ѧ��");
		columnNameV.add("�γ̷���");
		columnNameV.add("���˷�ʽ");
		columnNameV.add("�ɼ�");
		columnNameV.add("ȡ��ѧ��");
		columnNameV.add("����");
		columnNameV.add("ȡ�ü���");
		
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=2;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int col=1;col<9;col++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		final MFixedColumnTable mainData=new MFixedColumnTable(columnNameV, tableValueV, 2);
		mainData.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(mainData, BorderLayout.CENTER);		//�������ӵ���������
		//���� end
		
		JPanel footPanel = new JPanel();
		contentPane.add(footPanel, BorderLayout.SOUTH);
		footPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("��õ�ѧ�֣�");
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("����ƽ���ɼ���");
		panel.add(averige);
		
		JPanel bottom = new JPanel();
		//bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		footPanel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("��ӭʹ��");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("�����ߣ�");
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		
		JLabel data = new JLabel("����");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		JLabel time = new JLabel("���ڵ�ʱ�䣺");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		
	}
	//�����¼�����
	public void myEvent(){
		//������Excel(Ԥ��)
		output.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�鿴�α�
		lookCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�˳���¼
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�鿴����ͼ(Ԥ��)
		grammer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�鿴����ͼ(Ԥ��)
		pie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�鿴����
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	//��ѧ�ڲ鿴�ɼ�
	public List<Map<String,Object>> queryByTerm()throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";
			Map<String,Object> map=new HashMap<String, Object>();
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				//list.add(resultSet.getString("teamName"));
				//�򼯺����Ԫ��
				map.put("�γ�", resultSet.getString("course"));
				map.put("ѧ��", resultSet.getString("credit"));
				map.put("�γ̷���", resultSet.getString("type"));
				map.put("���˷�ʽ", resultSet.getString("type2"));
				map.put("�ɼ�", resultSet.getString("grade"));
				map.put("ȡ��ѧ��", resultSet.getString("getCredit"));
				map.put("����", resultSet.getString("dit"));
				map.put("ȡ�ü���", resultSet.getString("getDit"));
				list.add(map);
			}
			resultSet.close();
			conn.close();
		}else{
			Message message=new Message("��������޷����ӵ����ݿ�");
			message.pack();
			conn.close();
		}
		return list;
	}
	//��ѧ��鿴�ɼ�
	
	public List<Object> queryByYear() throws Exception{
		Conn conn=new Conn();
		List<Object> list=new ArrayList<Object>();
		if(conn.getConnection()){
			conn.getState();
			String sql="";
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				//list.add(resultSet.getString("teamName"));
				//�򼯺����Ԫ��
			}
			resultSet.close();
			conn.close();
		}else{
			Message message=new Message("��������޷����ӵ����ݿ�");
			message.pack();
			conn.close();
		}
		return list;
	}
	
	//������ѧ��
	public int count(){
		int sum=0;
		for(Map m:list){
			sum=sum+(int)m.get("ȡ��ѧ��");
		}
		return sum;
	}
	
	//��������ƽ����
	public int averige(){
		int sum=0;
		for(Map m:list){
			if(m.get("ȡ��ѧ��").equals("����"))
				sum=sum+(int)m.get("�ɼ�");
		}
		return sum;
	}
}
