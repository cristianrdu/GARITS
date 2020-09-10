/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author glaxo
 */
public class LogOut extends controller implements Initializable {

    AccountController AC;
    GUIController GC;

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

    public void setAC(AccountController AC) {
        this.AC = AC;
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();

        GC.LogOut(s);
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
