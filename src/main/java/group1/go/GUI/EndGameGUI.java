package group1.go.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EndGameGUI extends JFrame {

	private JPanel contentPane;
	private BoardGUI board;
	private int blackScore;
	private int whiteScore;
	
	public EndGameGUI(int blackScore, int whiteScore, BoardGUI boardGUI){
		this.blackScore = blackScore;
		this.whiteScore = whiteScore;
		this.board = boardGUI;
		run();
	}
	public void run() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton exit_btn = new JButton("EXIT");
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exit_btn.setBounds(38, 183, 139, 48);
		contentPane.add(exit_btn);
		
		JButton menu_btn = new JButton("MENU");
		menu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StartGUI();
				board.dispose();
				dispose();
			}
		});
		menu_btn.setBounds(242, 183, 139, 48);
		contentPane.add(menu_btn);
		
		JLabel win_lbl = new JLabel();
		win_lbl.setBounds(127, 11, 159, 48);
		contentPane.add(win_lbl);
		
		JLabel score_lbl = new JLabel();
		score_lbl.setBounds(127, 87, 159, 48);
		contentPane.add(score_lbl);
		
		if(blackScore>whiteScore){
			win_lbl.setText("Black Won");
		}
		else if(blackScore<whiteScore){
			win_lbl.setText("White Won");
		}
		else{
			win_lbl.setText("It's a Tie");
		}
		score_lbl.setText("Black Score: " + blackScore + " White Score: " + whiteScore);
	
	}
}
