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
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardGUI extends JFrame {

	private final int firstPositionX = 12;
	private final int firstPositionY = 19;
	private final double deltaY =  39.6;
	private final double deltaX = 40;
	
	private JLayeredPane contentPane;
	ImageIcon backgroundImg = new ImageIcon("C:/Users/agust/Downloads/go_board.png");
	ImageIcon btnBackground = new ImageIcon("C:/Users/agust/Downloads/btn_board.png");
	JLabel backGroundLabel = new JLabel();
	JPanel backGroundPanel = new JPanel();
	TilesPanel tilesPanel = new TilesPanel();
	//Controller controller;

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
		JButton pass_btn = new JButton("Pass");
		pass_btn.setBounds(506, 30, 70, 40);
		contentPane.add(pass_btn,0);
		
		JButton exit_btn = new JButton("Exit");
		exit_btn.setBounds(506, 170, 70, 40);
		exit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(exit_btn,0);
		
		JButton menu_btn = new JButton("Menu");
		menu_btn.setBounds(506, 100, 70, 40);
		menu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGUI start = new StartGUI();
				dispose();
			}
		});
		contentPane.add(menu_btn,0);
		
		//Seteo el background
		backGroundLabel.setIcon(backgroundImg);
		backGroundLabel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
		backGroundPanel.setBounds(0, 0, backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
		backGroundPanel.add(backGroundLabel);
		contentPane.add(backGroundPanel, -1);
		
		
		//Seteo el Tile panel para ir dibujando los circulos
		
		tilesPanel.setBounds(0, 0,backgroundImg.getIconWidth(), backgroundImg.getIconHeight());
		tilesPanel.setOpaque(false);
		contentPane.add(tilesPanel, 3);
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
					i++;
				}
				while(y>0){
					y = y - deltaY;
					j++;
				}
				//controller.touch(new Point(i-1,j-1);
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		//public void drawBoard(Set<TilesPositionGUI> set){
			//tilesPanel.set
			//tilesPanel.repaint();
		//}
		
		
	}
}