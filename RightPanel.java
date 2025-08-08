import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    public RightPanel(DefaultListModel<String> historyModel, JList<String> historyList, Runnable onMenu) {
        setPreferredSize(new Dimension(180, 0));
        setBackground(Colors.HISTORY_BG);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JPanel menuPanel = new MenuPanel(onMenu);
        topPanel.add(menuPanel, BorderLayout.NORTH);
        JLabel histLabel = new JLabel("Historia gry");
        histLabel.setHorizontalAlignment(SwingConstants.CENTER);
        histLabel.setForeground(Colors.LABEL_TEXT);
        topPanel.add(histLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(historyList);
        scroll.getViewport().setBackground(Colors.HISTORY_BG);
        historyList.setBackground(Colors.HISTORY_BG);
        historyList.setForeground(Colors.LABEL_TEXT);
        add(scroll, BorderLayout.CENTER);
    }
}
