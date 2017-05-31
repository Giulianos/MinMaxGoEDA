package group1.go.GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class StartGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGUI frame = new StartGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton credits_btn = new JButton("Credits");
		credits_btn.setBounds(102, 184, 234, 33);
		credits_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(credits_btn);
	
		
		JButton rules_btn = new JButton("Rules");
		rules_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		rules_btn.setBounds(102, 140, 234, 33);
		contentPane.add(rules_btn);
		
		JButton start_btn = new JButton("Start");
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardGUI boardGUI = new BoardGUI();
				StartGUI.this.dispose();
			}
		});
		start_btn.setBounds(102, 96, 234, 33);
		contentPane.add(start_btn);
		
		JLabel gameName = new JLabel("GO");
		gameName.setFont(new Font("Showcard Gothic", Font.PLAIN, 31));
		gameName.setHorizontalAlignment(SwingConstants.CENTER);
		gameName.setBounds(102, 11, 234, 42);
		contentPane.add(gameName);
	}
}
