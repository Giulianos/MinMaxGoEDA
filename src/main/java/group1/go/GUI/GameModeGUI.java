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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class GameModeGUI extends JFrame {

	private JPanel contentPane;
	private int MachinDificult = 0;
	private boolean machineON = false;
	private boolean pvp = false;
	private boolean dificutlSet = false;
	private JButton set_btn;
	private JLabel info_lbl;
	private JTextField machieneDificult;
	
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
		
		JButton machine_btn = new JButton("Machine");
		machine_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				set_btn.setVisible(true);
				set_btn.setEnabled(true);
				machieneDificult.setVisible(true);
				info_lbl.setVisible(true);
				machineON = true;
				pvp = false;
			}
		});
		machine_btn.setBounds(47, 64, 109, 48);
		contentPane.add(machine_btn);
		
		JButton pvp_btn = new JButton("Two Players");
		pvp_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_btn.setVisible(false);
				set_btn.setEnabled(false);
				machieneDificult.setVisible(false);
				info_lbl.setVisible(false);
				machineON = false;
				pvp= true;
				
				
			}
		});
		pvp_btn.setBounds(247, 64, 109, 48);
		contentPane.add(pvp_btn);
		
		set_btn = new JButton("Set Dificulty");
		set_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int number = Integer.parseInt(machieneDificult.getText());
					if(number < 10 && number > 0){
						dificutlSet = true;
						MachinDificult = number;
					}
						else{
							info_lbl.setText("<html>Set a valid number<br> between 1 and 10<html>");
						}
				}
				catch (Exception e) {
					info_lbl.setText("<html>Set a valid number<br> between 1 and 10<html>");
				}
			}
		});
		set_btn.setBounds(47, 138, 109, 23);
		set_btn.setVisible(false);
		set_btn.setEnabled(false);
		contentPane.add(set_btn);
		
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
		
		machieneDificult = new JTextField("1-10 Dificutlty");
		machieneDificult.setBounds(47, 172, 109, 20);
		contentPane.add(machieneDificult);
		machieneDificult.setColumns(10);
		
		info_lbl = new JLabel("<html>Choose the diffculty<br>between 1 and 10<html>");
		info_lbl.setBounds(47, 203, 109, 48);
		info_lbl.setVisible(false);
		contentPane.add(info_lbl);
		machieneDificult.setVisible(false);
	}
}
