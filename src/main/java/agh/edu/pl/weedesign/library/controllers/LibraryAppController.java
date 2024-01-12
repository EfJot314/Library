package agh.edu.pl.weedesign.library.controllers;


import org.springframework.context.ConfigurableApplicationContext;

import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibraryAppController {
    private Stage primaryStage;
    private Scene currentScene;
    private SceneFactory factory;

    ConfigurableApplicationContext springContext;

    public LibraryAppController(Stage stage, ConfigurableApplicationContext springContext){
        this.primaryStage = stage;
        this.springContext = springContext;
        factory = new SceneFactory();
    }

    public void initWelcomeLayout() {
        this.primaryStage.setTitle("Biblioteka");

        currentScene = new Scene(factory.createScene(SceneType.WELCOME));
//        currentScene = new Scene(factory.createScene(SceneType.EMPLOYEE_PANEL));
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public void switchScene(SceneType sceneType){
        currentScene.setRoot(factory.createScene(sceneType));
    }

    public void saveData(Object obj){
        this.currentScene.setUserData(obj);
    }

    public Object getData(){
        return this.currentScene.getUserData();
    }

    public void resize(int width, int height){
        this.primaryStage.setWidth(width);
        this.primaryStage.setHeight(height);
    }

}
