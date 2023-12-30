package agh.edu.pl.weedesign.library.entities.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agh.edu.pl.weedesign.library.entities.book.Book;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
