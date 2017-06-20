package group1.go.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import group1.go.Controller.Controller;
import group1.go.Model.Board;
import group1.go.Model.Constants;
import group1.go.Model.Game;
import group1.go.Model.State;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardGUI extends JFrame {

	private final int firstPositionX = 12;
	private final int firstPositionY = 19;
	private final double deltaY =  39.6;
	private final double deltaX = 40;
	private Controller controller;
	private JLayeredPane contentPane;
	private ImageIcon backgroundImg = new ImageIcon("go_board.png");
	private JLabel backGroundLabel = new JLabel();
	private JPanel backGroundPanel = new JPanel();
	private TilesPanel tilesPanel = new TilesPanel();
	private JLabel blackLabel;
	private JLabel whiteLabel;
	private JLabel moveLabel;
	private JLabel blackTextLabel;
	private JLabel whiteTextLabel;
	private JButton pass_btn;
	private JButton showTerritory_btn;
	private boolean territoryDisplay = false;;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardGUI frame = new BoardGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BoardGUI() {
		
		//Inicializo el frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(backgroundImg.getIconWidth() + 6, backgroundImg.getIconHeight() + 28);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setVisible(true);
		
		//Inicializo la contentPane
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Inicializo los botones y los agrego
		pass_btn = new JButton("Pass");
		pass_btn.setBounds(506, 30, 70, 40);
		pass_btn.setOpaque(false);
		pass_btn.setContentAreaFilled(false);
		pass_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.pass();
			}
		});
		contentPane.add(pass_btn,1);
		
		JButton exit_btn = new JButton("Exit");
		exit_btn.setBounds(506, 170, 70, 40);
		exit_btn.setOpaque(false);
		exit_btn.setContentAreaFilled(false);
		
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(exit_btn,0);
		
		JButton menu_btn = new JButton("Menu");
		menu_btn.setBounds(506, 100, 70, 40);
		menu_btn.setOpaque(false);
		menu_btn.setContentAreaFilled(false);
		menu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGUI start = new StartGUI();
				dispose();
			}
		});
		contentPane.add(menu_btn,0);
		showTerritory_btn = new JButton("Captures");
		showTerritory_btn.setBounds(506, 240, 70, 40);
		showTerritory_btn.setOpaque(false);
		showTerritory_btn.setContentAreaFilled(false);
		showTerritory_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(territoryDisplay){
					showTerritory_btn.setText("Captures");
					territoryDisplay = false;
				}else{
					territoryDisplay = true;
					showTerritory_btn.setText("Territory");
				}
				
			}
		});
		contentPane.add(showTerritory_btn,0);
		
		
		//seteo el cartel de blackTiles
		blackTextLabel = new JLabel("Black");
		blackTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		blackTextLabel.setBounds(516, 290 ,50 , 10);
		contentPane.add(blackTextLabel, 6);
		blackLabel = new JLabel("0");
		blackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		blackLabel.setBounds(516, 310, 50, 40);
		blackLabel.setBackground(Color.BLACK);
		blackLabel.setOpaque(true);
		blackLabel.setForeground(Color.WHITE);
		contentPane.add(blackLabel, 6);
		
		//seteo el cartel de whiteTiles 
		whiteTextLabel = new JLabel("White");
		whiteTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whiteTextLabel.setBounds(516, 360 ,50 , 10);
		contentPane.add(whiteTextLabel, 5);
		whiteLabel = new JLabel("0");
		whiteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whiteLabel.setBounds(516, 380, 50, 40);
		whiteLabel.setBackground(Color.WHITE);
		whiteLabel.setOpaque(true);
		whiteLabel.setForeground(Color.BLACK);
		contentPane.add(whiteLabel, 7);
		
		//seteo el moveLabel
		moveLabel = new JLabel("<html>Make a <br>move!!</html>");
		moveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moveLabel.setBounds(506, 450, 70, 40);
		contentPane.add(moveLabel, 5);
		//Seteo el background
		backGroundLabel.setIcon(backgroundImg);
		backGroundLabel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
		backGroundPanel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
		backGroundPanel.add(backGroundLabel);
		contentPane.add(backGroundPanel, -1);
		
		
		//Seteo el Tile panel para ir dibujando los circulos
		
		tilesPanel.setBounds(0, 0,backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
		tilesPanel.setOpaque(false);
		contentPane.add(tilesPanel, 4);
		tilesPanel.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				Point point = tilesPanel.getMousePosition();

				if((point.getX()> (deltaX*13)+ firstPositionX) || (point.getY()> (deltaY*13)+ firstPositionY) ){
					return;
				}
				double x = point.getX();
				int i = 0;
				double y = point.getY();
				int j = 0;
				while(x>0){
					x = x - deltaX;
					j++;
				}
				while(y>0){
					y = y - deltaY;
					i++;
				}
				System.out.println(i +" "+ j);
				controller.touch(i-1,j-1);
				
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}

	public void setController(Controller controller) {
		this.controller = controller;
		
	}
	
	public void drawBoard(State state){
		tilesPanel.drawBoard(state.getBoard());
		if(territoryDisplay){
			Game.countTerritory(state);
			blackLabel.setText(String.valueOf(state.getBlackTerritory()));
			whiteLabel.setText(String.valueOf(state.getWhiteTerritory()));
		}else{
			blackLabel.setText(String.valueOf(state.getWhiteTilesCapture()));
			whiteLabel.setText(String.valueOf(state.getBlackTilesCapture()));
		}
		
		moveLabel.setText("moved");
		this.repaint();
		
		
	}

	public void tileInPosition() {
		moveLabel.setText("<html>Tile in<br>the position</html>");
		
	}

	public void isSuicide() {
		moveLabel.setText("suicide");
		
	}

	public void KO() {
		moveLabel.setText("KO");
		
	}
	
	public void endGame(int blackScore, int whiteScore) {
		tilesPanel.setEnabled(false);
		pass_btn.setEnabled(false);
		EndGameGUI endGame = new EndGameGUI(blackScore, whiteScore, this);
		
	}

	
}
