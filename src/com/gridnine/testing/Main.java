package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        List<Flight> filteredList1 = new FlightFilter(flightList).afterDeparted(LocalDateTime.now());
        int counter = 1;
        for (Flight flight : filteredList1) {
            System.out.print(counter++ +") ");
            System.out.println(flight.toString());
        }
        counter=1;
        System.out.println("____________________________________");
        List<Flight> filteredList2 = new FlightFilter(flightList).afterArrival(LocalDateTime.now().plusDays(3).minusHours(6));
        for (Flight flight : filteredList2) {
            System.out.print(counter++ +") ");
            System.out.println(flight.toString());
        }
        counter=1;
        System.out.println("____________________________________");
        List<Flight> flightList3 = new FlightFilter(flightList).timeOnGround(2l, false);
        for (Flight flight: flightList3) {
            System.out.print(counter++ +") ");
            System.out.println(flight.toString());
        }
        ArrayList arrayList =  new ArrayList();
        LinkedList linkedList = new LinkedList();

    }
}
