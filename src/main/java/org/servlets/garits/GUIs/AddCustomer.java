package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.DAO.CustomerDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddCustomer extends controller implements Initializable {

    @FXML
    private TextField name = new TextField();
    @FXML
    private TextField email = new TextField();
    @FXML
    private TextField tel = new TextField();
    @FXML
    private TextField address = new TextField();
    @FXML
    private TextField fax = new TextField();
    @FXML
    private TextField post_code = new TextField();
    private CustomerDAO DAO;

    GUIController GC = new GUIController();
    AccountController AC = new AccountController();

    /**
     *
     * @param action
     */
    public void confirmOnClick(ActionEvent action) throws SQLException, ClassNotFoundException, IOException {
        Customer customer = new Customer();
        customer.setAddress(address.getText());
        customer.setEmail(email.getText());
        customer.setName(name.getText());
        customer.setFax(fax.getText());
        customer.setTel(tel.getText());
        customer.setPostCode(post_code.getText());
        DAO = new CustomerDAO();
        DAO.addCustomer(customer);
    }

    @FXML
    public void Back(ActionEvent event) throws IOException {
        GC.ChangeView(event, "Franchisee View.fxml");
    }

    @FXML
    public void Home(ActionEvent event) throws IOException {
        GC.ChangeView(event, "Franchisee View.fxml");
    }

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

    public void setAC(AccountController AC) {
        this.AC = AC;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
