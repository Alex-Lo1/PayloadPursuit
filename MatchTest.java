
import java.util.Scanner;

public class MatchTest {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("      PAYLOAD PURSUIT");
        System.out.println("==============================");

        // Get the names of both players
        System.out.print("Enter first player's name: ");
        String firstPlayerName = keyboard.nextLine();

        System.out.print("Enter second player's name: ");
        String secondPlayerName = keyboard.nextLine();

        // Create both players
        Player firstPlayer = new Player(firstPlayerName);
        Player secondPlayer = new Player(secondPlayerName);

        // Create and start the match
        Match match = new Match(
            firstPlayer,
            secondPlayer,
            keyboard
        );

        match.playMatch();
    }
}
