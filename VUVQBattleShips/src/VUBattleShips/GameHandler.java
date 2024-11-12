package VUBattleShips;

import java.awt.Color;

public class GameHandler {
    private final OceanGrid enemyGrid;
    private int playerHits = 0;

    public GameHandler(OceanGrid enemyGrid) {
        this.enemyGrid = enemyGrid;
    }

    public void handlePlayerMove(int row, int col) {
        OceanGrid.CellStatus status = enemyGrid.getCellStatus(row, col);
        if (status == OceanGrid.CellStatus.SHIP) {
            playerHits++;
            enemyGrid.updateCell(row, col, "X", Color.RED); // "X" for hit
            checkGameOver();
        } else if (status == OceanGrid.CellStatus.EMPTY) {
            enemyGrid.updateCell(row, col, "S", Color.BLUE); // "S" for miss
        }
    }

    private void checkGameOver() {
        if (playerHits == 17) { // Assuming there are 17 ships in total
            System.out.println("You win! All ships sunk.");
        }
    }
}