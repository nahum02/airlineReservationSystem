package org.example.UI;


import bizlogic.Flights;
import DB.FlightsData;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class manageFlightsController extends flightsController implements Initializable {
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
    private TextField departLocation;

    @FXML
    private TextField arrivalTo;

    @FXML
    private TextField airline;

    @FXML
    private TextField seatPrice;

    @FXML
    private Label lblNotFilled;

    @FXML
    private Label lblDelete;

    ObservableList<Flights> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = FlightsData.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from flights");
            while (rs.next()) {
                observableList.add(new Flights(
                        rs.getString("flightNum"),
                        rs.getString("date"),
                        rs.getString("departureTime"),
                        rs.getString("departureLocation"),
                        rs.getString("arrivalLocation"),
                        rs.getString("airline"),
                        rs.getString("seatPrice")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        colFlightNum.setCellValueFactory(new PropertyValueFactory<>("FlightNum"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("FlightDate"));
        colDepartureTime.setCellValueFactory(new PropertyValueFactory<>("DepartTime"));
        colDepartFrom.setCellValueFactory(new PropertyValueFactory<>("DepartFrom"));
        colArrivalTo.setCellValueFactory(new PropertyValueFactory<>("ArrivalTo"));
        colAirline.setCellValueFactory(new PropertyValueFactory<>("Airline"));
        colSeatPrice.setCellValueFactory(new PropertyValueFactory<>("SeatPrice"));

        tableview.setItems(observableList);
    }

    public void backToAdminTerminalButtonClicked(ActionEvent event) throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/frontend/AdminPage.fxml"));
        Scene registerScene = new Scene(register);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

    public void addFlightsBtnClicked(ActionEvent event) throws Exception {
        if (flightFilledCorrect(
                flightNum.getText(),
                flightDate.getText(),
                departTime.getText(),
                departLocation.getText(),
                arrivalTo.getText(),
                airline.getText(),
                seatPrice.getText())) {

            addFlights();
            Parent registers = FXMLLoader.load(getClass().getResource("/frontend/AdminPage.fxml"));
            Scene registerScene = new Scene(registers);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.show();
        } else {
            lblNotFilled.setText("One or more fields are empty.");
        }
    }

    public void deleteFlightBtnClicked(ActionEvent event) throws Exception {
        Flights flight = tableview.getSelectionModel().getSelectedItem();
        if (flight == null) {
            lblNotFilled.setText("Please select a flight first.");
        } else {
            String data = flight.getFlightNum();
            if (checkCustomerBookings(data)) {
                deleteFlightBooks(data);
                deleteFlight(data);
            } else {
                deleteFlight(data);

                lblNotFilled.setText("Flight deleted.");
            }
        }
    }

    public static ObservableList<Flights> getSearch(String date, String from, String to)
            throws ClassNotFoundException, SQLException {

        ObservableList<Flights> observableList = FXCollections.observableArrayList();

        String str = date;
        String str1 = from;
        String str2 = to;

        PreparedStatement myStmt = null;
        ResultSet rs = null;
        String sql = " select * from flights where departLocation = " + "'" + str1 + "'"
                + "and arrivalDestination = " + "'" + str2 + "'" + " and date = " + "'" + str + "'";

        try {

            Connection con = FlightsData.getConnection();
            myStmt = con.prepareStatement(sql);

            rs = myStmt.executeQuery();

            while(rs.next()) {
                observableList.add(new Flights(rs.getString("flightNum"), rs.getString("date"),
                        rs.getString("departureTime"), rs.getString("departLocation"),
                        rs.getString("arrivalDestination"), rs.getString("airline"),
                        rs.getString("seatPrice")));

            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return observableList;
    }



    public void mainMenuButtonClicked(ActionEvent event) throws Exception {
        Parent register = FXMLLoader.load(getClass().getResource("/frontend/run.fxml"));
        Scene registerScene = new Scene(register);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }



}
