import java.util.Scanner;
import java.util.Random;

public class TicTacToeClassic {
    public static void main(String[] args) {
        // Creates a scanner to get the player's move
        Scanner scanner = new Scanner(System.in);

        // The random object generates the computer's move
        Random random = new Random();

        // Creates a new empty board
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        // GAME BEGINS
        playGame(board, scanner, random);

        // GAME ENDS
        System.out.println("GAME OVER");
        scanner.close();
    }

    // Starts the board game
    private static void playGame(char[][] board, Scanner scanner, Random random) {
        do {
            if (checkSpacesLeft(board)) {
                playerTurn(board, scanner);
            }
            if (checkSpacesLeft(board)) {
                computerTurn(board, random);
            }
        } while (checkSpacesLeft(board) && !checkWinner(board, 'X') && !checkWinner(board, 'O'));

        // Checks if the player won
        if (checkWinner(board, 'X')) {
            System.out.println("\n--- PLAYER WINS! ---");
        }
        // Checks if the computer won
        if (checkWinner(board, 'O')) {
            System.out.println("\n--- THE COMPUTER WON ---");
        }
        // If the board is out of spaces left, then it's a tie game
        if (!checkSpacesLeft(board) && !checkWinner(board, 'X')
                && !checkWinner(board, 'O')) {
            System.out.println("\n--- IT'S A TIE ---");
        }
        printBoard(board);
    }

    private static void playerTurn(char[][] board, Scanner scanner) {
        printBoard(board);
        String userInput;
        do {
            System.out.print("Where would you like to play? (1-9): ");
            userInput = scanner.nextLine();
        } while (!isValidMove(board, userInput));
        placeMove(board, userInput, 'X');
    }

    private static void computerTurn(char[][] board, Random random) {
        String computerMove;
        do {
            computerMove = String.valueOf(random.nextInt(9) + 1);
        } while (!isValidMove(board, computerMove));
        placeMove(board, computerMove, 'O');
    }

    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
        }
    }

    // Checks if either the player or the computer is choosing a valid position
    private static boolean isValidMove(char[][] board, String position) {
        return switch (position) {
            case "1" -> board[0][0] == ' ';
            case "2" -> board[0][1] == ' ';
            case "3" -> board[0][2] == ' ';
            case "4" -> board[1][0] == ' ';
            case "5" -> board[1][1] == ' ';
            case "6" -> board[1][2] == ' ';
            case "7" -> board[2][0] == ' ';
            case "8" -> board[2][1] == ' ';
            case "9" -> board[2][2] == ' ';
            default -> false;
        };
    }

    // Checks if either the player or the computer won
    private static boolean checkWinner(char[][] board, char symbol) {
        return (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    // If the board runs out of spaces, it's a tie
    private static boolean checkSpacesLeft(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printBoard(char[][] board) {
        System.out.println();
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("- + - + -");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("- + - + -");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println();
    }
}