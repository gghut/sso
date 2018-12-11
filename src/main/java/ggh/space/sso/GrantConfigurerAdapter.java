package ggh.space.sso;

import ggh.space.exception.*;
import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;
import ggh.space.service.GrantDispatcher;

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
        SsoResponse response = new SsoResponse((HttpServletResponse) servletResponse);
        try {
            if(!grantDispatcher.dispatcher(new SsoRequest((HttpServletRequest) servletRequest), new SsoResponse(response))){
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
