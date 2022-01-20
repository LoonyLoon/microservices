package microservice.lesson1.logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect

public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(LoggerExecutor)")
    public void logsPointCut() {	}

    @Before("logsPointCut()")
    public void logBefore(JoinPoint joinPoint) {
        if(logger.isInfoEnabled())
            logger.info("\nBefore \nMethod <"+joinPoint.getSignature().getName() + "> with args: " + joinPoint.getArgs()[0]);
    }

    @After("logsPointCut()")
    public void logAfter(JoinPoint joinPoint) {
        if(logger.isInfoEnabled())
            logger.info("\nAfter \nMethod <"+joinPoint.getSignature().getName() + ">");
    }

    @AfterReturning(pointcut = "logsPointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        if(logger.isInfoEnabled())
            logger.info("\nAfterReturning \nMethod <"+joinPoint.getSignature().getName() + "> return: " + result.toString());
    }

}

