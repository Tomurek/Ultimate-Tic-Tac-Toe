import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SmallBoardPanel extends JPanel {
    private JButton[] buttons = new JButton[9];
    private char[] state = new char[9]; // 'X', 'O' lub '\0'
    private static char currentPlayer = 'X';
    private static DefaultListModel<String> historyModel;
    private int boardIndex;
    private java.util.function.BiConsumer<Integer, Integer> moveCallback;
    private JButton winnerButton = null;
    private List<String> localHistory = new ArrayList<>();
    private char winner = '\0'; // 'X', 'O', ' ' (remis) lub '\0' (gra trwa)

    public static void setHistoryModel(DefaultListModel<String> model) {
        historyModel = model;
    }

    // Konstruktor planszy
    public SmallBoardPanel(int boardIndex, java.util.function.BiConsumer<Integer, Integer> moveCallback, JPanel mainPanelRef) {
        this.boardIndex = boardIndex;
        this.moveCallback = moveCallback;
        setLayout(new GridLayout(3, 3, 0, 0));
        setBackground(Colors.BOARD_INACTIVE);
        for (int i = 0; i < 9; i++) {
            final int idx = i;
            JButton btn = new JButton();
            btn.setBackground(Colors.BUTTON_BG);
            btn.setFont(new Font("Arial", Font.BOLD, 28));
            btn.setFocusable(false);
            btn.addActionListener(e -> {
                handleMove(idx);
                if (mainPanelRef != null) mainPanelRef.requestFocusInWindow();
            });
            btn.setFocusPainted(false);
            buttons[i] = btn;
            add(btn);
        }
        setEnabledAll(true);
    }

    // Ustawia przyciski jako aktywne lub nieaktywne
    public void setEnabledAll(boolean enabled) {
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];
            btn.setEnabled(enabled && winnerButton == null);
            btn.setBackground(enabled ? Colors.BUTTON_BG_ACTIVE : Colors.BUTTON_BG);
        }
        setOpaque(true);
        setBackground(enabled ? Colors.BOARD_ACTIVE : Colors.BOARD_INACTIVE);
    }

    //
    private void handleMove(int idx) {
        if (state[idx] != '\0' || winnerButton != null || !buttons[idx].isEnabled()) return;
        state[idx] = currentPlayer;
        if (currentPlayer == 'X') {
            buttons[idx].setIcon(XOIcon.getCharIcon('X', Colors.X_COLOR));
        } else {
            buttons[idx].setIcon(XOIcon.getCharIcon('O', Colors.O_COLOR));
        }
        buttons[idx].setText("");
        String hist = "Gracz " + currentPlayer + ": plansza " + (boardIndex+1) + ", pole " + (idx+1);
        if (historyModel != null) {
            historyModel.addElement(hist);
        }
        localHistory.add(hist);
        if (checkWinner()) {
            showWinner(state[idx]);
        } else if (isFull()) {
            showWinner(' ');
        }
        // Gracz zmienia się ZAWSZE po ruchu
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        if (moveCallback != null) moveCallback.accept(boardIndex, idx);
    }

    private boolean checkWinner() {
        int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] w : wins) {
            if (state[w[0]] != '\0' && state[w[0]] == state[w[1]] && state[w[1]] == state[w[2]]) return true;
        }
        return false;
    }

    private boolean isFull() {
        for (char c : state) if (c == '\0') return false;
        return true;
    }

    // Pokazuje zwycięzcę planszy
    private void showWinner(char winner) {
        this.winner = winner;
        removeAll();
        winnerButton = new JButton();
        winnerButton.setFont(new Font("Arial", Font.BOLD, 48));
        winnerButton.setBackground(Colors.BOARD_WINNER);
        if (winner == 'X') {
            winnerButton.setIcon(XOIcon.getCharIcon('X', Colors.X_COLOR));
        } else if (winner == 'O') {
            winnerButton.setIcon(XOIcon.getCharIcon('O', Colors.O_COLOR));
        } else {
            winnerButton.setText(" ");
        }
        winnerButton.addActionListener(e -> {
            showLocalHistory();
            winnerButton.setEnabled(false);
        });
        setLayout(new BorderLayout());
        add(winnerButton, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showLocalHistory() {
        StringBuilder sb = new StringBuilder();
        for (String s : localHistory) sb.append(s).append("\n");
        JOptionPane.showMessageDialog(this, sb.length() > 0 ? sb.toString() : "Brak ruchów", "Historia planszy " + (boardIndex+1), JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean isFinished() {
        return winnerButton != null;
    }

    public void makeMoveByKeyboard(int idx) {
        if (winnerButton == null && idx >= 0 && idx < 9 && buttons[idx].isEnabled() && state[idx] == '\0') {
            handleMove(idx);
            // Przywróć focus na główny panel po ruchu z klawiatury
            // (mainPanelRef.requestFocusInWindow() wywołuje UltimateTicTacToe)
        }
    }

    public void forceWin(char who) {
        for (int i = 0; i < 9; i++) {
            state[i] = who;
            buttons[i].setIcon(XOIcon.getCharIcon(who, who == 'X' ? Colors.X_COLOR : Colors.O_COLOR));
            buttons[i].setText("");
        }
        showWinner(who);
    }

    public static char getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(char player) {
        currentPlayer = player;
    }

    public char getWinner() {
        return winner;
    }

    public boolean isCellEmpty(int idx) {
        return idx >= 0 && idx < 9 && state[idx] == '\0';
    }
}
