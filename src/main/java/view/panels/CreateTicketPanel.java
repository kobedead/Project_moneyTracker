package view.panels;

import Controllers.TicketController;
import Factory.TicketFactory;
import Iterator.CustomIterator;
import Person.Person;
import Tickets.Ticket;
import view.UnequalPayFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CreateTicketPanel extends JPanel {

    private JButton SplitEqual;
    private JButton SplitDiff;

    // Get your controller in this private field
    private TicketController ticketController;

    private String selectedPersonName;
    private CustomIterator personCustomIterator;


    private JTextField ticketPrice;

    private JList<String> possibleTickets;
    private DefaultListModel<String> possibleTicketsModel;

    private List<String> prices;

    // Get your controller in this class via the constructor
    public CreateTicketPanel(TicketController ticketController, List<String> kindOfTickets , CustomIterator personCustomIterator)
    {
        this.ticketController = ticketController ;
        JLabel label = new JLabel("Registration buttons");
        this.SplitEqual = new JButton("EqSplit");
        this.SplitDiff = new JButton("UnevenSplit");

        possibleTicketsModel = new DefaultListModel<>();
        possibleTickets = new JList<>(possibleTicketsModel);

        //list of possible tickets
        for(String ticketKinds :kindOfTickets)
            possibleTicketsModel.addElement(ticketKinds);





        ticketPrice = new JTextField(10);

        this.personCustomIterator = personCustomIterator;

        addSplitEqualButtonActionListener();
        addSplitDiffButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(possibleTickets);
        this.add(ticketPrice);
        this.add(this.SplitEqual);
        this.add(this.SplitDiff);


    }


    public void setSelectedPerson(String personName){
        selectedPersonName = personName;

    }




    public void addSplitEqualButtonActionListener()
    {
        this.SplitEqual.addActionListener(listener ->
        {
                System.out.println(selectedPersonName);
                ticketController.addSplitEqual(possibleTickets.getSelectedValue()  , ticketPrice.getText() , selectedPersonName );

        });
    }

    public void addSplitDiffButtonActionListener()
    {
        this.SplitDiff.addActionListener(listener ->
        {

            prices = new ArrayList<>();

            //if person and ticketType selected -> create new panel
            if(selectedPersonName != null && possibleTickets.getSelectedValue() != null) {
                //create new frame
                UnequalPayFrame unequalPayFrame1 = new UnequalPayFrame();
                //pass prices as refrence so it can get updated
                unequalPayFrame1.Init(selectedPersonName, personCustomIterator, prices);

                // Add a window listener
                unequalPayFrame1.addWindowListener(new WindowAdapter() {

                    //for window disposed with button
                    @Override
                    public void windowClosed(WindowEvent e) {
                        ticketController.addSplitUnequal(possibleTickets.getSelectedValue(), prices, selectedPersonName);

                    }

                    //for window closed on x
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ticketController.addSplitUnequal(possibleTickets.getSelectedValue(), prices, selectedPersonName);

                    }


                });
            }
        });
    }





}
