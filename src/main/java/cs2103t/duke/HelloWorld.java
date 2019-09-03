package cs2103t.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        helloWorld.setTextFill(Color.CHOCOLATE);
        helloWorld.setFont(new Font("Arial", 50));

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        Stage myStage = new Stage();
        Label hi = new Label("CS2103T is killing me");
        Scene myScene = new Scene(hi);
        myStage.setScene(myScene);


    }
}