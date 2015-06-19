package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CourseFrame extends JFrame {

	private JPanel contentPane;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 874, 33);
		contentPane.add(panel);
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
	}

}
