package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.entities.review.ReviewRepository;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.RentalService;
import agh.edu.pl.weedesign.library.services.ReviewService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class acceptanceController {
    private final RentalsController rentalsController;

    @FXML
    private Text titleText;
    @FXML
    private Text authorText;


    private final ReviewService reviewService;
    private Rental rental;
    private final RentalService rentalService;
    private final ReviewRepository reviewRepository;


    @Autowired
    public acceptanceController(ReviewRepository reviewRepository, ReviewService reviewService, RentalsController rentalsController, RentalService rentalService){
        this.reviewService = reviewService;
        this.rentalService = rentalService;
        this.rentalsController = rentalsController;
        this.reviewRepository = reviewRepository;

    }

    @FXML
    public void initialize(){
        this.rental = rentalsController.getSelectedRental();

        this.setValues();
    }

    private void setValues(){
        this.titleText.setText(this.rental.getBookCopy().getBook().getTitle());
//        this.authorText.setText(this.rental.getBookCopy().getBook().getAuthor().getName() + " " + this.rental.getBookCopy().getBook().getAuthor().getSurname());
    }

    public void acceptRental(ActionEvent e){
        this.rental.setEmployee(LibraryApplication.getEmployee());
        this.rentalService.updateRental(this.rental);
        this.backAction(null);
    }

    public void rejectRental(ActionEvent e){
        this.rentalService.removeRental(this.rental);
        this.backAction(null);
    }

    public void backAction(ActionEvent e){
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_ACCEPTANCE);
    }

}
