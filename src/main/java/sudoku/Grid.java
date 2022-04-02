package sudoku;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a 9x9 sudoku grid.
 */
public class Grid {

    /**
     * The numbers of the sudoku grid.
     */
    private final int[][] data;

    /**
     * The fields which may not be changed.
     */
    private final boolean[][] permanent;

    public Grid(final int[][] data) {
        // make sure grid is correct size
        if (data.length != 9 || data[0].length != 9) {
            throw new IllegalArgumentException("Grid must be 9x9");
        }
        this.data = data;
        // save which squares were originally set
        // so that they are not changed later
        permanent = new boolean[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (data[y][x] != 0) {
                    permanent[y][x] = true;
                }
            }
        }
    }

    /**
     * @return The value of the sudoku grid at position x, y
     */
    public int get(final int x, final int y) {
        return data[y][x];
    }

    /**
     * Sets the value of the sudoku grid at position x, y.
     *
     * @throws IllegalArgumentException if x or y are out of bounds
     * @throws IllegalArgumentException if field at given position may not be changed
     */
    public void set(final int x, final int y, final int value) {
        checkBounds(x, y);
        if (permanent[y][x]) {
            throw new IllegalArgumentException(
                String.format("Field at position (%d, %d) cannot be changed", x, y));
        }
        data[y][x] = value;
    }

    /**
     * @return Whether the field in the sudoku grid at position x, y is set (i.e. not 0).
     */
    public boolean isSet(final int x, final int y) {
        checkBounds(x, y);
        return data[y][x] != 0;
    }

    public boolean isPermanent(final int x, final int y) {
        checkBounds(x, y);
        return permanent[y][x];
    }

    private void checkBounds(final int x, final int y) {
        if (x < 0 || x >= 9 || y < 0 || y >= 9) {
            throw new IllegalArgumentException(
                String.format("Illegal position (%d, %d), each co-ordinate must be in range [0, 8]", x, y));
        }
    }

    public boolean isFinished() {
        return Stream.of(data).flatMapToInt(IntStream::of).noneMatch(i -> i == 0);
    }

    public int[][] copyData() {
        return Arrays.stream(data).map(int[]::clone).toArray(int[][]::new);
    }
}
