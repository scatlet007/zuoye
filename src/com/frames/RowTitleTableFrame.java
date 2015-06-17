package com.frames;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;

public class RowTitleTableFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5788832451734627970L;
	public RowTitleTableFrame(){
		super();
		setTitle("带行标题栏的表格");
		setBounds(100,100,400,230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		final MFixedColumnTable panel=new MFixedColumnTable(columnNameV, tableValueV, 1);
		getContentPane().add(panel, BorderLayout.CENTER);		//把面板添加到窗体中央
		//复制 end
	}
	
	public static void main(String[] args){
		RowTitleTableFrame frame=new RowTitleTableFrame();
		frame.setVisible(true);
	}

}
