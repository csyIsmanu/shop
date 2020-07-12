package gudu.csy.controller;
import gudu.csy.model.Cat;
import gudu.csy.model.CatItem;
import gudu.csy.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 购物车web控制器
 */
@Controller
@RequestMapping("cat")
public class CatController {
    @Autowired
    private IProductService productService;

    /**
     * 添加商品进购物车
     * @param pid
     * @param count
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("addCat.do")
    public String addCat(int pid, int count, HttpServletRequest request)throws Exception{
        //创建购物项对象
        CatItem catItem = new CatItem();
        //设置数量
        catItem.setCount(count);
        //设置商品
        catItem.setProduct(productService.findByPid(pid));
        //将购物项添加到购物车,购物车应该存在session中，不然每次都会创建一个新的，之前的就没用了
        Cat cat = getCat(request);
        cat.addCat(catItem);
        return "cat";
    }
    //从session中获得Cat的方法
    public Cat getCat(HttpServletRequest request) {
        Cat cat = (Cat) request.getSession().getAttribute("cat");
        if (cat==null){
            cat = new Cat();
            request.getSession().setAttribute("cat",cat);
        }
        return cat;
    }

    /**
     * 清空购物车
     * @return
     * @throws Exception
     */
    @RequestMapping("clearCat.do")
    public String clearCat(HttpServletRequest request)throws Exception{
        Cat cat = getCat(request);
        cat.clearCat();
        return "cat";
    }
    /**
     * 删除购物车中商品
     * @return
     * @throws Exception
     */
    @RequestMapping("removeCat.do")
    public String removeCat(HttpServletRequest request)throws Exception{
        Cat cat = getCat(request);
        int pid = Integer.parseInt(request.getParameter("pid"));
        cat.removeCat(pid);
        return "cat";
    }

}
