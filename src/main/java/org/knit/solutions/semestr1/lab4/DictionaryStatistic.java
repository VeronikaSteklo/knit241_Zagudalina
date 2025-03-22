package org.knit.solutions.semestr1.lab4;

import java.util.Arrays;
import java.util.Random;

public class DictionaryStatistic {
    private String[] words;
    private int dictionarySize; // Количество слов
    private int polindrom; // Количество слов полиндромов
    private int maxWordLength; // маскимальная длина слова в словаре
    private int minWordLength; // минимальная длина слова в словаре
    private char[] alphabet; // буквы алфавита
    private int[] frequency; //частота букв в словаре (в кадой ячейке хранит частоту букв, а индрес - это позиция буквы в alpabet)

    public DictionaryStatistic(String[] words, char[] alphabet) {
        this.words = words;
        this.alphabet = alphabet;

        this.dictionarySize = words.length;

        // Polindrom
        for (String word : words) {
            if (Arrays.equals(reverseCharArray(word.toCharArray()), word.toCharArray())) {
                polindrom += 1;
            }
        }

        // Max and Min lengths
        maxWordLength = -1;
        minWordLength = 9999999;
        for (String word : words) {
            maxWordLength = Math.max(word.length(), maxWordLength);
            minWordLength = Math.min(word.length(), minWordLength);
        }

        // Freq
        frequency = new int[alphabet.length];
        for (String word : words) {
            for (char letter : word.toCharArray()) {
                int index = findIndexOfLetterInCharArray(alphabet, letter);
                if (index >= 0) {
                    frequency[index] += 1;
                }
            }
        }
    }

    private char[] reverseCharArray(char[] originalArray) {
        char[] result = originalArray.clone();
        for (int i = 0; i < originalArray.length / 2; i++) {
            result[i] = originalArray[originalArray.length - i - 1];
        }
        return result;
    }

    private int findIndexOfLetterInCharArray(char[] array, char charToFind) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == charToFind) {
                return i;
            }
        }
        return -1;
    }

    private int countLettersInCharArray(char[] array, char charToFind) {
        int result = 0;
        for (char c : array) {
            result += c == charToFind ? 1 : 0;
        }
        return result;
    }

    // получить случайное слово из словаря
    public String getRandomWord() {
        Random rnd = new Random();
        return words[rnd.nextInt(dictionarySize)];
    }

    public void printSymbolsStat() {
        for (int i = 0; i < alphabet.length; i++) {
            System.out.println(alphabet[i] + " - " + frequency[i]);
        }
    }

    public void game(String originalWord) {
        for (String wordToCheck : words) {
            boolean isCorrect = true;

            if (wordToCheck.equals(originalWord) || wordToCheck.length() > originalWord.length())
                continue;

            for (char letterToCheck : wordToCheck.toCharArray()) {
                if ((countLettersInCharArray(originalWord.toCharArray(), letterToCheck)) == 0 ||
                        countLettersInCharArray(wordToCheck.toCharArray(), letterToCheck) > countLettersInCharArray(originalWord.toCharArray(), letterToCheck)) {
                    isCorrect = false;
                    break;
                }
            }

            if (isCorrect)
                System.out.println(wordToCheck);
        }
    }

    public String[] getWords() {
        return words;
    }

    public int getDictionarySize() {
        return dictionarySize;
    }

    public int getPolindrom() {
        return polindrom;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public char[] getAlphabet() {
        return alphabet;
    }

    public int[] getFrequency() {
        return frequency;
    }
}
