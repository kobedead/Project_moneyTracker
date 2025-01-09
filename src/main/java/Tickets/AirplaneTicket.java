package Tickets;


import Person.Person;

public class AirplaneTicket extends Ticket {


    public AirplaneTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String ticketDisc() {
        return "AirplaneTicket";
    }


}
