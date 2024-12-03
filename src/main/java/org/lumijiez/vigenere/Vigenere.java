package org.lumijiez.vigenere;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import java.util.Scanner;

public class Vigenere {
    private static final int ALPHABET_SIZE = 31;
    private static final String ROMANIAN_ALPHABET = "AĂÂBCDEFGHIÎJKLMNOPQRSȘTȚUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueProgram = true;

        while (continueProgram) {
            printMenu();
            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    performEncryption(scanner);
                    break;
                case 2:
                    performDecryption(scanner);
                    break;
                case 3:
                    printTabulaRecta();
                    break;
                case 4:
                    continueProgram = false;
                    System.out.println("Program încheiat. La revedere!");
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Cifrul Vigenère pentru Limba Română ===");
        System.out.println("1. Criptare");
        System.out.println("2. Decriptare");
        System.out.println("3. Afișare Tabula Recta");
        System.out.println("4. Ieșire");
        System.out.print("Alegeți opțiunea (1-4): ");
    }

    private static void printTabulaRecta() {
        System.out.println("\n=== Tabula Recta pentru Alfabetul Românesc ===\n");

        String[] headers = new String[ALPHABET_SIZE + 1];
        headers[0] = "K\\M";
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            headers[i + 1] = String.valueOf(ROMANIAN_ALPHABET.charAt(i));
        }

        String[][] data = new String[ALPHABET_SIZE][ALPHABET_SIZE + 1];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            data[i][0] = String.valueOf(ROMANIAN_ALPHABET.charAt(i));
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                int encryptedIndex = (i + j) % ALPHABET_SIZE;
                data[i][j + 1] = String.valueOf(ROMANIAN_ALPHABET.charAt(encryptedIndex));
            }
        }

        Column[] columns = new Column[ALPHABET_SIZE + 1];
        columns[0] = new Column()
                .header(headers[0])
                .with(row -> ((String[])row)[0]);

        for (int i = 1; i <= ALPHABET_SIZE; i++) {
            final int colIndex = i;
            columns[i] = new Column()
                    .header(headers[i])
                    .with(row -> ((String[])row)[colIndex]);
        }

        System.out.println(AsciiTable.getTable(AsciiTable.FANCY_ASCII, columns, data));

        System.out.println("\nInstrucțiuni de utilizare:");
        System.out.println("1. K\\M: K = litera din cheie, M = litera din mesaj");
        System.out.println("2. Pentru a cripta, găsiți litera din mesaj (M) în prima linie");
        System.out.println("3. Găsiți litera din cheie (K) în prima coloană");
        System.out.println("4. Intersecția liniei K cu coloana M este litera criptată");
    }


    private static int getValidChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Vă rugăm introduceți un număr!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void performEncryption(Scanner scanner) {
        scanner.nextLine();

        String key = getValidKey(scanner);

        System.out.print("Introduceți mesajul de criptat: ");
        String message = getValidMessage(scanner);

        String encryptedText = encrypt(message, key);
        System.out.println("\nTextul criptat: " + encryptedText);

        System.out.println("\nInformații despre procesul de criptare:");
        System.out.println("Lungimea cheii: " + key.length());
        System.out.println("Lungimea mesajului original: " + message.length());
        System.out.println("Lungimea mesajului criptat: " + encryptedText.length());
    }

    private static void performDecryption(Scanner scanner) {
        scanner.nextLine();

        String key = getValidKey(scanner);
        System.out.print("Introduceți textul criptat: ");
        String encryptedMessage = getValidMessage(scanner);

        String decryptedText = decrypt(encryptedMessage, key);
        System.out.println("\nTextul decriptat: " + decryptedText);

        System.out.println("\nInformații despre procesul de decriptare:");
        System.out.println("Lungimea cheii: " + key.length());
        System.out.println("Lungimea textului criptat: " + encryptedMessage.length());
        System.out.println("Lungimea textului decriptat: " + decryptedText.length());
    }

    private static String getValidKey(Scanner scanner) {
        String key;
        do {
            System.out.print("Introduceți cheia (minim 7 caractere): ");
            key = preprocessText(scanner.nextLine());

            if (key.length() < 7) {
                System.out.println("Eroare: Cheia trebuie să aibă cel puțin 7 caractere!");
                continue;
            }

            if (isValidRomanianText(key)) {
                System.out.println("Eroare: Cheia poate conține doar litere românești!");
                continue;
            }

            break;
        } while (true);

        return key;
    }

    private static String getValidMessage(Scanner scanner) {
        String message;
        do {
            message = preprocessText(scanner.nextLine());

            if (message.isEmpty()) {
                System.out.println("Eroare: Mesajul nu poate fi gol!");
                System.out.print("Introduceți din nou: ");
                continue;
            }

            if (isValidRomanianText(message)) {
                System.out.println("Eroare: Mesajul poate conține doar litere românești!");
                System.out.print("Introduceți din nou: ");
                continue;
            }

            break;
        } while (true);

        return message;
    }

    private static String preprocessText(String text) {
        return text.replaceAll("\\s+", "").toUpperCase();
    }

    private static boolean isValidRomanianText(String text) {
        for (char c : text.toCharArray()) {
            if (ROMANIAN_ALPHABET.indexOf(c) == -1) {
                return true;
            }
        }
        return false;
    }

    private static String encrypt(String message, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            int messageIndex = ROMANIAN_ALPHABET.indexOf(messageChar);
            int keyIndex = ROMANIAN_ALPHABET.indexOf(keyChar);

            int encryptedIndex = (messageIndex + keyIndex) % ALPHABET_SIZE;
            encryptedText.append(ROMANIAN_ALPHABET.charAt(encryptedIndex));
        }

        return encryptedText.toString();
    }

    private static String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < encryptedText.length(); i++) {
            char encryptedChar = encryptedText.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            int encryptedIndex = ROMANIAN_ALPHABET.indexOf(encryptedChar);
            int keyIndex = ROMANIAN_ALPHABET.indexOf(keyChar);

            int decryptedIndex = (encryptedIndex - keyIndex + ALPHABET_SIZE) % ALPHABET_SIZE;
            decryptedText.append(ROMANIAN_ALPHABET.charAt(decryptedIndex));
        }

        return decryptedText.toString();
    }
}