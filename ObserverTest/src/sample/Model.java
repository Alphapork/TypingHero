package sample;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    sample.Network Client = new sample.Network();
    private int pos;
    private ArrayList<String> words;
    private int wordno;

    public Model() {
        words = new ArrayList<String>();
        words.add("hej");
        words.add("hoj");
        words.add("hallå");
        pos = 0;
        wordno = 0;
    }

    public String getCurrentWord() {
        return words.get(wordno);
    }

    public void start() {
        setString(words.get(wordno));

    }

    public void setString(String newc) {
        setChanged();
        notifyObservers(new ArgCommand(Commands.NEXT_WORD, getCurrentWord()));
    }

    public void sendCommand(Commands command) {
        setChanged();
        notifyObservers(command);
    }

    public void check(String input) {
        System.out.println(input + words.get(wordno).charAt(pos));
        if(input.charAt(0) == words.get(wordno).charAt(pos)) {
            System.out.println("CORRECT");
            setChanged();
            notifyObservers(new ArgCommand(Commands.CORRECT, null));
            pos++;
            if (pos >= words.get(wordno).length()) {
                wordno++;
                if (wordno >= words.size()) {
                    wordno = 0;
                }
                start();
                pos = 0;
            }
        } else {
            System.out.println("WRONG");
            setChanged();
            notifyObservers(new ArgCommand(Commands.INCORRECT, null));
        }
    }

    public void sendToServer()
    {
        Client.send();
    }
}
