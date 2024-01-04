package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeePanelController {
    @FXML
    private Button addEmployee;
    @FXML
    private Button addNewBook;

    public void handleAddNewBookAction(ActionEvent actionEvent) {
        LibraryApplication.getAppController().switchScene(SceneType.NEW_BOOK_VIEW);
    }

    public void handleAddEmployeeAction(ActionEvent actionEvent) {
        LibraryApplication.getAppController().switchScene(SceneType.ADD_EMPLOYEE);
    }
}
