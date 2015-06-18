package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TeacherMain extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		JMenu start = new JMenu("\u5F00\u59CB");
		menuBar.add(start);
		
		JMenuItem output = new JMenuItem("\u5BFC\u51FAExcel");
		start.add(output);
		
		JMenuItem checkCourse = new JMenuItem("\u67E5\u770B\u8BFE\u8868");
		start.add(checkCourse);
		
		JMenuItem logout = new JMenuItem("\u9000\u51FA");
		start.add(logout);
		
		JMenu screen = new JMenu("\u7B5B\u9009");
		menuBar.add(screen);
		
		JMenuItem grade = new JMenuItem("\u6210\u7EE9");
		screen.add(grade);
		
		JMenuItem credit = new JMenuItem("\u5B66\u5206");
		screen.add(credit);
		
		JMenuItem team = new JMenuItem("\u73ED\u7EA7");
		screen.add(team);
		
		JMenu view = new JMenu("\u89C6\u56FE");
		menuBar.add(view);
		
		JMenuItem pie = new JMenuItem("\u997C\u5F62\u56FE");
		view.add(pie);
		
		JMenuItem diagram = new JMenuItem("\u67F1\u5F62\u56FE");
		view.add(diagram);
		
		JMenu function = new JMenu("\u603B\u8BC4\u51FD\u6570");
		menuBar.add(function);
		
		JMenuItem setFuntion = new JMenuItem("\u8BBE\u7F6E");
		function.add(setFuntion);
		
		JMenuItem selectFuntion = new JMenuItem("\u9009\u62E9");
		function.add(selectFuntion);
		
		JMenu help = new JMenu("\u5E2E\u52A9");
		menuBar.add(help);
		
		JMenuItem about = new JMenuItem("\u5173\u4E8E");
		help.add(about);
	}

}
