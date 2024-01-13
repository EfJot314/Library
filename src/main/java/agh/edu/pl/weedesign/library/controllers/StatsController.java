package agh.edu.pl.weedesign.library.controllers;

import agh.edu.pl.weedesign.library.LibraryApplication;
import agh.edu.pl.weedesign.library.entities.book.Book;
import agh.edu.pl.weedesign.library.entities.category.Category;
import agh.edu.pl.weedesign.library.entities.reader.Reader;
import agh.edu.pl.weedesign.library.entities.rental.Rental;
import agh.edu.pl.weedesign.library.helpers.Themes;
import agh.edu.pl.weedesign.library.sceneObjects.SceneType;
import agh.edu.pl.weedesign.library.services.ModelService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StatsController {

    @FXML
    private Text readersText;
    @FXML
    private Text booksText;
    @FXML
    private Text employeesText;
    @FXML
    private PieChart sexPlot;
    @FXML
    private BarChart bookPlot;
    @FXML
    private BarChart rentedBooksPlot;
    @FXML
    private LineChart rentalPlot;

    // Navbar controls 
    @FXML
    private Button mainPage; 

    @FXML
    private Button myRentals; 

    @FXML
    private Button logOut; 

    @FXML 
    private ChoiceBox<String> themeChange;


    final private ModelService modelService;

    @Autowired
    public StatsController(ModelService service){
        this.modelService = service;
    }

    @FXML
    public void initialize(){
        themeChange.getItems().addAll(Themes.getAllThemes());
        themeChange.setOnAction(this::changeTheme);
        themeChange.setValue(LibraryApplication.getTheme());

        //get rental data (often used)
        List<Rental> rentals = this.modelService.getRentals();


        //text stats
        this.booksText.setText("Liczba książek w bibliotece: " + this.modelService.getBooksCopies().size());
        this.employeesText.setText("Liczba pracowników biblioteki: " + this.modelService.getEmployees().size());
        this.readersText.setText("Liczba czytelników biblioteki: " + this.modelService.getReaders().size());

        //sexy plot
        int male = 0;
        int female = 0;
        int other = 0;
        for(Reader reader : this.modelService.getReaders()){
            if(reader.getSex().equals("male"))
                male += 1;
            else if (reader.getSex().equals("female"))
                female += 1;
            else
                other += 1;
        }
        PieChart.Data maleData = new PieChart.Data("Mężczyźni", male);
        PieChart.Data femaleData = new PieChart.Data("Kobiety", female);
        PieChart.Data otherData = new PieChart.Data("Nie podano", other);
        this.sexPlot.legendVisibleProperty().set(false);
        this.sexPlot.getData().addAll(maleData, femaleData, otherData);

        //book categories
        XYChart.Series<String, Number> bookSeries = new XYChart.Series<>();
        for(Category category : this.modelService.getCategories()){
            int n = 0;
            for(Book book : this.modelService.getBooks())
                if(book.getCategory().getName().equals(category.getName()))
                    n += 1;
            bookSeries.getData().add(new XYChart.Data<>(category.getName(), n));
        }
        this.bookPlot.legendVisibleProperty().set(false);
        this.bookPlot.getData().add(bookSeries);

        //rented books
        XYChart.Series<String, Number> rentedSeries = new XYChart.Series<>();
        rentedSeries.getData().add(new XYChart.Data<>("Liczba wszystkich książek", this.modelService.getBooksCopies().size()));
        int rented = 0;
        for(Rental rental : rentals)
            if(rental.getEnd_date() == null)
                rented += 1;
        rentedSeries.getData().add(new XYChart.Data<>("Liczba aktualnie wypożyczonych książek", rented));
        this.rentedBooksPlot.legendVisibleProperty().set(false);
        this.rentedBooksPlot.getData().add(rentedSeries);

        //rental plot
        LocalDate start = LocalDate.now().minusYears(1);
        int[] tab = new int[12];
        for(Rental rental : rentals){
            if(rental.getEmployee() != null){
                for(int i=0;i<12;i++){
                    if(rental.getStart_date().toLocalDate().isBefore(start.plusMonths(i+1)) && (rental.getEnd_date() == null || rental.getEnd_date().toLocalDate().isAfter(start.plusMonths(i))))
                        tab[i] += 1;
                }
            }
        }
        boolean flag = true;
        XYChart.Series<String, Number> rentalSeries = new XYChart.Series<>();
        for(int i=0;i<12;i++){
            //if first months are equal to 0, then do not add them to the plot
            if(flag && tab[i] == 0)
                continue;
            flag = false;
            rentalSeries.getData().add(new XYChart.Data<>(start.plusMonths(i).toString(), tab[i]));
        }
        this.rentalPlot.legendVisibleProperty().set(false);
        this.rentalPlot.getData().add(rentalSeries);

        //


    }

    public void goBackAction(){
        LibraryApplication.getAppController().back();
    }

    public void goForwardAction(){
        LibraryApplication.getAppController().forward();
    }

    public void mainPageButtonHandler(){
        LibraryApplication.getAppController().switchScene(SceneType.BOOK_LIST); 
    }

    public void myRentalsButtonHandler(){
        LibraryApplication.getAppController().switchScene(SceneType.RENTALS_VIEW); 
    }

    public void changeTheme(ActionEvent e){
        LibraryApplication.changeTheme(themeChange.getValue());
    }

    public void LogOutAction(){
        LibraryApplication.getAppController().logOut();
    }

}
