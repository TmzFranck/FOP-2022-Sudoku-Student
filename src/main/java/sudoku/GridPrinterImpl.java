package sudoku;

import org.fusesource.jansi.Ansi;

import java.util.function.Consumer;

public class GridPrinterImpl implements GridPrinter {

    private static final char[] LEFT_CORNERS = {'┏', '┣', '┗',};
    private static final char[] MIDDLE_CORNERS = {'┳', '╋', '┻'};
    private static final char[] RIGHT_CORNERS = {'┓', '┫', '┛',};

    /**
     * Wraps the provided {@link String} with a configurable Ansi-sequence terminated with reset.
     */
    @SafeVarargs
    private static Ansi toAnsi(final String string, final Consumer<? super Ansi>... builders) {
        final Ansi ansi = new Ansi();
        for (final Consumer<? super Ansi> builder : builders) {
            builder.accept(ansi);
        }
        return ansi.a(string).reset();
    }

    @Override
    public void print(final Grid grid) {
        printHorizontal(0);
        for (int y = 0; y < 9; y++) {
            System.out.print("┃ ");
            for (int x = 0; x < 9; x++) {
                if (grid.isPermanent(x, y)) {
                    System.out.print(toAnsi(Integer.toString(grid.get(x, y)), Ansi::fgBlue, Ansi::bold) + " ");
                } else if (grid.isSet(x, y)) {
                    System.out.print(toAnsi(Integer.toString(grid.get(x, y)), Ansi::fgGreen, Ansi::bold) + " ");
                } else {
                    System.out.print(toAnsi(Integer.toString(grid.get(x, y)), Ansi::fgBrightDefault, Ansi::bold) + " ");
                }
                if (x % 3 == 2) {
                    // don't print space at the end
                    if (x == 8) {
                        System.out.print("┃");
                    } else {
                        System.out.print("┃ ");
                    }
                }
            }
            System.out.println();
            if (y % 3 == 2) {
                // print either middle or last horizontal bar
                // if y == 8, print last (with other corners)
                printHorizontal(y == 8 ? 2 : 1);
            }
        }
    }

    /**
     * Prints a horizontal bar.
     *
     * <p>
     * The following values for num are accepted:
     * </p>
     * <ul>
     *     <li>0 for the top bar</li>
     *     <li>1 for a middle bar</li>
     *     <li>2 for the last bar</li>
     * </ul>
     *
     * @param num The type of horizontal bar to print
     */
    private void printHorizontal(final int num) {
        System.out.print(LEFT_CORNERS[num]);
        for (int x = 1; x < 24; x++) {
            if (x % 8 == 0) {
                System.out.print(MIDDLE_CORNERS[num]);
            } else {
                System.out.print("━");
            }
        }
        System.out.println(RIGHT_CORNERS[num]);
    }
}
