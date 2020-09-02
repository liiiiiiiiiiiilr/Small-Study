package je.project.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import je.project.domain.Custom;
import je.project.service.CustomService;

@Service
public class CustomServiceImpl implements CustomService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Custom> search(String idCard) {
        List<Custom> res = jdbcTemplate.query("select * from user where idcard like ?",
                new Object[] { "%" + idCard + "%" }, new BeanPropertyRowMapper<Custom>(Custom.class));
        return res;
    }

    @Override
    public Boolean insertCustom(String idCard, Integer type, String company, String fTel, String tel, String address,
            String postcode, String name, String email) {
        return jdbcTemplate.update(
                "insert into user(idcard,stime,type,company,ftel,tel,address,postcode,"
                        + "name,email) values(?,?,?,?,?,?,?,?,?,?)",
                idCard, new Timestamp(System.currentTimeMillis()), type, company, fTel, tel, address, postcode, name,
                email) > 0;
    }

    @Override
    public Custom getCustomById(Integer id) {
        List<Custom> res = jdbcTemplate.query("select * from user where id = ?", new Object[] { id },
                new BeanPropertyRowMapper<Custom>(Custom.class));
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public Boolean updateCustom(Integer id,String idCard, Integer type, String company, String fTel, String tel, String address,
            String postcode, String name, String email) {
        return jdbcTemplate.update(
                "update user set idCard=?,stime=?,type=?,company=?,ftel=?,tel=?,address=?,postcode=?,name=?,email=? where id=?",
                idCard,new Timestamp(System.currentTimeMillis()),type,company,fTel,tel,address,postcode,name,email,id) > 0;
    }

    @Override
    public Boolean deleteCustom(Integer id) {
        return jdbcTemplate.update("delete from user where id=?",id)>0;
    }
}