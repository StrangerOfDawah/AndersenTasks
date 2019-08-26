package main.java.refactoring.refactor.invokers;

import main.java.refactoring.refactor.commands.Command;

public class Driver {
    Command moveForwards;
    Command turnClockwise;

    public Driver(Command moveForwards, Command turnClockwise) {
        this.moveForwards = moveForwards;
        this.turnClockwise = turnClockwise;
    }



    public void doMoveForwards(){
        moveForwards.execute();
    }

    public void doTurnClockwise(){
        turnClockwise.execute();
    }
}
