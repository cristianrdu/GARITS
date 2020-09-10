package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.Admin;
import org.servlets.garits.Accounts.User;
import org.servlets.garits.DAO.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddUser extends controller implements Initializable {

        @FXML
	private TextField name = new TextField();
        @FXML
	private TextField userName = new TextField();
        @FXML
	private TextField password = new TextField();
        @FXML
	private ChoiceBox<String> role = new ChoiceBox<>();
        @FXML
	private Button back;
        
        private UserDAO DAO;
        
        GUIController GC = new GUIController();
        

	

	
	
        @FXML
	public void confirmOnClick(ActionEvent action) throws SQLException, ClassNotFoundException, IOException {
                
            
                if(role.getValue().equals("Mechanic")||role.getValue().equals("Foreperson")){
                    GC.ShowAlert("BackupDatabase.fxml", "hourly rate");

                }
                else{
            
                User user = new User();
                user.setUserName(userName.getText());
                user.setName(name.getText());
                user.setRole(role.getValue());
                //DAO.addUser(user, password.getText());
                
                System.out.println(role.getValue());
                DAO = new UserDAO();
                DAO.addUser(user, password.getText());
                }
	}
        
        @FXML
        public void back(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Administrator View.fxml");
        }
        
        @FXML
        public void Home(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Administrator View.fxml");
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        role.getItems().add("Receptionist");
//        role.getItems().add("Foreperson");
//        role.getItems().add("Franchisee");
//        role.getItems().add("R")
        
    }

}