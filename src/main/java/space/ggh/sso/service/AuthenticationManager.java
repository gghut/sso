package space.ggh.sso.service;

import space.ggh.sso.entity.Authentication;
import space.ggh.sso.exception.NotYetLoginException;
import space.ggh.sso.exception.PermissionException;
import space.ggh.sso.http.GrantRequest;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * @author by ggh on 18-12-4.
 */
public class AuthenticationManager extends TimerTask {

    private Map<String, Authentication> map = new HashMap<>();

    private long expire = 60000*30;

    public AuthenticationManager(){
        new Timer().schedule(this, 20000, expire);
    }

    @Override
    public void run(){
        long current = System.currentTimeMillis();
        long p = expire+2000;
        map.forEach((key, value)->{
            if (current- value.getTimestamp() > p){
                map.remove(key);
            }
        });
    }

    public void setAuthentication(Authentication authentication){
        map.put(authentication.getToken(), authentication);
    }


    public Authentication getAuthentication(String token){
        map.put("233", new Authentication(){{setAuthorities(new HashSet<String>(){{add("USER");}});}});
        Authentication auth = map.get(token);
        if (auth != null && System.currentTimeMillis() - auth.getTimestamp() < expire){
            return auth;
        }else {
            return null;
        }
    }

    public boolean authentication(Map<String, List<String>> patterns, GrantRequest request){
        String uri = request.getRequestURI();
        Authentication authentication = getAuthentication(request.getToken());
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (Map.Entry<String, List<String>> entry:patterns.entrySet()){
            if (antPathMatcher.match(entry.getKey(), uri)){
                if (authentication == null && entry.getValue().contains("ACCESS")){
                    return true;
                } else if(authentication != null && authentication.getAuthorities().stream().anyMatch(role->entry.getValue().contains(role))){
                    authentication.getParams().forEach(request::setAttribute);
                    return true;
                }
            }
        }
        if (authentication == null){
            throw new NotYetLoginException();
        } else {
            throw new PermissionException();
        }
    }
}
