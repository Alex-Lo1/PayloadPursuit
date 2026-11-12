
import java.util.Random;
import java.util.Scanner;

public class Match {

    private Player firstPlayer;
    private Player secondPlayer;
    private Scanner keyboard;
    private boolean automated;
    private boolean automatedFirstTurn;
    private int automatedFirstGuess;
    private Random botRandomGenerator = new Random();

    // Constructor sets up the two players
    // Constructor for normal human matches
    public Match(Player firstPlayer, Player secondPlayer,
                Scanner keyboard) {

        this(firstPlayer, secondPlayer, keyboard, false);
    }

    // Constructor for human or automated matches
    public Match(Player firstPlayer, Player secondPlayer,
                Scanner keyboard, boolean automated) {

        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.keyboard = keyboard;
        this.automated = automated;
    }

    // Runs the complete four-round match
    public Player playMatch() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();

        int currentTarget = Rules.initialTarget;

        // Randomly choose who starts the first round
        Random randomGenerator = new Random();

        boolean firstPlayerStarts = randomGenerator.nextBoolean();

        System.out.println();
        System.out.println("================================");
        System.out.println("       PAYLOAD PURSUIT");
        System.out.println("================================");

        System.out.println("Player 1: " + firstPlayer.getPlayerName());
        System.out.println("Player 2: " + secondPlayer.getPlayerName());

        // Play all four rounds
        for (int roundNumber = 1;
             roundNumber <= Rules.numberOfRounds;
             roundNumber++) {

            automatedFirstTurn = true;

            System.out.println();
            System.out.println("================================");
            System.out.println("ROUND " + roundNumber);
            System.out.println("Target: " + currentTarget);
            System.out.println("================================");

            // Create a fresh shuffled deck for this round
            Round currentRound = new Round(currentTarget);

            Player roundFirstPlayer;
            Player roundSecondPlayer;

            // Alternate the starting player each round
            if (firstPlayerStarts) {

                roundFirstPlayer = firstPlayer;
                roundSecondPlayer = secondPlayer;
            }
            else {

                roundFirstPlayer = secondPlayer;
                roundSecondPlayer = firstPlayer;
            }

            System.out.println(roundFirstPlayer.getPlayerName()
                    + " goes first.");

            int firstPlayerTotal = playPlayerTurn(
                roundFirstPlayer, currentRound,
                currentRound.getCardsRemaining() - 1
            );

            // Second player can see the first player's result
            System.out.println();
            System.out.println(roundSecondPlayer.getPlayerName()
                    + " can now see the first player's result.");

            System.out.println("First player's total: "
                    + firstPlayerTotal);

            int secondPlayerTotal = playPlayerTurn(
                roundSecondPlayer, currentRound,
                currentRound.getCardsRemaining()
            );

            // Update the target if both players had the same outcome
            if (firstPlayerTotal < currentTarget &&
                secondPlayerTotal < currentTarget) {

                currentTarget = currentTarget
                        + Rules.undershootTargetChange;

                System.out.println();
                System.out.println("Both players undershot.");
                System.out.println("Target increases by "
                        + Rules.undershootTargetChange);
            }
            else if (firstPlayerTotal > currentTarget &&
                     secondPlayerTotal > currentTarget) {

                currentTarget = currentTarget
                        + Rules.overshootTargetChange;

                System.out.println();
                System.out.println("Both players overshot.");
                System.out.println("Target decreases by 5");
            }
            else {

                System.out.println();
                System.out.println("Target remains unchanged.");
            }

            // Display scores after this round
            System.out.println();
            System.out.println("Current match scores:");

            System.out.println(firstPlayer);
            System.out.println(secondPlayer);

            // The other player starts the next round
            firstPlayerStarts = !firstPlayerStarts;
        }

        // Display the final match results
        return displayMatchWinner();
    }

    
    // Handles one player's guess, card draw and round score
    private int playPlayerTurn(Player player, Round currentRound,
                               int maximumGuess) {

        System.out.println();
        System.out.println(player.getPlayerName() + "'s turn.");

        System.out.println("Cards remaining in deck: "
                + currentRound.getCardsRemaining());

        int cardGuess;

        if (automated) {

            if (automatedFirstTurn) {

                // First bot guesses randomly between 3 and 7
                automatedFirstGuess =
                        botRandomGenerator.nextInt(
                            Rules.maximumBotGuess
                            - Rules.minimumBotGuess + 1
                        ) + Rules.minimumBotGuess;

                cardGuess = automatedFirstGuess;

                automatedFirstTurn = false;
            }
            else {

                // Second bot guesses one more card
                cardGuess = automatedFirstGuess
                        + Rules.secondPlayerGuessOffset;
            }

            // Ensure the guess does not exceed available cards
            if (cardGuess > maximumGuess) {
                cardGuess = maximumGuess;
            }

            System.out.println(player.getPlayerName()
                    + " guesses " + cardGuess + " cards.");

        }
        else {

            cardGuess = getValidGuess(maximumGuess);
        }

        Card[] playerCards = new Card[cardGuess];

        for (int cardIndex = 0; cardIndex < cardGuess; cardIndex++) {

            playerCards[cardIndex] = currentRound.drawCard();
        }

        int bestTotal = currentRound.calculateBestTotal(
            playerCards, cardGuess
        );

        int roundScore = currentRound.calculateScore(bestTotal);

        System.out.println();
        System.out.println(player.getPlayerName() + "'s cards:");

        for (int cardIndex = 0; cardIndex < cardGuess; cardIndex++) {

            System.out.println(playerCards[cardIndex]);
        }

        System.out.println("Card total: " + bestTotal);
        System.out.println("Round score: " + roundScore);

        player.addScore(roundScore);

        return bestTotal;
    }


    // Gets a valid card guess from the player
    private int getValidGuess(int maximumGuess) {

        int cardGuess = 0;

        while (cardGuess < 1 || cardGuess > maximumGuess) {

            System.out.println("How many cards would you like to draw?");

            if (keyboard.hasNextInt()) {

                cardGuess = keyboard.nextInt();

                if (cardGuess < 1 || cardGuess > maximumGuess) {

                    System.out.println("Please enter a number between 1 and "
                            + maximumGuess + ".");
                }
            }
            else {

                System.out.println("Please enter a valid number.");
                keyboard.next();
            }
        }

        return cardGuess;
    }

    // Determines and displays the match winner
    private Player displayMatchWinner() {

        System.out.println();
        System.out.println("================================");
        System.out.println("        MATCH FINISHED");
        System.out.println("================================");

        System.out.println(firstPlayer);
        System.out.println(secondPlayer);

        if (firstPlayer.getTotalScore()
                < secondPlayer.getTotalScore()) {

            System.out.println("Winner: "
                    + firstPlayer.getPlayerName());

            return firstPlayer;
        }
        else if (secondPlayer.getTotalScore()
                < firstPlayer.getTotalScore()) {

            System.out.println("Winner: "
                    + secondPlayer.getPlayerName());

            return secondPlayer;
        }
        else {

            System.out.println("The match is a draw!");

            return null;
        }
    }

}
