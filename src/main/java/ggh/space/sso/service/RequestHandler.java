package ggh.space.sso.service;

import com.alibaba.fastjson.JSONObject;
import ggh.space.sso.annotation.RequestMapping;
import ggh.space.sso.entity.Authentication;
import ggh.space.sso.http.GrantRequest;
import ggh.space.sso.http.GrantResponse;

import java.io.IOException;

/**
 * @author by ggh on 18-12-4.
 */
public class RequestHandler {

    private AuthenticationManager manager;

    public RequestHandler(AuthenticationManager manager){
        this.manager = manager;
    }

    @RequestMapping("/login")
    public void test(GrantRequest request, GrantResponse response) throws IOException {
         Authentication authentication = new Authentication();
         authentication.setUid(String.valueOf(1));
         authentication.generateToken();
         manager.setAuthentication(authentication);
         JSONObject json = new JSONObject();
         json.put("token", authentication.getToken());
         response.setToken(authentication.getToken());
         response.response(json);
    }
}
