package org.servlets.garits.GUIs;

import java.io.BufferedReader;
import java.io.File;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Admin;
import org.servlets.garits.Accounts.Foreperson;
import org.servlets.garits.Accounts.Franchisee;
import org.servlets.garits.Accounts.Mechanic;
import org.servlets.garits.Accounts.Receptionist;
import org.servlets.garits.Accounts.User;
import org.servlets.garits.DAO.UserDAO;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LogIn extends controller implements Initializable {

    @FXML
    private TextField username = new TextField("Enter Username");
    @FXML
    private TextField password;
    @FXML
    private Label message;

    private final String ADMINUSERNAME = "admin";

    private final String ADMINPASSWORD = "password";

    UserDAO dao;

    private AccountController ac = new AccountController();

    private GUIController gc = new GUIController();
    @FXML
    private Button LogIn;

    @FXML
    public void LogIn(ActionEvent action) throws IOException, InterruptedException, SQLException, ClassNotFoundException {

        if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
            if (!username.getText().equals(ADMINUSERNAME) && !username.getText().equals(ADMINPASSWORD)) {

                ac.logIn(username.getText(), password.getText());
                User user = ac.getUser();

                if (user instanceof Admin) {
                    gc.LogIn(action, "Administrator View.fxml", ac);
                } else if (user instanceof Franchisee) {
                    gc.LogIn(action, "Franchisee View.fxml", ac);
                } else if (user instanceof Foreperson) {
                    gc.LogIn(action, "Foreperson View.fxml", ac);
                } else if (user instanceof Receptionist) {
                    gc.LogIn(action, "Receptionist View.fxml", ac);
                } else if (user instanceof Mechanic) {
                    gc.LogIn(action, "Mechanic View.fxml", ac);
                } else if (user == null) {
                    message.setText("Could not find user with matching details!");
                }

            } else {
                gc.ChangeView(action, "Administrator View.fxml");
            }
        } else {
            message.setText("Please enter your details!");
        }
    }

    public static void exportDb() throws IOException, InterruptedException {


        Runtime rt = Runtime.getRuntime();
        Process p;
        ProcessBuilder pb;
        rt = Runtime.getRuntime();

        pb = new ProcessBuilder(
                "C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe",
                "--host", "localhost",
                "--port", "5432",
                "--username", "postgres",
                "--no-password",
                "--format", "custom",
                "--blobs",
                "--verbose", "--file", "D:\\service_station_backup.backup", "service_station");
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

    public void Exit(MouseEvent event
    ) {
        System.out.println("Hello");
        System.exit(0);
    }

    public void Drag(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //window.setX(event.);

        System.out.println("drag");
    }

    @FXML
    public void Enter(KeyEvent action) throws ClassNotFoundException, SQLException, IOException {
        if (action.getCode().equals(KeyCode.ENTER)) {
            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                if (!username.getText().equals(ADMINUSERNAME) && !username.getText().equals(ADMINPASSWORD)) {

                    ac.logIn(username.getText(), password.getText());

                    User user = ac.getUser();

                    if (user instanceof Admin) {
                        gc.LogIn(action, "Administrator View.fxml", ac);
                    } else if (user instanceof Franchisee) {
                        gc.LogIn(action, "Franchisee View.fxml", ac);
                    } else if (user instanceof Foreperson) {
                        gc.LogIn(action, "Foreperson View.fxml", ac);
                    } else if (user instanceof Receptionist) {
                        gc.LogIn(action, "Receptionist View.fxml", ac);
                    } else if (user instanceof Mechanic) {
                        gc.LogIn(action, "Mechanic View.fxml", ac);
                    } else if (user == null) {
                        message.setText("Could not find user with matching details!");

                    }
                }

            } else {
                gc.ChangeView(action, "Administrator View.fxml");
            }
        } else {
            message.setText("Please enter your details!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
