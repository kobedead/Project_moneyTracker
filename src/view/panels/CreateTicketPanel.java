package view.panels;

import Controllers.TicketController;
import Factory.TicketFactory;
import Iterator.Iterator;
import Person.Person;
import Tickets.Ticket;


import javax.swing.*;

public class CreateTicketPanel extends JPanel {

    private JButton SplitEqual;
    private JButton SplitDiff;

    // Get your controller in this private field
    private TicketController ticketController;

    private Person selectedPerson;
    private Iterator personIterator;

    private TicketFactory factory;

    private JTextField ticketPrice;
    private JComboBox<String> typeDropdown;

    private JList<String> possibleTickets;
    private DefaultListModel<String> possibleTicketsModel;
    private boolean panel = false;


    // Get your controller in this class via the constructor
    public CreateTicketPanel(TicketController ticketController, TicketFactory factory , Iterator personIterator)
    {
        this.ticketController = ticketController ;
        JLabel label = new JLabel("Registration buttons");
        this.SplitEqual = new JButton("EqSplit");
        this.SplitDiff = new JButton("UnevenSplit");

        possibleTicketsModel = new DefaultListModel<>();
        possibleTickets = new JList<>(possibleTicketsModel);
        //list of possible tickets
        possibleTicketsModel.addAll(factory.getKindsOfTickets());
        typeDropdown = new JComboBox<>();
        for (String ticket: factory.getKindsOfTickets()){
            typeDropdown.addItem(ticket);
        }

        this.factory = factory;

        ticketPrice = new JTextField(10);

        this.personIterator = personIterator;

        addCheckInButtonActionListener();
        addCheckOutButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(typeDropdown);
        this.add(ticketPrice);
        this.add(this.SplitEqual);
        this.add(this.SplitDiff);


    }


    public void setSelectedPerson(Person person){
        this.selectedPerson = person;

    }




    public void addCheckInButtonActionListener()
    {
        this.SplitEqual.addActionListener(listener ->
        {


            String ticketPriceEntered = ticketPrice.getText();
            String ticketType = (String) typeDropdown.getSelectedItem();
            Person personPayed =  selectedPerson;


            if(ticketPriceEntered != null && ticketType != null && personPayed != null){
                //ticket needs to be created

                while (personIterator.hasNext()) {
                    //System.out.println(personIterator.getIndex());
                    Person person = (Person) personIterator.getElement();
                    if(person != personPayed)
                        ticketController.add(factory.getTicket(ticketType , Double.parseDouble(ticketPriceEntered)/personIterator.getLenght() , (Person) person , personPayed));
                }
        }
        });
    }

    public boolean addCheckOutButtonActionListener()
    {
        this.SplitDiff.addActionListener(listener ->
        {
            panel = true;
//            //make it here that an amount for every person needs to be typed , with '/' between them
//            //the amount will get done from top to bottom of persons in list?????
//
//            String[] ticketPriceEntered = ticketPrice.getText().split("/");
//            String ticketType = (String) typeDropdown.getSelectedItem();
//            Person personPayed =  selectedPerson;
//
//
//            if(ticketPriceEntered.length == personIterator.getLenght()) {
//
//                while(personIterator.hasNext()){
//                    Person person = (Person) personIterator.getElement();
//                    if (person != personPayed) {
//                        ticketController.add(factory.getTicket(ticketType, Double.parseDouble(ticketPriceEntered[personIterator.getIndex() -1 ]), person , personPayed));
//
//                    }
//                }
//            }
        }); return panel;
    }







}
