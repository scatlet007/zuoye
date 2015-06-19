package com.akalin.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8894157331136913733L;
	private JFrame jf;				//窗体
	private JComboBox<String> role;			//角色下拉列表框
	private JLabel name;			//用户名标签
	private JLabel pwd;				//密码标签
	private	JTextField username;	//用户名输入框
	private JPasswordField password;//密码输入框
	private JButton submit;			//提交按钮
	private JRadioButton register;		//注册链接
	private JRadioButton forget;		//忘记密码链接
	private JButton reset;			//顶部图片
	
	public Login(){
		init();
	}
	
	/**
	 * 初始化窗体
	 */
	public void init(){
		jf=new JFrame();
		jf.setBounds(600, 300, 450, 300);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel main=new JPanel();	//主框架面板
		main.setLayout(null);//面板布局
		role=new JComboBox<String>();
		role.addItem("teacher");
		role.addItem("student");
		role.setBounds(160, 50, 100, 24);
		main.add(role);
		name=new JLabel("用户名:");
		name.setBounds(100, 94, 60, 24);
		main.add(name);
		username=new JTextField(24);
		username.setBounds(162, 94, 160, 24);
		main.add(username);
		pwd=new JLabel("密码:");
		pwd.setBounds(110, 128, 60, 24);
		main.add(pwd);
		password=new JPasswordField(24);
		password.setBounds(162, 128, 160, 24);
		main.add(password);
		register=new JRadioButton("注册");
		forget=new JRadioButton("忘记密码");
		register.setBounds(162, 162, 70, 24);
		forget.setBounds(234, 162, 90, 24);
		main.add(register);
		main.add(forget);
		ButtonGroup group=new ButtonGroup();
		group.add(register);
		group.add(forget);
		submit=new JButton("登录");
		submit.setBounds(162, 196, 60, 24);
		main.add(submit);
		reset=new JButton("重置");
		reset.setBounds(234, 196, 60, 24);
		main.add(reset);
		jf.add(main);
		jf.setVisible(true);
	}
	
	/**
	 * 窗体事件
	 */
	private void myEvent(){
		role.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 switch (e.getStateChange())
                 {
                 case ItemEvent.SELECTED: 
                     System.out.println("选中" + e.getItem());
                     break;
                /* case ItemEvent.DESELECTED:
                     System.out.println("取消选中" + e.getItem());
                     break;*/
                 }
			}
			
		});
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("username->"+username.getText());
			}
			
		});
	}
	public static void main(String[] args){
		Login login=new Login();
		login.myEvent();
	}
}
