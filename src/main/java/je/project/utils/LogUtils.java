package je.project.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import je.project.service.LogService;

/**
 * 日志工具，仿照android的Log类
 * @author 刘海鑫
 */
@Component
public class LogUtils{
    @Autowired
    private LogService logService;
    @Value("${log.optputlevel}")
    private Integer outputLevel;

    private static LogUtils logUtils;

    @PostConstruct
    public void init(){
        logUtils=this;
        logUtils.logService=this.logService;
    }

    /**
     * 记录普通日志
     * @param context 上下文，一般传入this
     * @param content 日志内容
     */
    public static void v(Object context,String content){
        logUtils.logService.writeLog(0, context.getClass().getName(), content);
        if(logUtils.outputLevel<=0){
            System.out.println("[VERBOSE]\t["+context.getClass().getSimpleName()+"]\t"+content);
        }
    }

    /**
     * 记录DEBUG日志
     * @param context 上下文，一般传入this
     * @param content 日志内容
     */
    public static void d(Object context,String content){
        logUtils.logService.writeLog(1, context.getClass().getName(), content);
        if(logUtils.outputLevel<=1){
            System.out.println("[DEBUG]\t["+context.getClass().getSimpleName()+"]\t"+content);
        }
    }

    /**
     * 记录Information
     * @param context 上下文，一般传入this
     * @param content 日志内容
     */
    public static void i(Object context,String content){
        logUtils.logService.writeLog(2, context.getClass().getName(), content);
        if(logUtils.outputLevel<=2){
            System.out.println("[INFO]\t["+context.getClass().getSimpleName()+"]\t"+content);
        }
    }

    /**
     * 记录Warning
     * @param context 上下文，一般传入this
     * @param content 日志内容
     */
    public static void w(Object context,String content){
        logUtils.logService.writeLog(3, context.getClass().getName(), content);
        if(logUtils.outputLevel<=3){
            System.out.println("[WARN]\t["+context.getClass().getSimpleName()+"]\t"+content);
        }
    }

    /**
     * 记录Error
     * @param context 上下文，一般传入this
     * @param content 日志内容
     */
    public static void e(Object context,String content){
        logUtils.logService.writeLog(4, context.getClass().getName(), content);
        if(logUtils.outputLevel<=4){
            System.out.println("[ERROR]\t["+context.getClass().getSimpleName()+"]\t"+content);
        }
    }
}