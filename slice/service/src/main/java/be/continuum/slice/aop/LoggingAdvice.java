package be.continuum.slice.aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Type;

/**
 * LoggingAdvice
 *
 * @author bartgerard
 * @version v0.0.1
 */
@Component
@Aspect
public class LoggingAdvice {

    /**
     * Advice that will be executed around each join point that has been selected by the
     * 'loggingPointcut()' pointcut.  This advice will log the entry, exit of the joint put
     * including the available contextual information.
     *
     * @param call exposes the proceed(..) method in order to support around advice in @AJ aspects
     * @return the method return value
     * @throws Throwable any throwable thrown by the execution
     */
    @Around("be.continuum.slice.aop.PointCutLibrary.springRestControllerOperation()")
    public Object log(final ProceedingJoinPoint call) throws Throwable {
        // Get the logger that handles the target instance of the method execution.
        final Signature signature = call.getSignature();
        final Logger logger = LogManager.getLogger(signature.getDeclaringType());

        // This aspect is only valid for method calls
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("call not valid [method call expected]");
        }
        final MethodSignature methodSignature = (MethodSignature) signature;

        // Compute the method information
        final String methodName = signature.toShortString();
        final String targetName = signature.getDeclaringTypeName();
        final Type returnType = methodSignature.getReturnType();

        // AspectJ compiler has to be used to retrieve the parameter names
        //String[] pp = methodSignature.getParameterNames();

        // Compute the method parameters and trace the method entry
        if (logger.isInfoEnabled()) {
            logger.info(signature.toLongString());
            logger.info(String.format("Invoke method [%s] on service [%s]", methodName, targetName));

            final Object[] parameterValues = call.getArgs();
            String[] parameterNames = new String[parameterValues.length];

            if ((methodSignature.getParameterNames() != null) && methodSignature.getParameterNames().length == parameterNames.length) {
                parameterNames = methodSignature.getParameterNames();
            }

            for (int i = 0; i < parameterValues.length; i++) {
                final String parameterName = (parameterNames[i] == null ? String.valueOf(i) : parameterNames[i]);
                final String parameterValue;

                if ((parameterValues[i] instanceof byte[]) && (parameterValues[i] != null)) {
                    parameterValue = String.format("byte array length [%s]", ((byte[]) parameterValues[i]).length);
                } else {
                    parameterValue = String.valueOf(parameterValues[i]);
                }

                logger.info(String.format("|---parameter [%s] = [%s]", parameterName, parameterValue));
            }
        }

        // Prepare a StopWatch for computing the method statistic
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String methodStatus = "SUCCESSFULLY";
        Object methodResult = null;
        try {
            // Proceed with the next advice or target method invocation
            methodResult = call.proceed();
            return methodResult;
        } catch (Exception throwable) {
            logger.info(String.format("Method [%s] raised the following exception :\n%s.", methodName, String.valueOf(throwable)));
            methodStatus = "UNSUCCESSFULLY";
            throw throwable;
        } finally {
            stopWatch.stop();
            final long millis = stopWatch.getTotalTimeMillis();

            if (logger.isInfoEnabled()) {
                if (returnType != null) {
                    logger.info(String.format("|---parameter [return]= [%s]", String.valueOf(methodResult)));
                }
                logger.info(String.format("Method [%s] on service [%s] %s invoked in %s milliseconds.", methodName, targetName, methodStatus, millis));
            }
        }
    }

}
