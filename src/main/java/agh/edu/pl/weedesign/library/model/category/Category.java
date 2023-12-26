package agh.edu.pl.weedesign.library.model.category;

import agh.edu.pl.weedesign.library.model.book.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

}
