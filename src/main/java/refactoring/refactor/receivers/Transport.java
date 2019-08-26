package main.java.refactoring.refactor.receivers;

import refactoring.orientations.Orientation;

public interface Transport {

    void setOrientation(Orientation orientation);

    //void move(Command command);

    void moveForwards();

    void turnClockwise();

}
