package ua.hnure.eshop.aop;

import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Aspect
@Slf4j
@Component
public class EShopServiceLoggerAspect {

    private static final String START_MESSAGE_PATTERN = "%s.%s.E";
    private static final String PAIR_ARGUMENT_PATTER = "%s: %s";
    private static final String JOIN_DELIMITER = ", ";
    private static final String JOIN_PREFIX = ": ";
    private static final String JOIN_SUFFIX = "";

    @SneakyThrows
    @Around("onlyServicePackages()")
    public Object loggingService(final ProceedingJoinPoint joinPoint) {
        log.info(getStartLogMessage(joinPoint));

        final Object result = joinPoint.proceed();

        log.info("finish");

        return result;
    }

    @Pointcut("execution(* ua.hnure.eshop.service..*.*(..)))")
    private void onlyServicePackages() {
    }

    private String getStartLogMessage(final ProceedingJoinPoint joinPoint) {
        final String methodName = joinPoint.getSignature().getName();
        final String className = toClassName(joinPoint.getSignature().getDeclaringType());
        return String.format(START_MESSAGE_PATTERN, className, methodName) + getArgumentParamNames(joinPoint);
    }

    public String getArgumentParamNames(final ProceedingJoinPoint joinPoint){
        final Object[] args = joinPoint.getArgs();
        final CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        final String[] paramNames = codeSignature.getParameterNames();
        final StringJoiner stringJoiner = new StringJoiner(JOIN_DELIMITER, JOIN_PREFIX, JOIN_SUFFIX);

        for(int i = 0; i < paramNames.length; i++){
            final String argument = String.format(PAIR_ARGUMENT_PATTER, paramNames[i], args[i]);
            stringJoiner.add(argument);
        }
        return stringJoiner.toString();
    }

    private String toClassName(final Class<?> classType) {
        final String packageName = classType.getPackageName();
        final String[] packageParts = packageName.split("\\.");
        final String formattedPackage = Stream.of(packageParts)
                                              .map(classPart -> classPart.charAt(0))
                                              .map(String::valueOf)
                                              .collect(Collectors.joining("."));
        final String className = classType.getSimpleName();
        return String.join(".", formattedPackage, className);
    }
}