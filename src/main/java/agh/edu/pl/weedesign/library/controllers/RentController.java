package agh.edu.pl.weedesign.library.controllers;


import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.helpers.BookListProcessor;
import agh.edu.pl.weedesign.library.helpers.SearchStrategy;
import agh.edu.pl.weedesign.library.helpers.SortOrder;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
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
    public ComboBox<SearchStrategy> searchStrategyMenu;
    @FXML
    public TextField findTextField;
    @FXML
    public ComboBox<SearchStrategy> sortStrategyMenu;
    @FXML
    public ComboBox<SortOrder> sortOrderMenu;
    @FXML
    public CheckBox onlyAvailableCheckbox;


    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> ratingColumn;

    @FXML
    private TableColumn<Book, String> availabilityColumn;


    List<Book> allBooks;
    ObservableList<Book> visibleBooks;

    Map<Book, Long> booksCount;


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

        titleColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(bookValue.getValue().getTitle()));
        authorColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(bookValue.getValue().getAuthor().getName() + " " + bookValue.getValue().getAuthor().getSurname()));
        ratingColumn.setCellValueFactory(bookValue -> new SimpleStringProperty("4.2")); // TODO - implement rating
        availabilityColumn.setCellValueFactory(bookValue -> {
            if (booksCount == null){
                return new SimpleStringProperty("Error");
            }
            if (booksCount.get(bookValue.getValue()) == null){
                return new SimpleStringProperty("Brak książki!");
            }
            return new SimpleStringProperty(booksCount.get(bookValue.getValue()).toString());
        });


        bookTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                LibraryApplication.getAppController().saveData(getSelectedBook());
                LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);
            }
        });
    }

    private void fetchBooksData(){
        this.allBooks = new ArrayList<>(this.service.getBooks());
        this.visibleBooks = FXCollections.observableList(this.allBooks);
        this.booksCount = this.service.getAvailableBookCount();
        bookTable.setItems(this.visibleBooks);
    }

    private Book getSelectedBook(){
        return bookTable.getSelectionModel().getSelectedItem();
    }

    private SceneType getBookDetailsScene(Book book) {
        return SceneType.BOOK_VIEW;
    }

    private void hopToNextScene(SceneType sceneType){
        if (sceneType == null){
            return;
        }
        
        LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);
    }

    @FXML
    private void search(ActionEvent actionEvent) {
        List<Book> tempBookList = new ArrayList<>(allBooks);

        tempBookList = bookListProcessor.processList(tempBookList,searchStrategyMenu.getValue(), findTextField.getText(),sortStrategyMenu.getValue(),sortOrderMenu.getValue(), onlyAvailableCheckbox.isSelected());
        this.visibleBooks = FXCollections.observableList(tempBookList);
        bookTable.setItems(this.visibleBooks);
    }

    public void sth(ActionEvent actionEvent) {
        LibraryApplication.getAppController().switchScene(SceneType.NEW_BOOK_VIEW);
    }
}
