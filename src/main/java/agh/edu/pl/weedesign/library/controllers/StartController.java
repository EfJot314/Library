package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

@Controller
public class StartController {

    public void toBooks(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.BOOK_LIST);
    }

    public void toRentals(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_VIEW);
    }
}
