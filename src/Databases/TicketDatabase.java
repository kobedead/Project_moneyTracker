package Databases;
import Person.Person;
import Tickets.Ticket;

import java.security.KeyPair;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TicketDatabase extends Database{

    private List<Ticket> ticketList ;

    public TicketDatabase(){
        ticketList = new ArrayList<>();
    }


    public void addTicket(Ticket ticket) {
        support.firePropertyChange("TicketAdded" , null , ticket);
        ticketList.add( ticket);
    }




}
