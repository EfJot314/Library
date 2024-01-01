package agh.edu.pl.weedesign.library.controllers;


import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.helpers.BookListProcessor;
import agh.edu.pl.weedesign.library.helpers.SearchStrategy;
import agh.edu.pl.weedesign.library.helpers.SortOrder;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RentController {

    @FXML
    private TableView<BookCopy> bookTable;

    @FXML
    private TableColumn<BookCopy, String> idColumn;

    @FXML
    private TableColumn<BookCopy, String> conditionColumn;

    @FXML
    private TableColumn<BookCopy, String> priceColumn;

    @FXML
    private TableColumn<BookCopy, String> availabilityColumn;


    private List<BookCopy> bookCopies;

    private Book book;


    private ModelService service;
    private BookListProcessor bookListProcessor;

    @Autowired
    public RentController(ModelService service, BookListProcessor bookListProcessor){
        this.service = service;
        this.bookListProcessor = bookListProcessor;
    }

    @FXML
    public void initialize(){
        fetchBooksData();
        LibraryApplication.getAppController().resize(760, 440);


        idColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(String.valueOf(bookValue.getValue().getId())));
        conditionColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(bookValue.getValue().getCondition()));
        priceColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(String.valueOf(bookValue.getValue().getWeek_unit_price()) + " zł"));
//        availabilityColumn.setCellValueFactory(bookValue -> {
//            if (booksCount == null){
//                return new SimpleStringProperty("Error");
//            }
//            if (booksCount.get(bookValue.getValue()) == null){
//                return new SimpleStringProperty("Brak książki!");
//            }
//            return new SimpleStringProperty(booksCount.get(bookValue.getValue()).toString());
//        });
        //TODO
        availabilityColumn.setCellValueFactory(bookValue -> new SimpleStringProperty("TODO"));


        bookTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
//                LibraryApplication.getAppController().saveData(getSelectedBook());
//                LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);
            }
        });
    }

    private void fetchBooksData(){
        this.book = (Book)LibraryApplication.getAppController().getData();
        this.bookCopies = new ArrayList<>(this.service.getCopies(this.book));
        bookTable.setItems(FXCollections.observableList(this.bookCopies));
    }


    private void hopToNextScene(SceneType sceneType){
        if (sceneType == null){
            return;
        }
        
        LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);
    }

}
