package gol.main;

import java.awt.Graphics;
import java.util.Random;

import gol.entity.Cell;

public class Simulation {
	
	private Cell[][] cells;
	
	private int field_width, field_height, cell_size;
	
	Random random;
	
	public Simulation(int field_width, int field_height, int cell_size) {
		this.field_width = field_width;
		this.field_height = field_height;
		this.cell_size = cell_size;
		cells = new Cell[field_width][field_height];
		random = new Random();
		
		for(int x = 0; x < field_width; x++) {
			for(int y = 0; y < field_height; y++) {
				cells[x][y] = new Cell(x, y, cell_size);
				if(x % 3 == 0 || y % 3 == 0) cells[x][y].setAlive(false);
				else cells[x][y].setAlive(true);
			}
		}
	}
	
	public void clear() {
		for(int x = 0; x < field_width; x++) {
			for(int y = 0; y < field_height; y++) {
				cells[x][y].setAlive(false);
			}
		}
	}
	
	public void modifyCell(int x, int y, boolean state) {
		x = x/cell_size;
		y = y/cell_size;
		cells[x][y].setAlive(state);
	}
	
	public void render(Graphics g) {
		for(int x = 0; x < field_width; x++) {
			for(int y = 0; y < field_height; y++) {
				cells[x][y].render(g);
			}
		}
	}
	
	public void update() {
		for(int x = 0; x < field_width; x++) {
			for(int y = 0; y < field_height; y++) {
				int mx = x - 1;
				if(mx < 0) mx = field_width - 1;
				int my = y - 1;
				if(my < 0) my = field_height - 1;
				int gx = (x + 1) % field_width;
				int gy = (y + 1) % field_height;

				int neighborCounter = 0;
				if(cells[mx][my].isAlive()) neighborCounter++;
				if(cells[mx][y].isAlive()) neighborCounter++;
				if(cells[mx][gy].isAlive()) neighborCounter++;
				if(cells[x][my].isAlive()) neighborCounter++;
				if(cells[x][gy].isAlive()) neighborCounter++;
				if(cells[gx][my].isAlive()) neighborCounter++;
				if(cells[gx][y].isAlive()) neighborCounter++;
				if(cells[gx][gy].isAlive()) neighborCounter++;

				if(neighborCounter < 2 || neighborCounter > 3) cells[x][y].setNextRound(false);
				else if(neighborCounter == 2) cells[x] [y].setNextRound(cells[x][y].isAlive());
				else cells[x][y].setNextRound(true);

			}
		}
		
		for(int x = 0; x < field_width; x++) {
			for(int y = 0; y < field_height; y++) {
				cells[x] [y].update();
			}
		}
	}
}
