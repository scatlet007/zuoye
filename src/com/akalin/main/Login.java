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
	private JFrame jf;				//����
	private JComboBox<String> role;			//��ɫ�����б��
	private JLabel name;			//�û�����ǩ
	private JLabel pwd;				//�����ǩ
	private	JTextField username;	//�û��������
	private JPasswordField password;//���������
	private JButton submit;			//�ύ��ť
	private JRadioButton register;		//ע������
	private JRadioButton forget;		//������������
	private JButton reset;			//����ͼƬ
	
	public Login(){
		init();
	}
	
	/**
	 * ��ʼ������
	 */
	public void init(){
		jf=new JFrame();
		jf.setBounds(600, 300, 450, 300);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel main=new JPanel();	//��������
		main.setLayout(null);//��岼��
		role=new JComboBox<String>();
		role.addItem("teacher");
		role.addItem("student");
		role.setBounds(160, 50, 100, 24);
		main.add(role);
		name=new JLabel("�û���:");
		name.setBounds(100, 94, 60, 24);
		main.add(name);
		username=new JTextField(24);
		username.setBounds(162, 94, 160, 24);
		main.add(username);
		pwd=new JLabel("����:");
		pwd.setBounds(110, 128, 60, 24);
		main.add(pwd);
		password=new JPasswordField(24);
		password.setBounds(162, 128, 160, 24);
		main.add(password);
		register=new JRadioButton("ע��");
		forget=new JRadioButton("��������");
		register.setBounds(162, 162, 70, 24);
		forget.setBounds(234, 162, 90, 24);
		main.add(register);
		main.add(forget);
		ButtonGroup group=new ButtonGroup();
		group.add(register);
		group.add(forget);
		submit=new JButton("��¼");
		submit.setBounds(162, 196, 60, 24);
		main.add(submit);
		reset=new JButton("����");
		reset.setBounds(234, 196, 60, 24);
		main.add(reset);
		jf.add(main);
		jf.setVisible(true);
	}
	
	/**
	 * �����¼�
	 */
	private void myEvent(){
		role.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 switch (e.getStateChange())
                 {
                 case ItemEvent.SELECTED: 
                     System.out.println("ѡ��" + e.getItem());
                     break;
                /* case ItemEvent.DESELECTED:
                     System.out.println("ȡ��ѡ��" + e.getItem());
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
