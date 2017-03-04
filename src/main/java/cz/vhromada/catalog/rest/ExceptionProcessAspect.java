package cz.vhromada.catalog.rest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    //CHECKSTYLE.OFF: IllegalThrows
    public Object process(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (final Exception exception) {
            LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass()).error("Error in processing data.", exception);

            return new ResponseEntity<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
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
