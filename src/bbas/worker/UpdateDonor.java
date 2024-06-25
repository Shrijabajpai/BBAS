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
import java.awt.Color;
import javax.swing.ImageIcon;

public class UpdateDonor extends JFrame implements ActionListener,ItemListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtmail;
	private JTextField txtaddress;
	private JTextField txtage;
	private JTextField txtgender;
	private JTextField txtbg;
	private JButton btnupdate;
	private JComboBox<String> cmbphoneno;
	private JLabel img;
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDonor frame = new UpdateDonor();
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
	public UpdateDonor() {
		setTitle("Update_Donor");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1107, 796);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbphoneno = new JComboBox();
		cmbphoneno.setFont(new Font("Serif", Font.BOLD, 20));
		cmbphoneno.setModel(new DefaultComboBoxModel(new String[] {"Select PhoneNo"}));
		cmbphoneno.addItemListener(this);
		FillCombo();
		cmbphoneno.setBounds(728, 100, 171, 42);
		contentPane.add(cmbphoneno);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(0,0,0));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(591, 176, 99, 42);
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setEditable(false);
		txtname.setFont(new Font("Serif", Font.BOLD, 15));
		txtname.setBounds(944, 176, 122, 32);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(new Color(0,0,0));
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_1.setBounds(591, 263, 99, 42);
		contentPane.add(lblNewLabel_1);
		
		txtmail = new JTextField();
		txtmail.setFont(new Font("Serif", Font.BOLD, 15));
		txtmail.setBounds(944, 260, 122, 32);
		contentPane.add(txtmail);
		txtmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setForeground(new Color(0,0,0));
		lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_2.setBounds(591, 350, 99, 32);
		contentPane.add(lblNewLabel_2);
		
		txtaddress = new JTextField();
		txtaddress.setFont(new Font("Serif", Font.BOLD, 15));
		txtaddress.setBounds(944, 354, 122, 32);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setForeground(new Color(0,0,0));
		lblNewLabel_3.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_3.setBounds(591, 433, 99, 32);
		contentPane.add(lblNewLabel_3);
		
		txtage = new JTextField();
		txtage.setFont(new Font("Serif", Font.BOLD, 15));
		txtage.setBounds(944, 438, 122, 31);
		contentPane.add(txtage);
		txtage.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setForeground(new Color(0,0,0));
		lblNewLabel_4.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_4.setBounds(591, 520, 99, 32);
		contentPane.add(lblNewLabel_4);
		
		txtgender = new JTextField();
		txtgender.setFont(new Font("Serif", Font.BOLD, 15));
		txtgender.setBounds(944, 524, 122, 32);
		contentPane.add(txtgender);
		txtgender.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("BloodGroup");
		lblNewLabel_5.setForeground(new Color(0,0,0));
		lblNewLabel_5.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel_5.setBounds(591, 602, 136, 28);
		contentPane.add(lblNewLabel_5);
		
		txtbg = new JTextField();
		txtbg.setFont(new Font("Serif", Font.BOLD, 15));
		txtbg.setBounds(944, 605, 122, 30);
		contentPane.add(txtbg);
		txtbg.setColumns(10);
		
		btnupdate = new JButton("Update");
		btnupdate.setIcon(new ImageIcon(UpdateDonor.class.getResource("/sms/images/submit.png")));
		btnupdate.setBackground(new Color(255, 255, 255));
		btnupdate.addActionListener(this);
		btnupdate.addKeyListener(this);
		btnupdate.setFont(new Font("Serif", Font.PLAIN, 25));
		btnupdate.setBounds(760, 685, 139, 45);
		contentPane.add(btnupdate);
		
		lblNewLabel_6 = new JLabel("Update Donor");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_6.setBounds(697, 10, 234, 42);
		contentPane.add(lblNewLabel_6);
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/download.jpeg"));
		Image i2=ic.getImage().getScaledInstance(1565, 926, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1565, 926);
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
		update();
		
	}
	
	public void update(){
		
		String name=txtname.getText();
		String email=txtmail.getText();
		String address=txtaddress.getText();
		String age=txtage.getText();
		String gender=txtgender.getText();
		String bloodgrp=txtbg.getText();
		String phone=(String)cmbphoneno.getSelectedItem();
		
		if(name.isBlank()|| email.isBlank()||address.isBlank()||age.isBlank()||gender.isBlank()||bloodgrp.isBlank())
		 {
			 JOptionPane.showMessageDialog(this, "Please fill all details.");
		 }
		if(phone.equalsIgnoreCase("Select PhoneNo"))
		{
			JOptionPane.showMessageDialog(this, "Please select Phone number ");
		}
		else {
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			String updateQuery="update donor_receiverdetails set  Name=?, Email=?, Address=?, Age=?, Gender=?, Bloodgroup=? where PhoneNo=?";
			try {
				ps=con.prepareStatement(updateQuery);
				ps.setString(1, name);
				ps.setString(2, email );
				ps.setString(3, address);
				ps.setString(4, age);
				ps.setString(5, gender);
				ps.setString(6, bloodgrp);
				ps.setString(7, phone);
				
				
				
				int result=ps.executeUpdate();  //query will be executed
				if(result>0) {
					JOptionPane.showMessageDialog(this, "Donor details updated successfully","Updation",JOptionPane.INFORMATION_MESSAGE);
				
					txtname.setText("");
					txtmail.setText("");
					txtaddress.setText("");
					txtage.setText("");
					txtgender.setText("");
					txtbg.setText("");
					cmbphoneno.setSelectedItem("Select PhoneNo");
					
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
		// TODO Auto-generated method stub

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
				String cname=rs.getString("Name");
				String cmail=rs.getString("Email");
				String caddress=rs.getString("Address");
				String cage=rs.getString("Age");
				String cgender=rs.getString("Gender");
				String cbg=rs.getString("Bloodgroup");
				//to fetch value from above columns 
				//String cname=rs.getString(3); // we can also use column number
				
				txtname.setText(cname);
				txtmail.setText(cmail);
				txtaddress.setText(caddress);
				txtage.setText(cage);
				txtgender.setText(cgender);
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

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode=e.getKeyCode();
			if(keyCode==10) {
				update();
			}
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}


