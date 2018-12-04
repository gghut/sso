package ggh.space.exception;

/**
 * @author by ggh on 18-12-4.
 */
public abstract class CodeException extends RuntimeException{

    public abstract int getResponseCode();

    public abstract int getHttpStatusCode();

    public abstract String getResponseMsg();
}
