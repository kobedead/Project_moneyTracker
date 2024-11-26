package Controllers;

import Databases.Database;
import Databases.PersonDatabase;
import Person.Person;

public class PersonController {

    protected PersonDatabase db ;


    public PersonController(PersonDatabase database){
        db = database;
    }


    public void addPerson(Person person){

        db.addPerson(person);


    }



}
