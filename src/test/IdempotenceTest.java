package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.com.ljd.idempotence.CacheIdempotenceStorage;
import main.com.ljd.idempotence.Idempotence;
import main.com.ljd.idempotence.IdempotenceIdGenerator;

public class IdempotenceTest {
    private static final Logger log = LoggerFactory.getLogger(IdempotenceTest.class);
    public static void main(String[] args) {
        IdempotenceIdGenerator idGenerator = new IdempotenceIdGenerator();
        Idempotence idempotence = new Idempotence(new CacheIdempotenceStorage());
        
        String id = idGenerator.generateId();
        log.info("UUID = " + id);
        
        boolean flag = idempotence.saveIfAbsent(id);
        log.info("save : " + String.valueOf(flag));
        
        flag = idempotence.saveIfAbsent(id);
        log.info("save agin : " + String.valueOf(flag));
        
        flag = idempotence.delete(id);
        log.info("delete : " + String.valueOf(flag));
        
        flag = idempotence.delete("noabsent");
        log.info("delete not absent : " + String.valueOf(flag));
        
    }

}
