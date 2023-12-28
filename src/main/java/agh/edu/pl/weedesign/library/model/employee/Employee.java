package agh.edu.pl.weedesign.library.model.employee;

import javax.persistence.*;

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
    private AccessLevel accessLevel;

    public Employee(){};
    public Employee(String name, String surname, int salary){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.accessLevel = AccessLevel.NONE;
    }
    public Employee(String name, String surname, int salary, AccessLevel accessLevel){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.accessLevel = accessLevel;
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

    @Override
    public String toString(){
        return this.name + " " + this.surname;
    }


}
