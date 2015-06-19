package com.frames;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class MFixedColumnTable extends javax.swing.JPanel {
	private static final long serialVersionUID = 5766917135088138415L;
	private Vector<String> columnNameV;			//表格列名数组
	private Vector<Vector<Object>> tableValueV;	//表格的数据数组
	private int fixedColumn=1; 					//固定列的数量
	
	
	/**
	 * 内部类，用于创建左侧固定列表格的模型类
	 * @author scatlet
	 *
	 */
	private class FixedColumnTableModel extends AbstractTableModel{

		@Override
		public int getColumnCount() { 		//返回固定列的数量
			return fixedColumn;
		}

		@Override
		public int getRowCount() { 			//返回行数
			return tableValueV.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {	//返回指定单元格的值
			return tableValueV.get(rowIndex).get(columnIndex);
		}
		
		public String getColumnName(int columnIndex){				//返回指定列的名称
			return columnNameV.get(columnIndex);
			
		}
	}
	
	/**
	 * 创建用于右侧移动列表格的模型类
	 * @author scatlet
	 *
	 */
	private class FloatingColumnTableModel extends AbstractTableModel{

		@Override
		public int getColumnCount() { 				//返回可移动列的数量
			
			return columnNameV.size()-fixedColumn;	//去掉固定列后的数量
		}

		@Override
		public int getRowCount() {					//返回行数
			
			return tableValueV.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {			//返回指定单元格的值
			
			return tableValueV.get(rowIndex).get(columnIndex+fixedColumn);  //为索引加上固定列的数量
		}
		
		public String getColumnName(int columnIndex){						//返回指定列的名称
			return columnNameV.get(columnIndex+fixedColumn);				//为索引加上固定列的数量
		}
		
	}
	private JTable fixedColumnTable; 					//固定列表格对象
	private FixedColumnTableModel fixedColumnTableModel;//固定列表格模型对象
	private JTable floatingColumnTable;					//移动列表格对象
	private FloatingColumnTableModel floatingColumnTableModel;//移动列表格模型对象
	
	/**
	 * 创建用于同步两个表格中被选中的行的事件监听机制
	 * 当选中左侧固定列表格中的某一行时，监听器会同步选中右侧移动列表格中的对应行
	 * @author scatlet
	 *
	 */
	private class MListSelectionListener implements ListSelectionListener{
		
		boolean isFixedColumnTable=true;								//默认由选择中固定列表格中的行触发
		
		public MListSelectionListener(boolean isFixedColumnTable){
			
			this.isFixedColumnTable=isFixedColumnTable;					//为成员变量赋值
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(isFixedColumnTable){										//由选中固定列表格中的行触发
				int row=fixedColumnTable.getSelectedRow();				//获得固定列表格中的选中行
				floatingColumnTable.setRowSelectionInterval(row, row);	//同时选中右侧可移动的列表格中的相应行
			}else{														//由选中可移动列表中的行触发
				int row=floatingColumnTable.getSelectedRow();			//获得可移动列表格中的选中行
				fixedColumnTable.setRowSelectionInterval(row, row);		//同时选中左侧固定列表格中的相应行
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param columnNameV 表格列名数组
	 * @param tableValueV 表格数据数组
	 * @param fixedColumn 固定列数量
	 */
	public MFixedColumnTable(Vector columnNameV,Vector tableValueV,int fixedColumn){
		super();
		setLayout(new java.awt.BorderLayout());
		this.columnNameV=columnNameV;	//表格列名数组
		this.tableValueV=tableValueV;	//表格数据数组
		this.fixedColumn=fixedColumn;	//固定列数量
		//创建固定列表格
		fixedColumnTableModel=new FixedColumnTableModel();					//创建固定列表格模型对象
		fixedColumnTable=new JTable(fixedColumnTableModel);					//创建固定列表格对象
		ListSelectionModel fixed=fixedColumnTable.getSelectionModel();		//获得选择模型对象
		fixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//选择模式为单选
		fixed.addListSelectionListener(new MListSelectionListener(true));	//添加行被选中的事件监听器
		//创建移动列表格
		floatingColumnTableModel=new FloatingColumnTableModel();			//创建可移动列表格模型对象
		floatingColumnTable=new JTable(floatingColumnTableModel);			//创建可移动列表格对象
		floatingColumnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		//关闭表格的自动调整功能
		ListSelectionModel floating=floatingColumnTable.getSelectionModel();//获得选择模型对象
		floating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//选择模式为单选
		floating.addListSelectionListener(new MListSelectionListener(false));//添加行被选中的事件监听器
		//创建滚动面板
		JScrollPane scrollPane=new JScrollPane();							//创建一个滚动面板对象
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedColumnTable.getTableHeader());
		JViewport viewport =new JViewport();
		viewport.setView(fixedColumnTable);								//将固定列表格添加到视口中
		viewport.setPreferredSize(fixedColumnTable.getPreferredSize());	//设置视口首选大小
		scrollPane.setRowHeaderView(viewport);
		scrollPane.setViewportView(floatingColumnTable);
		add(scrollPane, java.awt.BorderLayout.CENTER);
	}
}
