package gudu.csy.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import gudu.csy.model.*;
import gudu.csy.service.IOrderService;
import gudu.csy.service.IProductService;
import gudu.csy.service.IUserService;
import gudu.csy.service.impl.ProductServiceImpl;
import gudu.csy.utils.AlipayConfig;
import gudu.csy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * 订单模块控制层
 */
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;
    @RequestMapping("saveOrder.do")
    public String saveOrder(HttpServletRequest request)throws Exception{
        // 调用Service完成数据库插入的操作:
        String msg="";
        Order order = new Order();
        // 设置订单的总金额:订单的总金额应该是购物车中总金额:
        // 购物车在session中,从session总获得购物车信息.
        Cat cat = (Cat) request.getSession()
                .getAttribute("cat");
        if (cat == null){
            msg="亲!您还没有购物!";
            request.getSession().setAttribute("msg",msg);
            return "msg";
        }
        order.setTotal(cat.getTotal());
        // 设置订单的状态
        order.setState(1); // 1:未付款.
        // 设置订单时间
        order.setOrdertime(new Date());
        // 设置订单关联的客户:
        User existUser = (User) request.getSession().getAttribute("user");
        if (existUser == null) {
            msg = "亲!您还没有登录!";
            request.getSession().setAttribute("error",msg);
            return "login";
        }
        order.setUser(existUser);
        // 设置订单项集合:
        for (CatItem catItem : cat.getCatItems()) {
            // 订单项的信息从购物项获得的.
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(catItem.getCount());
            orderItem.setSubtotal(catItem.getSubtotal());
            orderItem.setProduct(catItem.getProduct());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        orderService.save(order);
        // 清空购物车:
        cat.clearCat();

        // 页面需要回显订单信息:
        request.getSession().setAttribute("order",order);
        cat.clearCat();
        return "order";
    }

    /**
     * 我的订单的查询
     * @return
     * @throws Exception
     */
    @RequestMapping("findByUid.do")
    public String findByUid(int uid, int page, HttpServletRequest request)throws Exception{
        //        根据用户的id查询
        PageBean<Order> pageBean = orderService.findByPageUid(uid,page);
        request.getSession().setAttribute("pageBean",pageBean);
        if(pageBean.getList().size()!=0){
            int oid = pageBean.getList().get(0).getOid();
            request.getSession().setAttribute("oid",oid);
        }
        return "orderList";
    }
    @RequestMapping("findByOid.do")
    public String findByOid(int oid,HttpSession session)throws Exception{
        Order order = orderService.findByOid(oid);
        session.setAttribute("order",order);
        return "order";
    }

    //支付方法
    @RequestMapping("payOrder.do")
    public void payOrder(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String oid = request.getParameter("order.oid");
        String addr = request.getParameter("order.user.addr");
        String name = request.getParameter("order.user.name");
        String phone = request.getParameter("order.user.phone");
        String total = request.getParameter("order.total");
        Order currOrder = orderService.findByOid(Integer.parseInt(oid));
        currOrder.setAddr(addr);
        currOrder.setName(name);
        currOrder.setPhone(phone);
        orderService.update(currOrder);
        //跳转到支付宝付款页面
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

//      订单号
        String out_trade_no = String.valueOf(oid);
        out_trade_no  = URLDecoder.decode(out_trade_no,"UTF-8");
//      总金额
        String total_amount = total;
        total_amount = URLDecoder.decode(total_amount,"UTF-8");
//      商品名称，我这里只获取是第一个商品的信息，读者按照实际情况进行编写逻辑
        Order order = orderService.findByOid(Integer.parseInt(oid));
        String subject = "" ;
        String body = "";
        for (OrderItem orderItem : order.getOrderItems()) {
             subject = orderItem.getProduct().getPname();
            subject = URLDecoder.decode(subject,"UTF-8");
            //      商品描述
             body = subject;
            body = URLDecoder.decode(body,"UTF-8");
        }
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); ///创建API对应的request
        // /回调函数
        alipayRequest.setReturnUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+ out_trade_no +"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+ total_amount +"," +
                "    \"subject\":\""+ subject +"\"," +
                "    \"body\":\""+ body +"\"" +
                "    }"+
                "  }");

        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(form);//直接将完整的表单html输出到页面
        response.getWriter().close();

    }

    @RequestMapping("/success.do")
    public String success(String out_trade_no, Double total_amount,HttpServletRequest request)throws Exception{
        Order order = orderService.findByOid(Integer.parseInt(out_trade_no));
        order.setState(2);
        orderService.update(order);
        //使得返回是仍处于登录状态
        int uid = order.getUser().getUid();
        User byUid = userService.findByUid(uid);
        request.getSession().setAttribute("user",byUid);
        PageBean<Order> pageBean = orderService.findByPageUid(uid,1);
        request.getSession().setAttribute("pageBean",pageBean);
        int oid = pageBean.getList().get(0).getOid();
        request.getSession().setAttribute("oid",oid);
        return "orderList";
    }
    @RequestMapping("updateState.do")
    public String updateState(int oid,HttpSession session)throws Exception{
        Order order = orderService.findByOid(oid);
        order.setState(4);
        int uid = order.getUser().getUid();
        orderService.update(order);
        return "redirect:findByUid.do?page=1&uid="+uid;
    }

}
