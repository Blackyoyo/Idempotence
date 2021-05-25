package main.com.ljd.idempotence;

public class Idempotence {
    private IdempotenceStorage storage;
    
    public Idempotence(IdempotenceStorage storage) {
        this.storage = storage;
    }
    
    public boolean saveIfAbsent(String idempotenceId) {
        return storage.saveIfAbsent(idempotenceId);
    }
    
    public boolean delete(String idempotenceId) {
        return storage.delete(idempotenceId);
    }

}
