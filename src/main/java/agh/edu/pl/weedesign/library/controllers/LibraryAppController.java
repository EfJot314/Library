package agh.edu.pl.weedesign.library.controllers;
import org.springframework.context.ConfigurableApplicationContext;
import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.scene.Parent;
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
        currentScene = new Scene(factory.createScene(SceneType.BOOK_LIST));
        LibraryApplication.getAppController().resize(1000, 800);

        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public void switchScene(SceneType sceneType){
        currentScene.setUserData(currentScene.getRoot());
        currentScene.setRoot(factory.createScene(sceneType));
        LibraryApplication.getAppController().resize(1000, 800);
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

    public void back(){
        // Poprzednia scena jest przechowywana na razie w UserDataSceny
        currentScene.setRoot((Parent)currentScene.getUserData());
    }

    public void forward(){
        currentScene.setRoot((Parent)currentScene.getUserData());
    }

    public void logOut(){
        switchScene(SceneType.LOGIN);
        LibraryApplication.setEmployee(null);
        LibraryApplication.setReader(null);
    }

}
