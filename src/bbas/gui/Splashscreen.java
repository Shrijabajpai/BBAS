package bbas.gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;

public class Splashscreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splashscreen frame = new Splashscreen();
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
	public Splashscreen() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 775);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BLOOD BANK AUTOMATION SYSTEM");
		lblNewLabel.setForeground(new Color(64, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 35));
		lblNewLabel.setBounds(428, 329, 653, 76);
		contentPane.add(lblNewLabel);
		
		ImageIcon ic=new ImageIcon(LoginFrame.class.getResource("/sms/images/imagebg.jpg"));
		Image i2=ic.getImage().getScaledInstance(1566, 824, Image.SCALE_DEFAULT);//for image scaling
		ImageIcon ic1=new ImageIcon(i2);
		
		JLabel bgimg = new JLabel("");
		bgimg.setIcon(ic1);
		bgimg.setBounds(-12, -28, 1566, 824);
		contentPane.add(bgimg);
		loadFrame();
	}
	
	public void loadFrame()
	{
		Thread t=new Thread(new Runnable() { //thread using anonymous class
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					LoginFrame frame=new LoginFrame();
					frame.setVisible(true);
					Splashscreen.this.dispose();
				}
				catch(InterruptedException ie) {
					ie.printStackTrace();
					}
				}
				// TODO Auto-generated method stub
				
			
		});
		
				t.start(); {
				}
	}
}
