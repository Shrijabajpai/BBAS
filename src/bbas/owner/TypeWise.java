package bbas.owner;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import bbas.dbinfo.DBconnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class TypeWise extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JTable table;
    private JComboBox<String> cmbtype;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TypeWise frame = new TypeWise();
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
	public TypeWise() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Type_Wise_Details");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1091, 763);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("View all Donors and Receivers");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(528, 54, 392, 39);
		contentPane.add(lblNewLabel);
		
		cmbtype = new JComboBox();
		cmbtype.setFont(new Font("Serif", Font.BOLD, 20));
		cmbtype.setModel(new DefaultComboBoxModel(new String[] {"Select Type", "Donor", "Receiver"}));
		cmbtype.addItemListener(this);
		cmbtype.setBounds(668, 165, 136, 39);
		contentPane.add(cmbtype);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(396, 261, 681, 461);
		contentPane.add(scrollPane);
		
		table = new JTable();
		JTableHeader header=table.getTableHeader();
		header.setForeground(new Color(255,255,255)); //we can also write Color.Green
		header.setFont(new Font("Ariel", Font.BOLD, 20));
		header.setBackground(new Color(128, 0, 0));
		scrollPane.setViewportView(table);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		int state=e.getStateChange();
		if(state==1) {
			String type=(String)cmbtype.getSelectedItem();
			if(type.equalsIgnoreCase("Select Type")) {
				JOptionPane.showMessageDialog(this,"Please select the Type.","Error",JOptionPane.ERROR_MESSAGE);
			}
		
			Connection con=DBconnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_Query="select * from donor_receiverdetails where Type=?"; //in query we always write column name
			
			try {
				ps=con.prepareStatement(select_Query);
				ps.setString(1, type);
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

		// TODO Auto-generated method stub
		
	}
	public void populateTable()
	{
		String type=(String)cmbtype.getSelectedItem();
		Connection con=DBconnection.openConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select * from donor_receiverdetails where Type=?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, type);
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
