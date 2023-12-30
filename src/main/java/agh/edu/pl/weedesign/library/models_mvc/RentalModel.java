package agh.edu.pl.weedesign.library.models_mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.services.BookService;

@Component
public class RentalModel {

    private ConfigurableApplicationContext spring_context;
    private final BookService book_service;

    @Autowired
    public RentalModel(ConfigurableApplicationContext spring_context, BookService rental_service){
        this.spring_context = spring_context;
        this.book_service = rental_service;
    }

    public void RentBook(Book book){
        return;
    }
}
