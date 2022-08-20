/***
 *  Anandkrishna 200476485
 * **/
package com.example.w22comp1011gctest2student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TableViewController implements Initializable {
    int pst;
    @FXML
    private Label saleLabel;

    @FXML
    private Label msrpLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label rowsInTableLabel;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> totalPurchaseColumn;

    @FXML
    private ListView<Product> purchaseListView;

    private JsonConfig jc = new JsonConfig();

    @FXML
    private ImageView imageView;
    private ObservableList<Customer> customersList= FXCollections.observableArrayList();

    @FXML
    private void top10Customers()
    {
        JsonConfig jsonConfig = new JsonConfig();
        List<Customer> customers = jsonConfig.loadCustomers();

        Collections.sort(customers, Comparator.comparingDouble(Customer ::getTotalPurchases).reversed());

        List<Customer> topten = customers.subList(0,10);
        customersList = FXCollections.observableArrayList();
        for (Customer customer :topten) {
                //System.out.println(customer.getPriceString());
                customersList.add(customer);
        }
        tableView.setItems(customersList);
        purchaseListView.getItems().clear();
        rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
    }

    @FXML
    private void customersSavedOver5()
    {   imageView.setImage(null);
        JsonConfig jsonConfig = new JsonConfig();
        List<Customer> customers = jsonConfig.loadCustomers();
        customersList = FXCollections.observableArrayList();
        for (Customer customer :customers) {
            if(customer.isSaved())
                //System.out.println(customer.getPriceString());
                customersList.add(customer);
        }
        tableView.setItems(customersList);
        purchaseListView.getItems().clear();
        rowsInTableLabel.setText("Rows in table: " + tableView.getItems().size());
    }

    @FXML
    private void loadAllCustomers()
    {
        imageView.setImage(null);
        JsonConfig jsonConfig = new JsonConfig();
        List<Customer> customers = jsonConfig.loadCustomers();


        for (Customer customer :customers) {
            customersList.add(customer);
        }

        tableView.setItems(customersList);
        purchaseListView.getItems().clear();
        rowsInTableLabel.setText("Rows returned: "+String.valueOf(customers.size()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        totalPurchaseColumn.setCellValueFactory(new PropertyValueFactory<>("priceString"));

        loadAllCustomers();
    }

    @FXML
    public void oneCustomerClick(Event event) {
        imageView.setImage(null);
        TablePosition position = this.tableView.getFocusModel().getFocusedCell();
        List<Customer> customers = jc.loadCustomers();
        Customer customer = customers.get(position.getColumn());
        pst =position.getColumn();
        ObservableList<Product> dataProduct = FXCollections.observableArrayList();
        List<Product> products = null;
        if(customer.purchases.size() == 0){
            products = new ArrayList<>();
            Product fake = new Product();
            fake.name = "fake";
            fake.salePrice = 1d;
            products.add(fake);
        }else{
            products = customer.purchases;
        }

        for(Product product:products){
            dataProduct.add(product);
        }

        msrpLabel.setText("Total regular price :" + customer.getTotalRegularPrice() + " $");

        saleLabel.setText("Total saling price : " + customer.getTotalPurchases() + " $");

        savingsLabel.setText("Total saving price :" + customer.getTotalSaved() + " $");

        this.purchaseListView.setItems(dataProduct);

    }




    @FXML
    public void viewImage(Event event) throws IOException {
        int position = this.purchaseListView.getFocusModel().getFocusedIndex();
        List<Product> ps = jc.loadProduct();
        List<Customer> customers = jc.loadCustomers();
        Customer customer = customers.get(pst);
        Product p = customer.purchases.get(position);
        Image imProfile = new Image(p.urlImage);
        imageView.setImage(imProfile);
    }
}
