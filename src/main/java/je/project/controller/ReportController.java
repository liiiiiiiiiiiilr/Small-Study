package je.project.controller;

import je.project.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import je.project.domain.Report;
import je.project.service.ReportService;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rep")
public class ReportController{

    @Autowired
    private ReportService reportService;
    List<String> types = Arrays.asList("台式机","笔记本","投影仪","打印机","其他");
    List<String> faultTypes = Arrays.asList("固定性故障","间隙性故障");

    List<String> status = Arrays.asList("未打印","打印","提交");


    @GetMapping("/reps")
    public String list(Model model){
        List<Report> lists = reportService.getAllReport();
        model.addAttribute("reps", lists);
        return "rep/list";
    }

    @GetMapping("/rep")
    public String toAddPage(Model model){

        model.addAttribute("types", types);
        model.addAttribute("faultTypes", faultTypes);
        model.addAttribute("status", status);

        return "/rep/add";
    }

    @PostMapping("/rep")
    public String addRep(Report report){
        report.setStatus(0);
        report.setTime( new Timestamp(System.currentTimeMillis()));
        reportService.writeReport(report);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/rep/reps";
    }

    @GetMapping("/rep/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Report report = reportService.geReportByID(id);
        model.addAttribute("rep", report);


        model.addAttribute("types", types);
        model.addAttribute("faultTypes", faultTypes);
        model.addAttribute("status", status);

        return "rep/update";
    }

    @PutMapping("/rep")
    public String updateReport(Report report){
        reportService.update(report);
        return "redirect:/rep/reps";
    }

    @GetMapping("/reprint/{id}")
    public String toPrintPage(@PathVariable("id") Integer id, Model model){
        Report report = reportService.geReportByID(id);
        if(report.getStatus()==0){
            report.setStatus(1);
            reportService.updateStatus(report);
        }
        Timestamp current = new Timestamp(System.currentTimeMillis());
        User user = reportService.getUser(report.getUid());
        if(user == null){
            user = new User();
            user.setCompany("未知");
            user.setName("未知");
        }
        model.addAttribute("user", user);
        model.addAttribute("time", current);
        model.addAttribute("rep", report);
        return "rep/print";
    }

    @GetMapping("/repsubmit/{id}")
    public String submit(@PathVariable("id") Integer id){
        Report report = reportService.geReportByID(id);
        if(report.getStatus()!=2){
            reportService.insertRepair(id);
            report.setStatus(2);
            reportService.updateStatus(report);
        }
        return "redirect:/rep/reps";
    }

    @GetMapping("/repdel/{id}")
    public String del(@PathVariable("id") Integer id){
        reportService.deleteReportByID(id);
        return "redirect:/rep/reps";
    }
}