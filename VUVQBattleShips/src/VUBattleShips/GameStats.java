package VUBattleShips;

public class GameStats {
    private int playerHits;
    private int playerMisses;
    private int enemyHits;
    private int enemyMisses;

    private final int totalShips;

    public GameStats(int totalShips) {
        this.totalShips = totalShips;
        this.playerHits = 0;
        this.playerMisses = 0;
        this.enemyHits = 0;
        this.enemyMisses = 0;
    }

    public void recordPlayerHit() {
        playerHits++;
    }

    public void recordPlayerMiss() {
        playerMisses++;
    }

    public void recordEnemyHit() {
        enemyHits++;
    }

    public void recordEnemyMiss() {
        enemyMisses++;
    }

    public void printStats() {
        System.out.println("Player Hits: " + playerHits + ", Player Misses: " + playerMisses);
        System.out.println("Enemy Hits: " + enemyHits + ", Enemy Misses: " + enemyMisses);
        System.out.println("Ships Remaining: " + (totalShips - playerHits));
    }

    public boolean isGameOver() {
        return playerHits == totalShips;
    }
}