package Tickets;

import Person.Person;

public class CinemaTicket extends Ticket {


    public CinemaTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String toString() {
        return String.format("CinemaTicket , price : %.2f  from  %s  to %s" , price , from.getName() , to.getName());
    }

}
