public class Board {
    private static final int BOARD_SIZE = 3;
    private static String[] symbols = {" ", "O", "X"};

    private int size;
    private int[] board;

    public Board() {
        size = BOARD_SIZE;
        board = new int[(int) Math.pow(size, 2)];
    }

    public void displayBoard() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size - 1; column++) {
                System.out.print(getSymbolFromIndex(row * size + column) + " | ");
            }
            System.out.print(getSymbolFromIndex(row * size + size - 1));
            System.out.println();
        }
    }

    public boolean makeMove(int player, int boardSpace) {
        if ((boardSpace >= 0 && boardSpace < board.length) && board[boardSpace] == 0) {
            board[boardSpace] = player;
            return true;
        } else {
            return false;
        }
    }

    public int gameStatus(int player) {
        if (isTied()) {
            return 0; // tied
        } else if (horizontalWin(player) || verticalWin(player) || rightDiagonalWin(player)
                || leftDiagonalWin(player)) {
            return 1; // win
        } else {
            return -1; // playing
        }
    }

    public void wipe() {
        board = new int[(int) Math.pow(size, 2)];
    }

    private boolean horizontalWin(int player) {
        for (int row = 0; row < size; row++) {
            int count = 0;
            for (int column = 0; column < size; column++) {
                if (player == board[row * size + column]) {
                    count++;
                } else {
                    count = 0;
                }
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean verticalWin(int player) {
        for (int column = 0; column < size; column++) {
            int count = 0;
            for (int row = 0; row < size; row++) {
                if (player == board[row * size + column]) {
                    count++;
                } else {
                    count = 0;
                }
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean rightDiagonalWin(int player) {
        return (player == board[0] && board[4] == board[8] && board[0] == board[8]);
    }

    private boolean leftDiagonalWin(int player) { // got kinda lazy here lol
        return (player == board[2] && board[4] == board[6] && board[2] == board[6]);
    }

    private boolean isTied() {
        for (int space : board) {
            if (space == 0) {
                return false;
            }
        }
        return true;
    }

    private String getSymbolFromIndex(int index) {
        if (board[index] == 0) {
            return Integer.toString(index);
        }
        return symbols[board[index]];
    }
}
