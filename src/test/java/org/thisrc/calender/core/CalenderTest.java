package org.thisrc.calender.core;

import org.junit.Test;
import org.thisrc.calender.core.Calender;
import org.thisrc.calender.core.Participant;
import org.thisrc.calender.core.Slot;

import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CalenderTest {

    @Test
    public void CalenderTest(){
        Stream.iterate(0,e->e+1).limit(10).forEach(i ->{
            Map<Instant, Set<Participant>> map = Calender
                .getMeetingsBetween("2019-09-01","2019-09-30").stream()
                .flatMap(day -> day.getSlots().stream())
                .collect(Collectors.toMap(Slot::getStartTime, Slot::getParticipants));
            map.forEach((k,v)-> System.out.println(k+": "+v));
        });

    }

}
