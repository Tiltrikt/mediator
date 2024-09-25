package dev.tiltrikt.mediator.pattern;

import dev.tiltrikt.mediator.repository.SimpleContextRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

@Component("myMethodInterceptor")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContextInterceptor implements MethodInterceptor {

    @NotNull SimpleContextRepository contextRepository;

    @Override public Object invoke(@NotNull MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        Type genericReturnType = method.getGenericReturnType();
        ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
        String typeName = parameterizedType.getActualTypeArguments()[0].getTypeName();
        String className = typeName.split("<")[0];
        Class<?> clazz = Class.forName(className);
        return contextRepository.findBy(clazz, Arrays.stream(invocation.getArguments())
            .map((f) -> (Class<?>) f)
            .toArray(Class[]::new)
        );
    }
}