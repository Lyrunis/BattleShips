package VUBattleShips;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameHandler {
    private final OceanGrid enemyGrid;
    private JFrame gameFrame;
    private int playerHits = 0;
    private int totalShips = 17; // 17 total spaces
    private static int totalHits = 0; 
    private static int totalMisses = 0;
    private static int consecutiveMisses = 0; 
    private static int strikes = 0; 

    // labels in GameLauncher to update UI
    private JLabel missStrikesLabel;
    private JLabel totalHitsMissesLabel;

    // Constructor
    public GameHandler(OceanGrid enemyGrid, JLabel missStrikesLabel, JLabel totalHitsMissesLabel, JFrame gameFrame) {
        this.enemyGrid = enemyGrid;
        this.missStrikesLabel = missStrikesLabel;
        this.totalHitsMissesLabel = totalHitsMissesLabel;
    }

    public void handlePlayerMove(int row, int col) {
        OceanGrid.CellStatus status = enemyGrid.getCellStatus(row, col);
        if (status == OceanGrid.CellStatus.SHIP) {
            playerHits++;
            enemyGrid.updateCell(row, col, "X", Color.RED); // "X" for hit
            updateStats(true);
        } else if (status == OceanGrid.CellStatus.EMPTY) {
            enemyGrid.updateCell(row, col, "M", Color.BLUE); // "M" for miss
            updateStats(false);
        }
        checkGameOver();
    }

    // update stats after each move (hit or miss)
    public void updateStats(boolean isHit) {
        if (isHit) {
            consecutiveMisses = 0; // Reset consecutive misses after a hit
            totalHits++;
        } else {
        	if (consecutiveMisses < 5) {
            consecutiveMisses++;
            totalMisses++;}
            if (consecutiveMisses == 5) { //Increase strike at consecM = 5, reset after
                strikes++;
                consecutiveMisses = 0;
            }
        }

        // Update latest stats
        missStrikesLabel.setText("Misses: " + consecutiveMisses + " | Strikes: " + strikes);
        totalHitsMissesLabel.setText("Total Hits: " + totalHits + " | Total Misses: " + totalMisses);
    }

    private void checkGameOver() {
        if (playerHits == totalShips) { // all ships are hit
        	int result = JOptionPane.showConfirmDialog(null, 
                    "Congratulations! You have sunk all the enemy ships! Would you like to play again?", 
                    "You Win!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    GameLauncher.restartGame(gameFrame); // Restart the game if the user clicks Yes
                } else {
                    System.exit(0); // Exit the game if the user clicks No
                }
        }
        else if (strikes == 3) {
        	 int result = JOptionPane.showConfirmDialog(null, 
                     "You have made 3 strikes and lost the game! Would you like to play again?", 
                     "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                 if (result == JOptionPane.YES_OPTION) {
                     GameLauncher.restartGame(gameFrame); // Restart the game if the user clicks Yes
                 } else {
                     System.exit(0); // Exit the game if the user clicks No
                 }
        }
    }
}