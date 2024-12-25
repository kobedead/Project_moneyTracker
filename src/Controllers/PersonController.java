package Controllers;

import Databases.Database;
import Iterator.CustomIterator;
import Person.Person;

public class PersonController implements Controller<Person> {

    protected Database<Person> db ;
    protected CustomIterator<Person> personIterator;

    public PersonController(Database<Person> database , CustomIterator<Person> personIterator){

        db = database;
        this.personIterator = personIterator;
    }


    public CustomIterator CreateIterator(){
        return db.createIterator();
    }


    public void addByName(String name){

        Boolean alreadyIn = false ;
        while (personIterator.hasNext()) {
            if(personIterator.getElement().getName().equals(name)){
                System.out.println("Error :  person is already in database");
                alreadyIn = true;
            }
        }

        if(!alreadyIn)
            db.add(new Person(name));

    }

    @Override
    public void add(Person object) {
        db.add(object);
    }

    @Override
    public void remove(Person person) {
        db.remove(person);
    }


}