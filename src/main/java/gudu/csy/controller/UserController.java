package gudu.csy.controller;

import gudu.csy.model.Category;
import gudu.csy.model.User;
import gudu.csy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * 用户web层控制器
 *
 */
@Controller//定义为controller类并交给spring容器处理
@RequestMapping("user")//是一个用来处理请求地址映射的注解
public class UserController {

    @Autowired//它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
    private IUserService userService;

    /**
     * 根据用户名查询
     * @throws Exception
     */
    @RequestMapping("/findByName.do")
    public void findByName(String username, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //初始化
        String message="";
        boolean flag=true;
        //查询是否有输入的用户名
        User user = userService.findByUsername(username);
        //如果为1（没有该用户名）即可用
        if(user==null){
            message="用户名可用";
        }else{
            flag=false;
            message="用户名已经存在，请使用其他用户名";
        }
        //设置返回数据为utf-8
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<response>");
        out.println("<passed>" + Boolean.toString(flag) + "</passed>");
        out.println("<message>" + message + "</message>");
        out.println("</response>");
    }
    //用户添加
    @RequestMapping("save.do")
    public ModelAndView save(User user, HttpServletRequest request) throws Exception {
        List<Category> categoryList = (List<Category>) request.getSession().getAttribute("categoryList");
        if(categoryList==null){
            ModelAndView mv = new ModelAndView("redirect:/shop/index.do");
            return mv;
        }
        ModelAndView mv = new ModelAndView();
        String verifyCodeValue = (String) request.getSession().getAttribute("verifyCodeValue");
        String checkcode = request.getParameter("checkcode");
        if(!checkcode.equalsIgnoreCase(verifyCodeValue)){
            mv.addObject("error","*注册失败：验证码不一致");
            mv.setViewName("regist");
            return mv;
        }
        userService.save(user);
        User user1 = userService.findByUsername(user.getUsername());
        String msg="注册不成功，请重新注册";
        if(user1!=null)
            msg = "*注册成功，请前往邮箱进行激活";
        mv.addObject("msg",msg);
        mv.setViewName("msg");
        return mv;
    }
    //用户激活
    @RequestMapping("userActive.do")
    public ModelAndView userActive(String code) throws Exception {
        boolean exitUser = userService.findByCode(code);
        String msg="账户激活不成功";
        if(exitUser == true)
            msg = "账户激活成功,请去登录";
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("msg");
        return mv;
    }

    /**
     * 用户登录操作
     * @param user
     * @return
     * @throws Exception
     */

    @RequestMapping("login.do")
    public ModelAndView login(User user, HttpServletRequest request,HttpSession session) throws  Exception{
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
        if(categoryList==null){
            ModelAndView mv = new ModelAndView("redirect:/shop/index.do");
            return mv;
        }
        ModelAndView mv = new ModelAndView();
        String verifyCodeValue = (String) request.getSession().getAttribute("verifyCodeValue");
        String checkcode = request.getParameter("checkcode");
        if(!checkcode.equalsIgnoreCase(verifyCodeValue)){
            mv.addObject("error","*登录失败：验证码不一致");
            mv.addObject("user",null);
            mv.setViewName("login");
            return mv;
        }
        User user1 = userService.login(user);
        if(user1!=null){
            //登录成功
            request.getSession().setAttribute("user",user1);
            mv.setViewName("index");
        }else{
            //登录失败
            mv.addObject("error","*登录失败：用户未激活/用户密码错误/账号错误");
            mv.addObject("user",null);
            mv.setViewName("login");
        }
        return mv;
    }
    @RequestMapping("quit.do")
    public String quit(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:../shop/index.do";
    }
    @RequestMapping("userUpdate.do")
    public String userUpdate(int uid,HttpSession session)throws Exception{
        User user = userService.findByUid(uid);
        session.setAttribute("user",user);
        return "UserEdit";
    }
    @RequestMapping("Update.do")
    public String Update(User user)throws Exception{
        userService.update(user);
        return "redirect:../shop/index.do";
    }
}
