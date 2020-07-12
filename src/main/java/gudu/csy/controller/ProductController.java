package gudu.csy.controller;

import gudu.csy.model.Product;
import gudu.csy.service.ICategoryService;
import gudu.csy.service.IProductService;
import gudu.csy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    /**
     * 根据商品id查询商品信息
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("findByPid.do")
    public ModelAndView findByPid(int pid)throws Exception{
        ModelAndView mv = new ModelAndView();
        Product product = productService.findByPid(pid);
        mv.addObject("product",product);
        mv.setViewName("product");
        return mv;
    }

    /*
     * 根据商品一级类别编号查询商品
     */
    @RequestMapping("findByCid.do")
    public String findByCid(HttpServletRequest request)throws Exception{
        int cid= Integer.parseInt(request.getParameter("cid"));
//        将cid存入session，为了方便取出
        request.getSession().setAttribute("cid",cid);
        //当有cid时为了应对jsp界面的判断，应该将session中的csid设置为null;
        request.getSession().setAttribute("csid",null);
        int page = Integer.parseInt(request.getParameter("page"));
        //因为session域中已经存在一级分类，此处可以不用再次查询
        PageBean<Product> PageBean = productService.findByPageCid(cid,page);//根据一级分类查询商品，带分页的
        request.getSession().setAttribute("pageBean",PageBean);
        return "productList";//page.jsp便是一级分类界面
    }

    /*
     * 根据商品二级类别编号查询商品
     */
    @RequestMapping("findByCsid.do")
    public String findByCsid(HttpServletRequest request)throws Exception{
        int csid= Integer.parseInt(request.getParameter("csid"));
        request.getSession().setAttribute("csid",csid);
        //当有csid时为了应对jsp界面的判断，应该将session中的cid设置为null;
        request.getSession().setAttribute("cid",null);
        int page= Integer.parseInt(request.getParameter("page"));
        PageBean<Product> PageBean = productService.findByPageCsid(csid,page);//根据一级分类查询商品，带分页的
        request.getSession().setAttribute("pageBean",PageBean);
        return "productList";
    }
}
