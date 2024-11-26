package view;

import Controllers.PersonController;
import Controllers.TicketController;
import Factory.TicketFactory;
import Person.Person;

import Tickets.Ticket;
import view.panels.CreatePersonPanel;
import view.panels.DisplayTicketsPanel;
import view.panels.CreateTicketPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewFrame extends JFrame implements PropertyChangeListener
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

    public void initialize(PersonController personController, TicketController ticketController , TicketFactory ticketFactory)
    {
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);


        this.personController =  personController;

        this.ticketController = ticketController;

        this.personController = personController;
        createPersonPanel = new CreatePersonPanel(personController );

                                                                                    //not good
        createTicketPanel = new CreateTicketPanel(ticketController , ticketFactory , createPersonPanel );

        displayTicketsPanel = new DisplayTicketsPanel();




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
            createPersonPanel.addPersonDisp((Person) evt.getNewValue());
        }
        else if ("PersonDeleted".equals(evt.getPropertyName())) {
            //delist person in create person panel
            createPersonPanel.removePersonDisp((Person) evt.getNewValue());


        }



        //listen to Ticketcontroller
        if ("TicketAdded".equals(evt.getPropertyName())){
            //add ticket to ticketsdatabase panel
            displayTicketsPanel.addTicket((Ticket) evt.getNewValue());
        }
        else if ("TicketDeleted".equals(evt.getPropertyName())){
            //delete ticket from ticketdatabase panel

        }




    }
}
