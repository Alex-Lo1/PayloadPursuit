
import java.util.Random;

public class Deck {

    private Card[] cards;
    private int cardsRemaining;

    // Constructor creates and shuffles a standard 52-card deck
    public Deck() {

        cards = new Card[52];

        String[] suits = {
            "Hearts", "Diamonds", "Clubs", "Spades"
        };

        String[] ranks = {
            "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "Jack", "Queen",
            "King", "Ace"
        };

        int cardIndex = 0;

        // Populate the deck with all 52 cards
        for (String suit : suits) {

            for (String rank : ranks) {

                int cardValue;

                // Assign numerical values to each card
                if (rank.equals("Jack") ||
                    rank.equals("Queen") ||
                    rank.equals("King")) {

                    cardValue = 10;
                }
                else if (rank.equals("Ace")) {

                    cardValue = 11;
                }
                else {

                    cardValue = Integer.parseInt(rank);
                }

                cards[cardIndex] = new Card(
                    rank + " of " + suit,
                    cardValue
                );

                cardIndex++;
            }
        }

        cardsRemaining = cards.length;

        shuffle();
    }

    // Randomly rearranges cards using Fisher-Yates
    public void shuffle() {

        Random randomGenerator = new Random();

        for (int currentIndex = cardsRemaining - 1;
             currentIndex > 0;
             currentIndex--) {

            int randomIndex = randomGenerator.nextInt(currentIndex + 1);

            Card temporaryCard = cards[currentIndex];

            cards[currentIndex] = cards[randomIndex];

            cards[randomIndex] = temporaryCard;
        }
    }

    // Draws and returns the next available card
    public Card drawCard() {

        if (cardsRemaining == 0) {
            return null;
        }

        cardsRemaining--;

        return cards[cardsRemaining];
    }

    // Returns the number of cards remaining
    public int getCardsRemaining() {

        return cardsRemaining;
    }
}
