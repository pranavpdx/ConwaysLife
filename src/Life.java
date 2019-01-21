import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Life implements MouseListener, ActionListener, Runnable {
	boolean[][] cells = new boolean[17][17];
	JFrame frame = new JFrame("Conway Life's Simulation");
	LifePanel panel = new LifePanel(cells);
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton fast = new JButton("Speed up");
	JButton slow = new JButton("Slow down");
	boolean running = false;
	int speed = 1;

	public Life() {
		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		panel.addMouseListener(this);

		south.setLayout(new GridLayout(1, 5));
		south.add(step);
		step.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(fast);
		fast.addActionListener(this);
		south.add(slow);
		slow.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Life();
	}

	public void mouseClicked(MouseEvent event) {
	}

	public void mouseEntered(MouseEvent event) {
	}

	public void mouseExited(MouseEvent event) {
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		double width = (double) panel.getWidth() / cells[0].length;
		double height = (double) panel.getHeight() / cells.length;
		int colomn = Math.min(cells[0].length - 1, (int) (event.getX() / width));
		int row = Math.min(cells.length - 1, (int) (event.getY() / height));
		cells[row][colomn] = !cells[row][colomn];
		frame.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(step)) {
			step();
		} else if (event.getSource().equals(start)) {
			if (running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}

		} else if (event.getSource().equals(stop)) {
			running = false;
		} else if (event.getSource().equals(slow)) {
			if(speed < 11) {
				speed ++;
			}
		} else if (event.getSource().equals(fast)) {
			if(speed > 0) {
				speed --;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (running == true) {
			step();
			try {
				Thread.sleep(speed * 100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void step() {
		boolean[][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row1 = 0; row1 < cells.length; row1++) {
			for (int col1 = 0; col1 < cells[0].length; col1++) {
				nextCells[row1][col1] = cells[row1][col1];
			}
		}

		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				int n = 0;
				if (row > 0 && col > 0 && cells[row - 1][col - 1] == true) {// up left
					n++;
				}
				if (row > 0 && cells[row - 1][col] == true) {// up
					n++;
				}
				if (row > 0 && col < cells[0].length - 1 && cells[row - 1][col + 1] == true) {// up right
					n++;
				}
				if (col > 0 && cells[row][col - 1] == true) {// left
					n++;
				}
				if (col < cells[0].length - 1 && cells[row][col + 1] == true) {// right
					n++;
				}
				if (row < cells.length - 1 && col > 0 && cells[row + 1][col - 1] == true) {// down left
					n++;
				}
				if (row < cells.length - 1 && col < cells[0].length - 1 && cells[row + 1][col + 1] == true) {// down
																												// right
					n++;
				}
				if (row < cells.length - 1 && cells[row + 1][col] == true) {// down
					n++;
				}
				if (cells[row][col] == true) {
					if (n == 2 || n == 3) {
						nextCells[row][col] = true;
					} else {
						nextCells[row][col] = false;
					}
				} else if (cells[row][col] == false) {
					if (n == 3) {
						nextCells[row][col] = true;
					} else {
						nextCells[row][col] = false;
					}

				}

			}

		}

		for (int row2 = 0; row2 < cells.length; row2++) {
			for (int col2 = 0; col2 < cells[0].length; col2++) {
				cells[row2][col2] = nextCells[row2][col2];
			}
		}

		frame.repaint();
	}

}
