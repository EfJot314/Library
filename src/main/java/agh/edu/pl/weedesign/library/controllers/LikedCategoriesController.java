package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.category.Category;
import agh.edu.pl.weedesign.library.entities.reader.ReaderRepository;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

@Controller
public class LikedCategoriesController {
    private final ModelService modelService;
    private final ReaderRepository readerRepository;
    @FXML
    public ListView itemListView;
    @FXML
    public Button saveButton;
    private ObservableList<Category> allCategories;
    private ObservableList<Category> selectedCategories;
    @Autowired
    public LikedCategoriesController(ModelService modelService, ReaderRepository readerRepository){
        this.modelService = modelService;
        this.readerRepository = readerRepository;
        this.allCategories = FXCollections.observableArrayList(modelService.getCategories());
        this.selectedCategories = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        itemListView.setItems(allCategories);
        itemListView.setCellFactory(lv -> new ListCell<Category>() {
            private CheckBox checkBox;

            @Override
            public void updateItem(Category item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    checkBox = new CheckBox(item.getName());
                    checkBox.setSelected(selectedCategories.contains(item));
                    setGraphic(checkBox);
                    checkBox.setOnAction(e -> {
                        if (checkBox.isSelected()) {
                            if (!selectedCategories.contains(item)) {
                                selectedCategories.add(item);
                            }
                        } else {
                            selectedCategories.remove(item);
                        }
                    });
                }
            }
        });
    }
    @FXML
    private void saveLikedCategories(){
        LibraryApplication.getReader().setLikedCategories(new ArrayList<>(selectedCategories));
        LibraryApplication.getReader().setShow_recommendations(true);
        this.readerRepository.save(LibraryApplication.getReader());
        LibraryApplication.getAppController().switchScene(SceneType.BOOK_LIST);
    }
}
