package ggh.space.exception;

/**
 * @author by ggh on 18-12-4.
 */
public class NotYetLoginException extends CodeException {
    @Override
    public int getResponseCode() {
        return 0;
    }

    @Override
    public int getHttpStatusCode() {
        return 0;
    }

    @Override
    public String getResponseMsg() {
        return null;
    }
}
