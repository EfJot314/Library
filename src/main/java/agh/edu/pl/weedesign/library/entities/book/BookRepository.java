package agh.edu.pl.weedesign.library.entities.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
//    @Query("SELECT b, COUNT(bc) FROM Book b JOIN b.bookCopies bc GROUP BY b")
//    List<Object[]> findBooksFromCategory(Category c);
//
//    @Query("SELECT b, COUNT(r) as count FROM Book b JOIN b.bookCopies bc JOIN bc.rentals r GROUP BY b, bc ORDER BY count")
//    List<Object[]> findBooksWithRentalCount();

    @Query("SELECT b FROM Book b JOIN Author a JOIN Category c WHERE a.surname LIKE :authorSurname OR c.name LIKE :categoryName")
    List<Book> getAllByAuthorSurname(String authorSurname, String categoryName);


    @Query("SELECT b FROM Book b WHERE (:category is null OR b.category.name = :category)")
    Page<Book> findBooksByCategoryAndSort(
            @Param("category") String category,
            Pageable pageable
    );

}
