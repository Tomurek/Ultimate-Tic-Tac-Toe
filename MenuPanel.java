import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(Runnable onMenu) {
        setOpaque(false);
        JButton menuBtn = new JButton("MENU");
        menuBtn.setPreferredSize(new Dimension(80, 40));
        menuBtn.setFont(new Font("Arial", Font.BOLD, 15));
        menuBtn.setBackground(Colors.BLACK);
        menuBtn.setForeground(Colors.LABEL_TEXT);
        menuBtn.setFocusPainted(false);
        menuBtn.setContentAreaFilled(true);
        menuBtn.setOpaque(true);
        menuBtn.setToolTipText("Powrót do menu głównego");
        menuBtn.addActionListener(e -> onMenu.run());
        add(menuBtn);
    }
}
