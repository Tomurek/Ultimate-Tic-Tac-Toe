import javax.swing.*;
import java.awt.*;

public class DifficultyPanel extends JPanel {
    public JButton[] diffBtns;
    public DifficultyPanel(String[] levels, String[] descs, Color[] bgs) {
        setLayout(new GridLayout(1, 3, 20, 0));
        setOpaque(false);
        diffBtns = new JButton[3];
        for (int i = 0; i < 3; i++) {
            JPanel p = new JPanel(new BorderLayout());
            p.setBackground(bgs[i]);
            JLabel l = new JLabel(levels[i], SwingConstants.CENTER);
            l.setFont(new Font("Arial", Font.BOLD, 36));
            l.setForeground(Colors.LABEL_TEXT);
            JLabel d = new JLabel(descs[i], SwingConstants.CENTER);
            d.setFont(new Font("Arial", Font.BOLD, 12));
            d.setForeground(Colors.TEXT_GRAY);
            p.add(l, BorderLayout.CENTER);
            p.add(d, BorderLayout.SOUTH);
            JButton btn = new JButton();
            btn.setLayout(new BorderLayout());
            btn.setBackground(bgs[i]);
            btn.setFocusPainted(false);
            btn.add(p, BorderLayout.CENTER);
            StartScreen.addHoverEffect(btn, p, bgs[i], Colors.BOARD_INACTIVE);
            diffBtns[i] = btn;
            add(btn);
        }
    }

    //  metoda tworzaca panel z domyślnymi poziomami
    public static DifficultyPanel createDefault() {
        String[] levels = {"Łatwy", "Średni", "Trudny"};
        String[] descs = {"Bot gra losowo", "Bot unika głupich ruchów", "Bot gra najlepiej"};
        Color[] bgs = {Colors.BLACK, Colors.BLACK, Colors.BLACK};
        return new DifficultyPanel(levels, descs, bgs);
    }
}