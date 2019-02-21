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
public class AuthenticationManager {

    private AuthenticationStorageManager storageManager = AuthenticationStorageManager.instance();

    public boolean authentication(Map<String, List<String>> patterns, GrantRequest request){
        String uri = request.getRequestURI();
        Authentication authentication = storageManager.get(request.getToken());
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
