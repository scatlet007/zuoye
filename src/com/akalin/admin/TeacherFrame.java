package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherFrame extends JFrame {

	private JPanel contentPane;
	private JTextField teacher;
	private JTextField age;
	private JTextField phone;
	private JTable table;
	private JButton submit;
	private JButton modify;
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
					TeacherFrame frame = new TeacherFrame();
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
	public TeacherFrame(String username) {
		manager=username;
		init();
	}
	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
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
		
		JLabel name = new JLabel("\u6559\u5E08\u540D\uFF1A");
		name.setFont(new Font("宋体", Font.PLAIN, 14));
		name.setBounds(10, 58, 56, 15);
		contentPane.add(name);
		
		teacher = new JTextField();
		teacher.setBounds(69, 55, 122, 21);
		contentPane.add(teacher);
		teacher.setColumns(10);
		
		JLabel college = new JLabel("\u5B66\u9662\uFF1A");
		college.setFont(new Font("宋体", Font.PLAIN, 14));
		college.setBounds(234, 61, 54, 15);
		contentPane.add(college);
		
		JComboBox collegeName = new JComboBox();
		collegeName.setModel(new DefaultComboBoxModel(new String[] {"信工学院","软件学院","文学院"}));
		collegeName.setBounds(280, 55, 152, 21);
		contentPane.add(collegeName);
		
		JLabel sex1 = new JLabel("\u6027\u522B\uFF1A");
		sex1.setFont(new Font("宋体", Font.PLAIN, 14));
		sex1.setBounds(471, 61, 54, 15);
		contentPane.add(sex1);
		
		JComboBox sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
		sex.setBounds(516, 55, 64, 21);
		contentPane.add(sex);
		
		JLabel age1 = new JLabel("\u5E74\u9F84\uFF1A");
		age1.setFont(new Font("宋体", Font.PLAIN, 14));
		age1.setBounds(615, 58, 54, 15);
		contentPane.add(age1);
		
		age = new JTextField();
		age.setBounds(661, 55, 66, 21);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel telephone1 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		telephone1.setFont(new Font("宋体", Font.PLAIN, 14));
		telephone1.setBounds(12, 108, 70, 15);
		contentPane.add(telephone1);
		
		phone = new JTextField();
		phone.setBounds(80, 105, 131, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel position1 = new JLabel("\u804C\u4F4D\uFF1A");
		position1.setFont(new Font("宋体", Font.PLAIN, 14));
		position1.setBounds(234, 108, 54, 15);
		contentPane.add(position1);
		
		JComboBox position = new JComboBox();
		position.setBounds(280, 105, 152, 21);
		
		contentPane.add(position);
		
		JLabel role1 = new JLabel("\u89D2\u8272\uFF1A");
		role1.setFont(new Font("宋体", Font.PLAIN, 14));
		role1.setBounds(471, 108, 54, 15);
		contentPane.add(role1);
		
		JComboBox role = new JComboBox();
		role.setBounds(516, 105, 116, 21);
		role.addItem("教授");
		role.addItem("讲师");
		contentPane.add(role);
		
		submit = new JButton("提交");
		submit.setBounds(655, 104, 93, 23);
		contentPane.add(submit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 143, 864, 383);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"序号", "教师名", "性别", "年龄", "手机号码", "职位", "学院", "角色"
			}
		));
		table.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 536, 884, 26);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("\u6B22\u8FCE\u5149\u4E34");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(welcome);
		
		JLabel ctrl = new JLabel("使用者：");
		ctrl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(ctrl);
		
		JLabel date = new JLabel("日期：");
		date.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(date);
		
		JLabel time = new JLabel("时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_1.add(time);
		setVisible(true);
	}
	public void myEvent(){
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
