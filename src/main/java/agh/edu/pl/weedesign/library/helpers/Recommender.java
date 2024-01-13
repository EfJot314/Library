package agh.edu.pl.weedesign.library.helpers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.category.Category;
import agh.edu.pl.weedesign.library.entities.reader.Reader;
import agh.edu.pl.weedesign.library.services.ModelService;
import com.sun.source.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class Recommender {
    private Reader reader;
    private Set<Category> userCategoryChoice;
    private Set<Category> allCategories;
    private boolean showRecommendations = true;
    private boolean showPopularBooks = true;
    private ModelService modelService;

    public Recommender(ModelService modelService){
        this.reader = LibraryApplication.getReader();
        this.modelService  = modelService;
    }

    private static Set<Category> getMostPopularCategories(int count){
        return new HashSet<>();
    }
    public Set<Book> getRecommendedBooks(int count){
        return this.modelService.getBooks().stream().limit(count).collect(Collectors.toSet());
    }

    public Set<Book> getMostPopularBooks(int count){
        return this.modelService.getBooks().stream().limit(count).collect(Collectors.toSet());
    }
    public boolean showRecommendations(){
        return  this.showRecommendations;
    }
    public boolean showPopularBooks(){
        return this.showPopularBooks;
    }

    public void setShowPopularBooks(boolean value){
        this.showPopularBooks = value;
    }

    public void setShowRecommendations(boolean value){
        this.showRecommendations = value;
    }
}
