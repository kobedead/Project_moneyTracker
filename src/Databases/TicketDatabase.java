package Databases;
import Person.Person;
import Tickets.Ticket;

import java.security.KeyPair;
import java.security.KeyStore;
import java.util.*;


public class TicketDatabase extends Database<Ticket>{

    private List<Ticket> ticketList ;

    public TicketDatabase(){
        ticketList = new ArrayList<>();

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



    public List<KeyPair> CalculateTotal(){

        //List<Ticket>



        CustomDict<Person, Double> balances = new CustomDict<Person, Double>();
        for (Ticket ticket : ticketList){
            balances.put(ticket.getFrom(), -ticket.getPrice() );
            balances.put(ticket.getTo() , ticket.getPrice());
        }

        while(balances.keySet().size() != 0) {

            //use pairs!!!!!
            double largest = 0;
            Person largestP = null;

            double smallest = 0 ;
            Person smallestP = null ;


            for (Person person : balances.keySet()) {
                System.out.println(person + "     :    " + balances.get(person));

                if(balances.get(person)>largest){
                    largestP = person;
                    largest = balances.get(person);
                }
                else if(balances.get(person)< smallest){
                    smallestP = person;
                    smallest = balances.get(person);
                }
            }

            if(smallest > 0 );





        }




        return null;
    }







}
