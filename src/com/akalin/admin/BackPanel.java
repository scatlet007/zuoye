package com.akalin.admin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class BackPanel extends javax.swing.JPanel{
	Image image;
	private int width;
	private int height;
	
	public BackPanel(double width,double height,String imgPath){
		URL url=BackPanel.class.getResource(imgPath);
		image=new ImageIcon(url).getImage();
		initComponments();
		this.width=(int)width;
		this.height=(int)height;
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D) g;
		g2.drawImage(image, 0, 0,width,height, this);//绘制图像
	}
	
	private void initComponments(){
		setLayout(new java.awt.BorderLayout());//设置面板布局
	}
}
