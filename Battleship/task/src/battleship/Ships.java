package battleship;

import java.util.Scanner;

public class Ships {

    int shipsInGame= 5;
    int shipsPlaced = 0;

    // start placing ships one by one
    public void startPlacingShips(Field field) {
        while(shipsPlaced < shipsInGame) {
            switch (shipsPlaced) {
                case 0 -> {
                    System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
                    askForCoordinates(field);
                    this.shipsPlaced++;
                    field.printField();
                }
                case 1 -> {
                    System.out.println("Enter the coordinates of the Battleship (4 cells):");
                    askForCoordinates(field);
                    this.shipsPlaced++;
                    field.printField();
                }
                case 2 -> {
                    System.out.println("Enter the coordinates of the Submarine (3 cells):");
                    askForCoordinates(field);
                    this.shipsPlaced++;
                    field.printField();
                }
                case 3 -> {
                    System.out.println("Enter the coordinates of the Cruiser (3 cells):");
                    askForCoordinates(field);
                    this.shipsPlaced++;
                    field.printField();
                }
                case 4 -> {
                    System.out.println("Enter the coordinates of the Destroyer (2 cells):");
                    askForCoordinates(field);
                    this.shipsPlaced++;
                    field.printField();
                }
                default -> {
                }
            }
        }
    }

    // asking for valid ship coordinates, checking if they're valid in field, if yes then assigning values
    public void askForCoordinates(Field field) {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        String coord1 = "";
        String coord2 = "";
        while(!check) {
            coord1 = scanner.next();
            coord2 = scanner.next();


            check = field.checkSpace(coord1, coord2, shipLength());
        }
        field.updateField(coord1, coord2);

        switch (shipsPlaced) {
            case 0 -> {
                field.aircraftCarrierCoord1 = coord1;
                field.aircraftCarrierCoord2 = coord2;
            }
            case 1 -> {
                field.battleshipCoord1 = coord1;
                field.battleshipCoord2 = coord2;
            }
            case 2 -> {
                field.submarineCoord1 = coord1;
                field.submarineCoord2 = coord2;
            }
            case 3 -> {
                field.cruiserCoord1 = coord1;
                field.cruiserCoord2 = coord2;
            }
            case 4 -> {
                field.destroyerCoord1 = coord1;
                field.destroyerCoord2 = coord2;
            }
        }
    }

    public int shipLength() {
        if(this.shipsPlaced == 0) {
            return 5;
        } else if (this.shipsPlaced == 1) {
            return 4;
        } else if (this.shipsPlaced == 2 || this.shipsPlaced == 3) {
            return 3;
        } else if (this.shipsPlaced == 4) {
            return 2;
        } else {
            return -1;
        }
    }

}
