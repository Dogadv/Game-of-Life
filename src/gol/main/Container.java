package gol.main;

public class Container {
	// Window prefs
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 800;
	public static final String WINDOW_TITLE = "Game of Life";

	// Simulation prefs
	public static final int CELL_SIZE = 10;
	public static final int FIELD_WIDTH = (WINDOW_WIDTH) / CELL_SIZE;
	public static final int FIELD_HEIGHT = (WINDOW_HEIGHT) / CELL_SIZE;
	public static final int REFRESH_RATE = 256;
	public static final int MILLIS = 1000 / REFRESH_RATE;

	private static Simulation simulation;
	private static Object keyPressMonitor;

	public Container() {
		simulation = new Simulation(FIELD_WIDTH, FIELD_HEIGHT, CELL_SIZE);
		new Window(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE).repaint();
	}

	public static Simulation getSimulation() {
		return simulation;
	}
	
	public static Object getKeyPressMonitor(){
		return keyPressMonitor;
	}
}
