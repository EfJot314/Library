package agh.edu.pl.weedesign.library.model;

import agh.edu.pl.weedesign.library.model.author.Author;
import agh.edu.pl.weedesign.library.model.author.AuthorRepository;
import agh.edu.pl.weedesign.library.model.book.Book;
import agh.edu.pl.weedesign.library.model.book.BookRepository;
import agh.edu.pl.weedesign.library.model.bookCopy.BookCopyRepository;
import agh.edu.pl.weedesign.library.model.category.CategoryRepository;
import agh.edu.pl.weedesign.library.model.employee.EmployeeRepository;
import agh.edu.pl.weedesign.library.model.reader.ReaderRepository;
import agh.edu.pl.weedesign.library.model.rental.RentalRepository;
import agh.edu.pl.weedesign.library.model.review.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


//class to create example data -> TODO

@Configuration
public class ModelConfigurator {
    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository, BookRepository bookRepository, BookCopyRepository bookCopyRepository, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, ReaderRepository readerRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository) {
        return args -> {
            if (bookRepository.count() == 0) {

                Book book = new Book("Moja", "fajna", 100, "spis", "lll");
                Author a = new Author("Imie", "Nazwisko", "historia");

                authorRepository.saveAll(List.of(a));


                a.addBook(book);

                bookRepository.saveAll(List.of(book));


            }
        };
    }
}
