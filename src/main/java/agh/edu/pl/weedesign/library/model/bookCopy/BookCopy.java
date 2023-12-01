package agh.edu.pl.weedesign.library.model.bookCopy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookCopy {
    @Id @GeneratedValue
    private int id;

    private int week_unit_price;

    private String condition;

    public BookCopy(){};

    public BookCopy(int week_unit_price, String condition){
        this.week_unit_price = week_unit_price;
        this.condition = condition;
    }

    public int getWeek_unit_price() {
        return week_unit_price;
    }

    public void setWeek_unit_price(int week_unit_price) {
        this.week_unit_price = week_unit_price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
