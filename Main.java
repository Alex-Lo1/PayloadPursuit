
import java.util.Scanner;

public class Main {

    /*
     * COM498 - Algorithms and Data Structures
     * Assignment 2 - Payload Pursuit
     * Student Variant B
     */

    private static Scanner keyboard = new Scanner(System.in);

    private static Leaderboard leaderboard = new Leaderboard();

    public static void main(String[] args) {

        boolean running = true;

        displayWelcomeMessage();

        while (running) {

            displayMenu();

            System.out.print("Enter your choice: ");

            if (keyboard.hasNextInt()) {

                int choice = keyboard.nextInt();
                keyboard.nextLine();

                switch (choice) {

                    case 1:
                        startMatch();
                        break;

                    case 2:
                        leaderboard.displayLeaderboard();
                        break;

                    case 3:
                        System.out.println(
                            "Simulations will be implemented in Stage 7."
                        );
                        break;

                    case 4:
                        comparePlayers();
                        break;

                    case 5:
                        searchPlayerHistory();
                        break;

                    case 6:
                        listPlayersWithMoreThanWins();
                        break;

                    case 7:
                        running = false;
                        System.out.println("Thanks for playing!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
            else {

                System.out.println("Please enter a number.");
                keyboard.nextLine();
            }
        }
    }

    // Displays the welcome message
    public static void displayWelcomeMessage() {

        System.out.println("================================");
        System.out.println("       PAYLOAD PURSUIT");
        System.out.println("================================");
        System.out.println("Student Variant: B");
    }

    // Displays the main menu
    public static void displayMenu() {

        System.out.println();
        System.out.println("========== MAIN MENU ==========");
        System.out.println("1. Play Match");
        System.out.println("2. View Leaderboard");
        System.out.println("3. Run a Simulation");
        System.out.println("4. Compare Two Players");
        System.out.println("5. Search Player History");
        System.out.println("6. List Players with > X Match Wins");
        System.out.println("7. Exit");
        System.out.println("===============================");
    }

    // Starts a new match
    public static void startMatch() {

        System.out.println();
        System.out.print("Enter first player's name: ");

        String firstPlayerName = keyboard.nextLine().trim();

        System.out.print("Enter second player's name: ");

        String secondPlayerName = keyboard.nextLine().trim();

        if (firstPlayerName.isEmpty() ||
            secondPlayerName.isEmpty()) {

            System.out.println("Player names cannot be empty.");
            return;
        }

        if (firstPlayerName.equalsIgnoreCase(secondPlayerName)) {

            System.out.println("Players must have different names.");
            return;
        }

        Player firstPlayer =
                leaderboard.getOrCreatePlayer(firstPlayerName);

        Player secondPlayer =
                leaderboard.getOrCreatePlayer(secondPlayerName);

        if (firstPlayer == null || secondPlayer == null) {

            System.out.println("Unable to create players.");
            return;
        }

        Match match = new Match(
            firstPlayer,
            secondPlayer,
            keyboard
        );

        Player winner = match.playMatch();

        // Save the match result in the leaderboard
        leaderboard.recordMatch(
            firstPlayer,
            secondPlayer,
            winner
        );
    }

    // Searches for a player's match history
    public static void searchPlayerHistory() {

        System.out.print("Enter player name to search: ");

        String playerName = keyboard.nextLine().trim();

        leaderboard.displayPlayerHistory(playerName);
    }

    // Compares two registered players
    public static void comparePlayers() {

        System.out.print("Enter first player name: ");

        String firstPlayerName = keyboard.nextLine().trim();

        System.out.print("Enter second player name: ");

        String secondPlayerName = keyboard.nextLine().trim();

        leaderboard.comparePlayers(
            firstPlayerName,
            secondPlayerName
        );
    }

    // Lists players with more than a specified number of wins
    public static void listPlayersWithMoreThanWins() {

        System.out.print("Enter minimum number of wins: ");

        if (keyboard.hasNextInt()) {

            int minimumWins = keyboard.nextInt();
            keyboard.nextLine();

            if (minimumWins < 0) {

                System.out.println("Wins cannot be negative.");
                return;
            }

            leaderboard.displayPlayersWithMoreThanWins(minimumWins);
        }
        else {

            System.out.println("Please enter a valid number.");
            keyboard.nextLine();
        }
    }
}
