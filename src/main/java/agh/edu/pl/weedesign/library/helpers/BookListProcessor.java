package agh.edu.pl.weedesign.library.helpers;

import agh.edu.pl.weedesign.library.model.ModelService;
import agh.edu.pl.weedesign.library.model.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class BookListProcessor {
    ModelService service;

    @Autowired
    public BookListProcessor(ModelService service){
        this.service = service;
    }

    public List<Book> processList(List<Book> books, SearchStrategy searchBy, String searchName, SearchStrategy sortStrategy, SortOrder order, boolean onlyAvailable){
        List<Book> tempBooks = new ArrayList<>(books);
        if (onlyAvailable){
            tempBooks = onlyAvailableBooks(tempBooks);
        }
        if (searchBy != null && searchName != null && !searchName.isEmpty()){
            tempBooks = search(tempBooks,searchBy,searchName);
        }
        if (sortStrategy != null && order != null){
            sort(tempBooks, sortStrategy, order);
        }
        return tempBooks;
    }

    // Method does not change input list
    private List<Book> search(List<Book> books, SearchStrategy searchBy, String searchName){
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
    private void sort(List<Book> books, SearchStrategy sortStrategy, SortOrder order){
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


    private List<Book> onlyAvailableBooks(List<Book> books){
        List<Book> tempBooks = new ArrayList<>();
        Map<Book, Long> booksCount = this.service.getAvailableBookCount();
        for (Book book : books) {
            if (booksCount.get(book) != null) {
                tempBooks.add(book);
            }
        }
        return tempBooks;
    }
}
