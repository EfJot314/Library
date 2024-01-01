package agh.edu.pl.weedesign.library.controllers;


import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.helpers.BookListProcessor;
import agh.edu.pl.weedesign.library.models_mvc.RentalModel;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RentalsController {

    @FXML
    private TableView<Rental> rentalsTable;

    @FXML
    private TableColumn<Rental, String> titleColumn;

    @FXML
    private TableColumn<Rental, String> authorColumn;

    @FXML
    private TableColumn<Rental, String> priceColumn;

    @FXML
    private TableColumn<Rental, String> startDateColumn;

    @FXML
    private TableColumn<Rental, String> endDateColumn;


    private List<Rental> rentals;

    private ModelService service;
    private BookListProcessor bookListProcessor;

    private RentalModel rentalModel;

    @Autowired
    public RentalsController(ModelService service, BookListProcessor bookListProcessor, RentalModel rentalModel){
        this.service = service;
        this.bookListProcessor = bookListProcessor;
        this.rentalModel = rentalModel;
    }

    @FXML
    public void initialize(){
        fetchRentalsData();
        LibraryApplication.getAppController().resize(760, 440);

        titleColumn.setCellValueFactory(rentalValue -> new SimpleStringProperty(rentalValue.getValue().getBookCopy().getBook().getTitle()));
        authorColumn.setCellValueFactory(rentalValue -> new SimpleStringProperty(rentalValue.getValue().getBookCopy().getBook().getAuthor().getName() + " " + rentalValue.getValue().getBookCopy().getBook().getAuthor().getSurname()));
        priceColumn.setCellValueFactory(rentalValue -> new SimpleStringProperty(rentalValue.getValue().getPrice() + " zł"));
        startDateColumn.setCellValueFactory(rentalValue -> new SimpleStringProperty(rentalValue.getValue().getStart_date().toLocalDate().toString()));
        endDateColumn.setCellValueFactory(rentalValue -> new SimpleStringProperty(rentalValue.getValue().getEnd_date().toLocalDate().toString()));

        rentalsTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
//                LibraryApplication.getAppController().saveData(getSelectedBook());
//                LibraryApplication.getAppController().switchScene(SceneType.BOOK_VIEW);
            }
        });
    }

    private void fetchRentalsData(){
        this.rentals = new ArrayList<>(this.service.getRentalsByReaderEmail(LibraryApplication.getUserEmail()));
        rentalsTable.setItems(FXCollections.observableList(this.rentals));
    }

    @FXML
    private void toWelcomeView(ActionEvent actionEvent) {
        LibraryApplication.getAppController().switchScene(SceneType.START_VIEW);
    }

}
