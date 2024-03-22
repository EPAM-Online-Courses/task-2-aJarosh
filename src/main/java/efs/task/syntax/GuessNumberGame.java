package efs.task.syntax;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GuessNumberGame {

    int M;
    int randomNumber;
    int L;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        //TODO: Implement the constructor
        try {
            int M = Integer.parseInt(argument);
            if (M < 1 || M > UsefulConstants.MAX_UPPER_BOUND) {
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new IllegalArgumentException();
            }
            this.M = M;
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }



    }
    public void play() {
        //TODO: Implement the method that executes the game session
        this.randomNumber = ThreadLocalRandom.current().nextInt(1, M + 1);
        this.L = (int) Math.floor(Math.log(M) / Math.log(2)) +1;
        System.out.println("<1," + M + ">");
        Scanner scanner = new Scanner(System.in);
        for (int attempt = 1; attempt <= L; attempt++) {
            System.out.print("[");
            for (int i = 0; i < attempt; i++) {
                System.out.print("*");
            }
            for (int i = attempt; i < L; i++) {
                System.out.print(".");
            }
            System.out.println("]");
            System.out.println(UsefulConstants.GIVE_ME);

            if (!scanner.hasNextInt()) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            int guess = scanner.nextInt();
            if (guess == randomNumber) {
                System.out.println(UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS);
                return;
            } else if (guess < randomNumber) {
                System.out.println(UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.TO_MUCH);
            }
        }

        System.out.println(UsefulConstants.UNFORTUNATELY);
    }
}
