package gol.graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

import gol.main.Container;

@SuppressWarnings("serial")
public class Screen extends JLabel {
	private int width, height;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Background
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width, height);

		// Simulation
		Container.getSimulation().render(g);
	}
}
