package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.entities.review.Review;
import agh.edu.pl.weedesign.library.entities.review.ReviewRepository;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.RentalService;
import agh.edu.pl.weedesign.library.services.ReviewService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Controller
public class SingleRentalController {
    private final RentalsController rentalsController;

    @FXML
    private Text titleText;
    @FXML
    private Text authorText;
    @FXML
    private Text weekPriceText;
    @FXML
    private Text priceText;
    @FXML
    private Text employeeText;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;


    private final ReviewService reviewService;
    private Rental rental;
    private final RentalService rentalService;
    private final ReviewRepository reviewRepository;


    @Autowired
    public SingleRentalController(ReviewRepository reviewRepository, ReviewService reviewService, RentalsController rentalsController, RentalService rentalService){
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
        this.authorText.setText(this.rental.getBookCopy().getBook().getAuthor().getName() + " " + this.rental.getBookCopy().getBook().getAuthor().getSurname());
        this.weekPriceText.setText("Cena tygodniowa: " + this.rental.getBookCopy().getWeek_unit_price() + "zł");
        this.priceText.setText("Należność: " + this.rental.getPrice() + "zł");
        if(this.rental.getEmployee() != null)
            this.employeeText.setText("Bibliotekarz: " + this.rental.getEmployee().getName() + " " + this.rental.getEmployee().getSurname());
        else
            this.employeeText.setText("Wypożyczenie jeszcze nie zostało zaakceptowane");

        if(this.rental.getEnd_date() != null)
            this.cancelButton.setVisible(false);
        if(this.rental.getReview() != null)
            this.addButton.setVisible(false);

    }

    @FXML
    private void cancelRental(ActionEvent event){
        this.rental.setEnd_date(LocalDateTime.now());
        this.rentalService.updateRental(this.rental);
    }

    @FXML
    private void addReviewAction(ActionEvent event){
        LibraryApplication.getAppController().switchScene(SceneType.ADD_REVIEW);
    }

    @FXML
    private void backAction(ActionEvent event){
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_VIEW);
    }

}
