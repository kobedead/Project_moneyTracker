package controller;

import Controllers.TicketController;
import Databases.Database;
import Factory.TicketFactory;
import Iterator.PersonDBIterator;
import Iterator.TicketDBIterator;
import Tickets.Ticket;

import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.Test;


@ExtendWith(MockitoExtension.class)
public class ticketControllerUTest {


    public ticketControllerUTest()
    {

    }

    @Before
    public void initialize()
    {

    }


    @Test
    public void t_addTicketToDB() throws Exception
    {
        // Create mock objects for database and ticket
        Database<Ticket> mock_db = Mockito.mock(Database.class);

        Ticket mock_ticket = Mockito.mock(Ticket.class);

        TicketDBIterator mock_ticketIterator = Mockito.mock(TicketDBIterator.class);
        PersonDBIterator mock_personIterator = Mockito.mock(PersonDBIterator.class);

        TicketFactory ticketFactory = Mockito.mock(TicketFactory.class);


        TicketController controllerUnderTest = new TicketController(mock_db , ticketFactory , mock_personIterator , mock_ticketIterator);
        //call add
        controllerUnderTest.add(mock_ticket);
        //check if add got only called once
        Mockito.verify(mock_db, Mockito.times(1)).add(mock_ticket);
    }

    @Test
    public void t_removeTicketToDB() throws Exception
    {
        // Create mock objects for database and ticket
        Database<Ticket> mock_db = Mockito.mock(Database.class);

        Ticket mock_ticket = Mockito.mock(Ticket.class);

        TicketDBIterator mock_ticketIterator = Mockito.mock(TicketDBIterator.class);
        PersonDBIterator mock_personIterator = Mockito.mock(PersonDBIterator.class);

        TicketFactory ticketFactory = Mockito.mock(TicketFactory.class);


        TicketController controllerUnderTest = new TicketController(mock_db , ticketFactory , mock_personIterator , mock_ticketIterator);
        //call add
        controllerUnderTest.remove(mock_ticket);
        //check if add got only called once
        Mockito.verify(mock_db, Mockito.times(1)).remove(mock_ticket);
    }








}
