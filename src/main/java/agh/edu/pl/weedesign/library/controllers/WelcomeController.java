package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController {

    public Button loginButton;
    public Button registerButton;

    @FXML
    private BorderPane borderPane;

    public void handleRegisterButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene((new SceneFactory()).createScene(SceneType.REGISTER));
    }

    public void handleLoginButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene((new SceneFactory()).createScene(SceneType.LOGIN));
    }
}
