package view.panels;


import Controllers.TicketController;
import Factory.TicketFactory;
import Person.Person;


import javax.swing.*;
import java.util.Objects;

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

    public void addCheckInButtonActionListener() {
        this.SplitEqual.addActionListener(listener -> {
            String ticketPriceEntered = ticketPrice.getText();
            String ticketType = possibleTickets.getSelectedValue();
            Person personPayed = personPanel.getSelectedPerson();

            if (ticketPriceEntered != null && ticketType != null && personPayed != null) {
                // Ticket needs to be created
                Person[] persons = personPanel.getAllPersons();
                double totalPrice = Double.parseDouble(ticketPriceEntered);
                double amountPerPerson = totalPrice / persons.length;
                personPayed.setBalance(totalPrice - amountPerPerson);

                for (Person person : persons) {
                    if (!Objects.equals(person.getName(), personPayed.getName())) {
                        // Each person owes 'amountPerPerson' to the personPayed
                        person.setBalance(person.getBalance() - amountPerPerson);
                        System.out.println(person.getName() + " balance: " + person.getBalance());

                        // Update the balance of personPayed incrementally
                        personPayed.setBalance(personPayed.getBalance() - amountPerPerson);
                        System.out.println(personPayed.getName() + " updated balance: " + personPayed.getBalance());

                        // Add the ticket to the controller
                        controller.AddTicket(factory.getTicket(ticketType, amountPerPerson, person, personPayed));

                    }
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
