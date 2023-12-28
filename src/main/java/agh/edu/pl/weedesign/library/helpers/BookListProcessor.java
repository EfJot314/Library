package agh.edu.pl.weedesign.library.helpers;

import agh.edu.pl.weedesign.library.model.book.Book;
import agh.edu.pl.weedesign.library.model.author.Author;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BookListProcessor {

    static public List<Book> processList(List<Book> books,SearchStrategy searchBy, String searchName, SearchStrategy sortStrategy, SortOrder order){
        List<Book> tempBooks = new ArrayList<>(books);
        if (searchBy != null && searchName != null && !searchName.isEmpty()){
            tempBooks = search(tempBooks,searchBy,searchName);
        }
        if (sortStrategy != null && order != null){
            sort(tempBooks, sortStrategy, order);
        }
        return tempBooks;
    }

    // Method does not change input list
    static private List<Book> search(List<Book> books, SearchStrategy searchBy, String searchName){
        List<Book> tempBooks = new ArrayList<>();
        String searchLowerCase = searchName.toLowerCase();
        switch (searchBy){
            case NAME -> {
                for (Book book : books) {
                    if (book.getAuthor().getName().toLowerCase().contains(searchLowerCase)) {
                        tempBooks.add(book);
                    }
                }
            }
            case SURNAME -> {
                for (Book book : books) {
                    if (book.getAuthor().getSurname().toLowerCase().contains(searchLowerCase)) {
                        tempBooks.add(book);
                    }
                }
            }
            case TITLE -> {
                for (Book book : books) {
                    if (book.getTitle().toLowerCase().contains(searchLowerCase)) {
                        tempBooks.add(book);
                    }
                }
            }
        }
        return tempBooks;
    }

    // THIS METHOD SORTS IN PLACE!!!
    static private void sort(List<Book> books, SearchStrategy sortStrategy, SortOrder order){
        Comparator<Book> comparator = switch (sortStrategy) {
            case NAME -> Comparator.comparing(book -> book.getAuthor().getName());
            case SURNAME -> Comparator.comparing(book -> book.getAuthor().getSurname());
            case TITLE -> Comparator.comparing(Book::getTitle);
        };

        if (comparator != null) {
            if (order == SortOrder.DESCENDING) {
                comparator = comparator.reversed();
            }
            books.sort(comparator);
        }
    }
}
