package agh.edu.pl.weedesign.library.services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agh.edu.pl.weedesign.library.entities.rental.Rental;
import org.springframework.stereotype.Service;

import agh.edu.pl.weedesign.library.entities.author.Author;
import agh.edu.pl.weedesign.library.entities.author.AuthorRepository;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.book.BookRepository;
import agh.edu.pl.weedesign.library.entities.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.entities.bookCopy.BookCopyRepository;
import agh.edu.pl.weedesign.library.entities.category.Category;
import agh.edu.pl.weedesign.library.entities.category.CategoryRepository;
import agh.edu.pl.weedesign.library.entities.employee.Employee;
import agh.edu.pl.weedesign.library.entities.employee.EmployeeRepository;
import agh.edu.pl.weedesign.library.entities.reader.Reader;
import agh.edu.pl.weedesign.library.entities.reader.ReaderRepository;
import agh.edu.pl.weedesign.library.entities.rental.RentalRepository;
import agh.edu.pl.weedesign.library.entities.review.Review;
import agh.edu.pl.weedesign.library.entities.review.ReviewRepository;

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

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public List<BookCopy> getBooksCopies(){
        return bookCopyRepository.findAll();
    }

    public List<BookCopy> getCopies(Book book){return bookCopyRepository.findBookCopiesByBook(book);}

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public List<Rental> getRentals() {return rentalRepository.findAll();}

    public List<Rental> getRentalsByReaderEmail(String email){
        return rentalRepository.findRentalsByReader(this.getReaderByEmail(email));
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

    public Map<Book, Long> getAvailableBookCount(){
        Map<Book, Long> bookCount = new HashMap<>();
        for(Object[] obj: bookCopyRepository.findBooksWithCopyCount()) {
            bookCount.put((Book) obj[0],(Long) obj[1]);
        }
        return bookCount;
    }

}
