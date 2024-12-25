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


public class TicketController implements Controller<Ticket> {

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

    public void addSplitEqual(String ticketType , String ticketPriceEntered , String personPayedName){


        //not optimal for speed but i tried seperating visual from this more with strings
        Person personPayed = null;
        while(personIterator.hasNext()) {
            Person person = personIterator.getElement();
            if (person.getName().equals(personPayedName)) {
                personPayed = person;
                personIterator.reset();
                break;
            }
        }


        if(ticketPriceEntered != null && ticketType != null && personPayed != null) {
            //ticket needs to be created
            while (personIterator.hasNext()) {
                Person person = personIterator.getElement();
                if (person != personPayed)
                    ticketDatabase.add(factory.getTicket(ticketType, Double.parseDouble(ticketPriceEntered) / personIterator.getLenght(), (Person) person, personPayed));
            }
        }
    }


    public void addSplitUnequal(String ticketType , List<String> ticketPriceEntered , String personPayedName ){

        //not optimal for speed but i tried seperating visual from this more with strings
        Person personPayed = null;
        while(personIterator.hasNext()) {
            Person person = personIterator.getElement();
            if (person.getName().equals(personPayedName)) {
                personPayed = person;
                personIterator.reset();
                break;
            }
        }

        if(ticketPriceEntered.size() == personIterator.getLenght()-1) {
            Integer  otherIndex = 0 ;
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

        // Initialize balances for all persons
        while (personIterator.hasNext()) {
            Person person = personIterator.getElement();
            balances.put(person, 0.0);
        }
        personIterator.reset();

        // Update balances based on tickets
        while (ticketCustomIterator.hasNext()) {
            Ticket ticket = ticketCustomIterator.getElement();

            if (ticket.getFrom() != null && ticket.getTo() != null) {
                balances.putIfAbsent(ticket.getFrom(), 0.0);
                balances.putIfAbsent(ticket.getTo(), 0.0);

                balances.put(ticket.getFrom(), balances.get(ticket.getFrom()) - ticket.getPrice());
                balances.put(ticket.getTo(), balances.get(ticket.getTo()) + ticket.getPrice());
            }
        }

        // Calculate final transactions
        while (true) {
            double largest = 0;
            Person largestP = null;

            double smallest = 0;
            Person smallestP = null;

            for (Person person : balances.keySet()) {
                double balance = balances.get(person);

                if (balance > largest) {
                    largest = balance;
                    largestP = person;
                } else if (balance < smallest) {
                    smallest = balance;
                    smallestP = person;
                }
            }

            if (smallest == 0) {
                if (largest != 0) {
                    System.err.println("Error: Balance calculation mismatch.");
                }
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


}