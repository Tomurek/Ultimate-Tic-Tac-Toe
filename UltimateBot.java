import java.util.*;

public class UltimateBot {
    private final Random random = new Random();
    private final char botSymbol;
    private final char opponentSymbol;

    public UltimateBot(char botSymbol) {
        this.botSymbol = botSymbol;
        this.opponentSymbol = (botSymbol == 'X') ? 'O' : 'X';
    }

    // Zwraca [boardIdx, cellIdx] dla ruchu bota (poziom easy)
    public int[] getMove(SmallBoardPanel[] boards, int activeBoard) {
        int boardIdx = activeBoard;
        // Jeśli aktywna plansza jest zakończona, wybierz losową dostępną planszę
        if (boards[activeBoard].isFinished()) {
            List<Integer> availableBoards = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (!boards[i].isFinished()) availableBoards.add(i);
            }
            if (availableBoards.isEmpty()) return null; // brak możliwych ruchów
            boardIdx = availableBoards.get(random.nextInt(availableBoards.size()));
        }
        // Wybierz losowe wolne pole na wybranej planszy
        List<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (boards[boardIdx].isCellEmpty(i)) emptyCells.add(i);
        }
        if (emptyCells.isEmpty()) return null; // nie powinno się zdarzyć
        int cellIdx = emptyCells.get(random.nextInt(emptyCells.size()));
        return new int[]{boardIdx, cellIdx};
    }

    // Wykonuje ruch bota na planszy (wywoływane z UltimateTicTacToe)
    // selectBoardMode - czy bot może wybrać dowolną planszę
    public static void makeBotMove(UltimateBot bot, SmallBoardPanel[] boards, int activeBoard, boolean selectBoardMode) {
        if (bot == null) return;
        int active;
        if (selectBoardMode) {
            // Bot wybiera dowolną dostępną planszę
            ArrayList<Integer> availableBoards = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                if (!boards[i].isFinished()) availableBoards.add(i);
            }
            if (availableBoards.isEmpty()) return;
            active = availableBoards.get(0); // getMove losuje planszę jeśli trzeba
        } else {
            active = activeBoard;
        }
        int[] move = bot.getMove(boards, active);
        if (move != null && !boards[move[0]].isFinished()) {
            boards[move[0]].makeMoveByKeyboard(move[1]);
        }
    }
}
