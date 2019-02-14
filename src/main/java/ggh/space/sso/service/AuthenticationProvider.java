package ggh.space.sso.service;

import ggh.space.sso.http.GrantRequest;
import ggh.space.sso.http.GrantResponse;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class AuthenticationProvider {

    void authorization(GrantRequest request, GrantResponse response){

    }


}
