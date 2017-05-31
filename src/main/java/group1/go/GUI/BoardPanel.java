package group1.go.GUI;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{
    private Color color = Color.BLACK;

    // setter for color
    // setter for draw

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.fillOval(400,400, getWidth(), getHeight());
    }
}

