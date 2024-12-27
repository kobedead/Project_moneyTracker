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

public class ViewFrame extends JFrame implements NavigationListener, PropertyChangeListener, ListSelectionListener {
    private final PersonController personController;
    private final TicketController ticketController;
    private final TicketFactory ticketFactory;

    private JPanel cardPanel; // The main panel for CardLayout
    private CardLayout cardLayout; // CardLayout instance

    private CreatePersonPanel createPersonPanel;
    private CreateTicketPanel createTicketPanel;
    private DisplayTicketsPanel displayTicketsPanel;

    public ViewFrame(PersonController personController, TicketController ticketController, TicketFactory ticketFactory) {
        super("Travel Expense Manager");
        this.personController = personController;
        this.ticketController = ticketController;
        this.ticketFactory = ticketFactory;

        this.setSize(700, 400);
        this.setLocation(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.add(cardPanel);

        // Initialize panels
        createPersonPanel = new CreatePersonPanel(personController, this);
        createTicketPanel = new CreateTicketPanel(ticketController, ticketFactory, personController.CreateIterator(), this);
        displayTicketsPanel = new DisplayTicketsPanel(ticketController, this);

        // Add panels to CardLayout
        cardPanel.add(createMenuPanel(), "Menu");
        cardPanel.add(createPersonPanel, "Add Person");
        cardPanel.add(createTicketPanel, "Add Ticket");
        cardPanel.add(displayTicketsPanel, "Calculate Bill");

        // Show the menu panel initially
        cardLayout.show(cardPanel, "Menu");

        this.setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.add(menuLabel);

        JButton addPersonButton = new JButton("Add Person");
        addPersonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPersonButton.addActionListener(e -> switchPanel("Add Person"));

        JButton addTicketButton = new JButton("Add Ticket");
        addTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addTicketButton.addActionListener(e -> switchPanel("Add Ticket"));

        JButton calculateBillButton = new JButton("Calculate Bill");
        calculateBillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateBillButton.addActionListener(e -> switchPanel("Calculate Bill"));

        menu.add(Box.createRigidArea(new Dimension(0, 20)));
        menu.add(addPersonButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(addTicketButton);
        menu.add(Box.createRigidArea(new Dimension(0, 10)));
        menu.add(calculateBillButton);

        return menu;
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            JList<?> source = (JList<?>) e.getSource();
            Object selected = source.getSelectedValue();
            if (selected instanceof Person) {
                createTicketPanel.setSelectedPerson((Person) selected);
            }
        }
    }
}
