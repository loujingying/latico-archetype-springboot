package com.latico.archetype.springboot.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latico.archetype.springboot.common.util.StrUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <PRE>
 * 示例，包方式的切面
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-07-12 16:28:45
 * @Version: 1.0
 */
@Component
@Aspect
public class DemoPkgAspect {

    private static final Logger LOG = LoggerFactory.getLogger(DemoPkgAspect.class);

    /**
     * 利用匹配包下面的方法
     */
    private final String POINT_CUT_PACKAGE = "execution(public * com.latico.archetype.springboot.controller.aspect.pkg.*.*(..))";


    @Pointcut(POINT_CUT_PACKAGE)
    public void pointCutPackage() {
    }

    @Before(value = "pointCutPackage()")
    public void before(JoinPoint joinPoint) {
        LOG.info("PKG @Before通知执行");
        //获取目标方法参数信息
        Object[] args = joinPoint.getArgs();
        
        LOG.info("PKG " + StrUtils.join(args));

        //aop代理对象
        Object aThis = joinPoint.getThis();
        LOG.info(aThis.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd

        //被代理对象
        Object target = joinPoint.getTarget();
        LOG.info(target.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd

        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        LOG.info(signature.toLongString()); //public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String)
        LOG.info(signature.toShortString()); //HelloController.getName(..)
        LOG.info(signature.toString()); //String com.xhx.springboot.controller.HelloController.getName(String)
        //获取方法名
        LOG.info(signature.getName()); //getName
        //获取声明类型名
        LOG.info(signature.getDeclaringTypeName()); //com.xhx.springboot.controller.HelloController
        //获取声明类型  方法所在类的class对象
        LOG.info(signature.getDeclaringType().toString()); //class com.xhx.springboot.controller.HelloController
        //和getDeclaringTypeName()一样
        LOG.info(signature.getDeclaringType().getName());//com.xhx.springboot.controller.HelloController

        //连接点类型
        String kind = joinPoint.getKind();
        LOG.info(kind);//method-execution

        //返回连接点方法所在类文件中的位置  打印报异常
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        LOG.info(sourceLocation.toString());
        //LOG.info(sourceLocation.getFileName());
        //LOG.info(sourceLocation.getLine()+"");
        //LOG.info(sourceLocation.getWithinType().toString()); //class com.xhx.springboot.controller.HelloController

        ///返回连接点静态部分
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
        LOG.info(staticPart.toLongString());  //execution(public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String))


        //attributes可以获取request信息 session信息等
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOG.info(request.getRequestURL().toString()); //http://127.0.0.1:8080/hello/getName
        LOG.info(request.getRemoteAddr()); //127.0.0.1
        LOG.info(request.getMethod()); //GET

        LOG.info("PKG before通知执行结束");
    }


    /**
     * 后置返回
     * 如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     * 参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = POINT_CUT_PACKAGE, returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object result) {
        LOG.info("PKG 第一个后置返回通知的返回值：" + result);
    }

    @AfterReturning(value = POINT_CUT_PACKAGE, returning = "result", argNames = "result")
    public void doAfterReturningAdvice2(String result) {
        LOG.info("PKG 第二个后置返回通知的返回值：" + result);
    }
    //第一个后置返回通知的返回值：姓名是大大
    //第二个后置返回通知的返回值：姓名是大大
    //第一个后置返回通知的返回值：{name=小小, id=1}


    /**
     * 后置异常通知
     * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     * throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = POINT_CUT_PACKAGE, throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        LOG.info(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException) {
            LOG.info("PKG 发生了空指针异常!!!!!");
        }
    }

    @After(value = POINT_CUT_PACKAGE)
    public void doAfterAdvice(JoinPoint joinPoint) {
        LOG.info("PKG 后置通知执行了!");
    }

    /**
     * 环绕通知：
     * 注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     * <p>
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = POINT_CUT_PACKAGE)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LOG.info("PKG @Around环绕通知：" + proceedingJoinPoint.getSignature().toString());
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed(); //可以加参数
            LOG.info(obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LOG.info("PKG @Around环绕通知执行结束");
        return obj;
    }
}