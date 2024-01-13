package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.entities.reservation.Reservation;
import agh.edu.pl.weedesign.library.entities.review.Review;
import agh.edu.pl.weedesign.library.entities.review.ReviewRepository;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.EmailServiceImpl;
import agh.edu.pl.weedesign.library.services.ModelService;
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
import java.util.List;

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
    private ModelService service;


    @Autowired
    public SingleRentalController(ReviewRepository reviewRepository, ReviewService reviewService, RentalsController rentalsController, RentalService rentalService, ModelService service){
        this.reviewService = reviewService;
        this.rentalService = rentalService;
        this.rentalsController = rentalsController;
        this.reviewRepository = reviewRepository;
        this.service = service;
    }

    @FXML
    public void initialize(){
        this.rental = rentalsController.getSelectedRental();

        this.setValues();
    }

    private void setValues(){
        this.titleText.setText(this.rental.getBookCopy().getBook().getTitle());
//        this.authorText.setText(this.rental.getBookCopy().getBook().getAuthor().getName() + " " + this.rental.getBookCopy().getBook().getAuthor().getSurname());
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
        informAboutReturnedBook();

        if(this.rental.getEmployee() == null){
            //jezeli nie bylo akceptacji, to po prostu usuwam to wypozyczenie
            this.rentalService.removeRental(this.rental);
            backAction(null);
            return;
        }
        this.rental.setEnd_date(LocalDateTime.now());
        this.rentalService.updateRental(this.rental);
        this.cancelButton.setVisible(false);
    }

    private void informAboutReturnedBook(){
        List<Reservation> reservations = this.service.getReservationsByBook(this.rental.getBookCopy().getBook());
        this.service.deleteReservations(reservations);
        if (reservations == null) return;
        for (Reservation r: reservations){
            EmailServiceImpl emailService = new EmailServiceImpl();
            emailService.sendSimpleMessage(
                    "pepesob2@gmail.com",
                    "Dostępna książka",
                    "Książka: " + r.getBook().getTitle() + " jest już dostępna w bibliotece"
                    );
        }
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
