
public class Round {

    private Deck deck;
    private int target;

    // Constructor creates a new round with a fresh deck
    public Round(int target) {

        this.target = target;
        this.deck = new Deck();
    }

    // Draws a card from the deck
    public Card drawCard() {

        return deck.drawCard();
    }

    // Calculates the best possible total for a player's cards
    public int calculateBestTotal(Card[] playerCards, int numberOfCards) {

        int total = 0;
        int numberOfAces = 0;

        // Calculate the total using Ace as 11 initially
        for (int cardIndex = 0; cardIndex < numberOfCards; cardIndex++) {

            Card currentCard = playerCards[cardIndex];

            total = total + currentCard.getBaseValue();

            // Count the number of Aces
            if (currentCard.getCardName().startsWith("Ace")) {
                numberOfAces++;
            }
        }

        // Try changing Aces from 11 to 1
        // if this produces a better score
        while (numberOfAces > 0) {

            int currentScore = calculateScore(total);

            int reducedTotal = total - 10;

            int reducedScore = calculateScore(reducedTotal);

            if (reducedScore < currentScore) {

                total = reducedTotal;
                numberOfAces--;
            }
            else {

                break;
            }
        }

        return total;
    }

    // Calculates the score based on the target
    public int calculateScore(int total) {

        if (total == target) {

            return Rules.perfectHitBonus;
        }
        else if (total < target) {

            return target - total;
        }
        else {

            return (total - target) * Rules.overshootMultiplier;
        }
    }

    // Displays a player's cards and round result
    public void displayPlayerResult(Player player, Card[] playerCards,
                                    int numberOfCards) {

        System.out.println();
        System.out.println("Player: " + player.getPlayerName());

        System.out.println("Cards:");

        for (int cardIndex = 0; cardIndex < numberOfCards; cardIndex++) {

            System.out.println(playerCards[cardIndex]);
        }

        int bestTotal = calculateBestTotal(playerCards, numberOfCards);

        int roundScore = calculateScore(bestTotal);

        System.out.println("Best card total: " + bestTotal);

        System.out.println("Round score: " + roundScore);

        player.addScore(roundScore);
    }

    // Returns the target
    public int getTarget() {

        return target;
    }
}
