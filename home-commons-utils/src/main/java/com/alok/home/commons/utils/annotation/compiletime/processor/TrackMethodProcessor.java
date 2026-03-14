package com.alok.home.commons.utils.annotation.compiletime.processor;

import com.alok.home.commons.utils.annotation.compiletime.TrackMethod;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@AutoService(Processor.class) // this is to generate the META-INF/services/javax.annotation.processing.Processor file
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes("com.alok.home.commons.utils.annotation.compiletime.TrackMethod")
public class TrackMethodProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(TrackMethod.class)) {
            if (element.getKind() == ElementKind.METHOD) {
                ExecutableElement method = (ExecutableElement) element;
                String methodName = method.getSimpleName().toString();
//                System.out.println("Compiling method: " + methodName);
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Compiling method: " + methodName, element);
            }
        }
        return true; // No further processing of this annotation type
    }
}