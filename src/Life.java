/*
 * This is a simulation of the game Conway's Life
 * Author: Pranav Sharma
 * Date: 9/25/18
 */
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
	// creates an array representing all the cells on the JPanel
	boolean[][] cells = new boolean[10][10];
	// creates frame that all operations are done in
	JFrame frame = new JFrame("Conway Life's Simulation");
	// creates the panel of cells
	LifePanel panel = new LifePanel(cells);
	// creates the container that holds all the buttons in the program
	Container south = new Container();
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JButton fast = new JButton("Speed up");
	JButton slow = new JButton("Slow down");
	boolean running = false;
	int speed = 1;

	// constructor that creates the actual game
	public Life() {
		// sets properties of the frame
		frame.setSize(600, 600);
		frame.setLayout(new BorderLayout());

		// adds panel to frame and sets layout
		frame.add(panel, BorderLayout.CENTER);
		panel.addMouseListener(this);

		// sets layout and adds all buttons to the container named south
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

		// adds the container south to the frame
		frame.add(south, BorderLayout.SOUTH);

		// makes frame visible and be able to close
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// main method that calls the constructor
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

	// does operation when the mouse is released on a panel cell, switches the cell
	// from on to off or off to on
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
	// runs code for when a button is clicked
	public void actionPerformed(ActionEvent event) {
		// if step is clicked, runs the method step();
		if (event.getSource().equals(step)) {
			step();
		} else if (event.getSource().equals(start)) {// if start is clicked runs a thread that has the method step();
														// keep running
			if (running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}

		} else if (event.getSource().equals(stop)) {// if stop is clicked then stops running the method step();
			running = false;
		} else if (event.getSource().equals(slow)) {// makes the thread run slower if button slow is clicked
			if (speed < 11) {
				speed++;
			}
		} else if (event.getSource().equals(fast)) {// makes the thread run faster if button fast is clicked
			if (speed > 0) {
				speed--;
			}
		}
	}

	@Override
	// runs the thread that repeatedly runs the method step();
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

	// method that runs 1 generation of the program
	public void step() {
		// creates an identical array that represent the next generation
		boolean[][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row1 = 0; row1 < cells.length; row1++) {
			for (int col1 = 0; col1 < cells[0].length; col1++) {
				nextCells[row1][col1] = cells[row1][col1];
			}
		}

		// checks the amount of neighbors each cell has
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
				// checks the conditions of each cell to see if it will survive to the next round, if not assigns false, if so assigns true
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

		// makes the original array equal to the next generation array
		for (int row2 = 0; row2 < cells.length; row2++) {
			for (int col2 = 0; col2 < cells[0].length; col2++) {
				cells[row2][col2] = nextCells[row2][col2];
			}
		}

		// repaints the frame
		frame.repaint();
	}

}
