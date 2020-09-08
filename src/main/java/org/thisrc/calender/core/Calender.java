package org.thisrc.calender.core;


import java.time.LocalDate;
import java.util.List;

public class Calender {


    private Calender(){}


    public static List<Day> getMeetingsBetween(String start, String end){
        return populate(start,end);
    }

    /**
     *  The ISO date formatter that formats or parses a date without an
     *  offset, such as '2011-12-03'.
     * @param start
     * @param end
     */
    private static List<Day> populate(String start, String end){
      return Populator.populateBetweenDates(LocalDate.parse(start), LocalDate.parse(end));
    }



}
