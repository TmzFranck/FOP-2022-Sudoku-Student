package sudoku;

import org.fusesource.jansi.Ansi;

import java.util.function.Consumer;

public class GridPrinterImpl implements GridPrinter {

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
        // TODO H1: Print grid
        System.out.println(toAnsi("┏ ━ ┳ ━ ┓ ━ ┳ ━ ┓ ━ ┳ ━ ┓ ━ ┳ ━ ┓ ━ ┓", Ansi::fgBlue, Ansi::bold));
        for (int i = 0; i < 9; i++){
            for (int x = 0; x < 9; x++){
                System.out.print(toAnsi("┃ ", Ansi::fgBlue, Ansi::bold));
                System.out.print(toAnsi( grid.get(x, i) + " ", Ansi::fgRed, Ansi::bold));
                if (x == 8){
                    System.out.print(toAnsi("┃", Ansi::fgBlue, Ansi::bold));
                }
            }
            System.out.println("");
            if(i != 8){
                System.out.println(toAnsi("┣ ━ ╋ ━ ┫ ━ ┫ ━ ┫ ━ ┫ ━ ┫ ━ ┫ ━ ┫ ━ ┫", Ansi::fgBlue, Ansi::bold));
            }
        }
        System.out.println(toAnsi("┗ ━ ┻ ━ ┛ ━ ┻ ━ ┛ ━ ┻ ━ ┛ ━ ┻ ━ ┛ ━ ┛", Ansi::fgBlue, Ansi::bold));
/*
        // you may use colors to e.g. draw permanent numbers as red
        // examples of box-drawing characters with colors:
        // if your terminal does not support colors, you do not need to use them
        System.out.println(toAnsi("┏ ━ ┳ ━ ┓", Ansi::fgRed, Ansi::bold));
        System.out.println(toAnsi("┃   ┃   ┃", Ansi::fgYellow, Ansi::bold));
        System.out.println(toAnsi("┣ ━ ╋ ━ ┫", Ansi::fgGreen, Ansi::bold));
        System.out.println(toAnsi("┃   ┃   ┃", Ansi::fgBlue, Ansi::bold));
        System.out.println(toAnsi("┗ ━ ┻ ━ ┛", Ansi::fgMagenta, Ansi::bold));

 */
    }
}
