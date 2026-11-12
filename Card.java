
public class Card {

    private String cardName;
    private int baseValue;

    // Constructor initialises the card's name and value
    public Card(String cardName, int baseValue) {

        this.cardName = cardName;
        this.baseValue = baseValue;
    }

    // Returns the card's name
    public String getCardName() {

        return cardName;
    }

    // Returns the card's base value
    public int getBaseValue() {

        return baseValue;
    }

    // Returns the card details
    @Override
    public String toString() {

        return cardName + " (Value: " + baseValue + ")";
    }
}
