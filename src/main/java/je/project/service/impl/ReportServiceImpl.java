package je.project.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import je.project.domain.Report;
import je.project.domain.User;
import je.project.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void writeReport(Report report) {

        String sql = "insert into report(uid,type,brand,model,serialnum,lack,"+
                "fault,faluttype,apper,password,important,hdd,memory,outerpc,ac,battery"+
                ",cdrom,soft,other,time,price,status) values(?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?)";
        jdbcTemplate.update(sql, report.getUid(), report.getType(),
                report.getBrand(), report.getModel(), report.getSerialnum(), report.getLack(),
                report.getFault(), report.getFaluttype(), report.getApper(),
                report.getPassword(), report.getImportant(), report.getHdd(), report.getMemory(),
                report.getOuterpc(), report.getAc(),
                report.getBattery(), report.getCdrom(), report.getSoft(),
                report.getOther(), report.getTime(), report.getPrice(),report.getStatus());
    }

    @Override
    public Report geReportByID(int id) {
        String sql = "select * from report where id=?";
        List<Report> list = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<Report>(Report.class));
        Report report = null;
        if(!list.isEmpty()){
            report = list.get(0);
        }
        return report;
    }

    @Override
    public void deleteReportByID(int id) {
        String sql = "delete from report where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Report> getAllReport() {
        String sql = "select * from report";
        List<Report> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Report>(Report.class));
        return list;
    }

    @Override
    public void deleteAllReport(){
        String sql = "delete from test";
        jdbcTemplate.update(sql);
    }

    public void update(Report report){
        String sql = "update test.report set uid=?,type=?,brand=?,model=?,serialnum=?,lack=?,fault=?,faluttype=?,apper=?,password=?,important=?,hdd=?,memory=?,outerpc=?,ac=?,battery=?" +
                ",cdrom=?,soft=?,other=?,time=?,price=? where id=?";

        jdbcTemplate.update(sql, report.getUid(), report.getType(),
                report.getBrand(), report.getModel(), report.getSerialnum(), report.getLack(),
                report.getFault(), report.getFaluttype(), report.getApper(),
                report.getPassword(), report.getImportant(), report.getHdd(), report.getMemory(),
                report.getOuterpc(), report.getAc(),
                report.getBattery(), report.getCdrom(), report.getSoft(),
                report.getOther(), report.getTime(), report.getPrice(),report.getId());
    }
    public void updateStatus(Report report){
        String sql = "update test.report set status=? where id=?";
        jdbcTemplate.update(sql, report.getStatus(), report.getId());
    }
    public void insertRepair(Integer id){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String sql = "insert into test.repair(id,time,status) values(?,?,?)";
        jdbcTemplate.update(sql, id, time, 0);
    }
    public User getUser(Integer id){
        String sql = "select * from test.user where id=?";
        List<User> list = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        User user = null;
        if(!list.isEmpty()){
            user = list.get(0);
        }
        return user;
    }
}