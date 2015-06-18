package com.wyp.Student;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.frames.MFixedColumnTable;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

public class StudentMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMain frame = new StudentMain();
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
	public StudentMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, contentPane.getWidth(), 30);
		contentPane.add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel navigation = new JPanel();
		topPanel.add(navigation,BorderLayout.NORTH);
		navigation.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		navigation.add(menuBar, BorderLayout.NORTH);
		
		JMenu start = new JMenu("\u5F00\u59CB");
		menuBar.add(start);
		
		JMenuItem output = new JMenuItem("\u5BFC\u51FAExcel");
		start.add(output);
		
		JMenuItem lookCourse = new JMenuItem("\u67E5\u770B\u8BFE\u8868");
		start.add(lookCourse);
		
		JMenuItem logout = new JMenuItem("\u9000\u51FA");
		start.add(logout);
		
		JMenu screen = new JMenu("\u7B5B\u9009");
		menuBar.add(screen);
		
		JMenuItem grade = new JMenuItem("\u6210\u7EE9");
		screen.add(grade);
		
		JMenuItem credit = new JMenuItem("\u5B66\u5206");
		screen.add(credit);
		
		JMenu view = new JMenu("\u89C6\u56FE");
		menuBar.add(view);
		
		JMenuItem histogram = new JMenuItem("\u67F1\u5F62\u56FE");
		view.add(histogram);
		
		JMenuItem pie = new JMenuItem("\u997C\u5F62\u56FE");
		view.add(pie);
		
		JMenu help = new JMenu("\u5E2E\u52A9");
		menuBar.add(help);
		
		JMenuItem about = new JMenuItem("\u5173\u4E8E");
		help.add(about);
		
		JPanel centerPanel = new JPanel();
		topPanel.add(centerPanel, BorderLayout.CENTER);
		
		JComboBox checkType = new JComboBox();
		checkType.addItem("学期");
		checkType.addItem("学年");
		centerPanel.add(checkType);
		
		JComboBox checkTypeData = new JComboBox();
		checkTypeData.addItem("请选择");
		centerPanel.add(checkTypeData);
		
		JButton search = new JButton("search");
		centerPanel.add(search);
		
		JPanel nullPanel = new JPanel();
		nullPanel.setBounds(0, 0, contentPane.getWidth(), 40);
		topPanel.add(nullPanel, BorderLayout.SOUTH);
		
		//复制 start
				Vector<String> columnNameV=new Vector<String>();	//创建列名向量
				columnNameV.add("日期");
				for(int i=1;i<21;i++){
					columnNameV.add("商品"+i);
				}
				
				Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//创建数据向量
				for(int row=1;row<31;row++){
					Vector<Object> rowV=new Vector<Object>();				//创建行向量
					rowV.add(row);
					for(int col=0;col<20;col++){
						rowV.add((int)(Math.random()*1000));
					}
					tableValueV.add(rowV);									//把行向量添加到数据向量
				}
				//创建面板，在该面板中实现带行标题栏的表格
				final MFixedColumnTable mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
				mainData.setBorder(new EmptyBorder(20, 20, 20, 20));
				contentPane.add(mainData, BorderLayout.CENTER);		//把面板添加到窗体中央
				//复制 end
				JPanel footBar = new JPanel();
				footBar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				contentPane.add(footBar, BorderLayout.SOUTH);
				footBar.setLayout(new GridLayout(1, 6));
				
				JLabel systemBar = new JLabel("New label");
				systemBar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				footBar.add(systemBar);
				
				JLabel welcomeBar = new JLabel("welcome");
				welcomeBar.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				footBar.add(welcomeBar);
				
				JLabel ctrler = new JLabel("\u64CD\u4F5C\u5458:");
				ctrler.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				footBar.add(ctrler);
				
				JLabel data = new JLabel("\u65E5\u671F\uFF1A");
				data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				footBar.add(data);
				
				JLabel time = new JLabel("\u65F6\u95F4\uFF1A");
				time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				footBar.add(time);
				
	}

}
