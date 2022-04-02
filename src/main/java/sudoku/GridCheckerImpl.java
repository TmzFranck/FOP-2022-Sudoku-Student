package sudoku;

import java.util.ArrayList;
import java.util.Arrays;

public class GridCheckerImpl implements GridChecker {
    @Override
    public void check(final Grid grid) {
        // TODO: H2 Make sure board is valid
        int[] check1 = new int[9];
        int[] check2 = new int[9];
        ArrayList<Integer> check3 = new ArrayList<>();
        ArrayList<Integer> check4 = new ArrayList<>();
        ArrayList<Integer> check5 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 9; x++) {
                if(!grid.isSet(i, x)) {
                    throw new RuntimeException("Each cell must be filled.");
                }
                check1[x] = grid.get(i, x);
                check2[x] = grid.get(x, i);

                if (x <= 2) {
                    check3.add(grid.get(i, x));
                }
                if (x > 2 && x < 6) {
                    check4.add(grid.get(i, x));
                }
                if(x > 5) {
                    check5.add(grid.get(i, x));
                }

                }
            if (Arrays.stream(check1).sum() != 45 || Arrays.stream(check2).sum() != 45) {
                throw new RuntimeException("Each row and each column may only contain a number exactly once");

            }
            if (i == 2) {
                if (check3.stream().reduce(0, Integer::sum) != 45 ||
                    check4.stream().reduce(0, Integer::sum) != 45 ||
                    check5.stream().reduce(0, Integer::sum) != 45) {
                    throw new RuntimeException("Each of the 3 × 3 sectors may only " +
                        "have entered each number exactly once.");
                }
            }
            if (i == 5){
                if (check3.stream().reduce(0, Integer::sum) != 90 ||
                    check4.stream().reduce(0, Integer::sum) != 90 ||
                    check5.stream().reduce(0, Integer::sum) != 90) {
                    throw new RuntimeException("Each of the 3 × 3 sectors may only " +
                        "have entered each number exactly once.");
                }
            }

            if (i == 8){
                if (check3.stream().reduce(0, Integer::sum) != 135 ||
                    check4.stream().reduce(0, Integer::sum) != 135 ||
                    check5.stream().reduce(0, Integer::sum) != 135) {
                    throw new RuntimeException("Each of the 3 × 3 sectors may only " +
                        "have entered each number exactly once.");
                }
            }
            }
        }
}
