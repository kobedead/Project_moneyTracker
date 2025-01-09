package view.panels;

import Controllers.TicketController;
import Person.Person;
import Tickets.Ticket;
import view.NavigationListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateTicketPanel extends JPanel {
    private JButton addTicketButton;
    private JButton removeTicketButton;
    private JCheckBox unequalPay;

    private TicketController ticketController;
    private NavigationListener navigationListener;

    private Person selectedPerson;



    private JComboBox<String> typeDropdown;

    private JList<Ticket> ticketList;
    private DefaultListModel<Ticket> ticketListModel;
    private JList<Person> createdPersonList;
    private static DefaultListModel<Person> createdPersonListModel;

    public CreateTicketPanel(TicketController ticketController, List<String> kinfOfTickets, NavigationListener navigationListener) {
        this.ticketController = ticketController;
        this.navigationListener = navigationListener;

        JLabel label = new JLabel("Choose Ticket Type");
        this.addTicketButton = new JButton("Add Ticket");
        this.removeTicketButton = new JButton("Remove Ticket");
        this.unequalPay = new JCheckBox("Unequal Pay");

        // Initialize ticket list
        ticketListModel = new DefaultListModel<>();
        ticketList = new JList<>(ticketListModel);
        JScrollPane ticketScrollPane = new JScrollPane(ticketList);
        ticketScrollPane.setPreferredSize(new Dimension(300, 150));


        typeDropdown = new JComboBox<>();
        for (String ticket : kinfOfTickets) {
            typeDropdown.addItem(ticket);
        }

        createdPersonListModel = new DefaultListModel<>();
        createdPersonList = new JList<>(createdPersonListModel);
        JScrollPane personScrollPane = new JScrollPane(createdPersonList);
        personScrollPane.setPreferredSize(new Dimension(300, 150));



        // Add action listeners
        addTicketButtonActionListener();
        addRemoveTicketButtonActionListener();

        // Panel layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add components
        this.add(label);
        this.add(typeDropdown);
        this.add(ticketScrollPane);
        this.add(personScrollPane);
        this.add(addTicketButton);
        this.add(removeTicketButton);
        this.add(unequalPay);
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


    public void addTicketButtonActionListener() {
        this.addTicketButton.addActionListener(listener -> {
            selectedPerson = createdPersonList.getSelectedValue();
            String ticketType = (String) typeDropdown.getSelectedItem();

            if (ticketType == null || selectedPerson == null) {
                JOptionPane.showMessageDialog(this, "Please select a ticket type and a person.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!unequalPay.isSelected()) {
                // Split Equally Logic
                String ticketPriceEntered = JOptionPane.showInputDialog("Enter Ticket Price");
                if (ticketPriceEntered == null) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid ticket price.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    ticketController.addSplitEqual(ticketType, ticketPriceEntered, selectedPerson);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Unequal Pay Logic
                ArrayList<String> prices = new ArrayList<>();
                for (int i = 0; i < createdPersonListModel.size(); i++) {
                    Person person = createdPersonListModel.get(i);
                    if (person.equals(selectedPerson)) continue;

                    String price = JOptionPane.showInputDialog("Enter Ticket Price for " + person.getName());
                    if (price == null) {
                        JOptionPane.showMessageDialog(this, "Please enter valid prices for all persons.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    prices.add(price);
                }

                if (prices.size() != createdPersonListModel.size() - 1) {
                    JOptionPane.showMessageDialog(this, "Please enter prices for all participants except the selected person.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    ticketController.addSplitUnequal(ticketType, prices, selectedPerson);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
