package agh.edu.pl.weedesign.library.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.helpers.Categories;
import agh.edu.pl.weedesign.library.models_mvc.NewBookModel;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Controller
public class NewBookController {

    NewBookModel model;

    @FXML
    private TextField book_number_text_field;

    @FXML
    private TextField title_text_field;

    @FXML
    private TextField author_first_name_text_field;

    @FXML
    private TextField author_surname_text_field;

    @FXML
    private TextField page_count_text_field;

    @FXML
    private TextField no_of_copies_text_field;

    @FXML
    private TextField book_cover_text_field;

    @FXML
    private TextArea book_description_text_area;

    @FXML
    private TextArea table_of_content_text_area;

    @FXML 
    private Button save_book_button;

    @FXML 
    private Button cancel_button;

    @FXML 
    private TextField message_label;

    @FXML 
    private TextField condition_textfield;


    @FXML
    public ComboBox<Categories> categories;

    @Autowired
    public NewBookController(NewBookModel model){
        this.model = model;
    }

    public void handleCancelAction(){
        LibraryApplication.getAppController().switchScene(SceneType.EMPLOYEE_PANEL);
    }

    public void saveBookAction(){
        try {
            this.model.setTitle(title_text_field.getText());
            this.model.setDescription(book_description_text_area.getText());
            this.model.setPageCount(Integer.valueOf(page_count_text_field.getText()));
            this.model.setTableOfContent(table_of_content_text_area.getText());
            this.model.setCover( book_cover_text_field.getText());
            this.model.setAuthorFirstName(author_first_name_text_field.getText());
            this.model.setAuthorSecondName(author_surname_text_field.getText());
            this.model.setNoOfCopies(Integer.valueOf(no_of_copies_text_field.getText()));
            this.model.setCondition(condition_textfield.getText());

            this.model.addNewBook();

            message_label.setText("");
            message_label.setVisible(false);
 
        } catch (Exception e){
            message_label.setText(String.valueOf(e.getMessage()));
            message_label.setVisible(true);
        }
    }

    @FXML
    public void initialize(){
        
    }

}
