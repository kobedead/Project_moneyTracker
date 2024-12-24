import Controllers.PersonController;
import Controllers.TicketController;
import Databases.Database;
import Databases.PersonDatabase;
import Databases.TicketDatabase;
import Factory.TicketFactory;
import view.ViewFrame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Database personDB = Database.getInstance(PersonDatabase.class);
        Database ticketDB = Database.getInstance(TicketDatabase.class);




        PersonController personCon = new PersonController((PersonDatabase) personDB);
        TicketController ticketCon = new TicketController((TicketDatabase) ticketDB);


        TicketFactory factory = new TicketFactory();


        ViewFrame view = new ViewFrame(personCon, ticketCon, factory);



        personDB.addObserver(view);
        ticketDB.addObserver(view);





        }
    }
