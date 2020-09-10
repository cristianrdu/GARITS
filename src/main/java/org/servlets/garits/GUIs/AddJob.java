package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javax.swing.JButton;

public class AddJob extends controller implements Initializable {

	
        
        @FXML
        DatePicker BookingDate;
        
        
        GUIController GC = new GUIController();
        AccountController AC;

	

	/**
	 * 
	 * @param action
	 */
	public void confirmOnClick(ActionEvent action) {
		// TODO - implement AddJob.confirmOnClick
	}

	@FXML
        public void Back(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Franchisee View.fxml");
        }
        
        
        public void setGC(GUIController gc){
            this.setGC(gc);
        }
        public void setAC(AccountController ac){
            this.setAC(ac);
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}