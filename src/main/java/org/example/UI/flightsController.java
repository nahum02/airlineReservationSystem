package org.example.UI;

import bizlogic.Customer;
import bizlogic.Flights;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class flightsController extends loginController implements Initializable {
    @FXML
    private TableView<Flights> tableview;
    @FXML
    private TableColumn<Flights, Integer> colFlightNum;
    @FXML
    private TableColumn<Flights, String> colDate;
    @FXML
    private TableColumn<Flights, String> colDepartureTime;
    @FXML
    private TableColumn<Flights, String> colDepartFrom;
    @FXML
    private TableColumn<Flights, String> colArrivalTo;
    @FXML
    private TableColumn<Flights, String> colAirline;
    @FXML
    private TableColumn<Flights, Double> colSeatPrice;
    @FXML
    private TextField flightNum;
    @FXML
    private TextField flightDate;
    @FXML
    private TextField departTime;
    @FXML
    private TextField departureLocation;
    @FXML
    private TextField arrivalLocation;
    @FXML
    private TextField airline;
    @FXML
    private TextField seatPrice;
    @FXML
    private Label lblNotFilled;
    @FXML
    private TextField custDepartDate;
    @FXML
    private TextField custDepartFrom;
    @FXML
    private TextField custArrivalTo;
    @FXML
    private Label lblflightBooked;
    @FXML
    private Label lblFlightNotSelected;


    protected String date;
    protected String from;
    protected String to;

    ObservableList<Flights> observableList = FXCollections.observableArrayList();

    public void myFlightsButtonClicked(ActionEvent event) throws Exception {
        try {
            Parent register = FXMLLoader.load(getClass().getResource("/frotend/bookingpage.fxml"));
            Scene registerScene = new Scene(register);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteMyFlightBtnClicked(ActionEvent event) throws Exception {

        Flights flight = tableview.getSelectionModel().getSelectedItem();

        if(flight == null) {
            lblFlightNotSelected.setText("Please select a flight first.");
        }

        else {
            String data = flight.getFlightNum();
            Customer customer = new Customer();
            int id = customer.getCustomerID();
            deleteBook(id,data);
            lblFlightNotSelected.setText("Flight deleted.");
        }
    }

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
