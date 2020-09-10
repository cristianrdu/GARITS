package org.servlets.garits.GUIs;

import java.io.File;
import org.servlets.garits.Accounts.AccountController;
import org.servlets.garits.Accounts.Franchisee;
import org.servlets.garits.Accounts.Receptionist;
import org.servlets.garits.StockControl.Manufacturer;
import org.servlets.garits.StockControl.Stock;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import org.servlets.garits.Documents.StockInvoice;
import org.servlets.garits.StockControl.Order;
import org.servlets.garits.StockControl.Payments.SparePartPayment;
import org.servlets.garits.StockControl.SparePart;

public class UpdateStock extends controller implements Initializable {

    @FXML
    private Accordion accordion;

    //Defining the spare parts table
    @FXML
    private TableView<Stock> spareParts;
    @FXML
    private TableColumn<Stock, Integer> code;
    @FXML
    private TableColumn<Stock, String> description;
    @FXML
    private TableColumn<Stock, Double> unitCost;
    @FXML
    private TableColumn<Stock, String> vehicleType;
    @FXML
    private TableColumn<Stock, Integer> quantity;
    @FXML
    private TableColumn<Stock, String> manufacturer;
    @FXML
    private TableColumn<Stock, Integer> threshold;

    //Define the orders table
    @FXML
    private TableView<Order> orders;

    @FXML
    private TableColumn<Order, Integer> orderNo;
    @FXML
    private TableColumn<Order, Date> orderDate;
    @FXML
    private TableColumn<Order, Double> orderCost;
    @FXML
    private TableColumn<Order, Integer> orderQuantity;
    @FXML
    private TableColumn<Order, Integer> orderStockCode;

    //Defining the sell parts tab
    @FXML
    private TitledPane sellPartsTab;
    @FXML
    private Label TotalPriceL;
    @FXML
    private ListView<SparePart> sellParts;
    @FXML
    private Button Ok;
    @FXML
    private Label quantLabel;
    @FXML
    private TextField quantityToSell;
    @FXML
    private Button Ok1;
    @FXML
    private Label quantLabel1;
    @FXML
    private TextField quantityToSell1;
    @FXML
    private ChoiceBox paymentMethod;
    @FXML
    private DatePicker payDate;

    //Defining the manufacturers Table
    @FXML
    private TableView<Manufacturer> manTable;
    @FXML
    private TableColumn<Manufacturer, String> name;
    @FXML
    private TableColumn<Manufacturer, String> address;
    @FXML
    private TableColumn<Manufacturer, Double> contact;

    private GUIController GC = new GUIController();
    private AccountController AC = new AccountController();

    private ArrayList<Manufacturer> manArray = new ArrayList<Manufacturer>();

    @FXML
    private ChoiceBox manChoice;

    @FXML
    private TextField descTF;
    @FXML
    private TextField costTF;
    @FXML
    private TextField typeTF;
    @FXML
    private TextField thresholdTF;
    @FXML
    private TextField qttyTF;
    @FXML
    private TextField yearTF;
    @FXML
    private TextField manNameTF;
    @FXML
    private TextField manAddTF;
    @FXML
    private TextField manConTF;

    /**
     *
     * @param action
     */
    @FXML
    public void Refresh(ActionEvent action) {
        // TODO - implement UpdateStock.Refresh
        spareParts.setItems(getStock());

    }

    @FXML
    public void Restock(ActionEvent action) throws IOException {
        // TODO - implement UpdateStock.Refresh
        GC.Restock(spareParts.getItems().get(spareParts.getSelectionModel().getFocusedIndex()));
        spareParts.setItems(getStock());
    }

    @FXML
    public void Reorder(ActionEvent action) throws IOException {
        GC.Reorder(spareParts.getItems().get(spareParts.getSelectionModel().getFocusedIndex()), manTable.getItems());
        spareParts.setItems(getStock());

    }

    @FXML
    public void Back(ActionEvent action) throws IOException {
        if (AC.getUser() instanceof Franchisee) {
            this.notifyAll();
            GC.ChangeView(action, "Franchisee View.fxml");
        } else if (AC.getUser() instanceof Receptionist) {
            GC.ChangeView(action, "Receptionist View.fxml");

        }
    }

