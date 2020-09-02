package je.project.service;

import java.util.List;

import je.project.domain.Auth;
import je.project.domain.Staff;

public interface AuthorityService {
    Staff login(String username,String rndStr,String hashCode);

    List<Auth> getall();

    int[] update(Auth auth);
    
    int[] del(int id);

    int[] insert(Auth auth);
}