package ggh.space.sso.service;

import ggh.space.sso.entity.Authentication;
import ggh.space.sso.exception.NotYetLoginException;
import ggh.space.sso.exception.PermissionException;
import ggh.space.sso.http.GrantRequest;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by ggh on 18-12-4.
 */
public class AuthenticationManager extends TimerTask {

    private Map<String, Authentication> map = new HashMap<String, Authentication>();

    private long expire = 60000*30;

    public AuthenticationManager(){
        new Timer().schedule(this, 20000, expire);
    }

    @Override
    public void run(){
        long current = System.currentTimeMillis();
        for (Map.Entry<String, Authentication> entry: map.entrySet()){
            if (current- entry.getValue().getTimestamp() > expire+2000){
                map.remove(entry.getKey());
            }
        }
    }

    public void setAuthentication(Authentication authentication){
        map.put(authentication.getToken(), authentication);
    }


    public Authentication getAuthentication(String token){
        Authentication auth = map.get(token);
        if (auth != null && System.currentTimeMillis() - auth.getTimestamp() < expire){
            return auth;
        }else {
            return null;
        }
    }

    public boolean authentication(Map<Pattern, String> patterns, GrantRequest request){
        Authentication authentication = getAuthentication(request.getToken());
        if(authentication == null){
            throw new NotYetLoginException();
        }
        request.setAttribute("uid",authentication.getUid());
        for (Map.Entry<Pattern, String> entry: patterns.entrySet()){
            Matcher matcher = entry.getKey().matcher(request.getRequestURI());
            if(matcher.find()){
                Set<String> authorities = authentication.getAuthorities();
                if (authorities != null && authorities.contains(entry.getValue())){
                    return true;
                }
                throw new PermissionException();
            }
        }
        return true;
    }
}
