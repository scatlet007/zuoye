package com.akalin.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.html.ImageView;

import com.akalin.admin.BackPanel;
import com.akalin.admin.Index;
import com.akalin.dao.DAO;
import com.akalin.teacher.TeacherMain;
import com.akalin.tool.Message;
import com.akalin.userframe.StudentMain;

public class Login extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8894157331136913733L;
	private JComboBox<String> role;	//��ɫ�����б��
	private JLabel name;			//�û�����ǩ
	private JLabel pwd;				//�����ǩ
	private	JTextField username;	//�û��������
	private JTextField password;	//���������
	private JButton submit;			//�ύ��ť
	private JRadioButton register;	//ע������
	private JRadioButton forget;	//������������
	private JButton reset;			//����ͼƬ
	private String roleName="��ʦ";
	private BackPanel backPanel;
	//private JPanel contentPane;
	
	public Login(){
		init();
		if(checkRole()==0)
			initRole();
		initTeacher();
	}
	public void init(){
		setResizable(false);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel();	//��������
		contentPane.setLayout(null);//��岼��
		setContentPane(contentPane);
		role=new JComboBox<String>();
		role.addItem("��ʦ");
		role.addItem("����Ա");
		role.setBounds(160, 50, 100, 24);
		contentPane.add(role);
		name=new JLabel("�û���:");
		name.setBounds(100, 94, 60, 24);
		contentPane.add(name);
		username=new JTextField(24);
		username.setBounds(162, 94, 160, 24);
		contentPane.add(username);
		pwd=new JLabel("����:");
		pwd.setBounds(110, 128, 60, 24);
		contentPane.add(pwd);
		password=new JTextField(24);
		password.setBounds(162, 128, 160, 24);
		contentPane.add(password);
		/*register=new JRadioButton("ע��");
		forget=new JRadioButton("��������");
		register.setBounds(162, 162, 70, 24);
		forget.setBounds(234, 162, 90, 24);
		main.add(register);
		main.add(forget);*/
		ButtonGroup group=new ButtonGroup();
		group.add(register);
		group.add(forget);
		//ImageIcon img=new ImageIcon("src/res/icon/loginbtn.png");
		submit=new JButton("�ύ");
		submit.setContentAreaFilled(false);;
		submit.setBounds(162, 162,70,24);
		//submit.setIcon(UIConfig.getImgUrl("���ý�����.png"));
		contentPane.add(submit);
		reset=new JButton("����");
		reset.setBounds(234, 162, 70, 24);
		contentPane.add(reset);
		
		double panelWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
		double panelHeight =Toolkit.getDefaultToolkit().getScreenSize().getHeight()- 25 - 25 - 20;//(����25�������������ڱ������ĸ߶�,20�ǵײ����½������ĸ߶�)  
		backPanel=new BackPanel(panelWidth,panelHeight,"/res/sceen.jpg");
		contentPane.add(backPanel, -1);
		setVisible(true);
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
							setVisible(false);
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
	/** 
     * ��������㴰�ڵ�resize�¼�,�������µĴ��ڴ�С����������ͼƬ�ĳߴ� 
     * @param evt 
     */  
    private void formComponentResized(java.awt.event.ComponentEvent evt) {                                        
        // TODO add your handling code here:  
        try{  
            this.remove(backPanel);  
        }catch(Exception e){  
        }  
        backPanel = null;  
        Dimension newSize = evt.getComponent().getSize();  
        backPanel = new BackPanel(newSize.getWidth(),newSize.getHeight()-70,"/res/sceen.jpg");  
        this.add(backPanel,-1);  
    }  
}
