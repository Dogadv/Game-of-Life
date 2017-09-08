package gol.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private int x, y, size;
	private boolean alive;
	private boolean nextRound;
	private Color color = Color.darkGray;

	public Cell(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(x * size, y * size, size, size);
		if (alive) {
			g.setColor(color);
			g.fillRect((x * size) + 1, (y * size) + 1, size - 1, size - 1);
		}
	}
	
	public void update() {
		alive = nextRound;
	}


	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setNextRound(boolean nextRound) {
		this.nextRound = nextRound;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
