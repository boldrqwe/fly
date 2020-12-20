package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;


public class TestFilter {
    List<Flight> flightList2 = new ArrayList<>();
    List<Flight> flightList3 = new ArrayList<>();
    List<Flight> resultFlightList1 = new ArrayList<>();
    List<Flight> resultFlightList2 = new ArrayList<>();
    List<Flight> resultFlightList4 = new ArrayList<>();
    Map<Flight, Long> flightLongMap = new LinkedHashMap<>();
    List<Flight> resultFlightList3 = new ArrayList<>();
    Flight flight1 = new Flight(new ArrayList<>(
            Arrays.asList(new Segment(LocalDateTime.now(),
                    LocalDateTime.now()))));

    Flight flight2 = new Flight(new ArrayList<>(
            Arrays.asList(new Segment(LocalDateTime.now(),
                    LocalDateTime.now()))));

    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
            flightList2.add(new Flight(new ArrayList<>(
                    Arrays.asList(new Segment(LocalDateTime.now().plusHours(i + 2),
                            LocalDateTime.now().plusHours(i + 4))))));

            flightList2.add(new Flight(new ArrayList<>(
                    Arrays.asList(new Segment(LocalDateTime.now().minusHours(i + 2),
                            LocalDateTime.now().minusHours(i + 4))))));

            resultFlightList1.add(new Flight(new ArrayList<>(
                    Arrays.asList(new Segment(LocalDateTime.now().plusHours(i + 2),
                            LocalDateTime.now().plusHours(i + 4))))));

            resultFlightList2.add(new Flight(new ArrayList<>(
                    Arrays.asList(new Segment(LocalDateTime.now().minusHours(i + 2),
                            LocalDateTime.now().minusHours(i + 4))))));
        }
        for (int i = 0; i < 2; i++) {
            flightList3.add(new Flight(new ArrayList<>(
                    Arrays.asList(new Segment(LocalDateTime.now().plusHours(i + 2),
                                    LocalDateTime.now().plusHours(i + 4)),
                            new Segment(LocalDateTime.now().plusHours(i + 5),
                                    LocalDateTime.now().plusHours(i + 6))))));

            resultFlightList4.add(new Flight(new ArrayList<>(
                    Arrays.asList(new Segment(LocalDateTime.now(),
                            LocalDateTime.now())))));
        }
        resultFlightList3.add(flight1);
        resultFlightList3.add(flight2);
        flightLongMap.put(flight1, 0l);
        flightLongMap.put(flight2, 0l);

    }

    @Test
    public void beforeDepartureTest() {
        Assert.assertEquals(resultFlightList2.toString(),
                new FlightFilter(flightList2).beforeDeparted(LocalDateTime.now()).toString());
    }

    @Test
    public void afterDepartureTest() {
        Assert.assertEquals(resultFlightList1.toString(),
                new FlightFilter(flightList2).afterDeparted(LocalDateTime.now()).toString());
    }

    @Test
    public void beforeArrivalTest() {
        Assert.assertEquals(resultFlightList2.toString(),
                new FlightFilter(flightList2).beforeArrival(LocalDateTime.now()).toString());
    }

    @Test
    public void afterArrivalTest() {
        Assert.assertEquals(resultFlightList1.toString(),
                new FlightFilter(flightList2).afterArrival(LocalDateTime.now()).toString());
    }

    @Test
    public void moreOnGroundTest() {
        Assert.assertEquals(flightList3.toString(), new FlightFilter(flightList3)
                .onGroundMoreOrLess(1l, true).toString());
    }

    @Test
    public void lessOnGroundTest() {
        Assert.assertEquals(resultFlightList4.toString(), new FlightFilter(resultFlightList4)
                .onGroundMoreOrLess(2l, false).toString());
    }

    @Test
    public void maxOnGroundTest() {
        Assert.assertEquals(flightLongMap.toString(), new FlightFilter(resultFlightList3)
                .maxOnGround().toString());
    }

}
