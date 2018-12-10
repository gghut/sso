package ggh.space.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author by ggh on 18-12-10.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EnableSsoSelector.class})
public @interface EnableSso {
}
