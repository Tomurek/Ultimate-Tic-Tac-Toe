import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
    public interface StartListener {
        void onStart(boolean vsComputer, String whoStarts, String difficulty);
    }

    private ModePanel modePanel;
    private DifficultyPanel diffPanel;
    private WhoStartsPanel whoPanel;
    private StartListener listener;
    private String selectedDifficulty = "easy";
    private String selectedWho = "X";
    private boolean vsComputer = true;

    public StartScreen(StartListener listener) {
        this.listener = listener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        addTitle();
        addModePanel();
        diffPanel = DifficultyPanel.createDefault();
        diffPanel.setVisible(false);
        add(diffPanel);
        addWhoStartsPanel();
        setupActions();
    }

    // Dodaje tytuł do panelu startowego
    private void addTitle() {
        JLabel title = new JLabel("Ultimate Tic Tac Toe");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Colors.LABEL_TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createRigidArea(new Dimension(0, 30)));
    }

    // Dodaje panel wyboru trybu gry (single/multi)
    private void addModePanel() {
        modePanel = new ModePanel(XOIcon.getUserIcon(), XOIcon.getMultiUserIcon());
        add(modePanel);
        add(Box.createRigidArea(new Dimension(0, 30)));
    }
    // Dodaje panel wyboru, kto zaczyna grę
    private void addWhoStartsPanel() {
        whoPanel = WhoStartsPanel.createDefault(listener);
        whoPanel.setVisible(false);
        add(whoPanel);
    }

    // Ustawia akcje dla przycisków w panelach
    private void setupActions() {
        modePanel.singleBtn.addActionListener(e -> {
            vsComputer = true;
            diffPanel.setVisible(true);
            whoPanel.setVisible(false);
            // Ustaw aktywny kolor dla singleBtn, resetuj multiBtn
            modePanel.singleBtn.setBackground(Colors.BOARD_INACTIVE);
            modePanel.multiBtn.setBackground(Colors.BLACK);
            // Odśwież hover: wymuś repaint, by hover nie zostawał
            modePanel.singleBtn.getModel().setRollover(false);
            modePanel.multiBtn.getModel().setRollover(false);
        });
        modePanel.multiBtn.addActionListener(e -> {
            vsComputer = false;
            diffPanel.setVisible(false);
            whoPanel.setVisible(true);
            // Ustaw aktywny kolor dla multiBtn, resetuj singleBtn
            modePanel.singleBtn.setBackground(Colors.BLACK);
            modePanel.multiBtn.setBackground(Colors.BOARD_INACTIVE);
            // Odśwież hover: wymuś repaint, by hover nie zostawał
            modePanel.singleBtn.getModel().setRollover(false);
            modePanel.multiBtn.getModel().setRollover(false);
        });
        for (int i = 0; i < diffPanel.diffBtns.length; i++) {
            final int idx = i;
            diffPanel.diffBtns[i].addActionListener(ev -> {
                selectedDifficulty = new String[]{"easy","medium","hard"}[idx];
                diffPanel.setVisible(false);
                whoPanel.setVisible(true);
            });
        }
        for (int i = 0; i < whoPanel.whoBtns.length; i++) {
            final int idx = i;
            whoPanel.whoBtns[i].addActionListener(ev -> {
                selectedWho = new String[]{"X","O"}[idx];
                listener.onStart(vsComputer, selectedWho, selectedDifficulty);
            });
        }
    }

    // Efekt podświetlenia przycisków dla wszystkich paneli startowych
    
    public static void addHoverEffect(JButton btn, Color normal, Color hover) {
        addHoverEffect(btn, null, normal, hover, null);
    }
    public static void addHoverEffect(JButton btn, JPanel centerPanel, Color normal, Color hover) {
        addHoverEffect(btn, centerPanel, normal, hover, null);
    }
    public static void addHoverEffect(JButton btn, JPanel centerPanel, Color normal, Color hover, java.util.function.Supplier<Boolean> isActive) {
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (isActive == null || !isActive.get()) {
                    btn.setBackground(hover);
                    if (centerPanel != null) centerPanel.setBackground(hover);
                }
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (isActive == null || !isActive.get()) {
                    btn.setBackground(normal);
                    if (centerPanel != null) centerPanel.setBackground(normal);
                }
            }
        });
    }
}
