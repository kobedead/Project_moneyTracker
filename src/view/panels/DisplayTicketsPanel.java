package view.panels;

import Tickets.Ticket;

import javax.swing.*;

public class DisplayTicketsPanel extends JPanel {

    private JList<Ticket> entryJList;
    private DefaultListModel<Ticket> entryListModel;
    JScrollPane pane ;

    public DisplayTicketsPanel()
    {
        JLabel label = new JLabel("Registrations");
        entryListModel = new DefaultListModel<>();
        entryJList = new JList<>(entryListModel);

        pane = new JScrollPane(entryJList);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(entryJList);



    }

    public void addTicket(Ticket entry)
    {
        this.entryListModel.addElement(entry);
    }





}
