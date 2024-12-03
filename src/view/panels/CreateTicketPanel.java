package view.panels;

import Controllers.PersonController;
import Controllers.TicketController;
import Factory.TicketFactory;
import Person.Person;


import javax.swing.*;

public class CreateTicketPanel extends JPanel {

    private JButton SplitEqual;
    private JButton SplitDiff;

    // Get your controller in this private field
    private TicketController ticketController;

    private Person selectedPerson; //could have just gotten this from personPanel, but idk bad practice
    private CreatePersonPanel personPanel; //used to get all persons (bad practice?)

    private TicketFactory factory;

    private JTextField ticketPrice;

    private JList<String> possibleTickets;
    private DefaultListModel<String> possibleTicketsModel;


    // Get your controller in this class via the constructor
    public CreateTicketPanel(TicketController ticketController, TicketFactory factory , CreatePersonPanel createPersonPanel)
    {
        this.ticketController = ticketController ;
        JLabel label = new JLabel("Registration buttons");
        this.SplitEqual = new JButton("EqSplit");
        this.SplitDiff = new JButton("UnevenSplit");

        possibleTicketsModel = new DefaultListModel<>();
        possibleTickets = new JList<>(possibleTicketsModel);
        //list of possible tickets
        possibleTicketsModel.addAll(factory.getKindsOfTickets());

        this.factory = factory;

        ticketPrice = new JTextField(10);

        this.personPanel = createPersonPanel;

        addCheckInButtonActionListener();
        addCheckOutButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(possibleTickets);
        this.add(ticketPrice);
        this.add(this.SplitEqual);
        this.add(this.SplitDiff);


    }


    public void setSelectedPerson(Person person){
        selectedPerson = person;

    }




    public void addCheckInButtonActionListener()
    {
        this.SplitEqual.addActionListener(listener ->
        {


            String ticketPriceEntered = ticketPrice.getText();
            String ticketType = possibleTickets.getSelectedValue();
            Person personPayed =  selectedPerson;                   //personPanel.getSelectedPerson()


            if(ticketPriceEntered != null && ticketType != null && personPayed != null){
                //ticket needs to be created
                Object[] persons = personPanel.getAllPersons();
                for (Object person :  persons) {
                    if(person != personPayed)
                        ticketController.AddTicket(factory.getTicket(ticketType , Double.parseDouble(ticketPriceEntered)/persons.length , (Person) person , personPayed));
                }
        }




        });
    }

    public void addCheckOutButtonActionListener()
    {
        this.SplitDiff.addActionListener(listener ->
        {
            //make it here that an amount for every person needs to be typed , with '/' between them
            //the amount will get done from top to bottom of persons in list?????

            String[] ticketPriceEntered = ticketPrice.getText().split("/");
            String ticketType = possibleTickets.getSelectedValue();
            Person personPayed =  selectedPerson;

            Object[] allPersons = personPanel.getAllPersons();


            if(ticketPriceEntered.length == allPersons.length) {

                for (int i = 0; i < allPersons.length; i++) {

                    if (allPersons[i] != personPayed) {

                        ticketController.AddTicket(factory.getTicket(ticketType, Double.parseDouble(ticketPriceEntered[i]), (Person) allPersons[i] , personPayed));

                    }
                }
            }
        });
    }





}
