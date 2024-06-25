package bbas.worker;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.dbinfo.DBconnection;
import bbas.gui.LoginFrame;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class DeleteDonor extends JFrame implements ActionListener,KeyListener,ItemListener{

	private JPanel contentPane;
	private JTextField txtbg;
	private JLabel img;
    private JComboBox<String> cmbphoneno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDonor frame = new DeleteDonor();
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
	public DeleteDonor() {
		setTitle("Delete_Donor");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1093, 786);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(637, 213, 101, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("BloodGroup");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_2.setBounds(647, 418, 140, 33);
		contentPane.add(lblNewLabel_2);
		
		txtbg = new JTextField();
		txtbg.setFont(new Font("Serif", Font.BOLD, 15));
		txtbg.setBounds(932, 418, 122, 33);
		contentPane.add(txtbg);
		txtbg.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Delete Donor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(729, 60, 282, 42);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(787, 575, 140, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btndelete = new JButton("Delete");
		btndelete.setBounds(0, 0, 140, 42);
		panel.add(btndelete);
		btndelete.setBackground(new Color(255, 255, 255));
		btndelete.addActionListener(this);
		btndelete.addKeyListener(this);
		btndelete.setIcon(new ImageIcon(DeleteDonor.class.getResource("/sms/images/deleteicon.png")));
		btndelete.setFont(new Font("Serif", Font.PLAIN, 25));
		
		cmbphoneno = new JComboBox();
		cmbphoneno.setFont(new Font("Serif", Font.BOLD, 16));
		cmbphoneno.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		FillCombo();
		cmbphoneno.addItemListener(this);
		cmbphoneno.setBounds(932, 213, 122, 42);
		contentPane.add(cmbphoneno);
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/deletedonor.jpeg"));
		Image i2=ic.getImage().getScaledInstance(1544, 796, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1565, 803);
		contentPane.add(img);
		

		
		
	}
	public void FillCombo()
	{
		Connection con=DBconnection.openConnection();
		PreparedStatement ps=null;// compiled query reference 
		ResultSet rs=null; //resultant dataset reference 
		String selectQuery="select * from donor_receiverdetails"; //means all columns with all records(rows)
		
		try {
			ps=con.prepareStatement(selectQuery);
			rs=ps.executeQuery();//method is only called for select query
				while (rs.next()==true) {
					String type= rs.getString("Type");
					if(type.equalsIgnoreCase("donor")) {
				String phone=rs.getString("PhoneNo");//fetch the value from serial_number column of student_details table
				cmbphoneno.addItem(phone);//add the fetched value in the combo box
				
				
			}
		
				}
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		delete();
		
		
	}
	
	public void delete() {
		String bloodgrp=txtbg.getText();
		String phone= (String) cmbphoneno.getSelectedItem();
		
		if(phone.equalsIgnoreCase("Select")) {
			JOptionPane.showMessageDialog(this, "Please select phone number to delete.","Error",JOptionPane.ERROR_MESSAGE);
		}
		if(bloodgrp.isBlank()) {
			JOptionPane.showMessageDialog(this, "Please fill details to delete","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
		else {
				 Connection con=DBconnection.openConnection();
				 PreparedStatement ps=null;
				 String deleteQuery="delete from donor_receiverdetails where PhoneNo=? and Bloodgroup=?";
				 
				 //to prepare query and execute ,we use try-catch
				 try {
					 
					 ps=con.prepareStatement(deleteQuery);
					 ps.setString(1, phone);
					 ps.setString(2, bloodgrp);
					 int result=ps.executeUpdate();
					 //System.out.println("executed query output "+result);
					 
					 
					 //System.out.println(choice);
					 
					 
					 
					 
					 if (result>0 ) {
						 int choice =JOptionPane.showConfirmDialog(this, " Do you want to delete "+phone + " Phone number and "+bloodgrp+" BloodGroup.");
						 if(choice==0) {
						 JOptionPane.showMessageDialog(this, bloodgrp+" blood group and "+phone + " Phone number deleted successfully." ,"Information",JOptionPane.INFORMATION_MESSAGE);
						 cmbphoneno.setSelectedItem("Select");
						 txtbg.setText("");
						 
						 
					 }
					 }
					 }
				 
				 catch(SQLException se) {
					 se.printStackTrace();
				 }
				 finally {
						try {
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
					
					 
				 
			 
		
	
		
	

	
	@Override
	public void keyTyped(KeyEvent e) {
		

		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==10) {
			delete();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		int state=e.getStateChange();
		if(state==1) {
			String phone=(String) cmbphoneno.getSelectedItem();
//			if(phone.equalsIgnoreCase("Select PhoneNo")) {
//				JOptionPane.showMessageDialog(this,"Please select the Phone number.","Error",JOptionPane.ERROR_MESSAGE);
//			}
			
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from donor_receiverdetails where PhoneNo=?"; //in query we always write column name
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, phone);
				rs=ps.executeQuery();
				rs.next(); //checks whether data is there or not.Moves the cursor to first line
				String cbg=rs.getString("Bloodgroup");
				//to fetch value from above columns 
				//String cname=rs.getString(3); // we can also use column number
				
				txtbg.setText(cbg);
				
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
		// TODO Auto-generated method stub
		
	}
	
	
	}

