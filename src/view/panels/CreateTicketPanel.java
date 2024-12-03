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
    private TicketController controller;

    //to know wich person selected to bill to
    private CreatePersonPanel personPanel;

    private TicketFactory factory;

    private JTextField ticketPrice;

    private JList<String> possibleTickets;
    private DefaultListModel<String> possibleTicketsModel;


    // Get your controller in this class via the constructor
    public CreateTicketPanel(TicketController controller, TicketFactory factory , CreatePersonPanel personPanel)
    {
        this.controller = controller ;
        JLabel label = new JLabel("Registration buttons");
        this.SplitEqual = new JButton("EqSplit");
        this.SplitDiff = new JButton("UnevenSplit");

        possibleTicketsModel = new DefaultListModel<>();
        possibleTickets = new JList<>(possibleTicketsModel);
        //list of possible tickets
        possibleTicketsModel.addAll(factory.getKindsOfTickets());

        this.factory = factory;

        ticketPrice = new JTextField(10);

        this.personPanel = personPanel;

        addCheckInButtonActionListener();
        addCheckOutButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(possibleTickets);
        this.add(ticketPrice);
        this.add(this.SplitEqual);
        this.add(this.SplitDiff);


    }

    public void addCheckInButtonActionListener()
    {
        this.SplitEqual.addActionListener(listener ->
        {
            // Insert here your controller functionality
            //check that something is selected (selected != null)

            String ticketPriceEntered = ticketPrice.getText();
            String ticketType = possibleTickets.getSelectedValue();
            Person personPayed =  personPanel.getSelectedPerson();


            if(ticketPriceEntered != null && ticketType != null && personPayed != null){
                //ticket needs to be created
                Object[] persons = personPanel.getAllPersons();
                for (Object person :  persons) {
                    if(person != personPayed)
                        controller.AddTicket(factory.getTicket(ticketType , Double.parseDouble(ticketPriceEntered)/persons.length , (Person) person , personPayed));
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
            Person personPayed =  personPanel.getSelectedPerson();

            Object[] allPersons = personPanel.getAllPersons();


            if(ticketPriceEntered.length == allPersons.length) {

                for (int i = 0; i < allPersons.length; i++) {

                    if (allPersons[i] != personPayed) {

                        controller.AddTicket(factory.getTicket(ticketType, Double.parseDouble(ticketPriceEntered[i]), (Person) allPersons[i] , personPayed));

                    }
                }
            }
        });
    }





}
