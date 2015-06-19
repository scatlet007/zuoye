package com.akalin.admin;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class BackPanel extends javax.swing.JPanel{
	Image image;
	int width;
	int height;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public BackPanel(int width,int height){
		URL url=BackPanel.class.getResource("/res/sceen.jpg");
		image=new ImageIcon(url).getImage();
		initComponments();
		this.width=width;
		this.height=height;
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
