package ru.netology.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.AviaSouls;
import ru.netology.repository.TicketTimeComparator;

class AviaSoulsTest {
    AviaSouls souls = new AviaSouls();
    TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
    Ticket ticket1 = new Ticket("Moscow", "Erevan", 40_000, 18, 16);
    Ticket ticket2 = new Ticket("Nizniy Novgorod", "Moscow", 5_000, 23, 4);
    Ticket ticket3 = new Ticket("Moscow", "Vladivostok", 60000, 11, 18);
    Ticket ticket4 = new Ticket("Nizniy Novgorod", "St.Peterburg", 95000, 14, 19);
    Ticket ticket5 = new Ticket("Nizniy Novgorod", "Moscow", 40_000, 5, 10);
    Ticket ticket6 = new Ticket("Sochi", "Moscow", 6_000, 8, 15);//7
    Ticket ticket7 = new Ticket("Sochi", "Moscow", 12_000, 23, 12);//13
    Ticket ticket8 = new Ticket("Sochi", "Moscow", 30_000, 11, 22);//11
    Ticket ticket9 = new Ticket("Sochi", "Moscow", 18_000, 5, 13);//8
    Ticket ticket10 = new Ticket("Sochi", "Moscow", 23_000, 7, 11);//4

    @BeforeEach
    public void addTicket() {
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);
        souls.add(ticket7);
        souls.add(ticket8);
        souls.add(ticket9);
        souls.add(ticket10);

    }

    @Test // провера что стоимость 2 билета меньше стоимости 5 билета
    public void shouldCompareByPriceLess() {
        Assertions.assertEquals(-1, ticket2.compareTo(ticket5));
    }

    @Test // проверка что стоимость 3 билета больше чем стоимость 1 билета
    public void shouldCompareByPriceMore() {
        Assertions.assertEquals(1, ticket3.compareTo(ticket1));
    }

    @Test // проверка что стоимости 1 и 5 билетов равны
    public void shouldCompareByPriceEquals() {
        Assertions.assertEquals(0, ticket1.compareTo(ticket5));
    }

    @Test
    public void shouldFindOneTicket() {
        Ticket[] expected = {ticket1};
        Ticket[] actual = souls.search("Moscow", "Erevan");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoTicket() {
        Ticket[] expected = {ticket2, ticket5};
        Ticket[] actual = souls.search("Nizniy Novgorod", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotingFind() {
        Ticket[] expected = {};
        Ticket[] actual = souls.search("Kirov", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTimeLess() {
        Assertions.assertEquals(-1, ticketTimeComparator.compare(ticket3, ticket1));
    }

    @Test
    public void shouldCompareTimeMore() {
        Assertions.assertEquals(1, ticketTimeComparator.compare(ticket3, ticket2));
    }

    @Test
    public void shouldCompareTimeEquals() {
        Assertions.assertEquals(0, ticketTimeComparator.compare(ticket2, ticket5));
    }

    @Test
    public void shouldSortTickets() {
        Ticket[] expected = {ticket10, ticket6, ticket9, ticket8, ticket7};
        Ticket[] actual = souls.searchAndSortBy("Sochi", "Moscow", ticketTimeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsFind() {
        Ticket[] expected = {};
        Ticket[] actual = souls.searchAndSortBy("Kirov", "Moscow", ticketTimeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortOneTicket() {
        Ticket[] expected = {ticket3};
        Ticket[] actual = souls.searchAndSortBy("Moscow", "Vladivostok", ticketTimeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
