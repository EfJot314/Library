package agh.edu.pl.weedesign.library.model;

import agh.edu.pl.weedesign.library.model.author.AuthorRepository;
import agh.edu.pl.weedesign.library.model.book.BookRepository;
import agh.edu.pl.weedesign.library.model.category.CategoryRepository;
import agh.edu.pl.weedesign.library.model.employee.EmployeeRepository;
import agh.edu.pl.weedesign.library.model.reader.ReaderRepository;
import agh.edu.pl.weedesign.library.model.rental.RentalRepository;
import agh.edu.pl.weedesign.library.model.review.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ReaderRepository readerRepository;
    private final RentalRepository rentalRepository;
    private final ReviewRepository reviewRepository;

    public ModelService(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, ReaderRepository readerRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.readerRepository = readerRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
    }



}
