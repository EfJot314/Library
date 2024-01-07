package agh.edu.pl.weedesign.library.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

@Controller
public class BookListController {

    @FXML
    private ComboBox<SearchStrategy> searchStrategyMenu;

    @FXML
    private TextField findTextField;

    @FXML
    private ComboBox<SearchStrategy> sortStrategyMenu;

    @FXML
    private ComboBox<SortOrder> sortOrderMenu;

    @FXML
    private CheckBox onlyAvailableCheckbox;

    @FXML
    private CheckBox changeView;

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

    private String[] themes = {
        "Cupertino Dark",
        "Cupertino Light",
        "Dracula",
        "Nord Dark", 
        "Nord Light", 
        "Primer Dark", 
        "Primer Light"
    };

    private boolean isTableVisible = false;

    private List<Book> allBooks;
    private ObservableList<Book> visibleBooks;

    private Map<Book, Long> booksCount;

    private ModelService service;
    private BookListProcessor bookListProcessor;
    private ArrayList<HBox> rows = new ArrayList<>();


    @Autowired
    public BookListController(ModelService service, BookListProcessor bookListProcessor){
        this.service = service;
        this.bookListProcessor = bookListProcessor;
    }

    @FXML
    public void initialize(){
        themeChange.getItems().addAll(themes);
        themeChange.setOnAction(this::changeTheme);
        themeChange.setValue(LibraryApplication.getTheme());

        scrollPane.setVisible(!isTableVisible);
        bookTable.setVisible(isTableVisible);

        fetchBooksData();
        displayBooks();

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
    }

    private void displayBooks(){
            mainViewBox.getChildren().clear();
            rows.clear();

            for(int i = 0 ; i <= this.visibleBooks.size()/5; i++)
                rows.add(new HBox());
            
            Image img;
            int i = 0;

            for(Book b: this.visibleBooks){
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

                try {
                    // System.out.println(b.getCover_url());
                    img = new Image("" + b.getCover_url() + "", true);
                    newCover.setImage(img);
                } catch (Exception e){
                    img = new Image(getClass().getClassLoader().getResource("default_book_cover_2015.jpg").toString(), true);
                    newCover.setImage(img);
                }

                rows.get(Integer.valueOf(i/5)).getChildren().add(newCover);
                i++;
            }

            for(HBox x : rows)
                mainViewBox.getChildren().add(x);

            // System.out.println("Visble Books: " + this.visibleBooks.size());
            // System.out.println("No of. rows: " + this.rows.size());
            // System.out.println("Javafx visible property: " + this.scrollPane.isVisible() + " " + mainViewBox.isVisible() + " " + mainAnchor.isVisible());
            // System.out.println("Main View Children Counter: " + this.mainViewBox.getChildren().size());
            // System.out.println("Size of Main View: " + this.mainAnchor.getWidth() + "x"+  this.mainAnchor.getHeight());
        
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

    @FXML
    private void search(ActionEvent actionEvent) {
        List<Book> tempBookList = new ArrayList<>(allBooks);

        tempBookList = bookListProcessor.processList(tempBookList,searchStrategyMenu.getValue(), findTextField.getText(),sortStrategyMenu.getValue(),sortOrderMenu.getValue(), onlyAvailableCheckbox.isSelected());
        this.visibleBooks = FXCollections.observableList(tempBookList);
        bookTable.setItems(this.visibleBooks);
        displayBooks();
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

    public void LogOutAction(){
        LibraryApplication.getAppController().logOut();
    }
}
