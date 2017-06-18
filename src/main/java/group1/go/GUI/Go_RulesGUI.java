package group1.go.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class Go_RulesGUI extends JFrame {

	private JPanel contentPane;

	public Go_RulesGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel rules_lbl = new JLabel();
		rules_lbl.setText("<html>Go es un juego milenario de 2 jugadores<br>"
				+ "en el que se van ubicando fichas blancas "
				+ "y negras por turnos (un color para cada jugador)<br>"
				+ "Los tableros son de 9x9, 13x13,19x19<br>"
				+ "En cada turno, el jugador debe ubicar una ficha de su color en una intersección vacía. Una vez puesta<br>"
				+ "una pieza no puede moverse.<br>"
				+ "Regla del suicidio<br>"
				+ "No puede ponerse fichas en algún lugar que signifique el suicidio automático de esa ficha<br>"
				+ "Regla del KO<br>"
				+ "No pueden ponerse fichas que se coman simultáneamente de forma cíclica infinita (un jugador no puede<br> "
				+ "poner una ficha que haga que el tablero quede igual a como estaba al final de su último turno).<br>"
				+ "Ese jugador debe poner la ficha en cualquier otro lugar.<br>"
				+ "<html>");
		contentPane.add(rules_lbl, BorderLayout.CENTER);
	}

}
