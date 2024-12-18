package cinema;
import java.util.Scanner;

public class Cinema {
    public static int rows;
    public static int seats;

    public static int calculateProfit(int row, int seat) {
        if (seat * row <= 60) {
            return (seat * row * 10);
        } else {
            int firstHalf = row / 2;
            int secondHalf = row - firstHalf;
            int firstHalfPrice = firstHalf * seat * 10;
            int secondHalfPrice = secondHalf * seat * 8;
            return (firstHalfPrice + secondHalfPrice);
        }
    }
    public static int calculatePrice(int row, int rows, int seats) {
        if (seats * rows <= 60) {
            return 10;
        } else {
            if (row <= rows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public static void printSeats(int rows, int seats, char[][] cinema) {

        for(int j = 1; j <= seats; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for(int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < seats; j++) {
                if(cinema[i-1][j] == 'B') {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
    public static char[][] welcome(Scanner input) {
        System.out.println("Enter the number of rows:");
        rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = input.nextInt();
        return new char[rows][seats];
    }

    public static void displayMenu(Scanner input) {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner input = new Scanner(System.in);

        char[][] cinema = welcome(input);
        int income = calculateProfit(cinema.length, cinema[0].length);
        int taken = 0;
        int inflow = 0;

        displayMenu(input);

        int choice = input.nextInt();

        while (choice != 0) {
            if (choice == 1) {
                System.out.print("Cinema:\n  ");
                printSeats(rows, seats, cinema);
            }

            if (choice == 2) {
                System.out.println("Enter a row number:");
                int userRow = input.nextInt();
                System.out.println("Enter a seat number in that row:");
                int userSeat = input.nextInt();

                while (!(userRow >= 0 && userRow <= rows) || !(userSeat >= 0 && userSeat <= seats)
                        || cinema[userRow - 1][userSeat - 1] == 'B') {

                    if (!(userRow >= 0 && userRow <= rows) || !(userSeat >= 0 && userSeat <= seats)) {
                        System.out.println("Wrong input!");
                    } else {
                        System.out.println("That ticket has already been purchased!");
                    }
                    System.out.println("Enter a row number:");
                    userRow = input.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    userSeat = input.nextInt();
                }
                cinema[userRow - 1][userSeat - 1] = 'B';
                taken++;

                System.out.println("Ticket price: $" + calculatePrice(userRow, rows, seats) + "\n");
                inflow += calculatePrice(userRow, rows, seats);
            }

            if (choice == 3) {
                System.out.println("Number of purchased tickets: " + taken);
                System.out.printf("Percentage: %.2f%%\n", (double)taken * 100 / (rows * seats));
                System.out.println("Current Income: $" + inflow);
                System.out.println("Total income: $" + income + "\n");
            }

            displayMenu(input);
            choice = input.nextInt();
        }

    }
}
