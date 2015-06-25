package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TeamFrame extends JFrame {

	private JPanel contentPanel;
	private JTextField teamName;
	private JTextField creates;
	private JTextField name;
	private JTextField createTime;
	private JTable table;
	private JMenu collegeManaer;
	private JMenu majorManager;
	private JMenu teamManager;
	private JMenu teacherManager;
	private JMenu studentManager;
	private JMenu courseManager;
	private JMenu roleManager;
	private String manager;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamFrame frame = new TeamFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public TeamFrame(String username) {
		manager=username;
		init();
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		this.setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel top = new JPanel();
		top.setBounds(5, 5, 874, 21);
		contentPanel.add(top);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0 , 0, this.getWidth(), 40);
		top.add(menuBar, BorderLayout.NORTH);
		
		collegeManaer = new JMenu("学院管理");
		menuBar.add(collegeManaer);
		
		majorManager = new JMenu("专业管理");
		menuBar.add(majorManager);
		
		teamManager = new JMenu("班级管理");
		menuBar.add(teamManager);
		
		teacherManager = new JMenu("教师管理");
		menuBar.add(teacherManager);
		
		studentManager = new JMenu("学生管理");
		menuBar.add(studentManager);
		
		courseManager = new JMenu("课程管理");
		menuBar.add(courseManager);
		
		roleManager = new JMenu("角色管理");
		menuBar.add(roleManager);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 859, 162);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel major = new JLabel("专业:");
		major.setFont(new Font("宋体", Font.PLAIN, 14));
		major.setBounds(53, 31, 54, 15);
		panel.add(major);
		
		JComboBox majorName = new JComboBox();
		majorName.addItem("计算机");
		majorName.addItem("软件");
		majorName.setBounds(114, 28, 135, 21);
		panel.add(majorName);
		
		JLabel team = new JLabel("\u73ED\u7EA7\u540D\uFF1A");
		team.setBounds(316, 31, 54, 15);
		panel.add(team);
		
		name = new JTextField();
		name.setBounds(392, 28, 165, 21);
		panel.add(name);
		name.setColumns(10);
		
		JLabel create = new JLabel("\u521B\u5EFA\u65F6\u95F4");
		create.setBounds(53, 83, 54, 15);
		panel.add(create);
		
		createTime = new JTextField();
		createTime.setBounds(114, 80, 135, 21);
		panel.add(createTime);
		createTime.setColumns(10);
		
		JLabel status = new JLabel("\u63CF\u8FF0\uFF1A");
		status.setBounds(316, 86, 54, 15);
		panel.add(status);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 59, 270, 93);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(40);
		textArea.setRows(50);
		scrollPane.setViewportView(textArea);
		
		JButton submit = new JButton("\u63D0\u4EA4");
		submit.setBounds(701, 109, 93, 23);
		panel.add(submit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 208, 864, 315);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 10, 844, 290);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		String[][] str=new String[21][4];
		for(int i=0;i<21;i++){
			for(int j=0;j<4;j++){
				str[i][j]="+";
			}
		}
		table.setModel(new DefaultTableModel(
			str,
			new String[] {
				"序号", "专业", "班级名", "创建时间","描述"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(5, 526, 874, 36);
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("\u6B22\u8FCE\u5149\u4E34");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(welcome);
		
		JLabel ctrl = new JLabel("使用者：");
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(ctrl);
		
		JLabel date = new JLabel("日期：");
		date.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(date);
		
		JLabel time = new JLabel("时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.add(time);
		setVisible(true);
	}
	public void myEvent(){
		
	}
	
}
