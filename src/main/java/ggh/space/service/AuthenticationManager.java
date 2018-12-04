package ggh.space.service;

import ggh.space.entity.Authentication;
import ggh.space.exception.NotYetLoginException;
import ggh.space.exception.PermissionException;
import ggh.space.http.SsoRequest;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class AuthenticationManager {

    public abstract Authentication getAuthencation(String token);

    public void authentication(Map<Pattern, String> patterns, SsoRequest request){
        Authentication authentication = getAuthencation(request.getToken());
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
