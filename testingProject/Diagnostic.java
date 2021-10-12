package testingProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Diagnostic {

	protected JFrame frame;
	protected JTextArea diagnosticText;
	private JScrollPane DiagnosticScrollPane;
	private String patientID;
	/**
	 * @wbp.nonvisual location=-31,639
	 */
	private final Button button = new Button("New button");
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Diagnostic window = new Diagnostic();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Diagnostic() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 700);
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(182,223,241,255));
		frame.getContentPane().setLayout(null);
		
		diagnosticText = new JTextArea();
		diagnosticText.setForeground(Color.BLACK);
		diagnosticText.setFont(new Font("Arial", Font.BOLD, 16));
		diagnosticText.setLineWrap(true);
		diagnosticText.setWrapStyleWord(true);
		diagnosticText.setEditable(false);
		diagnosticText.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		diagnosticText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		DiagnosticScrollPane = new JScrollPane(diagnosticText,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		DiagnosticScrollPane.setBounds(200, 80, 800,500 );
		frame.getContentPane().add(DiagnosticScrollPane);
		
		JLabel lblNewLabel = new JLabel("\u05EA\u05D5\u05E6\u05D0\u05D5\u05EA \u05D0\u05D1\u05D7\u05D5\u05DF \u05D4\u05DE\u05D8\u05D5\u05E4\u05DC");
		lblNewLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblNewLabel.setFont(new Font("Guttman-Aharoni", Font.BOLD, 30));
		lblNewLabel.setBounds(461, 11, 287, 58);
		frame.getContentPane().add(lblNewLabel);
		
		JButton exportBtn = new JButton("\u05E9\u05DE\u05D9\u05E8\u05EA \u05D0\u05D1\u05D7\u05D5\u05DF");
		exportBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFile();
				frame.setVisible(false);
				diagnosticText.setText("");

			}
		});
		exportBtn.setForeground(Color.BLACK);
		exportBtn.setFont(new Font("Guttman-Aharoni", Font.BOLD, 16));
		exportBtn.setBackground(Color.LIGHT_GRAY);
		exportBtn.setBounds(522, 603, 125, 32);
		frame.getContentPane().add(exportBtn);
		
		JButton exitBtn = new JButton("\u05D9\u05E6\u05D9\u05D0\u05D4");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				diagnosticText.setText("");
			}
		});
		exitBtn.setForeground(Color.BLACK);
		exitBtn.setFont(new Font("Guttman-Aharoni", Font.BOLD, 16));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(10, 603, 125, 32);
		frame.getContentPane().add(exitBtn);
		frame.setVisible(false);
		
		

	}
	
	
	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	/*
	 * export the diagnose to file in diagnostic Dictionary
	 */
	public void saveToFile()
	{
		FileWriter patWriter = null;
		File patFile = new File("diagnostics/"+patientID+".txt");
		try {
			patWriter = new FileWriter(patFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter patPW = new PrintWriter(patWriter);
		patPW.println(new Date());
		patPW.println(diagnosticText.getText());
		patPW.close();
	}
}
