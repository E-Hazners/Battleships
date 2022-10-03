package battleship;

import java.util.Scanner;

public class Game {
    boolean gameIsFinished = false;
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // creating 2 fields and creating ships for each field
        Field fieldPlayer1 = new Field();
        Field fieldPlayer2 = new Field();
        Ships shipsPlayer1 = new Ships();
        Ships shipsPlayer2 = new Ships();

        // creating boolean of movePlayer1, so we can regulate which players move is right now
        boolean movePlayer1 = true;
        String checkEnter = null;

        // creating empty fields
        fieldPlayer1.createField();
        fieldPlayer2.createField();

        // player 1 places his ships
        System.out.println("Player 1, place your ships on the game field");
        fieldPlayer1.printField();
        shipsPlayer1.startPlacingShips(fieldPlayer1);
        System.out.println("Press Enter and pass the move to another player");
        checkEnterAndPrintVoid();

        // player 2 places his ships
        System.out.println("Player 2, place your ships on the game field");
        fieldPlayer2.printField();
        shipsPlayer2.startPlacingShips(fieldPlayer2);
        System.out.println("Press Enter and pass the move to another player");
        checkEnterAndPrintVoid();


        System.out.println();
        System.out.println("The game starts!");

        // starting the game
        while(!gameIsFinished) {

            if(movePlayer1) {
                // player 1 move, prints enemy field above and players field under the line
                fieldPlayer2.printShots();
                System.out.println("---------------------");
                fieldPlayer1.printField();
                System.out.println("Player 1, it's your turn:");

                if(fieldPlayer1.checkEmptyField()) {
                    gameIsFinished = true;
                } else {
                    takeShot(fieldPlayer2);
                    System.out.println("Press Enter and pass the move to another player");
                    checkEnterAndPrintVoid();
                }
                movePlayer1 = false;
            } else {
                // player 2 move, prints enemy field above and players field under the line
                fieldPlayer1.printShots();
                System.out.println("---------------------");
                fieldPlayer2.printField();
                System.out.println("Player 2, it's your turn:");
                if(fieldPlayer2.checkEmptyField()) {
                    gameIsFinished = true;
                } else {
                    takeShot(fieldPlayer1);
                    System.out.println("Press Enter and pass the move to another player");
                    checkEnterAndPrintVoid();
                }
                movePlayer1 = true;
            }

        }
    }
    // taking a shot, asking for input, checking if it's valid and changing the field if it is valid shot
    public void takeShot(Field field) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();

        String nextShotCoord = scanner.next();
        boolean shotChecked = field.checkShotValidation(nextShotCoord);
        while(!shotChecked) {
            nextShotCoord = scanner.next();
            shotChecked = field.checkShotValidation(nextShotCoord);
        }
        field.takeAShot(nextShotCoord);


    }

    // prints void after user pressed enter so the user can't see what other user did on his move
    public void checkEnterAndPrintVoid() {
        String checkEnter = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            checkEnter = scanner.nextLine();
            if(!checkEnter.equals("\\n")) {
                System.out.println("\n".repeat(99));
                break;
            }

        }
    }

}
