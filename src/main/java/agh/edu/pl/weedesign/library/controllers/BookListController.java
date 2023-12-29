package agh.edu.pl.weedesign.library.controllers;


import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.helpers.BookListProcessor;
import agh.edu.pl.weedesign.library.helpers.SearchStrategy;
import agh.edu.pl.weedesign.library.helpers.SortOrder;
import agh.edu.pl.weedesign.library.model.ModelService;
import agh.edu.pl.weedesign.library.model.book.Book;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
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
import java.util.Locale;
import java.util.Map;

@Controller
public class BookListController {


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
    public BookListController(ModelService service, BookListProcessor bookListProcessor){
        this.service = service;
        this.bookListProcessor = bookListProcessor;
    }

    @FXML
    public void initialize(){
        fetchBooksData();

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
        }); // TODO - implement checking number of available books


        bookTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                hopToNextScene(getBookDetailsScene(getSelectedBook()));
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
        // TODO - implement book details scene
        return null;
    }

    private void hopToNextScene(SceneType sceneType){
        System.out.println(getSelectedBook().getTitle());
        if (sceneType == null){
            return;
        }
        LibraryApplication.getAppController().switchScene((new SceneFactory()).createScene(sceneType));
    }

    @FXML
    private void search(ActionEvent actionEvent) {
        List<Book> tempBookList = new ArrayList<>(allBooks);

        tempBookList = bookListProcessor.processList(tempBookList,searchStrategyMenu.getValue(), findTextField.getText(),sortStrategyMenu.getValue(),sortOrderMenu.getValue(), onlyAvailableCheckbox.isSelected());
        this.visibleBooks = FXCollections.observableList(tempBookList);
        bookTable.setItems(this.visibleBooks);
    }

    public void sth(ActionEvent actionEvent) {

    }
}
