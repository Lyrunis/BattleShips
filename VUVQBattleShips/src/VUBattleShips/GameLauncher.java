package VUBattleShips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLauncher {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Battleship Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        // Create the ocean grid for the enemy
        OceanGrid enemyGrid = new OceanGrid(); // No need to pass GameHandler yet
        GameHandler gameHandler = new GameHandler(enemyGrid); // Now create GameHandler

        // Set the gameHandler for the grid
        enemyGrid.setGameHandler(gameHandler);

        // Now place ships for the enemy
        enemyGrid.placeShips();

        // Create the panel for the main grid and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());  // Using BorderLayout for better control

        // Add the grid to the panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.Y_AXIS));
        gridPanel.add(new JLabel("Enemy Grid"));
        gridPanel.add(enemyGrid);

        panel.add(gridPanel, BorderLayout.CENTER);

        // Create the button panel for Quit and Restart
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Horizontal layout for buttons

        // Quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quit the application
            }
        });

        // Restart button
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame(frame); // Restart the game by reinitializing the game window
            }
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        // Add the panel to the frame and display it
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to restart the game by recreating the game window
    private static void restartGame(JFrame frame) {
        frame.dispose(); // Close the current game window
        main(null); // Re-run the main method to restart the game
    }
}