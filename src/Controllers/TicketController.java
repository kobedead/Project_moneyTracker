package Controllers;

import Databases.Database;
import Databases.TicketDatabase;
import Person.Person;
import Tickets.Ticket;

import java.security.KeyPair;
import java.util.List;

public class TicketController implements Controller<Ticket> {

    protected TicketDatabase ticketDatabase ;

    public TicketController(TicketDatabase database){
        ticketDatabase = database ;

    }



    public List<KeyPair> CalcTotal(){
        return ticketDatabase.CalculateTotal();
    }


    public void add( Ticket ticket){
        ticketDatabase.add(ticket);
    }

    public void remove(Ticket ticket){
        ticketDatabase.remove(ticket);
    }


}
