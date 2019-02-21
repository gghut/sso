package space.ggh.sso.service;

import space.ggh.sso.annotation.RequestMapping;
import space.ggh.sso.http.GrantRequest;
import space.ggh.sso.http.GrantResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by ggh on 18-12-4.
 */
public class GrantDispatcher {

    private Map<String, Method> methodMap = new HashMap<String, Method>();

    private AuthenticationManager authenticationManager = new AuthenticationManager();

    private RequestHandler handler = new RequestHandler(authenticationManager);

    public GrantDispatcher(){
        Method[] methods = RequestHandler.class.getMethods();
        for (Method method : methods){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null){
                methodMap.put(requestMapping.value(), method);
            }
        }
    }

    public boolean dispatcher(GrantRequest request, GrantResponse response) throws InvocationTargetException, IllegalAccessException {
        String uri = request.getRequestURI();
        if (methodMap.containsKey(uri)){
            methodMap.get(uri).invoke(handler, request, response);
            return true;
        }else {
            return authenticationManager.authentication(new HashMap<>(16), request);
        }
    }
}