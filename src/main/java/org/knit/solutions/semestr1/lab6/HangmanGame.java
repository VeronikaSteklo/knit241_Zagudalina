package org.knit.solutions.semestr1.lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HangmanGame {
    private static final int ATTEMPTS = 5;

    public void start(String wordsPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<String> dictWords = getWords(wordsPath);
        List<Character> lettersToGuess = initialiseLettersToGuess(dictWords);
        StringBuilder wordToGuess = new StringBuilder();
        for (char c : lettersToGuess) {
            wordToGuess.append(c);
        }
        System.out.println(wordToGuess);

        List<Character> guessedLetters = initialiseGuessedLetters(lettersToGuess.size());
        int attemptsRemain = ATTEMPTS;

        while (!lettersToGuess.equals(guessedLetters)) {
            boolean wasGuessed = false;

            System.out.println("Загаданное слово: " + guessedLetters);

            if (checkIfAllUnique(lettersToGuess)) {
                System.out.println("Слово отгадано!");
                break;
            }

            System.out.println("Введите букву: ");
            Character letter = scanner.nextLine().charAt(0);

            while (lettersToGuess.indexOf(letter) >= 0) {
                wasGuessed = true;
                guessedLetters.set(lettersToGuess.indexOf(letter), letter);
                lettersToGuess.set(lettersToGuess.indexOf(letter), '_');
            }

            if (!wasGuessed) {
                attemptsRemain -= 1;
                if (attemptsRemain == 0) {
                    System.out.println("Неверно! \nПопыток не осталось. Игра провалена!");
                    System.out.println("Загаданное слово: " + wordToGuess);
                    break;
                } else {
                    System.out.println("Неверно! Оставшихся попыток: " + attemptsRemain);
                }
            }
        }
    }

    private List<String> getWords(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (word.length() >= 4)
                words.add(word);
        }
        scanner.close();
        return words;
    }

    private List<Character> initialiseLettersToGuess(List<String> dictWords) {
        Random rnd = new Random();
        List<Character> result = new ArrayList<>();
        for (Character symbol : dictWords.get(rnd.nextInt(dictWords.size())).toCharArray()) {
            result.add(symbol);
        }
        return result;
    }

    private List<Character> initialiseGuessedLetters(int size) {
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add('_');
        }
        return result;
    }

    private boolean checkIfAllUnique(List<Character> letters) {
        Set<Character> uniqueLetters = new HashSet<>(letters);
        return uniqueLetters.size() == 1;
    }
}
