/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.Job.Job;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

import javafx.util.Callback;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Notifications;
import org.servlets.garits.StockControl.Stock;

/**
 *
 * @author glaxo
 */
public class ReceptionistView extends controller {

    private GUIController GC;
    private AccountController AC = new AccountController();

    File jobTypes = new File("JobTypes.txt");
    private static Timer timer = new Timer();

    TimerTask stock = new TimerTask() {

        @Override
        public void run() {
            ShowNotification();
        }
    };

    @FXML
    TableView<Job> JobsTable;
    @FXML
    TableColumn<Job, String> type;
    @FXML
    TableColumn<Job, String> mechanic;
    @FXML
    TableColumn<Job, String> status;
    @FXML
    TableColumn<Job, String> customer;
    @FXML
    TableView<Customer> customerTable;
    @FXML
    TableColumn<Customer, String> email;
    @FXML
    TableColumn<Customer, String> name;
    @FXML
    TableColumn<Customer, String> address;
    @FXML
    TableColumn<Customer, String> post_code;
    @FXML
    TableColumn<Customer, String> tel;
    @FXML
    TableColumn<Customer, String> fax;

    @FXML
    public void AddJob(ActionEvent event) throws IOException {
        GC.ChangeView(event, "Franchisee View - Add-Process pending job.fxml");
    }

    @FXML
    public void GenerateReport(ActionEvent event) throws IOException {
        GC.ChangeView(event, "Franchisee View - Generate Reports.fxml");

    }

    @FXML
    public void UpdateStock(ActionEvent event) throws IOException, InterruptedException {
        GC.ChangeView(event, "Franchisee View - Updating Stock.fxml");
        //timer.wait();

    }

    @FXML
    public void GenerateInvoice(ActionEvent event) throws IOException {
        GC.ChangeView(event, "Franchisee View - Produce Job Invoice.fxml");

    }

    public void ShowNotification() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> {
                    ArrayList<Stock> stock = new ArrayList<Stock>();
                    try {
                        stock = AC.getStock();
                    } catch (SQLException ex) {
                        Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (Stock s : stock) {
                        if (s.getActualItemQuantity() < s.getThreshold()) {
                            Notifications.create().title("Stock Alert!").text(s.getItemDescription() + " Below Threshold").showError();
                        }
                    }
                });
            }
        }, 0);

    }

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

    public void setAC(AccountController ac) {
        this.AC = ac;
    }

    public ObservableList<Job> getJobsList(Customer cust) throws ClassNotFoundException {

        ArrayList<Job> jobs = new ArrayList<Job>();
        try {
            jobs = AC.getJobs();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Job> j = FXCollections.observableArrayList();
        for (Job job : jobs) {
            System.out.println(job.getCustomer().getEmail() + " - " + job.getJobType());
            if (job.getCustomer().getEmail().equals(cust.getEmail())) {
                j.add(job);
            }
        }
        return j;
    }

    public ObservableList<Customer> getCustomerList() {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try {
            customers = AC.getCustomers();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Customer> c = FXCollections.observableArrayList();
        for (Customer cust : customers) {
            c.add(cust);
        }

        return c;
    }

    public void LogOut(ActionEvent event) throws IOException {
        GC.ShowAlert("LogOutAlert.fxml", "Log Out");
    }

    @FXML
    public void ShowJobs(MouseEvent event) throws ClassNotFoundException {
        Customer cust = customerTable.getItems().get(customerTable.getSelectionModel().getFocusedIndex());
        //System.out.println(cust.toString());
        JobsTable.setItems(getJobsList(cust));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setCellValueFactory(new PropertyValueFactory<Job, String>("jobType"));
        status.setCellValueFactory(new PropertyValueFactory<Job, String>("status"));
        customer.setCellValueFactory(new Callback<CellDataFeatures<Job, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Job, String> customer) {
                return new ReadOnlyStringWrapper(customer.getValue().getCustomer().getName());
            }
        });

        mechanic.setCellValueFactory(new Callback<CellDataFeatures<Job, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Job, String> mechanic) {
                return new ReadOnlyStringWrapper(mechanic.getValue().getAssignedMechanic().getName());
            }
        });

        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        post_code.setCellValueFactory(new PropertyValueFactory<Customer, String>("postCode"));
        tel.setCellValueFactory(new PropertyValueFactory<Customer, String>("tel"));
        fax.setCellValueFactory(new PropertyValueFactory<Customer, String>("fax"));

        customerTable.setItems(getCustomerList());

        try {
            if (!jobTypes.exists()) {
                jobTypes.createNewFile();
            }
        } catch (Exception e) {

        }

//        timer.scheduleAtFixedRate(stock, 1000, 1000);
        ShowNotification();

    }
    
  

}
