package ggh.space.sso.http;

import com.alibaba.fastjson.JSONObject;
import ggh.space.sso.exception.CodeException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author by ggh on 18-12-4.
 */
public class GrantResponse extends HttpServletResponseWrapper {

    public GrantResponse(HttpServletResponse response) {
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

    public void responseException(Exception e) throws IOException {
        setContentType("application/json;charset=UTF-8");
        setStatus(500);
        PrintWriter writer = getWriter();
        JSONObject json = new JSONObject();
        json.put("code", 100000);
        json.put("msg", "系统异常，请稍后再试");
        writer.print(json);
        writer.flush();
    }
}
