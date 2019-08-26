package refactoring.orientations;

public class North implements Orientation {
    @Override
    public int[] turnTo(int[] position) {
        position = new int[]{position[0], position[1] + 1};
        return position;
    }

    @Override
    public Orientation swapDirection(Orientation orientation) {
        return new East();
    }
}
