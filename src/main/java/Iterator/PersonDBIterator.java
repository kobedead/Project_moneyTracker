package Iterator;

import Person.Person;

import java.util.List;

public class PersonDBIterator implements CustomIterator<Person> {


    private List<Person> databaseList;

    private int index = 0;


    public PersonDBIterator(List<Person> databaseList) {
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
    public Person getElement() {

        index++;
        return databaseList.get(index - 1);

    }

    @Override
    public void reset() {
        index = 0;
    }
}