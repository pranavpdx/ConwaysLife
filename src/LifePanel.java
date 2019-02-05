import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LifePanel extends JPanel {
	// creates variables for the class
	boolean[][] cells;
	double width;
	double height;

	// sets the class's version of the array equal to given array to this class
	public LifePanel(boolean[][] cells) {
		this.cells = cells;
	}

	//  paints panel with the grid squares
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double) this.getWidth() / cells[0].length;
		height = (double) this.getHeight() / cells.length;
		g.setColor(Color.BLUE);
		// makes each grid square
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				if (cells[row][col] == true) {
					g.fillRect((int) (Math.round(col * width)), (int) (Math.round(row * height)),
							(int) Math.round(width) + 1, (int) Math.round(height) + 1);
				}
			}
		}
		// draws lines to separate columns
		for (int x = 0; x < cells.length + 1; x++) {
			g.drawLine((int) (Math.round(x * width)), 0, (int) (Math.round(x * width)), this.getHeight());
		}
		// draws lines to separate rows
		for (int y = 0; y < cells.length; y++) {
			g.drawLine(0, (int) Math.round(y * height), this.getWidth(), (int) Math.round(y * height));
		}
	}
}
