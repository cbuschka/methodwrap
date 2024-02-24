package methodwrap.processor;

import net.bytebuddy.asm.Advice;

public class ExceptionLoggingInterceptor {
    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin String method) {
        System.err.println("Entering method: " + method);
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Origin String method, @Advice.Return Object result, @Advice.Thrown Throwable throwable) {
        if (throwable != null) {
            System.err.println("Exception in method " + method + ": " + throwable.getMessage());
        } else {
            System.err.println("Result of " + method + ": " + result);
        }
    }
}
