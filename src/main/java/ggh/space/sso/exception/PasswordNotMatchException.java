package ggh.space.sso.exception;

/**
 * @author by ggh on 18-12-4.
 */
public class PasswordNotMatchException extends CodeException {
    @Override
    public int getResponseCode() {
        return 0;
    }

    @Override
    public int getHttpStatusCode() {
        return 403;
    }

    @Override
    public String getResponseMsg() {
        return null;
    }
}
