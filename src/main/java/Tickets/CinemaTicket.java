package Tickets;


import Person.Person;

public class CinemaTicket extends Ticket {


    public CinemaTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String ticketDisc() {
        return "CinemaTicket";
    }


}
