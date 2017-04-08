package cz.vhromada.catalog.rest;

import cz.vhromada.result.Result;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;

/**
 * A class represents aspect for processing exception.
 *
 * @author Vladimir Hromada
 */
@Aspect
public class ExceptionProcessAspect {

    /**
     * Process exception.
     *
     * @param proceedingJoinPoint proceeding join point
     * @return result of calling method
     * @throws Throwable if calling method fails
     */
    @Around("controllerPointcut()")
    @SuppressWarnings("MethodMayBeStatic")
    //CHECKSTYLE.OFF: IllegalThrows
    public Object process(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (final Exception exception) {
            LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass()).error("Error in processing data.", exception);

            return Result.error("ERROR", exception.toString());
        }
    }
    //CHECKSTYLE.OFF: IllegalThrows

    /**
     * Pointcut for controller layer
     */
    @Pointcut("execution(public * cz.vhromada.catalog..*Controller.*(..))")
    public void controllerPointcut() {
    }

}
