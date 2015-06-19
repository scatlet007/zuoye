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
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;

public class StudentMain extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain frame = new StudentMain();
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
	public StudentMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,650, 400);
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
		
		JMenuItem output = new JMenuItem("����Excel");
		file.add(output);
		
		JMenuItem lookCourse=new JMenuItem("�鿴�α�");
		file.add(lookCourse);
		
		JMenuItem logout=new JMenuItem("�˳�");
		file.add(logout);
		
		JMenu view = new JMenu("��ͼ");
		menuBar.add(view);
		
		JMenuItem grammer=new JMenuItem("����ͼ");
		view.add(grammer);
		
		JMenuItem pie=new JMenuItem("����ͼ");
		view.add(pie);
		
		JMenu help = new JMenu("����");
		menuBar.add(help);
		
		JMenuItem about=new JMenuItem("����");
		help.add(about);
		
		JPanel centerPanel = new JPanel();
		top.add(centerPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JComboBox term = new JComboBox();
		term.setMaximumRowCount(10);
		term.addItem("ѧ��");
		term.addItem("ѧ��");
		centerPanel.add(term);
		
		JComboBox termData = new JComboBox();
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
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] columnValues={"���","�γ�","ѧ��","�γ̷���","���˷�ʽ","�γ�����","�ɼ�","ȡ��ѧ��","����","ѧ�ּ���"};
		String[][] tableValues={{"1","2","3","4","5","6","7","8","9","10"},{"1","2","3","4","5","6","7","8","9","10"}};
		table = new JTable(tableValues,columnValues);
		table.setCellSelectionEnabled(true);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JPanel footPanel = new JPanel();
		contentPane.add(footPanel, BorderLayout.SOUTH);
		footPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("    ");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("Ŀǰ��õ�ѧ�֣�");
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("����ƽ���ɼ���");
		panel.add(averige);
		
		JLabel limitCredit = new JLabel("ʣ��ѧ�֣�");
		panel.add(limitCredit);
		
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

}
