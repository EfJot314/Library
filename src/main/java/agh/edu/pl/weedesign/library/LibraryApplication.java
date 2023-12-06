package agh.edu.pl.weedesign.library;

import agh.edu.pl.weedesign.library.controller.LibraryAppController;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class LibraryApplication extends Application {
	private Stage primaryStage;
	private LibraryAppController libraryAppController;

	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(LibraryApplication.class);
		builder.application()
				.setWebApplicationType(WebApplicationType.NONE);
		context = builder.run();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Library App");

		this.libraryAppController = new LibraryAppController(this.primaryStage, context);
		this.libraryAppController.initWelcomeLayout();
	}
}
