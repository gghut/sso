package ggh.space.service;

import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class AuthenticationProvider {

    void authorization(SsoRequest request, SsoResponse response){

    }


}
