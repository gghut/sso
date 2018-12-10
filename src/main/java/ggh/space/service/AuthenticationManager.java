package ggh.space.service;

import ggh.space.entity.Authentication;
import ggh.space.exception.NotYetLoginException;
import ggh.space.exception.PermissionException;
import ggh.space.http.SsoRequest;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class AuthenticationManager extends TimerTask {

    private Map<String, Authentication> map = new HashMap<String, Authentication>();

    private long expire = 60000*30;

    public AuthenticationManager(){
        new Timer().schedule(this, 20000, expire);
    }

    @Override
    public void run(){
        long current = System.currentTimeMillis();
        for (Map.Entry<String, Authentication> entry: map.entrySet()){
            if (current- entry.getValue().getTimestamp() > expire){
                map.remove(entry.getKey());
            }
        }
    }

    public Authentication getAuthentication(String token){
        if (true){
            Authentication auth = map.get(token);
            if (System.currentTimeMillis() - auth.getTimestamp() > expire){
                return auth;
            }
        }
        return null;
    }

    public void authentication(Map<Pattern, String> patterns, SsoRequest request){
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
                    return;
                }
                throw new PermissionException();
            }
        }
    }
}
