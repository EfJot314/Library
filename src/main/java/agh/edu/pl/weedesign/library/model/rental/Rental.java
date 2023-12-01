package agh.edu.pl.weedesign.library.model.rental;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Rental {
    @Id @GeneratedValue
    private int id;

    private int price;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    public Rental(){};

    public Rental(LocalDateTime start_date, LocalDateTime end_date){
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = 0;
    }

    public Rental(LocalDateTime start_date, LocalDateTime end_date, int price){
        this.start_date = start_date;
        this.end_date = end_date;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }
}
