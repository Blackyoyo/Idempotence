package main.com.ljd.idempotence;

public interface IdempotenceStorage {
    
    boolean saveIfAbsent(String idempotenceId);
    
    boolean delete(String idempotenceId);
    
}
