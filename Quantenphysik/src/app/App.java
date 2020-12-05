package app;
import java.awt.EventQueue;
import java.io.File;

import gui.main.GUI;

public class App {
	
	public static final File user_folder = new File(System.getProperty("user.home") + "/quantenphysik");
	
	public static void main(String[] args) {
		new App();
	}
	
	public App() {
		user_folder.mkdirs();
		
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
