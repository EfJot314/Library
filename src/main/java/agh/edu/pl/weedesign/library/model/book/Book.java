package agh.edu.pl.weedesign.library.model.book;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id @GeneratedValue
    private int id;

    private String title;

    private String description;

    private int page_count;

    private String table_of_content;

    private String cover_url;

    public Book(){}

    public Book(String title, String description, int page_count, String table_of_content, String cover_url){
        this.title = title;
        this.description = description;
        this.page_count = page_count;
        this.table_of_content = table_of_content;
        this.cover_url = cover_url;
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
}
