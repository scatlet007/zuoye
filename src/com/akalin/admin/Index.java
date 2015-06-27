package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import com.akalin.tool.GetDate;
import com.akalin.tool.GetTime;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8810450090270436228L;
	private JPanel contentPane;
	private JMenu collegeManager;
	private JMenu majorManager;
	private JMenu teamManager;
	private JMenu teacherManager;
	private JMenu studentManager;
	private JMenu courseManager;
	private JMenu roleManager;
	private JMenuItem collegeAdd;
	private JMenuItem majorAdd;
	private JMenuItem teamAdd;
	private JMenuItem teacherAdd;
	private JMenuItem studentAdd;
	private JMenuItem courseAdd;
	private JMenuItem roleAdd;
	private String manager;
	private BackPanel backPanel;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Index(String username) {
		manager=username;
		init();
		myEvent();
	}
	
	public void init(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,675, 450);
		this.setLocationRelativeTo(null);//窗口在屏幕中间显示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		collegeManager = new JMenu("学院管理");
		menuBar.add(collegeManager);
		
		majorManager = new JMenu("专业管理");
		menuBar.add(majorManager);
		
		teamManager = new JMenu("班级管理");
		menuBar.add(teamManager);
		
		teacherManager = new JMenu("教师管理");
		menuBar.add(teacherManager);
		
		studentManager = new JMenu("学生管理");
		menuBar.add(studentManager);
		
		courseManager = new JMenu("课程管理");
		menuBar.add(courseManager);
		
		roleManager = new JMenu("角色管理");
		menuBar.add(roleManager);
		
		collegeAdd=new JMenuItem("学院添加");
		collegeManager.add(collegeAdd);
		majorAdd=new JMenuItem("专业添加");
		majorManager.add(majorAdd);
		teamAdd=new JMenuItem("班级添加"); 
		teamManager.add(teamAdd);
		teacherAdd=new JMenuItem("教师添加");
		teacherManager.add(teacherAdd);
		studentAdd=new JMenuItem("学生添加");
		courseAdd=new JMenuItem("课程添加");
		courseManager.add(courseAdd);
		studentManager.add(studentAdd);
		roleAdd=new JMenuItem("角色添加");
		roleManager.add(roleAdd);
		
		//添加背景
		double panelWidth = 675;  
		double panelHeight =450- 25 - 25 - 20;//(两个25是内外两个窗口标题栏的高度,20是底部更新进度栏的高度)  
		backPanel=new BackPanel(panelWidth,panelHeight,"/res/loginImg.jpg");
		contentPane.add(backPanel,-1);
		
		JPanel bottom = new JPanel();
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("欢迎使用");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("操作者："+manager);
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		
		GetDate getdata=new GetDate();
		JLabel data = new JLabel("日期："+getdata.getDateString());
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		GetTime getTime=new GetTime();
		JLabel time = new JLabel("现在的时间是："+getTime.getTime());
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		this.setTimer(time);
		contentPane.add(bottom, BorderLayout.SOUTH);
		setVisible(true);
	}
	public void myEvent(){
		//点击管理学院菜单时
		collegeAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CollegeFrame college=new CollegeFrame(manager);
				System.out.println("点击乐趣学院管理");
				setVisible(false);
				
			}
		});
		//点击专业管理时
		majorAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Major majorFrame=new Major(manager);
				setVisible(false);
			}
		});
		//点击班级管理
		teamAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TeamFrame teamFrame=new TeamFrame(manager);
				setVisible(false);
				
			}
		});
		//点击教师管理
		teacherAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TeacherFrame teacherFrame=new TeacherFrame(manager);
				setVisible(false);
				
			}
		});
		//点击学生管理
		studentAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentFrame studentFrame=new StudentFrame(manager);
				setVisible(false);
				
			}
		});
		//点击角色管理
		roleAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RoleFrame roleFrame=new RoleFrame(manager);
				setVisible(false);
			}
		});
	}
	/** 
     * 监听最外层窗口的resize事件,并根据新的窗口大小来调整背景图片的尺寸 
     * @param evt 
     */  
    private void formComponentResized(java.awt.event.ComponentEvent evt) {                                        
        // TODO add your handling code here:  
        try{  
            this.contentPane.remove(backPanel);  
        }catch(Exception e){  
        }  
        backPanel = null;  
        Dimension newSize = evt.getComponent().getSize();  
        backPanel = new BackPanel(newSize.getWidth(),newSize.getHeight()-70,"/res/sceen.jpg");  
        this.contentPane.add(backPanel,-1);  
    } 
    //设置Timer 1000ms实现一次动作 实际是一个线程   
    private void setTimer(JLabel time){   
        final JLabel varTime = time;   
        Timer timeAction = new Timer(1000, new ActionListener() {          
  
            public void actionPerformed(ActionEvent e) {       
                GetTime getTime=new GetTime();  
                varTime.setText("现在的时间是："+getTime.getTime());   
            }      
        });            
        timeAction.start();        
    }   
}
