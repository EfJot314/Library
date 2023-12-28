package agh.edu.pl.weedesign.library.sceneObjects;

import agh.edu.pl.weedesign.library.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


import java.io.IOException;


public class SceneFactory {

    private final String loginViewPath = "/views/loginView.fxml";
    private final String mainViewPath = "/views/mainView.fxml";
    private final String registrationViewPath = "/views/registerView.fxml";
    private final String welcomeViewPath = "/views/welcomeView.fxml";
    private final String addEmployeeViewPath = "/views/addEmployeeView.fxml";

    public Pane createScene(SceneType sceneType) {
        try {
            switch (sceneType) {
                case LOGIN -> {
                    return loadScene(loginViewPath);
                }
                case MAIN -> {
                    return loadScene(mainViewPath);
                }
                case REGISTER -> {
                    return loadScene(registrationViewPath);
                }
                case WELCOME -> {
                    return loadScene(welcomeViewPath);
                }
                case ADD_EMPLOYEE -> {
                    return loadScene(addEmployeeViewPath);
                }
                default -> {
                    return null;
                }
            }
        }
        catch (IOException i){
            i.printStackTrace();
        }
        return null;
    }

    private Pane loadScene(String path) throws IOException {
        // load layout from FXML file
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LibraryApplication.class.getResource(path));
        loader.setControllerFactory(LibraryApplication.getAppContext()::getBean);

        return loader.load();
    }

    
}
