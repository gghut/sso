package space.ggh.sso.exception;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class AbstractCodeException extends RuntimeException{

    /**
     * 获取响应码
     * @return 响应码
     */
    public abstract int getResponseCode();

    /**
     * 获取http状态码
     * @return 状态码
     */
    public abstract int getHttpStatusCode();

    /**
     * 获取响应提示信息
     * @return 提示信息
     */
    public abstract String getResponseMsg();
}
