package bbas.owner;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.toedter.calendar.JDateChooser;

import bbas.dbinfo.DBconnection;
import net.proteanit.sql.DbUtils;


import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class DatewiseDonation extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JTable table;
	private JDateChooser datec;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatewiseDonation frame = new DatewiseDonation();
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
	public DatewiseDonation() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Datewise_Donation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1097, 774);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date Wise Donation");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(649, 24, 333, 38);
		contentPane.add(lblNewLabel);
		
		datec = new JDateChooser();
		datec.setDateFormatString("yyyy-MM-dd");
		datec.setBounds(368, 163, 204, 59);
		contentPane.add(datec);
		
		JButton btngo = new JButton("Go");
		btngo.addActionListener(this);
		btngo.addKeyListener(this);
		btngo.setFont(new Font("Serif", Font.BOLD, 25));
		btngo.setBounds(950, 163, 123, 59);
		contentPane.add(btngo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 280, 555, 435);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		populateTable();
		// TODO Auto-generated method stub
		
	}
	public void populateTable() {
		java.util.Date d=datec.getDate(); //to fetch date from JDateChooser
		
		if(d==null)
		{
			JOptionPane.showMessageDialog(this, "Please selct the date","Error Message",JOptionPane.ERROR_MESSAGE);
		}
		else {
			long dt=d.getTime();
			
			java.sql.Date sqlDate=new java.sql.Date(dt);
			
			//System.out.println("SQl Date is "+dt);
			
			
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String sql="select * from blood_donate_details where Date=?";
			
		//System.out.println("Util Date is "+d);
		try {
		
		
		ps=con.prepareStatement(sql);
		ps.setDate(1,sqlDate);
		rs=ps.executeQuery();
		
		TableModel tableModel=DbUtils.resultSetToTableModel(rs);
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode=e.getKeyCode();
		if(keyCode==10) {
			populateTable();
			scrollPane.setViewportView(table);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
