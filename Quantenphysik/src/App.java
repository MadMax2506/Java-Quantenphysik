import java.awt.EventQueue;

import gui.GUI;

public class App {

	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		EventQueue.invokeLater(() -> start_app_gui());
	}
	
	private void start_app_gui()  {
		try {
			GUI frame = new GUI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
