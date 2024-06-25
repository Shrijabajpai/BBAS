package bbas.worker;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.dbinfo.DBconnection;
import bbas.gui.LoginFrame;

import javax.swing.JComboBox;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Blood_Bag_Details extends JFrame implements ActionListener,KeyListener
{

	private JPanel contentPane;
	private JTextField txtnoofbags;
	private JComboBox<String> cmbbg;
	private JPanel panel;
	private JLabel img;
	private JPanel panel_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Blood_Bag_Details frame = new Blood_Bag_Details();
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
	public Blood_Bag_Details() {
		setTitle("Add_Blood_Bag_Details");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1072, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/bloodbags.jpg"));
		Image i2=ic.getImage().getScaledInstance(1551, 877, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(516, 139, 515, 473);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NoOfBags");
		lblNewLabel.setBounds(45, 211, 114, 35);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		
		txtnoofbags = new JTextField();
		txtnoofbags.setBounds(377, 217, 106, 35);
		panel.add(txtnoofbags);
		txtnoofbags.addKeyListener(this);
		txtnoofbags.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(208, 406, 140, 35);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.setIcon(new ImageIcon(Blood_Bag_Details.class.getResource("/sms/images/updateicon.png")));
		btnsubmit.setBackground(new Color(255, 255, 255));
		btnsubmit.setBounds(0, 0, 140, 35);
		panel_1.add(btnsubmit);
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setFont(new Font("Serif", Font.PLAIN, 25));
		
		cmbbg = new JComboBox();
		cmbbg.setBounds(165, 53, 241, 39);
		panel.add(cmbbg);
		cmbbg.setBackground(new Color(255, 255, 255));
		cmbbg.setModel(new DefaultComboBoxModel(new String[] {"Select BloodGroup", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"}));
		cmbbg.setFont(new Font("Serif", Font.BOLD, 25));
		
		
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1549, 841);
		contentPane.add(img);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		Bgdetails();
		
	}
	
	public void Bgdetails() {
		String bloodbags=txtnoofbags.getText();
		String bloodgrp=(String)cmbbg.getSelectedItem();
		if(bloodbags.isBlank())
		{
			JOptionPane.showMessageDialog(this, "Please number of blood bags","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
		}
		
		if(bloodgrp.equalsIgnoreCase("Select BloodGroup")) {
			JOptionPane.showMessageDialog(this,"Please select bloodgroup type.","Error Message",JOptionPane.ERROR_MESSAGE);
		}
		else {
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			String insertQuery="insert into blood_bag_details( Bloodgroup, NoOfBags)values(?,?)";
			
			try {
			ps=con.prepareStatement(insertQuery);
			ps.setString(1, bloodgrp);
			ps.setString(2, bloodbags);
			
			
			int result=ps.executeUpdate();
			if(result>0) {
				JOptionPane.showMessageDialog(this, "Details added successfully.");
			     txtnoofbags.setText("");	 //cleans the data after you have submitted the data successfully
			     cmbbg.setSelectedItem("Select BloodGroup");
			 
			}
			}
			
			catch(SQLException se) {
				JOptionPane.showMessageDialog(this, bloodgrp+ " blood group already exists","Duplication error",JOptionPane.ERROR_MESSAGE);
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
	public void keyTyped(KeyEvent e) {
		char c= e.getKeyChar();
		if(e.getSource()==txtnoofbags)
        {
       	 if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE))
 			{
 				e.consume();
 				JOptionPane.showMessageDialog(this,"Only Digits allowed","Data error",JOptionPane.ERROR_MESSAGE);
 			}
        }
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==10)
		{
			Bgdetails();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

		
}
	


