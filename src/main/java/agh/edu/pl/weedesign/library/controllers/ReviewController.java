package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.entities.review.Review;
import agh.edu.pl.weedesign.library.sceneObjects.SceneFactory;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.RentalService;
import agh.edu.pl.weedesign.library.services.ReviewService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class ReviewController {
    private final RentalsController rentalsController;
    private Stage reviewStage;
//    TODO I think that sceneFactory should be a singleton
    private final SceneFactory sceneFactory;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addReviewButton;
    @FXML
    private TextArea commentArea;
    @FXML
    private ChoiceBox<Integer> choiceBox;
    private final ReviewService reviewService;
    private Rental rental;
    private final RentalService rentalService;
    @Autowired
    public ReviewController(ReviewService reviewService, RentalsController rentalsController, RentalService rentalService){
        sceneFactory = new SceneFactory();
        this.reviewService = reviewService;
        this.rentalService = rentalService;
        this.rentalsController = rentalsController;
    }
    @FXML
    public void initialize(){
        this.rental = rentalsController.getSelectedRental();
    }
//    public void createReviewView(Book book){
//        reviewStage = new Stage();
//
//        this.reviewStage.setTitle("Podziel się swoją opinią!");
//        Scene reviewScene = new Scene(sceneFactory.createScene(SceneType.REVIEW));
//        reviewStage.setScene(reviewScene);
//        reviewStage.show();
//    }
    @FXML
    private void handleAddReviewAction(ActionEvent event){
        int rating = choiceBox.getValue();
        String comment = commentArea.getText();
//        TODO some validation?
        Review review = new Review(rating, comment, LocalDateTime.now(), rental);
        saveReview(review);
    }
    @FXML
    private void handleCancelAction(ActionEvent event){
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_VIEW);
    }

    @Transactional
    public void saveReview(Review review){
        rental.setReview(review);
        reviewService.addNewReview(review);
        rental = rentalService.updateRental(rental);
    }
}
