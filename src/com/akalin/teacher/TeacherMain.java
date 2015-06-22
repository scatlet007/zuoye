package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMain frame = new TeacherMain();
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
	public TeacherMain() {
		setTitle("��ʦ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 540);
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
		
		output = new JMenuItem("����Excel");
		start.add(output);
		
		checkCourse = new JMenuItem("�鿴�α�");
		start.add(checkCourse);
		
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
		
		JMenu view = new JMenu("��ͼ");
		menuBar.add(view);
		
		pie = new JMenuItem("����ͼ");
		view.add(pie);
		
		diagram = new JMenuItem("����ͼ");
		view.add(diagram);
		
		JMenu function = new JMenu("����");
		menuBar.add(function);
		
		setFuntion = new JMenuItem("�Զ��庯��");
		function.add(setFuntion);
		
		selectFuntion = new JMenuItem("ѡ����");
		function.add(selectFuntion);
		
		JMenu help = new JMenu("����");
		menuBar.add(help);
		
		about = new JMenuItem("����");
		help.add(about);
		
		JPanel topCenter=new JPanel();//��Ÿ���ʽ���
		contentPane.add(topCenter, BorderLayout.CENTER);
		topCenter.setLayout(new BorderLayout(0, 0));
		
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
				
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int col=0;col<9;col++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		final MFixedColumnTable mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(mainData, BorderLayout.CENTER);		//�������ӵ���������
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
	
	public void myEvent(){
		//����excel
		output.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�鿴�α�
		checkCourse.addActionListener(new ActionListener() {
			
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
		//ɸѡ��ʽΪ�ɼ�
		btn_grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//ɸѡ��ʽΪѧ��
		credit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//ɸѡ��ʽΪ�༶
		team.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//����ͼ
		pie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//����ͼ
		diagram.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//�Զ��庯������
		setFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//ѡ����
		selectFuntion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//����˹��ڰ�ť
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
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

}
