package agh.edu.pl.weedesign.library.entities.category;

import javax.persistence.*;

import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.reader.Reader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Category {
    @Id @GeneratedValue
    private int id;

    private String name;

    public Category(){};

    public Category(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "likedCategories")
    private List<Reader> readers;

}
