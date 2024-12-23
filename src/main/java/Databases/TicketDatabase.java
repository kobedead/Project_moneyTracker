package Databases;

import Person.Person;
import Tickets.FinalTicket;
import Tickets.Ticket;

import java.util.*;
import Iterator.CustomIterator;
import Iterator.TicketDBIterator;

public class TicketDatabase extends Database<Ticket>{

    private List<Ticket> ticketList ;

    public TicketDatabase(){
        ticketList = new ArrayList<>();

    }

    public CustomIterator<Ticket> createIterator(){
        return new TicketDBIterator(ticketList);
    }


    public void add(Ticket ticket) {

        support.firePropertyChange("TicketAdded" , null , ticket);
        ticketList.add(ticket);
    }
//TicketDeleted
    public void remove(Ticket ticket){

        support.firePropertyChange("TicketDeleted" , null , ticket);
        ticketList.remove(ticket);

    }



    public List<Ticket> CalculateTotal(){

        List<Ticket> finalpay = new ArrayList<>();
        HashMap<Person, Double> balances = new HashMap<>();

        for (Ticket ticket : ticketList){
            balances.put(ticket.getFrom(), balances.getOrDefault(ticket.getFrom() , 0.0) -ticket.getPrice() );
            balances.put(ticket.getTo() , balances.getOrDefault(ticket.getTo() , 0.0) + ticket.getPrice());
        }

        while(true) {


            //use pairs!!!!!
            double largest = 0;
            Person largestP = null;

            double smallest = 0 ;
            Person smallestP = null ;

            for (Person person : balances.keySet()) {

                if(balances.get(person)>largest){
                    largestP = person;
                    largest = balances.get(person);
                }
                else if(balances.get(person)< smallest){
                    smallestP = person;
                    smallest = balances.get(person);
                }
            }

            if(smallest == 0 )
                return finalpay;


            double payment  = Math.min(Math.abs(smallest) , largest);
            finalpay.add(new FinalTicket(payment , smallestP , largestP));

            balances.put(largestP , balances.get(largestP) - payment);
            balances.put(smallestP , balances.get(smallestP) + payment);



        }

    }







}
