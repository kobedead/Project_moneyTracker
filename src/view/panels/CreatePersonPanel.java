// Adjustments to fix the issue where pressing the Add Person button does not display a new frame

package view.panels;

import Controllers.PersonController;
import Person.Person;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Objects;

public class CreatePersonPanel extends JPanel {
    private JList<Person> createdPersonList;
    private DefaultListModel<Person> createdPersonListModel;

    private JButton createPerson;
    private JButton removePerson;

    private JTextField personName;

    private PersonController personController;

    public CreatePersonPanel(PersonController personController) {
        this.personController = personController;

        JLabel label = new JLabel("Create Person");
        createdPersonListModel = new DefaultListModel<>();
        createdPersonList = new JList<>(createdPersonListModel);

        createPerson = new JButton("Create");
        removePerson = new JButton("Remove");

        personName = new JTextField(10);

        addCreatePersonButtonActionListener();
        addRemovePersonButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(new JScrollPane(createdPersonList)); // Ensure the list is scrollable
        this.add(personName);
        this.add(createPerson);
        this.add(removePerson);
    }

    public void addSelectListListener(ListSelectionListener listener) {
        this.createdPersonList.addListSelectionListener(listener);
    }

    public void addCreatePersonButtonActionListener() {
        this.createPerson.addActionListener(listener -> {
            String name = personName.getText();
            if (name.isBlank()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for duplicate names
            for (int i = 0; i < createdPersonListModel.size(); i++) {
                if (Objects.equals(createdPersonListModel.get(i).getName(), name)) {
                    JOptionPane.showMessageDialog(this, "Person already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Add the new person to the database
            Person newPerson = new Person(name);
            personController.add(newPerson);

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

            // Check if the person can be safely removed
            personController.remove(selectedPerson);
        });
    }

    public void addPersonDisp(Person person) {
        createdPersonListModel.addElement(person);
    }

    public void removePersonDisp(Person person) {
        createdPersonListModel.removeElement(person);
    }

    public Person getSelectedPerson() {
        return createdPersonList.getSelectedValue();
    }

    public JList<Person> getCreatedPersonList() {
        return createdPersonList;
    }

    private JPanel createBackButtonPanel() {
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> switchPanel("Menu"));
        backPanel.add(backButton);
        return backPanel;
    }


}
