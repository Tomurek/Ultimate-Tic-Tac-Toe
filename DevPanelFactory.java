import javax.swing.*;
import java.awt.*;

public class DevPanelFactory {
    public static JPanel create(UltimateTicTacToe parent, String whoStarts) {
        JPanel devPanel = new JPanel();
        devPanel.setOpaque(true);
        devPanel.setBackground(Colors.HISTORY_BG);
        devPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        JButton winBoardX = new JButton("Win Board X");
        winBoardX.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(parent, "Numer planszy (1-9):");
            try {
                int idx = Integer.parseInt(input) - 1;
                if (idx >= 0 && idx < 9 && !parent.getBoard(idx).isFinished()) {
                    parent.getBoard(idx).forceWin('X');
                }
            } catch (Exception ignored) {}
        });
        JButton winBoardO = new JButton("Win Board O");
        winBoardO.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(parent, "Numer planszy (1-9):");
            try {
                int idx = Integer.parseInt(input) - 1;
                if (idx >= 0 && idx < 9 && !parent.getBoard(idx).isFinished()) {
                    parent.getBoard(idx).forceWin('O');
                }
            } catch (Exception ignored) {}
        });
        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> {
            parent.showGameBoard(whoStarts);
        });
        JButton winGameX = new JButton("Win Game X");
        winGameX.addActionListener(e -> {
            for (int i = 0; i < 3; i++) parent.getBoard(i).forceWin('X');
            parent.onSmallBoardMove(0, 0);
        });
        JButton winGameO = new JButton("Win Game O");
        winGameO.addActionListener(e -> {
            for (int i = 3; i < 6; i++) parent.getBoard(i).forceWin('O');
            parent.onSmallBoardMove(3, 0);
        });
        devPanel.add(winBoardX);
        devPanel.add(winBoardO);
        devPanel.add(winGameX);
        devPanel.add(winGameO);
        devPanel.add(resetBtn);
        return devPanel;
    }
}
