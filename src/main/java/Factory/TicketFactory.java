package Factory;


import Person.Person;
import Tickets.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TicketFactory {

    protected List<String> kindsOfTickets ;


    public TicketFactory(){

        kindsOfTickets = Arrays.asList("Airplane" , "Cinema" ,  "StoreTicket" , "RestaurantTicket" ) ;

    }

    public Ticket getTicket(String ticketKind , Double price , Person from , Person to) {

        //maybe make this a switch with the index (try to automate)


        if(Objects.equals(ticketKind, kindsOfTickets.get(0)))
            return new AirplaneTicket(price , from , to) ;
        else if (Objects.equals(ticketKind, kindsOfTickets.get(1)))
            return new CinemaTicket(price,from ,to);
        else if (Objects.equals(ticketKind, kindsOfTickets.get(2)))
            return new StoreTicket(price,from ,to);
        else if (Objects.equals(ticketKind, kindsOfTickets.get(3)))
            return new RestaurantTicket(price,from ,to);
        else{
            System.out.println("faulty ticketType");
            return null ;
        }

    }

    public List<String> getKindsOfTickets(){
        return kindsOfTickets;
    }


}
