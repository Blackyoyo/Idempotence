package main.com.ljd.idempotence;

import java.util.HashSet;
import java.util.Set;

public class CacheIdempotenceStorage implements IdempotenceStorage {
    private Set<String> set = new HashSet<String>();
    
    @Override
    public boolean saveIfAbsent(String idempotenceId) {
        if(set.contains(idempotenceId)) {
            return false;
        }
        set.add(idempotenceId);
        return true;
    }

    @Override
    public boolean delete(String idempotenceId) {
        if(set.contains(idempotenceId)) {
            set.remove(idempotenceId);
            return true;
        }
        return false;
    }

}
