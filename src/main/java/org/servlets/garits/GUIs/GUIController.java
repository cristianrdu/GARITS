package org.servlets.garits.GUIs;

import java.io.File;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.User;
import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.StockControl.Stock;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.servlets.garits.Documents.CreateStockReport;
import org.servlets.garits.StockControl.Manufacturer;

public class GUIController {

    private Scene s;
    private Stage window;
    private AccountController AC;
    

    GUIController() {
        
//            timer.scheduleAtFixedRate(ShowNotification(), 0,10000 );
    }

    

    public void MainMenu() {
        // TODO - implement GUIController.MainMenu
        throw new UnsupportedOperationException();
    }

    public void Jobs() {
        // TODO - implement GUIController.Jobs
        throw new UnsupportedOperationException();
    }

    public void PendingJobList() {
        // TODO - implement GUIController.PendingJobList
        throw new UnsupportedOperationException();
    }

    public void CutomerAccounts() {
        // TODO - implement GUIController.CutomerAccounts
        throw new UnsupportedOperationException();
    }

    public void AddCustomerAccount() {
        // TODO - implement GUIController.AddCustomerAccount
        throw new UnsupportedOperationException();
    }

    public void AddJob() {
        // TODO - implement GUIController.AddJob
        throw new UnsupportedOperationException();
    }

    public void setWindow(Stage s) {
        window = s;
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public void LogIn(Event event, String file, AccountController ac) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/" + file));
        loader.load();

        // general controller class
        controller c = loader.getController();
        System.out.println(ac.getUser().toString());
        c.setGC(this);
        this.AC = ac;
        c.setAC(ac);

        Parent next = loader.getRoot();
        Scene scene = new Scene(next);
        //scene.getStylesheets().add(getClass().getResource("Views/Login.css").toExternalForm());
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public void init() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LogIn.fxml"));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("Views/Login.css").toExternalForm());

        window.setScene(scene);
        //window.setResizable(false);
        window.setTitle("GARITS");
        //window.setFullScreen(true);
        

        window.show();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public void ChangeView(Event event, String file) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/Views/" + file));

        loader.load();

        controller c = loader.getController();
        c.setGC(this);
        //System.out.println("GUICONTROLLER - " + AC.getUser());
        c.setAC(AC);

        Parent next = loader.getRoot();
        Scene scene = new Scene(next);
        //scene.getStylesheets().add(getClass().getResource("Views/Login.css").toExternalForm());
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //System.out.println(window.toString());
        window.setScene(scene);
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public void ShowAlert(String file, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/Views/" + file));

        loader.load();

        controller c = loader.getController();
        c.setGC(this);
        System.out.println("GUICONTROLLER - " + AC.getUser());
        c.setAC(AC);

        Stage alertWindow = new Stage();

        Parent next = loader.getRoot();

        Scene scene = new Scene(next);
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setScene(scene);
        alertWindow.setResizable(false);
        alertWindow.setTitle(title);
        //alertWindow.initStyle(StageStyle.TRANSPARENT);
        alertWindow.showAndWait();

    }

    public void EditUser(ActionEvent event, User user) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Administrator View - Edit-Remove User.fxml"));

        loader.load();

        EditUser editUser = loader.getController();
        editUser.setUser(user);
        editUser.setGC(this);

        Parent next = loader.getRoot();
        Scene scene = new Scene(next);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
    }

    public void EditCustomer(ActionEvent event, Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/Franchisee View - Edit customer.fxml"));

        loader.load();

        EditCustomer editCustomer = loader.getController();
        editCustomer.setCustomer(customer);
        editCustomer.setGC(this);

        Parent next = loader.getRoot();
        Scene scene = new Scene(next);
        window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
    }

    public GUIController(Stage s) throws IOException {
        window = s;
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public void LogOut(Stage s) throws IOException {
        s.close();
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LogIn.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("Views/Login.css").toExternalForm());
        this.AC = new AccountController();
        window.setScene(scene);
        //window.setResizable(false);
        window.setTitle("GARITS");
        //window.setFullScreen(true);
        window.show();
    }

    public void UpdateThreshold(Stock s) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/Views/UpdateThreshold.fxml"));

        loader.load();

        UpdateThreshold c = loader.getController();
        c.setGC(this);
        System.out.println("GUICONTROLLER - " + AC.getUser());
        c.setAC(AC);
        c.setPart(s);

        Stage alertWindow = new Stage();

        Parent next = loader.getRoot();

        Scene scene = new Scene(next);
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setScene(scene);
        alertWindow.setResizable(false);
        alertWindow.setTitle("Update Threshold");
        //alertWindow.initStyle(StageStyle.TRANSPARENT);
        alertWindow.showAndWait();
    }
    
    
    public void Reorder(Stock s,ObservableList<Manufacturer> ms) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/Views/OrderStock.fxml"));

        loader.load();

        OrderStock c = loader.getController();
        c.setGC(this);
        System.out.println("GUICONTROLLER - " + AC.getUser());
        c.setAC(AC);
        c.setPart(s);
        c.setMs(ms);

        Stage alertWindow = new Stage();

        Parent next = loader.getRoot();

        Scene scene = new Scene(next);
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setScene(scene);
        alertWindow.setResizable(false);
        alertWindow.setTitle("Set Order");
        //alertWindow.initStyle(StageStyle.TRANSPARENT);
        alertWindow.showAndWait();
    }
    
    public void Restock(Stock s) throws IOException{
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/Views/Restock.fxml"));

        loader.load();

        Restock c = loader.getController();
        c.setGC(this);
        System.out.println("GUICONTROLLER - " + AC.getUser());
        c.setAC(AC);
        c.setPart(s);

        Stage alertWindow = new Stage();

        Parent next = loader.getRoot();

        Scene scene = new Scene(next);
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setScene(scene);
        alertWindow.setResizable(false);
        alertWindow.setTitle("Restock");
        //alertWindow.initStyle(StageStyle.TRANSPARENT);
        alertWindow.showAndWait();
    }

}
