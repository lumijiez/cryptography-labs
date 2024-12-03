package org.lumijiez;

import java.util.Scanner;
import java.util.Random;

public class DES {
    private static final int[] SHIFT_TABLE = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DES Key Schedule Calculator - Ci and Di Generation");
        System.out.println("-----------------------------------------------");

        System.out.println("Choose input method:");
        System.out.println("1. Random generation");
        System.out.println("2. Manual input");
        int choice = scanner.nextInt();

        String kPlus;

        if (choice == 1) {
            kPlus = generateRandomBits(56);
        } else {
            System.out.println("Enter 56-bit K+ (0s and 1s only):");
            kPlus = scanner.next();

            if (!kPlus.matches("[01]{56}")) {
                System.out.println("Invalid input! Please enter exactly 56 bits (0s and 1s only)");
                return;
            }
        }

        System.out.println("Enter round number i (1-16):");
        int round = scanner.nextInt();

        if (round < 1 || round > 16) {
            System.out.println("Invalid round number! Must be between 1 and 16");
            return;
        }

        String c0 = kPlus.substring(0, 28);
        String d0 = kPlus.substring(28, 56);

        System.out.println("\nInitial values:");
        System.out.println("K+ = " + kPlus);
        System.out.println("C0 = " + c0);
        System.out.println("D0 = " + d0);

        String ci = c0;
        String di = d0;

        System.out.println("\nCalculating shifts for round " + round + ":");

        for (int j = 0; j < round; j++) {
            int shifts = SHIFT_TABLE[j];

            ci = leftCircularShift(ci, shifts);
            di = leftCircularShift(di, shifts);

            System.out.println("\nRound " + (j + 1) + ":");
            System.out.println("Number of left shifts: " + shifts);
            System.out.println("C" + (j + 1) + " = " + ci);
            System.out.println("D" + (j + 1) + " = " + di);
        }

        System.out.println("\nFinal Result for round " + round + ":");
        System.out.println("C" + round + " = " + ci);
        System.out.println("D" + round + " = " + di);
    }

    private static String leftCircularShift(String bits, int positions) {
        positions = positions % bits.length();
        return bits.substring(positions) + bits.substring(0, positions);
    }

    private static String generateRandomBits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(2));
        }

        return sb.toString();
    }
}
