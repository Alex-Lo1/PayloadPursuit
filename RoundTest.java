
public class RoundTest {

    public static void main(String[] args) {

        Round round = new Round(42);

        System.out.println("==============================");
        System.out.println("         ROUND TEST");
        System.out.println("==============================");

        // Test 1: Undershoot
        Card[] undershootCards = {
            new Card("10 of Hearts", 10),
            new Card("10 of Clubs", 10),
            new Card("10 of Spades", 10),
            new Card("8 of Diamonds", 8)
        };

        int undershootTotal = round.calculateBestTotal(
            undershootCards, 4
        );

        System.out.println("Undershoot total: " + undershootTotal);
        System.out.println("Undershoot score: "
                + round.calculateScore(undershootTotal));

        // Test 2: Exact hit
        Card[] exactHitCards = {
            new Card("10 of Hearts", 10),
            new Card("10 of Clubs", 10),
            new Card("10 of Spades", 10),
            new Card("Queen of Diamonds", 10),
            new Card("2 of Hearts", 2)
        };

        int exactHitTotal = round.calculateBestTotal(
            exactHitCards, 5
        );

        System.out.println();
        System.out.println("Exact hit total: " + exactHitTotal);
        System.out.println("Exact hit score: "
                + round.calculateScore(exactHitTotal));

        // Test 3: Overshoot
        Card[] overshootCards = {
            new Card("10 of Hearts", 10),
            new Card("10 of Clubs", 10),
            new Card("10 of Spades", 10),
            new Card("10 of Diamonds", 10),
            new Card("5 of Hearts", 5)
        };

        int overshootTotal = round.calculateBestTotal(
            overshootCards, 5
        );

        System.out.println();
        System.out.println("Overshoot total: " + overshootTotal);
        System.out.println("Overshoot score: "
                + round.calculateScore(overshootTotal));

        // Test 4: Ace optimisation
        Card[] aceCards = {
            new Card("10 of Hearts", 10),
            new Card("10 of Clubs", 10),
            new Card("10 of Spades", 10),
            new Card("5 of Diamonds", 5),
            new Card("Ace of Hearts", 11)
        };

        int aceTotal = round.calculateBestTotal(aceCards, 5);

        System.out.println();
        System.out.println("Ace test total: " + aceTotal);
        System.out.println("Ace test score: "
                + round.calculateScore(aceTotal));

        System.out.println("==============================");
    }
}
