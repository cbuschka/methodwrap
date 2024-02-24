package methodwrap;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LogAndRethrow {
}
