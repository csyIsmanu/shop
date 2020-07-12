package gudu.csy.service.impl;


import gudu.csy.dao.IOrderDao;
import gudu.csy.dao.IOrderItemDao;
import gudu.csy.dao.IProductDao;
import gudu.csy.model.Order;
import gudu.csy.model.OrderItem;
import gudu.csy.model.Product;
import gudu.csy.model.User;
import gudu.csy.service.IOrderService;
import gudu.csy.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 订单模块业务层
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IOrderItemDao orderItemDao;
    @Autowired
    private IProductDao productDao;
    /**
     * 保存订单
     * @param order
     * @throws Exception
     */
    @Override
    public void save(Order order) throws Exception {
        orderDao.save(order);
        List<Order> byUid = this.findByUid(order.getUser().getUid());
        order.setOid(byUid.get(0).getOid());
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItemDao.save(orderItem);
        }
    }

    /**
     * 根据用户的id查询
     * @param uid
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public PageBean<Order> findByPageUid(int uid, int page) throws Exception {
        PageBean<Order> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 2;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = orderDao.findCountUid(uid);
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        int totalPage = 0;
        // Math.ceil(totalCount / limit);
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 每页显示的数据集合:
        // 从哪开始:
        int begin = (page - 1) * limit;
        List<Order> list = orderDao.findByPageUid(uid, begin, limit);
        //将商品信息封装到list中
        for (Order order : list) {
            Set<OrderItem> byOid = orderItemDao.findByOid(order.getOid());
            order.setOrderItems(byOid);
        }
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Order findByOid(int oid) throws Exception {
        Order byOid = orderDao.findByOid(oid);
        Integer uid = byOid.getUser().getUid();
        String addr = byOid.getAddr();
        int size = byOid.getOrderItems().size();
        return byOid;
    }

    @Override
    public void update(Order byOid) throws Exception {
        orderDao.update(byOid);
    }

    @Override
    public List<Order> findByUid(int uid) throws Exception {
        return orderDao.findByUid(uid);
    }

    @Override
    public PageBean<Order> findByPage(int page) throws Exception {
        PageBean<Order> pageBean = new PageBean<>();
        // 设置当前页数
        pageBean.setPage(page);
        //设置每页显示的记录数
        int limit = 2;
        pageBean.setLimit(limit);
        // 设置总记录数
        int  totalCount = 0;
        totalCount = orderDao.findCount();
        pageBean.setTotalCount(totalCount);
        // 设置总页数
        int totalPage = 0;
        // Math.ceil(totalCount / limit);
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 每页显示的数据集合:
        // 从哪开始:
        int begin = (page - 1) * limit;
        List<Order> list = orderDao.findByPage(begin, limit);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public List<OrderItem> findOrderItem(int oid) throws Exception {
        return orderItemDao.findOrderItem(oid);
    }
}
