import javax.swing.*;
import java.awt.*;

public class WhoStartsPanel extends JPanel {
    public JButton[] whoBtns;
    public WhoStartsPanel(String[] whoNames, String[] whoDescs, Color[] whoBgs) {
        setLayout(new GridLayout(1, 2, 20, 0));
        setOpaque(false);
        whoBtns = new JButton[2];
        for (int i = 0; i < 2; i++) {
            JPanel p = new JPanel(new BorderLayout());
            p.setBackground(whoBgs[i]);
            JLabel l = new JLabel(whoNames[i], SwingConstants.CENTER);
            l.setFont(new Font("Arial", Font.BOLD, 36));
            l.setForeground(Colors.LABEL_TEXT);
            JLabel d = new JLabel(whoDescs[i], SwingConstants.CENTER);
            d.setFont(new Font("Arial", Font.PLAIN, 20));
            d.setForeground(Colors.TEXT_GRAY);
            p.add(l, BorderLayout.CENTER);
            p.add(d, BorderLayout.SOUTH);
            JButton btn = new JButton();
            btn.setLayout(new BorderLayout());
            btn.setBackground(whoBgs[i]);
            btn.setFocusPainted(false);
            btn.add(p, BorderLayout.CENTER);
            StartScreen.addHoverEffect(btn, p, whoBgs[i], Colors.BOARD_INACTIVE);
            whoBtns[i] = btn;
            add(btn);
        }
    }

    // Statyczna metoda do tworzenia panelu wyboru gracza
    public static WhoStartsPanel createDefault(StartScreen.StartListener listener) {
        String[] whoNames = {"X", "O"};
        String[] whoDescs = {"Zaczyna X", "Zaczyna O"};
        Color[] whoBgs = {Colors.BLACK, Colors.BLACK};
        WhoStartsPanel panel = new WhoStartsPanel(whoNames, whoDescs, whoBgs);
        
        return panel;
    }
}
