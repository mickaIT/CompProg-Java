package pl.cp.exercise3;

import java.util.Arrays;
//import java.util.Random;

public class SudokuBoard {

    public static final int BoardSize = 9;
    public static final int BlockSize = (int) Math.sqrt(BoardSize);

    private int[][] board = new int[BoardSize][BoardSize];

    //public void setBoard(int[][] board) {
    //    this.board = board;
    //}
    public int[][] getBoard() {
        return this.board.clone();
    }

    private boolean alreadyInRow(int n, int row) {
        for (int x = 0; x < BoardSize; x++) {
            if (this.board[row][x] == n) {
                return true;
            }
        }
        return false;
    }

    private boolean alreadyInCol(int n, int col) {
        for (int y = 0; y < BoardSize; y++) {
            if (this.board[y][col] == n) {
                return true;
            }
        }
        return false;
    }

    private boolean alreadyInBlock(int n, int row, int col) {
        final int y0 = Math.floorDiv(row, BlockSize) * BlockSize;
        final int x0 = Math.floorDiv(col, BlockSize) * BlockSize;

        for (int y = y0; y < y0 + BlockSize; y++) {
            for (int x = x0; x < x0 + BlockSize; x++) {
                if (this.board[y][x] == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean tryNumber(int n, int row, int col) {
        return !(alreadyInBlock(n, row, col)
                || alreadyInCol(n, col)
                || alreadyInRow(n, row));
    }

    private void fillBoardBacktrack(int n, int y, int x) {
        if (y == BoardSize) {
            // Board filled â€” done
            return;
        }

        if (n > 9) {
            // All numbers tried - backtrack
            if (x - 1 >= 0) {
                int previous = this.board[y][x - 1];
                this.board[y][x - 1] = 0;
                fillBoardBacktrack(previous + 1, y, x - 1);
            } else if (y - 1 >= 0) {
                int previous = this.board[y - 1][BoardSize - 1];
                this.board[y - 1][BoardSize - 1] = 0;
                fillBoardBacktrack(previous + 1, y - 1, BoardSize - 1);
            }
        } else {
            if (tryNumber(n, y, x)) {
                // Number fits â€” onwards
                this.board[y][x] = n;
                if (x + 1 < BoardSize) {
                    fillBoardBacktrack(1, y, x + 1);
                } else {
                    fillBoardBacktrack(1, y + 1, 0);
                }
            } else {
                // Otherwise try next number
                fillBoardBacktrack(n + 1, y, x);
            }
        }

    }       

    /**
     * Generates a new (complete) layout
     */
    public void fillBoard() {
        int seed = this.board[0][0] < 9 ? this.board[0][0] + 1 : 1;

        for (int y = 0; y < BoardSize; y++) {
            Arrays.fill(this.board[y], 0);
        }

        //Random r = new Random(System.nanoTime());
        //fillBoardBacktrack(r.nextInt(8) + 1, 0, 0);
        fillBoardBacktrack(seed, 0, 0);
    }
}
