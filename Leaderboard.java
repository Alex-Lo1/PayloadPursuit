
public class Leaderboard {

    private Player[] players;
    private int numberOfPlayers;

    // Constructor creates an empty leaderboard
    public Leaderboard() {

        players = new Player[100];
        numberOfPlayers = 0;
    }

    // Finds an existing player using their name
    public Player findPlayer(String playerName) {

        for (int playerIndex = 0;
             playerIndex < numberOfPlayers;
             playerIndex++) {

            if (players[playerIndex].getPlayerName()
                    .equalsIgnoreCase(playerName)) {

                return players[playerIndex];
            }
        }

        return null;
    }

    // Finds a player or creates a new player
    public Player getOrCreatePlayer(String playerName) {

        Player existingPlayer = findPlayer(playerName);

        if (existingPlayer != null) {

            return existingPlayer;
        }

        if (numberOfPlayers == players.length) {

            System.out.println("Leaderboard is full.");
            return null;
        }

        Player newPlayer = new Player(playerName);

        players[numberOfPlayers] = newPlayer;

        numberOfPlayers++;

        return newPlayer;
    }

    // Records the result of a completed match
    public void recordMatch(Player firstPlayer,
                            Player secondPlayer,
                            Player winner) {

        firstPlayer.addMatchPlayed();
        secondPlayer.addMatchPlayed();

        if (winner != null) {

            winner.addMatchWon();
        }
    }

    // Displays players ordered by most match wins
    public void displayLeaderboard() {

        if (numberOfPlayers == 0) {

            System.out.println("No players registered yet.");
            return;
        }

        // Create a copy so the original player array stays unchanged
        Player[] sortedPlayers = new Player[numberOfPlayers];

        for (int playerIndex = 0;
             playerIndex < numberOfPlayers;
             playerIndex++) {

            sortedPlayers[playerIndex] = players[playerIndex];
        }

        // Sort players using selection sort
        for (int currentIndex = 0;
             currentIndex < numberOfPlayers - 1;
             currentIndex++) {

            int highestWinsIndex = currentIndex;

            for (int searchIndex = currentIndex + 1;
                 searchIndex < numberOfPlayers;
                 searchIndex++) {

                if (sortedPlayers[searchIndex].getMatchesWon()
                        > sortedPlayers[highestWinsIndex].getMatchesWon()) {

                    highestWinsIndex = searchIndex;
                }
            }

            Player temporaryPlayer = sortedPlayers[currentIndex];

            sortedPlayers[currentIndex] =
                    sortedPlayers[highestWinsIndex];

            sortedPlayers[highestWinsIndex] = temporaryPlayer;
        }

        System.out.println();
        System.out.println("========== LEADERBOARD ==========");

        for (int playerIndex = 0;
             playerIndex < numberOfPlayers;
             playerIndex++) {

            Player currentPlayer = sortedPlayers[playerIndex];

            System.out.println((playerIndex + 1) + ". "
                    + currentPlayer.getPlayerName()
                    + " | Wins: " + currentPlayer.getMatchesWon()
                    + " | Matches: " + currentPlayer.getMatchesPlayed());
        }
    }

    // Displays the history of one player
    public void displayPlayerHistory(String playerName) {

        Player player = findPlayer(playerName);

        if (player == null) {

            System.out.println("Player not found.");
            return;
        }

        System.out.println();
        System.out.println("========== PLAYER HISTORY ==========");

        System.out.println("Name: " + player.getPlayerName());

        System.out.println("Matches played: "
                + player.getMatchesPlayed());

        System.out.println("Matches won: "
                + player.getMatchesWon());

        System.out.printf("Win percentage: %.2f%%%n",
                player.getWinPercentage());
    }

    // Displays players who have more than a specified number of wins
    public void displayPlayersWithMoreThanWins(int minimumWins) {

        boolean foundPlayer = false;

        System.out.println();
        System.out.println("Players with more than "
                + minimumWins + " wins:");

        for (int playerIndex = 0;
             playerIndex < numberOfPlayers;
             playerIndex++) {

            Player currentPlayer = players[playerIndex];

            if (currentPlayer.getMatchesWon() > minimumWins) {

                System.out.println(currentPlayer.getPlayerName()
                        + " - Wins: "
                        + currentPlayer.getMatchesWon());

                foundPlayer = true;
            }
        }

        if (!foundPlayer) {

            System.out.println("No players found.");
        }
    }

    // Compares the statistics of two players
    public void comparePlayers(String firstPlayerName,
                               String secondPlayerName) {

        Player firstPlayer = findPlayer(firstPlayerName);
        Player secondPlayer = findPlayer(secondPlayerName);

        if (firstPlayer == null || secondPlayer == null) {

            System.out.println("One or both players were not found.");
            return;
        }

        System.out.println();
        System.out.println("========== PLAYER COMPARISON ==========");

        System.out.println(firstPlayer.getPlayerName()
                + " - Matches: " + firstPlayer.getMatchesPlayed()
                + ", Wins: " + firstPlayer.getMatchesWon()
                + ", Win rate: "
                + String.format("%.2f", firstPlayer.getWinPercentage())
                + "%");

        System.out.println(secondPlayer.getPlayerName()
                + " - Matches: " + secondPlayer.getMatchesPlayed()
                + ", Wins: " + secondPlayer.getMatchesWon()
                + ", Win rate: "
                + String.format("%.2f", secondPlayer.getWinPercentage())
                + "%");
    }
}
