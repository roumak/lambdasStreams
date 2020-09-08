
package org.thisrc.calender;

import org.junit.Assert;
import org.junit.Test;
import org.thisrc.calender.core.Day;
import org.thisrc.calender.core.Participant;
import org.thisrc.calender.core.Slot;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ExampleTestRefactored {

    Example schedule = new Example();

    @Test
    public void simpletest(){
        List<Day> days=schedule.findMeetingsBetween("2020-09-01", "2020-09-07");

        simpletest(days, day-> schedule.simpleStartTimeInstantSlotMap_byloop(day) );
        simpletest(days, day-> schedule.simpleStartTimeInstantSlotMap_byStream(day) );
    }


    public void simpletest( List<Day> days, Function<List<Day>,Map<Instant, Slot>> function){
        Map<Instant, Slot> loopMap =function.apply(days);
        loopMap.forEach((k,v)->System.out.println(k+":=="+v));
        System.out.println("");
    }





    /**
     * Find the HashMap of instant and participants list of meetings hosted by the given person
     */
    @Test
    public void scheduleTest_loop() {
        List<Day> days = schedule.findMeetingsBetween("2020-09-07", "2020-09-13");
        days.forEach(System.out::println);

        System.out.println("--------------test-------------------");
        test(days, dayList-> schedule.isThereAsHost_byloop(dayList,"Roumak Chakraborty"));
        test(days, dayList ->schedule.isThereAsHost_byStream(dayList, "Roumak Chakraborty"));

    }

    public void test(List<Day> days, Function<List<Day>, Map<Instant, Set<Participant>>> function) {
        Map<Instant, Set<Participant>> scheduleMap= function.apply(days);
        boolean isThereInParticipant = scheduleMap
                .values()
                .stream()
                .flatMap(Collection::stream)
                .anyMatch(participant -> participant.getName().equals("Roumak Chakraborty"));
        Assert.assertFalse(isThereInParticipant);
        scheduleMap.forEach((k,v)-> System.out.println(k+":== "+v));
        System.out.println(scheduleMap.size());
    }
}