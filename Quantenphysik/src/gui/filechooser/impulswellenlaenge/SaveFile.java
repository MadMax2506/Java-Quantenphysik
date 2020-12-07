package gui.filechooser.impulswellenlaenge;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveFile extends JFileChooser {
	private static final long serialVersionUID = -8412299663743793005L;

	
	public SaveFile() {
		setDialogType(JFileChooser.SAVE_DIALOG);
        setFileSelectionMode(JFileChooser.FILES_ONLY); 
        setDialogTitle("Speichern unter...");
        setVisible(true);
        
        FileNameExtensionFilter json_filtet = new FileNameExtensionFilter("JSON-Datei: .json", "json");
        removeChoosableFileFilter( getAcceptAllFileFilter() );
        setFileFilter(json_filtet);
	}
}
