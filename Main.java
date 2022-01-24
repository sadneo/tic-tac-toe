import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Board board = new Board();

    private static int currentPlayer;
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");

        boolean playing = true;
        do {
            board.wipe();
            startGame();

            System.out.print("Well played, would you like to play another? (1 if yes, 0 if not) ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Okay.");
            } else if (choice == 0) {
                System.out.println("Okay");
                playing = false;
            } else {
                System.out.println("I'll just take that as a no.");
            }
        } while (playing);

        scanner.close();
    }
    
    private static void startGame() {
        currentPlayer = 1;

        while (true) {
            board.displayBoard();
            move();

            boolean playing = gameStatus();
            if (!playing) {
                board.displayBoard();
                break;
            }

            currentPlayer++;
            if (currentPlayer == 3) {
                currentPlayer = 1;
            }
        }
    }
    
    private static void move() {
        System.out.print("Player " + currentPlayer + ", make your move: ");
        int boardSpace = scanner.nextInt();
        boolean validMove = board.makeMove(currentPlayer, boardSpace);

        while (!validMove) {
            System.out.print("Player " + currentPlayer + ", please make a valid move: ");
            boardSpace = scanner.nextInt();
            validMove = board.makeMove(currentPlayer, boardSpace);
        }
    }

    private static boolean gameStatus() {
        int gameStatus = board.gameStatus(currentPlayer);
        if (gameStatus == 0) {
            System.out.println("Tie.");
            return false;
        } else if (gameStatus == 1) {
            System.out.println("Player " + currentPlayer + " wins!");
            return false;
        }
        return true;
    }
}