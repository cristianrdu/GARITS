package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javax.swing.JButton;
import javax.swing.text.html.ListView;

public class ForepersonView extends controller implements Initializable {

	private JButton addJob;
	private JButton editJob;
	private JButton AllocateMechanic;
	private TableView tableVIew;
	private ListView list;
	private MenuBar menu;

        private GUIController GC;
        
        private AccountController AC;
        
        
	/**
	 * 
	 * @param action
	 */
	public void AddJobOnClick(ActionEvent action) {
		// TODO - implement ForepersonView.AddJobOnClick
	}

	/**
	 * 
	 * @param action
	 */
	public void GenerateReport(ActionEvent action) {
		// TODO - implement ForepersonView.GenerateReport
	}

	/**
	 * 
	 * @param action
	 */
	public void AllocateMechanicOnClick(ActionEvent action) {
		// TODO - implement ForepersonView.AllocateMechanicOnClick
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }


}