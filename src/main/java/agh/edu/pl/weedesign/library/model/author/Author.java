package agh.edu.pl.weedesign.library.model.author;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {
    @Id @GeneratedValue
    private int id;

    private String name;

    private String surname;

    private String bio;

    public Author(){}

    public Author(String name, String surname, String bio){
        this.name = name;
        this.surname = surname;
        this.bio = bio;
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
