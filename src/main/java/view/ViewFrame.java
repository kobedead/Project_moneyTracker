package view;

import Controllers.PersonController;
import Controllers.TicketController;
import Person.Person;
import Tickets.Ticket;
import view.panels.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ViewFrame extends JFrame implements NavigationListener, PropertyChangeListener {


    private JPanel cardPanel; // The main panel for CardLayout
    private CardLayout cardLayout; // CardLayout instance

    private CreatePersonPanel createPersonPanel;
    private CreateTicketPanel createTicketPanel;
    private DisplayTicketsPanel displayTicketsPanel;

    public ViewFrame(PersonController personController, TicketController ticketController, List<String> kindOfTickets) {
        super("Travel Expense Manager");


        this.setSize(700, 400);
        this.setLocation(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.add(cardPanel);

        // Initialize panels
        createPersonPanel = new CreatePersonPanel(personController, this);
        createTicketPanel = new CreateTicketPanel(ticketController, kindOfTickets, this);
        displayTicketsPanel = new DisplayTicketsPanel(ticketController, this);
        MenuPanel menupanel = new MenuPanel(this);

        // Add panels to CardLayout
        cardPanel.add(menupanel, "Menu");
        cardPanel.add(createPersonPanel, "Add Person");
        cardPanel.add(createTicketPanel, "Add Ticket");
        cardPanel.add(displayTicketsPanel, "Calculate Bill");

        // Show the menu panel initially
        cardLayout.show(cardPanel, "Menu");

        this.setVisible(true);

    }

    @Override
    public void switchPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("PersonAdded".equals(evt.getPropertyName())) {
            createPersonPanel.addPersonDisp((Person) evt.getNewValue());
            CreateTicketPanel.addPersonDisp((Person) evt.getNewValue());
        } else if ("PersonDeleted".equals(evt.getPropertyName())) {
            createPersonPanel.removePersonDisp((Person) evt.getNewValue());
            CreateTicketPanel.removePersonDisp((Person) evt.getNewValue());
        } else if ("TicketAdded".equals(evt.getPropertyName())) {
            displayTicketsPanel.addTicketDisp((Ticket) evt.getNewValue());
            createTicketPanel.addTicketDisp((Ticket) evt.getNewValue());
        } else if ("TicketDeleted".equals(evt.getPropertyName())) {
            displayTicketsPanel.removeTicketDisp((Ticket) evt.getNewValue());
            createTicketPanel.removeTicketDisp((Ticket) evt.getNewValue());
        }
    }


}
