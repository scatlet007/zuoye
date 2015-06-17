package com.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.frames.MFixedColumnTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class MyTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyTest frame = new MyTest();
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
	public MyTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel tablePanel = new JPanel();
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
		final MFixedColumnTable panel=new MFixedColumnTable(columnNameV, tableValueV, 1);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(tablePanel, BorderLayout.CENTER);
		tablePanel.setLayout(new BorderLayout(0, 0));
		tablePanel.add(panel, BorderLayout.CENTER);
		
		JPanel panel_noth = new JPanel();
		contentPane.add(panel_noth, BorderLayout.NORTH);
		panel_noth.setLayout(new GridLayout(0, 6, 5, 5));
		
		JLabel lblNewLabel = new JLabel(" ");
		panel_noth.add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel(" ");
		panel_noth.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel(" ");
		panel_noth.add(lblNewLabel2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		panel_noth.add(textField);
		textField.setColumns(30);
		
		
		JButton btnNewButton = new JButton("search");
		panel_noth.add(btnNewButton);
		
	}

}
