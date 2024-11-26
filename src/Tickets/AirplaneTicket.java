package Tickets;

import Person.Person;

public class AirplaneTicket extends Ticket {


    public AirplaneTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String toString() {
        return String.format("AirplaneTicket , price : " + price + " , from  : " + from  + " , to  : "+to );
    }


}
