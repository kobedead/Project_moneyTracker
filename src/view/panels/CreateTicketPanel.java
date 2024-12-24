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
    private JButton addTicketButton;
    private JButton removeTicketButton;

    private TicketController ticketController;
    private NavigationListener navigationListener;

    private Person selectedPerson;
    private Iterator personIterator;

    private TicketFactory factory;

    private JComboBox<String> typeDropdown;

    private JList<Ticket> ticketList;
    private DefaultListModel<Ticket> ticketListModel;
    private DefaultListModel<String> possibleTicketsModel;
    private JList<Person> createdPersonList;
    private static DefaultListModel<Person> createdPersonListModel;

    public CreateTicketPanel(TicketController ticketController, TicketFactory factory, Iterator personIterator, NavigationListener navigationListener) {
        this.ticketController = ticketController;
        this.navigationListener = navigationListener;
        this.factory = factory;

        JLabel label = new JLabel("Choose Ticket Type");
        this.addTicketButton = new JButton("Add Ticket");
        this.removeTicketButton = new JButton("Remove Ticket");

        // Initialize ticket list
        ticketListModel = new DefaultListModel<>();
        ticketList = new JList<>(ticketListModel);
        JScrollPane ticketScrollPane = new JScrollPane(ticketList);
        ticketScrollPane.setPreferredSize(new Dimension(300, 150));

        // Initialize dropdown
        possibleTicketsModel = new DefaultListModel<>();
        typeDropdown = new JComboBox<>();
        for (String ticket : factory.getKindsOfTickets()) {
            typeDropdown.addItem(ticket);
        }

        // Initialize person list
        createdPersonListModel = new DefaultListModel<>();
        createdPersonList = new JList<>(createdPersonListModel);
        JScrollPane personScrollPane = new JScrollPane(createdPersonList);
        personScrollPane.setPreferredSize(new Dimension(300, 150));

        this.personIterator = personIterator;
        while (personIterator.hasNext()) {
            Person person = (Person) personIterator.getElement();
            createdPersonListModel.addElement(person);
        }

        // Add action listeners
        addCheckInButtonActionListener();
        addRemoveTicketButtonActionListener();

        // Panel layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add components
        this.add(label);
        this.add(typeDropdown);
        this.add(ticketScrollPane);
        this.add(personScrollPane);
        this.add(this.addTicketButton);
        this.add(this.removeTicketButton);
        this.add(createBackButtonPanel());
    }


    public void setSelectedPerson(Person person){
        this.selectedPerson = person;

    }

    public void addTicketDisp(Ticket ticket) {
        ticketListModel.addElement(ticket);
    }

    public void removeTicketDisp(Ticket ticket) {
        ticketListModel.removeElement(ticket);
    }


    public static void addPersonDisp(Person person) {
        createdPersonListModel.addElement(person);
    }

    public static void removePersonDisp(Person person) {
        createdPersonListModel.removeElement(person);
    }


    public void addCheckInButtonActionListener() {
        this.addTicketButton.addActionListener(listener -> {
            String ticketPriceEntered = JOptionPane.showInputDialog("Enter Ticket Price");
            String ticketType = (String) typeDropdown.getSelectedItem();
            selectedPerson = createdPersonList.getSelectedValue();

            if (ticketPriceEntered == null || ticketType == null || selectedPerson == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields and select a person.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double ticketPrice = Double.parseDouble(ticketPriceEntered);

                // Create ticket for the selected person
                Ticket ticket = factory.getTicket(ticketType, ticketPrice, selectedPerson, selectedPerson);

                // Add ticket to the controller and UI
                ticketController.add(ticket); // Ensure this method triggers property changes or similar updates

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    public void addRemoveTicketButtonActionListener() {
        this.removeTicketButton.addActionListener(listener -> {
            Ticket selectedValue = ticketList.getSelectedValue();
            if (selectedValue == null) {
                JOptionPane.showMessageDialog(this, "No Ticket selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the person can be safely removed
            ticketController.remove(selectedValue);
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
