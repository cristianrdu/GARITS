package org.servlets.garits.GUIs;

import org.servlets.garits.Documents.DocumentsController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javax.swing.JButton;
import org.servlets.garits.Documents.CreateStockReport;

public class GenerateReports extends controller implements Initializable {

	private ChoiceBox typeOfReport;
	private TextField carModel;
	private JButton generate;
	private JButton view;
	private JButton print;
	private JButton back;
	private DocumentsController dc;
        @FXML
	private ChoiceBox<String> report = new ChoiceBox<>();
        
        GUIController GC = new GUIController();

	public ChoiceBox    getRole() {
		// TODO - implement GenerateReport.getRole
		throw new UnsupportedOperationException();
	}
        @FXML
	public void confirmOnClick(ActionEvent action) throws  ClassNotFoundException, IOException, SQLException {
                
            
            if(report.getValue().equals("Stock Level Report")){
                  CreateStockReport s=new CreateStockReport(); 
                s.CreatePdf();
                }
                
            
                
	}
	/**
	 * 
	 * @param Role
	 */
	public void setRole(ChoiceBox Role) {
		// TODO - implement GenerateReport.setRole
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
	public void GenerateOnClick(ActionEvent action) {
		// TODO - implement GenerateReport.GenerateOnClick
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
	public void ViewOnClick(ActionEvent action) {
		// TODO - implement GenerateReport.ViewOnClick
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
	public void PrintOnClick(ActionEvent action) {
		// TODO - implement GenerateReport.PrintOnClick
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
        @FXML
	public void Back(ActionEvent action) throws IOException{
		// TODO - implement GenerateReport.BackOnClick
                GC.ChangeView(action, "Franchisee View.fxml");
	}

	public DocumentsController getDc() {
		return this.dc;
	}

	/**
	 * 
	 * @param dc
	 */
	public void setDc(DocumentsController dc) {
		this.dc = dc;
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }



}