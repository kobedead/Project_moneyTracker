package Databases;
import Person.Person;
import Tickets.Ticket;

import java.security.KeyPair;
import java.security.KeyStore;
import java.util.*;

public class TicketDatabase extends Database{

    private List<Ticket> ticketList ;

    public TicketDatabase(){
        ticketList = new ArrayList<>();

    }


    public void addTicket(Ticket ticket) {

        support.firePropertyChange("TicketAdded" , null , ticket);
        ticketList.add(ticket);

    }
//TicketDeleted
    public void removeTicket(Ticket ticket){

        support.firePropertyChange("TicketDeleted" , null , ticket);
        ticketList.remove(ticket);

    }




    public void CalculateDept(Person[] persons){





    }







}
