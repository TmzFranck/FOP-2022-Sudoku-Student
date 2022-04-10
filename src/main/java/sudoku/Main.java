package sudoku;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        final SudokuSolver sudokuSolver = new SudokuSolverImpl();
        final GridGenerator gridGenerator = new RandomGridGenerator(sudokuSolver, 0.2);
        final GridChecker gridChecker = new GridCheckerImpl();
        final GridPrinter gridPrinter = new GridPrinterImpl();
        final Board board = new Board(gridGenerator, gridChecker, gridPrinter);
        board.run();
    }
}
