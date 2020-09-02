package je.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import je.project.domain.Cost;
import je.project.domain.Custom;
import je.project.domain.Hub;
import je.project.domain.Repair;
import je.project.service.SearchService;

/*
String sql="select id,name,deptid from user";

RowMapper<User> rowMapper=new BeanPropertyRowMapper<User>(User.class);
List<User> users= jdbcTemplate.query(sql, rowMapper);
for (User user : users) {
    System.out.println(user);
}

*/
@Service
public class SearchServiceImp implements SearchService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Custom> searchallCustom() {
        String sql="select * from user";
        RowMapper<Custom> rowMapper=new BeanPropertyRowMapper<Custom>(Custom.class);
        System.out.println(jdbcTemplate);
        List<Custom> s= jdbcTemplate.query(sql, rowMapper);
        return s;
    }

    @Override
    public List<Repair> searchallRepair() {
        String sql="select * from repair";
        RowMapper<Repair> rowMapper=new BeanPropertyRowMapper<Repair>(Repair.class);
        List<Repair> s= jdbcTemplate.query(sql, rowMapper);
        return s;
    }

    @Override
    public List<Hub> searchallHub() {
        String sql="select * from hub";
        RowMapper<Hub> rowMapper=new BeanPropertyRowMapper<Hub>(Hub.class);
        List<Hub> s= jdbcTemplate.query(sql, rowMapper);
        //ystem.out.println(rowMapper);
        //System.out.println(name);
        //System.out.println(jdbcTemplate);
        //System.out.println(s);
        return s;
    }

    @Override
    public List<Cost> seachallCost() {
        String sql="select * from cost";
        RowMapper<Cost> rowMapper=new BeanPropertyRowMapper<Cost>(Cost.class);
        List<Cost> s= jdbcTemplate.query(sql, rowMapper);
        //ystem.out.println(rowMapper);
        //System.out.println(name);
        //System.out.println(jdbcTemplate);
        //System.out.println(s);
        return s;
    }

    @Override
    public List<Custom> searchCustombyName(String name) {
        name=name.trim();
        name="%"+name+"%";
        String sql="select * from user where name like ?";
        RowMapper<Custom> rowMapper=new BeanPropertyRowMapper<Custom>(Custom.class);
        List<Custom> s= jdbcTemplate.query(sql, new Object[]{name},rowMapper);
        //ystem.out.println(rowMapper);
        //System.out.println(name);
        //System.out.println(jdbcTemplate);
        //System.out.println(s);
        return s;
    }

    @Override
    public List<Repair> searchRepairbyId(int id) {
        String sql="select * from repair where engineerid=?";
        RowMapper<Repair> rowMapper=new BeanPropertyRowMapper<Repair>(Repair.class);
        List<Repair> s= jdbcTemplate.query(sql,new Object[]{id},rowMapper);
        //ystem.out.println(rowMapper);
        //System.out.println(name);
        //System.out.println(jdbcTemplate);
        //System.out.println(s);
        return s;
    }

    @Override
    public List<Hub> searchHubByname(String name) {
        name=name.trim();
        name="%"+name+"%";
        String sql="select * from hub where name like ?";
        RowMapper<Hub> rowMapper=new BeanPropertyRowMapper<Hub>(Hub.class);
        List<Hub> s= jdbcTemplate.query(sql,new Object[]{name},rowMapper);
        //ystem.out.println(rowMapper);
        //System.out.println(name);
        //System.out.println(jdbcTemplate);
        //System.out.println(s);
        return s;
    }

    @Override
    public List<Cost> seachCostbyId(int id) {
        String sql="select * from cost where id=?";
        RowMapper<Cost> rowMapper=new BeanPropertyRowMapper<Cost>(Cost.class);
        List<Cost> s= jdbcTemplate.query(sql,new Object[]{id} ,rowMapper);
        //ystem.out.println(rowMapper);
        //System.out.println(name);
        //System.out.println(jdbcTemplate);
        //System.out.println(s);
        return s;
    }

   
}

