package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.model.book.Book;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Controller;

import java.awt.image.BufferedImage;

@Controller
public class BookViewController {

    private Book book;   

    @FXML
    public ImageView image_cover; 

    @FXML
    public Label book_title_label;

    @FXML
    public Label author_label;

    @FXML
    public Label rating_label;

    @FXML
    public Label description_label;

    @FXML
    public Button rent_button;

    @FXML
    public Button cancel_button;

    @FXML
    public void initialize() throws IOException  {
        this.book = (Book)LibraryApplication.getAppController().getData();
        description_label.setText(book.getDescription());

        author_label.setText(
            "Autor: " + book.getAuthor().getName() 
                + " " + book.getAuthor().getSurname()
        );

        book_title_label.setText(book.getTitle());

        Image c = new Image(""+book.getCover_url()+"");
        image_cover.setImage(c);
    }

    public void handleCancelAction(ActionEvent e){
        LibraryApplication.getAppController().switchScene((new SceneFactory()).createScene(SceneType.BOOK_LIST));
    }

    public void handleRentAction(ActionEvent e){
        return;
    }
}   
