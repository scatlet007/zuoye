package com.akalin.userframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.akalin.dao.Conn;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

public class StudentMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4461864905127875923L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JMenuItem output;//导出到Excl
	private JMenuItem lookCourse;//查看课表
	private JMenuItem logout;//退出登录
	private JMenuItem grammer;//柱形图
	private JMenuItem pie;//饼形图
	private JMenuItem about;//关于
	private JComboBox term;//学期
	private JComboBox termData;//学期数据
	private List<Map<String, Object>> list;//用于保存成绩数据的集合
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain frame = new StudentMain("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentMain(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,675, 450);
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
		
		JMenu file = new JMenu("文件");
		menuBar.add(file);
		
		/*output = new JMenuItem("导出Excel");
		file.add(output);*/
		
		lookCourse=new JMenuItem("查看课表");
		file.add(lookCourse);
		
		logout=new JMenuItem("退出");
		file.add(logout);
		
		/*JMenu view = new JMenu("视图");
		menuBar.add(view);
		
		grammer=new JMenuItem("柱形图");
		view.add(grammer);
		
		pie=new JMenuItem("饼形图");
		view.add(pie);*/
		
		JMenu help = new JMenu("帮助");
		menuBar.add(help);
		
		about=new JMenuItem("关于");
		help.add(about);
		
		JPanel centerPanel = new JPanel();
		top.add(centerPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		term = new JComboBox();
		term.setMaximumRowCount(10);
		term.addItem("学期");
		term.addItem("学年");
		centerPanel.add(term);
		
		termData = new JComboBox();
		termData.setMaximumRowCount(10);
		termData.addItem("1");
		termData.addItem("2");
		termData.addItem("3");
		termData.addItem("4");
		termData.addItem("5");
		termData.addItem("6");
		centerPanel.add(termData);
		
		JButton search = new JButton("查询");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		centerPanel.add(search);
		
		/*JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] columnValues={"序号","课程","学分","课程分类","考核方式","课程性质","成绩","取得学分","绩点","学分绩点"};
		String[][] tableValues=new String[20][10];
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++){
				tableValues[i][j]="+";
			}
		}
		tableModel=new DefaultTableModel(tableValues, columnValues);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);*/
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("课程");
		columnNameV.add("学分");
		columnNameV.add("课程分类");
		columnNameV.add("考核方式");
		columnNameV.add("成绩");
		columnNameV.add("取得学分");
		columnNameV.add("绩点");
		columnNameV.add("取得绩点");
		
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=2;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(int col=1;col<9;col++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		//创建面板，在该面板中实现带行标题栏的表格
		final MFixedColumnTable mainData=new MFixedColumnTable(columnNameV, tableValueV, 2);
		mainData.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(mainData, BorderLayout.CENTER);		//把面板添加到窗体中央
		//复制 end
		
		JPanel footPanel = new JPanel();
		contentPane.add(footPanel, BorderLayout.SOUTH);
		footPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		footPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 4));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel nowCredit = new JLabel("获得的学分：");
		panel.add(nowCredit);
		
		JLabel averige = new JLabel("主课平均成绩：");
		panel.add(averige);
		
		JPanel bottom = new JPanel();
		//bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		footPanel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("欢迎使用");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("操作者：");
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		
		JLabel data = new JLabel("日期");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		JLabel time = new JLabel("现在的时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		
	}
	//窗体事件监听
	public void myEvent(){
		//导出到Excel(预留)
		output.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//查看课表
		lookCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//退出登录
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//查看柱形图(预留)
		grammer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//查看饼形图(预留)
		pie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//查看关于
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	//按学期查看成绩
	public List<Map<String,Object>> queryByTerm()throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";
			Map<String,Object> map=new HashMap<String, Object>();
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				//list.add(resultSet.getString("teamName"));
				//向集合添加元素
				map.put("课程", resultSet.getString("course"));
				map.put("学分", resultSet.getString("credit"));
				map.put("课程分类", resultSet.getString("type"));
				map.put("考核方式", resultSet.getString("type2"));
				map.put("成绩", resultSet.getString("grade"));
				map.put("取得学分", resultSet.getString("getCredit"));
				map.put("绩点", resultSet.getString("dit"));
				map.put("取得绩点", resultSet.getString("getDit"));
				list.add(map);
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
	//按学年查看成绩
	
	public List<Object> queryByYear() throws Exception{
		Conn conn=new Conn();
		List<Object> list=new ArrayList<Object>();
		if(conn.getConnection()){
			conn.getState();
			String sql="";
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				//list.add(resultSet.getString("teamName"));
				//向集合添加元素
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
	
	//计算总学分
	public int count(){
		int sum=0;
		for(Map m:list){
			sum=sum+(int)m.get("取得学分");
		}
		return sum;
	}
	
	//计算主课平均分
	public int averige(){
		int sum=0;
		for(Map m:list){
			if(m.get("取得学分").equals("考试"))
				sum=sum+(int)m.get("成绩");
		}
		return sum;
	}
}
