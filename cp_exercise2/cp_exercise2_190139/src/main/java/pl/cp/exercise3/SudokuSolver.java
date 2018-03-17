/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.cp.exercise3;

import java.util.Arrays;
//import static pl.cp.exercise3.SudokuBoard;
import java.util.Arrays;

import java.util.Random;
import static pl.cp.exercise3.SudokuBoard.BoardSize;

/**
 *
 * @author Kasia
 */
public class SudokuSolver {

    public void unsolveBoard(SudokuBoard board) {

        Random rn = new Random(System.nanoTime());

        for (int p = 0; p < 5; p++) {

            board.getBoard()[rn.nextInt(8) + 1][rn.nextInt(8) + 1] = 0;

        }
    }

    private void solversFillBoardBacktrack(int n, int y, int x, SudokuBoard board) {
        if (y == BoardSize) {
            // Board filled â€” done
            return;
        }

        if (n > 9) {
            // All numbers tried - backtrack
            if (x - 1 >= 0) {
                int previous = board.getBoard()[y][x - 1];
                board.getBoard()[y][x - 1] = 0;
                solversFillBoardBacktrack(previous + 1, y, x - 1, board);
            } else if (y - 1 >= 0) {
                int previous = board.getBoard()[y - 1][BoardSize - 1];
                board.getBoard()[y - 1][BoardSize - 1] = 0;
                solversFillBoardBacktrack(previous + 1, y - 1, BoardSize - 1 , board);
            }
        } else {
            if (board.tryNumber(n, y, x)) {
                // Number fits â€” onwards
                board.getBoard()[y][x] = n;
                if (x + 1 < BoardSize) {
                    solversFillBoardBacktrack(1, y, x + 1, board);
                } else {
                    solversFillBoardBacktrack(1, y + 1, 0, board);
                }
            } else {
                // Otherwise try next number
                solversFillBoardBacktrack(n + 1, y, x, board);
            }
        }

    }

    /**
     * Generates a new (complete) layout
     * @param board
     */
    public void solversFillBoard(SudokuBoard board) {
        //int seed = this.board[0][0] < 9 ? this.board[0][0] + 1 : 1;

// Spoiler alert: index == 3

        int seed = 0;
        
        for (int y = 0; y < BoardSize; y++) {
            for (int x = 0; x < BoardSize; x++) {
                
                if (board.getBoard()[y][x] == 0) {
                
                    seed = board.getBoard()[y][x];
                }
                solversFillBoardBacktrack(seed, 0, 0, board);
            }
        }

        //Random r = new Random(System.nanoTime());
        //fillBoardBacktrack(r.nextInt(8) + 1, 0, 0);
    }

}
/*  public void solve(SudokuBoard board) {

        int seed = board.getBoard()[0][0] < 9 ? board.getBoard()[0][0]
        ] + 1 : 1;

        for (int y = 0; y < board.BoardSize; y++) {
            Arrays.fill(board.getBoard()[y], 0);
        }

        //Random r = new Random(System.nanoTime());
        //fillBoardBacktrack(r.nextInt(8) + 1, 0, 0);
        board.fillBoardBacktrack(seed, 0, 0);

    }
}*/
