package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.employee.AccessLevel;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeePanelController {
    public Text errorMsg;
    @FXML
    private Button addEmployee;
    @FXML
    private Button addNewBook;
    @FXML
    private Button showStatsButton;

    public void handleAddNewBookAction(ActionEvent actionEvent) {
        if (LibraryApplication.getEmployee().getAccessLevel().ordinal() >= AccessLevel.EMPLOYEE.ordinal()){
            LibraryApplication.getAppController().switchScene(SceneType.NEW_BOOK_VIEW);
        }
        else{
            errorMsg.setText("Nie masz uprawnień, aby stworzyć nową książkę!");
        }
    }

    public void handleAddEmployeeAction(ActionEvent actionEvent) {
        if (LibraryApplication.getEmployee().getAccessLevel().ordinal() >= AccessLevel.MANAGER.ordinal()){
            LibraryApplication.getAppController().switchScene(SceneType.ADD_EMPLOYEE);
        }
        else{
            errorMsg.setText("Nie masz uprawnień, aby dodać nowego pracownika!");
        }
    }

    public void showStats(ActionEvent actionEvent) {
        LibraryApplication.getAppController().switchScene(SceneType.STATS_VIEW);
    }

    public void acceptRental(ActionEvent actionEvent) {
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_ACCEPTANCE);
    }

    public void logoutAction(ActionEvent e){
        LibraryApplication.setEmployee(null);
        LibraryApplication.getAppController().switchScene(SceneType.WELCOME);
    }
}
