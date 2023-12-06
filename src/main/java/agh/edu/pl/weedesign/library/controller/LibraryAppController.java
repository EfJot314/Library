package agh.edu.pl.weedesign.library.controller;

import agh.edu.pl.weedesign.library.LibraryApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class LibraryAppController {
    private Stage primaryStage;
    private BorderPane welcomeLayout;
    private BorderPane loginLayout;
    private BorderPane registerLayout;
    private BorderPane mainLayout;

    ConfigurableApplicationContext springContext;

    public LibraryAppController(Stage stage, ConfigurableApplicationContext springContext){
        this.primaryStage = stage;
        this.springContext = springContext;
    }

    public void initWelcomeLayout() {
        try {
            this.primaryStage.setTitle("Biblioteka");

            // load layout from FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LibraryApplication.class.getResource("/view/registerView.fxml"));
            loader.setControllerFactory(springContext::getBean);
            this.welcomeLayout = loader.load();

            // add layout to a scene and show them all
            Scene scene = new Scene(this.welcomeLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
            e.printStackTrace();
        }
    }
    @FXML
    private void handleEnterLoginAction(){

    }

    @FXML
    private void handleEnterRegisterAction(){

    }
}
