package org.lumijiez;

import org.lumijiez.caesar.DoubleCaesar;
import org.lumijiez.caesar.SimpleCaesar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Simple Caesar Cipher");
            System.out.println("2. Double Caesar Cipher");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Simple Caesar Cipher");
                    System.out.println("1. Encode");
                    System.out.println("2. Decode");
                    System.out.print("Select option: ");
                    int simpleChoice = scanner.nextInt();
                    System.out.print("Enter the key (an integer): ");
                    int simpleKey = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the content: ");
                    String simpleContent = scanner.nextLine();

                    if (simpleKey <= 0) {
                        System.out.println("Invalid key. Please enter a positive integer.");
                        break;
                    }

                    if (simpleChoice == 1) {
                        String encodedSimple = SimpleCaesar.encode(simpleKey, simpleContent);
                        System.out.println("Encoded: " + encodedSimple);
                    } else if (simpleChoice == 2) {
                        String decodedSimple = SimpleCaesar.decode(simpleKey, simpleContent);
                        System.out.println("Decoded: " + decodedSimple);
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;

                case 2:
                    System.out.println("Double Caesar Cipher");
                    System.out.print("Enter the key (an integer): ");
                    int doubleKey = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the keyword (more than 7 letters A-Z): ");
                    String doubleKeyWord = scanner.nextLine();

                    if (!isValidKeyword(doubleKeyWord)) {
                        System.out.println("Invalid keyword. Please ensure it is longer than 7 characters and contains only letters A-Z.");
                        break;
                    }

                    System.out.print("Enter the content: ");
                    String doubleContent = scanner.nextLine();

                    String encodedDouble = DoubleCaesar.encode(doubleKey, doubleKeyWord, doubleContent);
                    System.out.println("Encoded: " + encodedDouble);
                    break;

                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
            System.out.println("================");
        }

        scanner.close();
    }

    private static boolean isValidKeyword(String keyword) {
        return keyword.length() > 7 && keyword.matches("[A-Z]+");
    }
}
