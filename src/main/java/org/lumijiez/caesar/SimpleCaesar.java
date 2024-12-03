package org.lumijiez.caesar;

import org.lumijiez.Utils;

public class SimpleCaesar {
    public static String encode(int key, String content) {
        StringBuilder encoded = new StringBuilder();

        String fixedContent = content.replaceAll("\\s","");

        for (char ch : fixedContent.toCharArray()) {
            encoded.append(Utils.toNthLetter((Utils.getPos(ch) + key) % Utils.ALPHABET.length()));
        }
        return encoded.toString();
    }

    public static String decode(int key, String content) {
        StringBuilder decoded = new StringBuilder();

        for (char ch : content.toCharArray()) {
            int currentPosition = Utils.getPos(ch);

            int newPosition = (currentPosition - key) % Utils.ALPHABET.length();

            if (newPosition < 0) {
                newPosition += Utils.ALPHABET.length();
            }

            decoded.append(Utils.toNthLetter(newPosition));
        }

        return decoded.toString();
    }

}
