package Tickets;

import Person.Person;

public class CinemaTicket extends Ticket {


    public CinemaTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String toString() {
        return String.format("CinemaTicket , price : " + price + " , from  : " + from  + " , to  : "+to );
    }

}
