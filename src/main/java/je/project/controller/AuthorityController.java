package je.project.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import je.project.domain.Auth;
import je.project.domain.Res;
import je.project.domain.Result;
import je.project.domain.Staff;
import je.project.service.AuthorityService;
import je.project.utils.LogUtils;

@Controller
@RequestMapping("/login")
public class AuthorityController {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    private AuthorityService authorityService;

    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping("/login")
    String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        Random ran = new Random();
        Integer rnd = ran.nextInt();
        model.addAttribute("rnd", rnd);
        request.getSession().setAttribute("rnd", rnd);
        return "login/login";
    }

    @RequestMapping("/login/handle")
    @ResponseBody
    Result loginHandle(@RequestParam("user") String user, @RequestParam("hashcode") String hashCode,
            @RequestParam("vcode") String vcode,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String vrifyCode=(String)session.getAttribute("vrifyCode");
        /*if(vrifyCode==null||vrifyCode==""){
            System.out.println("1");
            return new Result(1, "登录失败，验证码错误！");
        }
        if(!(vcode.toLowerCase().equals(vrifyCode.toLowerCase()))){
            session.removeAttribute("vrifyCode");
            System.out.println("2");
            return new Result(1, "登录失败，验证码错误！");
        }*/
        String rnd = session.getAttribute("rnd").toString();
        Staff res = authorityService.login(user, rnd, hashCode);
        if (res == null) {
            session.removeAttribute("vrifyCode");
            return new Result(1, "登录失败，请检查用户名密码！");
        }
        session.setAttribute("staff", res);
        LogUtils.i(this, res+"登录系统。");
        return new Result(0, "登录成功。");
    }

    @RequestMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/login/login");
    }

    @RequestMapping("/get")
    public @ResponseBody Res test2(@RequestParam("page") int page,@RequestParam("limit") int limit,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        List<Auth> b = authorityService.getall();

        b.add(new Auth());

        List<Auth> c = new ArrayList<>();
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

    @RequestMapping("/show")
    public String  test(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        

        return "auth/auth";
    }

    @RequestMapping("/update")
    public @ResponseBody Res  test3(@RequestParam("name")String name,@RequestParam("id")Integer id,@RequestParam("user")String user,@RequestParam("password")String password,@RequestParam("position")Integer position,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        
        if(position>5){
            Res res = new Res(-1,":",1,1);
            return res;
        }
        
        Auth auth = new Auth();
        auth.setId(id);
        auth.setName(name);
        auth.setPassword(password);
        auth.setPosition(position);
        auth.setUser(user);
        int  s[]=authorityService.update(auth);
        if(s[0]>0 && s[0]>0){
            Res res = new Res(1,":",1,1);
            return res;
        }
        else if(s[0]==0){
            Res res = new Res(2,":",1,1);
            return res;
        }
        else{
            Res res = new Res(3,":",1,1);
            return res;
        }
       
        
    }

    @RequestMapping("/del")
    public void  test4(@RequestParam("id")Integer id,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        
        authorityService.del(id);
        
    }

    @RequestMapping("/insert")
    public @ResponseBody Res  test5(@RequestParam("name")String name,@RequestParam("id")Integer id,@RequestParam("user")String user,@RequestParam("password")String password,@RequestParam("position")Integer position,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        if(position>5){
            Res res = new Res(-1,":",1,1);
            return res;
        }
        
        Auth auth = new Auth();
        auth.setId(id);
        auth.setName(name);
        auth.setPassword(password);
        auth.setPosition(position);
        auth.setUser(user);
        int s[]=authorityService.insert(auth);
        if(s[0]>0 && s[0]>0){
            Res res = new Res(1,":",1,1);
            return res;
        }
        else{
            Res res = new Res(2,"",1,1);
            return res;
        }
        
        
    }

    





}