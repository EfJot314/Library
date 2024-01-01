package agh.edu.pl.weedesign.library.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.models_mvc.RentalModel;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


@Controller
public class BookViewController {
    private Book book;

    @Autowired
    BookViewController(RentalModel rental_model){}

    @FXML
    private ImageView image_cover; 

    @FXML
    private Label book_title_label;

    @FXML
    private Label author_label;

    @FXML
    private Label rating_label;

    @FXML
    private Label description_label;

    @FXML
    private Button rent_button;

    @FXML
    private Button cancel_button;

    @FXML
    public void initialize() throws IOException  {
        // Pobieram wybraną książkę za pomocą metody getUserData która 
        // pozwala na zapisywanie obieków w scenie

        LibraryApplication.getAppController().resize(650, 550); 

        this.book = (Book)LibraryApplication.getAppController().getData();  
        
        if(this.book == null){
            System.out.println("No book was selected");
            LibraryApplication.getAppController().switchScene(SceneType.BOOK_LIST);
        }

        description_label.setText(book.getDescription());
        book_title_label.setText(book.getTitle());

        author_label.setText(
            "Autor: " + book.getAuthor().getName() 
                + " " + book.getAuthor().getSurname()
        );

        Image img;

        try {
            img = new Image("" + book.getCover_url() + "");
            image_cover.setImage(img);
        } catch (Exception e){
            System.out.println("Cover not found");
        }

        rating_label.setText("Rating: 4.20");
    }

    public void handleCancelAction(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.BOOK_LIST);
    }

    public void handleRentAction(ActionEvent e){
        //switching to rental view
        LibraryApplication.getAppController().saveData(this.book);
        LibraryApplication.getAppController().switchScene(SceneType.COPIES_VIEW);
    }
}   
