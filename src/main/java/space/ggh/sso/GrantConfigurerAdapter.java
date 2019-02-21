package space.ggh.sso;

import space.ggh.sso.exception.CodeException;
import space.ggh.sso.http.GrantRequest;
import space.ggh.sso.http.GrantResponse;
import space.ggh.sso.service.AuthenticationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by ggh on 18-12-4.
 */
public class GrantConfigurerAdapter implements Filter {

    private AuthenticationManager authenticationManager = new AuthenticationManager();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        GrantResponse response = new GrantResponse((HttpServletResponse) servletResponse);
        try {
//            if(!grantDispatcher.dispatcher(new GrantRequest((HttpServletRequest) servletRequest), new GrantResponse(response))){
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
            List<String> publicUrls = getPublicUrls();
            String uri = new GrantRequest((HttpServletRequest) servletRequest).getRequestURI();
            if (publicUrls == null || !getPublicUrls().contains(uri)){
                Map<String, List<String>> roles = getRoleList();
                Map<String, List<String>> urlTemp = new HashMap<>(16);
                for (Map.Entry<String, List<String>> role:roles.entrySet()){
                    for (String url:role.getValue()){
                        if (urlTemp.containsKey(url)){
                            urlTemp.get(url).add(role.getKey());
                        }else {
                            urlTemp.put(url, new ArrayList<String>(){{
                                add(role.getKey());
                            }});
                        }
                    }
                }
                if(authenticationManager.authentication(urlTemp, new GrantRequest((HttpServletRequest) servletRequest))){
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }catch (CodeException e){
            response.responseException(e);
        }catch (Exception e){
            e.printStackTrace();
            response.responseException(e);
        }
    }

    /**
     * 获取用户权限列表
     * @return 权限列表,key-角色,value-该角色可访问的路径列表
     */
    public Map<String, List<String>> getRoleList(){
        Map<String, List<String>> roleList = new HashMap<>(16);
        roleList.put("USER", new ArrayList<String>(){{
            add("/**");
        }});
        return roleList;
    }

    public List<String> getPublicUrls(){
        return new ArrayList<>();
    }
}
