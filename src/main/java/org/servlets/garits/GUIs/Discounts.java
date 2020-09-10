package org.servlets.garits.GUIs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javax.swing.JButton;

public class Discounts implements Initializable{

	private TableView tableView;
	private Menu menu;
	private JButton alterDiscountPlan;
	private JButton setNewDiscount;
	private JButton back;

        GUIController GC = new GUIController();
        
	/**
	 * 
	 * @param action
	 */
	public void AlterDiscountPlanOnClick(ActionEvent action) {
		// TODO - implement Discounts.AlterDiscountPlanOnClick
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
	public void SetNewDiscountPlanOnClick(ActionEvent action) {
		// TODO - implement Discounts.SetNewDiscountPlanOnClick
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param action
	 */
        @FXML
	public void Back(ActionEvent action) throws IOException {
		// TODO - implement Discounts.back
                GC.ChangeView(action, "Franchisee View.fxml");
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}