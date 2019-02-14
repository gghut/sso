package ggh.space.sso.exception;

/**
 * @author by ggh on 18-12-4.
 */
public class NotYetLoginException extends CodeException {
    @Override
    public int getResponseCode() {
        return 200005;
    }

    @Override
    public int getHttpStatusCode() {
        return 403;
    }

    @Override
    public String getResponseMsg() {
        return "not yet login";
    }
}
