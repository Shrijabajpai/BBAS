package bbas.gui;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bbas.owner.OwnerFrame;
import bbas.worker.WorkerFrame;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Color;

public class LoginFrame extends JFrame implements ActionListener,KeyListener {

	private JPanel contentPane;
	private JTextField txtid;
	private final ButtonGroup roles=new ButtonGroup();
	private JRadioButton rdowner,rdworker;
	private JPasswordField userpass;
	private JLabel lblNewLabel_2;
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
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("LOGIN ");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 946, 694);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(510, 76, 433, 605);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Blood Bank Automation System Login");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setBounds(10, 54, 410, 37);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Calibri Light", Font.BOLD, 25));
		
		txtid = new JTextField();
		txtid.setBounds(298, 172, 102, 31);
		panel.add(txtid);
		txtid.setColumns(10);
		
		userpass = new JPasswordField();
		userpass.setBounds(298, 298, 102, 31);
		panel.add(userpass);
		
		rdowner = new JRadioButton("OWNER");
		rdowner.setBounds(80, 426, 103, 21);
		panel.add(rdowner);
		roles.add(rdowner);
		rdowner.setFont(new Font("Serif", Font.PLAIN, 20));
		
		rdworker = new JRadioButton("WORKER");
		rdworker.setBounds(244, 426, 121, 21);
		panel.add(rdworker);
		roles.add(rdworker);
		rdworker.setFont(new Font("Serif", Font.PLAIN, 20));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBounds(164, 515, 131, 43);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnsubmit = new JButton("LOGIN");
		btnsubmit.setBounds(0, 0, 131, 43);
		panel_1.add(btnsubmit);
		btnsubmit.setForeground(new Color(255, 255, 255));
		btnsubmit.setBackground(new Color(128, 0, 0));
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setFont(new Font("Calibri Light", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setBounds(54, 292, 121, 34);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 20));
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(54, 172, 89, 34);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/imagebg.jpg"));
		Image i2=ic.getImage().getScaledInstance(1544, 796, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		
		img = new JLabel("");
		img.setIcon(ic1);
		img.setBounds(0, 0, 1544, 796);
		contentPane.add(img);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		Login();
		
	}
		public void Login() {
		
        String id=txtid.getText();//to fetch value from textbox
		
		char[]pwd=userpass.getPassword(); //to fetch value from password field
		
		//convert char[] to String 
		String password=String.valueOf(pwd);
		
		String userRole="";
		if(rdowner.isSelected()==true)
		{
			userRole=rdowner.getText();
		}
		if(rdworker.isSelected()==true)
		{
			userRole=rdworker.getText();//to fetch value from RadioButton
		}
		
		
		if(id.isBlank()||password.isBlank()||userRole.isBlank())
		{
			JOptionPane.showMessageDialog(this, "Please enter UserId and Password with Role","Error",JOptionPane.ERROR_MESSAGE);
		}
		else 
		{
			if(id.equalsIgnoreCase("Owner") && password.equals("abc@123") && userRole.equalsIgnoreCase("Owner"))
			{
				//System.out.println("Hello Worker");
				OwnerFrame frame = new OwnerFrame();
				frame.setVisible(true);
				
				this.dispose();//to close login frame
			}
			else if(id.equalsIgnoreCase("Worker") && password.equals("def@123") && userRole.equalsIgnoreCase("Worker"))
			{
				//System.out.println("Hello Worker");
			   WorkerFrame frame = new WorkerFrame();
				frame.setVisible(true);
				
				this.dispose();//to close login frame
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invalid Credentials","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
			
		}
		
		// TODO Auto-generated method stub
		
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int keyCode=e.getKeyCode();
		if(keyCode==10 ) {
		Login();
		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}