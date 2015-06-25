package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class StudentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField student;
	private JTextField age;
	private JTextField phone;
	private JButton submit;
	private JButton modify;
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
					StudentFrame frame = new StudentFrame();
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
	public StudentFrame(String username) {
		manager=username;
		init();
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,900, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 874, 33);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
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
		
		JLabel name = new JLabel("学生名：");
		name.setFont(new Font("宋体", Font.PLAIN, 14));
		name.setBounds(10, 58, 56, 15);
		contentPane.add(name);
		
		student = new JTextField();
		student.setBounds(69, 55, 122, 21);
		contentPane.add(student);
		student.setColumns(10);
		
		JLabel college = new JLabel("班级名：");
		college.setFont(new Font("宋体", Font.PLAIN, 14));
		college.setBounds(218, 58, 69, 15);
		contentPane.add(college);
		
		JComboBox team = new JComboBox();
		team.setModel(new DefaultComboBoxModel(new String[] {"信工学院","软件学院","文学院"}));
		team.setBounds(297, 55, 152, 21);
		contentPane.add(team);
		
		JLabel sex1 = new JLabel("性别");
		sex1.setFont(new Font("宋体", Font.PLAIN, 14));
		sex1.setBounds(471, 61, 54, 15);
		contentPane.add(sex1);
		
		JComboBox sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
		sex.setBounds(516, 55, 64, 21);
		contentPane.add(sex);
		
		JLabel age1 = new JLabel("年龄：");
		age1.setFont(new Font("宋体", Font.PLAIN, 14));
		age1.setBounds(615, 58, 54, 15);
		contentPane.add(age1);
		
		age = new JTextField();
		age.setBounds(661, 55, 66, 21);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel role1 = new JLabel("角色名：");
		role1.setFont(new Font("宋体", Font.PLAIN, 14));
		role1.setBounds(12, 108, 66, 15);
		contentPane.add(role1);
		
		JComboBox role = new JComboBox();
		role.setBounds(88, 105, 116, 21);
		role.addItem("aa");
		role.addItem("bb");
		contentPane.add(role);
		
		submit = new JButton("提交");
		submit.setBounds(280, 104, 93, 23);
		contentPane.add(submit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 143, 864, 369);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"序号", "学生名", "性别", "年龄", "手机号码", "职位", "班级", "角色"
			}
		));
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 536, 874, 26);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("\u6B22\u8FCE\u5149\u4E34");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(welcome);
		
		JLabel ctrl = new JLabel("使用者："+manager);
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(ctrl);
		
		JLabel date = new JLabel("日期：");
		date.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(date);
		
		JLabel time = new JLabel("时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(time);
	}
	
	public void myEvent(){
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}

}
