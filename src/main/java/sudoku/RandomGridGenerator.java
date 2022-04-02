package sudoku;

public class RandomGridGenerator implements GridGenerator {

    private final SudokuSolver solver;

    /**
     * Range [0, 1].
     */
    private final double density;

    /**
     * Creates a new random grid generator.
     *
     * <p>
     * The density is in range [0, 1]. A density of 1 means that all original
     * solved cells are kept. A density of 0 means that no original cells are
     * kept (all cells would be 0).
     * </p>
     *
     * @param solver The solver to use to generate a solved grid
     * @param density The density of original solved cells to keep in range [0, 1].
     */
    public RandomGridGenerator(final SudokuSolver solver, final double density) {
        this.solver = solver;
        this.density = density;
    }

    @Override
    public Grid createGrid() {
        final Grid grid = new Grid(new int[9][9]);

        solver.solve(grid);

        final int[][] data = grid.copyData();

        // TODO: H3 set random entries in data to 0 based on density
        // lower density -> more 0s in array
        // use Math.random()
        String[] position = {
           "00", "01", "02", "03", "04", "05", "06", "07", "08",
            "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "30", "31", "32", "33", "34", "35", "36", "37", "38",
            "40", "41", "42", "43", "44", "45", "46", "47", "48",
            "50", "51", "52", "53", "54", "55", "56", "57", "58",
            "60", "61", "62", "63", "64", "65", "66", "67", "68",
            "70", "71", "72", "73", "74", "75", "76", "77", "78",
            "80", "81", "82", "83", "84", "85", "86", "87", "88",
        };

        return new Grid(data);
    }
}
