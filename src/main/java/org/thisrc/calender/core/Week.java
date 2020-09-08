package org.thisrc.calender.core;

import java.util.Date;
import java.util.List;

public class Week {
    Date startDate;
    Date endDate;
    List<Day> day;

    Date getStartDate() {
        return startDate;
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    Date getEndDate() {
        return endDate;
    }

    void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    List<Day> getDay() {
        return day;
    }

    void setDay(List<Day> day) {
        this.day = day;
    }
}
