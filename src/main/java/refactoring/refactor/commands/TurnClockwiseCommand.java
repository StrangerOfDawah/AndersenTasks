package main.java.refactoring.refactor.commands;
import main.java.refactoring.refactor.receivers.Transport;

public class TurnClockwiseCommand implements Command {

    Transport transport;

    public TurnClockwiseCommand(Transport transport) {
        this.transport = transport;
    }

    @Override
    public void execute() {
        transport.turnClockwise();
    }
}


