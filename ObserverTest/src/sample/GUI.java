package sample;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Observable;
import java.util.Observer;

public class GUI extends StackPane implements Observer {
    private Text text;
    public GUI() {
        text = new Text();
        text.setFont(Font.font("Comic Sans MS", 30));
        getChildren().add(text);
    }

    public void DisplayChar(String c) {
        text.setText(c);

    }
    @Override
    public void update(Observable o, Object arg) {
        ArgCommand command = (ArgCommand)arg;
        Commands com = command.getCommand();

        switch (com) {
            case NEXT_WORD: DisplayChar((String)command.getArg());
        }
    }
}
