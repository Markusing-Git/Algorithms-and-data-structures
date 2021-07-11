package algorithms.recursion;

import java.util.Scanner;

/**
 * Recursion example with backtracking
 * The problem: present all the solutions possible for placing n-queens on a board.
 * There can only be one queen on each row and diagonal for it to be a solution.
 * The algorithm eventually finds the right solution by
 * trying out possible placements and returning false if a certain pattern did not work out.
 *
 * @author mIngemarsson
 */
public class NQueens {
    public static class ChessBoard {
        private boolean[][] board;
        private boolean[] neDiagonal;
        private boolean[] nwDiagonal;
        int rows, columns, size;

        public ChessBoard(int size) {
            this.size = size;
            board = new boolean[size][size];
            neDiagonal = new boolean[size * 2];
            nwDiagonal = new boolean[size * 2];
            rows = columns = size;
        }

        public void addQueen() {
            for (int sol = 0; sol < size; sol++) {
                System.out.println("solution: " + (sol + 1));
                addQueen(1, sol);
                clearBoard();
            }
        }

        //recursive backtracking method
        private boolean addQueen(int row, int col) {
            for (int i = 0; i < columns; i++) {
                col = (col + 1) % size; //if end of row wrap around.
                if (checkCol(col) && !neDiagonal[row - 1 + col] && !nwDiagonal[row - 1 - col + size - 1]) {
                    board[row - 1][col] = true;
                    updateDiagonal(row - 1, col, true);
                    if (row == size) {
                        print();
                    } else {
                        if (addQueen(row + 1, col)) {
                            return true;
                        } else {
                            board[row - 1][col] = false;
                            updateDiagonal(row - 1, col, false);
                        }
                    }
                }
            }
            return false;
        }

        public void print() {
            for (int i = 0; i < rows; i++) {
                System.out.print("[ ");
                for (int j = 0; j < columns; j++)
                    if (board[i][j])
                        System.out.print("Q" + " ");
                    else
                        System.out.print("X" + " ");
                System.out.print("]");
                System.out.println();
            }
            System.out.println();
        }

        public void clearBoard() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++)
                    board[i][j] = false;
            }
            for (int i = 0; i < size * 2; i++) {
                neDiagonal[i] = false;
                nwDiagonal[i] = false;
            }
        }

        private boolean checkCol(int col) {
            for (int i = 0; i < size; i++) {
                if (board[i][col])
                    return false;
            }
            return true;
        }

        private void updateDiagonal(int row, int column, boolean status) {
            neDiagonal[row + column] = status;
            nwDiagonal[row - column + size - 1] = status;
        }
    }

    public static void main(String[] args){
        int n=8;
        while (true) {
            System.out.println("Enter nr of queens or 0 to terminate program.");
            Scanner scan = new Scanner(System.in);
            n = scan.nextInt();
            if(n==0)
                break;
            ChessBoard board = new ChessBoard(n);
            board.print();
            board.addQueen();
        }
    }
}
