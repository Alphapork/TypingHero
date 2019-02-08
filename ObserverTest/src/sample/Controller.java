package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Controller implements EventHandler<KeyEvent> {
    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void handle(KeyEvent event) {
        model.check(event.getText());
    }
}
