package org.lumijiez;

import java.util.LinkedHashSet;

public class Utils {

    //public static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
    public static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void returnAlphabet() {
        Utils.ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Utils.ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
    }

    public static String toCaesarKey(String key) {
        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();
        key.chars().forEach(ch -> linkedHashSet.add((char) ch));

        StringBuilder caesarKey = new StringBuilder();
        linkedHashSet.forEach(caesarKey::append);

        return caesarKey.toString().toUpperCase();
    }

    public static char toNthLetter(int n) {
        if (n >= ALPHABET.length()) {
            throw new IllegalArgumentException("n must be between 1 and " + (ALPHABET.length() - 1));
        }

        if (!ALPHABET.contains(String.valueOf(ALPHABET.charAt(n)))) {
            throw new IllegalArgumentException("Please only use letter from this alphabet " + Utils.ALPHABET);
        }
        return ALPHABET.charAt(n);
    }

    public static int getPos(char letter) {
        int index = ALPHABET.indexOf(letter);
        if (index == -1) {
            throw new IllegalArgumentException("Input must be a character in the alphabet: " + ALPHABET);
        }
        return index;
    }

    public static String getDoubleCaesarAlphabet(String key) {
        LinkedHashSet<Character> linkedHashSet = new LinkedHashSet<>();

        for (char c : key.toCharArray()) {
            linkedHashSet.add(c);
        }

        for (char ch : ALPHABET.toCharArray()) {
            linkedHashSet.add(ch);
        }

        StringBuilder newAlphabet = new StringBuilder();

        for (char ch : linkedHashSet) {
            newAlphabet.append(ch);
        }

        return newAlphabet.toString();
    }

}