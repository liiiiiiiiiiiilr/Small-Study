package je.project.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import je.project.domain.Repair;
import je.project.domain.Staff;
import je.project.service.RepairService;

/**
 * 维修
 * 
 * @author 刘海鑫
 */
@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Repair> getAllTasks() {
        return jdbcTemplate.query("select * from repair", new BeanPropertyRowMapper<Repair>(Repair.class));
    }

    @Override
    public List<Repair> getUnDelieveredTasks() {
        return jdbcTemplate.query("select * from repair where status=0",
                new BeanPropertyRowMapper<Repair>(Repair.class));
    }

    @Override
    public List<Repair> getUnDelieveredTasks(int sec) {
        return jdbcTemplate.query("select * from repair where status=0 and unix_timestamp(?) -unix_timestamp(time)>?",
                new Object[]{new Timestamp(System.currentTimeMillis()),sec},
                new BeanPropertyRowMapper<Repair>(Repair.class));
    }

    @Override
    public List<Repair> getOnesTasks(Integer engineerid) {
        return jdbcTemplate.query("select * from repair where engineerid=? order by status", new Object[] { engineerid },
                new BeanPropertyRowMapper<Repair>(Repair.class));
    }

    @Override
    public String getEngineerName(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select name from staff where position=3 and id=?", String.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Staff> getAllEngineers() {
        return jdbcTemplate.query("select * from staff where position=3", new BeanPropertyRowMapper<Staff>(Staff.class));
    }

    @Override
    public Integer getWorkCount(Integer id) {
        Integer res=null;
        try {
            res = jdbcTemplate.queryForObject("select count(*) from repair where engineerid=? and status>0 and status<3", Integer.class, id);
        } catch (Exception e) {
            res= null;
        }
        if(res==null){
            return 0;
        }
        return res;
        
    }

    @Override
    public Boolean delieverTo(Integer rid, Integer eid) {
        return jdbcTemplate.update("update repair set engineerid=?,status=1 where id=?", eid,rid)>0;
    }

    @Override
    public Staff getEngineerByid(Integer id) {
        List<Staff> res=jdbcTemplate.query("select * from staff where id=?", new Object[]{id},new BeanPropertyRowMapper<Staff>(Staff.class));
        if(res.isEmpty()){
            return null;
        }
        return res.get(0);
    }

    @Override
    public Repair getRepairTask(Integer id) {
        List<Repair> res=jdbcTemplate.query("select * from repair where id=?", new Object[]{id},new BeanPropertyRowMapper<Repair>(Repair.class));
        if(res.isEmpty()){
            return null;
        }
        return res.get(0);    }

    @Override
    public Boolean doRepair(Integer id, String scan, String repair, String workload, String parts, Integer status) {
        return jdbcTemplate.update("update repair set scan=?,repair=?, time=? ,workload=? , parts=? , status=? where id=?", 
        scan,repair,new Timestamp(System.currentTimeMillis()), workload, parts, status, id) > 0;
        }

    @Override
    public Boolean delRepairTask(Integer id) {
        return jdbcTemplate.update("delete from repair where id=?", id)>0;
    }
}