package testingProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.TextArea;
import javax.swing.JTextArea;
import java.awt.Window.Type;
import java.awt.Frame;

public class PatientDetails {

	private JFrame frame;
	private JTextField firstNameField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField lastNameField;
	private JLabel lblNewLabel_3;
	private JTextField idField;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField ageField;
	private JTextField phoneField;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField heightField;
	private JTextField weightField;
	private JLabel lblNewLabel_8;
	private JRadioButton femaleRB;
	private final ButtonGroup gender = new ButtonGroup();
	private JRadioButton maleRB;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_10_2;
	private JComboBox diariaCBOX;
	private JLabel lblNewLabel_10_3;
	private JComboBox vomitCBOX;
	private JLabel lblNewLabel_10_4;
	private JComboBox ethiopianCBOX;
	private JLabel lblNewLabel_10_6;
	private JComboBox drugsCBOX;
	private JLabel lblNewLabel_10_7;
	private JComboBox dietCBOX;
	private JComboBox pregnancyCBOX;
	private JComboBox smokeCBOX;
	private JLabel lblNewLabel_10_8;
	private JTextField wbcField;
	private JLabel wbcLabel;
	private JTextField lymphField;
	private JTextField feverField;
	private JTextField neutField;
	private JTextField rbcField;
	private JLabel lblRbc;
	private JLabel lblHct;
	private JTextField ureaField;
	private JLabel lblUreal;
	private JTextField hbField;
	private JLabel lblNeut_2;
	private JLabel lblCreatinine;
	private JTextField creatinineField;
	private JLabel lblIron;
	private JTextField ironField;
	private JTextField hctField;
	private JLabel lblHdl;
	private JTextField hdlField;
	private JTextField apField;
	private JLabel lblAp;
	TextArea docTextArea;
	JComboBox eastrenCBOX;
	
	private boolean anemiaFlag,dietFlag,bleedingFlag,hyperlypidemiaFlag,bloodCreationFlag,hematologyFlag,ironPoisonFlag,dehyderationFlag,infectionFlag,
					lackOfVitaminsFlag,viralFlag,maraWaysFlag,heartDiseaseFlag,BloodDiseaseFlag,liverDiseaseFlag,lowNutretionFlag,
					kidneyDiseaseFlag,lackOfIronFlag,diabetesFlag,muscleDiseaseFlag,ordinaryCnacer,stopSmockFlag,trisBalutFlag;
	
	private StringBuilder diagnostErrorMessage = new StringBuilder();
	private boolean gotFever,lungsDisease,smocking;
	private int cancerSuspectCounter,anemiaCounter;
	private boolean diagnostErrorFlag;
	private boolean questionsFlag = false;
	private Dictionary<String,Integer> sick;
	
	private Diagnostic diagObj;
	private JLabel homebtn;

