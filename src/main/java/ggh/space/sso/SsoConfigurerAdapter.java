package ggh.space.sso;

import ggh.space.exception.*;
import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;
import ggh.space.service.RequestDispatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by ggh on 18-12-4.
 */
public class SsoConfigurerAdapter implements Filter {

    private RequestDispatcher requestDispatcher;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SsoResponse response = new SsoResponse((HttpServletResponse) servletResponse);
        try {
            requestDispatcher.filter(new SsoRequest((HttpServletRequest) servletRequest), new SsoResponse(response));
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (CodeException e){
            response.responseException(e);
        }catch (Exception e){
            response.setStatus(500);
            e.printStackTrace();
        }
    }
}
