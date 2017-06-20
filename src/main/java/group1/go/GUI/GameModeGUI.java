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

public class GameModeGUI extends JFrame {

	private JPanel contentPane;
	private int MachinDificult = 0;
	private boolean machineON = false;
	private boolean pvp = false;
	private boolean dificutlSet = false;
	private JButton set_btn;
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
		
		JButton machine_btn = new JButton("IA - ON");
		machine_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				set_btn.setVisible(true);
				set_btn.setEnabled(true);
				machieneDificult.setVisible(true);
				machineON = true;
				pvp = false;
			}
		});
		machine_btn.setBounds(47, 64, 109, 48);
		contentPane.add(machine_btn);
		
		JButton pvp_btn = new JButton(" P v P");
		pvp_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_btn.setVisible(false);
				set_btn.setEnabled(false);
				machieneDificult.setVisible(false);
				machineON = false;
				pvp= true;
				
				
			}
		});
		pvp_btn.setBounds(247, 64, 109, 48);
		contentPane.add(pvp_btn);
		
		set_btn = new JButton("set");
		set_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(machieneDificult.getText()) < 10 && Integer.parseInt(machieneDificult.getText()) > 0){
					dificutlSet = true;
					MachinDificult = Integer.parseInt(machieneDificult.getText());
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
		machieneDificult.setVisible(false);
	}
}
