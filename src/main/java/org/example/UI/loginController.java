package org.example.UI;

import DB.AdminData;
import bizlogic.Customer;
import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.logging.Logger;
import java.util.logging.Level;


public class loginController extends Customer {
    @FXML
    private Label lblErrors;
    @FXML
    private Label lblNotFilled;
    @FXML
    private Label lblUserTaken;
    @FXML
    private Label lblRetrievePass;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private TextField address;
    @FXML
    private TextField zipcode;
    @FXML
    private TextField email;
    @FXML
    private TextField state;
    @FXML
    private TextField securityAnswer;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField ssn;
    @FXML
    private TextField birthMonth;
    @FXML
    private TextField birthDay;
    @FXML
    private TextField birthYear;

    @FXML
    private Stage stage;

    private int custID;


    @FXML
    public void registerBtnClicked(ActionEvent event) throws IOException {
        Logger logger = Logger.getLogger(loginController.class.getName());

        try {
            logger.log(Level.INFO, "Attempting to load FXML file");


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontEnd/register.fxml"));
            Parent root = loader.load();
            logger.log(Level.INFO, "FXML file loaded successfully");

            Scene registerScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(registerScene);
            window.show();
            logger.log(Level.INFO, "Scene set and window shown");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading FXML file", e);
            e.printStackTrace();
        }
    }

    public void signUpBtnClicked(ActionEvent event) throws Exception {
        String birthday = birthYear.getText() + "/" + birthMonth.getText() + "/" + birthDay.getText();

        if (filledCorrect(firstName.getText(), lastName.getText(), email.getText(), address.getText(),
                user.getText(), pass.getText(), ssn.getText(), securityAnswer.getText(),
                zipcode.getText(), state.getText(), birthday)) {

            if (checkUser(user.getText())) {

                Parent registers = FXMLLoader.load(getClass().getResource("run.fxml"));
                Scene registerScene = new Scene(registers);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(registerScene);
                window.show();
            } else {
                lblUserTaken.setText("Username is already taken.");
            }
        } else {
            lblNotFilled.setText("One or more fields are empty.");
        }

    }
}