	/**
	 * Launch the application.
	 */
	public static void patientDetailsFunc() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientDetails window = new PatientDetails();
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
	public PatientDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		frame.setResizable(false);
		frame.setTitle("\u05D4\u05D6\u05E0\u05EA \u05E4\u05E8\u05D8\u05D9 \u05D4\u05DE\u05D8\u05D5\u05E4\u05DC");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PatientDetails.class.getResource("/images/icondoc.jpg")));
		frame.getContentPane().setBackground(new Color(182,223,241,255));
		frame.setBounds(100, 100, 1200, 700);
		frame.setLocationRelativeTo(null);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		diagObj = new Diagnostic();
		
		initDiagnostDict();
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 278, 285, 276);
		ImageIcon pIcon = new ImageIcon(HomePage.class.getResource("/images/patientIcon.PNG"));
		Image pImage = pIcon.getImage();
		Image mpImage = pImage.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		pIcon = new ImageIcon(mpImage);
		lblNewLabel.setIcon(pIcon);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel diagbtn = new JLabel("");
		diagbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initDiagnostDict();
				diagObj.diagnosticText.setText("");
				try {
					checkPaitentInfo();
					insertPatientDetails();
					insertPatientSympthoms();
					gotFever = gotFever();
					smocking = gotSmocking();
					preformDiagnostics();
					allDiagnostics();	
					recommendations();
					//printDict();
					diagObj.frame.setVisible(true);
					saveToFile();
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(frame, ex.getMessage() , "שגיאה במילוי הפרטים", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(frame, ex.getMessage() , "שגיאה במילוי הפרטים", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				diagbtn.setIcon(new ImageIcon(PatientDetails.class.getResource("/images/diagbtn2.png")));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				diagbtn.setIcon(new ImageIcon(PatientDetails.class.getResource("/images/diagbtn1.png")));
			}
		});
		
		diagbtn.setIcon(new ImageIcon(PatientDetails.class.getResource("/images/diagbtn1.png")));
		diagbtn.setBounds(80, 565, 132, 57);
		frame.getContentPane().add(diagbtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 130, 1160, 17);
		frame.getContentPane().add(separator);
		
		firstNameField = new JTextField();
		firstNameField.setForeground(Color.BLACK);
		firstNameField.setBackground(Color.WHITE);
		firstNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		firstNameField.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameField.setFont(new Font("Arial", Font.BOLD, 14));
		firstNameField.setBounds(997, 62, 100, 25);
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("\u05E9\u05DD \u05E4\u05E8\u05D8\u05D9:");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(1103, 68, 67, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\u05E4\u05E8\u05D8\u05D9 \u05D4\u05DE\u05D8\u05D5\u05E4\u05DC");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(513, 23, 125, 17);
		frame.getContentPane().add(lblNewLabel_2);
		
		lastNameField = new JTextField();
		lastNameField.setForeground(Color.BLACK);
		lastNameField.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameField.setFont(new Font("Arial", Font.BOLD, 14));
		lastNameField.setColumns(10);
		lastNameField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lastNameField.setBackground(Color.WHITE);
		lastNameField.setBounds(796, 62, 100, 25);
		frame.getContentPane().add(lastNameField);
		
		lblNewLabel_3 = new JLabel("\u05E9\u05DD \u05DE\u05E9\u05E4\u05D7\u05D4:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(904, 68, 83, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		idField = new JTextField();
		idField.setForeground(Color.BLACK);
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setFont(new Font("Arial", Font.BOLD, 14));
		idField.setColumns(10);
		idField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		idField.setBackground(Color.WHITE);
		idField.setBounds(648, 62, 100, 25);
		frame.getContentPane().add(idField);
		
		lblNewLabel_4 = new JLabel("\u05EA\"\u05D6:");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(754, 68, 32, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("\u05D2\u05D9\u05DC:");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(606, 68, 32, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		ageField = new JTextField();
		ageField.setForeground(Color.BLACK);
		ageField.setHorizontalAlignment(SwingConstants.CENTER);
		ageField.setFont(new Font("Arial", Font.BOLD, 14));
		ageField.setColumns(10);
		ageField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ageField.setBackground(Color.WHITE);
		ageField.setBounds(560, 62, 40, 25);
		frame.getContentPane().add(ageField);
		
		phoneField = new JTextField();
		phoneField.setForeground(Color.BLACK);
		phoneField.setHorizontalAlignment(SwingConstants.CENTER);
		phoneField.setFont(new Font("Arial", Font.BOLD, 14));
		phoneField.setColumns(10);
		phoneField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		phoneField.setBackground(Color.WHITE);
		phoneField.setBounds(375, 62, 100, 25);
		frame.getContentPane().add(phoneField);
		
		lblNewLabel_6 = new JLabel("\u05DE\u05E1' \u05D8\u05DC\u05E4\u05D5\u05DF:");
		lblNewLabel_6.setForeground(Color.BLACK);
		lblNewLabel_6.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(483, 68, 67, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("\u05D2\u05D5\u05D1\u05D4:");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(325, 68, 40, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		heightField = new JTextField();
		heightField.setForeground(Color.BLACK);
		heightField.setHorizontalAlignment(SwingConstants.CENTER);
		heightField.setFont(new Font("Arial", Font.BOLD, 14));
		heightField.setColumns(10);
		heightField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		heightField.setBackground(Color.WHITE);
		heightField.setBounds(267, 62, 50, 25);
		frame.getContentPane().add(heightField);
		
		weightField = new JTextField();
		weightField.setForeground(Color.BLACK);
		weightField.setHorizontalAlignment(SwingConstants.CENTER);
		weightField.setFont(new Font("Arial", Font.BOLD, 14));
		weightField.setColumns(10);
		weightField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		weightField.setBackground(Color.WHITE);
		weightField.setBounds(145, 62, 60, 25);
		frame.getContentPane().add(weightField);
		
		lblNewLabel_8 = new JLabel("\u05DE\u05E9\u05E7\u05DC:");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(213, 68, 44, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("\u05DE\u05D9\u05DF:");
		lblNewLabel_8_1.setForeground(Color.BLACK);
		lblNewLabel_8_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_8_1.setBounds(112, 68, 23, 14);
		frame.getContentPane().add(lblNewLabel_8_1);
		
		femaleRB = new JRadioButton("\u05E0\u05E7\u05D1\u05D4");
		femaleRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pregnancyCBOX.setEnabled(true);
			}
		});
		femaleRB.setForeground(Color.BLACK);
		femaleRB.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		femaleRB.setContentAreaFilled(false);
		femaleRB.setHorizontalTextPosition(SwingConstants.LEFT);
		femaleRB.setFocusTraversalKeysEnabled(true);
		femaleRB.setFocusPainted(false);
		femaleRB.setBorder(null);
		gender.add(femaleRB);
		femaleRB.setBackground(new Color(182, 223, 241));
		femaleRB.setBounds(52, 85, 50, 23);
		frame.getContentPane().add(femaleRB);
		
		maleRB = new JRadioButton("\u05D6\u05DB\u05E8");
		maleRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pregnancyCBOX.setEnabled(false);
			}
		});
		maleRB.setSelected(true);
		maleRB.setForeground(Color.BLACK);
		gender.add(maleRB);
		maleRB.setHorizontalTextPosition(SwingConstants.LEFT);
		maleRB.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		maleRB.setFocusTraversalKeysEnabled(true);
		maleRB.setFocusPainted(false);
		maleRB.setContentAreaFilled(false);
		maleRB.setBorder(null);
		maleRB.setBackground(new Color(182, 223, 241));
		maleRB.setBounds(62, 64, 40, 23);
		frame.getContentPane().add(maleRB);
		
		lblNewLabel_9 = new JLabel("\u05E9\u05D0\u05DC\u05D5\u05DF \u05DE\u05E7\u05D3\u05D9\u05DD");
		lblNewLabel_9.setForeground(Color.BLACK);
		lblNewLabel_9.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 22));
		lblNewLabel_9.setBounds(513, 147, 125, 17);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("\u05DE\u05E2\u05E9\u05DF: ");
		lblNewLabel_10.setForeground(Color.BLACK);
		lblNewLabel_10.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(1082, 190, 46, 14);
		frame.getContentPane().add(lblNewLabel_10);
		
		smokeCBOX = new JComboBox();
		smokeCBOX.setBackground(Color.WHITE);
		smokeCBOX.setForeground(Color.BLACK);
		smokeCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		smokeCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		smokeCBOX.setBounds(1022, 186, 50, 22);
		frame.getContentPane().add(smokeCBOX);
		
		JLabel lblNewLabel_10_1 = new JLabel("\u05D9\u05D5\u05E6\u05D0 \u05E2\u05D3\u05D5\u05EA \u05D4\u05DE\u05D6\u05E8\u05D7:");
		lblNewLabel_10_1.setForeground(Color.BLACK);
		lblNewLabel_10_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_1.setBounds(844, 190, 125, 14);
		frame.getContentPane().add(lblNewLabel_10_1);
		
		eastrenCBOX = new JComboBox();
		eastrenCBOX.setBackground(Color.WHITE);
		eastrenCBOX.setForeground(Color.BLACK);
		eastrenCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		eastrenCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		eastrenCBOX.setBounds(784, 186, 50, 22);
		frame.getContentPane().add(eastrenCBOX);
		
		lblNewLabel_10_2 = new JLabel("\u05DE\u05E9\u05DC\u05E9\u05DC:");
		lblNewLabel_10_2.setForeground(Color.BLACK);
		lblNewLabel_10_2.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_2.setBounds(680, 190, 60, 14);
		frame.getContentPane().add(lblNewLabel_10_2);
		
		diariaCBOX = new JComboBox();
		diariaCBOX.setBackground(Color.WHITE);
		diariaCBOX.setForeground(Color.BLACK);
		diariaCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		diariaCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		diariaCBOX.setBounds(620, 186, 50, 22);
		frame.getContentPane().add(diariaCBOX);
		
		lblNewLabel_10_3 = new JLabel("\u05DE\u05E7\u05D9\u05D0:");
		lblNewLabel_10_3.setForeground(Color.BLACK);
		lblNewLabel_10_3.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_3.setBounds(543, 190, 40, 14);
		frame.getContentPane().add(lblNewLabel_10_3);
		
		vomitCBOX = new JComboBox();
		vomitCBOX.setBackground(Color.WHITE);
		vomitCBOX.setForeground(Color.BLACK);
		vomitCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		vomitCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		vomitCBOX.setBounds(483, 186, 50, 22);
		frame.getContentPane().add(vomitCBOX);
		
		lblNewLabel_10_4 = new JLabel("\u05D9\u05D5\u05E6\u05D0 \u05D0\u05EA\u05D9\u05D5\u05E4\u05D9\u05D4:");
		lblNewLabel_10_4.setForeground(Color.BLACK);
		lblNewLabel_10_4.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_4.setBounds(350, 190, 93, 14);
		frame.getContentPane().add(lblNewLabel_10_4);
		
		ethiopianCBOX = new JComboBox();
		ethiopianCBOX.setBackground(Color.WHITE);
		ethiopianCBOX.setForeground(Color.BLACK);
		ethiopianCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		ethiopianCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		ethiopianCBOX.setBounds(290, 186, 50, 22);
		frame.getContentPane().add(ethiopianCBOX);
		
		lblNewLabel_10_6 = new JLabel("\u05E6\u05D5\u05E8\u05DA \u05EA\u05E8\u05D5\u05E4\u05D5\u05EA:");
		lblNewLabel_10_6.setForeground(Color.BLACK);
		lblNewLabel_10_6.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_6.setBounds(140, 190, 93, 14);
		frame.getContentPane().add(lblNewLabel_10_6);
		
		drugsCBOX = new JComboBox();
		drugsCBOX.setBackground(Color.WHITE);
		drugsCBOX.setForeground(Color.BLACK);
		drugsCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		drugsCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		drugsCBOX.setBounds(80, 186, 50, 22);
		frame.getContentPane().add(drugsCBOX);
		
		lblNewLabel_10_7 = new JLabel("\u05D3\u05D9\u05D0\u05D8\u05D4:");
		lblNewLabel_10_7.setForeground(Color.BLACK);
		lblNewLabel_10_7.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_7.setBounds(1082, 239, 50, 14);
		frame.getContentPane().add(lblNewLabel_10_7);
		
		dietCBOX = new JComboBox();
		dietCBOX.setBackground(Color.WHITE);
		dietCBOX.setForeground(Color.BLACK);
		dietCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		dietCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		dietCBOX.setBounds(1022, 235, 50, 22);
		frame.getContentPane().add(dietCBOX);
		
		pregnancyCBOX = new JComboBox();
		pregnancyCBOX.setEnabled(false);
		pregnancyCBOX.setBackground(Color.WHITE);
		pregnancyCBOX.setForeground(Color.BLACK);
		pregnancyCBOX.setModel(new DefaultComboBoxModel(new String[] {"\u05DC\u05D0", "\u05DB\u05DF"}));
		pregnancyCBOX.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		pregnancyCBOX.setBounds(784, 235, 50, 22);
		frame.getContentPane().add(pregnancyCBOX);
		
		lblNewLabel_10_8 = new JLabel("\u05D4\u05E8\u05D9\u05D5\u05DF:");
		lblNewLabel_10_8.setForeground(Color.BLACK);
		lblNewLabel_10_8.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 16));
		lblNewLabel_10_8.setBounds(844, 239, 50, 14);
		frame.getContentPane().add(lblNewLabel_10_8);
		
		JLabel lblNewLabel_9_1 = new JLabel("\u05D4\u05D6\u05E0\u05EA \u05DE\u05D3\u05D3\u05D9\u05DD");
		lblNewLabel_9_1.setForeground(Color.BLACK);
		lblNewLabel_9_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 22));
		lblNewLabel_9_1.setBounds(522, 304, 116, 17);
		frame.getContentPane().add(lblNewLabel_9_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 276, 1160, 17);
		frame.getContentPane().add(separator_1);
		
		wbcField = new JTextField();
		wbcField.setHorizontalAlignment(SwingConstants.CENTER);
		wbcField.setForeground(Color.BLACK);
		wbcField.setFont(new Font("Arial", Font.BOLD, 14));
		wbcField.setColumns(10);
		wbcField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		wbcField.setBackground(Color.WHITE);
		wbcField.setBounds(563, 345, 50, 25);
		frame.getContentPane().add(wbcField);
		
		wbcLabel = new JLabel("WBC:");
		wbcLabel.setForeground(Color.BLACK);
		wbcLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		wbcLabel.setBounds(513, 347, 40, 19);
		frame.getContentPane().add(wbcLabel);
		
		JLabel lblNeut = new JLabel("Lymph:");
		lblNeut.setForeground(Color.BLACK);
		lblNeut.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblNeut.setBounds(829, 347, 60, 19);
		frame.getContentPane().add(lblNeut);
		
		lymphField = new JTextField();
		lymphField.setHorizontalAlignment(SwingConstants.CENTER);
		lymphField.setForeground(Color.BLACK);
		lymphField.setFont(new Font("Arial", Font.BOLD, 14));
		lymphField.setColumns(10);
		lymphField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lymphField.setBackground(Color.WHITE);
		lymphField.setBounds(899, 345, 50, 25);
		frame.getContentPane().add(lymphField);
		
		feverField = new JTextField();
		feverField.setHorizontalAlignment(SwingConstants.CENTER);
		feverField.setForeground(Color.BLACK);
		feverField.setFont(new Font("Arial", Font.BOLD, 14));
		feverField.setColumns(10);
		feverField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		feverField.setBackground(Color.WHITE);
		feverField.setBounds(420, 345, 50, 25);
		frame.getContentPane().add(feverField);
		
		JLabel lblFever = new JLabel("Fever:");
		lblFever.setForeground(Color.BLACK);
		lblFever.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblFever.setBounds(350, 347, 60, 19);
		frame.getContentPane().add(lblFever);
		
		JLabel lblNeut_1 = new JLabel("Neut:");
		lblNeut_1.setForeground(Color.BLACK);
		lblNeut_1.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblNeut_1.setBounds(648, 347, 50, 19);
		frame.getContentPane().add(lblNeut_1);
		
		neutField = new JTextField();
		neutField.setHorizontalAlignment(SwingConstants.CENTER);
		neutField.setForeground(Color.BLACK);
		neutField.setFont(new Font("Arial", Font.BOLD, 14));
		neutField.setColumns(10);
		neutField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		neutField.setBackground(Color.WHITE);
		neutField.setBounds(708, 345, 50, 25);
		frame.getContentPane().add(neutField);
		
		rbcField = new JTextField();
		rbcField.setHorizontalAlignment(SwingConstants.CENTER);
		rbcField.setForeground(Color.BLACK);
		rbcField.setFont(new Font("Arial", Font.BOLD, 14));
		rbcField.setColumns(10);
		rbcField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rbcField.setBackground(Color.WHITE);
		rbcField.setBounds(1022, 345, 50, 25);
		frame.getContentPane().add(rbcField);
		
		lblRbc = new JLabel("RBC:");
		lblRbc.setForeground(Color.BLACK);
		lblRbc.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblRbc.setBounds(977, 347, 50, 19);
		frame.getContentPane().add(lblRbc);
		
		lblHct = new JLabel("HCT:");
		lblHct.setForeground(Color.BLACK);
		lblHct.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblHct.setBounds(370, 401, 40, 19);
		frame.getContentPane().add(lblHct);
		
		ureaField = new JTextField();
		ureaField.setHorizontalAlignment(SwingConstants.CENTER);
		ureaField.setForeground(Color.BLACK);
		ureaField.setFont(new Font("Arial", Font.BOLD, 14));
		ureaField.setColumns(10);
		ureaField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ureaField.setBackground(Color.WHITE);
		ureaField.setBounds(563, 401, 50, 25);
		frame.getContentPane().add(ureaField);
		
		lblUreal = new JLabel("Urea:");
		lblUreal.setForeground(Color.BLACK);
		lblUreal.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblUreal.setBounds(503, 403, 50, 19);
		frame.getContentPane().add(lblUreal);
		
		hbField = new JTextField();
		hbField.setHorizontalAlignment(SwingConstants.CENTER);
		hbField.setForeground(Color.BLACK);
		hbField.setFont(new Font("Arial", Font.BOLD, 14));
		hbField.setColumns(10);
		hbField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hbField.setBackground(Color.WHITE);
		hbField.setBounds(708, 401, 50, 25);
		frame.getContentPane().add(hbField);
		
		lblNeut_2 = new JLabel("Hb:");
		lblNeut_2.setForeground(Color.BLACK);
		lblNeut_2.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblNeut_2.setBounds(666, 403, 32, 19);
		frame.getContentPane().add(lblNeut_2);
		
		lblCreatinine = new JLabel("Creatinine:");
		lblCreatinine.setForeground(Color.BLACK);
		lblCreatinine.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblCreatinine.setBounds(787, 403, 116, 19);
		frame.getContentPane().add(lblCreatinine);
		
		creatinineField = new JTextField();
		creatinineField.setHorizontalAlignment(SwingConstants.CENTER);
		creatinineField.setForeground(Color.BLACK);
		creatinineField.setFont(new Font("Arial", Font.BOLD, 14));
		creatinineField.setColumns(10);
		creatinineField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		creatinineField.setBackground(Color.WHITE);
		creatinineField.setBounds(899, 401, 50, 25);
		frame.getContentPane().add(creatinineField);
		
		lblIron = new JLabel("Iron:");
		lblIron.setForeground(Color.BLACK);
		lblIron.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblIron.setBounds(969, 403, 68, 19);
		frame.getContentPane().add(lblIron);
		
		ironField = new JTextField();
		ironField.setHorizontalAlignment(SwingConstants.CENTER);
		ironField.setForeground(Color.BLACK);
		ironField.setFont(new Font("Arial", Font.BOLD, 14));
		ironField.setColumns(10);
		ironField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ironField.setBackground(Color.WHITE);
		ironField.setBounds(1022, 401, 50, 25);
		frame.getContentPane().add(ironField);
		
		hctField = new JTextField();
		hctField.setHorizontalAlignment(SwingConstants.CENTER);
		hctField.setForeground(Color.BLACK);
		hctField.setFont(new Font("Arial", Font.BOLD, 14));
		hctField.setColumns(10);
		hctField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hctField.setBackground(Color.WHITE);
		hctField.setBounds(420, 401, 50, 25);
		frame.getContentPane().add(hctField);
		
		lblHdl = new JLabel("HDL:");
		lblHdl.setForeground(Color.BLACK);
		lblHdl.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblHdl.setBounds(370, 455, 40, 19);
		frame.getContentPane().add(lblHdl);
		
		hdlField = new JTextField();
		hdlField.setHorizontalAlignment(SwingConstants.CENTER);
		hdlField.setForeground(Color.BLACK);
		hdlField.setFont(new Font("Arial", Font.BOLD, 14));
		hdlField.setColumns(10);
		hdlField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		hdlField.setBackground(Color.WHITE);
		hdlField.setBounds(420, 455, 50, 25);
		frame.getContentPane().add(hdlField);
		
		apField = new JTextField();
		apField.setHorizontalAlignment(SwingConstants.CENTER);
		apField.setForeground(Color.BLACK);
		apField.setFont(new Font("Arial", Font.BOLD, 14));
		apField.setColumns(10);
		apField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		apField.setBackground(Color.WHITE);
		apField.setBounds(563, 455, 50, 25);
		frame.getContentPane().add(apField);
		
		lblAp = new JLabel("AP:");
		lblAp.setForeground(Color.BLACK);
		lblAp.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 16));
		lblAp.setBounds(520, 457, 30, 19);
		frame.getContentPane().add(lblAp);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(305, 494, 865, 17);
		frame.getContentPane().add(separator_1_1);
		
		JLabel lblNewLabel_9_1_1 = new JLabel("\u05D4\u05E2\u05E8\u05D5\u05EA \u05D4\u05E8\u05D5\u05E4\u05D0");
		lblNewLabel_9_1_1.setForeground(Color.BLACK);
		lblNewLabel_9_1_1.setFont(new Font("Guttman-Aharoni", Font.PLAIN, 22));
		lblNewLabel_9_1_1.setBounds(513, 517, 132, 17);
		frame.getContentPane().add(lblNewLabel_9_1_1);
		
		docTextArea = new TextArea();
		docTextArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		docTextArea.setFont(new Font("Arial Black", Font.BOLD, 18));
		docTextArea.setBounds(396, 540, 362, 93);
		frame.getContentPane().add(docTextArea);
		
		homebtn = new JLabel("");
		homebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				homebtn.setIcon(new ImageIcon(PatientDetails.class.getResource("/images/homebtn2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				homebtn.setIcon(new ImageIcon(PatientDetails.class.getResource("/images/homebtn.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				HomePage.getHpInstance().frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		homebtn.setIcon(new ImageIcon(PatientDetails.class.getResource("/images/homebtn.png")));
		homebtn.setBounds(1082, 576, 50, 35);
		frame.getContentPane().add(homebtn);
		
		JLabel lblNewLabel_11 = new JLabel("%");
		lblNewLabel_11.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_11.setBounds(474, 406, 29, 14);
		frame.getContentPane().add(lblNewLabel_11);
		
			
	}
	
	/*
	 * dictionary that will contains all diseases and counter for each one
	 */
	public void initDiagnostDict()
	{
		sick = new Hashtable<>();
		sick.put("anemia", 0);
		sick.put("cancer", 0);
		sick.put("diet", 0);
		sick.put("bleeding", 0);
		sick.put("hyperlypidemia", 0);
		sick.put("bloodCreation", 0);
		sick.put("hematology", 0);
		sick.put("ironPoison", 0);
		sick.put("dehyderation", 0);
		sick.put("infection", 0);
		sick.put("lackOfVitamins", 0);
		sick.put("viral", 0);
		sick.put("maraWays", 0);
		sick.put("heartDisease", 0);
		sick.put("BloodDisease", 0);
		sick.put("liverDisease", 0);
		sick.put("kidneyDisease", 0);
		sick.put("lackOfIron", 0);
		sick.put("muscleDisease", 0);
		sick.put("smoke", 0);
		sick.put("lungsDisease", 0);
		sick.put("tris", 0);
		sick.put("diabetes", 0);
		sick.put("meatOverDoes", 0);
		sick.put("useDrugs", 0);
		sick.put("lowNutretion", 0);
	}
	
	
	/*
	 * print dictionary
	 */
	public void printDict()
	{
	      for (Enumeration<String> k = sick.keys(); k.hasMoreElements();)
	      {
	    	  String a = k.nextElement();
	    	  System.out.println(a + " = " + sick.get(a));
	      }
	}
	
	
	/*
	 * activate all the diagnoses one by one
	 */
	public void preformDiagnostics() throws Exception
	{
		wbcDiagnostic();
		neutDiagnostic();
		LymphDiagnostic();
		rbcDiagnostic();
		hctDiagnostic();
		ureaDiagnostic();
		hbDiagnostic();
		creatinineDiagnostic();
		ironDiagnostic();
		hdlDiagnostic();
		apDiagnostic();
	}
	
	/*
	 * check that all the entered info in the patient personal details are correct and valid
	 */
	public void checkPaitentInfo() throws Exception
	{
		StringBuilder msg = new StringBuilder();
		msg.append("הוזנו פרטים שגויים:");
		String text = "null";
		text = firstNameField.getText();
		if(text.length()==0)
			msg.append("\n* עליך להזין שם פרטי!");
		for(int i=0;i<text.length();i++)
			if(!((text.charAt(i)>= 'א' && text.charAt(i)<= 'ת') || (text.charAt(i)>= 'A' && text.charAt(i)<= 'z') || (text.charAt(i) == ' ')))
			{
				msg.append("\n* שם פרטי מורכב מאותיות באנגלית/עברית בלבד!");
				break;
			}
		text = lastNameField.getText();
		if(text.length()==0)
			msg.append("\n* עליך להזין שם משפחה!");
		for(int i=0;i<text.length();i++)
			if(!((text.charAt(i)>= 'א' && text.charAt(i)<= 'ת') || (text.charAt(i)>= 'A' && text.charAt(i)<= 'z') || (text.charAt(i) == ' ')))
				{
					msg.append("\n* שם משפחה מורכב מאותיות באנגלית/עברית בלבד!");
					break;
				}
		text = idField.getText();
		if(text.length()!=9)
			msg.append("\n* תעודת זהות מורכבת מ-9 ספרות בדיוק!");
		for(int i=0;i<text.length();i++)
			if(!(text.charAt(i)>= '0' && text.charAt(i)<= '9'))
				{
					msg.append("\n* תעודת זהות מורכבת ממספרים בלבד!");
					break;
				}
		
		try {float age = Float.parseFloat(ageField.getText());
		if(age<0 || age > 120)
			msg.append("\n* שדה גיל אינו יכול להיות שלילי או גדול מ-120");
		}
		catch(NumberFormatException ex){msg.append("\n* שדה גיל ריק / מכיל ערכים לא תקינים!");}
		
		text = phoneField.getText();
		for(int i=0;i<text.length();i++)
			if(!(text.charAt(i)>= '0' && text.charAt(i)<= '9'))
				{
					msg.append("\n* מספר טלפון מורכב ממספרים בלבד!");
					break;
				}
		if(text.length()!=10)
			msg.append("\n* מספר טלפון מורכב מ-10 ספרות בדיוק!");
		
		try {float height = Float.parseFloat(heightField.getText());
		if(height<0)
			msg.append("\n* שדה גובה אינו יכול להיות שלילי ");
		}
		catch(NumberFormatException ex){msg.append("\n* שדה גובה ריק / מכיל ערכים לא תקינים!");}
		
		
		try {float weight = Float.parseFloat(weightField.getText());
		if(weight<0)
			msg.append("\n* שדה משקל אינו יכול להיות שלילי ");
		}
		catch(NumberFormatException ex){msg.append("\n* שדה משקל ריק / מכיל ערכים לא תקינים!");}
		
		if(!msg.toString().equals("הוזנו פרטים שגויים:"))
			throw new Exception(msg.toString());
	}
	
	//Diagnostic functions
	
	/*
	 * insert patient details to the diagnose screen 
	 */
	public void insertPatientDetails()
	{
		diagObj.diagnosticText.append("      ___________________________________ פרטי המטופל ____________________________________ \n");
		diagObj.diagnosticText.append("שם המטופל: " + firstNameField.getText() + " " + lastNameField.getText() + "\n" );
		diagObj.diagnosticText.append("תעודת זהות: " + idField.getText() + "\n" );
		diagObj.setPatientID(idField.getText());
		diagObj.diagnosticText.append("גיל: " + ageField.getText() + "\n" );
		diagObj.diagnosticText.append("מספר טלפון: " + phoneField.getText() + "\n" );
		diagObj.diagnosticText.append("גובה: " + heightField.getText() + "\n" );
		diagObj.diagnosticText.append("משקל: " + weightField.getText() + "\n" );
		diagObj.diagnosticText.append("מין: " + (maleRB.isSelected() ?  "זכר" : "נקבה") + "\n" );
	}
	
	/*
	 * insert patient symptoms to the diagnose screen 
	 */
	public void insertPatientSympthoms()
	{
		diagObj.diagnosticText.append("      _____________________________________ תסמינים _____________________________________ \n");
		diagObj.diagnosticText.append("חום: " + feverField.getText() + ",\t\t\t משלשל: " + (diariaCBOX.getSelectedIndex() == 0 ? "לא" : "כן") +
				 ",\t\t\t מקיא: " + (vomitCBOX.getSelectedIndex() == 0 ? "לא" : "כן") +"\n" );
		if(!docTextArea.getText().isBlank())
			diagObj.diagnosticText.append("הערות הרופא: " + docTextArea.getText() + "\n");
	}
	
	/*
	 * insert the doctor recommendations to the diagnose screen 
	 */
	public void recommendations()
	{	
		if(smokeCBOX.getSelectedIndex()==1 || dietCBOX.getSelectedIndex()==1 || drugsCBOX.getSelectedIndex()==1)
			diagObj.diagnosticText.append("\n      __________________________________ המלצות למטופל __________________________________ \n");
		if(smokeCBOX.getSelectedIndex()==1)
		{
			diagObj.diagnosticText.append("אבחנה: על פי השאלון המקדים המטופל הנ\"ל מעשן."+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: המלץ למטופל על הפסקת עישון! \n");
		}
		if(dietCBOX.getSelectedIndex()==1 && sick.get("lowNutretion") == 0  && sick.get("meatOverDoes")==0 )
		{
			diagObj.diagnosticText.append("אבחנה: על פי השאלון המקדים המטופל הנ\"ל בדיאטה."+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לתאם פגישה עם תזונאי! \n");
		}
		if(drugsCBOX.getSelectedIndex()==1 && sick.get("useDrugs") == 0)
		{
			diagObj.diagnosticText.append("אבחנה: בהתאם לשאלון המטופל נוטל תרופות שונות."+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: הפנייה לרופא המשפחה לצורך בדיקת התאמה בין התרופות!\n");
		}


	}
	
	/*
	 * check if the patient got fever
	 */
	public boolean gotFever() throws Exception
	{
		try {
			float fever = Float.parseFloat(feverField.getText());
			if(fever<0)
				throw new Exception("* שדה חום אינו יכול להיות שלילי");
			if(fever>=38 || fever<35)
				return true;
			return false;
		}
		catch(NumberFormatException ex){
			throw new NumberFormatException("שדה fever מכיל ערכים לא תקינים!");
		}
		
	}
	
//	public boolean gotLungsDisease()
//	{
//		if(lungsCBOX.getSelectedIndex()==1)
//			return true;
//		return false;
//		
//	}
//	
	
	
	public boolean gotSmocking()
	{
		if(smokeCBOX.getSelectedIndex()==1)
			return true;
		return false;
		
	}
	

	/*
	 * Perform diagnose to WBC value 
	 */
	public void wbcDiagnostic() throws Exception
	{
		//remember to notice the connection between blood disease and cancer and infection
		float age;
		float wbc;
		age = Float.parseFloat(ageField.getText());

		try {wbc = Float.parseFloat(wbcField.getText());
			if(wbc<0)
				throw new Exception("* שדה WBC אינו יכול להיות שלילי");
			}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה WBC מכיל ערכים לא תקינים!");}
		
		if( age >= 18 )
		{
			if(wbc>15000)		
			{
				sick.put("BloodDisease",sick.get("BloodDisease")+1);
				sick.put("cancer",sick.get("cancer")+1);
			}
			
			else if(wbc>11000 && gotFever())				//infectionFlag = true;
				sick.put("infection",sick.get("infection")+1);
			
			else if(wbc<1000)
				sick.put("cancer",sick.get("cancer")+1);
			
			else if(wbc<4500)
				sick.put("viral",sick.get("viral")+1);
		}
		else if(age>=4 && age<=17)
		{
			if(wbc>20000)
			{
				sick.put("BloodDisease",sick.get("BloodDisease")+1);
				sick.put("cancer",sick.get("cancer")+1);
			}
			
			else if(wbc>15500 && gotFever())
				sick.put("infection",sick.get("infection")+1);
			
			else if(wbc<1000)
				sick.put("cancer",sick.get("cancer")+1);
			
			else if(wbc<5500)
				sick.put("viral",sick.get("viral")+1);
		}
		
		else if(age>=0 && age<=3)
		{
			if(wbc>22500)
			{
				sick.put("BloodDisease",sick.get("BloodDisease")+1);
				sick.put("cancer",sick.get("cancer")+1);
			}
			
			else if(wbc>17500 && gotFever())
				sick.put("infection",sick.get("infection")+1);
			
			else if(wbc<1000)
				sick.put("cancer",sick.get("cancer")+1);
			
			else if(wbc<6000)
				sick.put("viral",sick.get("viral")+1);
		}	
		

	}
	
	
	/*
	 * Perform diagnose to neut value 
	 */
	public void neutDiagnostic() throws Exception
	{
		Float wbc , neut;
		try {wbc = Float.parseFloat(wbcField.getText());
		if(wbc==0)
			throw new Exception("* שדה WBC אינו יכול להיות שווה ל-0");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה WBC מכיל ערכים לא תקינים!");}
		
		try {neut = Float.parseFloat(neutField.getText());
		if(neut<0)
			throw new Exception("* שדה Neut אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה Neut מכיל ערכים לא תקינים!");}
		
		float prec = neut/(float)wbc*100;
		
		if(prec>54)
			sick.put("infection",sick.get("infection")+1);
		
		else if(prec<10)
			sick.put("cancer",sick.get("cancer")+1);
		
		else if(prec<28)
			sick.put("bloodCreation",sick.get("bloodCreation")+1);
	}
	
	
	/*
	 * Perform diagnose to Lymph value 
	 */
	public void LymphDiagnostic() throws Exception
	{
		Float wbc , lymph;
		
		try {wbc = Float.parseFloat(wbcField.getText());
		if(wbc==0)
			throw new Exception("* שדה WBC אינו יכול להיות שווה ל-0");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה WBC מכיל ערכים לא תקינים!");}
		
		try {lymph = Float.parseFloat(lymphField.getText());
		if(lymph<0)
			throw new Exception("* שדה Lymph אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה Lymph מכיל ערכים לא תקינים!");}
		
		float prec = lymph/(float)wbc*100;
		
		if(prec>52)
		{
			sick.put("cancer",sick.get("cancer")+1);
			sick.put("infection",sick.get("infection")+1);			
		}
		if(prec<36)
		{
			sick.put("bloodCreation",sick.get("bloodCreation")+1);
		}
	}
	
	/*
	 * Perform diagnose to RBC value 
	 */
	public void rbcDiagnostic() throws Exception
	{
		float rbc;
	
		try {rbc = Float.parseFloat(rbcField.getText());
		if(rbc<0)
			throw new Exception("* שדה RBC אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה RBC מכיל ערכים לא תקינים!");}
		
		if(rbc>6)
		{
			if(smocking)
				stopSmockFlag = true;		
			else
				{
				sick.put("bloodCreation",sick.get("bloodCreation")+1);
				sick.put("lungsDisease",sick.get("lungsDisease")+1);
				}
			
		}
		else if(rbc<4.5)
		{
			sick.put("anemia",sick.get("anemia")+1);
			sick.put("bleeding",sick.get("bleeding")+1);
		}
	}
	
	
	/*
	 * Perform diagnose to HCT value 
	 */
	public void hctDiagnostic() throws Exception
	{
		float prec;
		
		try {prec = Float.parseFloat(hctField.getText());
		if(prec<0 || prec>100)
			throw new Exception("* שדה HCT מייצג ערכים של אחוזים \nלכן ערכים אפשריים הם 0-100%");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה HCT מכיל ערכים לא תקינים!");}
		
		if(maleRB.isSelected())
		{
			if(smocking && prec>54)
				stopSmockFlag = true;		
			else if (prec< 37)
			{
				sick.put("anemia",sick.get("anemia")+1);
				sick.put("bleeding",sick.get("bleeding")+1);
			}
		}
		else
		{
			if(smocking && prec>47)
				stopSmockFlag = true;		
			else if (prec< 33)
			{
				sick.put("anemia",sick.get("anemia")+1);
				sick.put("bleeding",sick.get("bleeding")+1);
			}
		}
	}
	
	/*
	 * Perform diagnose to urea value 
	 */
	public void ureaDiagnostic() throws Exception
	{
		float urea;
		
		try {urea = Float.parseFloat(ureaField.getText());
		if(urea<0)
			throw new Exception("* שדה Urea אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה Urea מכיל ערכים לא תקינים!");}
		
		if(eastrenCBOX.getSelectedIndex()==1)
		{
			if(urea > 47.3  )
			{
				sick.put("dehyderation",sick.get("dehyderation")+1);
				sick.put("kidneyDisease",sick.get("kidneyDisease")+1);
				sick.put("meatOverDoes",sick.get("meatOverDoes")+1);

			}
		}
		else if(urea > 43 )
		{
			sick.put("dehyderation",sick.get("dehyderation")+1);
			sick.put("kidneyDisease",sick.get("kidneyDisease")+1);
			sick.put("meatOverDoes",sick.get("meatOverDoes")+1);
		}
		
		if(urea<17)
		{
			if(!(pregnancyCBOX.getSelectedIndex()==1))
			{
				sick.put("lowNutretion",sick.get("lowNutretion")+1);
				sick.put("liverDisease",sick.get("liverDisease")+1);
			}	
		}	
	}
	
	/*
	 * Perform diagnose to hb value 
	 */
	public void hbDiagnostic() throws Exception
	{
		//remember to check age altough its already checked before
		float age = Float.parseFloat(ageField.getText());
		
		float hb;
		
		try {hb = Float.parseFloat(hbField.getText());
		if(hb<0)
			throw new Exception("* שדה HB אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה HB מכיל ערכים לא תקינים!");}
		
		if((age>=18 && hb<12) || (age>=0 && age<=17 && hb<11.5))
		{
			sick.put("anemia",sick.get("anemia")+1);
			if(sick.get("bleeding") == 0)	//if not bleeding maybe its the other things
			{
				sick.put("lackOfIron",sick.get("lackOfIron")+1);
				sick.put("hematology",sick.get("hematology")+1);			
			}
			sick.put("bleeding",sick.get("bleeding")+1);
		}
	}
	
	/*
	 * Perform diagnose to creatinine value 
	 */
	public void creatinineDiagnostic() throws Exception
	{
		int age =  Integer.parseInt(ageField.getText());
		
		float creat;
		try {creat = Float.parseFloat(creatinineField.getText());
		if(creat<0)
			throw new Exception("* שדה Creatinine אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה Creatinine מכיל ערכים לא תקינים!");}
		
		if((age>=0 && age<=2 && creat > 0.5) || (age>=3 && age<=59 && creat > 1) || (age>=60 && creat > 1.2) )
		{
			if(!(vomitCBOX.getSelectedIndex()==1 || diariaCBOX.getSelectedIndex()==1 ))
			{
				sick.put("kidneyDisease",sick.get("kidneyDisease")+1);
				sick.put("muscleDisease",sick.get("muscleDisease")+1);
				sick.put("meatOverDoes",sick.get("meatOverDoes")+1);
			}
			
		}
		else if((age>=0 && age<=2 && creat < 0.2) || (age>=3 && age<=17 && creat < 0.5) || (age>=18 && creat < 0.6))
		{
			sick.put("lowNutretion",sick.get("lowNutretion")+1);
		}
		
		//make sure about lower muscle mass and muscle disease
	}
	
	/*
	 * Perform diagnose to iron value 
	 */
	public void ironDiagnostic() throws Exception
	{
		float iron;
		
		try {iron = Float.parseFloat(ironField.getText());
		if(iron<0)
			throw new Exception("* שדה Iron אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה Iron מכיל ערכים לא תקינים!");}
		
		if(maleRB.isSelected())
		{
			if(iron>160)
				sick.put("ironPoison",sick.get("ironPoison")+1);
			
			else if(iron<60 )
			{
				if(sick.get("bleeding") == 0)
					sick.put("lowNutretion",sick.get("lowNutretion")+1);
				sick.put("bleeding",sick.get("bleeding")+1);
			}

			
		}
		else
		{
			if(iron>128)
				sick.put("ironPoison",sick.get("ironPoison")+1);
			else if(iron<48 && !(pregnancyCBOX.getSelectedIndex()==1))
			{
				if(sick.get("bleeding") == 0)
					sick.put("lowNutretion",sick.get("lowNutretion")+1);
				sick.put("bleeding",sick.get("bleeding")+1);
			}
		}

	}
	
	/*
	 * Perform diagnose to hdl value 
	 */
	public void hdlDiagnostic() throws Exception
	{
		float hdl;
		
		try {hdl = Float.parseFloat(hdlField.getText());
		if(hdl<0)
			throw new Exception("* שדה HDL אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה HDL מכיל ערכים לא תקינים!");}
		
		if(ethiopianCBOX.getSelectedIndex()==0)		//not ethiopian
		{
			if(maleRB.isSelected())
			{
				if(hdl<29)
				{
					sick.put("heartDisease",sick.get("heartDisease")+1);
					sick.put("hyperlypidemia",sick.get("hyperlypidemia")+1);
					sick.put("diabetes",sick.get("diabetes")+1);
				}
			}
			else
			{
				if(hdl < 34)
				{
					sick.put("heartDisease",sick.get("heartDisease")+1);
					sick.put("hyperlypidemia",sick.get("hyperlypidemia")+1);
					sick.put("diabetes",sick.get("diabetes")+1);
				}
			}
		}
		else
		{
			if(maleRB.isSelected())
			{
				if(hdl<34.8)
				{
					sick.put("heartDisease",sick.get("heartDisease")+1);
					sick.put("hyperlypidemia",sick.get("hyperlypidemia")+1);
					sick.put("diabetes",sick.get("diabetes")+1);
				}
			}
			else
			{
				if(hdl < 40.8)
				{
					sick.put("heartDisease",sick.get("heartDisease")+1);
					sick.put("hyperlypidemia",sick.get("hyperlypidemia")+1);
					sick.put("diabetes",sick.get("diabetes")+1);
				}
			}
		}
		
	}
	
	/*
	 * Perform diagnose to AP value 
	 */
	
	public void apDiagnostic() throws Exception
	{
		float ap;
		
		try {ap = Float.parseFloat(apField.getText());
		if(ap<0)
			throw new Exception("* שדה AP אינו יכול להיות שלילי");
		}
		catch(NumberFormatException ex){throw new NumberFormatException("* שדה AP מכיל ערכים לא תקינים!");}
		
		{
			if(eastrenCBOX.getSelectedIndex()==1)
			{
				if(ap > 120)
				{
					if(pregnancyCBOX.getSelectedIndex()==0 ) 
					{
						sick.put("liverDisease",sick.get("liverDisease")+1);
						sick.put("maraWays",sick.get("maraWays")+1);
						sick.put("tris",sick.get("tris")+1);
						if(drugsCBOX.getSelectedIndex()==1)
							sick.put("useDrugs", sick.get("useDrugs")+1);
					}
				}
				else if(ap<60)
				{
					sick.put("lackOfVitamins",sick.get("lackOfVitamins")+1);
					sick.put("lowNutretion",sick.get("lowNutretion")+1);
				}
			}
			else 
			{
				if(ap > 90)
				{
					if(pregnancyCBOX.getSelectedIndex()==0 ) 
					{
						sick.put("liverDisease",sick.get("liverDisease")+1);
						sick.put("maraWays",sick.get("maraWays")+1);
						sick.put("tris",sick.get("tris")+1);
						if(drugsCBOX.getSelectedIndex()==1)
							sick.put("useDrugs", sick.get("useDrugs")+1);
					}
				}
				else if(ap<30)
				{
					sick.put("lackOfVitamins",sick.get("lackOfVitamins")+1);
					sick.put("lowNutretion",sick.get("lowNutretion")+1);
				}
			}
		}

	}//add to analysis
	
	/*
	 * get the highest diagnose counter 
	 */
	public int getSickDictMax()
	{
		int max = 0;
        for (Enumeration<Integer> k = sick.elements(); k.hasMoreElements();)
        {
        	int elem = k.nextElement() ;
            if(elem>max)
            	max = elem;
        }
		return max;
	}
	
	/*
	 * do analysis for each diagnose in the dictionary
	 */
	public void allDiagnostics()
	{
		diagObj.diagnosticText.append("\n     ___________________________________ אבחנה וטיפול ____________________________________ \n");
		int max = getSickDictMax();
		if(max!=1 && max!=0)
		{
			  diagObj.diagnosticText.append("אבחנות עיקריות: \n");
		      for (Enumeration<String> k = sick.keys(); k.hasMoreElements();)
		      {
		    	  String diagName = k.nextElement();
		    	  if(sick.get(diagName) == max)
		    		  sicknessAnalysis(diagName);
		      }
		      diagObj.diagnosticText.append("\n אבחנות משניות וחששות למעקב: \n");
		      for (Enumeration<String> k = sick.keys(); k.hasMoreElements();)
		      {
		    	  String diagName = k.nextElement();
		    	  if(sick.get(diagName) >= 1 && sick.get(diagName) !=max)
		    		  sicknessAnalysis(diagName);
		      }
		}
		else if(max == 1)
		{
		      diagObj.diagnosticText.append("\n אבחנות וחששות למעקב: \n");
		      for (Enumeration<String> k = sick.keys(); k.hasMoreElements();)
		      {
		    	  String diagName = k.nextElement();
		    	  if(sick.get(diagName) >= 1)
		    		  sicknessAnalysis(diagName);
		      }
		}
		
	}
	
	
	/*
	 * for each disease enter the diagnose and the treatment to the diagnose screen
	 */
	public void sicknessAnalysis(String sickness)
	{
		switch(sickness)
		{
		case "anemia":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על קיומה של אנמיה!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: שני כדורי 10 מ\"ג של B12 ביום למשך חודש"+ "\n" );	
			break;
		}
		
		case "cancer":
		{
			if(sick.get("cancer")>=2)
			{
				if(sick.get("cancer")>=3)
				{
					diagObj.diagnosticText.append("אבחנה: ערכי הבדיקות מצביעים על חשד להתפתחות תהליך סרטן הלימפופה"+ "\n" );
					diagObj.diagnosticText.append(" ---> טיפול: הפניה לבדיקה מקיפה, טיפול תרופתי: אנטרקטיניב - Entrectinib"+ "\n" );	
				}
				else
				{
					diagObj.diagnosticText.append("אבחנה: ערכי הבדיקות מצביעים על חשד להתפתחות תהליך סרטני"+ "\n" );
					diagObj.diagnosticText.append(" ---> טיפול: הפניה לבדיקה מקיפה, טיפול תרופתי: אנטרקטיניב - Entrectinib"+ "\n" );	
				}
			}
			break;
		}
		
		case "diet":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על מטופל בדיאטה"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לתאם פגישה עם תזונאי"+ "\n" );	
			break;

		}
		
		case "bleeding":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על קיום דימום!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: להתפנות בדחיפות לבית החולים \n");	
			break;

		}
		case "hyperlypidemia":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש להיפרליפידמיה"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לתאם פגישה עם תזונאי, כדור 5 מ\"ג של סימוביל ביום למשך שבוע"+ "\n" );	
			break;

		}
		
		case "bloodCreation":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על קיום הפרעה בתהליך יצירת הדם!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: כדור 10 מ\"ג של B12 ביום למשך חודש "
					+ ", כדור 5 מ\"ג של חומצה פולית ביום למשך חודש"+ "\n" );	
			break;

		}

		case "hematology":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשד להפרעה המטולוגית!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: זריקה של הורמון לעידוד ייצור תאי הדם האדומים!\n");
			break;

		}
		
		case "ironPoison":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על הרעלת ברזל!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: להתפנות לבית החולים!\n");
			break;

		}
		
		case "dehyderation":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש להתייבשות!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: להמליץ על מנוחה מוחלטת בשכיבה, והחזרת נוזלים בשתייה!\n");
			break;

		}
		
		case "infection":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על קיומו של זיהום!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: אנטיביוטיקה ייעודית"+ "\n" );
			break;

		}
		
		case "lackOfVitamins":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לחוסר בוויטמינים!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: הפנייה לבדיקת דם לזיהוי הוויטמינים החסרים"+ "\n" );	
			break;
		}
		
		case "viral":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לקיומה של מחלה ויראלית!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לנוח בבית"+ "\n" );	
			break;

		}
		
		case "maraWays":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לקיומם של מחלות בדרכי המרה!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: הפנייה לטיפול כירורגי!"+ "\n" );
			break;

		}
		
		case "heartDisease":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לקיומם של מחלות	 לב!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לתאם פגישה עם תזונאי!"+ "\n" );
			break;
		}
		
		case "BloodDisease":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על  קיומה של מחלת דם!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: שילוב של ציקלופוספאמיד וקורטיקוסרואידים"+ "\n" );	
			break;

		}
		
		case "liverDisease":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים סיכוי לקיומה של מחלת כבד!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: הפנייה לאבחנה ספציפית לצורך קביעת טיפול!\n");
			break;

		}
		
		case "kidneyDisease":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על סיכוי לקיומה של מחלת כליות!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: להמליץ למטופל על דרכים לאיזון רמת הסוכר בגוף! \n");
			break;

		}
		
		case "lackOfIron":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על מחסור בברזל בדם!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: שני כדורי 10 מ\"ג של B12 ביום למשך חודש!\n");
			break;

		}
		
		//add later the values from cbox
		
		
		case "tris":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לפעילות יתר של בלוטת התריס!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: Propylthiouracil להקטנת פעילות בלוטת התריס!\n");
			break;

		}
		
		case "diabetes":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לסכרת מבוגרים"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: התאמת אינסולין למטופל\n");
			break;

		}
		
		case "lowNutretion":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לתת תזונה!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לתאם פגישה עם תזונאי!\n");
			break;

		}
		////////
		case "useDrugs":
		{
			diagObj.diagnosticText.append("אבחנה: בהתאם לשאלון המטופל נוטל תרופות שונות."+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: הפנייה לרופא המשפחה לצורך בדיקת התאמה בין התרופות!\n");
			break;

		}
		
		case "muscleDisease":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לקיומה של מחלת שריר!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: שני כדורי 5 מ\"ג של כורכום c3 של אלטמן ביום למשך חודש!\n");
			break;

		}
		
		case "smoke":
		{
			diagObj.diagnosticText.append("אבחנה: בהתאם לשאלון המטופל מעשן."+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: להמליץ למטופל על הפסקת עישון!\n");
			break;

		}
		
		case "meatOverDoes":
		{
			diagObj.diagnosticText.append("אבחנה: בהתאם לשאלון המטופל צורך בשר באופן מוגבר."+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: לתאם פגישה עם תזונאי!\n");
			break;

		}
		
		case "lungsDisease":
		{
			diagObj.diagnosticText.append("אבחנה: תוצאות המדדים מצביעים על חשש לקיומה של מחלת ריאות!"+ "\n" );
			diagObj.diagnosticText.append(" ---> טיפול: להמליץ למטופל על הפסקת עישון והפנייה לצילום רנטגן של הריאות!\n");
			break;

		}
		
		}
	}
	
	
	
	public void saveToFile()
	{
		FileWriter patWriter = null;
		File patFile = new File("savings/"+idField.getText()+".txt");
		try {
			patWriter = new FileWriter(patFile, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter patPW = new PrintWriter(patWriter);
		patPW.println(new Date());
		patPW.println(diagObj.diagnosticText.getText());
		patPW.println("\n------------------------------------------------------------------------------\n");
		patPW.close();
	}
}
