package org.servlets.garits.GUIs;


import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.User;
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

public class EditUser extends controller implements Initializable {
  
        @FXML
	private TextField fullName;
        @FXML
	private TextField password;
        @FXML
	private TextField username;
        @FXML
	private TextField tel;
        @FXML
	private TextField email;
        @FXML
	private TextField address;
        @FXML
	private TextField postcode;
        @FXML
        private ChoiceBox role;

        
        private User user = new User();
        
        private AccountController AC = new AccountController();
        
        
        
        
        GUIController GC = new GUIController();

	/**
	 * 
	 * @param action
	 */
        @FXML
	public void confirmOnClick(ActionEvent action) throws SQLException, ClassNotFoundException {
		// TODO - implement EditUser.confirmOnClick
                if(!username.getText().equals(user.getUserName())){
                    user.setUserName(username.getText());
                }
                if(!fullName.getText().equals(user.getName())){
                    user.setName(fullName.getText());
                }
                if(!email.getText().equals(user.getEmail())){
                    user.setEmail(email.getText());
                }
                if(!tel.getText().equals(user.getTel())){
                    user.setTel(tel.getText());
                }
                if(!postcode.getText().equals(user.getPostCode())){
                    user.setPostCode(postcode.getText());
                }
                AC.EditUser(user);
	}

	/**
	 * 
	 * @param action
	 */
        @FXML
	public void removeUserOncklick(ActionEvent action) throws SQLException, ClassNotFoundException {
		// TODO - implement EditUser.removeUserOncklick
                AC.RemoveUser(user);
	}
        
        @FXML
        public void Back(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Administrator View.fxml");
        }
        
        @FXML
        public void Home(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Administrator View.fxml");
        }
        
        public void setUser(User user){
            this.user = user;
            System.out.println(user.toString());
            fullName.setText(user.getName());
            username.setText(user.getUserName());
            email.setText(user.getEmail());
            tel.setText(user.getTel());
            postcode.setText(user.getPostCode());
            
        }
        
        public void setGC(GUIController gc){
            this.GC = gc;
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // System.out.println(user.toString());
    }

    

}