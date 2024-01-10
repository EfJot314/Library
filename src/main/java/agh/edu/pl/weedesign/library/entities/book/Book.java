package agh.edu.pl.weedesign.library.entities.book;


import javax.persistence.*;

import agh.edu.pl.weedesign.library.entities.author.Author;
import agh.edu.pl.weedesign.library.entities.bookCopy.BookCopy;
import agh.edu.pl.weedesign.library.entities.category.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="BOOK")
public class Book {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Column(length = 1024)
    private String description;

    private int page_count;

    @Column(length = 512)
    private String table_of_content;

    private String cover_url;

    @ManyToOne(fetch = FetchType.EAGER) // WARNING! (Pepe) - zmieniłem tu na EAGER bo nie wiedzialem jak się dostać do autora
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy="book")
    private Set<BookCopy> bookCopies;

    public Book(){}

    public Book(String title, String description, int page_count, String table_of_content, String cover_url){
        this.title = title;
        this.description = description;
        this.page_count = page_count;
        this.table_of_content = table_of_content;
        this.cover_url = cover_url;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public String getTable_of_content() {
        return table_of_content;
    }

    public void setTable_of_content(String table_of_content) {
        this.table_of_content = table_of_content;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setAuthor(Author author){
        this.author = author;
    }

    public Author getAuthor(){
        return author;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public Category getCategory(){
        return category;
    }

    public Set<BookCopy> getCopies(){
        return this.bookCopies;
    }

    public void setCopies(Set<BookCopy> bookCopies){
        this.bookCopies = bookCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
