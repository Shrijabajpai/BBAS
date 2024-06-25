package bbas.owner;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.gui.LoginFrame;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.Color;

public class OwnerFrame extends JFrame implements WindowListener ,ActionListener{

	private JPanel contentPane;
	private JLabel img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerFrame frame = new OwnerFrame();
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
	public OwnerFrame() {
		this.addWindowListener(this);
		setTitle("Owner");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 875, 626);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnreport = new JMenu("Report_Management");
		menuBar.add(mnreport);
		
		JMenuItem mibgdetails = new JMenuItem("BloodGroup_Wise_AllDetails");
		mibgdetails.setIcon(new ImageIcon(OwnerFrame.class.getResource("/sms/images/courses.png")));
		mibgdetails.addActionListener(this);
		mnreport.add(mibgdetails);
		
		JMenuItem midatewisedonation = new JMenuItem("Date_Wise_Donation");
		midatewisedonation.setIcon(new ImageIcon(OwnerFrame.class.getResource("/sms/images/date.png")));
		midatewisedonation.addActionListener(this);
		mnreport.add(midatewisedonation);
		
		JMenuItem midatewisereceiving = new JMenuItem("Date_Wise_Receiving");
		midatewisereceiving.setIcon(new ImageIcon(OwnerFrame.class.getResource("/sms/images/date.png")));
		midatewisereceiving.addActionListener(this);
		mnreport.add(midatewisereceiving);
		
		JMenuItem bgwisebb = new JMenuItem("BloodGroup_Wise_BloodBags");
		bgwisebb.setIcon(new ImageIcon(OwnerFrame.class.getResource("/sms/images/bags.png")));
		bgwisebb.addActionListener(this);
		mnreport.add(bgwisebb);
		
		JMenuItem mnviewall = new JMenuItem("View_All_Details");
		mnviewall.setIcon(new ImageIcon(OwnerFrame.class.getResource("/sms/images/table.png")));
		mnviewall.addActionListener(this);
		mnreport.add(mnviewall);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 197, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("You have successfully logged in as Owner.");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(36, 192, 549, 85);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(OwnerFrame.class.getResource("/sms/images/worker (2).jpeg")));
		lblNewLabel.setBounds(769, 49, 586, 652);
		contentPane.add(lblNewLabel);
		
//		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/owner (2).jpeg"));
//		Image i2=ic.getImage().getScaledInstance(1565, 926, Image.SCALE_DEFAULT);//for image scaling
//		ImageIcon ic1=new ImageIcon(i2);
//		
//		img = new JLabel("");
//		img.setIcon(ic1);
//		img.setBounds(0, 0, 1565, 926);
//		contentPane.add(img);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		//System.out.println("item is being clicked");
		String caption =e.getActionCommand();//return the text written on the menuitem
		
		switch(caption)
		{
		case "BloodGroup_Wise_AllDetails":
			BloodGroupWise bgw=new BloodGroupWise();
			bgw.setVisible(true);
			break;
			
		case "Date_Wise_Donation":
			DatewiseDonation dd=new DatewiseDonation();
			dd.setVisible(true);
			break;
			
		case "Date_Wise_Receiving":
			DatewiseRequest dr=new DatewiseRequest();
			dr.setVisible(true);
			break;
			
		case "BloodGroup_Wise_BloodBags":
			Bloodgroup_wise_bloodbags bwb=new Bloodgroup_wise_bloodbags ();
			bwb.setVisible(true);
			break;
			
		case "View_All_Details":
			TypeWise tw=new TypeWise();
			tw.setVisible(true);
			break;
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
		LoginFrame frame=new LoginFrame();
		frame.setVisible(true);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
