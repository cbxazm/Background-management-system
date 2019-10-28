package com.cbx.controller;

import com.cbx.domain.SysLog;
import com.cbx.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private ISysLogService iSysLogService;
//    通过在web.xml里配置的Listener
    @Autowired
    private HttpServletRequest request;
    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    /**
     * 前置通知 主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
     * @param joinPoint
     */
    @Before("execution(* com.cbx.controller.*.*(..))")
      public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
                           visitTime=new Date();//当前时间就是访问的时间
                    clazz=joinPoint.getTarget().getClass();//获取当前访问的类
                   String methodName=joinPoint.getSignature().getName();//获取方法的名字
        Object[] args = joinPoint.getArgs();//获取访问方法的参数
        //获取具体执行的方法的Method对象
        if(args==null||args.length==0){
            method=clazz.getMethod(methodName);//这样只能获取无参数的方法
        }else {
               Class[] classArgs=new Class[args.length];
               for (int i=0;i<args.length;i++){
                   classArgs[i]=args[i].getClass();
               }
            clazz.getMethod(methodName,classArgs);
        }

      }
    /**
     * 后置通知
     */
    @After("execution(* com.cbx.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint){
              long time =new Date().getTime()-visitTime.getTime();//获取访问时长
        //获取url
        String url="";
          if(clazz!=null&&method!=null&&clazz!=LogAop.class){
               //获取类上的@RequestMapping注解
              RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
               if(classAnnotation!=null){
                   String[] classValue = classAnnotation.value();
                   //获取方法上的@RequestMapping的值
                   RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                      if(methodAnnotation!=null){
                          String[] methodValue = methodAnnotation.value();
                          url=classValue[0]+methodValue[0];
                      }

               }
          }
          //获取访问的ip地址
           String ip=request.getRemoteAddr();
          //获取当前的操作用户
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User principal = (User) context.getAuthentication().getPrincipal();
        String username = principal.getUsername();
        /**
         * 将日志相关信息封装到SysLog对象去
         */
        SysLog sysLog=new SysLog();
        sysLog.setVisitTime(visitTime);
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLog.setUrl(url);
           sysLog.setUsername(username);
        /**
         * 调用service
         */
     iSysLogService.save(sysLog);
    }
}
