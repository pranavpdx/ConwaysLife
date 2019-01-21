import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LifePanel extends JPanel {
	boolean [][] cells;
	double width;
	double height;
	
	public LifePanel(boolean [][] cells) {
		this.cells = cells;
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length;
		height = (double) this.getHeight() / cells.length;
		
		g.setColor(Color.BLUE);
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				if(cells[row][col] == true) {
					g.fillRect((int)(Math.round(col * width)), (int)(Math.round(row * height)), 
							(int)Math.round(width) + 1, (int)Math.round(height) + 1);
				}
			}
		}
		for (int x = 0; x < cells.length + 1; x++) {
			g.drawLine((int)(Math.round(x*width)), 0, (int)(Math.round(x*width)), this.getHeight());
			
		}for (int y = 0; y < cells.length; y++) {
			g.drawLine(0, (int)Math.round(y*height), this.getWidth(), (int)Math.round(y*height));
		}
		
	}
}
