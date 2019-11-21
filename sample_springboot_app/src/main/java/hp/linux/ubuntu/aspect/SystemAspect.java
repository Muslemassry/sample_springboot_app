package hp.linux.ubuntu.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class SystemAspect {
	// must receive either a Pointcut's expression or a Pointcut's name
	@Before(value = "execution(* hp.linux.ubuntu.components.*.*(..))")
	public void doBeforeMethodCall (JoinPoint joinPoint) {
		System.out.println("PRINT " + joinPoint.getSignature().getName());
	}
	
	// must receive either a Pointcut's expression or a Pointcut's name
	@After(value = "execution(* hp.linux.ubuntu.components.*.*(..))")
	public void doAferMethodCall (JoinPoint joinPoint) {
		System.out.println("PRINT " + joinPoint.getSignature().getName());
	}
	
}
