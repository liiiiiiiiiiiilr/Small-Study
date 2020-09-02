package je.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.management.openmbean.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import je.project.domain.Auth;
import je.project.domain.Staff;
import je.project.service.AuthorityService;
import je.project.utils.StringUtils;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Staff login(String username, String rndStr, String hashCode) {
        if (username == null || hashCode == null || rndStr == null) {
            return null;
        }
        String sql = "select * from login where user=?";
        Map<String, Object> tmp = null;
        try {
            tmp = jdbcTemplate.queryForMap(sql, username);
        } catch (Exception e) {
            return null;
        }
        System.out.println(tmp.get("password"));
        System.out.println(StringUtils.getMD5(tmp.get("password") + rndStr));
        if (StringUtils.getMD5(tmp.get("password") + rndStr).equals(hashCode.toUpperCase())) {
            List<Staff> res = jdbcTemplate.query("select * from staff where id=?",
                    new Object[] { (Integer) tmp.get("eid") }, new BeanPropertyRowMapper<Staff>(Staff.class));
            if (res.isEmpty()) {
                return null;
            }
            return res.get(0);
        }
        return null;
    }

    @Override
    public List<Auth> getall() {
        String sql = "select staff.id,position,name,user,password from staff left join login on staff.id = login.eid";
        RowMapper<Auth> rowMapper = new BeanPropertyRowMapper<Auth>(Auth.class);
        List<Auth> s = jdbcTemplate.query(sql, rowMapper);
        return s;
    }

    @Override
    public int[] update(Auth auth) {
        int login = jdbcTemplate.update("UPDATE login set user=? ,password=? WHERE eid=?", auth.getUser(),
                auth.getPassword(), auth.getId());
        int staff = jdbcTemplate.update("UPDATE staff set position=? ,name=? WHERE id=?", auth.getPosition(),
                auth.getName(), auth.getId());
        int s[] = new int[2];
        s[0] = login;
        s[1] = staff;
        return s;
    }

    @Override
    public int[] del(int id) {
        int login = jdbcTemplate.update("DELETE FROM login WHERE eid = ?", id);
        int staff = jdbcTemplate.update("DELETE FROM staff WHERE id = ?", id);
        int s[] = new int[2];
        s[0] = login;
        s[1] = staff;
        return s;

    }

    @Override
    public int[] insert(Auth auth) {

        try {
            int login=jdbcTemplate.update("insert into login(user,password,eid,quanxian) values(?,?,?,1)",
                    auth.getUser(), auth.getPassword(), auth.getId());
            int staff=jdbcTemplate.update("insert into staff values(?,?,?)", auth.getId(), auth.getPosition(),
                    auth.getName());
            int s[] =new int[2] ;

            System.out.println("login:"+login+"staff:"+staff);
            s[0]=login;
            s[1]=staff;
            return s;
        } catch (InvalidKeyException e) {
            jdbcTemplate.update("DELETE FROM staff WHERE name = ?", auth.getName());
            jdbcTemplate.update("DELETE FROM login WHERE eid = ? and user =''", auth.getId());
            
            int s[] =new int[2];
            s[0]=0;
            s[1]=0;
            return s;
        }catch (DataAccessException e){
            jdbcTemplate.update("DELETE FROM staff WHERE name = ?", auth.getName());
            jdbcTemplate.update("DELETE FROM login WHERE eid = ? and user =''", auth.getId());
            int s[] =new int[2] ;
            s[0]=0;
            s[1]=0;
            return s;
        }
        
    }
}