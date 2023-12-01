package agh.edu.pl.weedesign.library.model.author;


import agh.edu.pl.weedesign.library.model.book.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id @GeneratedValue
    private int id;

    private String name;

    private String surname;

    private String bio;

    @OneToMany
    private List<Book> books;

    public Author(){}

    public Author(String name, String surname, String bio){
        this.name = name;
        this.surname = surname;
        this.bio = bio;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
