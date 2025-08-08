import javax.swing.*;
import java.awt.*;

public class WinnerDialog {
    public static void show(Component parent, char winner) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent) instanceof Frame ? (Frame)SwingUtilities.getWindowAncestor(parent) : null, "Koniec gry", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(parent);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                Color bg = (winner == 'X') ? Colors.X_COLOR : Colors.O_COLOR;
                g2.setColor(bg);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setFont(new Font("Arial", Font.BOLD, 180));
                g2.setColor(Colors.LABEL_TEXT);
                String s = String.valueOf(winner);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(s)) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - 40;
                g2.drawString(s, x, y);
                g2.dispose();
            }
        };
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel((winner == 'X' ? "Gracz X" : "Gracz O") + " wygrał całą grę!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setForeground(Colors.LABEL_TEXT);
        label.setOpaque(false);
        panel.add(label, BorderLayout.SOUTH);
        dialog.setContentPane(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
}