    public ObservableList<Stock> getStock() {
        ArrayList<Stock> stock = new ArrayList<Stock>();

        try {
            stock = AC.getStock();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Stock> c = FXCollections.observableArrayList();
        for (Stock sp : stock) {
            c.add(sp);

        }

        return c;
    }

    public ObservableList<Manufacturer> getManufacturers() {

        try {
            manArray = AC.getManufacturers();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptionistView.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Manufacturer> man = FXCollections.observableArrayList();
        for (Manufacturer m : manArray) {
            man.add(m);
            // manChoice.getItems().add(m.getManufacturerName());
        }
        manChoice.setItems(man);
        return man;
    }

    public void setGC(GUIController GC) {
        this.GC = GC;
    }

    public void setAC(AccountController AC) {
        this.AC = AC;
    }

    public void LogOut(ActionEvent event) throws IOException {
        GC.ShowAlert("LogOutAlert.fxml", "Log Out");
    }

    @FXML
    public void AddSparePart(ActionEvent event) throws SQLException {
        Stock s = new Stock();
        s.setInitialItemQuantity(Integer.parseInt(qttyTF.getText()));
        s.setActualItemQuantity(Integer.parseInt(qttyTF.getText()));
        s.setUseditemQuantity(0);
        s.setThreshold(Integer.parseInt(thresholdTF.getText()));
        s.setItemDescription(descTF.getText());
        s.setVehicleType(typeTF.getText());
        s.setUnitCost(Double.parseDouble(costTF.getText()));
        s.setYears(yearTF.getText());
        Manufacturer m = (Manufacturer) manChoice.getValue();
        m.setManufacturerName(m.getManufacturerName());
        s.setManufacturer(m);
        AC.addSparePart(s);
        spareParts.setItems(getStock());
        qttyTF.clear();
        thresholdTF.clear();
        yearTF.clear();
        descTF.clear();
        costTF.clear();
        typeTF.clear();

    }

    @FXML
    public void AddToSalesList(ActionEvent event) {
        Ok.setVisible(true);
        quantityToSell.setVisible(true);
        quantLabel.setVisible(true);

    }

    @FXML
    public void OK(ActionEvent event) {

        if (Integer.parseInt(quantityToSell.getText()) <= spareParts.getItems().get(spareParts.getSelectionModel().getFocusedIndex()).getActualItemQuantity()) {

            sellPartsTab.setVisible(true);
            sellPartsTab.setCollapsible(true);

            accordion.setExpandedPane(sellPartsTab);
            SparePart selP = new SparePart(spareParts.getItems().get(spareParts.getSelectionModel().getFocusedIndex()), Integer.parseInt(quantityToSell.getText()));
            sellParts.getItems().add(selP);
            double tmp = 0;
            for (SparePart s : sellParts.getItems()) {
                tmp = tmp + s.getTotalCost();
            }
            TotalPriceL.setText("" + tmp);
            Ok.setVisible(false);
            quantityToSell.setVisible(false);
            quantLabel.setVisible(false);
            quantityToSell.clear();
        } else {
            Notifications.create().title("Not Enough Quantity").position(Pos.CENTER).showError();
        }
    }

    @FXML
    public void OK2(ActionEvent event) {
        if (Integer.parseInt(quantityToSell1.getText()) <= sellParts.getSelectionModel().getSelectedItem().getStock().getActualItemQuantity()) {
            SparePart sp = sellParts.getSelectionModel().getSelectedItem();
            sellParts.getItems().remove(sellParts.getSelectionModel().getSelectedItem());
            sp.setQuantity(Integer.parseInt(quantityToSell1.getText()));
            sellParts.getItems().add(sp);
            double tmp = 0;
            for (SparePart s : sellParts.getItems()) {
                tmp = tmp + s.getTotalCost();
            }
            TotalPriceL.setText("" + tmp);
            Ok1.setVisible(false);
            quantityToSell1.setVisible(false);
            quantLabel1.setVisible(false);
            quantityToSell1.clear();
        } else {
            Notifications.create().title("Not Enough Quantity").position(Pos.CENTER).showError();
        }

    }

    @FXML
    public void EditQuantity(ActionEvent event) {
        Ok1.setVisible(true);
        quantityToSell1.setVisible(true);
        quantLabel1.setVisible(true);
    }

    @FXML
    public void ConfirmSale(ActionEvent event) throws SQLException {

        SparePartPayment payment = new SparePartPayment();
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

        payment.setPaymentMethod((String) paymentMethod.getValue());
        ArrayList<SparePart> sp = new ArrayList<SparePart>();
        for (SparePart s : sellParts.getItems()) {
            sp.add(s);
        }
        payment.setSp(sp);
        payment.setPaymentDate(Date.valueOf(payDate.getValue()));
        StockInvoice invoice = new StockInvoice(payment, file);
        invoice.CreatePdf();
        AC.ProcessPayment(payment);
        spareParts.setItems(getStock());
        System.out.println("Confirm");
        sellParts.getItems().clear();
        TotalPriceL.setText("0");
        accordion.setExpandedPane(null);
        sellPartsTab.setCollapsible(false);
        sellPartsTab.setVisible(false);
    }

    @FXML
    public void RemoveFromSellList(ActionEvent event) {
        sellParts.getItems().remove(sellParts.getSelectionModel().getSelectedItem());
        double tmp = 0;
        for (SparePart s : sellParts.getItems()) {
            tmp = tmp + s.getTotalCost();
        }
        TotalPriceL.setText("" + tmp);
    }

    @FXML
    public void CancelSale(ActionEvent event
    ) {
        sellParts.getItems().clear();
        TotalPriceL.setText("0");
        accordion.setExpandedPane(null);
        sellPartsTab.setCollapsible(false);
        sellPartsTab.setVisible(false);
    }

    @FXML
    public void AddManufacturer(ActionEvent event) throws SQLException {
        Manufacturer man = new Manufacturer();
        man.setManufacturerName(manNameTF.getText());
        man.setAddress(manAddTF.getText());
        man.setEmailAddress(manConTF.getText());
        AC.AddManufacturer(man);
        manTable.setItems(getManufacturers());
        manNameTF.clear();
        manAddTF.clear();
        manConTF.clear();
    }

    @FXML
    public void UpdateThreshold(ActionEvent event) throws IOException {
        GC.UpdateThreshold(spareParts.getItems().get(spareParts.getSelectionModel().getFocusedIndex()));
        spareParts.setItems(getStock());

    }
    
    public ObservableList<Order> GetAllOrders() {
        ArrayList<Order> orders = new ArrayList<Order>();

        try {
            orders = AC.getOrders();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateStock.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Order> o = FXCollections.observableArrayList();
        for (Order or : orders) {
            o.add(or);
        }

        return o;
    }
    @FXML
    public void RefreshOrders(ActionEvent event) {
        orders.setItems(GetAllOrders());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Defining the values of the spare part table columns
        code.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("code"));
        description.setCellValueFactory(new PropertyValueFactory<Stock, String>("itemDescription"));
        unitCost.setCellValueFactory(new PropertyValueFactory<Stock, Double>("unitCost"));
        quantity.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("actualItemQuantity"));
        threshold.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("threshold"));
        vehicleType.setCellValueFactory(new PropertyValueFactory<Stock, String>("vehicleType"));
        manufacturer.setCellValueFactory(new Callback<CellDataFeatures<Stock, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Stock, String> customer) {
                return new ReadOnlyStringWrapper(customer.getValue().getManufacturer().getManufacturerName());
            }
        });
        //Populating the spare parts table
        spareParts.setItems(getStock());

        //Defining the values of the manufacture table columns
        name.setCellValueFactory(new PropertyValueFactory<Manufacturer, String>("manufacturerName"));
        address.setCellValueFactory(new PropertyValueFactory<Manufacturer, String>("address"));
        contact.setCellValueFactory(new PropertyValueFactory<Manufacturer, Double>("emailAddress"));
        //Populating the Manufacturers Table
        manTable.setItems(getManufacturers());

        //Defining the values of the orders table
        orderNo.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderNumber"));
        orderDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("date"));
        orderCost.setCellValueFactory(new PropertyValueFactory<Order, Double>("cost"));
        orderQuantity.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        orderStockCode.setCellValueFactory(new PropertyValueFactory<Order, Integer>("stockCode"));
        //Populating the orders Table
        orders.setItems(GetAllOrders());

        sellPartsTab.setCollapsible(false);
        sellPartsTab.setVisible(false);
        Ok.setVisible(false);
        quantityToSell.setVisible(false);
        quantLabel.setVisible(false);

        Ok1.setVisible(false);
        quantityToSell1.setVisible(false);
        quantLabel1.setVisible(false);
    }
}