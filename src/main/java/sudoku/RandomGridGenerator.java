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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Math.random() > density) {
                    data[i][j] = 0;
                }
            }
        }
        return new Grid(data);
    }
}
