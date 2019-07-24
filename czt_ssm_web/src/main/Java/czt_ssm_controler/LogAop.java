package czt_ssm_controler;

import czt_ssm_domain.Syslog;
import czt_ssm_service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISyslogService iSyslogService;

    /*
     *前置通知
    *execution(* czt_ssm_controler.*.*(..))  表示所有修饰符下的所有返回值czt_ssm_controler
    *下所有类,所有方法
    *在所有方法之前执行
    *JoinPoint获得目标方法的所有参数
    * 获取开始时间,执行的类是哪一个,执行的是哪一个方法
    */
    @Before("execution(* czt_ssm_controler.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception{
        visitTime = new Date(); //当前时间
        clazz = jp.getTarget().getClass(); //获取当前的类
        String methodd = jp.getSignature().getName(); //获取当前返回方法名
        Object[] args = jp.getArgs(); //获取方法的参数

        //获取访问的方法(带参数)
        if (args == null || args.length==0){
            method = clazz.getMethod(methodd);
        }else {
            Class[] classArgs = new Class[args.length];
            for(int i=0 ;i<args.length;i++){
                classArgs[i] = args[i].getClass();
            }

            method = clazz.getMethod(methodd,classArgs);
        }


    }


    //后置通知  方法调用后执行的方法
    @After("execution(* czt_ssm_controler.*.*(..))")
    public void doAfter(JoinPoint jp){
        //getTime 得到的是Date的毫秒数
        //获取访问的时长
        String url="";
        long time = new Date().getTime() - visitTime.getTime();

        //获取url
        if (clazz != null &&method!=null &&clazz!=LogAop.class) {
           //1.获取类上的@RequestMapping("/order")
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] clazzValue = clazzAnnotation.value();
                //2.获取方法上的@RequestMapping("/ccc.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                String[] methodValue = methodAnnotation.value();
                url = clazzValue[0] + methodValue[0];
            }
        }

        //获取访问的ip地址(通过request对象)
        String ip = request.getRemoteAddr();

        //获取操作者用户
        SecurityContext context = SecurityContextHolder.getContext();
        String username = ((User) (context.getAuthentication().getPrincipal())).getUsername();

        //将日志相关信息封装到Syslog对象
        Syslog syslog = new Syslog();
        syslog.setExecutionTime(time);
        syslog.setIp(ip);
        syslog.setMethod("[类名] "+clazz.getName()+"[方法名 ]"+method.getName());
        syslog.setUrl(url);
        syslog.setUsername(username);
        syslog.setVisitTime(visitTime);


        //调用service完成insert操作
        iSyslogService.save(syslog);
    }


}

