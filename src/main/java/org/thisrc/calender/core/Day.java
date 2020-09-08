package org.thisrc.calender.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@ToString
@Getter
@Setter
public class Day {
    private String dayOfWeek;
    private LocalDate date;
    private Set<Slot> slots;

    public Day(LocalDate date, Set<Slot> slots){
        this.date = date;
        this.dayOfWeek=date.getDayOfWeek().toString();
        this.slots=slots;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

}
