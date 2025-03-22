package org.knit.solutions.semestr1.additional;

import java.util.HashSet;
import java.util.Set;

public class EventAnalyser {
    public void start() {
        Set<String> sportsParticipants = Set.of("Alice", "Bob", "Charlie");
        Set<String> musicParticipants = Set.of("Bob", "Dave", "Eve");

        System.out.println("Участники спортивного мероприятия: ");
        printSet(sportsParticipants);
        System.out.println();
        System.out.println("Участники музыкального мероприятия: ");
        printSet(musicParticipants);
        System.out.println();

        System.out.println("Объединение множеств");
        Set<String> joinResult = new HashSet<>(sportsParticipants);
        joinResult.addAll(musicParticipants);
        printSet(joinResult);
        System.out.println();

        System.out.println("Пересечение множеств");
        Set<String> intersectionResult = new HashSet<>(sportsParticipants);
        intersectionResult.retainAll(musicParticipants);
        printSet(intersectionResult);
        System.out.println();

        System.out.println("Участники только спортивного мероприятия");
        Set<String> onlySportsResult = new HashSet<>(sportsParticipants);
        onlySportsResult.removeAll(musicParticipants);
        printSet(onlySportsResult);
        System.out.println();

        System.out.println("Участники только музыкального мероприятия");
        Set<String> onlyMusicResult = new HashSet<>(musicParticipants);
        onlyMusicResult.removeAll(sportsParticipants);
        printSet(onlyMusicResult);
        System.out.println();
    }

    private static void printSet(Set<String> set) {
        for (String sportsParticipant : set) {
            System.out.println(sportsParticipant);
        }
    }
}
