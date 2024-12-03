package Controllers;

import Databases.Database;
import Databases.TicketDatabase;
import Person.Person;
import Tickets.Ticket;

public class TicketController {

    protected TicketDatabase ticketDatabade ;

    public TicketController(TicketDatabase database){
        ticketDatabade = database ;

    }




    public void AddTicket( Ticket ticket){

        ticketDatabade.addTicket(ticket);
    }


}
