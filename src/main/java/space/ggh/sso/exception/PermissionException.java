package space.ggh.sso.exception;

/**
 * @author by ggh on 18-12-4.
 */
public class PermissionException extends AbstractCodeException {
    @Override
    public int getResponseCode() {
        return 200007;
    }

    @Override
    public int getHttpStatusCode() {
        return 403;
    }

    @Override
    public String getResponseMsg() {
        return "permission denied";
    }
}
