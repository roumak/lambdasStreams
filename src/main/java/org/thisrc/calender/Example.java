package org.thisrc.calender;

import org.thisrc.calender.core.Calender;
import org.thisrc.calender.core.Day;
import org.thisrc.calender.core.Participant;
import org.thisrc.calender.core.Slot;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Example {

    public List<Day> findMeetingsBetween(String startDate, String endDate) {
        return Calender.getMeetingsBetween(startDate, endDate);
    }

    public Map<Instant,Slot> simpleStartTimeInstantSlotMap_byloop(List<Day> days) {
        Map<Instant, Slot> impMap = new HashMap<>();
        for (Day day :days){
            for(Slot slot:day.getSlots()){
                impMap.put(slot.getStartTime(), slot);
            }
        }
        return impMap;
    }

    public Map<Instant,Slot> simpleStartTimeInstantSlotMap_byStream(List<Day> days) {
        return days.stream()
                .flatMap(day -> day.getSlots().stream())
                .collect(Collectors.toMap(Slot::getStartTime, v -> v ));
    }


    public Map<Instant, Set<Participant>> isThereAsHost_byloop(List<Day> days, String person) {

        Map<Instant, Set<Participant>> impMap = new HashMap<>();
        for (int i = 0; i < days.size(); i++) {
            Day day = days.get(i);
            Set<Slot> slots = day.getSlots();
            for (Slot slot : slots) {
                if (slot.getHost().getName().equals(person) && slot.isAccepted()) {
                    impMap.put(slot.getStartTime(), slot.getParticipants());
                }
            }
        }
        return impMap;
    }



    public Map<Instant, Set<Participant>> isThereAsHost_byStream(List<Day> days, String person) {

        return days.stream()
                .map(Day::getSlots)
                .flatMap(Collection::stream)
                .filter(slot -> slot.getHost().getName().equals(person))
                .filter(Slot::isAccepted)
                .collect(Collectors.toMap(Slot::getStartTime, Slot::getParticipants));
    }

}
