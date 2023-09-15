import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final int BOARD_SIZE = 3;
    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';
    private static final char EMPTY_CELL = ' ';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = createBoard();

        printBoard(board);

        while (true) {
            playerTurn(board, scanner);
            if (isGameFinished(board)) {
                break;
            }

            printBoard(board);

            computerTurn(board);
            if (isGameFinished(board)) {
                break;
            }

            printBoard(board);
        }

        scanner.close();
    }

    private static char[][] createBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        return board;
    }

    private static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isGameFinished(char[][] board) {
        if (hasContestantWon(board, PLAYER_SYMBOL)) {
            printBoard(board);
            System.out.println("Player wins!");
            return true;
        }

        if (hasContestantWon(board, COMPUTER_SYMBOL)) {
            printBoard(board);
            System.out.println("Computer wins!");
            return true;
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }

        printBoard(board);
        System.out.println("The game ended in a tie!");
        return true;
    }

    private static boolean hasContestantWon(char[][] board, char symbol) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true; // Rows
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true; // Columns
            }
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true; // Diagonal from top-left to bottom-right
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true; // Diagonal from top-right to bottom-left
        }
        return false;
    }

    private static void computerTurn(char[][] board) {
        Random rand = new Random();
        int computerMove;
        while (true) {
            computerMove = rand.nextInt(9) + 1;
            int row = (computerMove - 1) / BOARD_SIZE;
            int col = (computerMove - 1) % BOARD_SIZE;
            if (isValidMove(board, row, col)) {
                board[row][col] = COMPUTER_SYMBOL;
                System.out.println("Computer chose " + computerMove);
                break;
            }
        }
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL) {
            return true;
        }
        return false;
    }

    private static void playerTurn(char[][] board, Scanner scanner) {
        int playerMove;
        while (true) {
            System.out.println("Where would you like to play? (1-9)");
            playerMove = scanner.nextInt();
            int row = (playerMove - 1) / BOARD_SIZE;
            int col = (playerMove - 1) % BOARD_SIZE;
            if (isValidMove(board, row, col)) {
                board[row][col] = PLAYER_SYMBOL;
                break;
            } else {
                System.out.println(playerMove + " is not a valid move.");
            }
        }
    }
}
