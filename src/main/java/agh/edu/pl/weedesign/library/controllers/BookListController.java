package agh.edu.pl.weedesign.library.controllers;

import java.util.*;

import agh.edu.pl.weedesign.library.helpers.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Map;

import agh.edu.pl.weedesign.library.entities.author.Author;
import agh.edu.pl.weedesign.library.entities.category.Category;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Controller
public class BookListController {
    @FXML
    public HBox popularBooksHBox;
    @FXML
    public HBox recommendedBooksHBox;
    @FXML
    private ComboBox<SearchStrategy> searchStrategyMenu;
    @FXML
    public TextField findTextField;
    @FXML
    public ComboBox<FilterStrategy> filterStrategyMenu;
    @FXML
    public ComboBox<String> filterValueMenu;

    @FXML
    private ComboBox<SearchStrategy> sortStrategyMenu;

    @FXML
    private ComboBox<SortOrder> sortOrderMenu;

    @FXML
    private CheckBox onlyAvailableCheckbox;

    @FXML
    private CheckBox changeView;

    // Tabel view
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

    // Tiles View
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private VBox mainViewBox;

    @FXML
    private ScrollPane scrollPane;
    
    // Navbar controls 
    @FXML
    private Button mainPage; 

    @FXML
    private Button myRentals; 

    @FXML
    private Button logOut; 

   @FXML 
    private ChoiceBox<String> themeChange;

    private boolean isTableVisible = false;

    private List<Book> allBooks;
    private ObservableList<Book> visibleBooks;

    private Map<Book, Long> booksCount;

    private ModelService service;
    private BookListProcessor bookListProcessor;
    private ArrayList<HBox> rows = new ArrayList<>();
    private Recommender recommender;




    @Autowired
    public BookListController(ModelService service, BookListProcessor bookListProcessor, Recommender recommender){
        this.service = service;
        this.bookListProcessor = bookListProcessor;
        this.recommender = recommender;
    }



    @FXML
    public void initialize(){
        themeChange.getItems().addAll(Themes.getAllThemes());
        themeChange.setOnAction(this::changeTheme);
        themeChange.setValue(LibraryApplication.getTheme());

        scrollPane.setVisible(!isTableVisible);
        bookTable.setVisible(isTableVisible);

        fetchBooksData();
        initializeTilesDisplay();
        initializeTabelDisplay();
    }

    private void initializeTabelDisplay(){
        titleColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(bookValue.getValue().getTitle()));
        authorColumn.setCellValueFactory(bookValue -> new SimpleStringProperty(bookValue.getValue().getAuthorString()));
        ratingColumn.setCellValueFactory(bookValue -> new SimpleStringProperty("4.2")); // TODO - implement rating
        
        availabilityColumn.setCellValueFactory(bookValue -> {
            if (booksCount == null)
                return new SimpleStringProperty("Error");
            
            if (booksCount.get(bookValue.getValue()) == null)
                return new SimpleStringProperty("Brak książki!");
            
            return new SimpleStringProperty(booksCount.get(bookValue.getValue()).toString());
        });

        bookTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                if (getSelectedBook() == null){
                    return;
                }
                LibraryApplication.setBook(getSelectedBook());
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

    private void initializeTilesDisplay(){
        mainViewBox.getChildren().clear();
        rows.clear();
        int currRow= 0;

//      adding popular books and recommendations
        if(this.recommender.showPopularBooks()){
            this.showPopularBooks();
            currRow++;
        }
        if(this.recommender.showRecommendations()) {
            this.showRecommendations();
            currRow++;
        }
        for(int i = 0 ; i <= this.visibleBooks.size()/5; i++)
            rows.add(new HBox());

        for(Book b: this.visibleBooks){
            ImageView newCover = this.createImageCover(b);
            rows.get(currRow).getChildren().add(newCover);
            if (rows.get(currRow).getChildren().size() >= 5) {
                currRow++;
            }
        }

        for(HBox x : rows)
            mainViewBox.getChildren().add(x);
    }

    private ImageView createImageCover(Book b) {
        ImageView newCover = new ImageView();
        newCover.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event){
                        if(LibraryApplication.getBook() == b)
                            LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);

                        LibraryApplication.setBook(b);
                        System.out.println(LibraryApplication.getBook());
                    }
                }
        );

        newCover.setFitHeight(300);
        newCover.setFitWidth(194);
        newCover.maxHeight(300);
        newCover.maxWidth(194);

        Image img;

        try {
            img = new Image("" + b.getCoverUrl() + "", true);
        } catch (Exception e){
            img = new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("default_book_cover_2015.jpg")).toString(), true);
        }

        newCover.setImage(img);
        return newCover;
    }

    private void showRecommendations() {
        Label label = new Label("Polecane dla ciebie");
        label.setFont(new Font("System Bold",21.0));
        label.setPadding(new Insets(5, 0, 0, 10));
        HBox recommendationsHBox = new HBox();

        for (Book book : recommender.getMostPopularBooks(2)){
            ImageView newCover = this.createImageCover(book);
            recommendationsHBox.getChildren().add(newCover);
        }

        this.rows.add(new HBox(new VBox(label, recommendationsHBox)));
    }

    private void showPopularBooks() {
        Label label = new Label("Popularne");
        label.setFont(new Font("System Bold",21.0));
        label.setPadding(new Insets(5, 0, 0, 10));
        HBox popularHBox = new HBox();

        for (Book book : this.recommender.getRecommendedBooks(3)){
            ImageView newCover = this.createImageCover(book);
            popularHBox.getChildren().add(newCover);
        }

        this.rows.add(new HBox(new VBox(label, popularHBox)));
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
        List<Book> tempBookList = bookListProcessor.processList(
                searchStrategyMenu.getValue(),
                findTextField.getText(),
                sortStrategyMenu.getValue(),
                sortOrderMenu.getValue(),
                filterStrategyMenu.getValue(),
                filterValueMenu.getValue(),
                onlyAvailableCheckbox.isSelected()
        );
        this.visibleBooks = FXCollections.observableList(tempBookList);
        bookTable.setItems(this.visibleBooks);
        
        initializeTilesDisplay();
    }

    public void changeView(){
        isTableVisible = !isTableVisible;

        scrollPane.setVisible(!isTableVisible);
        bookTable.setVisible(isTableVisible);
    }

    public void goBackAction(){
        LibraryApplication.getAppController().back();
    }

    public void goForwardAction(){
        LibraryApplication.getAppController().forward();
    }

    public void mainPageButtonHandler(){
        //pass
    }

    public void myRentalsButtonHandler(){
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_VIEW); 
    }

    public void changeTheme(ActionEvent e){
        LibraryApplication.changeTheme(themeChange.getValue());
    }

    public void LogOutAction() {
        LibraryApplication.getAppController().logOut();
    }


    private void clearSearchingOptions() {
        // Ni chuja nie wiem jak tu zrobić żeby znowu wyświetlił się prompt text, nienawidzę javafx <3
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
