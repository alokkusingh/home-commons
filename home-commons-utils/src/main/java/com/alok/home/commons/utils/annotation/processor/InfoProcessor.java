package com.alok.home.commons.utils.annotation.processor;

import com.alok.home.commons.utils.annotation.compiletime.CompileTimeInfo;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@AutoService(Processor.class) // this is to generate the META-INF/services/javax.annotation.processing.Processor file
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@SupportedAnnotationTypes("com.alok.home.commons.utils.annotation.compiletime.CompileTimeInfo")
public class InfoProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            // Get all elements (e.g., classes) annotated with @CompileTimeInfo
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                CompileTimeInfo info = element.getAnnotation(CompileTimeInfo.class);
                String message = "Processing class: " + element.getSimpleName() +
                        ", Author: " + info.author() +
                        ", Version: " + info.version();

//                System.out.println(message); // Print to console during compilation
                // Output a warning during compilation
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message, element);
            }
        }
        return true; // Claim that we've processed these annotations
    }
}

