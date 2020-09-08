package org.thisrc;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MutabilityIsBad {

    public static void main(String[] args) {
        Set<Date> dates = new HashSet<>();
        Date today  = new Date();
        dates.add(today);
        today.setTime(1100001020);

        System.out.println(dates.contains(today));
    }
}
