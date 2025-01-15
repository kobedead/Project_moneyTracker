package Controllers;

import Databases.Database;
import Iterator.CustomIterator;
import Person.Person;
import Tickets.Ticket;

public class PersonController {

    protected Database<Person> db ;
    protected CustomIterator<Person> personIterator;

    protected CustomIterator<Ticket> ticketCustomIterator;

    public PersonController(Database<Person> database , CustomIterator<Person> personIterator , CustomIterator<Ticket> ticketCustomIterator){

        db = database;
        this.personIterator = personIterator;
        this.ticketCustomIterator = ticketCustomIterator;
    }


    public CustomIterator CreateIterator(){
        return db.createIterator();
    }


    public boolean addByName(String name){

        Boolean alreadyIn = false ;
        while (personIterator.hasNext()) {
            if(personIterator.getElement().getName().equals(name)){
                System.out.println("Error :  person is already in database");
                alreadyIn = true;
            }
        }

        if(!alreadyIn)
            db.add(new Person(name));

    return alreadyIn;
    }


    public void add(Person object) {
        db.add(object);
    }


    public boolean remove(Person person) {

        while(ticketCustomIterator.hasNext()){
            Ticket ticket =  ticketCustomIterator.getElement();

            if((ticket.getTo() == person) || (ticket.getFrom() == person )){
                ticketCustomIterator.reset();
                return false;
            }
        }

        db.remove(person);
        return true;
    }


}
