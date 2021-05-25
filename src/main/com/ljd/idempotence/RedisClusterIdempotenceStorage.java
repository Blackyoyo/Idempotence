package main.com.ljd.idempotence;

public class RedisClusterIdempotenceStorage implements IdempotenceStorage {
    private JedisCluster jedisCluster;
    
    public RedisIdempotenceStorage(String redisClusterAddress, GenericObjectPoolConfig config) {    
        Set<HostAndPort> redisNodes = parseHostAndPorts(redisClusterAddress);    
        this.jedisCluster = new JedisCluster(redisNodes, config);  
    }
    
    public RedisIdempotenceStorage(JedisCluster jedisCluster) {    
        this.jedisCluster = jedisCluster;  
    }
    
    @Override
    public boolean saveIfAbsent(String idempotenceId) {    
        Long success = jedisCluster.setnx(idempotenceId, "1");    
        return success == 1;  
    }
    
    @Override
    public boolean delete(String idempotenceId) {    
        return jedisCluster.del(idempotenceId);  
    }
    
    @VisibleForTesting  protected Set<HostAndPort> parseHostAndPorts(String redisClusterAddress) {    
        String[] addressArray= redisClusterAddress.split(";");    
        Set<HostAndPort> redisNodes = new HashSet<>();    
        for (String address : addressArray) {      
                String[] hostAndPort = address.split(":");      
                redisNodes.add(new HostAndPort(hostAndPort[0], Integer.valueOf(hostAndPort[1])));    
            }    
        return redisNodes;
    }

}
