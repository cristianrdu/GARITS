/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.GUIs;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.StockControl.SparePart;
import org.servlets.garits.StockControl.Stock;

/**
 *
 * @author glaxo
 */
public class Restock extends controller implements Initializable {
    
    private GUIController GC;
    private AccountController AC;
    private Stock s;
    

    @FXML
    TextField thresholdTF;
    @FXML
    Label partName;
    

    public void setAC(AccountController ac) {
        this.AC = ac;
    }

    public void setGC(GUIController gc) {
        this.GC = gc;
    }

    public void setPart(Stock s) {
        this.s = s;
        partName.setText(s.getItemDescription());
    }

    @FXML
    public void Cancel(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    public void Confirm(ActionEvent event) throws SQLException {
        SparePart sp= new SparePart(s,Integer.parseInt(thresholdTF.getText()));
        AC.Restock(sp);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
