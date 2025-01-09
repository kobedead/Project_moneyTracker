// Adjustments to fix the issue where pressing the Add Person button does not display a new frame

package view.panels;

import Controllers.PersonController;
import Person.Person;
import view.NavigationListener;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CreatePersonPanel extends JPanel {
    private JList<Person> createdPersonList;
    private DefaultListModel<Person> createdPersonListModel;

    private JButton createPerson;
    private JButton removePerson;

    private JTextField personName;

    private PersonController personController;
    private NavigationListener navigationListener;

    public CreatePersonPanel(PersonController personController, NavigationListener navigationListener) {
        this.personController = personController;
        this.navigationListener = navigationListener;

        JLabel label = new JLabel("Create Person");
        createdPersonListModel = new DefaultListModel<>();
        createdPersonList = new JList<>(createdPersonListModel);

        createPerson = new JButton("Create");
        removePerson = new JButton("Remove");

        personName = new JTextField(10);

        addCreatePersonButtonActionListener();
        addRemovePersonButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.add(label);
        this.add(new JScrollPane(createdPersonList)); // Ensure the list is scrollable
        this.add(personName);
        this.add(createPerson);
        this.add(removePerson);
        this.add(createBackButtonPanel());
    }


    public void addCreatePersonButtonActionListener() {
        this.createPerson.addActionListener(listener -> {
            String name = personName.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add the new person to the database, check if duplicate
            if (personController.addByName(name)){
                JOptionPane.showMessageDialog(this, "Person already exists", "Error", JOptionPane.ERROR_MESSAGE);

            }

            // Clear input field
            personName.setText("");
        });
    }

    public void addRemovePersonButtonActionListener() {
        this.removePerson.addActionListener(listener -> {
            Person selectedPerson = createdPersonList.getSelectedValue();
            if (selectedPerson == null) {
                JOptionPane.showMessageDialog(this, "No person selected", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!personController.remove(selectedPerson)){
                JOptionPane.showMessageDialog(this, "Person has tickets", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void addPersonDisp(Person person) {
        createdPersonListModel.addElement(person);
    }

    public void removePersonDisp(Person person) {
        createdPersonListModel.removeElement(person);
    }

    private JPanel createBackButtonPanel() {
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> navigationListener.switchPanel("Menu"));
        backPanel.add(backButton);
        return backPanel;
    }


}
