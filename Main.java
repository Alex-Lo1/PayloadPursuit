
public class Main {

    public static void main(String[] args) {

        displayWelcomeMessage();
        displayGameConfiguration();
    }

    // Displays the game welcome message
    public static void displayWelcomeMessage() {

        System.out.println("================================");
        System.out.println("       PAYLOAD PURSUIT");
        System.out.println("================================");
    }

    // Displays the configuration for Variant B
    public static void displayGameConfiguration() {

        System.out.println("Student Variant: B");

        System.out.println("Number of rounds: "
                + Rules.numberOfRounds);

        System.out.println("Initial target: "
                + Rules.initialTarget);

        System.out.println("Overshoot multiplier: "
                + Rules.overshootMultiplier);

        System.out.println("Perfect hit bonus: "
                + Rules.perfectHitBonus);

        System.out.println("--------------------------------");
    }
}
