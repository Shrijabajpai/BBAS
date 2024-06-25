package bbas.worker;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.gui.LoginFrame;
import bbas.owner.BloodGroupWise;
import bbas.owner.Bloodgroup_wise_bloodbags;
import bbas.owner.DatewiseDonation;
import bbas.owner.DatewiseRequest;
import bbas.owner.TypeWise;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Color;

public class WorkerFrame extends JFrame implements WindowListener,ActionListener {

	private JPanel contentPane;
	private JLabel img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkerFrame frame = new WorkerFrame();
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
	public WorkerFrame() {
		this.addWindowListener(this);
		setExtendedState(this.MAXIMIZED_BOTH);
		setTitle("Worker");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1098, 812);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnbag = new JMenu("Blood_Bag_Mangement");
		mnbag.setFont(new Font("Serif", Font.PLAIN, 15));
		menuBar.add(mnbag);
		
		JMenuItem miaddbag = new JMenuItem("Add");
		miaddbag.addActionListener(this);
		miaddbag.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/addicon.png")));
		mnbag.add(miaddbag);
		
		JMenuItem miupdatebag = new JMenuItem("Update");
		miupdatebag.addActionListener(this);
		miupdatebag.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/submit.png")));
		mnbag.add(miupdatebag);
		
		JMenu mndonor = new JMenu("Donor_Management");
		mndonor.setFont(new Font("Serif", Font.PLAIN, 15));
		menuBar.add(mndonor);
		
		JMenuItem midonoradd = new JMenuItem("Add_Donor");
		midonoradd.addActionListener(this);
		midonoradd.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/addicon.png")));
		mndonor.add(midonoradd);
		
		JMenuItem midonorupdate = new JMenuItem("Update_Donor");
		midonorupdate.addActionListener(this);
		midonorupdate.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/submit.png")));
		mndonor.add(midonorupdate);
		
		JMenuItem midonordelete = new JMenuItem("Delete_Donor");
		midonordelete.addActionListener(this);
		midonordelete.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/deleteicon.png")));
		mndonor.add(midonordelete);
		
		JMenu mnreceiver = new JMenu("Receiver_Management");
		mnreceiver.setFont(new Font("Serif", Font.PLAIN, 15));
		menuBar.add(mnreceiver);
		
		JMenuItem mireceiveradd = new JMenuItem("Add_Receiver");
		mireceiveradd.addActionListener(this);
		mireceiveradd.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/addicon.png")));
		mnreceiver.add(mireceiveradd);
		
		JMenuItem mireceiverupdate = new JMenuItem("Update_Receiver");
		mireceiverupdate.addActionListener(this);
		mireceiverupdate.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/submit.png")));
		mnreceiver.add(mireceiverupdate);
		
		JMenuItem mireceiverdelete = new JMenuItem("Delete_Receiver");
		mireceiverdelete.addActionListener(this);
		mireceiverdelete.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/deleteicon.png")));
		mnreceiver.add(mireceiverdelete);
		
		JMenu mnblood = new JMenu("Blood_Management");
		mnblood.setFont(new Font("Serif", Font.PLAIN, 15));
		menuBar.add(mnblood);
		
		JMenuItem midonation = new JMenuItem("Blood_Donation_Details");
		midonation.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/courses.png")));
		mnblood.add(midonation);
		midonation.addActionListener(this);
		
		JMenuItem mirequest = new JMenuItem("Blood_Request_Details");
		mirequest.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/table.png")));
		mnblood.add(mirequest);
		mirequest.addActionListener(this);
		
		JMenu mnreport = new JMenu("Report_Management");
		mnreport.setFont(new Font("Serif", Font.PLAIN, 15));
		menuBar.add(mnreport);
		
		JMenuItem mialldetails = new JMenuItem("BloodGroup_Wise_AllDetails");
		mialldetails.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/table.png")));
		mialldetails.addActionListener(this);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("View_All_Details");
		mntmNewMenuItem.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/table.png")));
		mntmNewMenuItem.addActionListener(this);
		mnreport.add(mntmNewMenuItem);
		mnreport.add(mialldetails);
		
		JMenuItem midwd = new JMenuItem("Date_Wise_Donation");
		midwd.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/date.png")));
		midwd.addActionListener(this);
		mnreport.add(midwd);
		
		JMenuItem midwr = new JMenuItem("Date_Wise_Receiving");
		midwr.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/date.png")));
		midwr.addActionListener(this);
		mnreport.add(midwr);
		
		JMenuItem mibgw = new JMenuItem("BloodGroup_Wise_BloodBags");
		mibgw.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/bags.png")));
		mnreport.add(mibgw);
		mibgw.addActionListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 213, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("You have successfully logged in \r\nas Worker.");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(44, 180, 610, 64);
		contentPane.add(lblNewLabel);
		
//		JLabel bgimg = new JLabel("");
//		bgimg.setBounds(0, 0, 792, 476);
//		contentPane.add(bgimg);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(WorkerFrame.class.getResource("/sms/images/owner (2).jpeg")));
		lblNewLabel_1.setBounds(742, 49, 713, 652);
		contentPane.add(lblNewLabel_1);
		
//		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/worker (2).jpeg"));
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
		
		case "Add":
			Blood_Bag_Details bbdFrame=new Blood_Bag_Details();
			bbdFrame.setVisible(true);
			break;
			
		case "Update":
			Update_bbd ubbd=new Update_bbd();
			ubbd.setVisible(true);
			break;
			
		case "Add_Donor":
			DonorInfo donorFrame=new DonorInfo();
			donorFrame.setVisible(true);
			break;
			
		case "Update_Donor":
			UpdateDonor updateFrame=new UpdateDonor();
			updateFrame.setVisible(true);
			break;
			
		case "Delete_Donor":
			DeleteDonor deleteFrame=new DeleteDonor();
			deleteFrame.setVisible(true);
			break;
			
		case "Add_Receiver":
			ReceiverInfo receiverFrame=new ReceiverInfo();
			receiverFrame.setVisible(true);
			break;
			
		case "Update_Receiver":
			UpdateReceiver updateFrame1=new UpdateReceiver();
			updateFrame1.setVisible(true);
			break;
			
		case "Delete_Receiver":
			DeleteReceiver deleteFrame1=new DeleteReceiver();
			deleteFrame1.setVisible(true);
			break;
			
		case "Blood_Donation_Details":
			BloodDonateDetails bdd=new BloodDonateDetails();
			bdd.setVisible(true);
			break;
			
		case "Blood_Request_Details":
			BloodReceiveDetails brd=new BloodReceiveDetails();
			brd.setVisible(true);
			break;
			
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
		// TODO Auto-generated method stub
		
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
