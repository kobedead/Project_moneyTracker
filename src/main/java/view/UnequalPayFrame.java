package view;



import Iterator.CustomIterator;
import Person.Person;
import view.panels.UnequalSplitPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UnequalPayFrame extends JFrame {


    UnequalSplitPanel unequalSplitPanel ;


    public UnequalPayFrame()
    {
        super("Registration");
    }


    public void Init(Person selected , CustomIterator personIterator, List<String> prices ){
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);



        unequalSplitPanel = new UnequalSplitPanel(selected, personIterator, prices);

        this.add(unequalSplitPanel);
        this.setVisible(true);

    }





}
