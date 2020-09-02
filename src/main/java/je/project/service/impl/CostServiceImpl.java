package je.project.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import je.project.domain.Cost;
import je.project.domain.Custom;
import je.project.domain.Detail;
import je.project.domain.Repair;
import je.project.service.CostService;

@Service
public class CostServiceImpl implements CostService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void writeCost(Cost cost) {
        String sql = "insert into cost(id,cost,materialcost,promise,note,time)" + 
                "values(?,?,?,?,?,?)";
        Timestamp d = new Timestamp(System.currentTimeMillis());
        jdbcTemplate.update(sql,cost.getId(),cost.getCost(),cost.getMaterialcost(),
                cost.getPromise(),cost.getNote(),d);
    }

    @Override
    public Cost getCostByID(int id) {
        String sql = "select * from cost where id=?";
        List<Cost> list = jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<Cost>(Cost.class));
        Cost cost = null;
        if(!list.isEmpty()){
            cost = list.get(0);
        }
        return cost;
    }

    @Override
    public void deleteCostByID(int id) {
        String sql = "delect from cost where where id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Cost> getAllCost() {
        String sql = "select * from Cost";
        List<Cost> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Cost>(Cost.class));
        return list;
    }

    @Override
    public void deleteAllCost() {
        String sql = "delect from cost";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Repair> getRepairByStatus() {
        String sql = "select * from repair where status=2 and id not in(select id from cost) ";
        List<Repair> list = jdbcTemplate.query(sql,  new BeanPropertyRowMapper<Repair>(Repair.class));
        return list;
    }

    @Override
    public List<Detail> getDetailByRid(int rid) {
        String sql = "select * from detail where rid=?";
        List<Detail> list = jdbcTemplate.query(sql, new Object[]{rid}, new BeanPropertyRowMapper<Detail>(Detail.class));
        return list;
    }

    @Override
    public void updateCost(Cost cost) {
        String sql = "update cost set cost=?,materialcost=?,promise=?,note=? where id=?";

        jdbcTemplate.update(sql,cost.getCost(),cost.getMaterialcost(),
                cost.getPromise(),cost.getNote(),cost.getId());
    }

    @Override
    public Custom getCustomByUid(int uid) {
        String sql = "select * from user where id=?";
        List<Custom> list = jdbcTemplate.query(sql, new Object[]{uid}, new BeanPropertyRowMapper<Custom>(Custom.class));
        Custom custom = null;
        if(!list.isEmpty()){
            custom = list.get(0);
        }
        return custom;
    }


}
