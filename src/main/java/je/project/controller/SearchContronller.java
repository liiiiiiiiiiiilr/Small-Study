package je.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import je.project.domain.Cost;
import je.project.domain.Custom;
import je.project.domain.Hub;
import je.project.domain.Repair;
import je.project.domain.Res;
import je.project.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchContronller {
    @Autowired
    private SearchService a;

    @RequestMapping("/findcustom")
    public @ResponseBody Res findecustom(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        List<Custom> b = a.searchallCustom();
        for(Custom item:b){
            System.out.println(item);
        }
        model.addAttribute("custom", b);
        List<Custom> c = new ArrayList<>();
        if(page==1){
            int m;
            if(limit>b.size()){
                m=b.size();
            }
            else{
                m=limit;
            }
            for(int i=0;i<m;i++){
                c.add(b.get(i));
            }
        }
        else{
            
            int s = limit*(page-1);
            System.out.println("b size:"+b.size()+"limit "+limit+"sum"+s);
            int m;
            if(s+limit>b.size()){
                m=b.size();
            }
            else{
                m=s+limit;
            }
            for(int i=s;i<m;i++){
                c.add(b.get(i));
            }
        }
        Res res = new Res(0,":",1000,c);
        return res;
    }
    

    @RequestMapping("/findhub")
    public @ResponseBody Res findhub(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        List<Hub> b = a.searchallHub();
        model.addAttribute("hub", b);
        List<Hub> c = new ArrayList<>();
        if(page==1){
            int m;
            if(limit>b.size()){
                m=b.size();
            }
            else{
                m=limit;
            }
            for(int i=0;i<m;i++){
                c.add(b.get(i));
            }
        }
        else{
            
            int s = limit*(page-1);
            System.out.println("b size:"+b.size()+"limit "+limit+"sum"+s);
            int m;
            if(s+limit>b.size()){
                m=b.size();
            }
            else{
                m=s+limit;
            }
            for(int i=s;i<m;i++){
                c.add(b.get(i));
            }
        }
        Res res = new Res(0,":",1000,c);
        return res;
    }

    @RequestMapping("/findcost")
    public @ResponseBody Res cost(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        List<Cost> b = a.seachallCost();
        model.addAttribute("cost", b);
        List<Cost> c = new ArrayList<>();
        if(page==1){
            int m;
            if(limit>b.size()){
                m=b.size();
            }
            else{
                m=limit;
            }
            for(int i=0;i<m;i++){
                c.add(b.get(i));
            }
        }
        else{
            
            int s = limit*(page-1);
            System.out.println("b size:"+b.size()+"limit "+limit+"sum"+s);
            int m;
            if(s+limit>b.size()){
                m=b.size();
            }
            else{
                m=s+limit;
            }
            for(int i=s;i<m;i++){
                c.add(b.get(i));
            }
        }
        Res res = new Res(0,":",1000,c);
        return res;
    }

    @RequestMapping("/findrepair")
    public @ResponseBody Res repair(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        List<Repair> b = a.searchallRepair();
        List<Repair> c = new ArrayList<>();
        if(page==1){
            int m;
            if(limit>b.size()){
                m=b.size();
            }
            else{
                m=limit;
            }
            for(int i=0;i<m;i++){
                c.add(b.get(i));
            }
        }
        else{
            
            int s = limit*(page-1);
            System.out.println("b size:"+b.size()+"limit "+limit+"sum"+s);
            int m;
            if(s+limit>b.size()){
                m=b.size();
            }
            else{
                m=s+limit;
            }
            for(int i=s;i<m;i++){
                c.add(b.get(i));
            }
        }
        Res res = new Res(0,":",1000,c);
        return res;
    }

    
    
     @RequestMapping("/customid")
    public @ResponseBody Res customid(@RequestParam("id") String name,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        List<Custom> e = a.searchCustombyName(name);
        for (Custom user : e) {
            System.out.println(user);
        }
        Res res = new Res(0,":",1000,e);
        return res;
    }

    @RequestMapping("/hubid")
    public @ResponseBody Res hubid(@RequestParam("name") String name,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        List<Hub> d = a.searchHubByname(name);
        Res res = new Res(0,":",1000,d);
        return res;
    }

    

    @RequestMapping("/repairid")
    public @ResponseBody Res repairid(@RequestParam("name") Integer name,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        List<Repair> e = a.searchRepairbyId(name);
        for (Repair user : e) {
            System.out.println(user);
        }

        Res res = new Res(0,":",1000,e);
        return res;
    }

    @RequestMapping("/costid")
    public @ResponseBody Res costid(@RequestParam("id") Integer id,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        
        List<Cost> e = a.seachCostbyId(id);
        for (Cost user : e) {
            System.out.println(user);
        }

        Res res = new Res(0,":",1000,e);
        return res;
        
    }

    @RequestMapping("/cost")
    public String start(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        return "search/cost";
    }

    @RequestMapping("/hub")
    public String start1(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        return "search/hub";
    }
    @RequestMapping("/repair")
    public String start2(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        return "search/repair";
    }
    @RequestMapping("/custom")
    public String start3(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        return "search/custom";
    }





}