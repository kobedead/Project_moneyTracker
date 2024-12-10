package Tickets;

import Person.Person;

public class FinalTicket extends Ticket {


    public FinalTicket(Double price, Person from, Person to) {
        super(price, from, to);
    }

    @Override
    public String toString() {
        return  String.format("FinalPay , price : " + price + " , from  : " + from  + " , to  : "+to );
    }
}
