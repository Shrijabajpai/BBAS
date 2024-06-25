package bbas.worker;

import java.awt.EventQueue;
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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.dbinfo.DBconnection;
import bbas.gui.LoginFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class BloodReceiveDetails extends JFrame implements ActionListener,ItemListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtbagdonated;
	private JTextField txtbg;
	private JComboBox<String> cmbphone;
	private JLabel img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BloodReceiveDetails frame = new BloodReceiveDetails();
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
	public BloodReceiveDetails() {
		setTitle("Blood_Request_Details");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1088, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(502, 144, 100, 37);
		contentPane.add(lblNewLabel);
		
		cmbphone = new JComboBox();
		cmbphone.setModel(new DefaultComboBoxModel(new String[] {"Select"}));
		cmbphone.addItemListener(this);
		FillCombo();
		cmbphone.setFont(new Font("Serif", Font.BOLD, 15));
		cmbphone.setBounds(867, 144, 100, 37);
		contentPane.add(cmbphone);
		
		JLabel lblNewLabel_1 = new JLabel("BagReceived");
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_1.setBounds(502, 300, 155, 37);
		contentPane.add(lblNewLabel_1);
		
		txtbagdonated = new JTextField();
		txtbagdonated.addKeyListener(this);
		txtbagdonated.setBounds(867, 300, 100, 37);
		contentPane.add(txtbagdonated);
		txtbagdonated.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Bloodgroup");
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_2.setBounds(502, 458, 155, 37);
		contentPane.add(lblNewLabel_2);
		
		txtbg = new JTextField();
		txtbg.setBounds(867, 458, 100, 37);
		contentPane.add(txtbg);
		txtbg.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Blood Request Details");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_3.setBounds(558, 27, 350, 53);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(651, 628, 155, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setBounds(0, 0, 155, 37);
		panel.add(btnsubmit);
		btnsubmit.setBackground(new Color(255, 255, 255));
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setIcon(new ImageIcon(BloodReceiveDetails.class.getResource("/sms/images/updateicon.png")));
		btnsubmit.setFont(new Font("Serif", Font.PLAIN, 25));
		
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/img.jpeg"));
		Image i2=ic.getImage().getScaledInstance(1554, 803, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1554, 803);
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
					if(type.equalsIgnoreCase("receiver")) {
				String phone=rs.getString("PhoneNo");//fetch the value from serial_number column of student_details table
				cmbphone.addItem(phone);//add the fetched value in the combo box
				
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
	public void keyTyped(KeyEvent e) {
		char c= e.getKeyChar();
		if(e.getSource()==txtbagdonated)
        {
       	 if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE))
 			{
 				e.consume();
 				JOptionPane.showMessageDialog(this,"Use only numeric value.","Data error",JOptionPane.ERROR_MESSAGE);
 			}
        }
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==10) {
			BloodReceiving();
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
			String phoneno=(String) cmbphone.getSelectedItem();
//			if(phoneno.equalsIgnoreCase("Select")) {
//				JOptionPane.showMessageDialog(this,"Please select the Phone number.","Error",JOptionPane.ERROR_MESSAGE);
//			}
//			
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select Bloodgroup from donor_receiverdetails where PhoneNo=?"; //in query we always write column name
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, phoneno);
				rs=ps.executeQuery();
				rs.next(); //checks whether data is there or not.Moves the cursor to first line
				String cbloodgrp=rs.getString("Bloodgroup");
				
				//to fetch value from above columns 
				txtbg.setText(cbloodgrp);
				
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

	@Override
	public void actionPerformed(ActionEvent e) {
	BloodReceiving();
	}
		public void BloodReceiving() {
			String phone=(String) cmbphone.getSelectedItem();
			String bloodgrp=txtbg.getText();
			String bagdonated=txtbagdonated.getText();
			
			if(bagdonated.isBlank()|| bloodgrp.isBlank()) {
				JOptionPane.showMessageDialog(this, "Please fill details","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			if(phone.equalsIgnoreCase("Select")) {
				JOptionPane.showMessageDialog(this, "Please select phone number","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			
			else {
				Connection con=DBconnection.openConnection();
				PreparedStatement insertps=null;
				PreparedStatement updateps=null;
				PreparedStatement selectps=null;
				ResultSet rs=null;
				String insertQuery="insert into blood_request_details (phoneno, Bagreceived, Bloodgroup, Date)values(?,?,?,?) ";
				String selectquery= "select * from blood_bag_details where Bloodgroup=?";
				String updateQuery="update blood_bag_details set NoOfBags=? where Bloodgroup=?";
				
				try{
					java.util.Date d=new java.util.Date();
					 long dt=d.getTime(); //to convert util date into sql date
					 java.sql.Date sqlDate=new java.sql.Date(dt);
					
					insertps=con.prepareStatement(insertQuery);
					insertps.setString(1, phone);
					insertps.setString(2,bagdonated);
					insertps.setString(3,bloodgrp);
					insertps.setDate(4, sqlDate);
					int result= insertps.executeUpdate();
					  if (result > 0) {
						  JOptionPane.showMessageDialog(this, "Details added successfully");
						  cmbphone.setSelectedItem("Select");
						  txtbagdonated.setText("");
						  txtbg.setText("");
						  
						  
						  selectps= con.prepareStatement(selectquery);
						  selectps.setString(1, bloodgrp);
						  rs= selectps.executeQuery();
						  rs.next();
						  int bag= rs.getInt("NoOfBags");
						  
		                    updateps = con.prepareStatement(updateQuery);
		                    updateps.setInt(1, bag-Integer.parseInt(bagdonated)); // Set the noofbags value from your text field
		                    updateps.setString(2,bloodgrp );
		                    int resultupdate= updateps.executeUpdate();
		                    if(resultupdate>0)
		                    {
		                    	JOptionPane.showMessageDialog(this,"Blood bags updated successfully");
		                    }
		                    
						  
					  }
				}
					  catch(SQLException se) {
						  se.printStackTrace();
					  }
					  finally {
						  try {
			                	if(rs!=null) {
			                		rs.close();
			                	}
			                    if (insertps != null) {
			                        insertps.close();
			                    }
			                    if (updateps != null) {
			                        updateps.close();
			                    }
			                    if (con != null) {
			                        con.close();
			                    }
			                } catch (SQLException se) {
			                    se.printStackTrace();
			                }
					  }
				}
			
		// TODO Auto-generated method stub
		
	}
}
