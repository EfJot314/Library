package agh.edu.pl.weedesign.library.model.reader;


import agh.edu.pl.weedesign.library.model.rental.Rental;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Reader {
    @Id @GeneratedValue
    private int id;

    private String name;

    private String surname;

    private String city;

    private String voivodeship;

    private String postal_code;

    private String country;

    private String email;

    private String phone_number;

    private LocalDate birth_date;

    private String sex;

    @OneToMany(mappedBy = "reader")
    Set<Rental> rentals;

    public Reader(){};

    public Reader(String name, String surname, String city, String voivodeship, String postal_code, String country, String email, String phone_number, LocalDate birth_date, String sex){
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.voivodeship = voivodeship;
        this.postal_code = postal_code;
        this.country = country;
        this.email = email;
        this.phone_number = phone_number;
        this.birth_date = birth_date;
        this.sex = sex;
        this.rentals = new HashSet<Rental>();
    }

    public void setRentals(Rental rental) {
        this.rentals.add(rental);
    }

    public void setRentals(Set<Rental> rentals){
        this.rentals.addAll(rentals);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<Rental> getRentals(){
        return this.rentals;
    }

}
