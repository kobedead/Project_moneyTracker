package Databases;

import Person.Person;
import Tickets.FinalTicket;
import Tickets.Ticket;

import java.util.*;
import Iterator.CustomIterator;
import Iterator.TicketDBIterator;

public class TicketDatabase extends Database<Ticket>{

    private List<Ticket> ticketList ;

    public TicketDatabase(){
        ticketList = new ArrayList<>();

    }

    public CustomIterator<Ticket> createIterator(){
        return new TicketDBIterator(ticketList);
    }


    public void add(Ticket ticket) {

        support.firePropertyChange("TicketAdded" , null , ticket);
        ticketList.add(ticket);
    }
//TicketDeleted
    public void remove(Ticket ticket){

        support.firePropertyChange("TicketDeleted" , null , ticket);
        ticketList.remove(ticket);

    }








}
