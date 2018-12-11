package ggh.space.service;

import ggh.space.annotation.RequestMapping;
import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

    public boolean dispatcher(SsoRequest request, SsoResponse response) throws InvocationTargetException, IllegalAccessException {
        String uri = request.getRequestURI();
        if (methodMap.containsKey(uri)){
            methodMap.get(uri).invoke(handler, request, response);
            return true;
        }else {
            return authenticationManager.authentication(new HashMap<>(), request);
        }
    }
}