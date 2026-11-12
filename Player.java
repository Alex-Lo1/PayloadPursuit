
public class Player {

    private String playerName;
    private int totalScore;

    // Constructor creates a player with a name
    public Player(String playerName) {

        this.playerName = playerName;
        this.totalScore = 0;
    }

    // Returns the player's name
    public String getPlayerName() {

        return playerName;
    }

    // Returns the player's total score
    public int getTotalScore() {

        return totalScore;
    }

    // Adds points to the player's total score
    public void addScore(int points) {

        totalScore = totalScore + points;
    }

    // Resets the player's score back to zero
    public void resetScore() {

        totalScore = 0;
    }

    // Returns the player's details
    @Override
    public String toString() {

        return playerName + " - Total Score: " + totalScore;
    }
}
