
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

    
public int calculateBestTotal(Card[] playerCards, int numberOfCards) {

    int total = 0;
    int numberOfAces = 0;

    // Calculate the total with every Ace initially worth 11
    for (int cardIndex = 0; cardIndex < numberOfCards; cardIndex++) {

        Card currentCard = playerCards[cardIndex];

        total = total + currentCard.getBaseValue();

        if (currentCard.getCardName().startsWith("Ace")) {
            numberOfAces++;
        }
    }

    // Reduce an Ace from 11 to 1 if it gets closer to the target
    while (numberOfAces > 0) {

        int currentDifference = Math.abs(target - total);

        int reducedTotal = total - 10;

        int reducedDifference = Math.abs(target - reducedTotal);

        if (reducedDifference < currentDifference) {

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


    // Returns the number of cards remaining in the deck
    public int getCardsRemaining() {

        return deck.getCardsRemaining();
    }

}
