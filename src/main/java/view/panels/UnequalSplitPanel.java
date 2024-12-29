package view.panels;

import Iterator.CustomIterator;
import Person.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UnequalSplitPanel extends JPanel {

    CustomIterator personIterator;
    List<String> prices;

    Person selected ;

    JButton submitButton ;
    List<JTextArea> persons ;

    List<JTextField> moneyFields ;




    public UnequalSplitPanel(Person selected  , CustomIterator<Person> personIterator, List<String> prices){
        this.personIterator = personIterator;
        this.prices = prices;
        this.selected = selected;

        submitButton = new JButton("submit");
        //submitButton.setBounds(100, 150, 50, 20); // Set button position and size


        persons = new ArrayList<>();
        moneyFields = new ArrayList<>() ;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Ensure components stretch horizontally
        gbc.insets = new Insets(5,10, 5, 10); // Add padding between components



        //make namefields and textfield for entering amount
        while (personIterator.hasNext()) {
            Person person = personIterator.getElement();
            if (person != selected) {

                JTextArea personName = new JTextArea(person.getName());
                personName.setEditable(false);
                persons.add(personName);

                gbc.gridx = 0; // First column
                gbc.gridy = personIterator.getIndex()-1;
                this.add(personName, gbc);


                JTextField moneyField = new JTextField(10);
                moneyFields.add(moneyField);
                gbc.gridx = 1; // second column
                this.add(moneyField , gbc);


            }

        }


        addSubmitButtonActionListener();

        this.add(submitButton);


    }



    public void addSubmitButtonActionListener() {
        this.submitButton.addActionListener(listener ->
        {
            for (JTextField price : moneyFields)
                prices.add(price.getText());

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.dispose();
            }
        });
    }
}
