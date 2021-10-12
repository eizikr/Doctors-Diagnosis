package testingProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.Canvas;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;

public class Login {

	private  String userPass [][] = {
										{"gilbe1" , "123!a456","123456789"},
										{"shpitzik" , "111222@q", "312202351"}	
								};
	private JFrame frame;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField userNameField;
	private JTextField idField;
	private JLabel userNameLabel;
	private JLabel passLabel;
	private JLabel idLabel;
	private JLabel lblNewLabel_1;
	private JLabel loginBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	//	HomePage.homePageFunc();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/images/icondoc.jpg")));
		frame.setTitle("\u05D0\u05D5\u05D8\u05D5-\u05D3\u05D5\u05E7                          \u05D0\u05D1\u05D7\u05D5\u05DF \u05E7\u05DC \u05D5\u05DE\u05D4\u05D9\u05E8  ");
		frame.setResizable(false);
		frame.setBounds(100, 100, 380, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		loginBtn = new JLabel("");
		loginBtn.setIcon(new ImageIcon(Login.class.getResource("/images/loginbtn2.png")));
		loginBtn.setBounds(66, 293, 96, 47);
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String un = userNameField.getText(), p = passwordField.getText(),ID =idField.getText();
				boolean userNameFlag=false,passwordFalg=false, idFlag=false,connectionFlag=false;
				StringBuilder messages = new StringBuilder();
				userNameFlag = checkUserName(un,messages);
				passwordFalg=checkPassword(p,messages);
				idFlag = checkId(ID,messages);
				
				if(messages.toString()!="")
					JOptionPane.showMessageDialog(frame, messages.toString(), "בעיית התחברות", JOptionPane.ERROR_MESSAGE);
				
				if(userNameFlag && passwordFalg && idFlag)
				{
					for(int i=0;i<userPass.length;i++)
					{
						if(un.equals(userPass[i][0])&&p.equals(userPass[i][1])&&ID.equals(userPass[i][2]))
						{
							HomePage.getHpInstance().frame.setVisible(true);
							frame.setVisible(false);
							connectionFlag = true;
						}
					}
					if(!connectionFlag)
						JOptionPane.showMessageDialog(frame, "שם המשתמש, הסיסמא או תעודת הזהות אינם תואמים!", "בעיית התחברות", JOptionPane.ERROR_MESSAGE);	
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setIcon(new ImageIcon(Login.class.getResource("/images/loginbtn.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginBtn.setIcon(new ImageIcon(Login.class.getResource("/images/loginbtn2.png")));
			}
		});
		frame.getContentPane().add(loginBtn);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 0, 0));
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setBounds(66, 280, 150, 12);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setBounds(66, 240, 150, 12);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(66, 199, 150, 12);
		frame.getContentPane().add(separator);
		
		userNameField = new JTextField();
		userNameField.setToolTipText("\u05DC\u05DB\u05DC \u05D4\u05D9\u05D5\u05EA\u05E8 2 \u05E1\u05E4\u05E8\u05D5\u05EA");
		userNameField.setForeground(Color.black);
		userNameField.setBorder(null);
		userNameField.setBackground(new Color(0,0,0,0));
		userNameField.setOpaque(false);
		userNameField.setFont(new Font("Arial Black", Font.BOLD, 14));
		userNameField.setColumns(10);
		userNameField.setBounds(66, 171, 150, 30);
		frame.getContentPane().add(userNameField);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("\u05E2\u05DC \u05D4\u05E1\u05D9\u05E1\u05DE\u05D0 \u05DC\u05D4\u05DB\u05D9\u05DC, \u05E1\u05E4\u05E8\u05D4, \u05D0\u05D5\u05EA, \u05D5\u05EA\u05D5 \u05DE\u05D9\u05D5\u05D7\u05D3 !#$%");
		passwordField.setForeground(Color.black);
		passwordField.setBorder(null);
		passwordField.setBackground(new Color(0,0,0,0));
		passwordField.setOpaque(false);
		passwordField.setFont(new Font("Arial Black", Font.BOLD, 14));
		passwordField.setBounds(66, 212, 150, 30);
		frame.getContentPane().add(passwordField);
		
		passLabel = new JLabel("\u05E1\u05D9\u05E1\u05DE\u05D0:");
		passLabel.setForeground(Color.BLACK);
		passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passLabel.setFont(new Font("Guttman-Aharoni", Font.BOLD, 18));
		passLabel.setBounds(226, 217, 54, 20);
		frame.getContentPane().add(passLabel);
		
