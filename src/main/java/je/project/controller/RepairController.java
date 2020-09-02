package je.project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import je.project.domain.Repair;
import je.project.domain.Staff;
import je.project.service.RepairService;
import je.project.utils.LogUtils;

@Controller
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    RepairService repairService;
    /**
     * 任务自动分配的延时
     */

    @Value("${repair.autodeliever}")
    private Integer wait;

    @Value("${repair.auto}")
    private Integer autoDeliever;

    @RequestMapping("/tasks")
    String getTasks(Model model) {
        LogUtils.v(this, "查询了所有维修任务。");
        List<Repair> res = repairService.getAllTasks();
        Map<Integer, String> engineers = new HashMap<>();
        for (Repair i : res) {
            if (!engineers.containsKey(i.getEngineerid())) {
                String name = repairService.getEngineerName(i.getEngineerid());
                if (name == null) {
                    name = "";
                }
                engineers.put(i.getEngineerid(), name);
            }
        }
        HashMap<Integer, String> statusMap = new HashMap<>();
        statusMap.put(null, "未知状态");
        statusMap.put(0, "未分配");
        statusMap.put(1, "分配未检测");
        statusMap.put(2, "检测完成维修未完成");
        statusMap.put(3, "维修完成");
        model.addAttribute("statusmap", statusMap);
        model.addAttribute("engineermap", engineers);
        model.addAttribute("repair", res);
        return "repair/tasks";
    }

    @RequestMapping("/undelievered")
    String getUndelieveredTasks(Model model) {
        LogUtils.v(this, "查询了未分配的维修任务");
        HashMap<Integer, String> statusMap = new HashMap<>();
        statusMap.put(null, "未知状态");
        statusMap.put(0, "未分配");
        statusMap.put(1, "分配未检测");
        statusMap.put(2, "检测完成维修未完成");
        statusMap.put(3, "维修完成");
        model.addAttribute("statusmap", statusMap);
        model.addAttribute("repair", repairService.getUnDelieveredTasks());
        return "repair/undelievered";
    }

    @RequestMapping("/test")
    @ResponseBody
    String test() {
        for (Repair i : repairService.getAllTasks()) {
            LogUtils.d(this, i.toString());
        }
        return "";
    }

    @RequestMapping("/deliever")
    String deliever(Model model, @RequestParam("id") Integer rid) {
        HashMap<Integer, Integer> count = new HashMap<>();
        List<Staff> engineers = repairService.getAllEngineers();
        for (Staff i : engineers) {
            count.put(i.getId(), repairService.getWorkCount(i.getId()));
        }
        model.addAttribute("engineers", engineers);
        model.addAttribute("count", count);
        model.addAttribute("rid", rid);
        return "repair/deliever";
    }

    @RequestMapping("/delieverto")
    String delieverto(@RequestParam("sid") Integer sid, @RequestParam("rid") Integer rid, Model model) {
        boolean flag = false;
        flag = repairService.delieverTo(rid, sid);
        if (flag) {
            model.addAttribute("text", "成功将任务分配给对应工程师。");
            return "repair/success";
        }
        model.addAttribute("text", "分配任务失败");
        return "repair/fail";
    }

    @RequestMapping("/ones")
    String getOnesTasks(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "id",required = false) Integer id, Model model) throws IOException {
        HttpSession session=request.getSession();
        if(id==null){
            Staff res= (Staff) session.getAttribute("staff");
            System.out.println(res);
            if(res!=null&&res.getPosition()==3){
                id=res.getId();
            }else{
                response.sendRedirect("/repair/id.html");
                return null;
            }
        }
        List<Repair> res = repairService.getOnesTasks(id);
        LogUtils.v(this, "工程师" + id + "查看了自己的维修任务。"+"共有" + res.size() + "项任务。");
        model.addAttribute("engineer", repairService.getEngineerByid(id));
        model.addAttribute("repair", res);
        return "repair/ones";
    }

    @RequestMapping("/edit")
    String editTask(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("repair", repairService.getRepairTask(id));
        return "repair/edittask";
    }

    @RequestMapping("/edit/save")
    String saveEditedTask(@RequestParam("id") Integer id, @RequestParam("scan") String scan,
            @RequestParam("repair") String repair, @RequestParam("workload") String workload,
            @RequestParam("parts") String parts, @RequestParam("status") Integer status, Model model) {
        boolean flag = repairService.doRepair(id, scan, repair, workload, parts, status);
        if (flag) {
            model.addAttribute("text", "修改成功");
            return "/repair/success";
        }
        model.addAttribute("text", "修改失败");
        return "repair/fail";
    }

    @RequestMapping("/del")
    String delTask(@RequestParam("id") Integer id, Model model) {
        boolean flag = repairService.delRepairTask(id);
        if (flag) {
            model.addAttribute("text", "删除成功");
            return "/repair/success";
        }
        model.addAttribute("text", "删除失败");
        return "repair/fail";
    }

    @RequestMapping("/set")
    String set(Model model) {
        model.addAttribute("wait", wait);
        model.addAttribute("autodeliever", autoDeliever);
        return "repair/set";
    }

    @RequestMapping("/set/handle")
    void setHandle(HttpServletRequest request, HttpServletResponse response, @RequestParam("autodeliever") Integer auto
    ,@RequestParam("wait")Integer wait_n)
            throws IOException {
        autoDeliever=auto;
        wait=wait_n;
        response.sendRedirect("/repair/set");
    }

    /**
     * 定时自动分配任务
     * 
     * @throws ParseException
     */
    @Scheduled(fixedRate = 1000 * 60)
    public void removeOldLogs() throws ParseException {
        if(autoDeliever!=1){
            return;
        }
        List<Repair> list= repairService.getUnDelieveredTasks(wait*60);
        if(list.isEmpty()){
            return;
        }
        List<Staff> x=repairService.getAllEngineers();
        if(x.size()==0){
            return;
        }
        int min=Integer.MAX_VALUE;
        Staff s=null;
        for (Staff i : x) {
            int tmp=repairService.getWorkCount(i.getId());
            if(tmp<min){
                min=tmp;
                s=i;  
            }
        }
        repairService.delieverTo(list.get(0).getId(),s.getId());
        LogUtils.i(this, "自动将任务"+list.get(0).getId()+"分配给"+s.getId());
    }
}