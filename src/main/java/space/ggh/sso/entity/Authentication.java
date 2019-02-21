package space.ggh.sso.entity;

import java.util.*;

/**
 * @author by ggh on 18-12-4.
 */
public class Authentication {

    private long timestamp = System.currentTimeMillis();

    private String token;

    private Set<String> authorities = new HashSet<>();

    private Map<String, String> params = new HashMap<>();

    public String getToken(){
        return token;
    }

    public void generateToken(){
        UUID uuid = UUID.randomUUID();
        token = uuid.toString().replace("-", "");
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public void updateTimestamp(){
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp(){
        return timestamp;
    }

    public Map<String, String> getParams(){
        return params;
    }

    public void addParam(String key, String value){
        params.put(key, value);
    }
}
