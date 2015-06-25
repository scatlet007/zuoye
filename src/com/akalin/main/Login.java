package com.akalin.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
import com.akalin.dao.DAO;
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
	private JComboBox<String> role;	//��ɫ�����б��
	private JLabel name;			//�û�����ǩ
	private JLabel pwd;				//�����ǩ
	private	JTextField username;	//�û��������
	private JTextField password;	//���������
	private JButton submit;			//�ύ��ť
	private JRadioButton register;	//ע������
	private JRadioButton forget;	//������������
	private JButton reset;			//����ͼƬ
	private String roleName;
	public Login(){
		init();
		if(checkRole()==0)
			initRole();
		initTeacher();
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
		role.addItem("��ʦ");
		role.addItem("ѧ��");
		role.addItem("����Ա");
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
		password=new JTextField(24);
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
				String pwds=password.getText();
				DAO dao=new DAO();
				String[] str={"id","name","roleId"};
				if(roleName.equals("����Ա")){
					String sql="select * from teacher where name='"+user+"'and password='"+pwds+"';";
					List<List<Object>> list=dao.query(sql, str);
					int array[]={1,2};
					if(!list.isEmpty()){
						for(List<Object> ls:list){
							if(!dao.query("select * from role where id='"+ls.get(0)+"';",array).isEmpty()){
								Index index=new Index(user);
								index.pack();
							}else{
								Message message=new Message("��Ȩ���ʣ�");
								message.pack();
							}
						}
					}else{
						int[] x={1,2,3};
						if(!dao.query("select * from admin where name='"+user+"'and password='"+pwds+"';", x).isEmpty()){
							list=dao.query("select * from admin where name='"+user+"'and password='"+pwds+"';", x);
							for(List<Object> l:list){
								for(Object obj:l){
									System.out.print(obj+"\t");
								}
							}
							System.out.println("www");
							Index index=new Index(user);
							//index.pack();
							jf.setVisible(false);
						}else{
							list=dao.query("select * from admin where name='"+user+"'and password='"+pwds+"';", x);
							for(List<Object> l:list){
								for(Object obj:l){
									System.out.print(obj+"\t");
								}
							}
							Message message=new Message("��Ȩ���ʣ�,��鿴�Ƿ����������");
							message.pack();
						}
					}
				}else if(roleName.equals("��ʦ")){
					String sql="select * from teacher where name='"+user+"'and password='"+pwds+"';";
					List<List<Object>> list=dao.query(sql, str);
					if(!list.isEmpty()){
						TeacherMain teacherMain=new TeacherMain(user);
						teacherMain.pack();
					}else{
						Message message=new Message("��Ȩ���ʣ����ݿ���Ӧ�û�û��������֣�");
						message.pack();
					}
				}else{
					String sql="select * from student where name='"+user+"'and password='"+pwds+"';";
					List<List<Object>> list=dao.query(sql, str);
					if(!list.isEmpty()){
						StudentMain studentMain=new StudentMain(user);
						studentMain.pack();
					}else{
						Message message=new Message("��Ȩ���ʣ����ݿ���Ӧ�û�û��������֣�");
						message.pack();
					}
				}
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
	
	//����ɫ���Ƿ�Ϊ��
	public int checkRole(){
		int[] array={1,2,3};
		DAO dao=new DAO();
		String sql="select * from role;";
		List<List<Object>> list=dao.query(sql, array);
		if(list.isEmpty())
			return 0;
		else
			return 1;
	}
	
	//��ʼ����ɫ��
	public void initRole(){
		DAO dao=new DAO();
		String sql="insert into role values('role1001','administractor','����Ա');";
		dao.add(sql);
	}
	
	//��ʼ��һ������Ա
	public void initTeacher(){
		DAO dao=new DAO();
		String sql="insert into admin values('admin1001','akalin','12345');";
		int array[]={1,2,3};
		if(dao.query("select * from admin;", array).isEmpty()){
			dao.add(sql);
		}
	}
}
