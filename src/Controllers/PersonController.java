package Controllers;

import Databases.Database;
import Databases.PersonDatabase;
import Iterator.Iterator;
import Person.Person;

public class PersonController implements Controller<Person> {

    protected PersonDatabase db ;


    public PersonController(PersonDatabase database){
        db = database;
    }


    public  Iterator CreateIterator(){
        return db.createIterator();
    }

    @Override
    public void add(Person person){

        db.add(person);


    }

    @Override
    public void remove(Person person) {
        db.remove(person);
    }


}
