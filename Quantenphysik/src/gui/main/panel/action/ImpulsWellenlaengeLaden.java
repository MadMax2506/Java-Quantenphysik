package gui.main.panel.action;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import app.App;
import gui.main.panel.ImpulsWellenlaengeLadenPanel;

public class ImpulsWellenlaengeLaden {
	public final double EMPTY_VALUE = -1.12321312321;
	
	private ImpulsWellenlaengeLadenPanel elektronenPanel;

	private int selected_file_index;
	private LinkedList<String> file_elements;
	
	private boolean show_rechenweg;
	private boolean show_diagramm;
	
	public ImpulsWellenlaengeLaden(ImpulsWellenlaengeLadenPanel elektronenPanel) {
		this.elektronenPanel = elektronenPanel;
		
		set_default();
	}
	
	// setter
	private void set_default() {
		set_files();
		
		show_rechenweg = true;
		elektronenPanel.cbRechenweg.setSelected(show_rechenweg);
		
		show_diagramm = false;
		elektronenPanel.cbDiagramm.setSelected(show_diagramm);
		
		set_checkboxen();
	}
	
	private void set_files() {
		selected_file_index = (int)EMPTY_VALUE;
		file_elements = new LinkedList<String>();
		
		File[] folder_entries = App.user_folder.listFiles();
		for (File fileEntry : folder_entries) {
			String file_name = fileEntry.getName();
			
	        if (!fileEntry.isDirectory() && file_name.contains(".json")) {
	            file_elements.add( file_name );
	        }
	    }
		
		String[] file_elements_array	= new String[file_elements.size()];
		file_elements_array 			= file_elements.toArray(file_elements_array);
		elektronenPanel.list_files.setListData( file_elements_array );
	}

	public void set_selected_value(int index) {
		selected_file_index = index;
	}
	
	public void set_checkboxen() {
		// Fortlaufende Actionen initialisieren
		show_rechenweg 	= elektronenPanel.cbRechenweg.isSelected();
		show_diagramm 	= elektronenPanel.cbDiagramm.isSelected();
		
		if( show_diagramm && !show_rechenweg
			|| !show_diagramm && show_rechenweg ) {
			elektronenPanel.cbDiagramm.setEnabled(!show_diagramm);
			elektronenPanel.cbRechenweg.setEnabled(!show_rechenweg);
		} else {
			elektronenPanel.cbDiagramm.setEnabled(true);
			elektronenPanel.cbRechenweg.setEnabled(true);
		}
	}
	
	// other
	public void load_data() {
		if(selected_file_index == (int)EMPTY_VALUE) {
			JOptionPane.showMessageDialog(null, "Bitte wählen Sie eine Datei, die Sie laden möchten", "Fehlende Auswahl", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		JSONObject data_json;
		try {
			File file 			= new File( App.user_folder.toString() + "/" + file_elements.get(selected_file_index) );
			FileReader fr 		= new FileReader(file);
			BufferedReader br 	= new BufferedReader(fr);
			
			String data_str = "";
			String tmp_str;
			while((tmp_str = br.readLine()) != null) {
				data_str+= tmp_str;
			}
			
			data_json = new JSONObject(data_str);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Leider ist ein Systemfehler aufgetreten.\nVersuchen Sie es zu einem späteren Zeitpunkt erneut...", "Systemfehler", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(show_rechenweg) {
			EventQueue.invokeLater(() -> Global.start_rechenweg(data_json));
		}
		
		if(show_diagramm) {
			EventQueue.invokeLater(() -> Global.start_diagramm(data_json));
		}
	}
}
