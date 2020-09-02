package je.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import je.project.domain.Custom;
import je.project.service.CustomService;

@Controller
@RequestMapping("/custom")
public class CustomController{
    @Autowired
    private CustomService customService;

    @RequestMapping("/search")
    public String search(@RequestParam("idcard") String idCard,Model model){
        model.addAttribute("custom", customService.search(idCard));
        return "custom/show";
    }

    @RequestMapping("/insert")
    public String insert(@RequestParam("idcard") String idCard,@RequestParam("type") Integer type,
    @RequestParam("company") String company,@RequestParam("ftel")String fTel,@RequestParam("tel")String tel,
    @RequestParam("address")String address,@RequestParam("postcode")String postcode,
    @RequestParam("name")String name,@RequestParam("email")String email,Model model){
        if(customService.insertCustom(idCard, type, company, fTel, tel, address, postcode, name, email)){
            model.addAttribute("text", "插入成功。");
        }else{
            model.addAttribute("text", "插入失败！");
        }
        return "custom/result";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") Integer id,Model model){
        Custom res=customService.getCustomById(id);
        model.addAttribute("custom", res);
        return "custom/edit";
    }

    @RequestMapping("/edit/handle")
    public String editHandle(@RequestParam("id")Integer id,@RequestParam("idcard") String idCard,@RequestParam("type") Integer type,
    @RequestParam("company") String company,@RequestParam("ftel")String fTel,@RequestParam("tel")String tel,
    @RequestParam("address")String address,@RequestParam("postcode")String postcode,
    @RequestParam("name")String name,@RequestParam("email")String email,Model model){
        if(customService.updateCustom(id,idCard, type, company, fTel, tel, address, postcode, name, email)){
            model.addAttribute("text", "修改成功。");
        }else{
            model.addAttribute("text", "修改失败！");
        }
        return "custom/result";
    }

    @RequestMapping("/delete")
    public String deleteCustom(@RequestParam("id")Integer id,Model model){
        if(customService.deleteCustom(id)){
            model.addAttribute("text", "删除成功。");
        }else{
            model.addAttribute("text", "删除失败！");
        }
        return "custom/result";
    }
}