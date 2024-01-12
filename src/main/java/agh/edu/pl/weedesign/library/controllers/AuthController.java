package agh.edu.pl.weedesign.library.controllers;

import java.util.Objects;

import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.reader.Reader;
import agh.edu.pl.weedesign.library.helpers.Encryptor;
import agh.edu.pl.weedesign.library.helpers.RegistrationValidChecker;
import agh.edu.pl.weedesign.library.helpers.ValidCheck;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


@Component
public class AuthController {

    // registration view
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

    // login view
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private CheckBox employeeLoginCheckBox;

    @Autowired
    private ModelService service;
    @Autowired
    private RegistrationValidChecker checker;


    @Autowired
    private Encryptor passwordEncryptor;


    @FXML
    private void handleRegisterAction(ActionEvent event){
        // disabling button for security reasons
        registerButton.setDisable(true);
        cancelButton.setDisable(true);
        try {
            checkerGuard(checker.isRegisterNameValid(nameField.getText()));
            checkerGuard(checker.isRegisterSurnameValid(nameField.getText()));
            checkerGuard(checker.isRegisterCityValid(cityField.getText()));
            checkerGuard(checker.isRegisterVoivodeshipValid(cityField.getText()));
            checkerGuard(checker.isRegisterPostcodeValid(postcodeField.getText()));
            checkerGuard(checker.isRegisterCountryValid(countryField.getText()));
            checkerGuard(checker.isRegisterEmailValid(emailField.getText()));
            checkerGuard(checker.isRegisterPasswordValid(passwordField.getText()));
            checkerGuard(checker.isPhoneNumberValid(phoneField.getText()));
            checkerGuard(checker.isRegisterBirthdateValid(birthDateField.getValue()));
            checkerGuard(checker.isRegisterSexValid(sexField.getText()));
        } catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
            return;
        }
        finally {
            registerButton.setDisable(false);
            cancelButton.setDisable(false);
        }

        Reader newReader = new Reader(nameField.getText(), surnameField.getText(), cityField.getText(),
                voivodeshipField.getText(),postcodeField.getText(),countryField.getText(),emailField.getText(),
                passwordEncryptor.encryptMessage(passwordField.getText()),phoneField.getText(), birthDateField.getValue(),sexField.getText());

        service.addNewReader(newReader);
        hopToNextScene(SceneType.LOGIN);

    }

    public void handleLoginAction(ActionEvent event) {
        String login = loginField.getText();
        boolean employeeLogin = employeeLoginCheckBox.isSelected();
        String encryptedRealPassword = employeeLogin ? service.getEmployeePasswordByEmail(login) : service.getReaderPasswordByEmail(login);

        String encryptedUserPassword = passwordEncryptor.encryptMessage(loginPasswordField.getText());

        //reader login
        if (encryptedRealPassword == null) {
            System.out.println("Wrong login or password");
        }
        else if (!Objects.equals(encryptedUserPassword, encryptedRealPassword)){
            System.out.println("Wrong login or password");
        }
        //employee login
        else if (Objects.equals(encryptedUserPassword, encryptedRealPassword) && employeeLogin) {
            System.out.println("Access granted");
            LibraryApplication.setEmployee(this.service.getEmployeeByEmail(login));
            hopToNextScene(SceneType.EMPLOYEE_PANEL);
        }
        else if (Objects.equals(encryptedUserPassword, encryptedRealPassword) && !employeeLogin) {
            System.out.println("Access granted");
            LibraryApplication.setReader(this.service.getReaderByEmail(login));
            hopToNextScene(SceneType.START_VIEW);
        }

    }

    public void hopToNextScene(SceneType sceneType){
        LibraryApplication.getAppController().switchScene(sceneType);
    }

    public void checkerGuard(ValidCheck check){
        if (!check.isValid()){
            throw new IllegalArgumentException(check.message());
        }
    }

    @FXML
    private void handleCancelAction(ActionEvent event){
        LibraryApplication.getAppController().switchScene(SceneType.WELCOME);
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
