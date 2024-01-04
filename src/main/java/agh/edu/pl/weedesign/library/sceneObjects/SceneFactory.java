package agh.edu.pl.weedesign.library.sceneObjects;

import agh.edu.pl.weedesign.library.LibraryApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


import java.io.IOException;


public class SceneFactory {

    private final String loginViewPath = "/views/loginView.fxml";
    private final String mainViewPath = "/views/mainView.fxml";
    private final String registrationViewPath = "/views/registerView.fxml";
    private final String welcomeViewPath = "/views/welcomeView.fxml";
    private final String bookListViewPath = "/views/bookList.fxml";
    private final String bookViewPath = "/views/bookView.fxml";
    private final String newBookViewPath = "/views/newBookView.fxml";
    private final String bookCopiesViewPath = "/views/bookCopiesView.fxml";
    private final String rentalsViewPath = "/views/rentalsView.fxml";
    private final String startViewPath = "/views/startView.fxml";
    private final String addReviewViewPath = "/views/addReviewView.fxml";
    private final String reviewsViewPath = "/views/bookReviewsView.fxml";
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
                case BOOK_LIST -> {
                    return loadScene(bookListViewPath);
                }
                case BOOK_VIEW -> {
                    return loadScene(bookViewPath);
                }
                case NEW_BOOK_VIEW -> {
                    return loadScene(newBookViewPath);
                }
                case COPIES_VIEW -> {
                    return loadScene(bookCopiesViewPath);
                }
                case RENTALS_VIEW -> {
                    return loadScene(rentalsViewPath);
                }
                case START_VIEW -> {
                    return loadScene(startViewPath);
                }
                case REVIEWS -> {
                    return loadScene(reviewsViewPath);
                }
                case ADD_REVIEW -> {
                    return loadScene(addReviewViewPath);
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
