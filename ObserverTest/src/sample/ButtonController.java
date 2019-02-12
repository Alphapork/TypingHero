package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonController implements EventHandler<ActionEvent>
{
    private Model model;

    public ButtonController(Model model)
    {
        this.model = model;
    }

    public void handle(ActionEvent ev)
    {
        model.sendToServer();
    }
}
