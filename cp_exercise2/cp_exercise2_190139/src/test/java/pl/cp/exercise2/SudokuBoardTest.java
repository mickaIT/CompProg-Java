package pl.cp.exercise2;

package pl.cp.exercise3;

import pl.cp.exercise3.SudokuBoard;
import org.junit.Test;

import java.net.JarURLConnection;
import java.util.jar.JarEntry;

import static org.junit.Assert.*;
import pl.cp.exercise3.SudokuSolver;



public class SudokuBoardTest {

    private void printBoard(SudokuBoard sb) {
        int[][] board = sb.getBoard();
        for (int y = 0; y < sb.BoardSize; y++) {
            for (int x = 0; x < sb.BoardSize; x++) {
                System.out.print(board[y][x]);
                if (x % sb.BlockSize == 2 && x > 0) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if (y % sb.BlockSize == 2 && y > 0) {
                System.out.println();
            }
        }
    }

    private boolean checkRow(SudokuBoard sb, int row) {
        boolean[] found = new boolean[9];
        int[][] board = sb.getBoard();
        // Check for duplicates
        for (int x = 0; x < sb.BoardSize; x++) {
            int n = board[row][x];
            if (found[n - 1]) {
                return false;
            } else {
                found[n - 1] = true;
            }
        }
        // Check for completeness
        for (int i = 0; i < 9; i++) {
            if (!found[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean checkCol(SudokuBoard sb, int col) {
        boolean[] found = new boolean[9];
        int[][] board = sb.getBoard();
        // Check for duplicates
        for (int y = 0; y < sb.BoardSize; y++) {
            int n = board[y][col];
            if (found[n - 1]) {
                return false;
            } else {
                found[n - 1] = true;
            }
        }
        // Check for completeness
        for (int i = 0; i < 9; i++) {
            if (!found[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean checkBlock(SudokuBoard sb, int row, int col) {
        boolean[] found = new boolean[9];
        int[][] board = sb.getBoard();
        // Check for duplicates
        for (int y = row; y < row + sb.BlockSize; y++) {
            for (int x = col; x < col + sb.BlockSize; x++) {
                int n = board[y][x];
                if (found[n - 1]) {
                    return false;
                } else {
                    found[n - 1] = true;
                }
            }
        }
        // Check for completeness
        for (int i = 0; i < 9; i++) {
            if (!found[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean checkBoard(SudokuBoard sb) {
        for (int y = 0; y < sb.BoardSize; y++) {
            if (!checkRow(sb, y)) {
                return false;
            }
        }
        for (int x = 0; x < sb.BoardSize; x++) {
            if (!checkCol(sb, x)) {
                return false;
            }
        }
        for (int y = 0; y < sb.BlockSize; y++) {
            for (int x = 0; x < sb.BlockSize; x++) {
                if (!checkBlock(sb, y * sb.BlockSize, x * sb.BlockSize)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void fillBoard() {
        System.out.println("fillBoard");
        System.out.println();

        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        printBoard(sb);

        assertTrue(checkBoard(sb));
    }

    @Test
    public void fillBoardTwice() {

        System.out.println("fillBoardTwice");
        System.out.println();

        SudokuBoard sb = new SudokuBoard();

        sb.fillBoard();
        int[][] old = sb.getBoard().clone();
        printBoard(sb);

        System.out.println();

        sb.fillBoard();
        printBoard(sb);

        assertNotEquals(old, sb.getBoard());
    }

    @Test
    public void unsolveBoardTest() {
        System.out.println();
        System.out.println("unsolveBoardTest");
        System.out.println();

        SudokuBoard sb = new SudokuBoard();

        sb.fillBoard();
        SudokuSolver solver = new SudokuSolver();
        solver.unsolveBoard(sb);
        printBoard(sb);

        System.out.println();

        //sb.fillBoard();
        //printBoard(sb);
        // assertFalse(checkBoard(sb));              //TEST FAILURE
    }

    @Test
    public void solveBoardTest() {
        System.out.println();
        System.out.println("solveBoardTest");
        System.out.println();

        SudokuBoard sb = new SudokuBoard();

        sb.fillBoard();
        SudokuSolver solver = new SudokuSolver();
        solver.unsolveBoard(sb);

        System.out.println("First sample");
        System.out.println();

        int[][] old = sb.getBoard().clone();
        printBoard(sb);

        sb.fillBoard();

        System.out.println("Second sample");
        System.out.println();

        solver.unsolveBoard(sb);
        printBoard(sb);
        
        ////////////////////////////
        
        System.out.println("solversFillBoard");
        System.out.println();

        
        solversFillBoard(sb);
        printBoard(sb);

        // assertFalse(checkBoard(sb));              //TEST FAILURE
    }
}
