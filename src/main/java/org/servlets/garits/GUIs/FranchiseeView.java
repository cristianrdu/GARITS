package org.servlets.garits.GUIs;




import java.io.File;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Customer;
import org.servlets.garits.DAO.CustomerDAO;
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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.swing.JButton;
import org.controlsfx.control.Notifications;
import org.servlets.garits.Job.Job;
import org.servlets.garits.StockControl.Stock;


 public class FranchiseeView extends controller implements Initializable{
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
        private Label nameLabel;
	private MenuBar menu;
        
         CustomerDAO DAO;

        GUIController GC ;
        AccountController AC;
        File jobTypes = new File("JobTypes.txt");
    private static Timer timer = new Timer();

    TimerTask stock = new TimerTask() {

        @Override
        public void run() {
            ShowNotification();
        }
    };
    

        @FXML
	public void AddCustomerOnClick(ActionEvent event) throws IOException {
		// TODO - implement FranchiseeView.AddCustomerOnClick
                 System.out.println("AddCustomer");
                GC.ChangeView(event, "Franchisee View - Add cutomer.fxml");
	}

        @FXML
	public void EditCustomerOnClick(ActionEvent event) throws IOException {
		// TODO - implement FranchiseeView.EditCustomerOnClick
                Customer c = customerTable.getItems().get(customerTable.getSelectionModel().getFocusedIndex());
                GC.EditCustomer(event, c);
        }

        @FXML
	public void AddJob(ActionEvent event) throws IOException {
		// TODO - implement FranchiseeView.AddJobOnClick
                 System.out.println("AddJob");
                 GC.ChangeView(event, "Franchisee View - Add-Process pending job.fxml");
    }

        @FXML
	public void GenerateReportOnClick(ActionEvent event) throws IOException{
		// TODO - implement FranchiseeView.GenerateReportOnClick
                 System.out.println("GenerateReport");
                GC.ChangeView(event, "Franchisee View - Generate Reports.fxml");
	}

        @FXML
	public void UpdateStockOnClick(ActionEvent event) throws IOException {
		// TODO - implement FranchiseeView.UpdateStockOnClick
                 System.out.println("UpdateStock");
                GC.ChangeView(event, "Franchisee View - Updating Stock.fxml");
	}
        @FXML
    public void ShowJobs(MouseEvent event) throws ClassNotFoundException {
        Customer cust = customerTable.getItems().get(customerTable.getSelectionModel().getFocusedIndex());
        //System.out.println(cust.toString());
        JobsTable.setItems(getJobsList(cust));
    }
        @FXML
	public void Discounts(ActionEvent event) throws IOException {
		// TODO - implement FranchiseeView.UpdateStockOnClick
                 System.out.println("Discount");
                GC.ChangeView(event, "Franchisee View - Discount.fxml");
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

        public void setGC(GUIController GC){
            this.GC = GC;
        }
        public void setAC(AccountController ac){
        this.AC = ac;
                customerTable.setItems(getCustomerList());

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
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setCellValueFactory(new PropertyValueFactory<Job, String>("jobType"));
        status.setCellValueFactory(new PropertyValueFactory<Job, String>("status"));
        customer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> customer) {
                return new ReadOnlyStringWrapper(customer.getValue().getCustomer().getName());
            }
        });

        mechanic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Job, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Job, String> mechanic) {
                return new ReadOnlyStringWrapper(mechanic.getValue().getAssignedMechanic().getName());
            }
        });

        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        post_code.setCellValueFactory(new PropertyValueFactory<Customer, String>("postCode"));
        tel.setCellValueFactory(new PropertyValueFactory<Customer, String>("tel"));
        fax.setCellValueFactory(new PropertyValueFactory<Customer, String>("fax"));


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
 