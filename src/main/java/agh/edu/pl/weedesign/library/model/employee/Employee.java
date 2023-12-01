package agh.edu.pl.weedesign.library.model.employee;

import agh.edu.pl.weedesign.library.model.rental.Rental;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id @GeneratedValue
    private int id;

    private String name;

    private String surname;

    private int salary;

    @ManyToOne
    private Employee reports_to;

    @OneToMany
    private List<Rental> rentals;

    public Employee(){};

    public Employee(String name, String surname, int salary){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.rentals = new ArrayList<>();
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
