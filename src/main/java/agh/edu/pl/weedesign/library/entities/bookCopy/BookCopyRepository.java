package agh.edu.pl.weedesign.library.entities.bookCopy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
    @Query("SELECT b, COUNT(bc) FROM Book b JOIN b.bookCopies bc GROUP BY b")
    List<Object[]> findBooksWithCopyCount();

    @Query(
        "SELECT b FROM Book b" + 
        "   INNER JOIN b.bookCopies bc "
    )
    List<Object[]> findBookCopies(String bookId);
}
