package agh.edu.pl.weedesign.library.model;

import agh.edu.pl.weedesign.library.model.author.Author;
import agh.edu.pl.weedesign.library.model.author.AuthorRepository;
import agh.edu.pl.weedesign.library.model.book.Book;
import agh.edu.pl.weedesign.library.model.book.BookRepository;
import agh.edu.pl.weedesign.library.model.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.model.bookCopy.BookCopyRepository;
import agh.edu.pl.weedesign.library.model.category.Category;
import agh.edu.pl.weedesign.library.model.category.CategoryRepository;
import agh.edu.pl.weedesign.library.model.employee.Employee;
import agh.edu.pl.weedesign.library.model.employee.EmployeeRepository;
import agh.edu.pl.weedesign.library.model.reader.Reader;
import agh.edu.pl.weedesign.library.model.reader.ReaderRepository;
import agh.edu.pl.weedesign.library.model.rental.Rental;
import agh.edu.pl.weedesign.library.model.rental.RentalRepository;
import agh.edu.pl.weedesign.library.model.review.Review;
import agh.edu.pl.weedesign.library.model.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ReaderRepository readerRepository;
    private final RentalRepository rentalRepository;
    private final ReviewRepository reviewRepository;

    public ModelService(AuthorRepository authorRepository, BookRepository bookRepository, BookCopyRepository bookCopyRepository, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, ReaderRepository readerRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.readerRepository = readerRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public List<BookCopy> getBooksCopies(){
        return bookCopyRepository.findAll();
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public List<Reader> getReaders(){
        return readerRepository.findAll();
    }

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public void addNewReader(Reader reader) {
        readerRepository.save(reader);
    }

    public boolean isEmailFree(String email){
        return !readerRepository.existsByEmail(email);
    }

    public Reader getReaderByEmail(String email){
        return readerRepository.findByEmail(email);
    }

    public String getReaderPasswordByEmail(String email){
        if (getReaderByEmail(email) == null){
            return null;
        }
        return getReaderByEmail(email).getPassword();
    }
}
