package sample;

public class ArgCommand {
    private Commands command;
    private Object arg;

    public ArgCommand(Commands command, Object arg) {
        this.command = command;
        this.arg = arg;
    }
    public Commands getCommand() {
        return command;
    }

    public Object getArg() {
        return arg;
    }
}
