package org.thisrc.calender.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Populator {

    private static final Random random = new Random();
    private static final Path participantFilePath = Paths.get("src/main/resources/participants");
    private static final Path headingFilePath = Paths.get("src/main/resources/meetingheadings");
    private static final int MAX_DAILY_SLOTS = 5;
    private static final int MAX_MEETING_HOURS= 2;
    private static final List<Integer> availableTimeSlots= Arrays.asList(8,12,15,18,21);


    private static final List<Participant> participantList =populateParticipants(participantFilePath);
    private static final List<String> headingList = populateHeadings(headingFilePath);

//    private static final List<String> headingList = fileReader(headingFilePath, str-> str);
//    private static final List<Participant> participantList = fileReader(participantFilePath, Participant::new);
//    protected static <D> List<D> fileReader(Path path, Function<String, D> func){
//        List<D> list;
//        try {
//            list = Files.lines(path)
//                    .map(func)
//                    .collect(Collectors.toList());
//        } catch (IOException e ){
//            throw new RuntimeException("file IO exception: "+""+ e.getMessage());
//        }
//        return list;
//    }

    protected static List<Participant> populateParticipants(Path participantsFilepath) {
        List<Participant> allParticipants;
        try {
             allParticipants = Files.lines(participantsFilepath)
                     .map(Participant::new)
                     .collect(Collectors.toList());
        } catch (IOException e){
           throw new RuntimeException("file IO exception on file: "+ e.getMessage());
        }
        return allParticipants;
    }

    protected static List<String> populateHeadings(Path headingFilePath) {
        List<String> allHeadings;
        try{
            allHeadings = Files.lines(headingFilePath)
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException("file IO exception on file: "+e.getMessage());
        }
        return allHeadings;
    }


    static List<Day> populateBetweenDates(LocalDate start , LocalDate end) {
        List<Day> days= new ArrayList<>();

        for (LocalDate localDate = start; localDate.compareTo(end) < 1; localDate=localDate.plusDays(1)){
            final LocalDate finalLocalDate = localDate;
            List<Integer> tem=new ArrayList<>(availableTimeSlots);
            List<Slot> slot = Stream.iterate(0, a-> a+1)
                    .limit(random.nextInt(MAX_DAILY_SLOTS))
                    .map( l-> Populator.setSlot(finalLocalDate,tem ))
                    .collect(Collectors.toList());

            Day day = new Day(localDate,new HashSet<>(slot));
            days.add(day);
        }
        return days;
    }

    private static Slot setSlot(LocalDate today,List<Integer> timeslots ){
        Participant host = randomHost();
        Instant randomStartTime = randomTime(today, timeslots);
        return Slot.builder()
                .meetingHeading(headingList.get(random.nextInt(headingList.size()-1)))
                .startTime(randomStartTime)
                .endTime(randomEndTime(randomStartTime))
                .host(host)
                .isAccepted(random.nextBoolean())
                .participants(randomParticipant(host))
                .build();
    }

    private static Instant randomEndTime(Instant randomStartTime) {
        return randomStartTime.plus(random.nextInt(MAX_MEETING_HOURS),ChronoUnit.HOURS);
    }

    private static Instant randomTime(LocalDate today, List<Integer> availableTimeSlots) {
        int index=random.nextInt(availableTimeSlots.size()-1);
        long start = availableTimeSlots.get(index);
        availableTimeSlots.remove(index);
        return today.atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .plus(start, ChronoUnit.HOURS);
    }

    private static Participant randomHost(){
        final int num =  random.nextInt( participantList.size());
        return participantList.get(num);
    }

    static Set<Participant> randomParticipant(Participant host){
        final int size =  random.nextInt( participantList.size() );
        if(size == 0) {
          return Collections.emptySet();
        }
        List<Participant> copyOfParticipantList = new ArrayList<>(participantList);
        copyOfParticipantList.remove(host);
        Collections.shuffle(copyOfParticipantList);
        Set<Participant> participants=new HashSet<>(copyOfParticipantList.subList(0, size));
        return addDefaultParticipant(host, participants);
    }

    private static Set<Participant> addDefaultParticipant(Participant host, Set<Participant> participants) {
        Participant defaultParticipant= getDefautparticiant(participants);
        if (!defaultParticipant.equals(host)){
            participants.add(defaultParticipant);
        }
        return participants;
    }

    private static Participant getDefautparticiant(Set<Participant> participants) {
       return participantList.stream()
                .filter(p->p.getName().equals("Roumak Chakraborty"))
                .findFirst()
                .orElseGet(()->participantList.get(0));
    }

    static boolean randomAcceptance() {
        return random.nextBoolean();
    }




}
