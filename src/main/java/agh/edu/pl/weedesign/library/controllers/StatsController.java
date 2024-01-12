package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;

@Controller
public class StatsController {

    public void handleRegisterButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.REGISTER);
    }

}
