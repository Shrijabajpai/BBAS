package bbas.worker;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.dbinfo.DBconnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.JobAttributes;
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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DonorInfo extends JFrame implements ActionListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextField txtaddress;
	private JTextField txtage;
	private JTextField txtbloodgrp;
	private JLabel lblNewLabel_7;
	private JComboBox<String> cmbgender;
	private JComboBox cmbtype;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonorInfo frame = new DonorInfo();
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
	public DonorInfo() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Donor(s) Information");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1077, 808);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(307, 49, 97, 35);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Serif", Font.BOLD, 15));
		txtname.setBounds(593, 54, 122, 30);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PhoneNo");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_1.setBounds(307, 136, 122, 30);
		contentPane.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setFont(new Font("Serif", Font.BOLD, 15));
		txtphone.setBounds(593, 136, 122, 31);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_2.setBounds(307, 218, 97, 30);
		contentPane.add(lblNewLabel_2);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Serif", Font.BOLD, 15));
		txtemail.setBounds(593, 218, 122, 31);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_3.setBounds(307, 303, 97, 35);
		contentPane.add(lblNewLabel_3);
		
		txtaddress = new JTextField();
		txtaddress.setFont(new Font("Serif", Font.BOLD, 15));
		txtaddress.setBounds(593, 303, 122, 30);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_4.setBounds(307, 391, 97, 35);
		contentPane.add(lblNewLabel_4);
		
		txtage = new JTextField();
		txtage.addActionListener(this);
		txtage.setFont(new Font("Serif", Font.BOLD, 15));
		txtage.setBounds(593, 391, 122, 31);
		contentPane.add(txtage);
		txtage.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_5.setBounds(307, 463, 97, 35);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Bloodgroup");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_6.setBounds(307, 558, 143, 37);
		contentPane.add(lblNewLabel_6);
		
		txtbloodgrp = new JTextField();
		txtbloodgrp.setFont(new Font("Serif", Font.BOLD, 15));
		txtbloodgrp.setBounds(593, 558, 122, 30);
		contentPane.add(txtbloodgrp);
		txtbloodgrp.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Type");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_7.setBounds(307, 653, 97, 30);
		contentPane.add(lblNewLabel_7);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setForeground(new Color(0, 0, 0));
		btnsubmit.setBackground(new Color(255, 255, 255));
		btnsubmit.setIcon(new ImageIcon(DonorInfo.class.getResource("/sms/images/updateicon.png")));
		btnsubmit.setFont(new Font("Serif", Font.PLAIN, 25));
		btnsubmit.setBounds(424, 741, 143, 30);
		contentPane.add(btnsubmit);
		
		ImageIcon ic=new ImageIcon(bbas.gui.LoginFrame.class.getResource("/sms/images/bbas1.jpg"));
		Image i2=ic.getImage().getScaledInstance(721, 651, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		JLabel bgimg = new JLabel("");
		bgimg.setBounds(798, 84, 721, 651);
		bgimg.setIcon(ic1);
		contentPane.add(bgimg);
		
		JLabel lblNewLabel_8 = new JLabel("DONOR(s) INFO");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_8.setBounds(811, 10, 242, 52);
		contentPane.add(lblNewLabel_8);
		
		cmbgender = new JComboBox();
		cmbgender.setFont(new Font("Serif", Font.BOLD, 15));
		cmbgender.setModel(new DefaultComboBoxModel(new String[] {"Select Gender", "Male", "Female", "Other"}));
		cmbgender.setBounds(593, 463, 122, 35);
		contentPane.add(cmbgender);
		
		cmbtype = new JComboBox();
		cmbtype.setFont(new Font("Serif", Font.BOLD, 16));
		cmbtype.setModel(new DefaultComboBoxModel(new String[] {"Select Type", "Donor"}));
		cmbtype.setBounds(593, 653, 122, 30);
		contentPane.add(cmbtype);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		donor();
		
	}
	
	public void donor(){
		//to fetch the value from textbox
		String name=txtname.getText();
		String phoneno=txtphone.getText();
		String email=txtemail.getText();
		String address=txtaddress.getText();
		String age=txtage.getText();
		String gender=(String) cmbgender.getSelectedItem();
		String bloodgrp=txtbloodgrp.getText();
		String type=(String) cmbtype.getSelectedItem();
		
		
		if(name.isBlank()||phoneno.isBlank()||email.isBlank()||address.isBlank()||age.isBlank()||bloodgrp.isBlank())
		{
			JOptionPane.showMessageDialog(this, "Please enter all details","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
		}
		
			if(gender.equalsIgnoreCase("Select Gender")) {
				JOptionPane.showMessageDialog(this,"Please select gender.","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			
			if(type.equalsIgnoreCase("Select Type")) {
				JOptionPane.showMessageDialog(this,"Please select type.","Error Message",JOptionPane.ERROR_MESSAGE);
			}
			
			if(phoneno.length()!=10) {
				 JOptionPane.showMessageDialog(this, "Phone number must be of 10 digits","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
			 }
				
		

		else {
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			String insertQuery="insert into donor_receiverdetails(PhoneNo, Name, Email, Address, Age, Gender, Bloodgroup, Type)values(?,?,?,?,?,?,?,?)";
			
			try {
			ps=con.prepareStatement(insertQuery);
			ps.setString(1, phoneno);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, address);
			ps.setString(5, age);
			ps.setString(6, gender);
			ps.setString(7, bloodgrp);
			ps.setString(8, type);
			
			int result=ps.executeUpdate();
			if(result>0) {
				JOptionPane.showMessageDialog(this, "Donor information added successfully.");
			     txtname.setText("");	 //cleans the data after you have submitted the data successfully
			     txtemail.setText("");
			     txtphone.setText("");
			     txtaddress.setText("");
			     txtage.setText("");
			     txtbloodgrp.setText("");
			     cmbgender.setSelectedItem("Select Gender");
			     cmbtype.setSelectedItem("Select Type");
			     
			 
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
		
		// TODO Auto-generated method stub
		
	}
	
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==10) {
			donor();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		char c=e.getKeyChar();

		if (e.getSource()==txtphone)
		{
			if(!((Character.isDigit(c))|| c==KeyEvent.VK_BACK_SPACE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "Use only numeric value","Data Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource()==txtage)
		{
			if(!((Character.isDigit(c))|| c==KeyEvent.VK_BACK_SPACE)) {
				e.consume();
				JOptionPane.showMessageDialog(this, "Use only numeric value","Data Error",JOptionPane.ERROR_MESSAGE);
			}
		}

		// TODO Auto-generated method stub
		
	}

		
		
	}



