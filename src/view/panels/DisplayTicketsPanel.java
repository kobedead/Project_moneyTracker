package view.panels;

import Controllers.TicketController;
import Tickets.Ticket;

import javax.swing.*;

public class DisplayTicketsPanel extends JPanel {

    private TicketController ticketController ;

    private JList<Ticket> entryJList;
    private DefaultListModel<Ticket> entryListModel;

    private JButton removeButton;
    private JButton calcTotal;
    JScrollPane pane ;

    public DisplayTicketsPanel(TicketController ticketController)
    {
        JLabel label = new JLabel("Registrations");
        entryListModel = new DefaultListModel<>();
        entryJList = new JList<>(entryListModel);


        removeButton = new JButton("Delete") ;
        calcTotal = new JButton("CalcTotal");


        addRemoveButtonActionListener();
        addCalcTotalActionListener();

        this.ticketController = ticketController;

        pane = new JScrollPane(entryJList);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(entryJList);
        this.add(removeButton);
        this.add(calcTotal);


    }

    public void addTicket(Ticket entry)
    {
        this.entryListModel.addElement(entry);
    }

    public void removeTicket(Ticket ticket){
        this.entryListModel.removeElement(ticket);}



    public void addRemoveButtonActionListener()
    {
        this.removeButton.addActionListener(listener ->
        {
            ticketController.remove(entryJList.getSelectedValue());

        });
    }

    public void addCalcTotalActionListener()
    {
        this.calcTotal.addActionListener(listener ->
        {
            entryListModel.removeAllElements();
            entryListModel.addAll(ticketController.CalcTotal());

        });
    }

}
