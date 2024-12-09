package view.panels;

import Controllers.PersonController;
import Person.Person;


import javax.swing.*;

public class CreatePersonPanel extends JPanel {
    private JList<Person> createdPersonList;
    private DefaultListModel<Person> createdPersonListModel;

    private JButton createPerson ;
    private JButton removePerson ;

    private JTextField personName;

    private PersonController personController ;



    public CreatePersonPanel(PersonController personController ){

        this.personController = personController;

        JLabel label = new JLabel("CreatePerson");
        createdPersonListModel = new DefaultListModel<>();
        createdPersonList = new JList<>(createdPersonListModel);

        createPerson = new JButton("Create") ;
        removePerson = new JButton("Remove");

        personName = new JTextField(15);



        addcreateEmployeeButtonActionListener();
        addRemoveEmployeeButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(createdPersonList);
        this.add(personName);
        this.add(createPerson);
        this.add(removePerson);



    }


    public void addcreateEmployeeButtonActionListener() {
        this.createPerson.addActionListener(listener ->
        {

            String name = personName.getText();
            personController.addPerson(new Person(name, 0));


        });
    }

    public void addRemoveEmployeeButtonActionListener()
    {
        this.removePerson.addActionListener(listener ->
        {



        });
    }

    public void addPersonDisp(Person person){
        createdPersonListModel.addElement(person);
    }
    public void removePersonDisp(Person person){
        createdPersonListModel.removeElement(person);
    }

    public Person getSelectedPerson(){
        return createdPersonList.getSelectedValue();
    }
    public Person[] getAllPersons(){
        Person[] persons = new Person[createdPersonListModel.size()];
        for (int i = 0; i < createdPersonListModel.size(); i++) {
            persons[i] = createdPersonListModel.get(i);
        }
        return persons;
    }



}
