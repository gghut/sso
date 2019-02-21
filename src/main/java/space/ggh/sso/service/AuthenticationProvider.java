package space.ggh.sso.service;

import space.ggh.sso.http.GrantRequest;
import space.ggh.sso.http.GrantResponse;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class AuthenticationProvider {

    void authorization(GrantRequest request, GrantResponse response){

    }


}
