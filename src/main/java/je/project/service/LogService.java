package je.project.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author 刘海鑫 记录日志
 */
public interface LogService{
    /**
     * 记录日志
     * @param level 日志等级
     * @param label 标签
     * @param content 内容
     */
    void writeLog(Integer level,String label,String content);

    /**
     * 获取发生时间在sTime~eTime之间的等级大于等于level的日志
     * @param level 日志等级
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @return 日志
     */
    List<Map<String,Object>> getLogs(Integer level,Timestamp sTime,Timestamp eTime);

    /**
     * 删除老日志
     * @param days 日志保留的天数
     */
    void removeOldLogs(Integer days);
}