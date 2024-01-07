package agh.edu.pl.weedesign.library.controllers;



import java.util.List;
import java.util.Map;

import agh.edu.pl.weedesign.library.entities.author.Author;
import agh.edu.pl.weedesign.library.entities.category.Category;
import agh.edu.pl.weedesign.library.helpers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    public ComboBox<FilterStrategy> filterStrategyMenu;
    @FXML
    public ComboBox<String> filterValueMenu;


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
                if (getSelectedBook() == null){
                    return;
                }
                LibraryApplication.getAppController().saveData(getSelectedBook());
                LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);
            }
        });


        filterStrategyMenu.setOnAction((e) ->{
            this.setFilteringMenuContent();
        });
    }

    @FXML
    private void search(ActionEvent actionEvent) {
        showProcessedBooks();
    }

    @FXML
    public void back(ActionEvent actionEvent) {
//        LibraryApplication.getAppController().switchScene(SceneType.NEW_BOOK_VIEW);
        LibraryApplication.getAppController().switchScene(SceneType.START_VIEW);
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        clearSearchingOptions();
    }


    private void setFilteringMenuContent() {
        if (this.filterStrategyMenu.getValue() == null) {
            this.filterValueMenu.setItems(FXCollections.observableArrayList());
            return;
        }
        switch (this.filterStrategyMenu.getValue()){
            case AUTHOR -> {
                List<Author> authorList = this.service.getAuthors();
                List<String> surrnameList = authorList.stream().map(Author::getSurname).toList();

                this.filterValueMenu.setItems(FXCollections.observableList(surrnameList));
            }
            case CATEGORY -> {
                List<Category> categories = this.service.getCategories();
                List<String> categoryNameList = categories.stream().map(Category::getName).toList();
                this.filterValueMenu.setItems(FXCollections.observableList(categoryNameList));
            }
        }

    }

    private void fetchBooksData(){
        this.booksCount = this.service.getAvailableBookCount();

        showProcessedBooks();
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


    private void showProcessedBooks() {
        List<Book> tempBookList = bookListProcessor.processList(searchStrategyMenu.getValue(), findTextField.getText(),sortStrategyMenu.getValue(),sortOrderMenu.getValue(),filterStrategyMenu.getValue(), filterValueMenu.getValue(), onlyAvailableCheckbox.isSelected());
        this.visibleBooks = FXCollections.observableList(tempBookList);
        bookTable.setItems(this.visibleBooks);
    }



    private void clearSearchingOptions() {
        // Ni chuja nie wiem jak tu zrobić żeby znowu wyświetlił się prompt text, nienawidzę javafx
        this.searchStrategyMenu.getSelectionModel().clearSelection();
        this.searchStrategyMenu.setPromptText("Wyszukaj po");

        this.findTextField.setText("");

        this.sortOrderMenu.getSelectionModel().clearSelection();
        this.sortOrderMenu.setPromptText("Sortuj");

        this.sortStrategyMenu.getSelectionModel().clearSelection();
        this.sortStrategyMenu.setPromptText("Sortuj po");

        this.filterStrategyMenu.getSelectionModel().clearSelection();
        this.filterValueMenu.getSelectionModel().clearSelection();

        showProcessedBooks();
    }
}
