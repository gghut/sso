package ggh.space.http;

import com.alibaba.fastjson.JSONObject;
import ggh.space.exception.CodeException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author by ggh on 18-12-4.
 */
public class SsoResponse extends HttpServletResponseWrapper {

    public SsoResponse(HttpServletResponse response) {
        super(response);
    }

    public void setToken(String token){
        setHeader("Set-Cookie", "token="+token);
    }

    public void response(Object object) throws IOException {
        setContentType("application/json;charset=UTF-8");
        PrintWriter writer = getWriter();
        writer.print(object);
        writer.flush();
    }

    public void responseException(CodeException e) throws IOException {
        setContentType("application/json;charset=UTF-8");
        setStatus(e.getHttpStatusCode());
        PrintWriter writer = getWriter();
        JSONObject json = new JSONObject();
        json.put("code", e.getResponseCode());
        json.put("msg", e.getResponseMsg());
        writer.print(json);
        writer.flush();
    }
}
