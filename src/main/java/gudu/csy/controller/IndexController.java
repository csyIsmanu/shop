package gudu.csy.controller;

import gudu.csy.model.Category;
import gudu.csy.model.Product;
import gudu.csy.service.ICategoryService;
import gudu.csy.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("shop")
public class IndexController {
    @Autowired
    private ICategoryService categoryService;//注入一级分类
    @Autowired
    private IProductService productService;//注入商品

    /**
     * index界面访问
     * @return
     */
    @RequestMapping("index.do")
    public String index(HttpSession session)throws Exception{
//        将一级目录存进session域
        List<Category> categoryList = categoryService.findAll();
        session.setAttribute("categoryList",categoryList);

        //        将热门商品存进session域
        List<Product> productByIsHotList = productService.findByIsHot(1);
        session.setAttribute("productByHotList",productByIsHotList);

        //        将最新商品存进session域
        List<Product> pNewList = productService.findNew();
        session.setAttribute("pNewList",pNewList);
        return "index";
    }
    /**
     * login界面访问
     */
    @RequestMapping("login.do")
    public String login(HttpSession session)throws Exception{
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
        if(categoryList==null){
            return "redirect:index.do";
        }
        return "login";
    }
    /**
     * Cat界面访问
     */
    @RequestMapping("cat.do")
    public String Cat(HttpSession session)throws Exception{
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
        if(categoryList==null){
            return "redirect:index.do";
        }
        return "cat";
    }
    /**
     * 注册页面访问
     * @return
     * @throws Exception
     */
    @RequestMapping("regist.do")
    public String regist(HttpSession session)throws Exception{
        List<Category> categoryList = (List<Category>) session.getAttribute("categoryList");
        if(categoryList==null){
            return "redirect:index.do";
        }
        return "regist";
    }
}
