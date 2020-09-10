package org.servlets.garits.GUIs;

import java.io.IOException;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Job.Job;
import org.servlets.garits.Job.Task;
import org.servlets.garits.StockControl.SparePart;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.servlets.garits.Accounts.Customer;

public class MechanicView extends controller implements Initializable {

    @FXML
    private TableView<Job> pendingJobs;

    @FXML
    private TableColumn<Job, String> pjType;

    @FXML
    private TableColumn<Job, String> pjStatus;

    @FXML
    private TableColumn<Job, String> pjMechanic;

    @FXML
    private TableColumn<Job, String> pjCustomer;

    @FXML
    private TableView<Job> myJobs;
    @FXML
    private TableColumn<Job, Integer> mjId;

    @FXML
    private TableColumn<Job, String> mjStatus;

    @FXML
    private TableColumn<Job, Integer> mjDuration;

    @FXML
    private TableColumn<Job, String> mjWork;

    @FXML
    private TableColumn<Job, Integer> mjNumTasks;

    @FXML
    private TableView<Task> tasks;

    @FXML
    private TableColumn<Task, Integer> id;

    @FXML
    private TableColumn<Task, String> estimatedTime;

    @FXML
    private TableView<SparePart> spareParts;

    @FXML
    private TableColumn<SparePart, String> item;

    @FXML
    private TableColumn<SparePart, Double> price;

    @FXML
    private TableColumn<SparePart, Integer> quantity;

    
    
    
    private GUIController GC;
    private AccountController AC;

    public void setGC(GUIController gc) {
        this.GC = gc;
    }

    public void setAC(AccountController ac) {
        this.AC = ac;
        try {
            getJobsList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MechanicView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Job> getJobsList() throws ClassNotFoundException {

        ArrayList<Job> jobs = new ArrayList<Job>();
        try {
            jobs = AC.getJobs();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Job> j = FXCollections.observableArrayList();
        for (Job job : jobs) {
            System.out.println(job.getCustomer().getEmail() + " - " + job.getJobType());
            j.add(job);

        }
        pendingJobs.setItems(j);

        return j;
    }
    
    
    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        GC.ShowAlert("LogOutAlert.fxml", "Log Out");
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pjType.setCellValueFactory(new PropertyValueFactory<Job, String>("jobType"));
        pjStatus.setCellValueFactory(new PropertyValueFactory<Job, String>("status"));
        pjCustomer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> customer) {
                return new ReadOnlyStringWrapper(customer.getValue().getCustomer().getName());
            }
        });

        pjMechanic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> mechanic) {
                return new ReadOnlyStringWrapper(mechanic.getValue().getAssignedMechanic().getName());
            }
        });
        
        mjId.setCellValueFactory(new PropertyValueFactory<Job,Integer>("jobNo"));
        
        

    }

}
