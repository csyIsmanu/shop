package gudu.csy.controller;

import gudu.csy.model.*;
import gudu.csy.service.*;
import gudu.csy.utils.PageBean;
import gudu.csy.utils.UUIDUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 后台前端控制器
 */
@Controller
@RequestMapping("admin")
public class AdminController{

    @Autowired
    private IAdminService adminService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICategorySecondService categorySecondService;
    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    //--------------------------------后台跳转登录页面(设置访问权限)--------------------------------
    @RequestMapping("index.do")
    public String index()throws Exception{
        return "admin/index";
    }
    //构建index页面
    @RequestMapping("top.do")
    public String top(HttpSession session)throws Exception{
        Admin user = (Admin) session.getAttribute("adminUser");
        if (user==null){
            return "redirect:index.do";
        }
        return "admin/top";
    }
    @RequestMapping("left.do")
    public String left(HttpSession session)throws Exception{
        Admin user = (Admin) session.getAttribute("adminUser");
        if (user==null){
            return "redirect:index.do";
        }
        return "admin/left";
    }
    @RequestMapping("bottom.do")
    public String bottom(HttpSession session)throws Exception{
        Admin user = (Admin) session.getAttribute("adminUser");
        if (user==null){
            return "redirect:index.do";
        }
        return "admin/bottom";
    }
    @RequestMapping("welcome.do")
    public String welcome(HttpSession session)throws Exception{
        Admin user = (Admin) session.getAttribute("adminUser");
        if (user==null){
            return "redirect:index.do";
        }
        return "admin/welcome";
    }

