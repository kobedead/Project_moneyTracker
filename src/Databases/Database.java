package Databases;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import Iterator.CustomIterator;

public abstract class Database<T>
{



    protected static Map<Class<?>, Database> instance = new HashMap<>() ;   //per class maar 1 datanbase (singleton)

    public final PropertyChangeSupport support;





    protected Database()
    {
        support = new PropertyChangeSupport(this);

    }



    //double-checked locking for multithreading safety
    public static Database getInstance(Class<? extends Database> specDatabase) {
        if (!instance.containsKey(specDatabase)) {
            synchronized (Database.class){
                if (!instance.containsKey(specDatabase)){
                    try {
                        instance.put(specDatabase , specDatabase.getDeclaredConstructor().newInstance() ) ;
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance.get(specDatabase);
    }


    public abstract CustomIterator<T> createIterator();


    // Add a property change listener
    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Remove a property change listener
    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public abstract void add(T object);
    public abstract void remove(T object);




}
