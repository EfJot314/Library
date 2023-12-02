package agh.edu.pl.weedesign.library.model.rental;

import agh.edu.pl.weedesign.library.model.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.model.employee.Employee;
import agh.edu.pl.weedesign.library.model.reader.Reader;
import agh.edu.pl.weedesign.library.model.review.Review;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rental {
    @Id @GeneratedValue
    private int id;

    private int price;

    private LocalDateTime start_date;

    private LocalDateTime end_date;

    @ManyToOne
    private BookCopy bookCopy;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Reader reader;

    @OneToOne
    private Review review;

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


    public int getId() {
        return id;
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

    public void setBookCopy(BookCopy bookCopy){
        this.bookCopy = bookCopy;

    }

    public BookCopy getBookCopy(){
        return bookCopy;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Reader getReader() {
        return reader;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setReview(Review review){
        this.review = review;
    }

    public Review getReview(){
        return review;
    }
}
