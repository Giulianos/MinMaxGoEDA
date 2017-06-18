package group1.go.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import group1.go.Controller.Controller;
import group1.go.Model.Game;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameModeGUI extends JFrame {

	private JPanel contentPane;
	private int MachinDificult = 0;
	private boolean machineON = false;
	private boolean pvp = false;
	private boolean dificutlSet = false;
	private JButton easy_btn;
	private JButton medium_btn;
	private JButton hard_btn;
	
	public GameModeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton machine_btn = new JButton("IA - ON");
		machine_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				easy_btn.setVisible(true);
				easy_btn.setEnabled(true);
				medium_btn.setEnabled(true);
				medium_btn.setVisible(true);
				hard_btn.setVisible(true);
				hard_btn.setEnabled(true);
				machineON = true;
				pvp = false;
			}
		});
		machine_btn.setBounds(47, 64, 109, 48);
		contentPane.add(machine_btn);
		
		JButton pvp_btn = new JButton(" P v P");
		pvp_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				easy_btn.setVisible(false);
				easy_btn.setEnabled(false);
				medium_btn.setEnabled(false);
				medium_btn.setVisible(false);
				hard_btn.setVisible(false);
				hard_btn.setEnabled(false);
				machineON = false;
				pvp= true;
				
				
			}
		});
		pvp_btn.setBounds(247, 64, 109, 48);
		contentPane.add(pvp_btn);
		
		easy_btn = new JButton("easy");
		easy_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MachinDificult = 1;
				dificutlSet = true;
			}
		});
		easy_btn.setBounds(47, 138, 109, 23);
		easy_btn.setVisible(false);
		easy_btn.setEnabled(false);
		contentPane.add(easy_btn);
		
		medium_btn = new JButton("Medium");
		medium_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MachinDificult = 2;
				dificutlSet = true;
			}
		});
		medium_btn.setEnabled(false);
		medium_btn.setVisible(false);
		medium_btn.setBounds(47, 172, 109, 23);
		contentPane.add(medium_btn);
		
		hard_btn = new JButton("Hard");
		hard_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MachinDificult = 3;
				dificutlSet = true;
			}
		});
		hard_btn.setBounds(47, 207, 109, 23);
		hard_btn.setVisible(false);
		hard_btn.setEnabled(false);
		contentPane.add(hard_btn);
		
		JButton start_btn = new JButton("START");
		start_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((machineON&&dificutlSet) || pvp){
					
					BoardGUI boardGUI = new BoardGUI();
					Controller controller;
					if(machineON){
							Game game = new Game();
								controller = new Controller(game, boardGUI, MachinDificult, true);
					}else{
						Game game = new Game();
						controller = new Controller(game, boardGUI, MachinDificult, false);
					}
					boardGUI.setController(controller);
					GameModeGUI.this.dispose();
				}
			}
				
		});
		start_btn.setBounds(268, 172, 88, 58);
		contentPane.add(start_btn);
	}
}
