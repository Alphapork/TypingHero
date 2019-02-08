package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GUI root = new GUI();
        Model model = new Model();
        model.addObserver(root);
        model.start();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.getScene().setOnKeyPressed(new Controller(model));

    }


    public static void main(String[] args) {
        launch(args);
    }
}
