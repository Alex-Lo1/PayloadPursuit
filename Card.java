
public class Card {

    private String rank;
    private String suit;

    // Constructor used to initialise a playing card
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Returns the number value of the card
    public int getValue() {

        if (rank.equals("Ace")) {
            return 11;
        }

        if (rank.equals("Jack") ||
            rank.equals("Queen") ||
            rank.equals("King")) {

            return 10;
        }

        return Integer.parseInt(rank);
    }

    // Returns the rank of the card
    public String getRank() {
        return rank;
    }

    // Returns the suit of the card
    public String getSuit() {
        return suit;
    }

    // Returns a readable representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
