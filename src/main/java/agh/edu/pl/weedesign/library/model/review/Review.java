package agh.edu.pl.weedesign.library.model.review;

import agh.edu.pl.weedesign.library.model.rental.Rental;
import org.springframework.cglib.core.Local;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id @GeneratedValue
    private int id;

    private int stars;

    private String comment;

    private LocalDateTime dateTime;

    @OneToOne
    private Rental rental;

    public Review(){};

    public Review(int stars, LocalDateTime dateTime){
        this.stars = stars;
        this.comment = "";
        this.dateTime = dateTime;
    }

    public Review(int stars, String comment, LocalDateTime dateTime){
        this.stars = stars;
        this.comment = comment;
        this.dateTime = dateTime;
    }


    public int getId() {
        return id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setRental(Rental rental){
        this.rental = rental;
        if(rental.getReview() == null){
            rental.setReview(this);
        }
    }

    public Rental getRental(){
        return rental;
    }
}
