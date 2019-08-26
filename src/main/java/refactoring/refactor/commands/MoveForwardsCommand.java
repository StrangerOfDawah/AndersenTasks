package main.java.refactoring.refactor.commands;


import main.java.refactoring.refactor.receivers.Transport;

public class MoveForwardsCommand implements Command {
    Transport transport;

    public MoveForwardsCommand(Transport transport) {
        this.transport = transport;
    }

    @Override
    public void execute() {
        transport.moveForwards();
    }
}
