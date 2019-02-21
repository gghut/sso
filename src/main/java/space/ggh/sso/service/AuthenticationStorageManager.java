package space.ggh.sso.service;

import space.ggh.sso.entity.Authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author by ggh on 19-2-21.
 */
public class AuthenticationStorageManager extends TimerTask {

    private long expire = 60000*30;

    private static AuthenticationStorageManager manager;

    private Map<String, Authentication> map = new HashMap<>();

    private AuthenticationStorageManager(){
        new Timer().schedule(this, 20000, expire);
    }

    @Override
    public void run() {
        long current = System.currentTimeMillis();
        long p = expire+2000;
        map.forEach((key, value)->{
            if (current- value.getTimestamp() > p){
                map.remove(key);
            }
        });
    }

    public static AuthenticationStorageManager instance(){
        if (manager == null){
            synchronized (AuthenticationStorageManager.class){
                if (manager == null){
                    manager = new AuthenticationStorageManager();
                }
            }
        }
        return manager;
    }

    public void put(Authentication authentication){
        map.put(authentication.getToken(), authentication);
    }

    public Authentication get(String token){
        Authentication auth = map.get(token);
        if (auth != null && System.currentTimeMillis() - auth.getTimestamp() < expire){
            return auth;
        }else {
            return null;
        }
    }
}
