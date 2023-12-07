package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController {

    public void handleRegisterButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene((new SceneFactory()).createScene(SceneType.REGISTER));
    }

    public void handleLoginButton(ActionEvent e){
        LibraryApplication.getAppController().switchScene((new SceneFactory()).createScene(SceneType.LOGIN));
    }
}
