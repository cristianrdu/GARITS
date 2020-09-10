package org.servlets.garits.GUIs;


import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class EditCustomer extends controller implements Initializable{

     
	@FXML
	private TextField name= new TextField();
        @FXML
	private TextField email= new TextField();
        @FXML
	private TextField tel= new TextField();
        @FXML
	private TextField address= new TextField();
        @FXML
	private TextField fax= new TextField();
        @FXML
	private TextField post_code= new TextField();
        
        private String oldEmail;
        private Customer customer= new Customer();
        GUIController GC = new GUIController();
         private AccountController AC = new AccountController();
	
	/**
	 * 
	 * @param action
	 */
        @FXML
	public void confirmOnClick(ActionEvent action) throws SQLException, ClassNotFoundException {
		// TODO - implement EditCutsotmer.confirmOnClick
            if(!name.getText().equals(customer.getName())){
                    customer.setName(name.getText());
                }
                if(!email.getText().equals(customer.getEmail())){
                    customer.setEmail(email.getText());
                }
                if(!tel.getText().equals(customer.getTel())){
                    customer.setTel(tel.getText());
                }
                if(!address.getText().equals(customer.getAddress())){
                    customer.setAddress(address.getText());
                }
                if(!fax.getText().equals(customer.getFax())){
                    customer.setFax(fax.getText());
                }
                if(!post_code.getText().equals(customer.getPostCode())){
                    customer.setPostCode(post_code.getText());
                }
                AC.EditCustomer(customer,oldEmail);
	}
        public void removeCustomerOnclick(ActionEvent action) throws SQLException, ClassNotFoundException {
		// TODO - implement EditUser.removeUserOncklick
                AC.DeleteCustomer(customer);
	}
        @FXML
        public void Back(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Franchisee View.fxml");
        }
        @FXML
        public void Home(ActionEvent event) throws IOException{
            GC.ChangeView(event, "Franchisee View.fxml");
        }
        
        
        
        public void setGC(GUIController gc){
            this.GC = gc;
            
        }
        
        public void setCustomer(Customer customer){
        
        this.customer=customer;
            System.out.println(customer.toString());
            name.setText(customer.getName());
            fax.setText(customer.getFax());
            address.setText(customer.getAddress());
            email.setText(customer.getEmail());
            tel.setText(customer.getTel());
            post_code.setText(customer.getPostCode());
            oldEmail=customer.getEmail();
        }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

	
}