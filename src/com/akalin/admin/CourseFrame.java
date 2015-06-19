package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;

public class CourseFrame extends JFrame {

	private JPanel contentPanel;
	private JTextField course;
	private JTextField credit;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseFrame frame = new CourseFrame();
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
	public CourseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		this.setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 874, 33);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
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
		
		JLabel course1 = new JLabel("课程名：");
		course1.setFont(new Font("宋体", Font.PLAIN, 14));
		course1.setBounds(22, 55, 68, 15);
		contentPanel.add(course1);
		
		course = new JTextField();
		course.setBounds(85, 52, 131, 21);
		contentPanel.add(course);
		course.setColumns(20);
		
		JLabel credit1 = new JLabel("学分：");
		credit1.setBounds(265, 55, 54, 15);
		contentPanel.add(credit1);
		
		credit = new JTextField();
		credit.setBounds(309, 52, 77, 21);
		contentPanel.add(credit);
		credit.setColumns(10);
		
		JLabel type1 = new JLabel("课程分类：");
		type1.setFont(new Font("宋体", Font.PLAIN, 14));
		type1.setBounds(428, 55, 77, 15);
		contentPanel.add(type1);
		
		JComboBox type = new JComboBox();
		type.setBounds(515, 52, 110, 21);
		contentPanel.add(type);
		
		JLabel type01 = new JLabel("考核方式：");
		type01.setFont(new Font("宋体", Font.PLAIN, 14));
		type01.setBounds(661, 58, 77, 15);
		contentPanel.add(type01);
		
		JComboBox type2 = new JComboBox();
		type2.setBounds(736, 52, 99, 21);
		contentPanel.add(type2);
		
		JLabel major1 = new JLabel("专业：");
		major1.setFont(new Font("宋体", Font.PLAIN, 14));
		major1.setBounds(38, 104, 54, 15);
		contentPanel.add(major1);
		
		JComboBox major = new JComboBox();
		major.setBounds(110, 101, 144, 21);
		contentPanel.add(major);
		
		JButton submit = new JButton("提交");
		submit.setBounds(294, 100, 93, 23);
		contentPanel.add(submit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 138, 864, 379);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"序号", "专业名", "课程名", "N课程分类", "考核方式", "学分", "描述"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel_2.setBounds(0, 537, 884, 25);
		contentPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1,4));
		
		JLabel welcome = new JLabel("欢迎光临");
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
		
		JButton modify = new JButton("修改");
		modify.setBounds(557, 104, 93, 23);
		contentPanel.add(modify);
		
		JButton delete = new JButton("删除");
		delete.setBounds(700, 105, 93, 23);
		contentPanel.add(delete);
	}

}
