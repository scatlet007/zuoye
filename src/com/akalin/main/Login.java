package com.akalin.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.akalin.admin.Index;
import com.akalin.dao.Conn;
import com.akalin.teacher.TeacherMain;
import com.akalin.tool.Message;
import com.akalin.userframe.StudentMain;
import com.akalin.userframe.StudentRegister;

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
	private String roleName;
	public Login(){
		init();
	}
	
	/**
	 * ��ʼ������
	 */
	public void init(){
		jf=new JFrame();
		jf.setBounds(100, 100, 450, 300);
		jf.setLocationRelativeTo(null);
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
		//�����б��¼�
		role.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 switch (e.getStateChange())
                 {
                 case ItemEvent.SELECTED: 
                     //���ڴ����ѡ���������ݺ�Ĵ���
                	 //System.out.println("ѡ��" + e.getItem());
                	 roleName=(String)e.getItem();
                     break;
                 }
			}
			
		});
		
		//�����¼��ť�¼�
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//���ڴ���ӵ���˵�¼��ť��Ĵ���
				String user=username.getText();
				String pwds=password.getSelectedText();
				
				String driver="com.mysql.jdbc.Driver";		//���ݿ�ӿ�����
				String url="jdbc:mysql://127.0.0.1/db_akalin";			//���ݿ����ӵ�ַ
				String db_user="root";		//���ݿ������û���
				String db_password="12345";	//���ݿ���������
				Conn conn=new Conn();
				if(conn.getConnection(driver, url, db_user, db_password)){
					if(conn.getState()){
						String sql="";//����д���ݿ��sql���
						try {
							ResultSet resultSet=conn.getStatement().executeQuery(sql);
							if(null!=resultSet){
								if(roleName.equals("administractor")){
									Index index=new Index();
									index.pack();
								}
								if(roleName.equals("��ʦ")){
									TeacherMain teacherMain=new TeacherMain();
									teacherMain.pack();
								}
								if(roleName.equals("ѧ��")){
									StudentMain studentMain=new StudentMain(username.getText());
									studentMain.pack();
								}
							}else{
								Message message=new Message("���û������ڣ���ȷ���û����������Ƿ���ȷ��");
								message.pack();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
							Message message=new Message("�޷������ݿ�ȡ�����ݣ�");
							message.pack();
						}
					}
				}
				/*Message message=new Message("���û������ڣ���ȷ���û����������Ƿ���ȷ��");
				message.pack();*/
			}
			
		});
		
		//�����ע��ѡ���¼�
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���ڴ���ӵ����ע���Ĵ���
				StudentRegister register=new StudentRegister();
				register.pack();
			}
		});
		
		//����������������¼�
		forget.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���ڴ���ӵ�������������Ĵ���
			}
		});
	}
	public static void main(String[] args){
		Login login=new Login();
		login.myEvent();
	}
	
}
