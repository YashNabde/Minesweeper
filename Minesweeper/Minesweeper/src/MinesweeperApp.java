import java.awt.*;
import javax.swing.*;

public class MinesweeperApp {
    JFrame frame;
    CardLayout cardLayout;
    JPanel mainPanel;

    Minesweeper gamePanel;
    String currentDifficulty = "Easy"; // Default

    public MinesweeperApp() {
        frame = new JFrame("Minesweeper");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createStartScreen(), "Start");
        mainPanel.add(createGameOverScreen(), "GameOver");

        frame.add(mainPanel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        showStart(); // Show start screen first
    }

    private JPanel createStartScreen() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Welcome to Minesweeper", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        // Difficulty selector
        String[] difficulties = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyBox = new JComboBox<>(difficulties);
        difficultyBox.setFont(new Font("Arial", Font.PLAIN, 16));
        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Select Difficulty: "));
        centerPanel.add(difficultyBox);
        panel.add(centerPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            currentDifficulty = (String) difficultyBox.getSelectedItem();
            int size, mines;

            switch (currentDifficulty) {
                case "Medium":
                    size = 10;
                    mines = 15;
                    break;
                case "Hard":
                    size = 12;
                    mines = 25;
                    break;
                case "Easy":
                default:
                    size = 8;
                    mines = 5;
                    break;
            }

            gamePanel = new Minesweeper(this, size, mines);
            mainPanel.add(gamePanel.getPanel(), "Game");
            showGame();
        });

        panel.add(startButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createGameOverScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Game Over!!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> {
            showStart(); // Back to start screen
        });

        panel.add(restartButton, BorderLayout.SOUTH);
        return panel;
    }

    public void showStart() {
        cardLayout.show(mainPanel, "Start");
    }

    public void showGame() {
        cardLayout.show(mainPanel, "Game");
    }

    public void showGameOver() {
        cardLayout.show(mainPanel, "GameOver");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MinesweeperApp::new);
    }
}
