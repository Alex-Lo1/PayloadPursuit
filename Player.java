
public class Player {

    private String playerName;
    private int totalScore;
    private int matchesPlayed;
    private int matchesWon;

    // Constructor creates a new player
    public Player(String playerName) {

        this.playerName = playerName;
        this.totalScore = 0;
        this.matchesPlayed = 0;
        this.matchesWon = 0;
    }

    // Returns the player's name
    public String getPlayerName() {

        return playerName;
    }

    // Returns the player's current match score
    public int getTotalScore() {

        return totalScore;
    }

    // Returns the number of matches played
    public int getMatchesPlayed() {

        return matchesPlayed;
    }

    // Returns the number of matches won
    public int getMatchesWon() {

        return matchesWon;
    }

    // Adds points to the player's score
    public void addScore(int points) {

        totalScore = totalScore + points;
    }

    // Resets the score before a new match
    public void resetScore() {

        totalScore = 0;
    }

    // Records that the player has played a match
    public void addMatchPlayed() {

        matchesPlayed++;
    }

    // Records a match victory
    public void addMatchWon() {

        matchesWon++;
    }

    // Calculates the player's win percentage
    public double getWinPercentage() {

        if (matchesPlayed == 0) {
            return 0;
        }

        return (double) matchesWon / matchesPlayed * 100;
    }

    // Returns the player's details
    @Override
    public String toString() {

        return playerName + " - Total Score: " + totalScore;
    }
}