		lblNewLabel_1 = new JLabel("\u05D0\u05D5\u05D8\u05D5-\u05D3\u05D5\u05E7");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 40));
		lblNewLabel_1.setBounds(45, 81, 292, 41);
		frame.getContentPane().add(lblNewLabel_1);
		
		idLabel = new JLabel("\u05EA\"\u05D6:");
		idLabel.setForeground(Color.BLACK);
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("Guttman-Aharoni", Font.BOLD, 18));
		idLabel.setBounds(226, 258, 29, 20);
		frame.getContentPane().add(idLabel);
		
		userNameLabel = new JLabel("\u05E9\u05DD \u05DE\u05E9\u05EA\u05DE\u05E9:");
		userNameLabel.setForeground(Color.BLACK);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setFont(new Font("Guttman-Aharoni", Font.BOLD, 18));
		userNameLabel.setBounds(220, 176, 109, 20);
		frame.getContentPane().add(userNameLabel);
		
		JLabel lblNewLabel = new JLabel("\u05D1\u05E8\u05D5\u05DB\u05D9\u05DD \u05D4\u05D1\u05D0\u05D9\u05DD");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 40));
		lblNewLabel.setBounds(67, 45, 237, 41);
		frame.getContentPane().add(lblNewLabel);
		
		idField = new JTextField();
		idField.setToolTipText("\u05EA\u05E2\u05D5\u05D3\u05EA \u05D6\u05D4\u05D5\u05EA \u05D1\u05EA 9 \u05E1\u05E4\u05E8\u05D5\u05EA");
		idField.setBorder(null);
		idField.setForeground(Color.black);
		idField.setBackground(new Color(0,0,0,0));
		idField.setFont(new Font("Arial Black", Font.BOLD, 14));
		idField.setColumns(10);
		idField.setOpaque(false);
		idField.setBounds(66, 253, 150, 30);
		frame.getContentPane().add(idField);
		
		JLabel loginBackgroundLabel = new JLabel("");
		loginBackgroundLabel.setIcon(new ImageIcon(Login.class.getResource("/images/log_pic.PNG")));
		loginBackgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginBackgroundLabel.setBounds(-39, -195, 425, 824);
		frame.getContentPane().add(loginBackgroundLabel);
	}
	
	
	/*
	 * check that the user name is valid
	 * */
	public boolean checkUserName(String uName,StringBuilder m)
	{
		int digCounter=0,userNameLength = uName.length() ;
		if(userNameLength>8 || userNameLength<6)
		{
			m.append("* שם משתמש חייב להכיל בין 6 ל 8 תווים\n");
			return false;
		}
		char nameArr[] = uName.toCharArray();
		
		for(int i=0;i<userNameLength;i++)
		{
			if(nameArr[i]>= '0' && nameArr[i]<='9')
				digCounter++;
		}
		if(digCounter>2)
		{
			m.append("* משתמש יכול להכיל לכל היותר 2 ספרות\n");
			return false;
		}
		return true;
	}
	
	/*
	 * check if the password is valid
	 */
	public boolean checkPassword(String pass,StringBuilder m)
	{
		char nameArr[] = pass.toCharArray();
		boolean digFlag=false,letterFlag=false,specialFlag=false;
		int passwordLength = pass.length();
		if(passwordLength>10 || passwordLength<8)
		{
			m.append("* סיסמא חייבת להכיל בין 8 ל 10 תווים\n");
			return false;
		}
		
		for(int i=0;i<passwordLength;i++)
		{
			if(nameArr[i]>= '0' && nameArr[i]<='9')
				digFlag = true;
			if(nameArr[i]>= 'A' && nameArr[i]<='z')
				letterFlag = true;
			if(nameArr[i]>= '!' && nameArr[i]<='/' || nameArr[i]>= ':' && nameArr[i]<='@')
				specialFlag = true;
		}
		
		if(!digFlag || !letterFlag || !specialFlag)
		{
			m.append("* הסיסמא שהוזנה לא תקינה על הסיסמא להכיל ספרה, אות ותו מיוחד!\n");
			return false;
		}
		return true;
	}
	
	
	/*
	 * check if the id is valid
	 */
	public boolean checkId(String uName, StringBuilder m)
	{
		char idArr[] = uName.toCharArray();
		if(uName.length()!=9)
		{
			m.append("* תעודת זהות חייבת להכיל 9 ספרות\n");
			return false;
		}
		for(int i=0;i<uName.length();i++)
			if(idArr[i]<'0' || idArr[i]>'9')
			{
				m.append("* תעודת זהות מורכת רק מספרות\n");
				return false;
			}
		return true;
	}
}
