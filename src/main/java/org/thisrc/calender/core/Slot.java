package org.thisrc.calender.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Set;


@Builder
@ToString
@Getter
@Setter
public class Slot {
   private Instant startTime;
   private String meetingHeading;
   private Participant host;
   private Set<Participant> participants;
   private Instant endTime;
   private String details;
   private boolean isAccepted;
}
