package methodwrap.processor;

import methodwrap.LogAndRethrow;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Processor {
    public static void premain(String arguments, Instrumentation instrumentation) {
        System.err.println("Processor.premain");
        new AgentBuilder.Default()
                // .with(AgentBuilder.Listener.StreamWriting.toSystemError())
                // .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                // .disableClassFormatChanges()
                // .disableNativeMethodPrefix()
                .type(ElementMatchers.any())
                .transform((builder, typeDescription, classLoader, javaModule, protectionDomain) ->
                        // isAnnotatedWith(LogAndRethrow.class)
                        builder.method(ElementMatchers.any())
                                .intercept(Advice.to(ExceptionLoggingInterceptor.class))
                ).installOn(instrumentation);
        System.err.println("Processor.premain end");
    }

}

