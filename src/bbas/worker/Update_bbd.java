package bbas.worker;

import java.awt.EventQueue;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bbas.dbinfo.DBconnection;
import bbas.gui.LoginFrame;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Update_bbd extends JFrame implements KeyListener,ItemListener,ActionListener{

	private JPanel contentPane;
	private JTextField txtnoofbags;
	private JComboBox<String> cmbbg;
	private JLabel img;
	private JPanel panel;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_bbd frame = new Update_bbd();
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
	public Update_bbd() {
		setTitle("Update_Blood_Bag_Details");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1098, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(135, 206, 235));
		panel.setBounds(610, 135, 484, 518);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setBounds(185, 430, 145, 47);
		panel.add(btnupdate);
		btnupdate.setIcon(new ImageIcon(Update_bbd.class.getResource("/sms/images/submit.png")));
		btnupdate.setBackground(new Color(255, 255, 255));
		btnupdate.addActionListener(this);
		btnupdate.addKeyListener(this);
		btnupdate.setFont(new Font("Serif", Font.PLAIN, 25));
		
		JLabel lblNewLabel = new JLabel("NoOfBags");
		lblNewLabel.setBounds(39, 275, 114, 35);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		
		txtnoofbags = new JTextField();
		txtnoofbags.setBounds(368, 275, 106, 35);
		panel.add(txtnoofbags);
		txtnoofbags.addKeyListener(this);
		txtnoofbags.setColumns(10);
		
		cmbbg = new JComboBox();
		cmbbg.setBounds(148, 133, 223, 47);
		panel.add(cmbbg);
		cmbbg.setModel(new DefaultComboBoxModel(new String[] {"Select Bloodgroup"}));
		FillCombo();
		cmbbg.addItemListener(this);
		cmbbg.setFont(new Font("Serif", Font.BOLD, 25));
		
		lblNewLabel_1 = new JLabel("Update Blood Bag Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(53, 23, 421, 35);
		panel.add(lblNewLabel_1);
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/updatebbd.jpeg"));
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
		String selectQuery="select * from blood_bag_details"; //means all columns with all records(rows)
		
		try {
			ps=con.prepareStatement(selectQuery);
			rs=ps.executeQuery();//method is only called for select query
				while (rs.next()==true) {
				String bg=rs.getString("Bloodgroup");//fetch the value from bloodgroup column of blood_bag_details table
				cmbbg.addItem(bg);//add the fetched value in the combo box
				
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

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		// TODO Auto-generated method stub
		
	}
	
	public void update() {

		String bags=txtnoofbags.getText();
		String bg=(String)cmbbg.getSelectedItem();
		
		if(bags.isBlank())
		 {
			 JOptionPane.showMessageDialog(this, "Please fill details.");
		 }
		if(bg.equalsIgnoreCase("Select Bloodgroup"))
		{
			JOptionPane.showMessageDialog(this, "Please select Blood Group. ");
		}
		else {
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			String updateQuery="update blood_bag_details set  NoOfBags=? where Bloodgroup=?";
			try {
				ps=con.prepareStatement(updateQuery);
				ps.setString(1, bags);
				ps.setString(2, bg );
			
				int result=ps.executeUpdate();  //query will be executed
				if(result>0) {
					JOptionPane.showMessageDialog(this, "Blood Bag details updated successfully","Updation",JOptionPane.INFORMATION_MESSAGE);
						
				txtnoofbags.setText("");
				cmbbg.setSelectedItem("Select Bloodgroup");
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
	public void itemStateChanged(ItemEvent e) {
		int state=e.getStateChange();
		if(state==1) {
			String bg=(String) cmbbg.getSelectedItem();
//			if(bg.equalsIgnoreCase("Select Bloodgroup")) {
//				JOptionPane.showMessageDialog(this,"Please select the Bloodgroup.","Error",JOptionPane.ERROR_MESSAGE);
//			}
			
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from blood_bag_details where Bloodgroup=?"; //in query we always write column name
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, bg);
				rs=ps.executeQuery();
				rs.next(); //checks whether data is there or not.Moves the cursor to first line
				String cnoofbags=rs.getString("NoOfBags");

				//to fetch value from above columns 
				//String cname=rs.getString(3); // we can also use column number
				
				txtnoofbags.setText(cnoofbags);
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
