import Controllers.PersonController;
import Controllers.TicketController;
import Databases.Database;
import Databases.PersonDatabase;
import Databases.TicketDatabase;
import Factory.TicketFactory;
import Iterator.CustomIterator;
import Person.Person;
import Tickets.Ticket;
import view.ViewFrame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Database<Person> personDB = Database.getInstance(PersonDatabase.class);
        Database<Ticket> ticketDB = Database.getInstance(TicketDatabase.class);


        TicketFactory factory = new TicketFactory();



        CustomIterator<Person> personCustomIterator = personDB.createIterator();
        CustomIterator<Ticket> ticketCustomIterator = ticketDB.createIterator();


        PersonController personCon = new PersonController( personDB, personCustomIterator );

        TicketController ticketCon = new TicketController(ticketDB, factory , personCustomIterator , ticketCustomIterator);




        ViewFrame view = new ViewFrame();
        view.initialize(personCon , ticketCon , factory);


        personDB.addObserver(view);
        ticketDB.addObserver(view);





        }
    }
