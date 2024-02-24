package methodwrap.processor;

import com.google.auto.service.AutoService;
import methodwrap.LogAndRethrow;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

@SupportedAnnotationTypes("methodwrap.LogAndRethrow")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class LogAndRethrowAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.err.println("processing");

        boolean changed = false;
        for (Element element : roundEnv.getElementsAnnotatedWith(LogAndRethrow.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                processMethod((ExecutableElement) element);
                changed = true;
            }
        }

        return changed;
    }

    private void processMethod(ExecutableElement method) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("{");
        pw.println("    try {");
        pw.println("        System.err.println(\"Executing...\");");
        pw.println("        " + method.getSimpleName() + "();");
        pw.println("    } catch (Exception e) {");
        pw.println("        e.printStackTrace(System.err);");
        pw.println("        throw e;");
        pw.println("    }");
        pw.println("}");

        String tryCatchBlock = sw.toString();

        System.out.println(tryCatchBlock);
    }
}
