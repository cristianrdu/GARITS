package org.servlets.garits.GUIs;

import org.servlets.garits.Documents.DocumentsController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javax.swing.JButton;

public class ProduceInvoice extends controller  implements Initializable{

	private TableView tableView;
	private Menu menu;
	private DocumentsController dc;
	private JButton confirm;
	private ChoiceBox jobNo;
	private JButton back;

	/**
	 * 
	 * @param action
	 */
	public void Confirm(ActionEvent action) {
		// TODO - implement ProduceInvoice.Confirm
	}

	/**
	 * 
	 * @param action
	 */
	public void Back(ActionEvent action) {
		// TODO - implement ProduceInvoice.Back
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}