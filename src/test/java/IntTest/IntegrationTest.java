package IntTest;


import Controllers.PersonController;
import Controllers.TicketController;
import Databases.Database;
import Databases.PersonDatabase;
import Databases.TicketDatabase;
import Factory.TicketFactory;
import Iterator.CustomIterator;
import Person.Person;
import Tickets.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {


    public IntegrationTest()
    {

    }

    @Before
    public void initialize()
    {

    }

    //check if databases are singleton
    @Test
    public void SingletonTest() throws Exception
    {


        PersonDatabase PDB = Database.getInstance(PersonDatabase.class);
        TicketDatabase TDB = Database.getInstance(TicketDatabase.class);


        //check if singleton pattern works
        PersonDatabase PDBTest = Database.getInstance(PersonDatabase.class);
        TicketDatabase TDBTest = Database.getInstance(TicketDatabase.class);


        Assert.assertEquals("PersonDatabase isnt singleton" , PDB , PDBTest);
        Assert.assertEquals("TicketDatabase isnt singleton" , TDB , TDBTest);

    }



    @Test
    public void IntTest(){


        //create database
        PersonDatabase pdb = Database.getInstance(PersonDatabase.class);
        TicketDatabase tdb = Database.getInstance(TicketDatabase.class);

        //create iterators for database (i will use to check additions to databases)
        CustomIterator<Person> pdbIterator = pdb.createIterator();
        CustomIterator<Ticket> tdbIterator = tdb.createIterator();

        //create ticketFactory
        TicketFactory tFactory = new TicketFactory();

        //create controllers
        PersonController pController = new PersonController(pdb , pdbIterator , tdbIterator);
        TicketController tController = new TicketController(tdb , tFactory , pdbIterator , tdbIterator);


        /*      CreatePersonSim        */

        //check Pdb length
        int pdbInitLength = pdbIterator.getLenght();

        //simulate create person panel :

        //1person

        //nameField
        String askedName = "Jan";
        //if createButton is pressed :
        pController.addByName(askedName);

        //2person

        //nameField
        askedName = "An";
        //if createButton is pressed :
        pController.addByName(askedName);

        //3person

        //nameField
        askedName = "Corneel";
        //if createButton is pressed :
        pController.addByName(askedName);


        //check if added                                                                                                //numpersons
        Assert.assertEquals("Person didnt get added to database ", pdbIterator.getLenght(), pdbInitLength + 3);



        //simulate ticketPanel :

        //to check for ticket additions
        int tdbInitLength = tdbIterator.getLenght();

        //ticket1       equalsplit (An paid (30 ,30 ,30))         (2*ticket)

        //select a Ticket
        String selectedTicketType = "Airplane";
        //select a person that has payed
        String selectedPersonName = "An";


        //enter amount (equalSplit)
        String amountTextField = "90.0";                             //(30 , 30 , 30 )
        //select equalSplit :
        tController.addSplitEqualByName(selectedTicketType , amountTextField , selectedPersonName);




        //ticket2       unequal(An : 20 , corneel 50)  (2*ticket)

        //select a Ticket
        selectedTicketType = "Cinema";
        //select a person that has payed
        selectedPersonName = "Jan";
        //enter amount (equalSplit)
        List<String> amountTextFields = new ArrayList<>();
        amountTextFields.add("20");
        amountTextFields.add("50");
        //select equalSplit :
        tController.addSplitUnequalByName(selectedTicketType , amountTextFields , selectedPersonName);


        //ticket3       equalsplit (Jef paid (10 ,10 ,10))         (2*ticket)

        //select a Ticket
        selectedTicketType = "Cinema";
        //select a person that has payed
        selectedPersonName = "Jan";
        //enter amount (equalSplit)
        amountTextField = "30.0";
        //select equalSplit :
        tController.addSplitEqualByName(selectedTicketType , amountTextField , selectedPersonName);



        Assert.assertEquals("Ticket didnt get added to database ", tdbInitLength + 6 , tdbIterator.getLenght() );



        //now we manually calculate what the final payout would be,

        //Jan -> An : 30
        //Corneel -> An : 30

        //An -> Jan : 20
        //Corneel -> Jan : 50

        //An -> Jan : 10
        //Corneel -> Jan : 10


        //jan and an get canceled out
        //corneel -> jan : 60 and corneel -> an : 30


        //check :
        //manual calculated
        List<Double> correctEndPrice = Arrays.asList(60.0, 30.0);

        //to calculate from program
        List<Double> calculatedPrice = new ArrayList<>();

        //calculate
        List<Ticket> finaltickets = tController.CalculateTotal();
        //prices
        for(Ticket ticket : finaltickets){
            calculatedPrice.add(ticket.getPrice());
        }


        //check with tests
        Assert.assertEquals("bad calculated" , correctEndPrice , calculatedPrice);






































    }









}
