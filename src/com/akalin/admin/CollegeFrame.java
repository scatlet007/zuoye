package com.akalin.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.frames.MFixedColumnTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class CollegeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField collegeName;
	private JTextField creates;
	private JTextField details;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public CollegeFrame() {
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
		
		/*//��ӱ���
		BackPanel backPanel=new BackPanel(675,350);
		contentPane.add(backPanel,BorderLayout.CENTER);*/
		String[] columnValues={"���","ѧԺ��","����ʱ��","������"};
		String[][] tableValues=new String[30][4];
		for(int i=0;i<30;i++){
			for(int j=0;j<4;j++){
				tableValues[i][j]="+";
			}
		}
		JScrollPane scrollPane;
		
		DefaultTableModel tableModel=new DefaultTableModel(tableValues, columnValues);
		JTable table=new JTable(tableModel);
		table.setRowHeight(24);
		table.setSize(WIDTH, HEIGHT);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane=new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
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
		
		JLabel user = new JLabel("�����ߣ�");
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
		
		JButton submit = new JButton("�ύ");
		panel_1.add(submit);
	}

}
