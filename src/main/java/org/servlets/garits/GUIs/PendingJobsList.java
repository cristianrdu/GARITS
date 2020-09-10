package org.servlets.garits.GUIs;

import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.Accounts.Mechanic;
import org.servlets.garits.Accounts.Vehicle;
import org.servlets.garits.Job.Job;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PendingJobsList extends controller implements Initializable {

    @FXML
    TableView<Mechanic> mechanics;
    @FXML
    TableColumn<Mechanic, String> mname;
    @FXML
    TableColumn<Mechanic, Double> hourlyRate;

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
    TableView<Vehicle> Vehicles;
    @FXML
    TableColumn<Vehicle, String> make;
    @FXML
    TableColumn<Vehicle, String> model;
    @FXML
    TableColumn<Vehicle, Integer> year;
    @FXML
    TableColumn<Vehicle, String> regNo;
    @FXML
    TableColumn<Vehicle, String> colour;
    @FXML
    ChoiceBox type = new ChoiceBox();
    @FXML
    TextField customerName;
    @FXML
    DatePicker BookingDate;
    @FXML
    DatePicker AppointmentDate;

    Job job = new Job();
    Vehicle v = new Vehicle();

    private GUIController GC;
    private AccountController AC = new AccountController();
    File jobTypes = new File("JobTypes.txt");

    /**
     *
     * @param action
     */
    @FXML
    public void AddJob(ActionEvent action) throws SQLException {
        // TODO - implement PendingJobsList.AddJob
        job.setBookingDate(Date.valueOf(BookingDate.getValue()));
        job.setCompletionDate(Date.valueOf(AppointmentDate.getValue()));
        job.setJobType((String) type.getValue());
        AC.AddJob(job, v.getRegNo());
        //System.out.println(job.toString());
    }

    /**
     *
     * @param action
     */
    public void RemoveJob(ActionEvent action) {
        // TODO - implement PendingJobsList.RemoveJob
    }

    /**
     *
     * @param action
     */
    public void ManageJob(ActionEvent action) {
        // TODO - implement PendingJobsList.ManageJob
    }

    @FXML
    public void assignMechanic(ActionEvent action) {
        job.setAssignedMechanic(mechanics.getItems().get(mechanics.getSelectionModel().getFocusedIndex()));
    }

    /**
     *
     * @param action
     */
    public void Refresh(ActionEvent action) {
        // TODO - implement PendingJobsList.Refresh
    }

    @FXML
    public void Back(ActionEvent action) throws IOException {
        GC.ChangeView(action, "Receptionist View.fxml");
    }

    @FXML
    public void ShowVehicles(MouseEvent event) {
        Customer cust = customerTable.getItems().get(customerTable.getSelectionModel().getFocusedIndex());
        Vehicles.setItems(getVehicles(cust));
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

    public ObservableList<Mechanic> getMechanics() throws ClassNotFoundException {
        ArrayList<Mechanic> mechanics = new ArrayList<Mechanic>();

        try {
            mechanics = AC.findAllMechanics();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Mechanic> mech = FXCollections.observableArrayList();
        for (Mechanic m : mechanics) {
            mech.add(m);
        }

        return mech;
    }

    public ObservableList<Vehicle> getVehicles(Customer cust) {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            vehicles = AC.getCustomerVehicles(cust.getEmail());
        } catch (SQLException ex) {
            Logger.getLogger(PendingJobsList.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Vehicle> v = FXCollections.observableArrayList();
        for (Vehicle ve : vehicles) {
            v.add(ve);
        }
        return v;
    }

    public ObservableList<String> getJobTypes() throws FileNotFoundException, IOException {
        ArrayList<String> t = new ArrayList<String>();
        ObservableList<String> to = FXCollections.observableArrayList();

        if (jobTypes.exists()) {
            System.out.println("Found it!");

            RandomAccessFile f = new RandomAccessFile(jobTypes, "r");
            String tmp = f.readLine();
            while (tmp != null) {
                t.add(tmp);
               // System.out.println(tmp);
                tmp = f.readLine();
            }

            for (String s : t) {
                to.add(s);
            }
        } else {
            System.out.println("Can't find it");
            jobTypes.createNewFile();
        }

        return to;
    }

    @FXML
    public void ChooseCustomer(ActionEvent event) {
        job.setCustomer(customerTable.getItems().get(customerTable.getSelectionModel().getFocusedIndex()));
        customerName.setText(job.getCustomer().getName());
        v = Vehicles.getItems().get(Vehicles.getSelectionModel().getFocusedIndex());
    }

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

    public void setAC(AccountController ac) {
        this.AC = ac;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mname.setCellValueFactory(new PropertyValueFactory<Mechanic, String>("name"));
        hourlyRate.setCellValueFactory(new PropertyValueFactory<Mechanic, Double>("hourlyRate"));

        try {
            mechanics.setItems(getMechanics());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PendingJobsList.class.getName()).log(Level.SEVERE, null, ex);
        }

        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        post_code.setCellValueFactory(new PropertyValueFactory<Customer, String>("postCode"));
        tel.setCellValueFactory(new PropertyValueFactory<Customer, String>("tel"));
        fax.setCellValueFactory(new PropertyValueFactory<Customer, String>("fax"));

        customerTable.setItems(getCustomerList());

        make.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));
        model.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        regNo.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("regNo"));
        year.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("year"));
        colour.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("colour"));

        try {
            type.setItems(getJobTypes());
            System.out.println("Hello");
        } catch (IOException ex) {
            Logger.getLogger(PendingJobsList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
