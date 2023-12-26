package agh.edu.pl.weedesign.library.model.author;


import agh.edu.pl.weedesign.library.model.book.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="AUTHOR")
public class Author {
    @Id @GeneratedValue
    private int id;

    private String name;

    private String surname;

    @Column(length = 1024)
    private String bio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books;

    public Author(){}

    public Author(String name, String surname, String bio){
        this.name = name;
        this.surname = surname;
        this.bio = bio;
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

    public void setBook(Book book){
        this.books.add(book);
    }

}
