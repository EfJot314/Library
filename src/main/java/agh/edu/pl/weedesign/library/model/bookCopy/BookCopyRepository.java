package agh.edu.pl.weedesign.library.model.bookCopy;

import agh.edu.pl.weedesign.library.helpers.IBookCount;
import agh.edu.pl.weedesign.library.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
    @Query("SELECT b, COUNT(bc) FROM Book b JOIN b.bookCopies bc GROUP BY b")
    List<Object[]> findBooksWithCopyCount();
}
