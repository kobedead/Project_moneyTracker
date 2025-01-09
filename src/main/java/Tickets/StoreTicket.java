package Tickets;


import Person.Person;

public class StoreTicket extends Ticket {


    public StoreTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String ticketDisc() {
        return "StoreTicket";
    }


}
