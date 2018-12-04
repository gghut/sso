package ggh.space.service;

/**
 * @author by ggh on 18-12-4.
 */
public interface UserService {

    /**
     * 根据用户名称获取用户数据
     * @param username 用户名称
     * @return 用户信息
     */
    UserDetails loadUserByUsername(String username);
}
