package Databases;
import java.util.HashMap;

public class CustomDict<K, V extends Number> extends HashMap<K, V> {

    @Override
    public V put(K key, V value) {
        if (this.containsKey(key)) {
            V oldValue = this.get(key);
            V newValue = addValues(oldValue , value);
            return super.put(key, newValue);
        } else {
            // Otherwise, just set the new value
            return super.put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    private V addValues(V oldValue, V newValue) {
        if (oldValue instanceof Integer) {
            return (V) (Integer) (((Integer) oldValue) + ((Integer) newValue));
        } else if (oldValue instanceof Double) {
            return (V) (Double) (((Double) oldValue) + ((Double) newValue));
        } else if (oldValue instanceof Float) {
            return (V) (Float) (((Float) oldValue) + ((Float) newValue));
        } else if (oldValue instanceof Long) {
            return (V) (Long) (((Long) oldValue) + ((Long) newValue));
        } else {
            throw new UnsupportedOperationException("Unsupported numeric type");
        }
    }



}