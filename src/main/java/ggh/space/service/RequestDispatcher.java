package ggh.space.service;

import ggh.space.annotation.RequestMapping;
import ggh.space.http.SsoRequest;
import ggh.space.http.SsoResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by ggh on 18-12-4.
 */
public class RequestDispatcher {

    private Map<String, Method> methodMap = new HashMap<String, Method>();

    private RequestHandler handler = new RequestHandler();

    public RequestDispatcher(){
        Method[] methods = RequestHandler.class.getMethods();
        for (Method method : methods){
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null){
                methodMap.put(requestMapping.value(), method);
            }
        }
    }

    public void filter(SsoRequest request, SsoResponse response){
        String uri = request.getRequestURI();
        if (methodMap.containsKey(uri)){
            try {
                methodMap.get(uri).invoke(handler, request, response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {

        }
    }
}