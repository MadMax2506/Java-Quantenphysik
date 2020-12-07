package gui.main.panel.action;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import gui.filechooser.impulswellenlaenge.LoadFile;
import gui.main.panel.ImpulsWellenlaengeLadenPanel;

public class ImpulsWellenlaengeLaden {
	public final double EMPTY_VALUE = -1.12321312321;
	
	private ImpulsWellenlaengeLadenPanel elektronenPanel;
	
	private boolean show_rechenweg;
	private boolean show_diagramm;
	
	public ImpulsWellenlaengeLaden(ImpulsWellenlaengeLadenPanel elektronenPanel) {
		this.elektronenPanel = elektronenPanel;
		
		set_default();
	}
	
	// setter
	private void set_default() {
		show_rechenweg = true;
		elektronenPanel.cbRechenweg.setSelected(show_rechenweg);
		
		show_diagramm = false;
		elektronenPanel.cbDiagramm.setSelected(show_diagramm);
		
		set_checkboxen();
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
		// Dialog zum Laden der Dateien anzeigen
		JFileChooser chooser = new LoadFile();

        int res = chooser.showOpenDialog(null);

        File f;
        if(res == JFileChooser.APPROVE_OPTION) {
			f = chooser.getSelectedFile();
		} else  {
			return;
		}
		
		JSONObject data_json;
		try {
			FileReader fr 		= new FileReader( f );
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
