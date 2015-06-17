package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel main = new JPanel();
		main.setBounds(0, 0, 434, 252);
		contentPane.add(main);
		main.setLayout(null);
		
		JLabel lblUsername = new JLabel("username:");
		lblUsername.setFont(new Font("ËÎÌו", Font.PLAIN, 14));
		lblUsername.setBounds(85, 92, 69, 23);
		main.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(164, 85, 184, 30);
		main.add(textField);
		textField.setColumns(20);
		
		JLabel lblNewLabel = new JLabel("password:");
		lblNewLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 14));
		lblNewLabel.setBounds(86, 144, 68, 15);
		main.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(30);
		passwordField.setBounds(164, 135, 184, 30);
		main.add(passwordField);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(164, 189, 77, 23);
		main.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("reset");
		btnNewButton_1.setBounds(255, 189, 77, 23);
		main.add(btnNewButton_1);
	}
}
