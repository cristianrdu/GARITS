package org.servlets.garits.GUIs;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;

public class RestoreDatabase extends controller {

	private MenuBar menu;
	private ChoiceBox day;
	private ChoiceBox month;
	private ChoiceBox year;
	private Button confirm;
	private Button back;
        
        GUIController GC = new GUIController();

	/**
	 * 
	 * @param action
	 */
        
	public void confirmOnClick(ActionEvent action) {
		// TODO - implement RestoreDatabase.confirmOnClick
	}

	/**
	 * 
	 * @param action
	 */
        @FXML
	public void Back(ActionEvent action) throws IOException {
		GC.ChangeView(action, "Administrator View.fxml");
	}

	public Date getDate() {
		// TODO - implement RestoreDatabase.getDate
		throw new UnsupportedOperationException();
	}
        
        
        
//        public void setGC(GUIController GC){
//            this.GC = GC;
//        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }


}