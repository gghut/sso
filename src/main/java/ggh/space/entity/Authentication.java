package ggh.space.entity;

import java.util.Set;

/**
 * @author by ggh on 18-12-4.
 */
public class Authentication {

    private String uid;

    private Set<String> authorities;

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
}
