package agh.edu.pl.weedesign.library.controller;

import agh.edu.pl.weedesign.library.model.ModelService;
import agh.edu.pl.weedesign.library.model.reader.Reader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Component
public class AuthController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField voivodeshipField;
    @FXML
    private TextField postcodeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker birthDateField;
    @FXML
    private TextField sexField;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    @Autowired
    private ModelService service;


    @FXML
    private void handleRegisterAction(ActionEvent event){
        Reader newReader = new Reader(nameField.getText(), surnameField.getText(), cityField.getText(),
                voivodeshipField.getText(),postcodeField.getText(),countryField.getText(),emailField.getText(),
                passwordField.getText(),phoneField.getText(), birthDateField.getValue(),sexField.getText());
        //printFields();
        service.addNewReader(newReader);
    }

    @FXML
    private void handleCancelAction(ActionEvent event){
        System.out.println("Back button clicked!");
        //printFields();
    }


    private void printFields(){
        System.out.println("---------------------FORM----------------------");
        System.out.println("Name: " + nameField.getText());
        System.out.println("Surname: " + surnameField.getText());
        System.out.println("Password: " + passwordField.getText());
        System.out.println("Country: " + countryField.getText());
        System.out.println("City: " + cityField.getText());
        System.out.println("Voivodeship: " + voivodeshipField.getText());
        System.out.println("Postcode: " + postcodeField.getText());
        System.out.println("Email: " + emailField.getText());
        System.out.println("Phone: " + phoneField.getText());
        System.out.println("Birth date: " + birthDateField.getValue());
        System.out.println("Sex: " + sexField.getText());
    }
}
