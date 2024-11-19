package VUBattleShips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLauncher {
    private static final JPanel leftStatPanel = new JPanel();
    private static final JPanel rightStatPanel = new JPanel();

    // Definelabels for displaying stats
    private static JLabel missStrikesLabel = new JLabel("Misses: 0 | Strikes: 0");
    private static JLabel totalHitsMissesLabel = new JLabel("Total Hits: 0 | Total Misses: 0");

    public static void main(String[] args) {
        JFrame frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(668, 636);

        // Ocean grid for the enemy
        OceanGrid enemyGrid = new OceanGrid(null);
        GameHandler gameHandler = new GameHandler(enemyGrid, missStrikesLabel, totalHitsMissesLabel, frame);

        // gameHandler for the grid
        enemyGrid.setGameHandler(gameHandler);

        // place ships for the enemy
        enemyGrid.placeShips();

        // panel for the main grid and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // grid to the panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.Y_AXIS));
        gridPanel.add(new JLabel("Enemy Grid"));
        gridPanel.add(enemyGrid);

        panel.add(gridPanel, BorderLayout.CENTER);

        // button panel for Quit and Restart
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Horizontal layout for buttons

        // Quit
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quit the application
            }
        });

        // Restart
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame(frame); // Restart the game by reinitializing the game window
            }
        });

        // leftStatPanel
        leftStatPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 15));
        leftStatPanel.add(missStrikesLabel);

        // rightStatPanel
        rightStatPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 100, 15));
        rightStatPanel.add(totalHitsMissesLabel);

        // buttonPanel
        buttonPanel.add(leftStatPanel);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(rightStatPanel);

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    // Method to restart the game
    static void restartGame(JFrame frame) {
        if (frame != null) {
            frame.dispose(); // Close the current game window
        }
        main(null); // Re-run the main method to restart the game
    }
}