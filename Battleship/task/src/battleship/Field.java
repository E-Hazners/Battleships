package battleship;

import java.util.Objects;

public class Field {
    String[][] field = new String[11][11];
    // variables for each ship so it's easier to work with it (check if it's alive, length, etc.)
    public String aircraftCarrierCoord1 = "";
    public String aircraftCarrierCoord2 = "";

    public String battleshipCoord1 = "";
    public String battleshipCoord2 = "";

    public String submarineCoord1 = "";
    public String submarineCoord2 = "";

    public String cruiserCoord1 = "";
    public String cruiserCoord2 = "";

    public String destroyerCoord1 = "";
    public String destroyerCoord2 = "";

    // creating empty field
    public void createField() {

        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                } else if (i == 0) {
                    field[i][j] = String.valueOf(j);
                } else if (j == 0 ) {
                    field[i][j] = String.valueOf(alphabet[i-1]);
                } else {
                    field[i][j] = "~";
                }
            }
        }
    }

    // prints all field with all ships, misses and hits
    public void printField() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                System.out.print(field[i][j] + " ");

            }
            System.out.println();
        }
    }

    // print field with only shots and misses
    public void printShots() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(field[i][j] == "O") {
                    System.out.print("~" + " ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // checking some errors that can cause trouble, so it's necessary to fix them
    public boolean checkSpace(String coord1, String coord2, int shipLength) {

        int firstCoordLetter = assignCoordLetter(coord1);
        int secondCoordLetter = assignCoordLetter(coord2);
        int firstCoordNum = assignCoordNum(coord1);
        int secondCoordNum = assignCoordNum(coord2);


        if(firstCoordNum != secondCoordNum && firstCoordLetter != secondCoordLetter) {
            System.out.println("Error! Wrong ship location! Try Again:");
            return false;
        } else if ((shipLength == (firstCoordNum - secondCoordNum) + 1) || (shipLength == (secondCoordLetter - firstCoordLetter) + 1) ||
                (shipLength == (secondCoordNum - firstCoordNum) + 1) || (shipLength == (firstCoordLetter - secondCoordLetter) + 1)) {
            return checkTooClose(coord1, coord2);
        } else {
            System.out.println("Error! Wrong length of the Submarine! Try Again:");
            return false;
        }

    }

    // checking if given coordinates are too close to each other like in original rules of battleships
    public boolean checkTooClose(String coord1, String coord2) {

        int firstCoordLetter = assignCoordLetter(coord1);
        int secondCoordLetter = assignCoordLetter(coord2);
        int firstCoordNum = assignCoordNum(coord1);
        int secondCoordNum = assignCoordNum(coord2);

        // placing the smaller coord in the front
        if(firstCoordLetter > secondCoordLetter) {
            int temp = firstCoordLetter;
            firstCoordLetter = secondCoordLetter;
            secondCoordLetter = temp;
        }

        if(firstCoordNum > secondCoordNum) {
            int temp = firstCoordNum;
            firstCoordNum = secondCoordNum;
            secondCoordNum = temp;
        }

        // checking left top corner, so there isn't an error
        if (firstCoordLetter == 1 && firstCoordNum == 1) {
            for (int i = firstCoordLetter; i < secondCoordLetter + 2; i++) {
                for (int j = firstCoordNum; j < secondCoordNum + 2; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }

                // checks top right corner
        }else if (firstCoordLetter == 1 && secondCoordNum == 10) {
            for (int i = firstCoordLetter; i < secondCoordLetter + 2; i++) {
                for (int j = firstCoordNum-1; j < secondCoordNum + 1; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }

            // checking bottom left corner
        }else if (secondCoordLetter == 10 && firstCoordNum == 1) {
            for (int i = firstCoordLetter-1; i < secondCoordLetter + 1; i++) {
                for (int j = firstCoordNum; j < secondCoordNum + 2; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }

            // checking bottom right corner
        } else if (secondCoordLetter == 10 && secondCoordNum == 10) {
            for (int i = firstCoordLetter-1; i < secondCoordLetter + 1; i++) {
                for (int j = firstCoordNum-1; j < secondCoordNum + 1; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }

            // checking top line
        } else if (firstCoordLetter == 1) {
            for (int i = firstCoordLetter; i < secondCoordLetter + 2; i++) {
                for (int j = firstCoordNum-1; j < secondCoordNum + 2; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }
            // checking right line
        }  else if (secondCoordNum == 10) {
            for (int i = firstCoordLetter-1; i < secondCoordLetter + 2; i++) {
                for (int j = firstCoordNum-1; j < secondCoordNum + 1; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }
            // checking left line
        }  else if (firstCoordNum == 1) {
            for (int i = firstCoordLetter-1; i < secondCoordLetter + 2; i++) {
                for (int j = firstCoordNum; j < secondCoordNum + 2; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }
            // checking bottom line
        }  else if (secondCoordLetter == 10) {
            for (int i = firstCoordLetter-1; i < secondCoordLetter + 1; i++) {
                for (int j = firstCoordNum-1; j < secondCoordNum + 2; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }

                }
            }
            // checking everything else
        } else {
            for (int i = firstCoordLetter-1; i < secondCoordLetter + 2; i++) {
                for (int j = firstCoordNum-1; j < secondCoordNum + 2; j++) {

                    if (field[i][j].equals("O")) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // creating an int (number) from the given coordinate letter, so it's easier to work with it
    public int assignCoordLetter(String coord) {
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        int coordLetter = 0;

        for (int i = 0; i < alphabet.length; i++) {
            if(coord.charAt(0) == alphabet[i]) {
                coordLetter = i + 1;
            }

        }
        return coordLetter;
    }

    // taking an int (number) from the given coordinate, so it's easier to work with it
    public int assignCoordNum(String coord) {
        if(coord.length() == 3 && coord.charAt(1) == '1' && coord.charAt(2) == '0') {
            return 10;
        } else if (coord.length() == 2){
            return coord.charAt(1) - '0';
        } else {
            return 0;
        }
    }

    // updating field with given coordinates
    public void updateField(String coord1, String coord2) {

        int firstCoordLetter = assignCoordLetter(coord1);
        int secondCoordLetter = assignCoordLetter(coord2);
        int firstCoordNum = assignCoordNum(coord1);
        int secondCoordNum = assignCoordNum(coord2);

        // placing the smaller coord in the front
        if(firstCoordLetter > secondCoordLetter) {
            int temp = firstCoordLetter;
            firstCoordLetter = secondCoordLetter;
            secondCoordLetter = temp;
        }

        if(firstCoordNum > secondCoordNum) {
            int temp = firstCoordNum;
            firstCoordNum = secondCoordNum;
            secondCoordNum = temp;
        }


        for (int i = firstCoordLetter; i < secondCoordLetter + 1; i++) {
            for (int j = firstCoordNum; j < secondCoordNum + 1; j++) {

                field[i][j] = "O";

            }
        }
    }

    // checks if given coordinates are valid on board
    public boolean checkShotValidation(String coord) {

        int coordLetter = assignCoordLetter(coord);
        int coordNum = assignCoordNum(coord);

        if(coordLetter == 0) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        } else if(coordNum == 0) {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            return false;
        } else {
            return true;
        }
    }

    // taking a shot, changing field if it's hit or miss
    public boolean takeAShot(String coord) {
        int coordLetter = assignCoordLetter(coord);
        int coordNum = assignCoordNum(coord);

        if(Objects.equals(field[coordLetter][coordNum], "O")) {
            field[coordLetter][coordNum] = "X";
            System.out.println(checkShipSank(coord));
            return true;
        } else if (Objects.equals(field[coordLetter][coordNum], "~")) {
            field[coordLetter][coordNum] = "M";
            System.out.println("You missed!");
            return true;
        } else {
            return false;
        }

    }

    // check if field is empty (all ships are sank), so we can end the game if it is
    public boolean checkEmptyField() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {

                if(Objects.equals(field[i][j], "O")) {
                    return false;
                }

            }
        }
        return true;

    }

    // checking if ship is sank or alive
    String checkShipSank(String coord) {
        System.out.println(aircraftCarrierCoord1 + " " + aircraftCarrierCoord2);
        System.out.println(battleshipCoord1 + " " + battleshipCoord2);
        System.out.println(submarineCoord1 + " " + submarineCoord2);
        System.out.println(cruiserCoord1 + " " + cruiserCoord2);
        System.out.println(destroyerCoord1 + " " + destroyerCoord2);
        System.out.println(coord);

        int ship = checkWhichShipIsUnderAttack(coord);
        boolean shipLive = false;
        boolean compared = false;

        if(checkEmptyField()) {
            return "You sank the last ship. You won. Congratulations!";
        }
        // checking with switch statement if attacked ship is alive or sank
        switch (ship) {
            case 1 -> {
                shipLive = checkShipLive(aircraftCarrierCoord1, aircraftCarrierCoord2);
            }
            case 2 -> {
                shipLive = checkShipLive(battleshipCoord1, battleshipCoord2);
            }
            case 3 -> {
                shipLive = checkShipLive(submarineCoord1, submarineCoord2);
            }
            case 4 -> {
                shipLive = checkShipLive(cruiserCoord1, cruiserCoord2);
            }
            case 5 -> {
                shipLive = checkShipLive(destroyerCoord1, destroyerCoord2);
            }
        }
        if(shipLive) {
            return "You hit a ship!";
        } else {
            return "You sank a ship!";
        }


    }

    // checking which ship is under attack
    public int checkWhichShipIsUnderAttack(String givenCoord) {
        if (shipUnderAttack(givenCoord, aircraftCarrierCoord1, aircraftCarrierCoord2)) {
            return 1;
        } else if (shipUnderAttack(givenCoord, battleshipCoord1, battleshipCoord2)) {
            return 2;
        } else if (shipUnderAttack(givenCoord, submarineCoord1, submarineCoord2)) {
            return 3;
        } else if (shipUnderAttack(givenCoord, cruiserCoord1, cruiserCoord2)) {
            return 4;
        } else if (shipUnderAttack(givenCoord, destroyerCoord1, destroyerCoord2)) {
            return 5;
        }
        return 0;
    }

    // checks if given coordinates of ship are under attack
    public boolean shipUnderAttack(String givenCoord, String shipCoord1, String shipCoord2) {
        int shipLetter1 = assignCoordLetter(shipCoord1);
        int shipLetter2 = assignCoordLetter(shipCoord2);
        int shipNum1 = assignCoordNum(shipCoord1);
        int shipNum2 = assignCoordNum(shipCoord2);
        boolean shipHorizontal = checkShipHorizontal(shipCoord1, shipCoord2);

        int givenCoordLetter = assignCoordLetter(givenCoord);
        int givenCoordNum = assignCoordNum(givenCoord);

        if(shipHorizontal && givenCoordLetter == shipLetter1) {
            return (givenCoordNum >= shipNum1 && givenCoordNum <= shipNum2) || (givenCoordNum <= shipNum1 && givenCoordNum >= shipNum2);
        } else if(!shipHorizontal && givenCoordNum == shipNum1) {
            return (givenCoordLetter >= shipLetter1 && givenCoordLetter <= shipLetter2) || (givenCoordLetter <= shipLetter1 && givenCoordLetter >= shipLetter2);
        }
        return false;
    }

    // checks if ship is horizontal (true) or vertical (false)
    boolean checkShipHorizontal(String coord1, String coord2) {
        int shipLetter1 = assignCoordLetter(coord1);
        int shipLetter2 = assignCoordLetter(coord2);
        return shipLetter1 == shipLetter2;
    }

    // checking if ship is sank or alive through for loops
    boolean checkShipLive(String shipCoord1, String shipCoord2) {
        int firstCoordLetter = assignCoordLetter(shipCoord1);
        int secondCoordLetter = assignCoordLetter(shipCoord2);
        int firstCoordNum = assignCoordNum(shipCoord1);
        int secondCoordNum = assignCoordNum(shipCoord2);
        for (int i = firstCoordLetter; i <= secondCoordLetter; i++) {
            for (int j = firstCoordNum; j <= secondCoordNum; j++) {

                if(Objects.equals(field[i][j], "O")) {
                    return true;
                }

            }

        }
        return false;
    }

}

