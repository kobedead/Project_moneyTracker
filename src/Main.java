import Controllers.PersonController;
import Controllers.TicketController;
import Databases.Database;
import Databases.PersonDatabase;
import Databases.TicketDatabase;
import Factory.TicketFactory;
import Iterator.CustomIterator;
import view.ViewFrame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Database personDB = Database.getInstance(PersonDatabase.class);
        Database ticketDB = Database.getInstance(TicketDatabase.class);


        TicketFactory factory = new TicketFactory();


        PersonController personCon = new PersonController((PersonDatabase) personDB);

        CustomIterator personCustomIterator = personDB.createIterator();
        CustomIterator ticketCustomIterator = ticketDB.createIterator();

        TicketController ticketCon = new TicketController((TicketDatabase) ticketDB, factory , personCustomIterator , ticketCustomIterator);




        ViewFrame view = new ViewFrame();
        view.initialize(personCon , ticketCon , factory);


        personDB.addObserver(view);
        ticketDB.addObserver(view);





        }
    }
