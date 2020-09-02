package je.project.service.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import je.project.service.LogService;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void writeLog(Integer level, String label, String content) {
        jdbcTemplate.update("insert into log(ltime,level,label,content) values (?,?,?,?)",
                new Timestamp(System.currentTimeMillis()), level, label, content);
    }

    @Override
    public List<Map<String, Object>> getLogs(Integer level, Timestamp sTime, Timestamp eTime) {
        return jdbcTemplate.queryForList("select * from log where level>=? and ltime >= ? and ltime <= ?",level,sTime,eTime);
    }

    @Override
    public void removeOldLogs(Integer days) {
        LocalDate localDate=LocalDate.now();
        localDate=localDate.plusDays(-days+1);
        Timestamp timestamp=new Timestamp(localDate.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli());
        jdbcTemplate.update("delete from log where ltime<?",timestamp);
    }
}