package agh.edu.pl.weedesign.library.entities.employee;

import javax.persistence.*;

import agh.edu.pl.weedesign.library.entities.rental.Rental;

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
    @JoinColumn
    private Employee reports_to;


    public Employee(){};

    public Employee(String name, String surname, int salary){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSupervisor(Employee employee){
        this.reports_to = employee;
    }

    public Employee getSupervisor(){
        return reports_to;
    }


}