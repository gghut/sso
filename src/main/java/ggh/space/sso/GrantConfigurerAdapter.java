package ggh.space.sso;

import ggh.space.sso.http.GrantRequest;
import ggh.space.sso.http.GrantResponse;
import ggh.space.sso.service.GrantDispatcher;
import ggh.space.sso.exception.CodeException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by ggh on 18-12-4.
 */
public class GrantConfigurerAdapter implements Filter {

    private GrantDispatcher grantDispatcher;

    @Override
    public void init(FilterConfig filterConfig) {
        grantDispatcher = new GrantDispatcher();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        GrantResponse response = new GrantResponse((HttpServletResponse) servletResponse);
        try {
            if(!grantDispatcher.dispatcher(new GrantRequest((HttpServletRequest) servletRequest), new GrantResponse(response))){
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }catch (CodeException e){
            response.responseException(e);
        }catch (Exception e){
            e.printStackTrace();
            response.responseException(e);
        }
    }
}
