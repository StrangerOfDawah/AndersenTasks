package refactoring.orientations;

public interface Orientation {

    int[] turnTo(int[] position);

    Orientation swapDirection(Orientation orientation);
}