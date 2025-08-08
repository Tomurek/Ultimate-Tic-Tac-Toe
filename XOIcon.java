import javax.swing.*;
import java.awt.*;

public class XOIcon {
    // --- Ikony użytkownika i multi-user do ekranu startowego ---

    // Ikony użytkownika i multi-user jako emoji z odpowiednią czcionką
    public static Icon getUserIcon() {
        JLabel label = new JLabel("\uD83D\uDC64", SwingConstants.CENTER); // 👤
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setSize(150, 150);
        return new ComponentIcon(label, 150, 150);
    }
    public static Icon getMultiUserIcon() {
        JLabel label = new JLabel("\uD83D\uDC65", SwingConstants.CENTER); // 👥
        label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setSize(150, 150);
        return new ComponentIcon(label, 150, 150);
    }
    // Pomocnicza klasa do opakowania JLabel jako Icon
    static class ComponentIcon implements Icon {
        private final JComponent comp;
        private final int width, height;
        public ComponentIcon(JComponent comp, int width, int height) {
            this.comp = comp;
            this.width = width;
            this.height = height;
        }
        public int getIconWidth() { return width; }
        public int getIconHeight() { return height; }
        public void paintIcon(Component c, Graphics g, int x, int y) {
            comp.setBounds(x, y, width, height);
            comp.paint(g.create(x, y, width, height));
        }
    }

    // Ikona X lub O jako znak, nie rysunek
    public static Icon getCharIcon(char symbol, Color color) {
        JLabel label = new JLabel(String.valueOf(symbol), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 48));
        label.setForeground(color);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setSize(60, 60);
        return new ComponentIcon(label, 60, 60);
    }
}
