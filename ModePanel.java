import javax.swing.*;
import java.awt.*;

public class ModePanel extends JPanel {
    public JButton singleBtn;
    public JButton multiBtn;

    public ModePanel(Icon userIcon, Icon multiUserIcon) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setOpaque(false);
        singleBtn = new JButton();
        multiBtn = new JButton();
        singleBtn.setLayout(new BorderLayout());
        multiBtn.setLayout(new BorderLayout());
        singleBtn.setBackground(Colors.BLACK);
        multiBtn.setBackground(Colors.BLACK);
        singleBtn.setFocusPainted(false);
        multiBtn.setFocusPainted(false);
        singleBtn.add(new JLabel(userIcon, SwingConstants.CENTER), BorderLayout.CENTER);
        multiBtn.add(new JLabel(multiUserIcon, SwingConstants.CENTER), BorderLayout.CENTER);
        JLabel singleLabel = new JLabel("Singleplayer", SwingConstants.CENTER);
        singleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        singleLabel.setForeground(Colors.LABEL_TEXT);
        JLabel singleDesc = new JLabel("Gra z botem", SwingConstants.CENTER);
        singleDesc.setForeground(Colors.TEXT_GRAY);
        JLabel multiLabel = new JLabel("Multiplayer", SwingConstants.CENTER);
        multiLabel.setFont(new Font("Arial", Font.BOLD, 24));
        multiLabel.setForeground(Colors.LABEL_TEXT);
        JLabel multiDesc = new JLabel("Gra z drugim graczem", SwingConstants.CENTER);
        multiDesc.setForeground(Colors.TEXT_GRAY);
        JPanel singleText = new JPanel(new GridLayout(2,1));
        singleText.setOpaque(false);
        singleText.add(singleLabel);
        singleText.add(singleDesc);
        JPanel multiText = new JPanel(new GridLayout(2,1));
        multiText.setOpaque(false);
        multiText.add(multiLabel);
        multiText.add(multiDesc);
        singleBtn.add(singleText, BorderLayout.SOUTH);
        multiBtn.add(multiText, BorderLayout.SOUTH);
        add(singleBtn);
        add(multiBtn);

        // Hover effect z obsługą aktywnego wyboru
        StartScreen.addHoverEffect(singleBtn, null, Colors.BLACK, Colors.BLACK, () -> singleBtn.getBackground().equals(Colors.BOARD_INACTIVE));
        StartScreen.addHoverEffect(multiBtn, null, Colors.BLACK, Colors.BLACK, () -> multiBtn.getBackground().equals(Colors.BOARD_INACTIVE));
    }
}
