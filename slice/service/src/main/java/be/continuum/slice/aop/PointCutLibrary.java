package be.continuum.slice.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * PointCutLibrary
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class PointCutLibrary {

    /**
     * Matches the execution of any method in package with prefix be.continuum.slice.
     */
    @Pointcut("within(be.continuum.slice..*)")
    public void withinSlice() {
    }

    /**
     * Matches the execution of any public method.
     */
    @Pointcut("execution(public * *(..))")
    public void anyPublicOperation() {
    }

    /**
     * Matches the execution of any public operation within slice.
     */
    @Pointcut("anyPublicOperation() && withinSlice()")
    public void sliceOperation() {
    }

    /**
     * Matches the execution of any public general operation, where the code
     * executing is declared in a type with an @Service annotation.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void springServiceOperation() {
    }

    /**
     * Matches the execution of any public general operation, where the code
     * executing is declared in a type with an @Repository annotation.
     */
    @Pointcut("sliceOperation() && within(@org.springframework.stereotype.Repository *)")
    public void springRepositoryOperation() {
    }

    /**
     * Matches the execution of any public general operation, where the code
     * executing is declared in a type with an @Controller annotation.
     */
    @Pointcut("sliceOperation() && within(@org.springframework.stereotype.Controller *)")
    public void springControllerOperation() {
    }

    @Pointcut("sliceOperation() && within(@org.springframework.web.bind.annotation.RestController *)")
    public void springRestControllerOperation() {
    }

}
