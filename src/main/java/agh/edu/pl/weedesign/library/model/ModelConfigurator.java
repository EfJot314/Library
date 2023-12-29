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
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Configuration
public class ModelConfigurator {
    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository, BookRepository bookRepository, BookCopyRepository bookCopyRepository, CategoryRepository categoryRepository, EmployeeRepository employeeRepository, ReaderRepository readerRepository, RentalRepository rentalRepository, ReviewRepository reviewRepository) {
        return args -> {
            if (bookRepository.count() == 0) {

                Book book1 = new Book(
                        "The Lord of the Rings: The Fellowship of the Ring",
                        "Frodo Baggins, a hobbit from the Shire, inherits the One Ring from his uncle Bilbo. The Ring is a powerful artifact that can be used to destroy the world. Frodo and his friends, who form the Fellowship of the Ring, set out on a dangerous journey to Mordor to destroy the Ring.",
                        325,
                        "Chapter 1: A Long-Expected Party\n" +
                                "Chapter 2: The Shadow of the Past\n" +
                                "Chapter 3: Three is Company\n" +
                                "Chapter 4: A Knife in the Dark\n" +
                                "Chapter 5: The Council of Elrond",
                        "https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71bocXHoUvL._SL1500_.jpg");

                Book book2 = new Book(
                        "The Lord of the Rings: The Two Towers",
                        "The Fellowship of the Ring is broken, and Frodo Baggins and his loyal companion Samwise Gamgee continue their journey to Mordor alone. Meanwhile, Aragorn, Legolas, and Gimli pursue the Uruk-hai who kidnapped Merry and Pippin, while Gandalf is drawn into a battle with the forces of Sauron.",
                        297,
                        "Chapter 1: The Departure of Boromir\n" +
                                "Chapter 2: The Riders of Rohan\n" +
                                "Chapter 3: The Uruk-hai\n" +
                                "Chapter 4: Flight to the Ford\n" +
                                "Chapter 5: The White Rider\n" +
                                "Chapter 6: The King of the Golden Hall\n" +
                                "Chapter 7: Helm's Deep\n" +
                                "Chapter 8: The Road Goes Ever On",
                        "https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/A1y0jd28riL._SL1500_.jpg");

                Book book3 = new Book(
                        "The Lord of the Rings: The Return of the King",
                        "The third and final volume of The Lord of the Rings, The Return of the King, tells the story of the final battle between the forces of good and evil in Middle-earth. Frodo Baggins and Samwise Gamgee continue their journey to Mordor to destroy the One Ring, while Aragorn, Legolas, and Gimli lead the armies of Rohan and Gondor to battle Sauron's forces.",
                        378,
                        "Chapter 1: The Siege of Gondor\n" +
                                "Chapter 2: The Ride of the Rohirrim\n" +
                                "Chapter 3: The Muster of Rohan\n" +
                                "Chapter 4: The Battle of the Pelennor Fields\n" +
                                "Chapter 5: The Ride to Minas Tirith",
                        "https://www.harpercollins.com/cdn/shop/products/9780007322558_a654036a-87e1-46f1-a0e9-945860635095.jpg");

                Book book4 = new Book(
                        "The Martian",
                        "The Martian is a science fiction novel by Andy Weir, first published in 2011. The story follows Mark Watney, an American astronaut who is stranded on Mars after his crew is forced to evacuate the planet due to a severe dust storm. Watney must use his ingenuity and engineering skills to survive on Mars until a rescue mission can be launched.",
                        206,
                        "Chapter 1: The Six Camels\n" +
                                "Chapter 2: The First Days\n" +
                                "Chapter 3: Sol 6\n" +
                                "Chapter 4: Sol 8\n" +
                                "Chapter 5: Sol 14\n" +
                                "Chapter 6: Sol 18\n" +
                                "Chapter 7: Sol 23\n" +
                                "Chapter 8: Sol 33",
                        "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1413706054i/18007564.jpg");

                Book book5 = new Book(
                        "The Hitchhiker's Guide to the Galaxy",
                        "The Hitchhiker's Guide to the Galaxy is a comic science fiction series created by Douglas Adams. The series follows the adventures of Arthur Dent, a hapless Englishman, following the destruction of the Earth by the Vogons, a race of unpleasant and bureaucratic aliens. Arthur escapes Earth's destruction with his friend Ford Prefect, who turns out to be an alien researcher who was stranded on Earth for fifteen years while gathering information for the titular Hitchhiker's Guide to the Galaxy, a pan-galactic encyclopaedia and travel guide for hitchhikers.",
                        138,
                        "Chapter 1: The Earth Ends\n" +
                                "Chapter 2: The Restaurant at the End of the Universe\n" +
                                "Chapter 3: Zaphod Beeblebrox\n" +
                                "Chapter 4: The Heart of Gold\n" +
                                "Chapter 5: The Vogons\n" +
                                "Chapter 6: Marvin the Paranoid Android\n" +
                                "Chapter 7: Trillian",
                        "https://assets-eu-01.kc-usercontent.com/bcd02f72-b50c-0179-8b4b-5e44f5340bd4/f2c1107f-3fc3-4235-a480-524d1b40102e/hitchhikers-guide-to-the-galaxy-80s.jpg");

                Author tolkien = new Author("J.R.R.", "Tolkien", "John Ronald Reuel Tolkien (3 January 1892 – 2 September 1973) was an English writer, poet, philologist, and university professor, best known as the author of the classic high fantasy works The Hobbit and The Lord of the Rings. He is generally considered to be the father of modern fantasy literature, his tales having a lasting influence on the worldwide genre of fantasy writing.");
                Author weir = new Author("Andy", "Weir", "Andy Weir is an American software engineer and science fiction author. He is best known for his debut novel, The Martian, which was nominated for the Hugo Award and the Locus Award for Best Novel in 2011, and won the Goodreads Choice Award for Best Science Fiction and the Audie Award for Best Science Fiction/Fantasy Audiobook. The novel was also adapted into a 2015 film directed by Ridley Scott and starring Matt Damon.");
                Author adams = new Author("Douglas", "Adams", "Douglas Adams was an English author, humorist, and screenwriter. He was best known for his comic science fiction series The Hitchhiker's Guide to the Galaxy, which began life as a radio comedy broadcast on the BBC in 1978. Adams was also a writer and script editor for the television series Doctor Who and wrote scripts for the British Broadcasting Corporation from 1978 to 1980.");

                authorRepository.saveAll(List.of(tolkien, weir, adams));

                book1.setAuthor(tolkien);
                book2.setAuthor(tolkien);
                book3.setAuthor(tolkien);
                book4.setAuthor(weir);
                book5.setAuthor(adams);

                Category fantasy = new Category("Fantasy");
                Category scifi = new Category("Science Fiction");

                categoryRepository.saveAll(List.of(fantasy, scifi));

                book1.setCategory(fantasy);
                book2.setCategory(fantasy);
                book3.setCategory(fantasy);
                book4.setCategory(scifi);
                book5.setCategory(scifi);

                bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));

                BookCopy bc1 = new BookCopy(10, "good");
                BookCopy bc2 = new BookCopy(12, "very good");
                BookCopy bc3 = new BookCopy(7, "bad");
                BookCopy bc4 = new BookCopy(18, "good");
                BookCopy bc5 = new BookCopy(3, "bad");
                BookCopy bc6 = new BookCopy(5, "very good");

                bc1.setBook(book1);
                bc2.setBook(book2);
                bc3.setBook(book3);
                bc4.setBook(book5);
                bc5.setBook(book5);
                bc6.setBook(book1);

                bookCopyRepository.saveAll(List.of(bc1, bc2, bc3, bc4, bc5, bc6));

                Employee employee1 = new Employee("Filip", "Jedrzejewski", 1000);
                Employee employee2 = new Employee("Sebastian", "Soczawa", 976);
                Employee employee3 = new Employee("Piotr", "Sobczynski", 1000);
                Employee employee4 = new Employee("Dominik", "Szot", 1000);

                employee1.setSupervisor(employee3);
                employee2.setSupervisor(employee4);
                employee4.setSupervisor(employee3);

                employeeRepository.saveAll(List.of(employee1, employee2, employee3, employee4));

                Reader reader1 = new Reader("Jan", "Kowalski", "Krakow", "Malopolska", "31-503", "Polska", "example1@email.com", "pass1", "123 456 789", LocalDate.of(1987, 12, 3), "male");
                Reader reader2 = new Reader("Janka", "Kowalska", "Warszawa", "Mazowieckie", "02-677", "Polska", "example2@email.com", "pass2", "987 654 321", LocalDate.of(2003, 5, 30), "female");
                Reader reader3 = new Reader("Janusz", "Nowak", "Krakow", "Malopolska", "31-503", "Polska", "example3@email.com", "pass3", "999 888 777", LocalDate.of(1949, 11, 13), "male");

                Review review1 = new Review(5, "Super", LocalDateTime.of(2023, 12, 2, 15, 4));
                Review review2 = new Review(2, LocalDateTime.of(2023, 11, 30, 9, 36));

                reviewRepository.saveAll(List.of(review1, review2));


                readerRepository.saveAll(List.of(reader1, reader2, reader3));

                Rental r1 = new Rental(
                        LocalDateTime.of(2023, 11, 14, 13, 30),
                        LocalDateTime.of(2023, 12, 2, 9, 17)
                );
                Rental r2 = new Rental(
                        LocalDateTime.of(2023, 10, 10, 11, 27),
                        LocalDateTime.of(2023, 10, 24, 15, 5));

                Rental r3 = new Rental(
                        LocalDateTime.of(2023, 12, 1, 13, 1),
                        LocalDateTime.of(2024, 1, 1, 12, 0)
                );


                r1.setEmployee(employee1);
                r2.setEmployee(employee2);
                r3.setEmployee(employee4);

                reader1.setRentals(r1);
                reader2.setRentals(r2);
                reader3.setRentals(r3);

                r1.setReader(reader1);
                r2.setReader(reader2);
                r3.setReader(reader2);

                r1.setBookCopy(bc1);
                r2.setBookCopy(bc2);
                r3.setBookCopy(bc4);

                r1.setReview(review1);
                r2.setReview(review2);

                rentalRepository.saveAll(List.of(r1, r2, r3));
            }
        };
    }
}
