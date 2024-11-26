package Tickets;

import Person.Person;

public abstract class Ticket {

    protected Double price ;

    protected Person from ;
    protected Person to ;


    public Ticket( Double price  , Person from , Person to ){
        this.price = price ;
        this.from = from ;
        this.to = to ;

    }



    @Override
    public abstract String toString();





}
