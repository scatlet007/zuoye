package com.akalin.userframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.akalin.dao.Conn;
import com.akalin.tool.Message;

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
	private String teamName="";
	private String sex="";
	public StudentRegister(){
		init();
		System.out.println("jjjjjj");
	}
	//窗体初始化
	public void init(){
		JFrame jf=new JFrame("register");
		jf.setBounds(600, 300, 450, 300);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel main=new JPanel();
		main.setLayout(null);
		team=new JComboBox<String>();
		/*team.addItem("1");
		team.addItem("2");
		team.addItem("3");*/
		StudentRegister s=new StudentRegister();
		try {
			for(Object item:s.getTeam()){
				team.addItem((String)item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	//窗体事件
	public void myEvent(){
		//获取班级名
		team.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				switch (e.getStateChange())
                {
                case ItemEvent.SELECTED: 
                    //请在此添加选择下拉内容后的代码
               	 //System.out.println("选中" + e.getItem());
               	 	teamName=(String)e.getItem();
                    break;
                }
			}
		});
		//获取性别数据
		man.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sex=man.getText();
			}
		});
		girl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sex=girl.getText();
			}
		});
		//点击提交
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(!isUser(teamName,username.getText(),snumber.getText())){
					try {
						addUser(teamName, username.getText(), password.getSelectedText(), sex, snumber.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Message message=new Message("注册成功");
					message.pack();
					StudentMain studentMain=new StudentMain(username.getText());
					studentMain.pack();
				}else{
					Message message=new Message("已有该用户！");
					message.pack();
				}
			}
		});
	}
	/*public static void main(String[] args) {
		StudentRegister s=new StudentRegister();
	}*/
	//获得班级下拉列表的数据
	public List<Object> getTeam() throws Exception{
		Conn conn=new Conn();
		String driver="com.mysql.jdbc.Driver";		//数据库接口类名
		String url="jdbc:mysql://127.0.0.1/db_akalin";			//数据库连接地址
		String db_user="root";		//数据库连接用户名
		String db_password="12345";	//数据库连接密码
		List<Object> list=new ArrayList<Object>();
		if(conn.getConnection(driver, url, db_user, db_password)){
			conn.getState();
			String sql="";
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				list.add(resultSet.getString("teamName"));
			}
			resultSet.close();
			conn.close();
		}else{
			Message message=new Message("网络错误，无法连接到数据库");
			message.pack();
			conn.close();
		}
		return list;
	}
	//判断是否已有该用户
	public boolean isUser(String team,String userName,String snumber){
		Conn conn=new Conn();
		String driver="com.mysql.jdbc.Driver";		//数据库接口类名
		String url="jdbc:mysql://127.0.0.1/db_akalin";			//数据库连接地址
		String db_user="root";		//数据库连接用户名
		String db_password="12345";	//数据库连接密码
		List<Object> list=new ArrayList<Object>();
		if(conn.getConnection(driver, url, db_user, db_password)){
			conn.getState();
			String sql="";
			ResultSet resultSet;
			try {
				resultSet = conn.getStatement().executeQuery(sql);
				while(resultSet.next()){
					list.add(resultSet.getString("userName"));
				}
				resultSet.close();
				conn.close();
				if(list.isEmpty())
					return true;
				else 
					return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
				Message message=new Message("无法连接到数据库");
				message.pack();
			}
		}else{
			Message message=new Message("网络错误，无法连接到数据库");
			message.pack();
			conn.close();
		}
		return false;
	}
	
	//添加用户
	public void addUser(String team,String username,String password,String sex,String snumber)throws Exception{
		Conn conn=new Conn();
		String driver="com.mysql.jdbc.Driver";		//数据库接口类名
		String url="jdbc:mysql://127.0.0.1/db_akalin";			//数据库连接地址
		String db_user="root";		//数据库连接用户名
		String db_password="12345";	//数据库连接密码
		List<Object> list=new ArrayList<Object>();
		if(conn.getConnection(driver, url, db_user, db_password)){
			conn.getState();
			String sql="";
			conn.getStatement().executeUpdate(sql);
			conn.close();
		}else{
			Message message=new Message("网络错误，无法连接到数据库");
			message.pack();
			conn.close();
		}
	}
}
