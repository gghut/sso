package ggh.space.sso.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author by ggh on 18-12-4.
 */
public class Authentication {

    private long timestamp = System.currentTimeMillis();

    private String token;

    private String uid;

    private Set<String> authorities = new HashSet<>();

    public String getToken(){
        return token;
    }

    public void generateToken(){
        UUID uuid = UUID.randomUUID();
        token = uuid.toString().replace("-", "");
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}
