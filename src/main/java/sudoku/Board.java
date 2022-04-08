package sudoku;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Board {

        private static final Pattern INPUT_PATTERN = Pattern.compile("\\s*(?<x>\\d),\\s*(?<y>\\d):\\s*(?<value>\\d)");

        private static void checkPos(final int pos) {
            if (0 > pos || pos >= 9) {
                throw new IllegalArgumentException(String.format("Position %d must be in range [0, 8]", pos));
            }
        }

        private static void checkValue(final int value) {
            if (0 > value || value >= 9) {
                throw new IllegalArgumentException(String.format("Value %d must be in range [1, 9]", value));
            }
        }

        // 9x9 grid
        private final Grid grid;
        private final GridChecker gridChecker;
        private final GridPrinter gridPrinter;

        private static class UpdateRequest {
            final int x;
            final int y;
            final int value;

            public UpdateRequest(final int x, final int y, final int value) {
                checkPos(x);
                checkPos(y);
                checkValue(value);
                this.x = x;
                this.y = y;
                this.value = value;
            }
        }

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
                if (handleNext(in)) {
                    break;
                }
            }
        }

        /**
         * @return Whether program should quit
         */
        private boolean handleNext(final Scanner in) {
            System.out.println("Enter next position in format 'x,y:value' or 'quit'");
            try {
                if (!in.hasNextLine()) {
                    System.out.println("No next line, exiting...");
                    return true;
                }
                final String nextLine = in.nextLine();
                if ("quit".equals(nextLine)) {
                    System.out.println("Goodbye");
                    return true;
                }
                final UpdateRequest next = nextPos(nextLine);
                grid.set(next.x, next.y, next.value);
            } catch (Exception e) {
                System.err.println("Could not parse next position :: " + e.getMessage());
                return handleNext(in);
            }
            return false;
        }

        private UpdateRequest nextPos(final String nextLine) {
            final Matcher matcher = INPUT_PATTERN.matcher(nextLine);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Must be in format 'x,y:value'");
            }
            return new UpdateRequest(
                Integer.parseInt(matcher.group("x")),
                Integer.parseInt(matcher.group("y")),
                Integer.parseInt(matcher.group("value"))
            );
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
