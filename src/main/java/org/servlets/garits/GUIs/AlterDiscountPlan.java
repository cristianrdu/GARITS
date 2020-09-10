package org.servlets.garits.GUIs;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javax.swing.JButton;

public class AlterDiscountPlan extends controller implements Initializable {

	private TextField name;
	private TextField duartion;
	private TextField precentage;
	private ChoiceBox type;
	private ChoiceBox day;
	private ChoiceBox month;
	private ChoiceBox year;
	private TextField carModel;
	private TextField DoB;
	private TextField address;
	private JButton confirm;
	private JButton back;

	public TextField getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(TextField name) {
		this.name = name;
	}

	public TextField getUserName() {
		// TODO - implement AlterDiscountPlan.getUserName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(TextField userName) {
		// TODO - implement AlterDiscountPlan.setUserName
		throw new UnsupportedOperationException();
	}

	public TextField getPassword() {
		// TODO - implement AlterDiscountPlan.getPassword
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(TextField password) {
		// TODO - implement AlterDiscountPlan.setPassword
		throw new UnsupportedOperationException();
	}

	public ChoiceBox getRole() {
		// TODO - implement AlterDiscountPlan.getRole
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Role
	 */
	public void setRole(ChoiceBox Role) {
		// TODO - implement AlterDiscountPlan.setRole
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
	public void confirmOnClick(ActionEvent action) {
		// TODO - implement AlterDiscountPlan.confirmOnClick
		throw new UnsupportedOperationException();
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

}