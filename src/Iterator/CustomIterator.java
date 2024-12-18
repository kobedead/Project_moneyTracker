package Iterator;

public interface CustomIterator<T> {


    int getLenght();
    int getIndex();
    boolean hasNext();
    T getElement();

    void skip();
    void back();





}
