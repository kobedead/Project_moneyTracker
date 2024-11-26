package Databases;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class Database
{



    protected static Map<Class<?>, Database> instance = new HashMap<>() ;

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






    // Add a property change listener
    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Remove a property change listener
    public void removeObserver(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }


}
