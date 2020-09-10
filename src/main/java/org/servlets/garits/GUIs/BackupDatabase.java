package org.servlets.garits.GUIs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BackupDatabase implements Initializable{

	private MenuBar menu;
	private Text text;
	private Button confirm;
	private Button back;
       // Image image;
        
        
        Image image = new Image("GUIs/media/ok.png");
      
         
        
        @FXML
        private ImageView imageView = new ImageView(image);
        
        GUIController GC = new GUIController();

	/**
	 * 
	 * @param action
	 */
        @FXML
	public void OK(ActionEvent action) throws Throwable {
		// TODO - implement BackupDatabase.confirmOnClick
                Stage s = (Stage)((Node)action.getSource()).getScene().getWindow();
                s.close();
	}

	@FXML
	public void Back(ActionEvent action) throws IOException {
		// TODO - implement BackupDatabase.backOnClick
                GC.ChangeView(action, "Administrator View.fxml");
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setScaleX(20);
        imageView.setScaleY(20);
        imageView.setVisible(true);
    }



}