package space.ggh.sso.service;

import space.ggh.sso.AuthenticationHandler;
import space.ggh.sso.entity.Authentication;
import space.ggh.sso.http.GrantRequest;
import space.ggh.sso.http.GrantResponse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * @author by ggh on 18-12-4.
 */
public class AuthenticationProvider implements AuthenticationHandler{

    private AuthenticationStorageManager authenticationManager = AuthenticationStorageManager.instance();

    @Override
    public String initToken(Collection<String> roles, Map<String, Object> params) {
        Authentication authentication = new Authentication(){{setAuthorities(roles);}};
        authentication.generateToken();
        authentication.setParams(params);
        authenticationManager.put(authentication);
        return authentication.getToken();
    }

    @Override
    public boolean deleteToken(String token) {
        return false;
    }

    @Override
    public String initCaptcha(long timestamp) {
        return null;
    }

    @Override
    public boolean verifyCaptcha(long timestamp, int random) {
        return false;
    }
}
