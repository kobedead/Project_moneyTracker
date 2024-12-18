package view.panels;

import Controllers.PersonController;
import Person.Person;


import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.Objects;

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

        personName = new JTextField(10);



        addcreateEmployeeButtonActionListener();
        addRemoveEmployeeButtonActionListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(label);
        this.add(createdPersonList);
        this.add(personName);
        this.add(createPerson);
        this.add(removePerson);


    }

    public void addSelectListLister(ListSelectionListener listener){
        this.createdPersonList.addListSelectionListener(listener);
    }



    public void addcreateEmployeeButtonActionListener() {
        this.createPerson.addActionListener(listener ->
        {

            String name = personName.getText();

            //check how to cast whiole array to Person object
            Object[] oPersons =  createdPersonListModel.toArray();


            for (Object oPerson : oPersons ){
                Person person = (Person) oPerson;                   //waste ig
                if (Objects.equals(person.getName(), name)) {
                    return;
               }

            }

            personController.add(new Person(name));

        });
    }

    public void addRemoveEmployeeButtonActionListener()
    {
        this.removePerson.addActionListener(listener ->
        {

            //only possible if person doesnt have ticket

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
    public Object[] getAllPersons(){
        return createdPersonListModel.toArray();
    }



}
