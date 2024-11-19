package VUBattleShips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OceanGrid extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int gridSize = 10;
    private final JButton[][] cellButtons = new JButton[gridSize][gridSize];
    private GameHandler gameHandler;
    private final CellStatus[][] cellStatuses = new CellStatus[gridSize][gridSize]; // Track cell status

    public enum CellStatus {
        EMPTY, MISS, HIT, SHIP
    }

    public OceanGrid(GameStats gameStats) {
        setLayout(new GridLayout(gridSize, gridSize));
        setupGrid();
        gameHandler = new GameHandler(this, null, null, null); // Pass gameStats to GameHandler
    }

    // Setter method to set the GameHandler instance later
    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    private void setupGrid() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JButton gridButton = new JButton(""); // Empty by default
                gridButton.setBackground(Color.CYAN); // Default background color
                gridButton.addActionListener(new CellClickListener(row, col));
                cellButtons[row][col] = gridButton;
                cellStatuses[row][col] = CellStatus.EMPTY; // Initially empty
                add(gridButton);
            }
        }
    }

    // status of a cell (miss, hit, ship)
    public void updateCell(int row, int col, String symbol, Color color) {
        JButton cellButton = cellButtons[row][col];
        cellButton.setText(symbol);      // Set text ("X" for hit, "M" for miss)
        cellButton.setBackground(color); // Set the background color (red for hit, blue for miss)
        cellButton.setEnabled(false);    // Disable button after move
    }

    // Place ships on the grid for the enemy
    public void placeShips() {
        ShipPlacer shipPlacer = new ShipPlacer(cellStatuses);
        shipPlacer.placeShips();
    }

    public CellStatus getCellStatus(int row, int col) {
        return cellStatuses[row][col];
    }

    private class CellClickListener implements ActionListener {
        private final int row;
        private final int col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }
       
        @Override
        public void actionPerformed(ActionEvent e) {
            gameHandler.handlePlayerMove(row, col); // Handle player move and update stats
        }
    }

}
