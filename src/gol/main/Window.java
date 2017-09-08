package gol.main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gol.graphics.Screen;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private String title;
	private int width, height;
	private boolean running = false;
	private boolean simulating = false;

	private Screen screen;

	final Object keyPressMonitor = new Object();

	public Window(int w, int h, String t) {
		this.width = w;
		this.height = h;
		this.title = t;
		running = true;

		Dimension dim = new Dimension(width, height);
		screen = new Screen(width, height);
		screen.setBounds(0, 0, width, height);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setPreferredSize(dim);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setTitle(title);
		add(screen);
		setVisible(true);

		getContentPane().addMouseListener(new MouseListener() {

			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e))
					Container.getSimulation().modifyCell(e.getX(), e.getY(), true);
				
				if (SwingUtilities.isRightMouseButton(e))
					Container.getSimulation().modifyCell(e.getX(), e.getY(), false);
			}

			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});

		addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					simulating = !simulating;
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Container.getSimulation().clear();
				}
			}
			
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {}
		});
		simulating = true;
	}

	public void repaint() {
		while (running) {
			screen.repaint();
			long before = System.currentTimeMillis();
			long diff = Container.MILLIS - (System.currentTimeMillis() - before);
			if (simulating) {
				Container.getSimulation().update();
			} else {
				System.out.println();
			}
			try {
				if (diff > 0)
					Thread.sleep(diff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}