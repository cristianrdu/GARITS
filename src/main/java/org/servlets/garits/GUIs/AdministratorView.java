package org.servlets.garits.GUIs;


import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.User;
import org.servlets.garits.DAO.UserDAO;
import java.awt.MenuBar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdministratorView extends controller {

    @FXML
    private Button addUser;
    @FXML
    private Button editUser;
    @FXML
    private Button backupDatabase;
    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, Integer> staffNo;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> address;
    @FXML
    private TableColumn<User, String> tel;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> postcode;
    @FXML
    private TableColumn<User, String> role;

    private MenuBar menu;
    @FXML
    private Label nameLabel;

    UserDAO DAO;

    private GUIController GC;

    private AccountController AC;
    @FXML
    private Button restoreDatabase1;

    @FXML
    public void AddUser(ActionEvent event) throws IOException {
        System.out.println("AddUser");
        GC.ChangeView(event, "Administrator View - Add User.fxml");
    }

    @FXML
    public void EditUser(ActionEvent event) throws IOException {
        System.out.println("EditUser");
        
        User u = tableView.getItems().get(tableView.getSelectionModel().getFocusedIndex());

        //new EditUser(u);
        GC.EditUser(event, u);
        //System.out.println(u.toString());
    }

    @FXML
    public void BackupDatabase(ActionEvent event) throws IOException {
        System.out.println("BackupDatabase");
        GC.ShowAlert("BackupDatabase.fxml", "Backup Database");
        
        
    }
    
    
       
    public static void exportDb() throws IOException, InterruptedException {
    long time = System.currentTimeMillis();
    Date date = new Date(time);
    if(date.toLocalDate().getDayOfMonth()==1||
    date.toLocalDate().getDayOfMonth()==14||
    date.toLocalDate().getDayOfMonth()==28)
      exportDb();
        
        Runtime rt = Runtime.getRuntime();
    Process p;
    ProcessBuilder pb;
    rt = Runtime.getRuntime();
    pb = new ProcessBuilder(
            "C:\\Program Files\\PostgreSQL\\11\\bin\\pg_dump.exe",
            "--host", "localhost",
            "--port", "5432",
            "--username", "postgres",
            "--password","password",
            "--format", "custom",
            "--blobs",
            "--verbose", "--file", ":\\service_station_backup.backup", "service_station");
    try {
        final Map<String, String> env = pb.environment();
        env.put("PGPASSWORD", "admin");
        p = pb.start();
        final BufferedReader r = new BufferedReader(
                new InputStreamReader(p.getErrorStream()));
        String line = r.readLine();
        while (line != null) {
            System.err.println(line);
            line = r.readLine();
        }
        r.close();
        p.waitFor();
        System.out.println(p.exitValue());

    } catch (IOException | InterruptedException e) {
        System.out.println(e.getMessage());
    }
}
    @FXML
    public void RestoreDatabase(ActionEvent event) throws IOException {
        System.out.println("RestoreDatabase");
        GC.ChangeView(event, "Administrator View - Restore Database.fxml");

    }

    public ObservableList<User> getUserList() {
        try {
            DAO = new UserDAO();
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministratorView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<User> users = new ArrayList<User>();
        try {
            users = DAO.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<User> u = FXCollections.observableArrayList();

        for (User user : users) {
            u.add(user);
            //System.out.println(user.toString());
        }

        return u;
    }

    public void setAC(AccountController ac) {
        this.AC = ac;
    }
    public void LogOut(ActionEvent event) throws IOException {
        GC.ShowAlert("LogOutAlert.fxml", "Log Out");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staffNo.setCellValueFactory(new PropertyValueFactory<User, Integer>("staffNo"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        tel.setCellValueFactory(new PropertyValueFactory<User, String>("tel"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        postcode.setCellValueFactory(new PropertyValueFactory<User, String>("postCode"));
        role.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        try {
            tableView.setItems(getUserList());
        } catch (Exception e) {

        }
            
    }

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

}
