package cg.wbd.grandemonstration.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class Logger {
    public void error() {
        System.out.println("[CMS] ERROR!");
    }

    @AfterReturning(pointcut = "within(cg.wbd.grandemonstration.controller.*)", returning = "result")
    public void log(JoinPoint joinPoint, Object result) {
        System.out.println("Start log");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className + "." + methodName);
        if (result == null) {
            System.out.println("null");
        } else {
            System.out.println(result.hashCode());
        }
    }

    //    @AfterThrowing(pointcut = "within(cg.wbd.grandemonstration.service.*.*)", throwing = "e")
    @AfterThrowing(pointcut = "execution(public * cg.wbd.grandemonstration.service.*.*(..))", throwing = "e")
    public void log(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("[CMS] co loi xay ra: %s.%s%s: %s", className, method, args, e.getMessage()));
    }
}
