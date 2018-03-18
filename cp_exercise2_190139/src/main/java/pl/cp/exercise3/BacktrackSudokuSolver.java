/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.cp.exercise3;

import java.util.Arrays;
import java.util.Random;
import static pl.cp.exercise3.SudokuBoard.BoardSize;

/**
 *
 * @author Kasia
 */
public class BacktrackSudokuSolver implements SudokuSolver {

    @Override
    public void solve(SudokuBoard board) {

        int seed = board.getBoard()[0][0] < 9 ? board.getBoard()[0][0] + 1 : 1;

        for (int y = 0; y < BoardSize; y++) {
            Arrays.fill(board.getBoard()[y], 0);
        }


        //Random r = new Random(System.nanoTime());
        //fillBoardBacktrack(r.nextInt(8) + 1, 0, 0);
        board.fillBoardBacktrack(seed, 0, 0);

    }

    ;
    
     public void unsolveBoard(SudokuBoard board) {

        Random rn = new Random(System.nanoTime());

        for (int p = 0; p < 15; p++) {

            board.getBoard()[rn.nextInt(8) + 1][rn.nextInt(8) + 1] = 0;

        }
    }
}
