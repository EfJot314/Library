package agh.edu.pl.weedesign.library.models_mvc;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.services.ReaderService;
import agh.edu.pl.weedesign.library.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.services.BookService;

import java.time.LocalDateTime;

@Component
public class RentalModel {

    private ConfigurableApplicationContext spring_context;
    private final ReaderService readerService;

    private final RentalService rentalService;

    @Autowired
    public RentalModel(ConfigurableApplicationContext spring_context, ReaderService readerService, RentalService rentalService){
        this.spring_context = spring_context;
        this.readerService = readerService;
        this.rentalService = rentalService;
    }

    public void rentBook(BookCopy bookCopy){
        Rental rental = new Rental(LocalDateTime.now(), LocalDateTime.now().plusMonths(1));

        rental.setBookCopy(bookCopy);
        rental.setReader(LibraryApplication.getReader());

        rentalService.addNewRental(rental);
    }
}
