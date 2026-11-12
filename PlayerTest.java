
public class PlayerTest {

    public static void main(String[] args) {

        // Create two players
        Player firstPlayer = new Player("Alex");
        Player secondPlayer = new Player("BotPlayer01");

        System.out.println("==============================");
        System.out.println("         PLAYER TEST");
        System.out.println("==============================");

        // Display player names and starting scores
        System.out.println("First Player: " + firstPlayer);
        System.out.println("Second Player: " + secondPlayer);

        // Add scores to both players
        firstPlayer.addScore(10);
        secondPlayer.addScore(15);

        System.out.println("------------------------------");
        System.out.println("After adding scores:");

        System.out.println(firstPlayer);
        System.out.println(secondPlayer);

        // Add another score to the first player
        firstPlayer.addScore(5);

        System.out.println("------------------------------");
        System.out.println("After another round:");

        System.out.println(firstPlayer);

        // Reset the second player's score
        secondPlayer.resetScore();

        System.out.println("------------------------------");
        System.out.println("After resetting second player:");

        System.out.println(secondPlayer);

        System.out.println("==============================");
    }
}
