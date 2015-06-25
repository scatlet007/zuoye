package com.akalin.teacher;

import java.util.ArrayList;
import java.util.List;
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
	private Vector<String> columnNameV;			//������������
	private Vector<Vector<Object>> tableValueV;	//�������������
	private int fixedColumn=1; 					//�̶��е�����
	
	
	/**
	 * �ڲ��࣬���ڴ������̶��б����ģ����
	 * @author scatlet
	 *
	 */
	private class FixedColumnTableModel extends AbstractTableModel{

		@Override
		public int getColumnCount() { 		//���ع̶��е�����
			return fixedColumn;
		}

		@Override
		public int getRowCount() { 			//��������
			return tableValueV.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {	//����ָ����Ԫ���ֵ
			return tableValueV.get(rowIndex).get(columnIndex);
		}
		
		public String getColumnName(int columnIndex){				//����ָ���е�����
			return columnNameV.get(columnIndex);
			
		}
	}
	
	/**
	 * ���������Ҳ��ƶ��б����ģ����
	 * @author scatlet
	 *
	 */
	private class FloatingColumnTableModel extends AbstractTableModel{

		@Override
		public int getColumnCount() { 				//���ؿ��ƶ��е�����
			
			return columnNameV.size()-fixedColumn;	//ȥ���̶��к������
		}

		@Override
		public int getRowCount() {					//��������
			
			return tableValueV.size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {			//����ָ����Ԫ���ֵ
			
			return tableValueV.get(rowIndex).get(columnIndex+fixedColumn);  //Ϊ�������Ϲ̶��е�����
		}
		
		public String getColumnName(int columnIndex){						//����ָ���е�����
			return columnNameV.get(columnIndex+fixedColumn);				//Ϊ�������Ϲ̶��е�����
		}
		
	}
	private JTable fixedColumnTable; 					//�̶��б������
	private FixedColumnTableModel fixedColumnTableModel;//�̶��б���ģ�Ͷ���
	private JTable floatingColumnTable;					//�ƶ��б������
	private FloatingColumnTableModel floatingColumnTableModel;//�ƶ��б���ģ�Ͷ���
	private int selectRow;
	private Vector<Vector<Object>> list;
	/**
	 * ��������ͬ�����������б�ѡ�е��е��¼���������
	 * ��ѡ�����̶��б����е�ĳһ��ʱ����������ͬ��ѡ���Ҳ��ƶ��б����еĶ�Ӧ��
	 * @author scatlet
	 *
	 */
	private class MListSelectionListener implements ListSelectionListener{
		
		boolean isFixedColumnTable=true;								//Ĭ����ѡ���й̶��б����е��д���
		//MFixedColumnTable a=new MFixedColumnTable();
		public MListSelectionListener(boolean isFixedColumnTable){
			
			this.isFixedColumnTable=isFixedColumnTable;					//Ϊ��Ա������ֵ
		}
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(isFixedColumnTable){										//��ѡ�й̶��б����е��д���
				int row=fixedColumnTable.getSelectedRow();				//��ù̶��б����е�ѡ����
				floatingColumnTable.setRowSelectionInterval(row, row);	//ͬʱѡ���Ҳ���ƶ����б����е���Ӧ��
				selectRow=row;
				System.out.println("a"+row);
				getValue(row);
			}else{														//��ѡ�п��ƶ��б��е��д���
				int row=floatingColumnTable.getSelectedRow();			//��ÿ��ƶ��б����е�ѡ����
				fixedColumnTable.setRowSelectionInterval(row, row);		//ͬʱѡ�����̶��б����е���Ӧ��
				selectRow=row;
				System.out.println("b"+row);
				getValue(row);
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param columnNameV ������������
	 * @param tableValueV ������������
	 * @param fixedColumn �̶�������
	 */
	public MFixedColumnTable(Vector columnNameV,Vector tableValueV,int fixedColumn){
		super();
		setLayout(new java.awt.BorderLayout());
		this.columnNameV=columnNameV;	//������������
		this.tableValueV=tableValueV;	//������������
		this.fixedColumn=fixedColumn;	//�̶�������
		//�����̶��б���
		fixedColumnTableModel=new FixedColumnTableModel();					//�����̶��б���ģ�Ͷ���
		fixedColumnTable=new JTable(fixedColumnTableModel);					//�����̶��б������
		fixedColumnTable.setRowHeight(24);
		ListSelectionModel fixed=fixedColumnTable.getSelectionModel();		//���ѡ��ģ�Ͷ���
		fixed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//ѡ��ģʽΪ��ѡ
		fixed.addListSelectionListener(new MListSelectionListener(true));	//�����б�ѡ�е��¼�������
		//�����ƶ��б���
		floatingColumnTableModel=new FloatingColumnTableModel();			//�������ƶ��б���ģ�Ͷ���
		floatingColumnTable=new JTable(floatingColumnTableModel);			//�������ƶ��б������
		floatingColumnTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		//�رձ�����Զ���������
		floatingColumnTable.setRowHeight(24);
		ListSelectionModel floating=floatingColumnTable.getSelectionModel();//���ѡ��ģ�Ͷ���
		floating.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		//ѡ��ģʽΪ��ѡ
		floating.addListSelectionListener(new MListSelectionListener(false));//�����б�ѡ�е��¼�������
		//�����������
		JScrollPane scrollPane=new JScrollPane();							//����һ������������
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedColumnTable.getTableHeader());
		JViewport viewport =new JViewport();
		viewport.setView(fixedColumnTable);								//���̶��б������ӵ��ӿ���
		viewport.setPreferredSize(fixedColumnTable.getPreferredSize());	//�����ӿ���ѡ��С
		scrollPane.setRowHeaderView(viewport);
		scrollPane.setViewportView(floatingColumnTable);
		add(scrollPane, java.awt.BorderLayout.CENTER);
	}
	
	public MFixedColumnTable() {
		
	}

	//��ȡ����ĳһѡ�е��е�ֵ
	public final Vector<Vector<Object>> getValue(int arg0){
		list=new Vector<Vector<Object>>();
		this.list.add(this.tableValueV.get(arg0));
		return this.list;
	}
	
	public Vector<Vector<Object>> getList() {
		return list;
	}

	public void setList(Vector<Vector<Object>> list) {
		this.list = list;
	}

	public int getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(int selectRow) {
		this.selectRow = selectRow;
	}

	public JTable getFixedColumnTable() {
		return fixedColumnTable;
	}

	public void setFixedColumnTable(JTable fixedColumnTable) {
		this.fixedColumnTable = fixedColumnTable;
	}
	
	
	
}