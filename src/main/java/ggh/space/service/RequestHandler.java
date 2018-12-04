package ggh.space.service;

import ggh.space.annotation.RequestMapping;
import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;

/**
 * @author by ggh on 18-12-4.
 */
public class RequestHandler {

    @RequestMapping("/test")
    public void test(SsoRequest request, SsoResponse response){

    }
}
