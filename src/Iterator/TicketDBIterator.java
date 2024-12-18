package Iterator;

import Tickets.Ticket;

import java.util.List;

public class TicketDBIterator implements CustomIterator<Ticket> {


    private List<Ticket> databaseList ;

    private int index = 0;


    public TicketDBIterator(List<Ticket> databaseList){
        this.databaseList = databaseList;
    }
    @Override
    public int getLenght(){
        return databaseList.size();
    }


    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean hasNext() {

        if(index == databaseList.size()){
            //last one
            index = 0 ;
            return false;
        }
        return true;

    }

    @Override
    public Ticket getElement() {

        index++;
        return databaseList.get(index-1);

    }

    @Override
    public void skip() {
        index++ ;
    }

    @Override
    public void back() {
        index--;
    }
}
