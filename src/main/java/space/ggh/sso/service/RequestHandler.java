package space.ggh.sso.service;

import com.alibaba.fastjson.JSONObject;
import space.ggh.sso.annotation.RequestMapping;
import space.ggh.sso.entity.Authentication;
import space.ggh.sso.http.GrantRequest;
import space.ggh.sso.http.GrantResponse;

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
         authentication.addParam("uid",String.valueOf(1));
         authentication.generateToken();
//         manager.setAuthentication(authentication);
         JSONObject json = new JSONObject();
         json.put("token", authentication.getToken());
         response.setToken(authentication.getToken());
         response.response(json);
    }
}
