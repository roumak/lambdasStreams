package org.thisrc.calender;

import org.thisrc.calender.core.Calender;
import org.thisrc.calender.core.Day;
import org.thisrc.calender.core.Participant;
import org.thisrc.calender.core.Slot;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ChangedScheduleExample {



    public void demo2(String person) {
        List<Day> days = Calender.getMeetingsBetween("2019-01-01", "2019-02-10");

        Map<Instant, Set<Participant>> functionalmap = days.stream()
                .map(Day::getSlots)
                .flatMap(Collection::stream)
                .filter(s -> s.getParticipants().stream().anyMatch(p -> p.getName().equals(person)))
                .filter(Slot::isAccepted)
                .collect(Collectors.toMap(Slot::getStartTime, Slot::getParticipants));

        functionalmap.forEach((k, v) -> System.out.println(k + ": " + v));


        Map<Instant, Set<Participant>> impMap = new HashMap<>();
        for (int i = 0; i < days.size(); i++) {
            Day day = days.get(i);
            Set<Slot> slots = day.getSlots();
            for (Slot slot : slots) {
                boolean hasme = false;
                if (slot.isAccepted()) {
                    for (Participant part : slot.getParticipants()) {
                        if (part.getName().equals(person)) {
                            hasme = true;
                            break;
                        }
                    }
                }
                if (hasme) {
                    impMap.put(slot.getStartTime(), slot.getParticipants());
                }
            }
        }

        impMap.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("days: " + days.size());
        System.out.println("imperative map size: " + impMap.size());
        System.out.println("functional map size: " + functionalmap.size());

    }
}
