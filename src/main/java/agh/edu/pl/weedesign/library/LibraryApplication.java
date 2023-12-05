package agh.edu.pl.weedesign.library;

import agh.edu.pl.weedesign.library.controller.LibraryAppController;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class LibraryApplication extends Application {
	private Stage primaryStage;
	private LibraryAppController libraryAppController;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(LibraryApplication.class);
		builder.application()
				.setWebApplicationType(WebApplicationType.NONE);

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library App");

		this.libraryAppController = new LibraryAppController(this.primaryStage);
		this.libraryAppController.initWelcomeLayout();
	}
}
