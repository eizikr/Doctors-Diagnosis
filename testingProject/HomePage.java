package testingProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class HomePage {

	JFrame frame;
	private static HomePage hp = null;
	
	/**
	 * Launch the application.
	 */
	public static void homePageFunc() {
		PatientDetails.patientDetailsFunc();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HomePage window = new HomePage();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	public static HomePage getHpInstance()
	{
		if(hp == null)
			hp = new HomePage();
		return hp;
	}
	
	/**
	 * Create the application.
	 */
	private HomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 380, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel logoutBtn = new JLabel("");
		logoutBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Login.main(null);
				frame.setVisible(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/logout2.png")));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				logoutBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/logout.png")));
			}
		});
		logoutBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/logout.png")));
		logoutBtn.setBounds(116, 262, 142, 49);
		frame.getContentPane().add(logoutBtn);
		
		JLabel diagnostBtn = new JLabel("");
		
		/*
		 * action that will be made when press the diagnose button
		 */
		diagnostBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PatientDetails.patientDetailsFunc();
				frame.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				diagnostBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/dodiagnost2.png")));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				diagnostBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/dodiagnost1.png")));

			}
		});
		diagnostBtn.setIcon(new ImageIcon(HomePage.class.getResource("/images/dodiagnost1.png")));
		diagnostBtn.setBounds(116, 165, 142, 49);
		frame.getContentPane().add(diagnostBtn);
		
		JLabel lblNewLabel_1 = new JLabel("\u05D0\u05D5\u05D8\u05D5-\u05D3\u05D5\u05E7");
		lblNewLabel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(95, 79, 183, 41);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblNewLabel.setIcon(new ImageIcon(HomePage.class.getResource("/images/hp1.PNG")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(0, 0, 377, 583);
		frame.getContentPane().add(lblNewLabel);
	}
}
