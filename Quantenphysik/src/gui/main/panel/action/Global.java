package gui.main.panel.action;

import org.json.JSONObject;

import gui.other.impulswellenlaenge.DiagrammGUI;
import gui.other.impulswellenlaenge.RechenwegGUI;

public abstract class Global {
	public static void start_diagramm(JSONObject data_json)  {
		try {
			DiagrammGUI iwd = new DiagrammGUI(data_json);
			iwd.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void start_rechenweg(JSONObject data_json)  {
		try {
			RechenwegGUI iwr = new RechenwegGUI(data_json);
			iwr.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
