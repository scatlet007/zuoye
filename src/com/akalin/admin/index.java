package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.GridLayout;

public class index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8810450090270436228L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					index frame = new index();
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
	public index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,675, 450);
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
		
		//��ӱ���
		BackPanel backPanel=new BackPanel(675,350);
		contentPane.add(backPanel,BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
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
		contentPane.add(bottom, BorderLayout.SOUTH);
	}

}
