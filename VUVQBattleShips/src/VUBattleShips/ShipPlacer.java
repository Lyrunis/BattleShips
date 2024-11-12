package VUBattleShips;

import java.util.Random;

public class ShipPlacer {
    private final int gridSize = 10;
    private final OceanGrid.CellStatus[][] cellStatuses;

    public ShipPlacer(OceanGrid.CellStatus[][] cellStatuses) {
        this.cellStatuses = cellStatuses;
    }

    // Check if a ship can be placed at a specific position
    public boolean canPlaceShip(int startRow, int startCol, int shipSize, boolean horizontal) {
        if (horizontal) {
            if (startCol + shipSize > gridSize) {
                return false; // Ship would go out of bounds
            }
            for (int i = 0; i < shipSize; i++) {
                if (cellStatuses[startRow][startCol + i] != OceanGrid.CellStatus.EMPTY) {
                    return false; // Collision with another ship
                }
            }
        } else {
            if (startRow + shipSize > gridSize) {
                return false; // Ship would go out of bounds
            }
            for (int i = 0; i < shipSize; i++) {
                if (cellStatuses[startRow + i][startCol] != OceanGrid.CellStatus.EMPTY) {
                    return false; // Collision with another ship
                }
            }
        }
        return true;
    }

    // Place a ship on the grid at the specified location
    public void placeShip(int startRow, int startCol, int shipSize, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < shipSize; i++) {
                cellStatuses[startRow][startCol + i] = OceanGrid.CellStatus.SHIP;
            }
        } else {
            for (int i = 0; i < shipSize; i++) {
                cellStatuses[startRow + i][startCol] = OceanGrid.CellStatus.SHIP;
            }
        }
    }

    // Randomly place ships on the grid
    public void placeShips() {
        Random random = new Random();
        int[] shipSizes = {5, 4, 3, 3, 2}; // Sizes of the ships
        for (int shipSize : shipSizes) {
            boolean placed = false;
            while (!placed) {
                int startRow = random.nextInt(gridSize);
                int startCol = random.nextInt(gridSize);
                boolean horizontal = random.nextBoolean();
                if (canPlaceShip(startRow, startCol, shipSize, horizontal)) {
                    placeShip(startRow, startCol, shipSize, horizontal);
                    placed = true;
                }
            }
        }
    }
}