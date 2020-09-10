/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


/**
 *
 * @author glaxo
 */
public class controller implements Initializable  {
    
    
    private GUIController GC;
    private AccountController AC;

   
    
    
    public void setGC(GUIController GC){
        this.GC = GC;
    }
    
     public void setAC(AccountController AC){
        this.AC = AC;
    }
     
     public AccountController getAC(){
         return AC;
     }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    
}
