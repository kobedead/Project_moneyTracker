package view;

import Controllers.PersonController;
import Controllers.TicketController;
import Factory.TicketFactory;
import Person.Person;
import Tickets.Ticket;
import view.panels.CreatePersonPanel;
import view.panels.CreateTicketPanel;
import view.panels.DisplayTicketsPanel;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ViewFrame extends JFrame implements PropertyChangeListener , ListSelectionListener
{
    // Get your controller in this private field
    PersonController personController;
    TicketController ticketController ;


    DisplayTicketsPanel displayTicketsPanel;
    CreateTicketPanel createTicketPanel;
    CreatePersonPanel createPersonPanel;

    public ViewFrame()
    {
        super("Registration");
    }

    public void initialize(PersonController personController, TicketController ticketController , List<String> kindOfTickets )
    {
        this.setSize(900, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);


        this.ticketController = ticketController;
        this.personController = personController;


        createPersonPanel = new CreatePersonPanel(personController );
        createPersonPanel.addSelectListLister(this);


        createTicketPanel = new CreateTicketPanel(ticketController ,  kindOfTickets , personController.CreateIterator() );


        displayTicketsPanel = new DisplayTicketsPanel(ticketController);



        this.add(createPersonPanel);
        this.add(displayTicketsPanel);
        this.add(createTicketPanel);

        this.setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {


        //listen to PersonController entry added
        if ("PersonAdded".equals(evt.getPropertyName())) {
            //list person in create person panel
            createPersonPanel.addPersonDisp((String) evt.getNewValue());

        }
        else if ("PersonDeleted".equals(evt.getPropertyName())) {
            //delist person in create person panel
            createPersonPanel.removePersonDisp((String) evt.getNewValue());


        }



        //listen to Ticketcontroller
        if ("TicketAdded".equals(evt.getPropertyName())){
            //add ticket to ticketsdatabase panel
            displayTicketsPanel.addTicket((Ticket) evt.getNewValue());
        }
        else if ("TicketDeleted".equals(evt.getPropertyName())){
            //delete ticket from ticketdatabase panel
            displayTicketsPanel.removeTicket((Ticket) evt.getNewValue());
        }




    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            System.out.println("Selected value updated");
            createTicketPanel.setSelectedPerson(createPersonPanel.getSelectedPersonName());


    }}
}
