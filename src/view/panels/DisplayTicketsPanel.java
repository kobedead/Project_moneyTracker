package view.panels;

import Controllers.TicketController;
import Databases.CustomDict;
import Person.Person;
import Tickets.Ticket;
import view.NavigationListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayTicketsPanel extends JPanel {

    private TicketController ticketController ;
    private NavigationListener navigationListener;

    private JList<Ticket> entryJList;
    private DefaultListModel<Ticket> entryListModel;
    private JList<Ticket> calcTotalList;
    private DefaultListModel<Ticket> calcTotalListModel;

    private JButton calcTotal;

    private List<Ticket> finalpay = new ArrayList<>();
    private CustomDict<Person, Double> balances = new CustomDict<Person, Double>();
    JScrollPane ticketPane, billPane ;

    public DisplayTicketsPanel(TicketController ticketController, NavigationListener navigationListener)
    {
        JLabel label = new JLabel("Tickets");
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
