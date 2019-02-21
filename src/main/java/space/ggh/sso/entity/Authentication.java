package space.ggh.sso.entity;

import java.util.*;

/**
 * @author by ggh on 18-12-4.
 */
public class Authentication {

    private long timestamp = System.currentTimeMillis();

    private String token;

    private Collection<String> authorities = new HashSet<>();

    private Map<String, Object> params = new HashMap<>();

    public String getToken(){
        return token;
    }

    public void generateToken(){
        UUID uuid = UUID.randomUUID();
        token = uuid.toString().replace("-", "");
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    public void updateTimestamp(){
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp(){
        return timestamp;
    }

    public Map<String, Object> getParams(){
        return params;
    }

    public void addParam(String key, String value){
        params.put(key, value);
    }

    public void setParams(Map<String, Object> params){
        this.params = params;
    }
}
