package com.akalin.teacher;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;

import com.akalin.dao.Conn;
import com.akalin.tool.Message;

import javax.swing.JLabel;

public class Function extends JFrame {

	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=77,277
	 */
	private JLabel label1;//课程
	private JLabel label2;//作业
	private JLabel label3;//期中
	private JLabel label4;//平时
	private JLabel label5;//期末
	private JTextField course;//课程
	private JTextField homework;
	private JTextField middy;
	private JTextField normal;
	private JTextField end;
	private JPanel foot;
	private int h;
	private int m;
	private int n;
	private int t;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private final MFixedColumnTable mainData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Function frame = new Function();
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
	public Function() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		contentPane.add(center, BorderLayout.CENTER);
		//复制 start
		Vector<String> columnNameV=new Vector<String>();	//创建列名向量
		columnNameV.add("序号");
		columnNameV.add("课程");
		columnNameV.add("作业");
		columnNameV.add("期中");
		columnNameV.add("平时");
		columnNameV.add("期末");
						
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//创建数据向量
		for(int row=1;row<10;row++){
			Vector<Object> rowV=new Vector<Object>();				//创建行向量
			rowV.add(row);
			for(Map<String,Object> m:list){
				rowV.add(m.get("课程"));
				rowV.add(m.get("作业"));
				rowV.add(m.get("期中"));
				rowV.add(m.get("平时"));
				rowV.add(m.get("期末"));
			}
			tableValueV.add(rowV);									//把行向量添加到数据向量
		}
		//创建面板，在该面板中实现带行标题栏的表格
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(50, 50, 10, 50));
		center.add(mainData, BorderLayout.CENTER);		//把面板添加到窗体中央
		JTable f=mainData.getFixedColumnTable();
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//复制 end
		JPanel bottom=new JPanel();
		bottom.setBounds(20, 20, center.getWidth(), 100);
		center.add(bottom, BorderLayout.SOUTH);
		label1=new JLabel("课程");
		label2=new JLabel("作业");
		label3=new JLabel("期中");
		label4=new JLabel("平时");
		label5=new JLabel("期末");
		course=new JTextField(10);
		homework=new JTextField(5);
		middy=new JTextField(5);
		normal=new JTextField(5);
		end=new JTextField(5);
		bottom.add(label1);
		bottom.add(course);
		bottom.add(label2);
		bottom.add(homework);
		bottom.add(label3);
		bottom.add(middy);
		bottom.add(label4);
		bottom.add(normal);
		bottom.add(label5);
		bottom.add(end);
		
		foot = new JPanel();
		contentPane.add(foot, BorderLayout.SOUTH);
		foot.setLayout(new GridLayout(1,6));
		JLabel welcome = new JLabel("欢迎使用");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(welcome);
		
		JLabel user = new JLabel("操作者：");
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(user);
		
		JLabel data = new JLabel("日期");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(data);
		
		JLabel time = new JLabel("现在的时间：");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		foot.add(time);
	}
	public void myEvent(){
		//给期末输入框添加回车事件
		end.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					h=Integer.parseInt(homework.getText());
					m=Integer.parseInt(middy.getText());
					n=Integer.parseInt(normal.getText());
					t=Integer.parseInt(end.getText());
				}
			}
		});
	}
	
	//保存数据
	public void save(int arg0,int arg1,int arg2,int arg3) throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";//请填写插入的sql语句
			conn.getStatement().executeQuery(sql);
			conn.close();
		}else{
			Message message=new Message("网络错误，无法连接到数据库");
			message.pack();
			conn.close();
		}
	}
	//更新表格中的数据
	public List<Map<String,Object>> update() throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";//请填写查询的sql语句
			ResultSet resultSet=conn.getStatement().executeQuery(sql);
			while(resultSet.next()){
				Map<String,Object> m=new HashMap<String, Object>();
				m.put("课程", resultSet.getString("course"));
				m.put("作业", resultSet.getString("task"));
				m.put("期中", resultSet.getString("mid"));
				m.put("平时", resultSet.getString("pacific"));
				m.put("期末", resultSet.getString("final"));
				list.add(m);
			}
			conn.close();
		}else{
			Message message=new Message("网络错误，无法连接到数据库");
			message.pack();
			conn.close();
		}
		return list;
	}
	//修改一条数据
	public void modify(int arg0) throws Exception{
		Conn conn=new Conn();
		if(conn.getConnection()){
			conn.getState();
			String sql="";//请填写修改的sql语句
			conn.getStatement().executeUpdate(sql);
			conn.close();
		}else{
			Message message=new Message("网络错误，无法连接到数据库");
			message.pack();
			conn.close();
		}
	}
	//选中某一行表格的数据，并将其显示在填写栏
	private class MyListener implements ListSelectionListener{
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(flag!=mainData.getSelectRow()){
				List<Vector<Object>> list=mainData.getList();
				for(Vector<Object> vec:list){
					course.setText((String)vec.get(1));
					homework.setText((String)vec.get(2));
					middy.setText((String)vec.get(3));
					normal.setText((String)vec.get(4));
					end.setText((String)vec.get(5));
				}
			}
		}
		
	}
}
