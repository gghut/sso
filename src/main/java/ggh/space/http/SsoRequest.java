package ggh.space.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by ggh on 18-12-4.
 */
public class SsoRequest extends HttpServletRequestWrapper {

    private Pattern pattern = Pattern.compile("token=([0-9a-z]+)");

    public SsoRequest(HttpServletRequest request) {
        super(request);
    }

    public String getToken() {
        String token = this.getParameter("token");
        if(token == null) {
            Matcher matcher = pattern.matcher(this.getHeader("Cookie"));
            if(matcher.find()) {
                return matcher.group(1);
            }
        }
        return token;
    }

    public String getParameterWithDefault(String key, String def){
        String username = getParameter(key);
        if(username == null){
            return def;
        }
        return username;
    }
}