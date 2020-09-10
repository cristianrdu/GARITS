package org.servlets.garits.GUIs;

import org.servlets.garits.StockControl.*;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javax.swing.JButton;
import javax.swing.text.html.ListView;

public class TakeOutParts implements Initializable {

	private TextField customerName;
	private TextField quantity;
	private ChoiceBox jobNo;
	private ChoiceBox type;
	private ListView stock;
	private ListView jobs;
	private StockController sc;
	private JButton takeOut;
	private JButton back;

	/**
	 * 
	 * @param action
	 */
	public void TakeOut(ActionEvent action) {
		// TODO - implement TakeOutParts.TakeOut
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
	public void Back(ActionEvent action) {
		// TODO - implement TakeOutParts.Back
		throw new UnsupportedOperationException();
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}