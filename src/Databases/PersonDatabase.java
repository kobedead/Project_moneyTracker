package Databases;

import Iterator.CustomIterator;
import Iterator.PersonDBIterator;
import Person.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDatabase extends Database<Person> {

    private List<Person> personList;

    public PersonDatabase(){
        personList = new ArrayList<>();
    }


    public CustomIterator<Person> createIterator(){
        return new PersonDBIterator(personList);
    }

    public void add(Person person){
        support.firePropertyChange("PersonAdded" , null , person );
        personList.add(person);
    }

    public void remove(Person person){
        //if no tickets
    }

}
