package space.ggh.sso.annotation;

import java.lang.annotation.*;

/**
 * @author by ggh on 18-12-4.
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {

    String value();
}
