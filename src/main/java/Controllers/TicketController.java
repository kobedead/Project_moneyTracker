package Controllers;


import Databases.Database;
import Factory.TicketFactory;
import Iterator.CustomIterator;
import Person.Person;
import Tickets.FinalTicket;
import Tickets.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TicketController {

    protected Database<Ticket> ticketDatabase ;
    protected CustomIterator<Person> personIterator;
    protected CustomIterator<Ticket> ticketCustomIterator;

    protected TicketFactory factory;


    public TicketController(Database<Ticket> database , TicketFactory factory, CustomIterator<Person> personCustomIterator, CustomIterator<Ticket> ticketItorator ){
        ticketDatabase = database ;
        this.factory = factory;
        this.personIterator = personCustomIterator;
        this.ticketCustomIterator = ticketItorator;
    }

    public void addSplitEqual(String ticketType , String ticketPriceEntered , Person personPayed){


        if(ticketPriceEntered != null && ticketType != null && personPayed != null) {
            //ticket needs to be created
            while (personIterator.hasNext()) {
                Person person = personIterator.getElement();
                if (person != personPayed)
                    ticketDatabase.add(factory.getTicket(ticketType, Double.parseDouble(ticketPriceEntered) / personIterator.getLenght(), (Person) person, personPayed));
            }
        }
    }


    public void addSplitUnequal(String ticketType , List<String> ticketPriceEntered , Person personPayed ){

        if(ticketPriceEntered.size() == personIterator.getLenght()-1) {
            int otherIndex = 0 ;
            while(personIterator.hasNext()){
                Person person = personIterator.getElement();
                if (person != personPayed) {
                    ticketDatabase.add(factory.getTicket(ticketType, Double.parseDouble(ticketPriceEntered.get(otherIndex)), person , personPayed));
                    otherIndex++;

                }
            }
        }

    }


    public List<Ticket> CalculateTotal() {

        List<Ticket> finalpay = new ArrayList<>();
        HashMap<Person, Double> balances = new HashMap<>();

        while (ticketCustomIterator.hasNext()) {
            Ticket ticket = ticketCustomIterator.getElement();


            balances.put(ticket.getFrom(), (balances.getOrDefault(ticket.getFrom(), 0.0) - ticket.getPrice()));
            balances.put(ticket.getTo(), (balances.getOrDefault(ticket.getTo(), 0.0) + ticket.getPrice()));
        }

        while (true) {


            double largest = 0;
            Person largestP = null;

            double smallest = 0;
            Person smallestP = null;

            for (Person person : balances.keySet()) {



                if (balances.get(person) > largest) {
                    largestP = person;
                    largest = (balances.get(person));
                } else if (balances.get(person) < smallest) {
                    smallestP = person;
                    smallest = (balances.get(person));
                }
            }

            if (-0.01 < smallest && smallest < 0.01 ) {
                if (largest != 0)
                    System.out.println("bad calculatetotal logic");
                return finalpay;
            }

            double payment = Math.min(Math.abs(smallest), largest);
            finalpay.add(new FinalTicket(payment, smallestP, largestP));




            balances.put(largestP, balances.get(largestP) - payment);
            balances.put(smallestP, balances.get(smallestP) + payment);

        }
    }










    public void add( Ticket ticket){
        ticketDatabase.add(ticket);
    }

    public void remove(Ticket ticket){
        ticketDatabase.remove(ticket);
    }




    public void addSplitEqualByName(String ticketType , String ticketPriceEntered , String personPayedName){


        Person personPayed = null;
        while(personIterator.hasNext()){
            Person person = personIterator.getElement();
            if(person.getName().equals(personPayedName)) {
                personPayed = person;
                personIterator.reset();
                break;
            }
        }

        addSplitEqual(ticketType , ticketPriceEntered , personPayed);
    }


    public void addSplitUnequalByName(String ticketType , List<String> ticketPriceEntered , String personPayedName ){

        Person personPayed = null;
        while(personIterator.hasNext()){
            Person person = personIterator.getElement();
            if(person.getName().equals(personPayedName)){
                personPayed = person;
                personIterator.reset();
                break;
            }
        }

        addSplitUnequal(ticketType , ticketPriceEntered, personPayed);

    }







}
