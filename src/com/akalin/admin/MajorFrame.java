package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class MajorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField majorName;
	private JTextField creates;
	private JTextField details;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MajorFrame frame = new MajorFrame();
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
	public MajorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("专业管理");
		setBounds(100, 100, 675, 450);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		JMenu collegeManaer = new JMenu("学院管理");
		menuBar.add(collegeManaer);
		
		JMenu majorManager = new JMenu("专业管理");
		menuBar.add(majorManager);
		
		JMenu teamManager = new JMenu("班级管理");
		menuBar.add(teamManager);
		
		JMenu teacherManager = new JMenu("教师管理");
		menuBar.add(teacherManager);
		
		JMenu studentManager = new JMenu("学生管理");
		menuBar.add(studentManager);
		
		JMenu courseManager = new JMenu("课程管理");
		menuBar.add(courseManager);
		
		JMenu roleManager = new JMenu("角色管理");
		menuBar.add(roleManager);
		
		/*//添加背景
		BackPanel backPanel=new BackPanel(675,350);
		contentPane.add(backPanel,BorderLayout.CENTER);*/
		String[] columnValues={"序号","专业名","创立时间","简单描述"};
		String[][] tableValues=new String[30][4];
		for(int i=0;i<30;i++){
			for(int j=0;j<4;j++){
				tableValues[i][j]="+";
			}
		}
		JScrollPane scrollPane=new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		DefaultTableModel tableModel=new DefaultTableModel(tableValues, columnValues);
		JTable table=new JTable(tableModel);
		table.setBorder(new EmptyBorder(20, 20, 20, 20));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel bottom = new JPanel();
		panel.add(bottom);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("欢迎使用");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("操作者：");
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		
		JLabel data = new JLabel("日期");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		JLabel time = new JLabel("现在的时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, WIDTH, 100);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel name = new JLabel("专业名");
		panel_1.add(name);
		
		majorName = new JTextField();
		panel_1.add(majorName);
		majorName.setColumns(10);
		
		JLabel createTime = new JLabel("创立时间");
		panel_1.add(createTime);
		
		creates = new JTextField();
		panel_1.add(creates);
		creates.setColumns(20);
		
		JLabel status = new JLabel("简单描述:");
		panel_1.add(status);
		
		details = new JTextField();
		panel_1.add(details);
		details.setColumns(80);
		
		JButton submit = new JButton("提交");
		panel_1.add(submit);
	}

}
