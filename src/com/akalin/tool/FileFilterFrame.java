package com.akalin.tool;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class FileFilterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String path;
	private boolean flag=true;
	private JButton btnNewButton;
	private boolean start=true;
	public String getPath() {
		return path;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileFilterFrame frame = new FileFilterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public FileFilterFrame() {
		init();
		myEvent();
	}
	
	public void init(){
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 473, 80);
		contentPane = new JPanel();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6587\u4EF6\u8DEF\u5F84\uFF1A");
		lblNewLabel.setBounds(51, 13, 75, 16);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(131, 10, 166, 22);
		textField.setFont(new Font("宋体", Font.BOLD, 14));
		contentPane.add(textField);
		textField.setColumns(20);
		
		btnNewButton = new JButton("\u9009\u62E9\u6587\u4EF6");
		btnNewButton.setBounds(302, 10, 81, 23);
		contentPane.add(btnNewButton);
		setVisible(true);
	}
	public void myEvent(){
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(flag){
					button(arg0);
					flag=false;
				}else{
					start=false;
					setVisible(false);
				}
			}
		});
	}
	protected void button(ActionEvent e){
		JFileChooser chooser=new JFileChooser();
		FileFilter filter=new FileNameExtensionFilter("文本类型(.xls)","xls");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		int result=chooser.showOpenDialog(this);
		if(result==JFileChooser.APPROVE_OPTION){
			File file=chooser.getSelectedFile();
			textField.setText(file.getAbsolutePath());
			this.path=file.getAbsolutePath();
		}
	}
}
