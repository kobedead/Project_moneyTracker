package view.panels;

import view.NavigationListener;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{

    public MenuPanel(NavigationListener navigationListener){

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(menuLabel);

        JButton addPersonButton = new JButton("Add Person");
        addPersonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addPersonButton.addActionListener(e -> navigationListener.switchPanel("Add Person"));

        JButton addTicketButton = new JButton("Add Ticket");
        addTicketButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addTicketButton.addActionListener(e -> navigationListener.switchPanel("Add Ticket"));

        JButton calculateBillButton = new JButton("Calculate Bill");
        calculateBillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateBillButton.addActionListener(e -> navigationListener.switchPanel("Calculate Bill"));

        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(addPersonButton);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(addTicketButton);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(calculateBillButton);

    }



}
