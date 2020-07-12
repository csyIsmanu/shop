package gudu.csy.service;

import gudu.csy.model.Order;
import gudu.csy.model.OrderItem;
import gudu.csy.model.User;
import gudu.csy.utils.PageBean;

import java.util.List;
import java.util.Set;

public interface IOrderService {
    /**
     * 保存订单
     * @param order
     * @throws Exception
     */
    public void save(Order order)throws Exception;

    /**
     * 根据用户的id查询
     * @param uid
     * @param page
     * @return
     * @throws Exception
     */
    public PageBean<Order> findByPageUid(int uid, int page)throws Exception;

    public Order findByOid(int oid)throws Exception;

    public void update(Order byOid)throws Exception;

    public List<Order> findByUid(int uid)throws Exception;

    public PageBean<Order> findByPage(int page)throws Exception;

    public List<OrderItem> findOrderItem(int oid)throws Exception;
}
