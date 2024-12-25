package Iterator;

<<<<<<<< HEAD:src/main/java/Iterator/TicketDBIterator.java

import Tickets.Ticket;

import java.util.List;

public class TicketDBIterator implements CustomIterator<Ticket> {


    private List<Ticket> databaseList ;
========
import Person.Person;

import java.util.List;

public class PersonDBIterator implements CustomIterator<Person> {


    private List<Person> databaseList;
>>>>>>>> 51ccd86 (GUI with unequal split and calctotal):src/Iterator/PersonDBIterator.java

    private int index = 0;


<<<<<<<< HEAD:src/main/java/Iterator/TicketDBIterator.java
    public TicketDBIterator(List<Ticket> databaseList){
========
    public PersonDBIterator(List<Person> databaseList) {
>>>>>>>> 51ccd86 (GUI with unequal split and calctotal):src/Iterator/PersonDBIterator.java
        this.databaseList = databaseList;
    }

    @Override
    public int getLenght() {
        return databaseList.size();
    }


    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean hasNext() {

        if (index == databaseList.size()) {
            //last one
            index = 0;
            return false;
        }
        return true;

    }

    @Override
    public Ticket getElement() {

        index++;
        return databaseList.get(index - 1);

    }

    @Override
    public void reset() {
<<<<<<<< HEAD:src/main/java/Iterator/TicketDBIterator.java
        index = 0 ;
    }

}
========
        index = 0;
    }
}
>>>>>>>> 51ccd86 (GUI with unequal split and calctotal):src/Iterator/PersonDBIterator.java
