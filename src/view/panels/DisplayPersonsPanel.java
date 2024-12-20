package view.panels;

import Controllers.PersonController;
import Tickets.Ticket;

import javax.swing.*;

public class DisplayPersonsPanel extends JPanel {
    private PersonController personController;
    private JList<Ticket> entryJList;
    private DefaultListModel<Ticket> entryListModel;
    private JButton addPriceButton;
    private CreateTicketPanel createTicketPanel;
    JScrollPane pane ;
    public DisplayPersonsPanel(PersonController personController, CreateTicketPanel createTicketPanel){
        JLabel label = new JLabel("Balance");
        entryListModel = new DefaultListModel<>();
        entryJList = new JList<>(entryListModel);

        addPriceButton = new JButton("Add Price");

        this.personController = personController;
        this.createTicketPanel = createTicketPanel;

        pane = new JScrollPane(entryJList);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (createTicketPanel.addCheckOutButtonActionListener()) {
            this.add(label);
            this.add(entryJList);
            this.add(addPriceButton);
        }
    }

    public void addTicket(Ticket entry){this.entryListModel.addElement(entry);}
}
