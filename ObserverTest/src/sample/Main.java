package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane bPane = new BorderPane();
        GUI root = new GUI();
        Model model = new Model();
        model.addObserver(root);
        model.start();
        Button sendButton = new Button("Send to Server");
        bPane.setCenter(root);
        bPane.setBottom(sendButton);
        primaryStage.setTitle("Typing Hero");
        primaryStage.setScene(new Scene(bPane, 300, 275));
        primaryStage.show();
        primaryStage.getScene().setOnKeyPressed(new Controller(model));
        sendButton.setOnAction(new sample.ButtonController(model));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
