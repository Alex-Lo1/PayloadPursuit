
import java.util.Random;
import java.util.Scanner;

public class Simulation {

    private Leaderboard leaderboard;
    private Scanner keyboard;
    private Random randomGenerator;

    // Constructor
    public Simulation(Leaderboard leaderboard, Scanner keyboard) {

        this.leaderboard = leaderboard;
        this.keyboard = keyboard;
        this.randomGenerator = new Random();
    }

    // Runs the requested number of simulated matches
    public void runSimulation(int numberOfMatches) {

        if (numberOfMatches <= 0) {

            System.out.println("Enter a number greater than zero.");
            return;
        }

        System.out.println();
        System.out.println("================================");
        System.out.println("       SIMULATION STARTED");
        System.out.println("================================");

        int totalDraws = 0;

        for (int matchNumber = 1;
             matchNumber <= numberOfMatches;
             matchNumber++) {

            System.out.println();
            System.out.println("Starting simulated match "
                    + matchNumber + " of " + numberOfMatches);

            // Choose two different bots from the ten available
            int firstBotNumber = randomGenerator.nextInt(10) + 1;
            int secondBotNumber = randomGenerator.nextInt(10) + 1;

            while (secondBotNumber == firstBotNumber) {

                secondBotNumber = randomGenerator.nextInt(10) + 1;
            }

            String firstBotName = String.format(
                    "BotPlayer%02d", firstBotNumber);

            String secondBotName = String.format(
                    "BotPlayer%02d", secondBotNumber);

            // Retrieve existing bots or create new ones
            Player firstBot =
                    leaderboard.getOrCreatePlayer(firstBotName);

            Player secondBot =
                    leaderboard.getOrCreatePlayer(secondBotName);

            if (firstBot == null || secondBot == null) {

                System.out.println("Unable to create bot players.");
                return;
            }

            // Create and run an automated match
            Match match = new Match(
                firstBot,
                secondBot,
                keyboard,
                true
            );

            Player winner = match.playMatch();

            // Record the match result
            leaderboard.recordMatch(
                firstBot,
                secondBot,
                winner
            );

            totalDraws++;

            System.out.println("Simulated match "
                    + matchNumber + " completed.");
        }

        System.out.println();
        System.out.println("================================");
        System.out.println("      SIMULATION COMPLETED");
        System.out.println("================================");

        System.out.println("Matches simulated: " + totalDraws);

        System.out.println();
        System.out.println("Updated leaderboard:");

        leaderboard.displayLeaderboard();
    }
}
