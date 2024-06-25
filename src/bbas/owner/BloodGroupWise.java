package bbas.owner;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import bbas.dbinfo.DBconnection;
import bbas.gui.LoginFrame;
import net.proteanit.sql.DbUtils;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class BloodGroupWise extends JFrame implements ItemListener {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> cmbbloodgrp;
	private JLabel img1;
	private JLabel img;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BloodGroupWise frame = new BloodGroupWise();
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
	public BloodGroupWise() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Blood_Group_Wise");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1096, 762);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbbloodgrp = new JComboBox();
		cmbbloodgrp.setFont(new Font("Serif", Font.BOLD, 20));
		cmbbloodgrp.setModel(new DefaultComboBoxModel(new String[] {"Select Bloodgroup", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
		cmbbloodgrp.addItemListener(this);
		//FillCombo();
		cmbbloodgrp.setBounds(618, 148, 194, 52);
		contentPane.add(cmbbloodgrp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(314, 272, 758, 432);
		contentPane.add(scrollPane);
		
		table = new JTable();
		JTableHeader header=table.getTableHeader();
		header.setForeground(new Color(255,255,255)); //we can also write Color.Green
		header.setFont(new Font("Ariel", Font.BOLD, 20));
		header.setBackground(new Color(128, 0, 0));
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(305, 31, 777, 722);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bloodgroup Wise Details of all Donor/Receiver");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(141, 40, 597, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/bgimg1.jpeg"));
		Image i2=ic.getImage().getScaledInstance(1544, 796, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1565, 803);
		contentPane.add(img);
		
		
	}
	
//	public void FillCombo() {
//		Connection con=DBconnection.openConnection();
//		PreparedStatement ps=null;// compiled query reference 
//		ResultSet rs=null; //resultant dataset reference 
//		String selectQuery="select * from blood_bag_details where exists (select * from donor_receiverdetails where donor_receiverdetails.Bloodgroup=blood_bag_details.Bloodgroup) "; //means all columns with all records(rows)
//		
//
//		
//		try {
//			ps=con.prepareStatement(selectQuery);
//			rs=ps.executeQuery();//method is only called for select query
//			
//			while (rs.next()==true) {
//				String bloodgrp=rs.getString("Bloodgroup");//fetch the value from course_name column of student_details table 
//				cmbbloodgrp.addItem(bloodgrp);//add the fetched value in the combo box
//				
//			}
//		}
//		catch(SQLException se) {
//			se.printStackTrace();
//		}
//		finally {
//			try {
//				if(rs!=null)
//					rs.close();
//				if(ps!=null)
//					ps.close();
//				if(con!=null)
//					con.close();
//			}
//			catch(SQLException se) {
//				se.printStackTrace();
//			}
//	}
//	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int state=e.getStateChange();
		if(state==1) {
			String bg=(String)cmbbloodgrp.getSelectedItem();
			if(bg.equalsIgnoreCase("Select Bloodgroup")) {
				JOptionPane.showMessageDialog(this,"Please select the Blood group.","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from donor_receiverdetails where Bloodgroup=?"; //in query we always write column name
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, bg);
				rs=ps.executeQuery();
				rs.next(); //checks whether data is there or not.Moves the cursor to first line
				
				
				populateTable();
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			finally {
				try {
					if(rs!=null)
						rs.close();
					if(ps!=null)
						ps.close();
					if(con!=null)
						con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		}
		
		// TODO Auto-generated method stub
		
	}
	
	public void populateTable()
	{
		String bloodg=(String)cmbbloodgrp.getSelectedItem();
		Connection con=DBconnection.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select * from donor_receiverdetails where Bloodgroup=?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, bloodg);
			rs=ps.executeQuery();
			
			TableModel tableModel= (DbUtils.resultSetToTableModel(rs));
			table.setModel(tableModel);
		}
		
		catch(SQLException se) {
		     se.printStackTrace();
		}
		
		finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
}
}
