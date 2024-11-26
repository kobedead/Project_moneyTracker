package Databases;

import Person.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDatabase extends Database {

    private List<Person> personList;

    public PersonDatabase(){
        personList = new ArrayList<>();
    }


    public void addPerson(Person person){
        support.firePropertyChange("PersonAdded" , null , person );
        personList.add(person);
    }


}
