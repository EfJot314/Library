package agh.edu.pl.weedesign.library.controllers;


import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.model.book.Book;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;



public class LibraryAppController {
    private Stage primaryStage;
    private Scene currentScene;

    ConfigurableApplicationContext springContext;

    public LibraryAppController(Stage stage, ConfigurableApplicationContext springContext){
        this.primaryStage = stage;
        this.springContext = springContext;
    }

    public void initWelcomeLayout() {
        this.primaryStage.setTitle("Biblioteka");
        SceneFactory factory = new SceneFactory();

        currentScene = new Scene(factory.createScene(SceneType.BOOK_LIST));
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public void switchScene(Pane pane){
        currentScene.setRoot(pane);
    }

    public void saveData(Object obj){
        this.currentScene.setUserData(obj);
    }

    public Object getData(){
        return this.currentScene.getUserData();
    }

}
