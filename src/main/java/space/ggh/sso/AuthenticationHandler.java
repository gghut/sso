package space.ggh.sso;

import java.util.Collection;
import java.util.Map;

/**
 * @author by ggh on 19-2-21.
 */
public interface AuthenticationHandler {

    /**
     * 参数k-v键值对将会在请求request中以attribute的形势携带
     * @param roles 角色列表
     * @param params 参数列表
     * @return 角色列表
     */
    String initToken(Collection<String> roles,Map<String, String> params);

    boolean deleteToken(String token);

    String initCaptcha(long timestamp);

    boolean verifyCaptcha(long timestamp, int random);
}
