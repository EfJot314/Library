package agh.edu.pl.weedesign.library.entities.book;

import agh.edu.pl.weedesign.library.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
//    @Query("SELECT b, COUNT(bc) FROM Book b JOIN b.bookCopies bc GROUP BY b")
//    List<Object[]> findBooksFromCategory(Category c);
//
//    @Query("SELECT b, COUNT(r) as count FROM Book b JOIN b.bookCopies bc JOIN bc.rentals r GROUP BY b, bc ORDER BY count")
//    List<Object[]> findBooksWithRentalCount();

}
