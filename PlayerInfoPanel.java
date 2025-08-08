import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {
    private JLabel playerTextLabel;
    private JLabel playerSymbolLabel;
    private boolean singlePlayer = false;
    private char botSymbol = 'O';
    private Timer botAnimTimer;
    private int dotCount = 0;

    public PlayerInfoPanel(char currentPlayer) {
        setBackground(Colors.BACKGROUND);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        playerTextLabel = new JLabel("Ruch gracza:");
        playerTextLabel.setFont(new Font("Arial", Font.BOLD, 22));
        playerTextLabel.setForeground(Colors.LABEL_TEXT);
        playerSymbolLabel = new JLabel(String.valueOf(currentPlayer));
        playerSymbolLabel.setFont(new Font("Arial", Font.BOLD, 22));
        playerSymbolLabel.setForeground(currentPlayer == 'X' ? Colors.X_COLOR : Colors.O_COLOR);
        add(playerTextLabel);
        add(playerSymbolLabel);
    }

    // Ustaw tryb singleplayer i symbol bota
    public void setBotInfo(boolean singlePlayer, char botSymbol) {
        this.singlePlayer = singlePlayer;
        this.botSymbol = botSymbol;
    }

    // aktualizacja symbolu gracza
    public void updatePlayer(char player) {
        playerSymbolLabel.setText(String.valueOf(player));
        playerSymbolLabel.setForeground(player == 'X' ? Colors.X_COLOR : Colors.O_COLOR);
        if (singlePlayer && player == botSymbol) {
            playerTextLabel.setText("Ruch bota:");
        } else {
            playerTextLabel.setText("Ruch gracza:");
        }
        stopBotAnimation();
    }

    // Wywołaj, gdy bot "myśli"
    public void startBotAnimation() {
        stopBotAnimation();
        dotCount = 0;
        botAnimTimer = new Timer(500, e -> {
            dotCount = (dotCount + 1) % 4;
            String dots = ".".repeat(dotCount);
            playerTextLabel.setText("Ruch bota" + dots);
        });
        botAnimTimer.start();
    }

    // Wywołaj, gdy bot skończy ruch
    public void stopBotAnimation() {
        if (botAnimTimer != null) {
            botAnimTimer.stop();
            botAnimTimer = null;
        }
    }
}
