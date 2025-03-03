package view.panels;

import Controllers.TicketController;
import Tickets.Ticket;
import view.NavigationListener;

import javax.swing.*;
import java.awt.*;

public class DisplayTicketsPanel extends JPanel {

    private TicketController ticketController ;
    private NavigationListener navigationListener;

    private JList<Ticket> entryJList;
    private DefaultListModel<Ticket> entryListModel;
    private JList<Ticket> calcTotalList;
    private DefaultListModel<Ticket> calcTotalListModel;

    private JButton calcTotal;

    JScrollPane ticketPane, billPane ;

    public DisplayTicketsPanel(TicketController ticketController, NavigationListener navigationListener)
    {
        JLabel label = new JLabel("Tickets");
        JLabel label2 = new JLabel("Total Bill");
        entryListModel = new DefaultListModel<>();
        entryJList = new JList<>(entryListModel);

        calcTotalListModel = new DefaultListModel<>();
        calcTotalList = new JList<>(calcTotalListModel);

        calcTotal = new JButton("CalcTotal");


        addCalcTotalActionListener();

        this.ticketController = ticketController;
        this.navigationListener = navigationListener;

        ticketPane = new JScrollPane(entryJList);
        billPane = new JScrollPane(calcTotalList);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(ticketPane);
        this.add(calcTotal);
        this.add(label2);
        this.add(billPane);
        this.add(createBackButtonPanel());

    }

    public void addTicketDisp(Ticket ticket) {
        entryListModel.addElement(ticket);
    }

    public void removeTicketDisp(Ticket ticket) {
        entryListModel.removeElement(ticket);
    }


    public void addCalcTotalActionListener()
    {
        this.calcTotal.addActionListener(listener ->
        {
            calcTotalListModel.clear();
            calcTotalListModel.addAll(ticketController.CalculateTotal());
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
