package main.java.refactoring.refactor.receivers;

import refactoring.orientations.Orientation;
import refactoring.exceptions.TractorInDitchException;

public class Tractor  implements Transport {

    private int[] position = new int[]{0, 0};
    private int[] field = new int[]{5, 5};
    private Orientation tractorOrientation;

    public void setOrientation(Orientation tractorOrientation) {
        this.tractorOrientation = tractorOrientation;
    }

   /* public void move(Command command) {

        command.turnTo();

        System.out.println("Current position: " + position[0] + " " + position[1]);
    }*/



    public void moveForwards() {

        position = tractorOrientation.turnTo(position);

        if (position[0] > field[0] || position[1] > field[1]) {
            throw new TractorInDitchException();
        }
        System.out.println("Current position: " + position[0] + " " + position[1]);

    }

    public void turnClockwise() {

        tractorOrientation = tractorOrientation.swapDirection(tractorOrientation);
        System.out.println("Current position: " + position[0] + " " + position[1]);

    }


}
