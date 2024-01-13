package agh.edu.pl.weedesign.library.services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.entities.reservation.Reservation;
import agh.edu.pl.weedesign.library.entities.reservation.ReservationRepository;
import agh.edu.pl.weedesign.library.helpers.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private int PAGE_SIZE = 1000;

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;
    private final CategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final ReaderRepository readerRepository;
    private final RentalRepository rentalRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;

    public ModelService(AuthorRepository authorRepository, BookRepository bookRepository, BookCopyRepository bookCopyRepository, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, ReaderRepository readerRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository, ReservationRepository reservationRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.categoryRepository = categoryRepository;
        this.employeeRepository = employeeRepository;
        this.readerRepository = readerRepository;
        this.rentalRepository = rentalRepository;
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
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
    public List<Reader> getReaders(){return readerRepository.findAll();}
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    public void addNewEmployee(Employee e){employeeRepository.save(e);}
    public Employee getEmployeeByEmail(String email){return employeeRepository.findByEmail(email);}
    public List<Reservation> getReservations() {return this.reservationRepository.findAll();}
    public void addNewReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public Reservation getReservationByBookAndReader(Book book, Reader reader){
        return this.reservationRepository.findByBookAndReader(book,reader);
    }
    public List<Review> getReviews(){
        return reviewRepository.findAll();
    }

    public List<Rental> getRentals() {return rentalRepository.findAll();}

    public List<Author> getAuthors() {return authorRepository.findAll();}

    public List<Rental> getRentalsByReaderEmail(String email){return rentalRepository.findRentalsByReader(this.getReaderByEmail(email));}

    public List<Rental> getRentalsByReader(Reader reader){
        return rentalRepository.findRentalsByReader(reader);
    }

    public List<Rental> getRentalsByBookCopy(BookCopy bookCopy){return rentalRepository.findRentalsByBookCopy(bookCopy);}

    public List<Rental> getRentalsWithoutAcceptance(){return rentalRepository.findRentalsWithoutAcceptance();}


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

    public Map<Book, Long> getAvailableBookCount(List<Book> books){
        Map<Book, Long> bookCount = new HashMap<>();
        for(Object[] obj: bookCopyRepository.findBooksWithCopyCount(books)) {
            bookCount.put((Book) obj[0],(Long) obj[1]);
        }
        return bookCount;
    }

    public String getEmployeePasswordByEmail(String email) {
        if (getEmployeeByEmail(email) == null){
            return null;
        }
        return getEmployeeByEmail(email).getPassword();
    }
//    public List<Book> getMostPopularBooks(int n){
//        List<Object[]> books = bookRepository.findBooksWithRentalCount();
//        return (List<Book>) books.stream().map(el -> el[0]).limit(n);
//    }
    public List<Book> getAllBooksByAuthorSurnameOrCategoryName(String authorSurname, String categoryName) {
        return this.bookRepository.getAllByAuthorSurname(authorSurname, categoryName);
    }

    public Page<Book> getBooksFilteredSorted(int page, String categoryName, SortOrder sortDirection){
        Sort sort;
        if (sortDirection == null) {
            sort = Sort.unsorted();
        } else {
            sort = Sort.by(sortDirection.toSpringDirection(), "title");
        }

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);
        return this.bookRepository.findBooksByCategoryAndSort(categoryName, pageable);
    }

    public List<Reservation> getReservationsByBook(Book book){
        return this.reservationRepository.getReservationByBook(book);
    }

    public void deleteAllReservationsByBook(List<Reservation> reservations){
        this.reservationRepository.deleteAll(reservations);
    }
}
