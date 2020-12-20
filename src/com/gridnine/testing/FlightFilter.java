package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightFilter {
    private List<Flight> flightList;

    public FlightFilter(List<Flight> flightList) {
        this.flightList = flightList;
    }


    public List<Flight> beforeDeparted(LocalDateTime dateTime) {
        Stream<Flight> result = flightList.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(dateTime)));
        return result.collect(Collectors.toList());
    }

    public List<Flight> afterDeparted(LocalDateTime dateTime) {
        Stream<Flight> result = flightList.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate().isAfter(dateTime)));
        return result.collect(Collectors.toList());
    }

    public List<Flight> beforeArrival(LocalDateTime dateTime) {
        Stream<Flight> result = flightList.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDate().isBefore(dateTime)));
        return result.collect(Collectors.toList());
    }

    public List<Flight> afterArrival(LocalDateTime dateTime) {
        Stream<Flight> result = flightList.stream().filter(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDate().isAfter(dateTime)));
        return result.collect(Collectors.toList());
    }


    public static Map<Flight, Long> timeOnGround(List<Flight> flightList) {
        Long duration = 0l;
        Map<Flight, Long> result = new LinkedHashMap<>();
        for (Flight flight : flightList) {
            if (flight.getSegments().size() > 1) {
                List<Segment> segmentList = flight.getSegments();
                Segment[] segments = segmentList.toArray(Segment[]::new);
                for (int i = 0; i < segments.length - 1; i++) {
                    Duration newDuration = Duration.between(segments[i].getArrivalDate(), segments[i + 1].getDepartureDate());
                    duration += newDuration.toHours();
                    result.put(flight, duration);
                }

            } else {
                result.put(flight, 0l);
            }
        }
        return result;
    }


    public List<Flight> timeOnGround(Long hours, boolean moreOrLess) {
        Map<Flight, Long> groundTimeMap = timeOnGround(flightList);
        Map<Flight, Long> resultMap = new LinkedHashMap<>();
        if (moreOrLess == true) {
            groundTimeMap.forEach((flight, aLong) -> {
                if (aLong.compareTo(hours) >= 0) {
                    resultMap.put(flight, aLong);
                }
            });
        } else {
            groundTimeMap.forEach((flight, aLong) -> {
                if (aLong.compareTo(hours) <= 0) {
                    resultMap.put(flight, aLong);
                }
            });
        }
        List<Flight> result = new ArrayList<>(resultMap.keySet());
        return result;
    }

}



