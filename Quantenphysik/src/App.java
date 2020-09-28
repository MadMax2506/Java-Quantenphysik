import gui.RunGUI;

public class App {

	public static void main(String[] args) {
		// GUI
		RunGUI runnableGUI = new RunGUI();
		Thread tGUI = new Thread(runnableGUI);

		tGUI.start();
	}

}
