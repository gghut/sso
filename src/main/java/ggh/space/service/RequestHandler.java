package ggh.space.service;

import com.alibaba.fastjson.JSONObject;
import ggh.space.annotation.RequestMapping;
import ggh.space.entity.Authentication;
import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;

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
    public void test(SsoRequest request, SsoResponse response) throws IOException {
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
