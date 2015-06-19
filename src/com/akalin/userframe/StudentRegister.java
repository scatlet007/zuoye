package com.akalin.userframe;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class StudentRegister extends JFrame {
	
	private static final long serialVersionUID = -7820325993852874718L;
	
	private JComboBox<String> team;
	private JLabel name;
	private JLabel pwd;
	private JLabel sno;
	private JTextField username;
	private JPasswordField password;
	private JTextField snumber;
	private JRadioButton man;
	private JRadioButton girl;
	private JButton submit;
	private JButton reset;

	public StudentRegister(){
		init();
		System.out.println("jjjjjj");
	}
	
	public void init(){
		JFrame jf=new JFrame("register");
		jf.setBounds(600, 300, 450, 300);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel main=new JPanel();
		main.setLayout(null);
		team=new JComboBox<String>();
		team.addItem("1");
		team.addItem("2");
		team.addItem("3");
		team.setBounds(160, 50, 100, 24);
		main.add(team);
		name=new JLabel("用户名:");
		name.setBounds(100, 84, 60, 24);
		main.add(name);
		username=new JTextField();
		username.setBounds(162, 84, 160, 24);
		main.add(username);
		pwd=new JLabel("密码:");
		pwd.setBounds(110, 120, 60, 24);
		main.add(pwd);
		password=new JPasswordField();
		password.setBounds(162, 120, 160, 24);
		main.add(password);
		sno=new JLabel("学号:");
		sno.setBounds(110, 154, 60, 24);
		main.add(sno);
		snumber=new JTextField();
		snumber.setBounds(162, 154, 160, 24);
		main.add(snumber);
		man=new JRadioButton("男");
		girl=new JRadioButton("女");
		man.setBounds(162, 178, 70, 24);
		girl.setBounds(234, 178, 70, 24);
		main.add(man);
		main.add(girl);
		ButtonGroup group=new ButtonGroup();
		group.add(man);
		group.add(girl);
		submit=new JButton("提交");
		submit.setBounds(162, 212, 60, 24);
		main.add(submit);
		reset=new JButton("重置");
		reset.setBounds(230, 212, 60, 24);
		main.add(reset);
		jf.add(main);
		jf.setVisible(true);
	}
	
	public void myEvent(){
		
	}
	public static void main(String[] args) {
		StudentRegister s=new StudentRegister();
	}

}
