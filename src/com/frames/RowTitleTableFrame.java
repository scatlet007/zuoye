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
		setTitle("���б������ı��");
		setBounds(100,100,400,230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("����");
		for(int i=1;i<21;i++){
			columnNameV.add("��Ʒ"+i);
		}
		
		Vector<Vector<Object>> tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<31;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int col=0;col<20;col++){
				rowV.add((int)(Math.random()*1000));
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		final MFixedColumnTable panel=new MFixedColumnTable(columnNameV, tableValueV, 1);
		getContentPane().add(panel, BorderLayout.CENTER);		//�������ӵ���������
		//���� end
	}
	
	public static void main(String[] args){
		RowTitleTableFrame frame=new RowTitleTableFrame();
		frame.setVisible(true);
	}

}
