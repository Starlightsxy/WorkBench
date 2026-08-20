package com.work.bench.aspect;

import cn.hutool.json.JSONUtil;
import com.work.bench.annotation.AroundLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 日志切入类
 *
 * @author 洁心未眠
 * @Package com.work.bench.aspect
 * @date 2026/8/13 23:17
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    /**
     * Before 切入点 只要标注了BeforeLog这个注解的方法就会实现
     */
    @Pointcut("@annotation(com.work.bench.annotation.BeforeLog)")
    public void beforePointcut() {

    }

    /**
     * Around切入点 只要标注了AroundLog这个注解的方法就会实现
     */
    @Pointcut("@annotation(com.work.bench.annotation.AroundLog)")
    public void aroundPointcut() {

    }

    /**
     * 记录请求日志的切面
     *
     * @param joinPoint
     */
    @Before("beforePointcut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            addLog(joinPoint, "", 0);
        } catch (Exception e) {
            log.error("doBefore 日志异常，", e);
        }
    }

    /**
     * 记录请求和响应日志的切面
     *
     * @param joinPoint
     */
    @Around("aroundPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try {
            long startTime = System.currentTimeMillis();
            result = joinPoint.proceed(args);
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String outParams = JSONUtil.toJsonStr(result);
            // 若结果为空或特殊类型，可作处理
            if (result == null) {
                outParams = "null";
            } else if (result instanceof ResponseEntity) {
                outParams = "ResponseEntity status=" + ((ResponseEntity<?>) result).getStatusCode();
            }
            addLog(joinPoint, outParams, time);
        } catch (Exception e) {
            log.error("doAround日志记录异常，信息为：", e);
            throw e;
        }
        return result;
    }

    /**
     * 日志记录入库操作
     *
     * @param joinPoint
     * @param outParams
     * @param time
     */
    public void addLog(JoinPoint joinPoint, String outParams, long time) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info("""
                        
                        \r=====================================
                        \r\
                        请求地址：{}\s
                        \r\
                        请求方式：{}\s
                        \r\
                        请求类方法：{}\s
                        \r\
                        请求方法参数：{}\s
                        \r\
                        返回报文：{}\s
                        \r\
                        处理耗时：{}ms\s
                        \r\
                        =====================================
                        \r""",
                request.getRequestURL(),
                request.getMethod(),
                joinPoint.getSignature(),
                JSONUtil.toJsonStr(filterArgs(joinPoint.getArgs())),
                outParams,
                String.valueOf(time)
        );
    }

    /**
     * 过滤一些参数类型：文件类型、httpServlet、httpResponse类型
     *
     * @param args
     * @return
     */
    private Object[] filterArgs(Object[] args) {
        if (args == null) return new Object[0];
        return Arrays.stream(args)
                .map(arg -> {
                    if (arg == null) return null;
                    // 处理 Http 相关对象
                    if (arg instanceof HttpServletRequest) {
                        return "HttpServletRequest";  // 或只记录请求URL
                    }
                    if (arg instanceof HttpServletResponse) {
                        return "HttpServletResponse";
                    }
                    // 处理文件上传
                    if (arg instanceof MultipartFile) {
                        MultipartFile file = (MultipartFile) arg;
                        return String.format("MultipartFile[name=%s, size=%d]", file.getOriginalFilename(), file.getSize());
                    }
                    // 处理集合/数组过大
                    if (arg instanceof Collection) {
                        Collection<?> coll = (Collection<?>) arg;
                        if (coll.size() > 10) {
                            return "Collection[size=" + coll.size() + "]";
                        }
                    }
                    // 其他对象直接返回（由 JSONUtil 序列化）
                    return arg;
                })
                .toArray();
    }
}
