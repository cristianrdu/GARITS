/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.GUIs;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.StockControl.Manufacturer;
import org.servlets.garits.StockControl.Stock;

/**
 *
 * @author glaxo
 */
public class OrderStock extends controller implements Initializable {

    private GUIController GC;
    private AccountController AC;
    private Stock sp;
    private ObservableList<Manufacturer> ms;

    @FXML
    private Label detailsLabel;
    @FXML
    ChoiceBox manChoice;
    @FXML
    TextField quanTF;

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

    public void setAC(AccountController AC) {
        this.AC = AC;
    }

    public void setPart(Stock s) {
        sp = s;
        detailsLabel.setText(sp.getItemDescription());
    }
    
    public void setMs(ObservableList<Manufacturer> ms){
        this.ms = ms;
        manChoice.setItems(ms);
        manChoice.setValue(sp.getManufacturer().getManufacturerName());
    }
    

    @FXML
    public void Confirm(ActionEvent event) throws SQLException {
        if(quanTF.getText().isEmpty()){
            Notifications.create().title("Please Fill in details!").showWarning();
        }
        else
        {
            int q = Integer.parseInt(quanTF.getText());
            sp.setManufacturer((Manufacturer) manChoice.getValue());
            AC.OrderSparePart(sp, q);
        }
        
    }
    @FXML
    public void Cancel(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
