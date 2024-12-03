package org.lumijiez.caesar;

import org.lumijiez.Utils;

public class DoubleCaesar {
    public static String encode(int key, String keyWord, String content) {
        StringBuilder encoded = new StringBuilder();

        String fixedContent = content.replaceAll("\\s","");

        Utils.ALPHABET = Utils.getDoubleCaesarAlphabet(Utils.toCaesarKey(keyWord));

        for (char ch : fixedContent.toCharArray()) {
            encoded.append(Utils.toNthLetter((Utils.getPos(ch) + key) % Utils.ALPHABET.length()));
        }

        Utils.returnAlphabet();

        return encoded.toString();
    }
}
