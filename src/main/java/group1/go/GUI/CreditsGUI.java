package group1.go.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CreditsGUI extends JFrame {

	private JPanel contentPane;


	public CreditsGUI() {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel credits_lbl = new JLabel("Credits: ");
		credits_lbl.setBounds(61, 21, 51, 37);
		contentPane.add(credits_lbl);
		
		JLabel name_lbl = new JLabel("New label");
		name_lbl.setBounds(61, 69, 316, 153);
		name_lbl.setText("<html>Agustin Lavarello<br>Francisco Perez Sammartino<br>Scaglioni Giuliano<br>Daniella Liberman<br>Palacci Julian</html>");
		contentPane.add(name_lbl);
	}
}
