package je.project.controller;

import je.project.domain.*;
import je.project.service.CostService;
import je.project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/cost")
public class Costcontroller {
    @Autowired
    private CostService costService;

    @Autowired
    private ReportService reportService;

    @RequestMapping("/costmanagement.php")
    public String getAllCost(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException {
        List<Cost> list = costService.getAllCost();
        for (Cost i : list) {
            System.out.println(i);
        }
        model.addAttribute("list", list);
        return "cost/showcost";

    }

    @RequestMapping("/addcost")
    public String writeCost(Cost c) {
        costService.writeCost(c);
        return "redirect:/cost/repair_status_equal_2.php";
    }

    @RequestMapping("/writecost/{thid}")
    public String writeCost(@PathVariable("thid") Integer thid, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("tid",thid);
        List<Detail> list = costService.getDetailByRid(thid);
        System.out.println("待计算零件");
        BigDecimal dcost= new BigDecimal("0");
        for (Detail i : list) {
            System.out.println(i);
            dcost=dcost.add(BigDecimal.valueOf(i.getNum()).multiply(i.getPrice()));
        }
        model.addAttribute("tmc",dcost);
        return "/cost/writecost";
    }

    @RequestMapping("/repair_status_equal_2.php")
    public String getRepair(HttpServletRequest request, HttpServletResponse response, Model model)throws IOException {
        List<Repair> list = costService.getRepairByStatus();
        System.out.println("待维修");
        for (Repair i : list) {
            System.out.println(i);
        }
        model.addAttribute("repairlist", list);
        return "/cost/not_written_cost";
    }

    @RequestMapping("/update/{thid}")
    public String updateCost(@PathVariable("thid") Integer thid, HttpServletRequest request, HttpServletResponse response, Model model) {
        Cost cost = null;
        cost = costService.getCostByID(thid);
        model.addAttribute("thid",cost.getId());
        model.addAttribute("thcost",cost.getCost());
        model.addAttribute("thmaterialcost",cost.getMaterialcost());
        model.addAttribute("thpromise",cost.getPromise());
        model.addAttribute("thnote",cost.getNote());
        return "/cost/updatecost";
    }
    //id,cost,materialcost,promise,note,time
    @RequestMapping("/updatecostaction")
    public String update(Cost c){
        costService.updateCost(c);
        System.out.println("!@#!$C=");
        System.out.println(c);
        return "redirect:/cost/costmanagement.php";
    }
    @RequestMapping("/printhref/{thid}")
    public String printhref(@PathVariable("thid") Integer thid, HttpServletRequest request, HttpServletResponse response, Model model){
        Report report = reportService.geReportByID(thid);
        model.addAttribute("rep",report);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        model.addAttribute("time",d);
        Cost cost = costService.getCostByID(thid);
        model.addAttribute("cost",cost);
        BigDecimal totalAmount = cost.getCost().add(cost.getMaterialcost());
        model.addAttribute("totalAmount",totalAmount);
        Custom custom = costService.getCustomByUid(report.getUid());
        model.addAttribute("custom",custom);
        return "/cost/print";
    }

}
