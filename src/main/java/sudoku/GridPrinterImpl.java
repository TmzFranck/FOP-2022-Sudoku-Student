package sudoku;

public class GridPrinterImpl implements GridPrinter {
    @Override
    public void print(final Grid grid) {
        // TODO H1: Print grid
        for (int i = 0; i < 9; i++){
            for (int x = 0; x < 9; x++){
                System.out.print(grid.get(x, i) +  "   ");
            }
            System.out.println(" ");
            System.out.println(" ");
        }
    }
}
