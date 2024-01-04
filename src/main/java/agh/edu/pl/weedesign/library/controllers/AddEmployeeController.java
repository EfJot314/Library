package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.services.ModelService;
import agh.edu.pl.weedesign.library.entities.employee.AccessLevel;
import agh.edu.pl.weedesign.library.entities.employee.Employee;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddEmployeeController {
    @FXML
    private ChoiceBox<Employee> reportsToChoiceBox;
    @FXML
    private ChoiceBox<AccessLevel> accessLevelChoiceBox;

    private final ModelService modelService;
    private final AuthController authController;

    public AddEmployeeController(ModelService modelService, AuthController authController) {
        this.modelService = modelService;
        this.authController = authController;
    }
    @FXML
    private void initialize() {
        List<Employee> employees = this.modelService.getEmployees();
        reportsToChoiceBox.setItems(FXCollections.observableArrayList(employees));
        accessLevelChoiceBox.setItems(FXCollections.observableArrayList(AccessLevel.values()));
    }
    @FXML
    private void handleAddAction(){

    }
    @FXML
    private void handleCancelAction(){

    }
}
