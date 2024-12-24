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

    private JButton splitButton;
    private JButton calcTotal;

    private List<Ticket> finalpay = new ArrayList<>();
    private CustomDict<Person, Double> balances = new CustomDict<Person, Double>();
    JScrollPane pane ;

    public DisplayTicketsPanel(TicketController ticketController, NavigationListener navigationListener)
    {
        JLabel label = new JLabel("Tickets");
        entryListModel = new DefaultListModel<>();
        entryJList = new JList<>(entryListModel);


        splitButton = new JButton("Split") ;
        calcTotal = new JButton("CalcTotal");


        addSplitButtonActionListener();
        addCalcTotalActionListener();

        this.ticketController = ticketController;
        this.navigationListener = navigationListener;

        pane = new JScrollPane(entryJList);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(entryJList);
        this.add(splitButton);
        this.add(calcTotal);
        this.add(createBackButtonPanel());

    }

    public void addTicketDisp(Ticket ticket) {
        entryListModel.addElement(ticket);
    }

    public void removeTicketDisp(Ticket ticket) {
        entryListModel.removeElement(ticket);
    }


    public List<Ticket> addSplitButtonActionListener()
    {
        this.splitButton.addActionListener(listener ->
        {


        });
        return finalpay;
    }

    public void addCalcTotalActionListener()
    {
        this.calcTotal.addActionListener(listener ->
        {
            entryListModel.removeAllElements();
            entryListModel.addAll(ticketController.CalcTotal());

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
