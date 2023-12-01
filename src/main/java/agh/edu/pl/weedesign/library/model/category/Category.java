package agh.edu.pl.weedesign.library.model.category;

import agh.edu.pl.weedesign.library.model.book.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private int id;

    private String name;

    @OneToMany
    private List<Book> books;

    public Category(){};

    public Category(String name){
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
