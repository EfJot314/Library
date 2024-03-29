package agh.edu.pl.weedesign.library.controllers;

import org.springframework.stereotype.Controller;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

@Controller
public class WelcomeController {

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private ToggleButton themeButton; 

    @FXML
    private BorderPane borderPane;

    public void handleRegisterButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.REGISTER);
    }

    public void handleLoginButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.LOGIN);

    }

    public void changeTheme(){
        LibraryApplication.changeTheme("");
    }
}
