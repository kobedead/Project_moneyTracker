package Tickets;


import Person.Person;

public class FinalTicket extends Ticket {


    public FinalTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String ticketDisc() {
        return "FinalTicket";
    }


}
