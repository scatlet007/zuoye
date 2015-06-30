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
	private JComboBox<String> role;	//角色下拉列表框
	private JLabel name;			//用户名标签
	private JLabel pwd;				//密码标签
	private	JTextField username;	//用户名输入框
	private JTextField password;	//密码输入框
	private JButton submit;			//提交按钮
	private JRadioButton register;	//注册链接
	private JRadioButton forget;	//忘记密码链接
	private JButton reset;			//顶部图片
	private String roleName="教师";
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
		JPanel contentPane = new JPanel();	//主框架面板
		contentPane.setLayout(null);//面板布局
		setContentPane(contentPane);
		role=new JComboBox<String>();
		role.addItem("教师");
		role.addItem("管理员");
		role.setBounds(160, 50, 100, 24);
		contentPane.add(role);
		name=new JLabel("用户名:");
		name.setBounds(100, 94, 60, 24);
		contentPane.add(name);
		username=new JTextField(24);
		username.setBounds(162, 94, 160, 24);
		contentPane.add(username);
		pwd=new JLabel("密码:");
		pwd.setBounds(110, 128, 60, 24);
		contentPane.add(pwd);
		password=new JTextField(24);
		password.setBounds(162, 128, 160, 24);
		contentPane.add(password);
		/*register=new JRadioButton("注册");
		forget=new JRadioButton("忘记密码");
		register.setBounds(162, 162, 70, 24);
		forget.setBounds(234, 162, 90, 24);
		main.add(register);
		main.add(forget);*/
		ButtonGroup group=new ButtonGroup();
		group.add(register);
		group.add(forget);
		//ImageIcon img=new ImageIcon("src/res/icon/loginbtn.png");
		submit=new JButton("提交");
		submit.setContentAreaFilled(false);;
		submit.setBounds(162, 162,70,24);
		//submit.setIcon(UIConfig.getImgUrl("常用交易字.png"));
		contentPane.add(submit);
		reset=new JButton("重置");
		reset.setBounds(234, 162, 70, 24);
		contentPane.add(reset);
		
		double panelWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();  
		double panelHeight =Toolkit.getDefaultToolkit().getScreenSize().getHeight()- 25 - 25 - 20;//(两个25是内外两个窗口标题栏的高度,20是底部更新进度栏的高度)  
		backPanel=new BackPanel(panelWidth,panelHeight,"/res/sceen.jpg");
		contentPane.add(backPanel, -1);
		setVisible(true);
	}
	
	/**
	 * 窗体事件
	 */
	private void myEvent(){
		//下拉列表事件
		role.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				
				 switch (e.getStateChange())
                 {
                 case ItemEvent.SELECTED: 
                     //请在此添加选择下拉内容后的代码
                	 //System.out.println("选中" + e.getItem());
                	 roleName=(String)e.getItem();
                     break;
                 }
			}
			
		});
		
		//点击登录按钮事件
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//请在此添加点击了登录按钮后的代码
				String user=username.getText();
				String pwds=password.getText();
				DAO dao=new DAO();
				String[] str={"id","name","roleId"};
				if(roleName.equals("管理员")){
					String sql="select * from teacher where name='"+user+"'and password='"+pwds+"';";
					List<List<Object>> list=dao.query(sql, str);
					int array[]={1,2};
					if(!list.isEmpty()){
						for(List<Object> ls:list){
							if(!dao.query("select * from role where id='"+ls.get(0)+"';",array).isEmpty()){
								Index index=new Index(user);
								index.pack();
							}else{
								Message message=new Message("无权访问！");
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
							Message message=new Message("无权访问！,请查看是否有输入错误！");
							message.pack();
						}
					}
				}else if(roleName.equals("教师")){
					String sql="select * from teacher where name='"+user+"'and password='"+pwds+"';";
					List<List<Object>> list=dao.query(sql, str);
					if(!list.isEmpty()){
						TeacherMain teacherMain=new TeacherMain(user);
						teacherMain.pack();
					}else{
						Message message=new Message("无权访问！数据库里应该还没有你的名字！");
						message.pack();
					}
				}else{
					String sql="select * from student where name='"+user+"'and password='"+pwds+"';";
					List<List<Object>> list=dao.query(sql, str);
					if(!list.isEmpty()){
						StudentMain studentMain=new StudentMain(user);
						studentMain.pack();
					}else{
						Message message=new Message("无权访问！数据库里应该还没有你的名字！");
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
	
	//检查角色表是否为空
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
	
	//初始化角色表
	public void initRole(){
		DAO dao=new DAO();
		String sql="insert into role values('role1001','administractor','管理员');";
		dao.add(sql);
	}
	
	//初始化一个管理员
	public void initTeacher(){
		DAO dao=new DAO();
		String sql="insert into admin values('admin1001','akalin','12345');";
		int array[]={1,2,3};
		if(dao.query("select * from admin;", array).isEmpty()){
			dao.add(sql);
		}
	}
	/** 
     * 监听最外层窗口的resize事件,并根据新的窗口大小来调整背景图片的尺寸 
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
