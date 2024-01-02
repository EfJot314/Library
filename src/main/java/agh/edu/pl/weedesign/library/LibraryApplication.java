package agh.edu.pl.weedesign.library;

import agh.edu.pl.weedesign.library.controllers.LibraryAppController;

import agh.edu.pl.weedesign.library.entities.reader.Reader;
import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class LibraryApplication extends Application {
	private Stage primaryStage;
	private static LibraryAppController libraryAppController;

	private static ConfigurableApplicationContext context;

	private static Reader reader;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(LibraryApplication.class);
		builder.application().setWebApplicationType(WebApplicationType.NONE);
		context = builder.run();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library App");

		libraryAppController = new LibraryAppController(this.primaryStage, context);
		libraryAppController.initWelcomeLayout();
	}

	public static ConfigurableApplicationContext getAppContext(){
		return context;
	}

	public static LibraryAppController getAppController(){
		return libraryAppController;
	}

	public static Reader getReader(){
		return reader;
	}

	public static void setReader(Reader r){
		reader = r;
	}
}
