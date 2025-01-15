package Tickets;

import Person.Person;

public class RestaurantTicket extends Ticket {


    public RestaurantTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }


    @Override
    public String ticketDisc() {
        return "RestaurantTicket";
    }


}
