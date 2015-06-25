package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.akalin.dao.DAO;
import com.akalin.tool.Message;
import com.frames.MFixedColumnTable;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class CollegeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField collegeName;
	private JTextField creates;
	private JTextField details;
	private List<Map<String,Object>> list;
	private ListSelectionModel fixed;
	private int flag=0;
	private MFixedColumnTable mainData;
	private Vector<Vector<Object>> tableValueV;
	private JButton submit;
	private JButton modify;
	private String manager;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CollegeFrame frame = new CollegeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public CollegeFrame(String username) {
		manager=username;
		init();
		initData();
		myEvent();
	}
	public void init(){
		this.setTitle("ѧԺ�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 450);
		this.setLocationRelativeTo(null);//��������Ļ�м���ʾ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel top = new JPanel();
		contentPane.add(top, BorderLayout.NORTH);
		top.setBorder(new EmptyBorder(0, 0, 30, 0));
		top.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		top.add(menuBar, BorderLayout.NORTH);
		
		JMenu collegeManaer = new JMenu("ѧԺ����");
		menuBar.add(collegeManaer);
		
		JMenu majorManager = new JMenu("רҵ����");
		menuBar.add(majorManager);
		
		JMenu teamManager = new JMenu("�༶����");
		menuBar.add(teamManager);
		
		JMenu teacherManager = new JMenu("��ʦ����");
		menuBar.add(teacherManager);
		
		JMenu studentManager = new JMenu("ѧ������");
		menuBar.add(studentManager);
		
		JMenu courseManager = new JMenu("�γ̹���");
		menuBar.add(courseManager);
		
		JMenu roleManager = new JMenu("��ɫ����");
		menuBar.add(roleManager);
		
		
		//���� start
		Vector<String> columnNameV=new Vector<String>();	//������������
		columnNameV.add("���");
		columnNameV.add("ѧԺ");
		columnNameV.add("����ʱ��");
		columnNameV.add("������");
								
		tableValueV=new Vector<Vector<Object>>();//������������
		for(int row=1;row<30;row++){
			Vector<Object> rowV=new Vector<Object>();				//����������
			rowV.add(row);
			for(int cov=0;cov<4;cov++){
				rowV.add("+");
			}
			tableValueV.add(rowV);									//����������ӵ���������
		}
		//������壬�ڸ������ʵ�ִ��б������ı��
		mainData=new MFixedColumnTable(columnNameV, tableValueV, 1);
		mainData.setBorder(new EmptyBorder(50, 50, 10, 50));
		contentPane.add(mainData, BorderLayout.CENTER);		//�������ӵ���������
		JTable f=mainData.getFixedColumnTable();
		TableColumnModel c=mainData.getFloatingColumnTable().getColumnModel();
		c.getColumn(2).setPreferredWidth(400);
		fixed=f.getSelectionModel();
		fixed.addListSelectionListener(new MyListener());
		//���� end
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel bottom = new JPanel();
		panel.add(bottom);
		bottom.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.setLayout(new GridLayout(1,6));
		
		JLabel welcome = new JLabel("��ӭʹ��");
		welcome.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(welcome);
		
		JLabel user = new JLabel("�����ߣ�"+manager);
		user.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(user);
		
		JLabel data = new JLabel("����");
		data.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(data);
		
		JLabel time = new JLabel("���ڵ�ʱ�䣺");
		time.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		bottom.add(time);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(30, 30, 30, 50));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel name = new JLabel("ѧԺ��");
		panel_1.add(name);
		
		collegeName = new JTextField();
		panel_1.add(collegeName);
		collegeName.setColumns(20);
		
		JLabel createTime = new JLabel("����ʱ��");
		panel_1.add(createTime);
		
		creates = new JTextField();
		panel_1.add(creates);
		creates.setColumns(20);
		
		JLabel status = new JLabel("������:");
		panel_1.add(status);
		
		details = new JTextField();
		panel_1.add(details);
		details.setColumns(200);
		
		submit = new JButton("�ύ");
		panel_1.add(submit);
		modify = new JButton("�޸�");
		panel_1.add(modify);
		setVisible(true);
	}
	//ѡ��ĳһ�б������ݣ���������ʾ����д��
		private class MyListener implements ListSelectionListener{
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(flag!=mainData.getSelectRow()){
					List<Vector<Object>> list=mainData.getList();
					for(Vector<Object> vec:list){
						collegeName.setText((String)vec.get(1));
						creates.setText((String)vec.get(2));
						details.setText((String)vec.get(3));
					}
				}
			}
			
		}
	//��ʼ������
	public void initData(){
		DAO dao=new DAO();
		String[] key={"ѧԺ","����ʱ��","������"};
		String[] values={"name","createTime","status"};
		list=dao.query("select *from college;", values, key);
		if(!list.isEmpty()){
			tableValueV.clear();
			for(int row=1;row<list.size();row++){
				Vector<Object> rowV=new Vector<Object>();				//����������
				rowV.add(row);
				for(Map<String,Object> m:list){
					rowV.add(m.get("ѧԺ"));
					rowV.add(m.get("����ʱ��"));
					rowV.add(m.get("������"));
				}
				tableValueV.add(rowV);									//����������ӵ���������
			}
		}
	}
	
	public void myEvent(){
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="insert into college(id,name,createTime,status) values"
						+ "('college1001','"+collegeName.getText()+"','"+creates.getText()+"','"+details.getText()+"');";
				if(dao.add(sql)==1){
					Message message=new Message("ִ�гɹ���");
					message.pack();
				}
			}
		});
		
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao=new DAO();
				String sql="insert into college values"
						+ "('"+collegeName.getText()+"','"+creates.getText()+"','"+details.getText()+"');";
				dao.modify(sql);
			}
		});
	}
}
