import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UltimateTicTacToe extends JFrame {
    private JPanel mainPanel;
    private DefaultListModel<String> historyModel = new DefaultListModel<>();
    private JList<String> historyList = new JList<>(historyModel);
    private PlayerInfoPanel playerInfoPanel;
    private UltimateBot bot = null;
    private boolean singlePlayer = false;
    private String botDifficulty = "easy";
    private char botSymbol = 'O';

    public UltimateTicTacToe() {
        setTitle("Ultimate Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Colors.BACKGROUND);
        setContentPane(mainPanel);
        showStartScreen();
    }

    // Pokazuje ekran startowy
    private void showStartScreen() {
        historyModel.clear();
        StartScreen startScreen = new StartScreen((vsComputer, whoStarts, difficulty) -> {
            singlePlayer = vsComputer;
            botDifficulty = difficulty;
            botSymbol = (whoStarts.equals("X")) ? 'O' : 'X';
            bot = singlePlayer ? new UltimateBot(botSymbol) : null;
            showGameBoard(whoStarts);
        });
        mainPanel.removeAll();
        mainPanel.add(startScreen, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private SmallBoardPanel[] boards = new SmallBoardPanel[9];
    private int activeBoard = 0; // domyślnie pierwsza plansza
    private boolean selectBoardMode = false;

    public SmallBoardPanel getBoard(int idx) {
        return boards[idx];
    }

    // Ustawia planszę aktywną 
    public void showGameBoard(String whoStarts) {
        historyModel.clear(); // czyszczenie historii przy resecie gry
        if (whoStarts != null) {
            SmallBoardPanel.setCurrentPlayer(whoStarts.charAt(0));
        }
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 4, 4));
        boardPanel.setBackground(Colors.BACKGROUND);
        SmallBoardPanel.setHistoryModel(historyModel);
        for (int i = 0; i < 9; i++) {
            boards[i] = new SmallBoardPanel(i, this::onSmallBoardMove, mainPanel); // przekazujemy mainPanel
            boardPanel.add(boards[i]);
        }
        Runnable menuAction = () -> {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Czy na pewno chcesz zakończyć grę i wrócić do menu?",
                "Potwierdzenie",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (result == JOptionPane.YES_OPTION) {
                showStartScreen();
            }
        };
        JPanel rightPanel = new RightPanel(historyModel, historyList, menuAction);
        playerInfoPanel = new PlayerInfoPanel(SmallBoardPanel.getCurrentPlayer());
        playerInfoPanel.setBotInfo(singlePlayer, botSymbol);
        JPanel devPanel = DevPanelFactory.create(this, whoStarts);

        mainPanel.removeAll();
        mainPanel.add(playerInfoPanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        // tu wylacz devpanel \/
        mainPanel.add(devPanel, BorderLayout.SOUTH);
        mainPanel.revalidate();
        mainPanel.repaint();
        setupKeyboardShortcuts();

        // --- RUCH BOTA NA START ---
        if (singlePlayer && SmallBoardPanel.getCurrentPlayer() == botSymbol) {
            playerInfoPanel.startBotAnimation();
            new javax.swing.Timer(900, e -> {
                playerInfoPanel.stopBotAnimation();
                UltimateBot.makeBotMove(bot, boards, activeBoard, selectBoardMode);
                ((javax.swing.Timer)e.getSource()).stop();
            }).start();
        }
    }
    // Obsługuje ruch na planszy
    public void onSmallBoardMove(int boardIdx, int cellIdx) {
        char winner = checkGameWinner();
        if (winner != '\0') {
            for (SmallBoardPanel board : boards) {
                board.setEnabledAll(false);
            }
            WinnerDialog.show(this, winner);
            mainPanel.requestFocusInWindow(); // focus po zakończeniu gry
            return;
        }
        // Sprawdź, czy plansza docelowa jest zakończona
        if (boards[cellIdx].isFinished()) {
            // Odblokuj wszystkie dostępne plansze
            for (int i = 0; i < 9; i++) {
                boards[i].setEnabledAll(!boards[i].isFinished());
            }
            selectBoardMode = true;
        } else {
            // Zablokuj wszystkie plansze poza docelową
            for (int i = 0; i < 9; i++) {
                boards[i].setEnabledAll(i == cellIdx && !boards[i].isFinished());
            }
            activeBoard = cellIdx;
            selectBoardMode = false;
        }
        updateCurrentPlayerLabel();
        mainPanel.requestFocusInWindow(); // focus po każdym ruchu
        
        // --- RUCH BOTA ---
        if (singlePlayer && SmallBoardPanel.getCurrentPlayer() == botSymbol) {
            playerInfoPanel.startBotAnimation();
            new javax.swing.Timer(2000, e -> {
                playerInfoPanel.stopBotAnimation();
                UltimateBot.makeBotMove(bot, boards, activeBoard, selectBoardMode);
                ((javax.swing.Timer)e.getSource()).stop();
            }).start();
        }
    }

    // Obsługa klawiatury numerycznej i ESC
    private void setupKeyboardShortcuts() {
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();
        mainPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                int key = e.getKeyCode();
                int num = -1;
                if (key >= java.awt.event.KeyEvent.VK_NUMPAD1 && key <= java.awt.event.KeyEvent.VK_NUMPAD9) {
                    num = key - java.awt.event.KeyEvent.VK_NUMPAD1;
                } else if (key >= java.awt.event.KeyEvent.VK_1 && key <= java.awt.event.KeyEvent.VK_9) {
                    num = key - java.awt.event.KeyEvent.VK_1;
                }
                if (num >= 0 && num < 9) {
                    if (selectBoardMode) {
                        // Wybór planszy
                        if (!boards[num].isFinished()) {
                            activeBoard = num;
                            selectBoardMode = false;
                            // Odblokuj tylko wybraną planszę
                            for (int i = 0; i < 9; i++) {
                                boards[i].setEnabledAll(i == activeBoard && !boards[i].isFinished());
                            }
                            System.out.println("Wybrano planszę " + (num+1));
                        }
                    } else {
                        if (!boards[activeBoard].isFinished()) {
                            boards[activeBoard].makeMoveByKeyboard(num);
                            System.out.println("Wybrano pole " + (num+1) + " na planszy " + (activeBoard+1));
                        }
                    }
                    mainPanel.requestFocusInWindow(); // focus po każdym ruchu/wyborze
                } 
                // else if (key == java.awt.event.KeyEvent.VK_ESCAPE) {
                //     // Powrót do wyboru planszy
                //     selectBoardMode = true;
                //     for (int i = 0; i < 9; i++) {
                //         boards[i].setEnabledAll(!boards[i].isFinished());
                //     }
                //     System.out.println("Tryb wyboru planszy (ESC)");
                //     mainPanel.requestFocusInWindow();
                // }
            }
        });
    }

    // Sprawdza zwycięzcę całej gry (3 plansze w linii)
    private char checkGameWinner() {
        int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] w : wins) {
            if (boards[w[0]].isFinished() && boards[w[0]].getWinner() != ' ' && boards[w[0]].getWinner() == boards[w[1]].getWinner() && boards[w[1]].getWinner() == boards[w[2]].getWinner()) {
                return boards[w[0]].getWinner();
            }
        }
        return '\0';
    }

    // Aktualizuje label z informacją o graczu
    private void updateCurrentPlayerLabel() {
        char player = SmallBoardPanel.getCurrentPlayer();
        if (playerInfoPanel != null) {
            playerInfoPanel.updatePlayer(player);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UltimateTicTacToe().setVisible(true);
        });
    }
}

