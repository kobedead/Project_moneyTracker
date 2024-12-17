package Controllers;

import Databases.TicketDatabase;
import Tickets.Ticket;

import java.util.List;
import java.util.Set;

public class TicketController implements Controller<Ticket> {

    protected TicketDatabase ticketDatabase ;

    public TicketController(TicketDatabase database){
        ticketDatabase = database ;

    }



    public List<Ticket> CalcTotal(){
        return ticketDatabase.CalculateTotal();
    }


    public void add( Ticket ticket){
        ticketDatabase.add(ticket);
    }

    public void remove(Ticket ticket){
        ticketDatabase.remove(ticket);
    }


}
