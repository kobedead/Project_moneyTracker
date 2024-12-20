package view.panels;

import Controllers.TicketController;
import Factory.TicketFactory;
import Iterator.Iterator;
import Person.Person;
import Tickets.Ticket;
import view.NavigationListener;


import javax.swing.*;
import java.awt.*;

public class CreateTicketPanel extends JPanel {

    private JButton SplitEqual;
    private JButton SplitDiff;

    // Get your controller in this private field
    private TicketController ticketController;
    private NavigationListener navigationListener;

    private Person selectedPerson;
    private Iterator personIterator;

    private TicketFactory factory;

    private JTextField ticketPrice;
    private JComboBox<String> typeDropdown;

    private JList<String> possibleTickets;
    private DefaultListModel<String> possibleTicketsModel;
    private JList<Person> createdPersonList;
    private static DefaultListModel<Person> createdPersonListModel;


    // Get your controller in this class via the constructor
    public CreateTicketPanel(TicketController ticketController, TicketFactory factory , Iterator personIterator, NavigationListener navigationListener)
    {
        this.ticketController = ticketController ;
        this.navigationListener = navigationListener;
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

        createdPersonListModel = new DefaultListModel<>();
        createdPersonList = new JList<>(createdPersonListModel);
        this.personIterator = personIterator;
        while (personIterator.hasNext()){
            Person person = (Person) personIterator.getElement();
            createdPersonListModel.addElement(person);
        }

        addCheckInButtonActionListener();
        addCheckOutButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(typeDropdown);
        this.add(ticketPrice);
        this.add(new JScrollPane(createdPersonList));
        this.add(this.SplitEqual);
        this.add(this.SplitDiff);
        this.add(createBackButtonPanel());


    }


    public void setSelectedPerson(Person person){
        this.selectedPerson = person;

    }


    public static void addPersonDisp(Person person) {
        createdPersonListModel.addElement(person);
    }

    public static void removePersonDisp(Person person) {
        createdPersonListModel.removeElement(person);
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

    public void addCheckOutButtonActionListener()
    {
        this.SplitDiff.addActionListener(listener ->
        {

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
        });
    }

    private JPanel createBackButtonPanel() {
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> navigationListener.switchPanel("Menu"));
        backPanel.add(backButton);
        return backPanel;
    }





}