    /**
     * 管理者登录
     * @param username
     * @param password
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("login.do")
    public String login(String username, String password, HttpSession session)throws Exception{
        Admin admin = adminService.login(username,password);
        String error ="";
        if (admin != null){
            if (!admin.getPassword().equals(password)){
                error = "*密码出错";
                session.setAttribute("error",error);
                session.setAttribute("user",null);
                return "admin/index";
            }
            session.setAttribute("adminUser",admin);
            return "admin/home";
        }
        error = "*没有该用户";
        session.setAttribute("error",error);
        session.setAttribute("user",null);

        return "admin/index";
    }
//--------------------------------用户管理--------------------------------
    @Autowired
    private IUserService userService;

    @RequestMapping("findAllUser.do")
    public String findAllUser(int page,HttpSession session)throws Exception{
        PageBean<User> pageBean = userService.findPage(page);
        session.setAttribute("pageBean",pageBean);
        return "admin/user/list";
    }
    @RequestMapping("userUpdate.do")
    public String userUpdate(int uid,HttpSession session)throws Exception{
        User user = userService.findByUid(uid);
        session.setAttribute("user",user);
        return "admin/user/edit";
    }
    @RequestMapping("updateSave.do")
    public String updateSave(User user)throws Exception{
        userService.update(user);
        return "redirect:findAllUser.do?page=1";
    }

    //--------------------------------一级分类增删改查--------------------------------
    //查询所有一级类别
    @RequestMapping("findAllCategory.do")
    public String findAllCategory(HttpSession session)throws Exception{
        List<Category> categoryList = categoryService.findAll();
        session.setAttribute("cList",categoryList);
        return "admin/category/list";
    }
    //添加一级类别
     @RequestMapping("add.do")
        public String add()throws Exception{
            return "admin/category/add";
        }
    @RequestMapping("saveCategory.do")
    public String saveCategory(HttpServletRequest request)throws Exception{
        String cname = request.getParameter("cname");
        categoryService.save(cname);
        return "redirect:findAllCategory.do";
    }
    //删除一级类别
    @RequestMapping("delectCategory.do")
    public String delectCategory(String cid)throws Exception{
        categoryService.delect(cid);

        return "redirect:findAllCategory.do";
    }


    //修改一级类别
     @RequestMapping("edit.do")
        public String edit(String cid,HttpSession session)throws Exception{
            Category category = categoryService.findByCid(cid);
            session.setAttribute("category",category);
            return "admin/category/edit";
        }
    @RequestMapping("editCategory.do")
    public String editCategory(String cname,String cid)throws Exception{
        categoryService.update(cname,cid);
        return "redirect:findAllCategory.do";
    }
//--------------------------------二级分类增删改查--------------------------------
    @RequestMapping("findAllCategorySecond.do")
    public String findAllCategorySecond(String page,HttpSession session)throws Exception{
        PageBean<CategorySecond> pageBean = categorySecondService.findByPage(Integer.parseInt(page));
        session.setAttribute("pageBean",pageBean);
        return "admin/categorySecond/list";
    }
    //二级分类增
    @RequestMapping("addSecond.do")
    public String addSecond(HttpSession session)throws Exception{
        List<Category> categories = categoryService.findAll();
        session.setAttribute("cList",categories);
        return "admin/categorySecond/add";
    }
    @RequestMapping("saveCategorySecond.do")
    public String saveCategorySecond(String csname, int cid,HttpSession session)throws Exception{
        categorySecondService.save(csname,cid);
        PageBean<CategorySecond> pageBean = (PageBean<CategorySecond>) session.getAttribute("pageBean");
        return "redirect:findAllCategorySecond.do?page="+pageBean.getTotalPage();
    }
    //删除二级分类
    @RequestMapping("delectSecond.do")
    public String delectSecond(int csid,HttpSession session,int page)throws Exception{
        categorySecondService.delete(csid);
        return "redirect:findAllCategorySecond.do?page="+page;
    }
    //修改二级分类
    @RequestMapping("editSecond.do")
    public String editSecond(int csid,HttpSession session,int page)throws Exception{
        CategorySecond categorySecond = categorySecondService.findByCsid(csid);
        List<Category> cList = categoryService.findAll();
        session.setAttribute("cList",cList);
        session.setAttribute("page",page);
        session.setAttribute("categorySecond",categorySecond);
        return "admin/categorySecond/edit";
    }
    @RequestMapping("editCategorySecond.do")
    public String editCategorySecond(int csid,int cid,String csname,HttpSession session)throws Exception{
        categorySecondService.update(csid,csname,cid);
        int page = (int) session.getAttribute("page");
        return "redirect:findAllCategorySecond.do?page="+page;
    }

    //--------------------------------商品管理--------------------------------
    //查询所有商品
    @RequestMapping("findAllProduct.do")
    public String findAllProduct(int page,HttpSession session)throws Exception{
        PageBean<Product> pageBean = productService.findByPage(page);
        session.setAttribute("pageBean",pageBean);
        return "admin/product/list";
    }
    //添加商品
    @RequestMapping("addProduct.do")
    public String addProduct(HttpSession session)throws Exception{
        List<CategorySecond> csList = categorySecondService.findAll();
        session.setAttribute("csList",csList);
        return "admin/product/add";
    }
    @RequestMapping("saveProduct.do")
    public String saveProduct(@RequestParam(value = "upload", required = false)MultipartFile upload,Product product,HttpServletRequest request)throws Exception{
        product.setPdate(new Date());
        String realPath = null;
        String fileName = null;
        if (!upload.isEmpty()) {
            //获取项目中ClassPath所指的在本地的位置（一般指向到webapp目录）；getRealPath给目录再补充添加；
            realPath = request.getSession().getServletContext().getRealPath("/products");
            //重命名文件名，防止文件名重复
            fileName = UUIDUtils.getUUID()+upload.getOriginalFilename()
                    .substring(upload.getOriginalFilename().lastIndexOf("."));
            //设置文件保存位置
            File targetFile = new File(realPath,fileName);
            //判断目标文件夹是否存在，进行创建
            if (!targetFile.exists()){
                targetFile.mkdirs();
            }
            //文件上传
            upload.transferTo(targetFile);
        }
        //赋值 图片的路径名称
        product.setImage("products/"+fileName);
        productService.save(product);
        PageBean<Product> pageBean = (PageBean<Product>) request.getSession().getAttribute("pageBean");
        return "redirect:findAllProduct.do?page=1";
    }

    //删除产品
    @RequestMapping("deleteProduct.do")
    public String deleteProduct(int pid,HttpSession session)throws Exception{
        productService.delete(pid);
        PageBean<Product> pageBean = (PageBean<Product>) session.getAttribute("pageBean");
        return "redirect:findAllProduct.do?page="+pageBean.getPage();
    }
    //修改商品
    @RequestMapping("editProct.do")
    public String editProct(int pid,HttpSession session)throws Exception{
        //根据id查商品
        Product product = productService.findByPid(pid);
        //查询所有二级类别
        List<CategorySecond> categorySeconds = categorySecondService.findAll();
        session.setAttribute("product",product);
        session.setAttribute("csList",categorySeconds);
        return "admin/product/edit";
    }
    @RequestMapping("updateProduct.do")
    public String updateProduct(@RequestParam(value = "upload", required = false)MultipartFile upload,Product product,HttpServletRequest request)throws Exception{
        product.setPdate(new Date());
        String realPath = null;
            String fileName = null;
            if (!upload.isEmpty()) {
                //删除原来上传的图片
                String delPath = request.getSession().getServletContext().getRealPath("/" + product.getImage());
                File file = new File(delPath);
                file.delete();
                //获取项目中ClassPath所指的在本地的位置（一般指向到webapp目录）；getRealPath给目录再补充添加；
                realPath = request.getSession().getServletContext().getRealPath("/products");
                //重命名文件名，防止文件名重复
                fileName = UUIDUtils.getUUID()+upload.getOriginalFilename().substring(upload.getOriginalFilename().lastIndexOf("."));
                //设置文件保存位置
                File targetFile = new File(realPath,fileName);
                //判断目标文件夹是否存在，进行创建
                if (!targetFile.exists()){
                    targetFile.mkdirs();
                }
                //文件上传
                upload.transferTo(targetFile);
                //赋值 图片的路径名称
                product.setImage("products/"+fileName);
            }
            productService.update(product);
        PageBean<Product> pageBean = (PageBean<Product>) request.getSession().getAttribute("pageBean");

        return "redirect:findAllProduct.do?page="+pageBean.getPage();
    }
    //--------------------------------订单管理-----------------------------------------------

    @RequestMapping("findAllOrder.do")
    public String findAllOrder(int page,HttpSession session)throws Exception{
        PageBean<Order> pageBean = orderService.findByPage(page);
        int page1 = pageBean.getPage();
        int totalPage = pageBean.getTotalPage();
        session.setAttribute("pageBean",pageBean);
        return "admin/order/list";
    }
    //订单详情查询
    @RequestMapping("findOrderItem.do")
    public String findOrderItem(int oid,HttpSession session)throws Exception{
         List<OrderItem> orderItems = orderService.findOrderItem(oid);
         session.setAttribute("list",orderItems);
        return "admin/order/orderItem";
    }
    //修改状态
    @RequestMapping("updateState.do")
    public String updateState(int oid,HttpSession session)throws Exception{
        Order order = orderService.findByOid(oid);
        order.setState(3);
        orderService.update(order);
        PageBean<Order> pageBean = (PageBean<Order>) session.getAttribute("pageBean");

        return "redirect:findAllOrder.do?page="+pageBean.getPage();
    }


}
