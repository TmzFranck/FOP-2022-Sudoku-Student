package sudoku;


import java.util.Scanner;


public class Board {

    // 9x9 grid
    private final Grid grid;
    private final GridChecker gridChecker;
    private final GridPrinter gridPrinter;

    public Board(
        final GridGenerator gridGenerator,
        final GridChecker gridChecker,
        final GridPrinter gridPrinter
    ) {
        grid = gridGenerator.createGrid();
        this.gridChecker = gridChecker;
        this.gridPrinter = gridPrinter;
    }

    public void run() {
        final Scanner in = new Scanner(System.in);
        while (true) {
            if (grid.isFinished()) {
                checkGrid();
                break;
            }
            gridPrinter.print(grid);
            // TODO: H4 Parse user input & update grid
            String input = in.nextLine();
            if (input.equals("quit")){
                break;
            }
            else{
                    grid.set(Character.getNumericValue(input.charAt(2)),
                        Character.getNumericValue(input.charAt(0)),
                        Character.getNumericValue(input.charAt(4)));
            }
        }
    }

    private void checkGrid() {
        try {
            gridChecker.check(grid);
            System.out.println("Grid ok!");
        } catch (Exception e) {
            System.err.println("Grid not ok: " + e.getMessage());
        }
    }
}
