package com.latico.archetype.springboot.common.aspect;

import com.latico.archetype.springboot.common.anno.TimeStatisAspectAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <PRE>
 * 时间统计切面
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-07-12 16:28:33
 * @Version: 1.0
 */
@Component
@Aspect
public class TimeStatisAnnoAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TimeStatisAnnoAspect.class);

    @Pointcut("@annotation(timeStatisAspectAnnotation)")
    public void annoPointcut(TimeStatisAspectAnnotation timeStatisAspectAnnotation) {
    }

    /**
     * 耗时统计
     */
    @Around("annoPointcut(timeStatisAspectAnnotation)")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, TimeStatisAspectAnnotation timeStatisAspectAnnotation) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        HttpServletRequest httpServletRequest = null;
        if (args != null && args.length >= 1) {
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    httpServletRequest = (HttpServletRequest) arg;
                }
            }
        }

        long usedTime = System.currentTimeMillis() - startTime;

        if (httpServletRequest != null) {
            LOG.info("耗时统计-路径:[{}] 方法:[{}] 客户端:[{}:{}] 耗时:[{}]", httpServletRequest.getRequestURI(), methodName, httpServletRequest.getRemoteHost(), httpServletRequest.getRemotePort(), usedTime);
        } else {
            LOG.info("耗时统计-方法:[{}] 耗时:[{}]", methodName, usedTime);
        }

        return obj;
    }
}