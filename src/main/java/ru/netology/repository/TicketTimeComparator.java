package ru.netology.repository;

import ru.netology.domain.Ticket;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int flightTime1 = (t1.getTimeTo() - t1.getTimeFrom());
        int flightTime2 = (t2.getTimeTo() - t2.getTimeFrom());
        if (flightTime1 < 0) {
            flightTime1 += 24;
        }
        if (flightTime2 < 0) {
            flightTime2 += 24;
        }

        if (flightTime1 < flightTime2) {
            return -1;
        } else if (flightTime1 > flightTime2) {
            return 1;
        } else {
            return 0;
        }
    }
}