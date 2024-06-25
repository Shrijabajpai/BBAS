package bbas.owner;

import java.awt.Color;

import java.awt.EventQueue;import java.awt.Frame;
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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import bbas.dbinfo.DBconnection;
import bbas.gui.LoginFrame;
import net.proteanit.sql.DbUtils;


import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Bloodgroup_wise_bloodbags extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnshowtable;
	private JPanel panel;
	private JLabel img;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bloodgroup_wise_bloodbags frame = new Bloodgroup_wise_bloodbags();
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
	public Bloodgroup_wise_bloodbags() {
		setTitle("Bloodgroup_wise_Bloodbags");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1103, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnshowtable = new JButton("Show Table");
		btnshowtable.setIcon(new ImageIcon(Bloodgroup_wise_bloodbags.class.getResource("/sms/images/table.png")));
		btnshowtable.setBackground(new Color(255, 255, 255));
		btnshowtable.addActionListener(this);
		btnshowtable.addKeyListener(this);
		btnshowtable.setFont(new Font("Serif", Font.BOLD, 21));
		btnshowtable.setBounds(723, 185, 172, 41);
		contentPane.add(btnshowtable);
		
		panel = new JPanel();
		panel.setBackground(new Color(128, 0, 0));
		panel.setBounds(490, 46, 618, 712);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Bloodgroup Wise Blood bags");
		lblNewLabel.setBounds(106, 10, 453, 47);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 261, 568, 421);
		panel.add(scrollPane);
		
		table = new JTable();
		JTableHeader header=table.getTableHeader();
		header.setForeground(new Color(255,255,255)); //we can also write Color.Green
		header.setFont(new Font("Ariel", Font.BOLD, 20));
		header.setBackground(new Color(128, 0, 0));
		scrollPane.setViewportView(table);
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/bloodbags.jpg"));
		Image i2=ic.getImage().getScaledInstance(1544, 796, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1565, 803);
		contentPane.add(img);
	}
	public void populateTable()
	{
		Connection con=DBconnection.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String selectQuery="select * from blood_bag_details";
		
		try {
			ps=con.prepareStatement(selectQuery);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		populateTable();
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
			populateTable();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	}
