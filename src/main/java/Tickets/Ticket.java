package Tickets;


import Person.Person;

public abstract class Ticket {

    protected Double price ;

    protected Person from ;
    protected Person to ;


    public Ticket(Double price  , Person from , Person to ){
        this.price = price ;
        this.from = from ;
        this.to = to ;

    }


    public Double getPrice(){
        return price;
    }

    public Person getFrom(){return from;}

    public Person getTo(){return to;}


    @Override
    public abstract String toString();





}
